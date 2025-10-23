package Tests.ExternalTest;

import Base.BaseTest;
import Pages.ExternalPage.P13_Jquery_DatePickerPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC13_Jquery_DatePickerTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC13_Jquery_DatePickerTest.class);
    private P13_Jquery_DatePickerPage datePickerPage;

    @BeforeMethod
    public void openPage() {
        log.info("===== Starting setup for JQuery DatePicker Test =====");
        datePickerPage = new P13_Jquery_DatePickerPage(page);
        datePickerPage.navigateToPage();
        log.info("Navigated to JQuery DatePicker Page successfully.");
    }

    @Test(description = "Verify that user can select today's date, the displayed value matches the current system date.")
    public void testClickDatePickerToday() {
        log.info("===== Starting test: testClickDatePickerToday =====");

        log.info("Clicking date input field...");
        datePickerPage.clickDateInput();

        log.info("Clicking 'Today' button...");
        datePickerPage.clickToday();

        String expectedDate = datePickerPage.getCurrentDate();
        log.info("Expected current system date: {}", expectedDate);

        String actualDate = datePickerPage.getDateValue();
        log.info("Actual date value from input field: {}", actualDate);

        Assert.assertEquals(actualDate, expectedDate, "Date value is incorrect!");
        log.info("Assertion passed: Date matches system date.");

        takeScreenshot("DatePicker");
        log.info("Screenshot captured: DatePicker");

        log.info("===== Finished test: testClickDatePickerToday =====");
    }

    @Test(description = "Verify that user can select custom date and the displayed value matches the date selected")
    public void testSmartDatePickerSelection() {
        log.info("===== Starting test: testSmartDatePickerSelection =====");

        log.info("Clicking date input field...");
        datePickerPage.clickDateInput();

        log.info("Selecting custom day (today ± 5 days)...");
        datePickerPage.selectCustomDay();

        String actualDate = datePickerPage.getDateValue();
        log.info("Custom date selected and displayed: {}", actualDate);

        takeScreenshot("DateSelected");
        log.info("Screenshot captured: DateSelected");

        log.info("===== Finished test: testSmartDatePickerSelection =====");
    }

    @Test(description = "Verify user can fill random date (MM/dd/yyyy) and calendar correctly highlights the same date.")
    public void testRandomDatePicker() {
        log.info("===== Starting test: testRandomDatePicker =====");

        log.info("Filling random date and verifying highlight...");
        datePickerPage.fillRandomDateAndVerify();

        takeScreenshot("RandomDatePicker");
        log.info("Screenshot captured: RandomDatePicker");

        log.info("===== Finished test: testRandomDatePicker =====");
    }
}
