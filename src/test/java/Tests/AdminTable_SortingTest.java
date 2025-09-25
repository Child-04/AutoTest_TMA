package Tests;

import Base.LoggedInBaseTest;
import Pages.AdminPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminTable_SortingTest extends LoggedInBaseTest {
    private AdminPage adminPage;
    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new AdminPage(page);
        adminPage.openAdminPage();
    }
    @Test
    public void testSortUserNameAsc() {
        adminPage.VerifySort("Username", "asc");
        page.waitForTimeout(10000);
    }

    @Test
    public void testSortUserNameDesc() {
        adminPage.VerifySort("Username", "desc");
        page.waitForTimeout(10000);
    }

    @Test
    public void testSortEmployeeNameAsc() {
        adminPage.VerifySort("Employee Name", "asc");
        page.waitForTimeout(10000);
    }
    @Test
    public void testSortEmployeeNameDesc() {
        adminPage.VerifySort("Employee Name", "desc");
        page.waitForTimeout(10000);
    }

    @Test
    public void testSortUserRoleAsc() {
        adminPage.VerifySort("User Role", "asc");
        page.waitForTimeout(10000);
    }
    @Test
    public void testSortUserRoleDesc() {
        adminPage.VerifySort("User Role", "desc");
        page.waitForTimeout(10000);
    }

    @Test
    public void testSortStatusAsc() {
        adminPage.VerifySort("Status", "asc");
        page.waitForTimeout(10000);
    }

    @Test
    public void testSortStatusDesc() {
        adminPage.VerifySort("Status", "desc");
        page.waitForTimeout(10000);
    }

}