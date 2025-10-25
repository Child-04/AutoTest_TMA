package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import Pages.OrangeHRM.P04_AdminPage_02_StringandLocatorParameter;
import com.microsoft.playwright.Locator;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.data.TestData.*;

public class TC04_AdminTable02_SortingTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC04_AdminTable02_SortingTest.class);

    private P04_AdminPage_02_StringandLocatorParameter adminPage;
    private P01_LoginPage loginPage;

    @BeforeMethod
    public void goToAdminPage() {
        log.info("===== Setup: Open login page and navigate to Admin page =====");
        adminPage = new P04_AdminPage_02_StringandLocatorParameter(page);
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();
        loginPage.enterUserAccount();
        adminPage.openAdminPage();
        log.info("Navigated to Admin page successfully.");
    }

    @Test(description = "Verify sorting of Username column in ascending order")
    @Description("System should correctly sort the Username column in ascending order")
    public void TC07_testSortUserNameAsc() {
        log.info("===== Test: Verify sorting by Username in ascending order =====");
        String columnName = "Username";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        takeScreenshot("sortByUsername_ASC");
        log.info("Verified sorting by Username ascending successfully.");
    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void TC08_testSortUserNameDesc() {
        log.info("===== Test: Verify sorting by Username in descending order =====");
        String columnName = USERNAME;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        takeScreenshot("sortByUsername_DESC");
        log.info("Verified sorting by Username descending successfully.");
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void TC09_testSortEmployeeNameAsc() {
        log.info("===== Test: Verify sorting by Employee Name in ascending order =====");
        String columnName = EMPLOYEE_NAME;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        takeScreenshot("sortByEmployeeName_ASC");
        log.info("Verified sorting by Employee Name ascending successfully.");
    }

    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void TC10_testSortEmployeeNameDesc() {
        log.info("===== Test: Verify sorting by Employee Name in descending order =====");
        String columnName = EMPLOYEE_NAME;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        takeScreenshot("sortByEmployeeName_DESC");
        log.info("Verified sorting by Employee Name descending successfully.");
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order")
    public void TC11_testSortUserRoleAsc() {
        log.info("===== Test: Verify sorting by User Role in ascending order =====");
        String columnName = USER_ROLE;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        takeScreenshot("sortByUserRole_ASC");
        log.info("Verified sorting by User Role ascending successfully.");
    }

    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order")
    public void TC12_testSortUserRoleDesc() {
        log.info("===== Test: Verify sorting by User Role in descending order =====");
        String columnName = USER_ROLE;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        takeScreenshot("sortByUserRole_DESC");
        log.info("Verified sorting by User Role descending successfully.");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order")
    public void TC13_testSortStatusAsc() {
        log.info("===== Test: Verify sorting by Status in ascending order =====");
        String columnName = STATUS;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        takeScreenshot("sortByStatus_ASC");
        log.info("Verified sorting by Status ascending successfully.");
    }

    @Test(description = "Verify that sorting of Status in descending order")
    @Description("System should sort correctly the Status in descending order")
    public void TC14_testSortStatusDesc() {
        log.info("===== Test: Verify sorting by Status in descending order =====");
        String columnName = STATUS;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        takeScreenshot("sortByStatus_DESC");
        log.info("Verified sorting by Status descending successfully.");
    }
}
