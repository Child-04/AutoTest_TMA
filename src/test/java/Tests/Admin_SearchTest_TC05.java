package Tests;

import Base.LoggedInBaseTest;
import Pages.AdminPage_Search_03;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Admin_SearchTest_TC05 extends LoggedInBaseTest {
    private AdminPage_Search_03 adminPage;
    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new AdminPage_Search_03(page);
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
    }

    @Test(priority = 3, description = "Search with all empty fields - should return full user list")
    public void testSearch_ByAllEmptyFields() {
        // Clear input and dropdown
        adminPage.enterUsername("");       // Username trống
        adminPage.enterEmployeeName("");   // Employee Name trống

        adminPage.clickSearch();

        // check record display
        Assert.assertTrue(
                adminPage.isRecordDisplayed(),
                "Expected system to display full user list when all search fields are empty."
        );
    }

    @Test(priority = 4, description = "Search with User Role = Admin")
    public void testSearchByUserRole() {
        adminPage.selectUserRole("Admin");
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.getNumberOfRecords() > 0,
                "Expected records with User Role = Admin");
    }

    @Test(priority = 6, description = "Search with Username + User Role + Status")
    public void testSearchByMultipleFilters() {
        adminPage.enterUsername("Admin");
        adminPage.selectUserRole("Admin");
        adminPage.selectStatus("Enabled");
        adminPage.clickSearch();
        Assert.assertTrue(adminPage.isRecordFound("Admin"),
                "Expected records that match Username = Admin, User Role = Admin, Status = Enabled");
    }


}
