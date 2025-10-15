package Pages.ReactTableDemoPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

public class P06_External_PaginationPage {
    private final Page page;

    private final Locator tableRows;
    private final Locator nextBTN;
    private final Locator prevBTN;
    private final Locator firstBTN;
    private final Locator lastBTN;
    private final Locator numberInput;
    private final Locator pageSizeDropdown;
    private final Locator pageInfoText;

    public P06_External_PaginationPage(Page page) {
        this.page = page;
        this.tableRows = page.locator("//table//tbody/tr");
        this.nextBTN = page.locator("//button[text()='Next']");
        this.prevBTN = page.locator("//button[text()='Previous']");
        this.firstBTN = page.locator("//button[text()='<<']");
        this.lastBTN = page.locator("//button[text()='>>']");
        this.numberInput = page.locator("//input[@type='number']");
        this.pageSizeDropdown = page.locator("//select");
        this.pageInfoText = page.locator("//span[contains(text(),'Page')]");
    }

    public void navigateToPage() {
        page.navigate("https://utkarsh-react-table-demo.netlify.app/pagination");
    }

    public void selectPageSize(String size) {
        pageSizeDropdown.selectOption(size);
        page.waitForTimeout(1000);
    }

    public int getRowCount() {
        return tableRows.count();
    }

    public int getCurrentPageNumber() {
        String pageText = pageInfoText.innerText();
        String current = pageText.replaceAll("[^0-9]", " ").trim().split("\\s+")[0];
        return Integer.parseInt(current);
    }

    public int getTotalPages() {
        String pageText = pageInfoText.innerText();
        String[] numbers = pageText.replaceAll("[^0-9]", " ").trim().split("\\s+");
        return Integer.parseInt(numbers[1]);
    }

    public void goToPage(int target) {
        numberInput.fill(String.valueOf(target));
        page.keyboard().press("Enter");
        page.waitForTimeout(1000);
    }

    public List<String> getVisibleRowIDs() {
        return page.locator("//table//tbody/tr/td[1]").allInnerTexts();
    }

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



    @Step("Verify buttons on first page are disabled")
    public void verifyFirstPageButtons() {
        goToPage(1);
        assert isFirstDisabled() : "First button should be disabled on page 1";
        assert isPreviousDisabled() : "Previous button should be disabled on page 1";
    }

    @Step("Verify buttons on last page are disabled")
    public void verifyLastPageButtons() {
        int totalPages = getTotalPages();
        goToPage(totalPages);
        assert isNextDisabled() : "Next button should be disabled on last page";
        assert isLastDisabled() : "Last button should be disabled on last page";
    }

    @Step("Verify navigation beyond last page stops at last page")
    public void verifyOutOfRangePageNavigation() {
        int totalPages = getTotalPages();
        goToPage(totalPages + 50);
        int currentPage = getCurrentPageNumber();
        assert currentPage == totalPages : "Page should stop at last page when input exceeds range, but is at: " + currentPage;
    }

    @Step("Verify data changes between pages")
    public void verifyPageTransitionAndDataChange() {
        goToPage(1);
        List<String> page1IDs = getVisibleRowIDs();

        int totalPages = getTotalPages();
        if (totalPages > 1) {
            goToPage(2);
            List<String> page2IDs = getVisibleRowIDs();

            assert !page1IDs.equals(page2IDs) : "Data did not change between page 1 and 2";
            for (String id : page2IDs) {
                assert !page1IDs.contains(id) : "Duplicate ID found between page 1 and 2: " + id;
            }
        }
    }

    @Step("Verify row count does not exceed selected page size")
    public void verifyRowCountDoesNotExceed(String size) {
        int actualRows = getRowCount();
        assert actualRows <= Integer.parseInt(size) : "Displayed " + actualRows + " rows, exceeding page size " + size;
    }

}