package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class P09_DemoQA_TextInputPage {
    private final Page page;

    // Các locator
    private final Locator inputFullName;
    private final Locator inputEmail;
    private final Locator inputCurrentAddress;
    private final Locator inputPermanentAddress;
    private final Locator btnSubmit;
    private final Locator outputName;
    private final Locator outputEmail;
    private final Locator outputCurrentAddress;
    private final Locator outputPermanentAddress;
    private final Locator outputBox;

    // =================== Constructor ===================
    public P09_DemoQA_TextInputPage(Page page) {
        this.page = page;

        // input fields
        this.inputFullName = page.getByPlaceholder("Full Name");
        this.inputEmail = page.getByPlaceholder("name@example.com");
        this.inputCurrentAddress = page.getByPlaceholder("Current Address");
        this.inputPermanentAddress = page.locator("//textarea[@id='permanentAddress']");
        this.btnSubmit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));

        // output fields
        this.outputName = page.locator("//p[@id='name']");
        this.outputEmail = page.locator("//p[@id='email']");
        this.outputCurrentAddress = page.locator("//p[@id='currentAddress']");
        this.outputPermanentAddress = page.locator("//p[@id='permanentAddress']");
        this.outputBox = page.locator("//div[@id='output']");
    }

    @Step("Navigate to text box page")
    public void navigateToPage() {
        page.navigate("https://demoqa.com/text-box");

    }
    // =================== Actions ===================

    @Step("Fill all fields")
    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        inputFullName.fill(fullName);
        inputEmail.fill(email);
        inputCurrentAddress.fill(currentAddress);
        inputPermanentAddress.fill(permanentAddress);
    }

    @Step("Click submit button")
    public void submit() {
        btnSubmit.click();
    }

    // =================== Getters (verify output hiển thị) ===================
    public String getName() {
        return outputName.textContent().replace("Name:", "").trim();
    }

    public String getEmail() {
        return outputEmail.textContent().replace("Email:", "").trim();
    }

    public String getCurrentAddress() {
        return outputCurrentAddress.textContent().replace("Current Address :", "").trim();
    }

    public String getPermanentAddress() {
        return outputPermanentAddress.textContent().replace("Permananet Address :", "").trim();
    }

    // =================== Helper ===================
    public boolean isOutputVisible() {
        return outputBox.isVisible();
    }
}