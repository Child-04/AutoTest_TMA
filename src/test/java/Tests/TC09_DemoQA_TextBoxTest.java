package Tests;

import Base.BaseTest;
import Pages.P09_External_DemoQAPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC09_DemoQA_TextBoxTest extends BaseTest {
    private P09_External_DemoQAPage external_DemoQAPage;

    @BeforeMethod
    public void openPage() {
        page.navigate("https://demoqa.com/text-box",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.LOAD));

        external_DemoQAPage = new P09_External_DemoQAPage(page);
    }
    @Test(description = "Verify user input and output dynamically without hardcoding")
    public void testTextBoxForm() {
        // ===== Test data =====
        String name = "Tuyet Nhi";
        String email = "nhi@example.com";
        String currentAddress = "123 Dương Thị Mười";
        String permanentAddress = "234 An Dương Vương";

        // ===== Actions =====
        external_DemoQAPage.fillForm(name, email, currentAddress, permanentAddress);
        external_DemoQAPage.submit();

        // ===== Verify =====
        Assert.assertTrue(external_DemoQAPage.isOutputVisible(), "Output section is not visible!");

        Assert.assertEquals(external_DemoQAPage.getName(), name, "Full name mismatch!");
        Assert.assertEquals(external_DemoQAPage.getEmail(), email, "Email mismatch!");
        Assert.assertEquals(external_DemoQAPage.getCurrentAddress(), currentAddress, "Current address mismatch!");
        Assert.assertEquals(external_DemoQAPage.getPermanentAddress(), permanentAddress, "Permanent address mismatch!");
    }

}
