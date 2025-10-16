package Pages;

import Utils.ScreenshotUtil;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;
import org.testng.Assert;

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

    @Step("Verify dropdown '{dropdownName}' hiển thị đúng danh sách tùy chọn")
    public void verifyDropdownOptions( List<String> expectedOptions) {

        List<String> actualOptions = genderDropdown.locator("option").allTextContents()
                .stream()
                .map(String::trim)
                .toList();

        Assert.assertEquals(
                actualOptions,
                expectedOptions,
                "Dropdown is not displayed correctly!"
        );
    }


    // ----------------------------------------------------------------------

    @Step("Select random option for {fieldName} dropdown and take screenshot")
    public void selectRandomOption(Locator dropdown, String fieldName) {
        dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        // Lấy tất cả các giá trị trong dropdown (trừ option rỗng)
        List<String> options = dropdown.locator("option").allTextContents()
                .stream()
                .map(String::trim)
                .filter(opt -> !opt.isEmpty())
                .collect(Collectors.toList());

        // Random 1 option
        Random rand = new Random();
        String randomOption = options.get(rand.nextInt(options.size()));

        // Chọn giá trị
        dropdown.selectOption(new SelectOption().setLabel(randomOption));

        // Kiểm tra kết quả
        Assert.assertEquals(dropdown.inputValue(), randomOption,
                fieldName + " dropdown: Expected value not matched after selection.");

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
