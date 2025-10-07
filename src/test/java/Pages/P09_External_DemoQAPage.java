package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class P09_External_DemoQAPage {
    private final Page page;

    private final Locator PermanentAddress;
    // Constructor
    public P09_External_DemoQAPage(Page page) {
        this.page = page;
        this.PermanentAddress = page.locator("//textarea[@id = 'permanentAddress']");
    }

    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        page.getByLabel("Full Name");
        page.getByPlaceholder("Full Name").fill(fullName);
        page.getByPlaceholder("name@example.com").fill(email);
        page.getByPlaceholder("Current Address").fill(currentAddress);
        PermanentAddress.fill(permanentAddress);
    }

    public void submit() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        page.waitForSelector("//div[@id='output']");
    }

    // =================== Getters (verify output hiển thị) ===================
    public String getName() {
        return page.textContent("//p[@id='name']").replace("Name:", "").trim();
    }

    public String getEmail() {
        return page.textContent("//p[@id='email']").replace("Email:", "").trim();
    }

    public String getCurrentAddress() {
        return page.textContent("//p[@id='currentAddress']").replace("Current Address :", "").trim();

    }

    public String getPermanentAddress() {
        return page.textContent("//p[@id='permanentAddress']").replace("Permananet Address :", "").trim();

    }

    // =================== Helper ===================
    public boolean isOutputVisible() {
        return page.isVisible("//div[@id='output']");
    }

}
