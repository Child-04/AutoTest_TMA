package Pages.ActionsPage;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Download;
import io.qameta.allure.Step;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class P15_Herokuapp_DownloadPage {
    private final Page page;
    private final String downloadDir = "src/test/resources/files";

    public P15_Herokuapp_DownloadPage(Page page) {
        this.page = page;
    }

    @Step("Navigate to Herokuapp File Downloader page")
    public void navigateToDownloadPage() {
        page.navigate("https://the-internet.herokuapp.com/download");
    }

    @Step("Download file: {fileName}")
    public Path downloadFile(String fileName) {
        Download download = page.waitForDownload(() -> {
            page.locator(String.format("//a[text()='%s']", fileName)).click();
        });

        // Save file to directory
        Path savePath = Paths.get(downloadDir, fileName);
        download.saveAs(savePath);

        return savePath; // 🔹 trả về đường dẫn file để test class tự kiểm tra
    }

    @Step("Get file path in download folder")
    public Path getDownloadedFilePath(String fileName) {
        return Paths.get(downloadDir, fileName);
    }

    @Step("Delete file '{fileName}' after check")
    public void deleteDownloadedFile(String fileName) {
        try {
            Path filePath = getDownloadedFilePath(fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (Exception e) {
            System.err.println("Cannot delete file: " + e.getMessage());
        }
    }
}
