package Tests;
import Base.BaseTest;
import Pages.P01_LoginPage;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Data.TestData.*;

public class TC01_LoginTest extends BaseTest {
    private P01_LoginPage loginPage;

    @BeforeMethod
    @Step("Step 1: Open login page")
    public void setUpTest() {
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();

    }
    @Test(description = "Verify login with valid credentials")
    @Description("User should be able to login with correct username and password")
    public void TC01_testLoginSuccess() {
        loginPage.loginAs(VALID_USERNAME , VALID_PASSWORD);
        Assert.assertTrue(loginPage.isLoginSuccess(), "Login failed!");
    }

    @Test(description = "Verify login with valid credentials")
    @Description("User should be able to login with correct username and password")
    public void TC02_login_WithWrongUsername_ShouldShowErrorMessage() {
        loginPage.loginAs(INVALID_USERNAME, VALID_PASSWORD);
        page.click("button[type='submit']");
        Assert.assertEquals(loginPage.getInvalidError(), "Invalid credentials");
    }

    @Test(description = "Login with empty username")
    @Description("System should show 'Required' message when username is empty")
    public void TC03_login_WithEmptyUsername_ShouldShowRequiredMessage() {
        loginPage.loginAs("", INVALID_PASSWORD);
        page.click("button[type='submit']");
        Assert.assertEquals(loginPage.getUsernameRequiredError(), "Required");
    }

    @Test(description = "Login with empty password")
    @Description("System should show 'Required' message when password is empty")
    public void TC04_login_WithEmptyPassword_ShouldShowRequiredMessage() {
        loginPage.loginAs(VALID_USERNAME ,  "");
        page.click("button[type='submit']");

        Assert.assertEquals(loginPage.getPasswordRequiredError(), "Required");
    }

    @Test(description = "Login with empty credentials")
    @Description("System should show two 'Required' messages when both fields are empty")
    public void TC05_login_WithEmptyCredentials_ShouldShowTwoRequiredMessages() {
        loginPage.loginAs("", "");
        loginPage.verifyRequiredMessagesForEmptyCredentials();
    }

}
