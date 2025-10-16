package Pages.OrangeHRM;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.*;

public class P02_AdminPage_01_StringParameter {
    private final Page page;

    // Locators (Locator object thay vì String)
    private final Locator adminMenu;
    private final Locator headerSelector;
    private final Locator rowSelector;
    private final Locator cellSelector;
    private final Locator tableBody;

    // Constructor
    public P02_AdminPage_01_StringParameter(Page page) {
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

    @Step("Click icon sort next to '{columnName}' and select '{sortType}' ")
    public void clickSortAndSelectType(String columnName, String sortType) {
        int colCount = headerSelector.count();
        for (int i = 1; i < colCount - 1; i++) {
            String headerText = headerSelector.nth(i).innerText().trim();
            if (headerText.equalsIgnoreCase(columnName)) {
                Locator sortIcon = headerSelector.nth(i).locator(".oxd-table-header-sort-icon");
                sortIcon.click();
                Locator dropdown = headerSelector.nth(i).locator(".oxd-table-header-sort-dropdown");
                dropdown.locator("li:has-text('" + (sortType.equals("asc") ? "Ascending" : "Descending") + "')").click();
                page.waitForTimeout(3000);
                return;
            }
        }
        throw new RuntimeException("No column found: " + columnName);
    }

    // Get value on each column
    public List<String> getColumnData(String columnName) {
        List<Map<String, String>> tableData = getUserTableData();
        List<String> columnData = new ArrayList<>();
        for (Map<String, String> row : tableData) {
            columnData.add(row.get(columnName));
        }
        return columnData;
    }

    // Print result
    @Step("Verify column '{columnName}' is sorted in '{sortType}' order")
    public void VerifySort(String columnName, String sortType) {

        clickSortAndSelectType(columnName, sortType);

        List<String> actualData = getColumnData(columnName); // List value UI
        List<String> expectedData = new ArrayList<>(actualData); // List value expected
        if (sortType.equals("asc")) {
            Collections.sort(expectedData);
        } else {
            Collections.sort(expectedData, Collections.reverseOrder());
        }

        System.out.println("=== Actual Data (" + columnName + " - " + sortType.toUpperCase() + ") ===");
        actualData.forEach(System.out::println);

        System.out.println("=== Expected Data (" + columnName + " - " + sortType.toUpperCase() + ") ===");
        expectedData.forEach(System.out::println);

        Assert.assertEquals(actualData, expectedData,
                "Column " + columnName + " has not been sorted " + sortType.toUpperCase() + " correctly");
    }
}
