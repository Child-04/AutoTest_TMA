package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class P13_Jquery_DatePickerPage {
    private final Page page;
    private final FrameLocator frame;
    private final Locator dateInput;
    private final Locator today;
    private final Locator customDate;
    private final Locator dateInMonth;
    private final Locator selectedDate;
    private final Random random = new Random();

    // Constructor
    public P13_Jquery_DatePickerPage(Page page) {
        this.page = page;
        // iframe chứa input datepicker
        this.frame = page.frameLocator("//iframe");
        this.dateInput = frame.locator("//input[@id='datepicker']");
        this.today = frame.locator("//td[contains(@class, 'ui-datepicker-today')]");
        this.customDate = frame.locator("//td[contains(@class, 'ui-datepicker-today')]/a");
        this.dateInMonth = frame.locator("//a[contains(@class,'ui-state-default')]");
        this.selectedDate = frame.locator("//a[contains(@class,'ui-state-active')]");
    }


    @Step("Navigate to jQuery DatePicker demo page")
    public void navigateToPage() {
        page.navigate("https://jqueryui.com/datepicker/");
    }

    @Step("Fill date")
    public void fillDate(String date) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dateInput.type(date);
    }

    public String getDateValue() {
        return dateInput.inputValue();
    }

    @Step("Open DatePicker calendar")
    public void  clickDateInput() {
        dateInput.click();
    }

    @Step("Click today's date in DatePicker")
    public void clickToday() {
        today.click();
    }

    // Get current date as format mm/dd/yyyy
    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.now().format(formatter);
    }

    public int getTodayDate() {
        String today = customDate.getAttribute("data-date");
        return Integer.parseInt(today);
    }

    //Get total days in month
    public int getTotalDaysInMonth() {
        return dateInMonth.count();
    }

    @Step("Select custom date")
    public void selectCustomDay() {
        int today = getTodayDate();
        int totalDays = getTotalDaysInMonth();

        int targetDay;
        if (today > 5) {
            targetDay = today - 5;
            System.out.println("Today is " + today + " → Selected " + targetDay + " (today - 5)");
        } else {
            // If today <= 5 then add 5 to still select a valid day
            targetDay = today + 5;
            // If the sum of 5 exceeds the total number of days, select the middle of the month
            if (targetDay > totalDays) targetDay = totalDays - 1;
            System.out.println("Today is " + today + " → Selected " + targetDay + " (today + 5)");
        }

        // Select a date from the calendar
        frame.locator("//a[@data-date='" + targetDay + "']").click();
    }

    @Step("Fill a random valid date (MM/dd/yyyy) and verify calendar highlight")
    public void fillRandomDateAndVerify() {
        // Generate valid random date
        int month = random.nextInt(12) + 1; // 1-12
        int day = random.nextInt(28) + 1;   // 1-28 tránh lỗi ngày không hợp lệ
        int year = LocalDate.now().getYear();

        LocalDate randomDate = LocalDate.of(year, month, day);
        String formattedDate = randomDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        clickDateInput();
        //Fill in the input
        fillDate(formattedDate);

        selectedDate.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(20*1000));
        // Locator for selected date
        Locator selectDay = selectedDate;

        String selectedText = selectDay.innerText().trim();
        int selectedInt = Integer.parseInt(selectedText);

        System.out.println("Input date: " + formattedDate);
        System.out.println("Highlighted date: " + selectedInt);

        if (selectedInt == day) {
            System.out.println("Calendar correctly highlighted selected date: " + day);
        } else {
            throw new AssertionError("Calendar did not highlight correct day! Expected " + day + " but got " + selectedInt);
        }
    }

}
