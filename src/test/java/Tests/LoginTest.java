package Tests;
import Base.BaseTest;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(page);
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Chờ input username xuất hiện
        //page.waitForSelector("//input[@name='username']");
    }
    @Test
    public void testLoginSuccess() {
        loginPage.loginAs("Admin", "admin123");
        page.waitForURL("**/dashboard/index");
        Assert.assertTrue(loginPage.isLoginSuccess(), "Login failed!");
    }

    @Test
    public void login_WithWrongUsername_ShouldShowErrorMessage() {
        loginPage.loginAs("wrongUser", "admin123");
        page.click("button[type='submit']");
        Assert.assertEquals(loginPage.getInvalidError(), "Invalid credential");
    }

    @Test
    public void login_WithEmptyUsername_ShouldShowRequiredMessage() {
        loginPage.loginAs("", "wrongPass");
        page.click("button[type='submit']");


        Assert.assertEquals(loginPage.getUserRequiredError(), "Required");
    }

    @Test
    public void login_WithEmptyPassword_ShouldShowRequiredMessage() {
        loginPage.loginAs("Admin",  "");
        page.click("button[type='submit']");

        Assert.assertEquals(loginPage.getPassRequiredError(), "Required");
    }

    @Test
    public void login_WithEmptyCredentials_ShouldShowTwoRequiredMessages() {
        loginPage.loginAs("", "");
        Assert.assertEquals(loginPage.getUserRequiredError().trim(), "Required");
        Assert.assertEquals(loginPage.getPassRequiredError().trim(), "Required");
    }

}
