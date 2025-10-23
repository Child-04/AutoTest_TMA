package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import Pages.OrangeHRM.P05_AdminPage_Search;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TC05_Admin_SearchTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TC05_Admin_SearchTest.class);
    private P05_AdminPage_Search adminPage;
    private P01_LoginPage loginPage;

    @BeforeMethod
    public void goToAdminPage() {
        log.info("=== Setup: Navigate to Admin Page ===");
        adminPage = new P05_AdminPage_Search(page);
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();
        log.info("Opened login page");
        loginPage.enterUserAccount();
        log.info("Logged in with valid account");
        adminPage.openAdminPage();
        log.info("Opened Admin page");
    }

    @Test(priority = 1, description = "Verify search by valid username returns correct user")
    public void testSearch_ByValidUsername() {
        log.info("=== Test 1: Search by valid username ===");
        adminPage.enterUsername("Admin");
        log.info("Entered username: Admin");
        adminPage.clickSearch();
        log.info("Clicked Search button");
        Assert.assertTrue(
                adminPage.isRecordFound("Admin"),
                "Expected user 'Admin' should be displayed in search results."
        );
        log.info("Verified that record 'Admin' is displayed in results");
        takeScreenshot("testSearch_ByValidUsername");
        log.info("Screenshot captured: testSearch_ByValidUsername");
    }

    @Test(priority = 2, description = "Verify search by invalid username shows 'No Records Found' message")
    public void testSearch_ByInvalidUsername() {
        log.info("=== Test 2: Search by invalid username ===");
        adminPage.enterUsername("wrongName");
        log.info("Entered invalid username: wrongName");
        adminPage.clickSearch();
        log.info("Clicked Search button");
        Assert.assertTrue(
                adminPage.isNoRecordMessageDisplayed(),
                "Expected 'No Records Found' message should be displayed."
        );
        log.info("Verified 'No Records Found' message is displayed");
        Assert.assertTrue(
                adminPage.isNoRecordBoxDisplayed(),
                "Expected 'No Records Found' toast should be displayed."
        );
        log.info("Verified 'No Records Found' toast is displayed");
        takeScreenshot("testSearch_ByInvalidUsername");
        log.info("Screenshot captured: testSearch_ByInvalidUsername");
    }

    @Test(priority = 3, description = "Search with all empty fields - should return full user list")
    public void testSearch_ByAllEmptyFields() {
        log.info("=== Test 3: Search with all empty fields ===");
        adminPage.clickSearch();
        log.info("Clicked Search button with all fields empty");

        Assert.assertTrue(
                adminPage.isRecordDisplayed(),
                "Expected system to display full user list when all search fields are empty."
        );
        log.info("Verified full user list is displayed when search fields are empty");
        page.waitForTimeout(2000);
        takeScreenshot("testSearch_ByAllEmptyFields");
        log.info("Screenshot captured: testSearch_ByAllEmptyFields");
    }

    @Test(priority = 4, description = "Search with User Role = Admin")
    public void testSearchByUserRole() {
        log.info("=== Test 4: Search by User Role = Admin ===");
        adminPage.selectUserRole("Admin");
        log.info("Selected User Role: Admin");
        adminPage.clickSearch();
        log.info("Clicked Search button");
        Assert.assertTrue(adminPage.getNumberOfRecords() > 0,
                "Expected records with User Role = Admin");
        log.info("Verified records found with User Role = Admin");
        takeScreenshot("testSearchByUserRole");
        log.info("Screenshot captured: testSearchByUserRole");
    }

    @Test(priority = 6, description = "Search with Username + User Role + Status")
    public void testSearchByMultipleFilters() {
        log.info("=== Test 5: Search with multiple filters (Username + Role + Status) ===");
        adminPage.enterUsername("Admin");
        log.info("Entered username: Admin");
        adminPage.selectUserRole("Admin");
        log.info("Selected User Role: Admin");
        adminPage.selectStatus("Enabled");
        log.info("Selected Status: Enabled");
        adminPage.clickSearch();
        log.info("Clicked Search button");
        Assert.assertTrue(adminPage.isRecordFound("Admin"),
                "Expected records that match Username = Admin, User Role = Admin, Status = Enabled");
        log.info("Verified record found with multiple filters");
        takeScreenshot("testSearchByMultipleFilters");
        log.info("Screenshot captured: testSearchByMultipleFilters");
    }
}
