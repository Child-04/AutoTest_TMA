package Tests;

import Base.BaseTest;
import Pages.AdminPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.util.*;

public class AdminTable_GetUser extends BaseTest {
    @Test
    public void AdminTable_GetUser_List()
    {
        //navigage admin page
        AdminPage adminPage = new AdminPage(page);
        adminPage.openAdminPage();

        //Get row list, expect header row
        Locator rows = page.locator("//div[@class='oxd-table-body']/div[@class='oxd-table-card']");
        int rowCount = rows.count();
        System.out.println("Total number of rows: " + rowCount);

        // Store dữ liệu vào List<List<String>>
        List<List<String>> tableData = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            Locator row = rows.nth(i);

            // Get all cells in 1 row
            Locator cells = row.locator("div[role='cell']");
            int cellCount = cells.count();

            List<String> rowData = new ArrayList<>();

            for (int j = 0; j < cellCount; j++) {
                String cellText = cells.nth(j).textContent().trim();
                rowData.add(cellText);
            }

            // Add row vào list chính
            if (!rowData.isEmpty()) {
                tableData.add(rowData);
            }
        }

        // In ra để kiểm tra
        for (int i = 0; i < tableData.size(); i++) {
            System.out.println("Row " + (i + 1) + ": " + tableData.get(i));
        }

        // Ex: Get data in the first cell
        List<String> usernames = new ArrayList<>();
        for (List<String> row : tableData) {
            usernames.add(row.get(1));
        }
        System.out.println("All usernames: " + usernames);
    }

    @Test
    public void AdminTable_GetUser_Map() {
        // Mở trang Admin
        AdminPage adminPage = new AdminPage(page);
        adminPage.openAdminPage();

        // ===== 1. Get header row (key) =====
        Locator headerCells = page.locator("//div[@class='oxd-table-header']//div[@role='columnheader']");
        int colCount = headerCells.count();
        List<String> headers = new ArrayList<>();
        for (int i = 1; i < colCount - 1; i++) {
            headers.add(headerCells.nth(i).innerText().trim());
        }
        System.out.println("Headers: " + String.join(" | ", headers));

        // ===== 2. Get body row =====
        Locator rows = page.locator("//div[@class='oxd-table-body']/div[@class='oxd-table-card']");
        int rowCount = rows.count();
        System.out.println("Total number of rows: " + rowCount);

        List<Map<String, String>> tableData = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            Locator row = rows.nth(i);
            Locator cells = row.locator("//div[@role='cell']");
            int cellCount = cells.count();

            // dùng LinkedHashMap để giữ thứ tự
            Map<String, String> rowMap = new LinkedHashMap<>();
            for (int j = 1; j < cellCount - 1; j++) { // bỏ checkbox và action
                String cellText = cells.nth(j).innerText().trim();
                rowMap.put(headers.get(j - 1), cellText); // headers bắt đầu từ 0
            }
            tableData.add(rowMap);
        }
        // In ra dữ liệu bảng
        for (Map<String, String> row : tableData) {
            System.out.println(row);
        }
    }
}
