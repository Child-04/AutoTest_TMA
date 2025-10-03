package Base;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import org.testng.annotations.*;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();

    }


    @AfterClass
    public void teardown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
