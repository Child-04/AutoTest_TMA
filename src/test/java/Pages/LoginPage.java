package Pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final String errorLocator = "//div[contains(@class,'oxd-alert oxd-alert--error')]";
    // Xpath locators
    private final String usernameInput = "//input[@name='username']";
    private final String passwordInput = "//input[@name='password']";
    private final String loginButton   = "//button[@type='submit']";
    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Action: login
    public void loginAs(String username, String password) {
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }
    public String getErrorMessage() {
        return page.textContent(errorLocator).trim();
    }

}
