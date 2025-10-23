package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P09_DemoQA_TextInputPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.TestData.*;

public class TC09_DemoQA_TextBoxTest extends BaseTest {
    private P09_DemoQA_TextInputPage external_DemoQAPage;

    @BeforeMethod
    public void openPage() {

        external_DemoQAPage = new P09_DemoQA_TextInputPage(page);
        external_DemoQAPage.navigateToPage();
        super.setup();
        page.evaluate("setTimeout(function(){debugger;}, 3000);");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(description = "Verify textarea supports newline but output displays single line")
    public void testTextareaHandlesNewlineProperly() {
        P09_DemoQA_TextInputPage textPage = new P09_DemoQA_TextInputPage(page);
        textPage.navigateToPage();

        String fullName = NAME;
        String email = EMAIL;
        String currentAddress = CURRENT_ADDRESS;
        String permanentAddress = PERMANENT_ADDRESS;

        textPage.fillForm(fullName, email, currentAddress, permanentAddress);
        textPage.submit();

        // Verify output display
        Assert.assertTrue(textPage.isOutputVisible(), "Output box should be visible");

        // The printed result must not contain a newline character.
        String currentOutput = textPage.getCurrentAddress();
        String permanentOutput = textPage.getPermanentAddress();

        System.out.println("Output Current Address: " + currentOutput);
        System.out.println("Output Permanent Address: " + permanentOutput);

        Assert.assertFalse(currentOutput.contains("\n") || currentOutput.contains("\r"),
                "Current Address output should be single line");

        Assert.assertFalse(permanentOutput.contains("\n") || permanentOutput.contains("\r"),
                "Permanent Address output should be single line");

        takeScreenshot("All information is match");
    }


}