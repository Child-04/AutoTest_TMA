package Utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;

public class ScreenshotUtil {
    private final Page page;

    public ScreenshotUtil(Page page) {
        this.page = page;
    }

    @Step("Take screenshot: {testName}")
    public void takeScreenshot(String testName, boolean isSuccess) {
        try {
            if (page == null) {
                System.err.println("⚠️ Cannot take screenshot: page is null!");
                return;
            }

            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true)
            );

            String status = isSuccess ? "PASSED" : "FAILED";
            Allure.addAttachment("Screenshot - " + testName + " (" + status + ")",
                    new ByteArrayInputStream(screenshot));

            System.out.println("📸 Screenshot captured for test: " + testName);

        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            Allure.addAttachment("Screenshot Error",
                    "Failed to take screenshot: " + e.getMessage());
        }
    }

    public void takeStepScreenshot(String stepName) {
        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment(stepName, "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.err.println("Failed to take step screenshot: " + e.getMessage());
        }
    }
}
