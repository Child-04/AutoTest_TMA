package Pages.OrangeHRM;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.*;

import static Utils.TestData.*;


@Epic("Authentication")
@Feature("Login Feature")
public class P01_LoginPage {
    private final Page page;

    // Locators
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator invalidErrorMessage;
    private final Locator usernameRequiredMessage;
    private final Locator passwordRequiredMessage;
    private final Locator dashboard;

    // Constructor
    public P01_LoginPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("//input[@name='username']");
        this.passwordField = page.locator("//input[@name='password']");
        this.loginButton = page.locator("//button[@type='submit']");
        this.invalidErrorMessage = page.locator("//p[contains(normalize-space(), 'Invalid credentials')]");
        this.usernameRequiredMessage = page.locator("//input[@name='username']/following::span").first();
        this.passwordRequiredMessage = page.locator("//input[@name='password']/following::span").first();
        this.dashboard = page.locator("//h6[text() = 'Dashboard']");
    }

    @Step("Step: Open login page")
    public void openLoginPage() {
        page.navigate(BASE_URL);
        page.waitForLoadState();
    }
    @Step("Step: Open OrangeHRM login page and login successfully")
    public void enterUserAccount() {

        usernameField.fill(VALID_USERNAME);
        passwordField.fill(VALID_PASSWORD);
        loginButton.click();
        page.waitForURL("**/dashboard/index");
        dashboard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }


    @Step("Step: Login with username: {username}, password: {password}")
    public void loginAs(String username, String password) {
        usernameField.waitFor();
        usernameField.fill(username);
        passwordField.waitFor();
        passwordField.fill(password);
        loginButton.click();
    }

    @Step("Step: Verify login is successful")
    public boolean isLoginSuccess() {
        page.waitForURL("**/dashboard/index");
        return page.url().contains("/dashboard/index");

    }

    @Step("Get invalid login error")
    public String getInvalidError() {
        invalidErrorMessage.waitFor();
        return invalidErrorMessage.textContent().trim();
    }

    @Step("Get username required error")
    public String getUsernameRequiredError() {
        usernameRequiredMessage.waitFor();
        return usernameRequiredMessage.textContent().trim();
    }

    @Step("Get password required error")
    public String getPasswordRequiredError() {
        passwordRequiredMessage.waitFor();
        return passwordRequiredMessage.textContent().trim();
    }

    @Step("Verify both 'Required' messages for empty username and password")
    public void verifyRequiredMessagesForEmptyCredentials() {
        String userError = getUsernameRequiredError();
        String passError = getPasswordRequiredError();
        assert userError.equals("Required") : "Username error not matched";
        assert passError.equals("Required") : "Password error not matched";
    }


}
