package Pages;

import Base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;

public class P12_JotForm_SelectPage extends BaseTest {
    private final Page page;

    // Locator cho dropdown Gender
    private final String genderDropdown = "//select[@id='input_71']";

    // Constructor
    public P12_JotForm_SelectPage(Page page) {
        this.page = page;
    }

    @Step("Select gender : {gender}")
    public void selectGender(String gender) {
        page.selectOption(genderDropdown, new SelectOption().setValue(gender));
    }


    @Step("Get value selected in dropdown")
    public String getSelectedGender() {
        return page.locator(genderDropdown).inputValue();
    }
}
