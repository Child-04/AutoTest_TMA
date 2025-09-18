package Tests;
import Base.BaseTest;
import Pages.LoginPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
    public void testValidLogin() {
        // nhập username và password đúng
        page.fill("input[name='username']", "Admin");
        page.fill("input[name='password']", "admin123");
        page.click("button[type='submit']");

        // kiểm tra đã chuyển hướng sang dashboard
        page.waitForURL("**/dashboard/index");
        Assert.assertTrue(page.url().contains("dashboard/index"), "Login failed with valid credentials");
    }
    @Test
    public void testInvalidLogin() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // nhập username sai
        page.fill("input[name='username']", "wrongUser");
        page.fill("input[name='password']", "wrongPass");
        page.click("button[type='submit']");

        // kiểm tra thông báo lỗi xuất hiện
        String errorText = page.textContent("div.oxd-alert.oxd-alert--error");
        Assert.assertEquals(errorText.trim(), "Invalid credentials");
    }
}
