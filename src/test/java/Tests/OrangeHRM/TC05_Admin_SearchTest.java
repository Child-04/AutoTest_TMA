package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import Pages.OrangeHRM.P05_AdminPage_Search;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC05_Admin_SearchTest extends BaseTest {
    private P05_AdminPage_Search adminPage;
    private P01_LoginPage loginPage;

    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new P05_AdminPage_Search(page);
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();
        loginPage.enterUserAccount();
        adminPage.openAdminPage();
    }

    @Test(priority = 1, description = "Verify search by valid username returns correct user")
    public void testSearch_ByValidUsername() {
        adminPage.enterUsername("Admin");
        adminPage.clickSearch();
        Assert.assertTrue(
                adminPage.isRecordFound("Admin"),
                "Expected user 'Admin' should be displayed in search results."
        );
        takeScreenshot("testSearch_ByValidUsername");
    }

    @Test(priority = 2, description = "Verify search by invalid username shows 'No Records Found' message")
    public void testSearch_ByInvalidUsername() {
        adminPage.enterUsername("wrongName");
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

    @Test(priority = 3, description = "Search with all empty fields - should return full user list")
    public void testSearch_ByAllEmptyFields() {
        adminPage.clickSearch();

        // check record display
        Assert.assertTrue(
                adminPage.isRecordDisplayed(),
                "Expected system to display full user list when all search fields are empty."
        );
        page.waitForTimeout(2000);

        takeScreenshot("testSearch_ByAllEmptyFields");
    }

    @Test(priority = 4, description = "Search with User Role = Admin")
    public void testSearchByUserRole() {
        adminPage.selectUserRole("Admin");
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.getNumberOfRecords() > 0,
                "Expected records with User Role = Admin");
        takeScreenshot("testSearchByUserRole");
    }

    @Test(priority = 6, description = "Search with Username + User Role + Status")
    public void testSearchByMultipleFilters() {
        adminPage.enterUsername("Admin");
        adminPage.selectUserRole("Admin");
        adminPage.selectStatus("Enabled");
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.isRecordFound("Admin"),
                "Expected records that match Username = Admin, User Role = Admin, Status = Enabled");
        takeScreenshot("testSearchByMultipleFilters");
    }


}
