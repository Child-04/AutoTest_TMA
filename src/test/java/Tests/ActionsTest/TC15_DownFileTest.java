package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P15_Herokuapp_DownloadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class TC15_DownFileTest extends BaseTest {

    @Test(description = "Verify download file thành công và tồn tại trong thư mục download")
    public void testDownloadFile() {

        P15_Herokuapp_DownloadPage downloadPage = new P15_Herokuapp_DownloadPage(page);

        // Step 1: Navigate to page
        downloadPage.navigateToDownloadPage();

        // Step 2: Select file to download
        String fileName = "test_upload.txt";

        Path downloadedFile = downloadPage.downloadFile(fileName);
        takeScreenshot("Download_File_Success");

        // Step 3: Verify file exists after downloading
        Assert.assertTrue(
                Files.exists(downloadedFile),
                "File does not exist after download: " + downloadedFile
        );
        System.out.println("File downloaded successfully: " + downloadedFile);

        // (Optional) Step 4: Delete file for cleanup
        // downloadPage.deleteDownloadedFile(fileName);
    }
}
