package Pages.ActionsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import java.nio.file.Paths;

public class P14_External_UploadFilePage {
    private final Page page;

    // Locators
    private final Locator inputUpload;
    private final Locator uploadBtn;
    private final Locator resultText;

    // Constructor
    public P14_External_UploadFilePage(Page page) {
        this.page = page;
        inputUpload = page.locator("//input[@type='file']");
        uploadBtn = page.locator("//button[text()='Upload']");
        resultText = page.locator("//h1[contains(text(), 'File Uploaded')]");
    }

    @Step("Navigate to text box page")
    public void navigateToPage() {
        page.navigate("https://practice.expandtesting.com/upload");

    }

    @Step("Upload file: {filePath}")
    public void uploadFile(String filePath) {
        inputUpload.setInputFiles(Paths.get(filePath));
        uploadBtn.click();
    }

    @Step("Get upload notification text after uploading file")
    public String getUploadNotificationText() {
        resultText.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return resultText.textContent().trim();
    }


}