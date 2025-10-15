package Tests;

import Base.BaseTest;
import Pages.JotForm.P11_JotForm_RadioPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC11_JotForm_RadioTest extends BaseTest {

    private P11_JotForm_RadioPage radioPage;

    @BeforeMethod
    public void openPage() {
        radioPage = new P11_JotForm_RadioPage(page);
        radioPage.navigateToPage();
    }

    @Test
    public void testSelectRadioAndVerifyOthers() {
        page.navigate("https://automationfc.github.io/multiple-fields/");
        P11_JotForm_RadioPage radioPage = new P11_JotForm_RadioPage(page);

        String selected = "3-4 days";
        radioPage.selectRadio(selected);

        Assert.assertTrue(radioPage.verifyOnlyOneChecked(selected),
                "Radio selection is incorrect. Expected only '" + selected + "' to be checked.");
        takeScreenshot("Select Radio 3-4 days");
    }
}
