package Pages;
import Base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class P05_AdminPage_Search extends BaseTest {
    private Page page;
    // Locators
    private final Locator adminMenu;
    private final Locator usernameInput;
    private final Locator userRoleDropdown;
    private final Locator employeeNameInput;
    private final Locator statusDropdown;
    private final Locator searchBtn;
    private final Locator noRecordsMsg;
    private final Locator resultRows;
    private final Locator noRecordsBox;
    private final Locator dropdownRole;
    private final Locator dropdownStatus;

    public P05_AdminPage_Search(Page page) {
        this.page = page;

        this.adminMenu = page.locator("//span[text()='Admin']");
        this.usernameInput = page.locator("//label[text()='Username']/following::input[1]");
        this.userRoleDropdown = page.locator("//label[text()='User Role']/parent::div/following-sibling::div");
        this.employeeNameInput = page.locator("//label[text()='Employee Name']/following::input[1]");
        this.statusDropdown = page.locator("//label[text()='Status']/parent::div/following-sibling::div");
        this.searchBtn = page.locator("//button[normalize-space()='Search']");
        this.noRecordsMsg = page.locator("//span[contains(normalize-space(),'No Records Found')]");
        this.resultRows = page.locator("//div[@class='oxd-table-body']//div[@role='row']");
        this.noRecordsBox = page.locator("//div[contains(@class,'oxd-toast-start')]");
        this.dropdownRole = page.locator("//div[@role='option']//span[normalize-space()='%s']");
        this.dropdownStatus = page.locator("//div[@role='option']//span[contains(text(),'%s')]");
    }

        @Step("Open Admin Page")
        // Action: click Admin menu
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

        @Step("Select User Role: {role}")
        public void selectUserRole(String role) {
            userRoleDropdown.click();
            page.locator(String.format(role, dropdownRole)).click();
        }

        @Step("Enter Employee Name: {name}")
        public void enterEmployeeName(String name) {
            employeeNameInput.fill("");
            employeeNameInput.fill(name);
        }

            @Step("Select Status: {status}")
            public void selectStatus(String status) {
                statusDropdown.click();
                page.locator(String.format(status, dropdownStatus)).click();
            }

        @Step("Click Search")
        public void clickSearch() {
            searchBtn.click();
        }

        @Step("Check if records are displayed")
        // check result
        public boolean isRecordFound(String expectedUsername) {
            Locator rows = resultRows;
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
        waitForVisible(noRecordsMsg, 5000);
            return noRecordsMsg.isVisible();
        }

        public boolean isNoRecordBoxDisplayed() {
            waitForVisible(noRecordsBox, 5000);
            return noRecordsBox.isVisible();
        }

    // Check record
    public boolean isRecordDisplayed() {
        Locator rows = noRecordsBox;
        return rows.count() > 0;
    }

    // Đếm số record hiển thị trong bảng
    public int getNumberOfRecords() {
        Locator rows = resultRows;
        return rows.count();
    }




}
