package Tests;

import Base.LoggedInBaseTest;
import Pages.AdminPage_01;
import org.testng.annotations.Test;

import java.util.*;

public class AdminTable_GetUserTest_TC02 extends LoggedInBaseTest {
    @Test
    public void TC06_test_GetUserTable() {
        AdminPage_01 adminPage = new AdminPage_01(page);
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
        page.waitForTimeout(10000);
    }
}
