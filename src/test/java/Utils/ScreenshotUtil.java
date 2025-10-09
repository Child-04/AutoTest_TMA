package Utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private final Page page;

    public ScreenshotUtil(Page page) {
        this.page = page;
    }

    @Step("Take screenshot: {testName}")
    public void takeScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "target/screenshots/" + testName + "_" + timestamp + ".png";

            // Lưu ảnh ra file
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(fileName))
                    .setFullPage(true));

            // Đọc ảnh vào byte array để đính kèm vào Allure
            byte[] screenshotBytes = Files.readAllBytes(Paths.get(fileName));
            Allure.addAttachment("Screenshot - " + testName, new ByteArrayInputStream(screenshotBytes));

            System.out.println("Screenshot saved and attached to Allure: " + fileName);
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
