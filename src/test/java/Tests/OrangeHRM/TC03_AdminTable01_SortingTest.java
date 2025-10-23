package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import Pages.OrangeHRM.P02_AdminPage_01_StringParameter;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Utils.TestData.*;

public class TC03_AdminTable01_SortingTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC03_AdminTable01_SortingTest.class);
    private P02_AdminPage_01_StringParameter adminPage;
    private P01_LoginPage loginPage;

    @BeforeMethod
    public void goToAdminPage() {
        log.info("===== Setup: Open login page and navigate to Admin page =====");
        adminPage = new P02_AdminPage_01_StringParameter(page);
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();
        loginPage.enterUserAccount();
        adminPage.openAdminPage();
        log.info("Navigated to Admin page successfully.");
    }

    @Test(description = "Verify sorting of Username column in ascending order")
    @Description("System should correctly sort the Username column in ascending order")
    public void testSortUserNameAsc() {
        log.info("===== Test: Verify Username column sorting in ascending order =====");
        adminPage.VerifySort(USERNAME, "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUsername_ASC");
        log.info("Verified sorting by Username ascending successfully.");
    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void testSortUserNameDesc() {
        log.info("===== Test: Verify Username column sorting in descending order =====");
        adminPage.VerifySort(USERNAME, "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUsername_DESC");
        log.info("Verified sorting by Username descending successfully.");
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void testSortEmployeeNameAsc() {
        log.info("===== Test: Verify Employee Name column sorting in ascending order =====");
        adminPage.VerifySort(EMPLOYEE_NAME, "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByEmployeeName_ASC");
        log.info("Verified sorting by Employee Name ascending successfully.");
    }

    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void testSortEmployeeNameDesc() {
        log.info("===== Test: Verify Employee Name column sorting in descending order =====");
        adminPage.VerifySort(EMPLOYEE_NAME, "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByEmployeeName_DESC");
        log.info("Verified sorting by Employee Name descending successfully.");
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order ")
    public void testSortUserRoleAsc() {
        log.info("===== Test: Verify User Role column sorting in ascending order =====");
        adminPage.VerifySort(USER_ROLE , "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUserRole_ASC");
        log.info("Verified sorting by User Role ascending successfully.");
    }

    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order ")
    public void testSortUserRoleDesc() {
        log.info("===== Test: Verify User Role column sorting in descending order =====");
        adminPage.VerifySort(USER_ROLE , "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUserRole_DESC");
        log.info("Verified sorting by User Role descending successfully.");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void testSortStatusAsc() {
        log.info("===== Test: Verify Status column sorting in ascending order =====");
        adminPage.VerifySort(STATUS, "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByStatus_ASC");
        log.info("Verified sorting by Status ascending successfully.");
    }

    @Test(description = "Verify that sorting of Status in descending order")
    @Description("System should sort correctly the Status in descending order ")
    public void testSortStatusDesc() {
        log.info("===== Test: Verify Status column sorting in descending order =====");
        adminPage.VerifySort(STATUS, "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByStatus_DESC");
        log.info("Verified sorting by Status descending successfully.");
    }
}
