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
    public void TC07_testSortUserNameAsc() {
        adminPage.VerifySort(USERNAME, "asc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void TC08_testSortUserNameDesc() {
        adminPage.VerifySort(USERNAME, "desc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void TC09_testSortEmployeeNameAsc() {
        adminPage.VerifySort(EMPLOYEE_NAME, "asc");
        page.waitForTimeout(3000);
    }
    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void TC10_testSortEmployeeNameDesc() {
        adminPage.VerifySort(EMPLOYEE_NAME, "desc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order ")
    public void TC11_testSortUserRoleAsc() {
        adminPage.VerifySort(USER_ROLE , "asc");
        page.waitForTimeout(3000);
    }
    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order ")
    public void TC12_testSortUserRoleDesc() {
        adminPage.VerifySort(USER_ROLE , "desc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC13_testSortStatusAsc() {
        adminPage.VerifySort(STATUS, "asc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC14_testSortStatusDesc() {
        adminPage.VerifySort(STATUS, "desc");
        page.waitForTimeout(3000);
    }
}