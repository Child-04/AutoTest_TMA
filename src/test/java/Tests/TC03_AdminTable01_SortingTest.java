package Tests;

import Base.LoggedInBaseTest;
import Pages.AdminPage_01;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC03_AdminTable01_SortingTest extends LoggedInBaseTest {

    private AdminPage_01 adminPage;
    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new AdminPage_01(page);
        adminPage.openAdminPage();
    }
    @Test(description = "Verify sorting of Username column in ascending order")
    @Description("System should correctly sort the Username column in ascending order")
    public void TC07_testSortUserNameAsc() {
        adminPage.VerifySort("Username", "asc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void TC08_testSortUserNameDesc() {
        adminPage.VerifySort("Username", "desc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void TC09_testSortEmployeeNameAsc() {
        adminPage.VerifySort("Employee Name", "asc");
        page.waitForTimeout(3000);
    }
    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void TC10_testSortEmployeeNameDesc() {
        adminPage.VerifySort("Employee Name", "desc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order ")
    public void TC11_testSortUserRoleAsc() {
        adminPage.VerifySort("User Role", "asc");
        page.waitForTimeout(3000);
    }
    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order ")
    public void TC12_testSortUserRoleDesc() {
        adminPage.VerifySort("User Role", "desc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC13_testSortStatusAsc() {
        adminPage.VerifySort("Status", "asc");
        page.waitForTimeout(3000);
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC14_testSortStatusDesc() {
        adminPage.VerifySort("Status", "desc");
        page.waitForTimeout(3000);
    }
}