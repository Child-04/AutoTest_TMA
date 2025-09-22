package Pages;

import com.microsoft.playwright.Page;

public class AdminPage {
    private Page page;

    // Locator
    private String adminMenu = "//span[text()='Admin']";

    // Constructor
    public AdminPage(Page page) {
        this.page = page;

    }

    // Action
    public void openAdminPage() {
        page.locator(adminMenu).click();
        page.waitForURL("**/admin/viewSystemUsers");
        page.waitForSelector("//div[@class='oxd-table-body']");
        page.waitForTimeout(5000);
    }
}
