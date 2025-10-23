package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P11_JotForm_RadioPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TC11_JotForm_RadioTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC11_JotForm_RadioTest.class);

    @Test(description = "Verify that selecting one radio button automatically unselects others")
    public void testSelectRadioAndVerifyOthers() {
        log.info("===== Starting test: testSelectRadioAndVerifyOthers =====");

        String url = "https://automationfc.github.io/multiple-fields/";
        log.info("Navigating to test page: {}", url);
        page.navigate(url);

        P11_JotForm_RadioPage radioPage = new P11_JotForm_RadioPage(page);

        String selected = "3-4 days";
        log.info("Selecting radio option: '{}'", selected);
        radioPage.selectRadio(selected);

        log.info("Verifying only '{}' is selected and others are unselected", selected);
        Assert.assertTrue(
                radioPage.verifyOnlyOneChecked(selected),
                "Radio selection is incorrect. Expected only '" + selected + "' to be checked."
        );

        takeScreenshot("Select_Radio_3-4_days");
        log.info("Screenshot captured: Select_Radio_3-4_days");

        log.info("===== Finished test: testSelectRadioAndVerifyOthers =====");
    }
}
