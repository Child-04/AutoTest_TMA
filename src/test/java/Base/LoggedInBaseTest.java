package Base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class LoggedInBaseTest extends BaseTest{
    @BeforeClass
    @Override
    public void setup() {
        super.setup(); // gọi setup từ BaseTest
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.fill("//input[@name='username']", "Admin");
        page.fill("//input[@name='password']", "admin123");
        page.click("//button[@type='submit']");
        page.waitForURL("**/dashboard/index");
    }
}
