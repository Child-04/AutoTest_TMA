package Pages.ActionsPage;

import Utils.utils.ScreenshotUtil;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class P12_JotForm_SelectPage {
    private final Page page;
    private ScreenshotUtil screenshotUtil;

    // Locator cho dropdown Gender
    private final Locator genderDropdown;
    private final Locator dayDropdown;
    private final Locator monthDropdown;
    private final Locator yearDropdown;

    // Constructor
    public P12_JotForm_SelectPage(Page page) {
        this.page = page;

        genderDropdown = page.locator("//select[@id='input_71']");
        dayDropdown = page.locator("//select[@id='input_46_day']");
        monthDropdown = page.locator("//select[@id='input_46_month']");
        yearDropdown =  page.locator("//select[@id='input_46_year']");

    }

    @Step("Navigate to Select page")
    public void navigateToPage() {
        page.navigate("https://automationfc.github.io/multiple-fields/");

    }

    @Step("Click dropdown")
    public void clickDropdown() {
        genderDropdown.click();
    }
    @Step("Select gender : {gender}")
    public void selectGender(String gender) {
        genderDropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        genderDropdown.selectOption(new SelectOption().setLabel(gender));
    }


    @Step("Get value selected in dropdown")
    public String getSelectedGender() {
        return genderDropdown.inputValue();
    }

    @Step("Get all options in Gender dropdown")
    public List<String> getDropdownOptions() {
        return genderDropdown.locator("option").allTextContents()
                .stream()
                .map(String::trim)
                .toList();
    }


    // ----------------------------------------------------------------------

    @Step("Select random option from {fieldName} dropdown")
    public String selectRandomOption(Locator dropdown, String fieldName) {
        dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        List<String> options = dropdown.locator("option").allTextContents()
                .stream()
                .map(String::trim)
                .filter(opt -> !opt.isEmpty())
                .collect(Collectors.toList());

        String randomOption = options.get(new Random().nextInt(options.size()));
        dropdown.selectOption(new SelectOption().setLabel(randomOption));

        return randomOption;
    }

    @Step("Select random Day")
    public void selectRandomDay() {
        selectRandomOption(dayDropdown, "Day");
    }

    @Step("Select random Month")
    public void selectRandomMonth() {
        selectRandomOption(monthDropdown, "Month");
    }

    @Step("Select random Year")
    public void selectRandomYear() {
        selectRandomOption(yearDropdown, "Year");
    }



}
