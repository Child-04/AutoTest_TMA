package Tests;

import Base.LoggedInBaseTest;
import Pages.P02_AdminPage_01_StringParameter;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Data.TestData.*;

public class TC03_AdminTable01_SortingTest extends LoggedInBaseTest {

    private P02_AdminPage_01_StringParameter adminPage;
    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new P02_AdminPage_01_StringParameter(page);
        adminPage.openAdminPage();
    }
    @Test(description = "Verify sorting of Username column in ascending order")
    @Description("System should correctly sort the Username column in ascending order")
    public void testSortUserNameAsc() {
        adminPage.VerifySort(USERNAME, "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUsername_ASC");

    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void testSortUserNameDesc() {
        adminPage.VerifySort(USERNAME, "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUsername_DESC");
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void testSortEmployeeNameAsc() {
        adminPage.VerifySort(EMPLOYEE_NAME, "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByEmployeeName_ASC");
    }
    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void testSortEmployeeNameDesc() {
        adminPage.VerifySort(EMPLOYEE_NAME, "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByEmployeeName_DESC");
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order ")
    public void testSortUserRoleAsc() {
        adminPage.VerifySort(USER_ROLE , "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUserRole_ASC");
    }
    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order ")
    public void testSortUserRoleDesc() {
        adminPage.VerifySort(USER_ROLE , "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByUserRole_DESC");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void testSortStatusAsc() {
        adminPage.VerifySort(STATUS, "asc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByStatus_ASC");
    }

    @Test(description = "Verify that sorting of Status in descending order")
    @Description("System should sort correctly the Status in descending order ")
    public void testSortStatusDesc() {
        adminPage.VerifySort(STATUS, "desc");
        page.waitForTimeout(3000);
        takeScreenshot("sortByStatus_DESC");
    }
}