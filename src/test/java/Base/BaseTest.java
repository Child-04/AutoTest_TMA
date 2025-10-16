package Base;

import Utils.AllureCommandRunner;
import Utils.ScreenshotUtil;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.ITestResult;
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

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        boolean isSuccess = result.isSuccess();

        // Chụp screenshot khi FAIL
        if (!isSuccess) {
            screenshotUtil.takeScreenshot(testName + "_FAILED", false);
        }
    }

    @AfterClass
    public void teardown() {
        if (page != null) page.context().browser().close();
    }

    @AfterSuite
    public void generateAllureReport() {
        AllureCommandRunner.runCommand("allure generate --clean target/allure-results -o target/allure-report");
        System.out.println("Allure report generated successfully!");
    }


}
