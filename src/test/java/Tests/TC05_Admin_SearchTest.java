package Tests;

import Base.LoggedInBaseTest;
import Pages.OrangeHRMPage.P05_AdminPage_Search;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Data.TestData.*;

public class TC05_Admin_SearchTest extends LoggedInBaseTest {
    private P05_AdminPage_Search adminPage;
    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new P05_AdminPage_Search(page);
        adminPage.openAdminPage();
    }

    @Test(priority = 1, description = "Verify search by valid username returns correct user")
    public void testSearch_ByValidUsername() {
        adminPage.enterUsername(VALID_USERNAME);
        adminPage.clickSearch();
        Assert.assertTrue(
                adminPage.isRecordFound(VALID_USERNAME),
                "Expected user 'Admin' should be displayed in search results."
        );

        takeScreenshot("testSearch_ByValidUsername");
    }

    @Test(priority = 2, description = "Verify search by invalid username shows 'No Records Found' message")
    public void testSearch_ByInvalidUsername() {
        adminPage.enterUsername(INVALID_USERNAME);
        adminPage.clickSearch();
        Assert.assertTrue(
                adminPage.isNoRecordMessageDisplayed(),
                "Expected 'No Records Found' message should be displayed."
        );
        Assert.assertTrue(
                adminPage.isNoRecordBoxDisplayed(),
                "Expected 'No Records Found' toast should be displayed."
        );
        takeScreenshot("testSearch_ByInvalidUsername");
    }

    @Test(priority = 3, description = "Verify search by valid Status returns correct user")
    public void testSearch_ByValidStatus() {
        adminPage.selectStatus(STATUS_DISABLED);
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.getNumberOfRecords() > 0,
                "Expected records with Status = Disable");

        takeScreenshot("testSearch_ByValidStatus");
    }



    @Test(priority = 4, description = "Search with User Role = Admin")
    public void testSearchByUserRole() {
        adminPage.selectUserRole(ROLE_ADMIN);
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.getNumberOfRecords() > 0,
                "Expected records with User Role = Admin");
        takeScreenshot("test Search By User Role");
    }

    @Test(priority = 6, description = "Search with Username + User Role + Status")
    public void testSearchByMultipleFilters() {
        adminPage.enterUsername(VALID_USERNAME);
        adminPage.selectUserRole(ROLE_ADMIN);
        adminPage.selectStatus(STATUS_ENABLED);
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.isRecordFound(VALID_USERNAME),
                "Expected records that match Username = Admin, User Role = Admin, Status = Enabled");
        takeScreenshot("test Search By Multiple Filters");
    }

    @Test(priority = 7, description = "Verify search by invalid username shows 'Invalid' message")
    public void testSearch_ByInvalidEmployeeName() {
        adminPage.enterEmployeeName(WRONG_EMPLOYEE);
        adminPage.clickSearch();
        Assert.assertTrue(
                adminPage.isInvalidDisplayed(),
                "Expected 'Invalid' message should be displayed."
        );

        takeScreenshot("testSearch_ByInvalidEmployeeName");
    }

}
