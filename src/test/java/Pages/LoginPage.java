package Pages;
import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPage extends BaseTest {
    @Test
    public void testOpenPlaywrightSite() {
        page.navigate("https://playwright.dev/");
        String title = page.title();
        System.out.println("Page title = " + title);
        Assert.assertTrue(title.contains("Playwright"));
    }

}
