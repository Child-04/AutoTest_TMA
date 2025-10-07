package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

public class External_PaginationPage {
    private final Page page;

    private final Locator tableRows;
    private final Locator nextBTN;
    private final Locator prevBTN;
    private final Locator firstBTN;
    private final Locator lastBTN;
    private final Locator numberInput;
    private final Locator pageSizeDropdown;
    private final Locator pageInfoText; // use for "Page 1 of 20"

    public External_PaginationPage(Page page) {
        this.page = page;

        // Khởi tạo locators
        this.tableRows = page.locator("//table//tbody/tr");
        this.nextBTN = page.locator("//button[text()='Next']");
        this.prevBTN = page.locator("//button[text()='Previous']");
        this.firstBTN = page.locator("//button[text()='<<']");
        this.lastBTN = page.locator("//button[text()='>>']");
        this.numberInput = page.locator("//input[@type='number']");
        this.pageSizeDropdown = page.locator("//select");
        this.pageInfoText = page.locator("//span[contains(text(),'Page')]");
    }

    // purpose: select the number of line to display
    @Step("Select the number of lines to display: {size}")
    public void selectPageSize(String size) {
        pageSizeDropdown.selectOption(size);
        page.waitForTimeout(1000);
    }

    // purpose: count how many rows in a page -> check and compare expected
    @Step("Get the current displayed line number")
    public int getRowCount() {
        return tableRows.count();
    }

    // purpose: compare when entering number in textbox and click button moving to another page
    @Step("Get the current page")
    public int getCurrentPageNumber() {
        String pageText = pageInfoText.innerText(); // "Page 1 of 20"
        String current = pageText.replaceAll("[^0-9]", " ").trim().split("\\s+")[0];
        return Integer.parseInt(current);
    }

    // Check the last page
    @Step("Get total number of pages")
    public int getTotalPages() {
        String pageText = pageInfoText.innerText(); // "Page 1 of 20"
        String[] numbers = pageText.replaceAll("[^0-9]", " ").trim().split("\\s+");
        return Integer.parseInt(numbers[1]);
    }

    @Step("Enter the page number to go to: {target}")
    public void goToPage(int target) {
        numberInput.fill(String.valueOf(target));
        page.keyboard().press("Enter");
        page.waitForTimeout(1000);
    }

    @Step("Get list of IDs displayed on current page")
    public List<String> getVisibleRowIDs() {
        return page.locator("//table//tbody/tr/td[1]").allInnerTexts();
    }

    // ----- Button status check -----
    private boolean isButtonDisabled(Locator button) {
        return button.getAttribute("disabled") != null;
    }

    public boolean isFirstDisabled() {
        return isButtonDisabled(firstBTN);
    }

    public boolean isPreviousDisabled() {
        return isButtonDisabled(prevBTN);
    }

    public boolean isNextDisabled() {
        return isButtonDisabled(nextBTN);
    }

    public boolean isLastDisabled() {
        return isButtonDisabled(lastBTN);
    }
    @Step("Get row count for all pages")
    public int[] getRowCountsForAllPages() {
        int totalPages = getTotalPages();
        int[] rowCounts = new int[totalPages];
        for (int i = 1; i <= totalPages; i++) {
            goToPage(i);
            rowCounts[i - 1] = getRowCount();
        }
        return rowCounts;
    }
}
