package Tests;

import Base.BaseTest;
import Pages.P15_Herokuapp_DownFilePage;
import org.testng.annotations.Test;

public class TC15_DownFileTest extends BaseTest {

    @Test(description = "Verify download file")
    public void testDownloadFile() {

        P15_Herokuapp_DownFilePage downloadPage = new P15_Herokuapp_DownFilePage(page);

        downloadPage.navigateToDownloadPage();

        // select file
        String fileName = "test_upload.txt";

        downloadPage.downloadFile(fileName);
        takeScreenshot("Download_File_Success");

        // Verify file exists after downloading
        downloadPage.verifyFileDownloaded(fileName);

        // (Optional) Delete the file for later testing
        // downloadPage.deleteDownloadedFile(fileName);
    }
}
