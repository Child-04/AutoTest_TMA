package Utils;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private final Page page;

    public ScreenshotUtil(Page page) {
        this.page = page;
    }

    public void takeScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "target/screenshots/" + testName + "_" + timestamp + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)).setFullPage(true));
            System.out.println("Screenshot saved: " + fileName);
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
