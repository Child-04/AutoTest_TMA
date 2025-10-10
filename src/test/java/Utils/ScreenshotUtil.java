package Utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private final Page page;

    public ScreenshotUtil(Page page) {
        this.page = page;
    }

    public void takeScreenshot(String testName, boolean isSuccess) {
        try {
            byte[] screenshot = page.screenshot();
            String status = isSuccess ? "PASSED" : "FAILED";
            Allure.addAttachment("Screenshot - " + testName + " (" + status + ")",
                    new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", "Failed to take screenshot: " + e.getMessage());
        }
    }

    public void takeStepScreenshot(String stepName) {
        try {
            byte[] screenshot = page.screenshot();
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.err.println("Failed to take step screenshot: " + e.getMessage());
        }
    }
}
