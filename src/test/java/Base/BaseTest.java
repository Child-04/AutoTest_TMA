package Base;

import Utils.AllureCommandRunner;
import Utils.ScreenshotUtil;
import Utils.TraceUtil;
import com.microsoft.playwright.*;
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
        TraceUtil.startTrace(context);

    }

    public void waitForVisible(Locator locator, int timeoutMs) {
        locator.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs));
    }

    @Step("Take test screenshot: {testName}")
    protected void takeScreenshot(String testName) {
        screenshotUtil.takeStepScreenshot(testName);
    }

    @AfterMethod
    public void afterEachTest(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        boolean isFailed = !result.isSuccess();

        if (isFailed) {
            screenshotUtil.takeScreenshot(testName + "_FAILED", false);
        }

        TraceUtil.stopTrace(context, testName, isFailed);
    }


    @AfterClass
    public void teardown() {
        if (page != null) page.context().browser().close();
    }

    @AfterSuite
    public void generateAllureReport() {
        AllureCommandRunner.runCommand("allure generate  --single-file target/allure-results -o target/allure-report");
    }


}
