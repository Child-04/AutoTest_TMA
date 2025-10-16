package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import Pages.OrangeHRM.P02_AdminPage_01_StringParameter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class TC02_AdminTable_GetUserTest extends BaseTest {
    private P02_AdminPage_01_StringParameter adminPage;
    private P01_LoginPage loginPage;

    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new P02_AdminPage_01_StringParameter(page);
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();
        loginPage.enterUserAccount();
        adminPage.openAdminPage();
    }

    @Test
    public void TC06_test_GetUserTable() {

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
