package Pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.*;
public class AdminPage_Search_03 {
    private Page page;
    // Locators
    private String adminMenu = "//span[text()='Admin']";

    private final String usernameInput= "//label[text()='Username']/following::input[1]";
    private final String userRoleDropdown = "//label[text()='User Role']/parent::div/following-sibling::div";
    private final String employeeNameInput = "//label[text()='Employee Name']/following::input[1]";
    private final String statusDropdown = "//label[text()='Status']/parent::div/following-sibling::div";
    private final String searchBtn = "//button[normalize-space()='Search']";
    private final String noRecordsMsg = "//span[contains(@class,'oxd-text--span') and text()='No Records Found']";
    private final String resultRows = "//div[@class='oxd-table-body']//div[@role='row']";
    private final String noRecordsBox = "//div[contains(@class,'oxd-toast-content oxd-toast-content--info')]";

    public AdminPage_Search_03(Page page) {
        this.page = page;
    }

        @Step("Open Admin Page")
        // Action: click Admin menu
        public void openAdminPage() {
            page.locator(adminMenu).click();
            page.waitForURL("**/admin/viewSystemUsers");
            page.waitForSelector("//div[@class='oxd-table-body']");
        }

        @Step("Enter Username: {username}")
        public void enterUsername(String username) {
            page.locator(usernameInput).fill("");
            page.waitForSelector(usernameInput);
            page.locator(usernameInput).fill(username);
        }

        @Step("Select User Role: {role}")
        public void selectUserRole(String role) {
            page.locator(userRoleDropdown).click();
            page.locator("//div[@role='option']/span[normalize-space(text())='" + role + "']").click();
        }

        @Step("Enter Employee Name: {name}")
        public void enterEmployeeName(String name) {
            page.locator(employeeNameInput).fill("");
            page.locator(employeeNameInput).fill(name);
        }

        @Step("Select Status: {status}")
        public void selectStatus(String status) {
            page.locator(statusDropdown).click();
            page.locator("//div[@role='option']/span[normalize-space(text())='" + status + "']").click();
        }

        @Step("Click Search")
        public void clickSearch() {
            page.locator(searchBtn).click();
        }

        @Step("Check if records are displayed")
        // check result
        public boolean isRecordFound(String expectedUsername) {
            Locator rows = page.locator(resultRows);
            int rowCount = rows.count();

            for (int i = 0; i < rowCount; i++) {
                String actualUsername = rows.nth(i).locator("div:nth-child(2)").innerText().trim();
                if (actualUsername.equalsIgnoreCase(expectedUsername)) {
                    return true;
                }
            }
            return false;
        }


    @Step("Check if 'No Records Found' message is displayed")
        public boolean isNoRecordMessageDisplayed() {
            page.waitForSelector(noRecordsMsg);
            return page.locator(noRecordsMsg).isVisible();
        }

        public boolean isNoRecordBoxDisplayed() {
            page.waitForSelector(noRecordsBox, new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator(noRecordsBox).isVisible();
        }

    // Check record
    public boolean isRecordDisplayed() {
        Locator rows = page.locator("//div[@class='oxd-table-body']//div[@role='row']");
        return rows.count() > 0;
    }

    // Đếm số record hiển thị trong bảng
    public int getNumberOfRecords() {
        Locator rows = page.locator("//div[@class='oxd-table-body']//div[@role='row']");
        return rows.count();
    }




}
