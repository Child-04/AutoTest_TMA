package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P14_External_UploadFilePage;
import Config.FileUploadConfig;
import Utils.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC14_UploadFileTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC14_UploadFileTest.class);
    private P14_External_UploadFilePage uploadFilePage;

    @BeforeMethod
    public void openPage() {
        log.info("===== Starting setup for Upload File Test =====");
        uploadFilePage = new P14_External_UploadFilePage(page);
        uploadFilePage.navigateToPage();
        log.info("Navigated to Upload File Page successfully.");
    }

    @Test(description = "Verify upload file thành công và hiển thị thông báo 'File Uploaded!'")
    public void testUploadFileSuccess() {
        log.info("===== Starting test: testUploadFileSuccess =====");

        // Get information from TestData
        FileUploadConfig fileConfig = TestData.SAMPLE_FILE;
        log.info("Preparing to upload file: {}", fileConfig.getFullPath());

        // Step 1: Upload file
        uploadFilePage.uploadFile(fileConfig.getFullPath());
        log.info("File upload triggered successfully.");

        takeScreenshot("Upload_file");

        /// Step 2: Verify upload success notification
        log.info("Verifying upload success notification...");
        String actualText = uploadFilePage.getUploadNotificationText();

        Assert.assertEquals(
                actualText,
                "File Uploaded!",
                "Notification after upload is incorrect. Reality: " + actualText
        );

        log.info("Upload verification passed: Notification displayed correctly - {}", actualText);


        takeScreenshot("UploadFile_Success");
        log.info("Screenshot captured: UploadFile_Success");

        log.info("===== Finished test: testUploadFileSuccess =====");
    }
}
