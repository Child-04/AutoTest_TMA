package Pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

public class PaginationPage {
    private final Page page;

    private final String tableRows = "//table//tbody/tr";
    private final String firstCellOfRow = "//table/tbody/tr[1]";
    private final String nextBTN = "//button[text()='Next']";
    private final String prevBTN = "//button[text()='Previous']";
    private final String firstBTN = "//button[text()='<<']";
    private final String lastBTN = "//button[text()='>>']";
    private final String numberInput = "//input[@type='number']";
    private final String pageSizeDropdown = "//select";

    public PaginationPage(Page page) {
        this.page = page;
    }

    //purpose : select the number of line to display
    @Step("Select the number of lines to display: {size}")
    public void selectPageSize(String size) {
        page.selectOption(pageSizeDropdown, size);
        page.waitForTimeout(1000);
    }

    //purpose : count how many rows in a page -> check end compare expected
    @Step("Get the current displayed line number")
    public int getRowCount() {
        return page.locator(tableRows).count();
    }

    //purpose : compare when entering number in textbox and click button moving to another page
    @Step("Get the current page")
    public int getCurrentPageNumber() {
        String pageText = page.locator("//span[contains(text(),'Page')]").innerText(); // "Page 1 of 20"
        String current = pageText.replaceAll("[^0-9]", " ").trim().split("\\s+")[0]; // remove letter, get the first number
        return Integer.parseInt(current);
    }

    //Check the last page
    @Step("Get total number of page")
    public int getTotalPages() {
        String pageText = page.locator("//span[contains(text(),'Page')]").innerText(); // "Page 1 of 20"
        String[] numbers = pageText.replaceAll("[^0-9]", " ").trim().split("\\s+");
        return Integer.parseInt(numbers[1]); // get the second number
    }


    @Step("Enter the page number to go to: {target}")
    public void goToPage(int target) {
        page.locator(numberInput).fill(String.valueOf(target));
        page.keyboard().press("Enter");
        page.waitForTimeout(1000);
    }

    @Step("Get list ID display on current page")
    public List<String> getVisibleRowIDs() {
        return page.locator("//table//tbody/tr/td[1]").allInnerTexts();
    }

    public boolean isButtonDisabled(String xpath) {
        return page.locator(xpath).getAttribute("disabled") != null;
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



}
