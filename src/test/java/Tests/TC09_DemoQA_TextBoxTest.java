package Tests;

import Base.BaseTest;
import Pages.DemoQAPage.P09_External_DemoQAPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Data.TestData.*;

public class TC09_DemoQA_TextBoxTest extends BaseTest {
    private P09_External_DemoQAPage external_DemoQAPage;

    @BeforeMethod
    public void openPage() {
        external_DemoQAPage = new P09_External_DemoQAPage(page);
        external_DemoQAPage.navigateToPage();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(description = "Verify user input and output matching")
    public void testTextBoxForm() {
        // ===== Test data =====
        String name = NAME;
        String email = EMAIL;
        String currentAddress = CURRENT_ADDRESS;
        String permanentAddress = PERMANENT_ADDRESS;

        // ===== Actions =====
        external_DemoQAPage.fillForm(name, email, currentAddress, permanentAddress);
        external_DemoQAPage.submit();

        // ===== Verify =====
        Assert.assertTrue(external_DemoQAPage.isOutputVisible(), "Output section is not visible!");

        Assert.assertEquals(external_DemoQAPage.getName(), name, "Full name mismatch!");
        Assert.assertEquals(external_DemoQAPage.getEmail(), email, "Email mismatch!");
        Assert.assertEquals(external_DemoQAPage.getCurrentAddress(), currentAddress, "Current address mismatch!");
        Assert.assertEquals(external_DemoQAPage.getPermanentAddress(), permanentAddress, "Permanent address mismatch!");
        takeScreenshot("All information is match");
    }

}
