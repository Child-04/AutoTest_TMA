package Tests;
import Base.BaseTest;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToLogin() {
        // mở trang login trước mỗi test
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(page);
    }

    @Test
    public void ValidLogin() {
        // nhập username và password đúng
        loginPage.loginAs("Admin", "admin123");
        page.click("button[type='submit']");

        // kiểm tra đã chuyển hướng sang dashboard
        page.waitForURL("**/dashboard/index");
        Assert.assertTrue(page.url().contains("dashboard/index"), "Login failed with valid credentials");
    }
    @Test
    public void InvalidLogin() {
       // page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.loginAs("wrongUser", "wrongPass");
        page.click("button[type='submit']");

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }

    @Test
    public void InvalidLogin_WrongPass() {
        loginPage.loginAs("Admin", "wrongPass");
        page.click("button[type='submit']");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }
    @Test
    public void InvalidLogin_WrongUsername() {
        loginPage.loginAs("wrongUser", "admin123");
        page.click("button[type='submit']");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credential");
    }

    @Test
    public void InvalidLogin_NoneUsername() {
        loginPage.loginAs("", "wrongPass");
        page.click("button[type='submit']");

       String errorText = page.textContent("//span[contains(@class,'oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message')]");
       Assert.assertEquals(errorText.trim(), "Required");
    }

    @Test
    public void testInvalidLogin_NonePassword() {
        loginPage.loginAs("Admin",  "");
        page.click("button[type='submit']");

        String errorText = page.textContent("//span[contains(@class,'oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message')]");
        Assert.assertEquals(errorText.trim(), "Required");
    }

    @Test
    public void testInvalidLogin_NoneBoth() {
        loginPage.loginAs("", "");
        page.click("button[type='submit']");

        String errorText = page.textContent("//span[contains(@class,'oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message')]");
        Assert.assertEquals(errorText.trim(), "Required");
    }
}
