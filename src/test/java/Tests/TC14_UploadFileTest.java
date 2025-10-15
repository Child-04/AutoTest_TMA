package Tests;

import Base.BaseTest;
import Pages.P14_External_UploadFilePage;
import Config.FileUploadConfig;
import Data.TestData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC14_UploadFileTest extends BaseTest {
    private P14_External_UploadFilePage uploadFilePage;

    @BeforeMethod
    public void openPage() {
        uploadFilePage = new P14_External_UploadFilePage(page);
        uploadFilePage.navigateToPage();
    }


    @Test(description = "Verify upload file thành công và hiển thị thông báo 'File Uploaded!'")
    public void testUploadFileSuccess() {
        // Lấy thông tin file từ TestData
        FileUploadConfig fileConfig = TestData.SAMPLE_FILE;

        // Upload file và click nút
        uploadFilePage.uploadFile(fileConfig.getFullPath());
        takeScreenshot("Upload file");

        // Verify thông báo "File Uploaded!" hiển thị
        uploadFilePage.verifyUploadedFile(fileConfig.getFileName());

        // Chụp màn hình kết quả
        takeScreenshot("UploadFile_Success");
    }

}
