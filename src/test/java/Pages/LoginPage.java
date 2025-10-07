package Pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;


@Epic("Authentication")
@Feature("Login Feature")
public class LoginPage {
    private final Page page;
    private final String errorLocator = "//div[contains(@class,'oxd-alert oxd-alert--error')]";
    private final String errorUserReq = "//input[@name='username']/ancestor::div[contains(@class,'oxd-form-row')]//span";
    private final String errorPassReq = "//input[@name='password']/ancestor::div[contains(@class,'oxd-form-row')]//span";
    private final String usernameInput = "//input[@name='username']";
    private final String passwordInput = "//input[@name='password']";
    private final String loginButton = "//button[@type='submit']";

    public  LoginPage(Page page) {
        this.page = page;
    }
    @Step("Step 1 : Open login page")
    public void openLoginPage() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Step("Step 2 : Login with username: {0} and password: {1}")
    public void loginAs(String username, String password) {
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

    @Step("Step 3 : Check if login is successful")
    public boolean isLoginSuccess() {
        page.waitForURL("**/dashboard/index");
        return page.url().contains("/dashboard/index");
    }

    @Step("Step 3 : Get invalid credentials error message")
    public String getInvalidError() {
        return page.locator(errorLocator).textContent().trim();
    }

    @Step("Step 3: Get 'Required' message for username")
    public String getUserRequiredError() {
        return page.locator(errorUserReq).textContent().trim();
    }

    @Step("Step 3 : Get 'Required' message for Password")
    public String getPassRequiredError() {
        return page.locator(errorPassReq).textContent().trim();
    }
    @Step("Step 3: Verify both 'Required' messages for empty username and password")
    public void verifyRequiredMessagesForEmptyCredentials() {
        String userError = getUserRequiredError();
        String passError = getPassRequiredError();
        assert userError.equals("Required") : "Username error not matched";
        assert passError.equals("Required") : "Password error not matched";
    }


}
