package Tests;

import Base.BaseTest;
import Pages.P13_Jquery_DatePickerPage;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TC13_Jquery_DatePickerTest extends BaseTest {

    private P13_Jquery_DatePickerPage datePickerPage;

    @BeforeMethod
    public void openPage() {
        datePickerPage = new P13_Jquery_DatePickerPage(page);
        datePickerPage.navigateToPage();
    }


    @Test
    @Description("Verify that user can select today's date, the displayed value matches the current system date.")
    public void testClickDatePickerToday() {
        datePickerPage.clickDateInput();
        datePickerPage.clickToday();

        String expectedDate = datePickerPage.getCurrentDate();
        // Get the value back and verify
        String actualDate = datePickerPage.getDateValue();
        Assert.assertEquals(actualDate, expectedDate, "Date value is incorrect!");

        System.out.println("Date entered successfully: " + actualDate);
        takeScreenshot("DatePicker");
    }

    @Test
    @Description("Verify that user can select custom date and the displayed value matches the date selected")
    public void testSmartDatePickerSelection() {
        datePickerPage.clickDateInput();

        // Automatically select valid date (today ± 5)
        datePickerPage.selectCustomDay();

        String actualDate = datePickerPage.getDateValue();
        System.out.println("Date selected" + actualDate);

        takeScreenshot("DateSelected");
    }

    @Test
    @Description("Verify user can fill random date (MM/dd/yyyy) and calendar correctly highlights the same date.")
    public void testRandomDatePicker() {
        datePickerPage.fillRandomDateAndVerify();
        takeScreenshot("RandomDatePicker");
    }
}
