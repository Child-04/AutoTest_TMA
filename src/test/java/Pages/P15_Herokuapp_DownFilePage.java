package Pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Download;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class P15_Herokuapp_DownFilePage {
    private final Page page;
    private final String downloadDir = "src/test/resources/files";

    public P15_Herokuapp_DownFilePage(Page page) {
        this.page = page;
    }

    @Step("Navigate to Herokuapp File Downloader page")
    public void navigateToDownloadPage() {
        page.navigate("https://the-internet.herokuapp.com/download");
    }

    @Step("Download file: {fileName}")
    public void downloadFile(String fileName) {
        Download download = page.waitForDownload(() -> {
            page.locator(String.format("//a[text()='%s']", fileName)).click();
        });

        // Save file as test/resources/files
        Path savePath = Paths.get(downloadDir, fileName);
        download.saveAs(savePath);
        Assert.assertTrue(Files.exists(savePath), "File does not exist after download!");
    }

    @Step("Verify file '{fileName}' tồn tại trong thư mục download")
    public void verifyFileDownloaded(String fileName) {
        Path filePath = Paths.get(downloadDir, fileName);
        Assert.assertTrue(Files.exists(filePath),
                String.format("File '%s' not yet downloaded!", fileName));
    }

    @Step("Delete file '{fileName}' after check")
    public void deleteDownloadedFile(String fileName) {
        try {
            Path filePath = Paths.get(downloadDir, fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (Exception e) {
            System.err.println("Cannot delete file: " + e.getMessage());
        }
    }
}
