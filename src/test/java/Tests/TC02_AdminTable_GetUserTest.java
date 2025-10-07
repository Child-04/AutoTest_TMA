package Tests;

import Base.LoggedInBaseTest;
import Pages.P02_AdminPage_01_StringParameter;
import org.testng.annotations.Test;

import java.util.*;

public class TC02_AdminTable_GetUserTest extends LoggedInBaseTest {
    @Test
    public void TC06_test_GetUserTable() {
        P02_AdminPage_01_StringParameter adminPage = new P02_AdminPage_01_StringParameter(page);
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
