package Pages;
import Base.BaseTest;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPage {
    private Page page;

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

}
