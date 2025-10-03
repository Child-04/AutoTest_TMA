package Tests;

import Base.LoggedInBaseTest;
import Pages.AdminPage_02;
import com.microsoft.playwright.Locator;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC04_AdminTable_SortingTest extends LoggedInBaseTest {

    private AdminPage_02 adminPage;
    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new AdminPage_02(page);
        adminPage.openAdminPage();
    }

    @Test(description = "Verify sorting of Username column in ascending order")
    @Description("System should correctly sort the Username column in ascending order")
    public void TC07_testSortUserNameAsc() {
        String columnName = "Username";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void TC08_testSortUserNameDesc() {
        String columnName = "Username";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void TC09_testSortEmployeeNameAsc() {
        String columnName = "Employee Name";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
    }
    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void TC10_testSortEmployeeNameDesc() {
        String columnName = "Employee Name";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order ")
    public void TC11_testSortUserRoleAsc() {
        String columnName = "User Role";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
    }
    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order ")
    public void TC12_testSortUserRoleDesc() {
        String columnName = "User Role";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC13_testSortStatusAsc() {
        String columnName = "Status";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC14_testSortStatusDesc() {
        String columnName = "Status";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
    }
}