package Pages.OrangeHRM;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.*;

public class P04_AdminPage_02_StringandLocatorParameter {
    private Page page;
    // Locators (Locator object thay vì String)
    private final Locator adminMenu;
    private final Locator headerSelector;
    private final Locator rowSelector;
    private final Locator cellSelector;
    private final Locator tableBody;

    // Constructor
    public P04_AdminPage_02_StringandLocatorParameter(Page page) {
        this.page = page;
        this.adminMenu = page.locator("//span[text()='Admin']");
        this.headerSelector = page.locator("//div[@class='oxd-table-header']//div[@role='columnheader']");
        this.rowSelector = page.locator("//div[@class='oxd-table-card']");
        this.cellSelector = page.locator("//div[@role='cell']");
        this.tableBody = page.locator("//div[@class='oxd-table-body']");
    }

    @Step("Open Admin Page")
    public void openAdminPage() {
        adminMenu.click();
        page.waitForURL("**/admin/viewSystemUsers");
        tableBody.waitFor();
    }

    // Get headers, skip checkbox and actions
    public List<String> getTableHeaders() {
        int colCount = headerSelector.count();
        List<String> headers = new ArrayList<>();
        for (int i = 1; i < colCount - 1; i++) { // bỏ checkbox đầu và Actions cuối
            headers.add(headerSelector.nth(i).innerText().trim());
        }
        return headers;

    }

    // Get table data as List<Map>
    public List<Map<String, String>> getUserTableData() {
        List<String> headers = getTableHeaders();
        int rowCount = rowSelector.count();
        List<Map<String, String>> tableData = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Locator cells = rowSelector.nth(i).locator(cellSelector);
            int cellCount = cells.count();
            Map<String, String> rowMap = new LinkedHashMap<>();
            for (int j = 1; j < Math.min(cellCount - 1, headers.size() + 1); j++) {
                String cellText = cells.nth(j).innerText().trim();
                rowMap.put(headers.get(j - 1), cellText);
            }
            tableData.add(rowMap);
        }
        return tableData;
    }

    public Locator getColumnLocatorByName(String columnName) {
        int colCount = headerSelector.count();
        for (int i = 0; i < colCount; i++) {
            String headerText = headerSelector.nth(i).innerText().trim();
            if (headerText.equalsIgnoreCase(columnName)) {
                return headerSelector.nth(i);
            }
        }
        throw new RuntimeException("No column found: " + columnName);
    }


    @Step("Click icon sort next to '{columnName}' and select '{sortType}' ")
    public void clickSortAndSelectType(String columnName, Locator columnLocator, String sortType) {
        Locator sortIcon = columnLocator.locator(".oxd-table-header-sort-icon");
        sortIcon.click();
        Locator dropdown = columnLocator.locator(".oxd-table-header-sort-dropdown");
        dropdown.locator("li:has-text('" + (sortType.equals("asc") ? "Ascending" : "Descending") + "')").click();
        page.waitForTimeout(3000);
    }

    // Get value on each column
    public List<String> getColumnData(Locator columnLocator) {
        Locator headers = headerSelector;
        int colCount = headers.count();
        int targetIndex = -1;
        for (int i = 1; i < colCount - 1; i++) {
            if (headers.nth(i).equals(columnLocator)) {
                targetIndex = i;
                break;
            }
        }
        if (targetIndex == -1) throw new RuntimeException("Column locator not found in header");

        Locator rows = rowSelector;
        int rowCount = rows.count();
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Locator cells = rows.nth(i).locator(cellSelector);
            String cellText = cells.nth(targetIndex).innerText().trim();
            columnData.add(cellText);
        }
        return columnData;
    }

    //Print result
    @Step("Verify column '{columnName}' is sorted in '{sortType}' order")
    public void VerifySort(String columnName, Locator columnLocator, String sortType) {
        clickSortAndSelectType(columnName, columnLocator, sortType);

        List<String> actualData = getColumnData(columnLocator);
        List<String> expectedData = new ArrayList<>(actualData);

        if (sortType.equalsIgnoreCase("asc")) {
            Collections.sort(expectedData);
        } else {
            Collections.sort(expectedData, Collections.reverseOrder());
        }

        System.out.println("=== Actual Data (" + columnName + ") ===");
        actualData.forEach(System.out::println);

        System.out.println("=== Expected Data (" + columnName + ") ===");
        expectedData.forEach(System.out::println);

        Assert.assertEquals(actualData, expectedData,
                "Column '" + columnName + "' has not been sorted " + sortType.toUpperCase() + " correctly");
    }
}