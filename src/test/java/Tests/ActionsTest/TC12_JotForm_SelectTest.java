package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P12_JotForm_SelectPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TC12_JotForm_SelectTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TC12_JotForm_SelectTest.class);
    private P12_JotForm_SelectPage selectPage;

    @BeforeMethod
    public void openPage() {
        log.info("===== Starting setup for JotForm Select Page Test =====");
        selectPage = new P12_JotForm_SelectPage(page);
        selectPage.navigateToPage();
        log.info("Navigated to JotForm Select Page successfully.");
    }

    @Test(description = "Verify dropdown gender display correctly")
    public void verifyGenderDropdown() {
        log.info("===== Starting test: verifyGenderDropdown =====");

        log.info("Clicked on Gender dropdown.");
        selectPage.clickDropdown();

        List<String> expectedOptions = Arrays.asList("", "Male", "Female");
        List<String> actualOptions = selectPage.getDropdownOptions();

        log.info("Verifying dropdown options: {}", expectedOptions);
        selectPage.getDropdownOptions();
        Assert.assertEquals(
                actualOptions,
                expectedOptions,
                "Dropdown is not displayed correctly!"
        );

        takeScreenshot("verifyGenderDropdown");
        log.info("Screenshot captured: verifyGenderDropdown");
        log.info("===== Finished test: verifyGenderDropdown =====");
    }

    @Test(description = "Check the Female selection in the Gender dropdown")
    public void testSelectFemale() {
        log.info("===== Starting test: testSelectFemale =====");

        selectPage.selectGender("Female");
        log.info("Selected option: Female");

        String selected = selectPage.getSelectedGender();
        log.info("Verifying selected gender: {}", selected);
        Assert.assertEquals(selected, "Female", "The selected value is incorrect!");

        takeScreenshot("Female selection");
        log.info("Screenshot captured: Female selection");

        log.info("===== Finished test: testSelectFemale =====");
    }

    @Test(description = "Check the Male selection in the Gender dropdown")
    public void testSelectMale() {
        log.info("===== Starting test: testSelectMale =====");

        selectPage.selectGender("Male");
        log.info("Selected option: Male");

        String selected = selectPage.getSelectedGender();
        log.info("Verifying selected gender: {}", selected);
        Assert.assertEquals(selected, "Male", "The selected value is incorrect!");

        takeScreenshot("Male selection");
        log.info("Screenshot captured: Male selection");

        log.info("===== Finished test: testSelectMale =====");
    }

    @Test(description = "Check the Empty selection in the Gender dropdown")
    public void testSelectEmpty() {
        log.info("===== Starting test: testSelectEmpty =====");

        selectPage.selectGender("");
        log.info("Selected option: Empty (default)");

        String selected = selectPage.getSelectedGender();
        log.info("Verifying selected gender: '{}'", selected);
        Assert.assertEquals(selected, "", "The selected value is incorrect!");

        takeScreenshot("Empty selection");
        log.info("Screenshot captured: Empty selection");

        log.info("===== Finished test: testSelectEmpty =====");
    }

    @Test(description = "Verify selecting random date")
    public void testSelectRandomDate() {
        log.info("===== Starting test: testSelectRandomDate =====");

        log.info("Selecting random day...");
        selectPage.selectRandomDay();
        takeScreenshot("selectRandomDay");
        log.info("Screenshot captured: selectRandomDay");

        log.info("Selecting random month...");
        selectPage.selectRandomMonth();
        takeScreenshot("selectRandomMonth");
        log.info("Screenshot captured: selectRandomMonth");

        log.info("Selecting random year...");
        selectPage.selectRandomYear();
        takeScreenshot("selectRandomYear");
        log.info("Screenshot captured: selectRandomYear");

        log.info("===== Finished test: testSelectRandomDate =====");
    }
}
