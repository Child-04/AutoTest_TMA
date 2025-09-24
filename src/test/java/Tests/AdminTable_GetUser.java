package Tests;

import Base.BaseTest;
import Base.LoggedInBaseTest;
import Pages.AdminPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.util.*;

public class AdminTable_GetUser extends LoggedInBaseTest {
    @Test
    public void test_GetUserTable() {
        AdminPage adminPage = new AdminPage(page);
        adminPage.openAdminPage();
        // Call method in AdminPage
        List<Map<String, String>> tableData = adminPage.getUserTableData();
        for (Map<String, String> row : tableData) {
            System.out.println(row);
        }
        // Ex: Get all usernames
        System.out.println("=== All Usernames ===");
        for (Map<String, String> row : tableData) {
            System.out.println(row.get("Username")); // cột Username
        }
        page.waitForTimeout(30000);
    }
}
