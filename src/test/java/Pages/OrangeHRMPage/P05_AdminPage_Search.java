package Pages.OrangeHRMPage;

import Base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class P05_AdminPage_Search extends BaseTest {
    private final Page page;

    // Locators
    private final Locator adminMenu;
    private final Locator usernameInput;
    private final Locator employeeNameInput;
    private final Locator userRoleDropdown;
    private final Locator statusDropdown;
    private final Locator searchButton;
    private final Locator resultRows;
    private final Locator noRecordsMessage;
    private final Locator toastMessage;
    private final Locator InvalidText;

    public P05_AdminPage_Search(Page page) {
        this.page = page;

        adminMenu = page.locator("//span[text()='Admin']");
        usernameInput = page.locator("//label[text()='Username']/parent::div/following-sibling::div/input");
        employeeNameInput = page.getByPlaceholder("Type for hints...");
        userRoleDropdown = page.locator("//label[text()='User Role']/parent::div/following-sibling::div");
        statusDropdown = page.locator("//label[text()='Status']/parent::div/following-sibling::div");
        searchButton = page.locator("//button[normalize-space()='Search']");
        resultRows = page.locator("//div[@class='oxd-table-body']//div[@role='row']");
        noRecordsMessage = page.locator("//span[text()='No Records Found']");
        toastMessage = page.locator("//div[contains(@class,'oxd-toast-start')]");
        InvalidText = page.locator("//span[text()= 'Invalid']");
    }

    @Step("Open Admin Page")
    public void openAdminPage() {
        adminMenu.click();
        page.waitForURL("**/admin/viewSystemUsers");
        page.waitForSelector("//div[@class='oxd-table-body']");
    }

    @Step("Enter Username: {username}")
    public void enterUsername(String username) {
        usernameInput.fill("");
        usernameInput.fill(username);
    }

    @Step("Enter Employee Name: {name}")
    public void enterEmployeeName(String name) {
        employeeNameInput.fill("");
        employeeNameInput.fill(name);
    }

    @Step("Select User Role: {role}")
    public void selectUserRole(String role) {
        selectDropdownOption(userRoleDropdown, role);
    }

    @Step("Select Status: {status}")
    public void selectStatus(String status) {
        selectDropdownOption(statusDropdown, status);
    }

    @Step("Click Search")
    public void clickSearch() {
        searchButton.click();
        page.waitForTimeout(2000);
    }

    @Step("Check if records are displayed for username: {expectedUsername}")
    public boolean isRecordFound(String expectedUsername) {
        int rowCount = resultRows.count();
        for (int i = 0; i < rowCount; i++) {
            String actualUsername = resultRows.nth(i).locator("div:nth-child(2)").innerText().trim();
            if (actualUsername.equalsIgnoreCase(expectedUsername)) {
                return true;
            }
        }
        return false;
    }

    @Step("Check if 'No Records Found' message is displayed")
    public boolean isNoRecordMessageDisplayed() {
        waitForVisible(noRecordsMessage, 5000);
        return noRecordsMessage.isVisible();
    }

    public boolean isNoRecordBoxDisplayed() {
        waitForVisible(toastMessage, 5000);
        return toastMessage.isVisible();
    }

    public boolean isInvalidDisplayed() {
        waitForVisible(InvalidText, 5000);
        return InvalidText.isVisible();
    }

    public int getNumberOfRecords() {
        return resultRows.count();
    }

    // Helper method to select dropdown option
    private void selectDropdownOption(Locator dropdown, String optionText) {
        dropdown.click();
        page.locator(String.format("//div[@role='option']//span[normalize-space()='%s']", optionText)).click();
    }
}