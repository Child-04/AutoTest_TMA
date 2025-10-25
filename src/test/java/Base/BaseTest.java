package Base;

import Utils.utils.ScreenshotUtil;
import Utils.reporting.TraceUtil;
import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.*;

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


    @AfterClass(alwaysRun = true)
    public void teardown() {
        try {
            if (context != null) context.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
