package Base;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class LoggedInBaseTest extends BaseTest {
    @BeforeClass
    @Step("Step 1: Open OrangeHRM login page and login successfully")
    public void openLoginPage() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.fill("//input[@name='username']", "Admin");
        page.fill("//input[@name='password']", "admin123");
        page.click("//button[@type='submit']");
        page.waitForURL("**/dashboard/index");
    }
}
