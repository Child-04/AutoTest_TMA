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
                System.err.println("Cannot take screenshot: page is null!");
                return;
            }

            waitForPageStable();

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions().setFullPage(true)
            );

            String status = isSuccess ? "PASSED" : "FAILED";
            Allure.addAttachment("Screenshot - " + testName + " (" + status + ")",
                    new ByteArrayInputStream(screenshot));

            System.out.println("Screenshot captured for test: " + testName);

        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            Allure.addAttachment("Screenshot Error",
                    "Failed to take screenshot: " + e.getMessage());
        }
    }

    @Step("Take step screenshot: {stepName}")
    public void takeStepScreenshot(String stepName) {
        try {
            waitForPageStable();

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions().setFullPage(true)
            );
            Allure.addAttachment(stepName, "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.err.println("Failed to take step screenshot: " + e.getMessage());
        }
    }

    /**
     * Chờ trang ổn định mà không dùng Page.LoadState.
     * Kiểm tra document.readyState hoặc chờ ngắn để đảm bảo trang hiển thị hoàn chỉnh.
     */
    private void waitForPageStable() {
        try {
            // Chờ document.readyState === "complete"
            page.waitForFunction("document.readyState === 'complete'");

            // Chờ thêm 0.5 giây để các animation hoặc render nhỏ hoàn tất
            page.waitForTimeout(500);
        } catch (Exception e) {
            System.err.println("Warning: Could not fully verify page stability - " + e.getMessage());
        }
    }
}
