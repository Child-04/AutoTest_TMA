package Base;

import Utils.ScreenshotUtil;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected ScreenshotUtil screenshotUtil;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        screenshotUtil = new ScreenshotUtil(page);

    }

    public void waitForVisible(Locator locator, int timeoutMs) {
        locator.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs));
    }
    @Step("Take test screenshot: {testName}")
    protected void takeScreenshot(String testName) {
        screenshotUtil.takeStepScreenshot(testName);
    }
    @AfterClass
    public void teardown() {
        if (page != null) page.context().browser().close();
    }
}
