package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class P08_External_SortDatePage {
    private final Page page;
    private final Locator Datecolumn;
    private final Locator ToggleSort;
    private final Locator DateCells;
    private final Locator FirstRowId;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public P08_External_SortDatePage(Page page) {
        this.page = page;
        this.Datecolumn = page.locator("//th[contains(normalize-space(),'Date of Birth')]");
        this.ToggleSort = page.locator("//th[contains(normalize-space(),'Date of Birth')]//span");
        this.DateCells = page.locator("//tbody/tr/td[4]");
        this.FirstRowId = page.locator("//tbody/tr[1]/td[1]");
    }

    @Step("Click 'Date of Birth' column — ascending sort")
    public void clickSortAscending() {
        Datecolumn.click();
        page.waitForTimeout(1000);
    }

    @Step("Click 'Date of Birth' column or toggle — descending sort")
    public void clickSortDescending() {
        if (ToggleSort.isVisible()) {
            ToggleSort.click();
        } else {
            Datecolumn.click();
        }
        page.waitForTimeout(1000);
    }

    @Step("Click again — reset sort and hide toggle")
    public void clickResetSort() {
        if (ToggleSort.isVisible()) {
            ToggleSort.click();
        } else {
            Datecolumn.click();
        }
        page.waitForTimeout(1000);
    }

    public boolean isToggleVisible() {
        return ToggleSort.isVisible();
    }

    // Get all date of birth as List<LocalDate>
    @Step("Get all 'Date of Birth' values as LocalDate list")
    public List<LocalDate> getAllDates() {
        List<String> dateStrings = DateCells.allTextContents();
        List<LocalDate> dates = new ArrayList<>();
        for (String d : dateStrings) {
            dates.add(LocalDate.parse(d.trim(), formatter));
        }
        return dates;
    }

    // Get ID in the first row
    public String getFirstRowId() {
        return FirstRowId.textContent().trim();
    }
}
