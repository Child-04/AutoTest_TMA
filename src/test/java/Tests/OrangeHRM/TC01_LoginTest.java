package Tests.OrangeHRM;
import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.TestData.*;

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
    public void login_WithWrongUsername_ShouldShowErrorMessage() {
        loginPage.loginAs(INVALID_USERNAME, VALID_PASSWORD);
        Assert.assertEquals(loginPage.getInvalidError(), "Invalid credentials");
        takeScreenshot("InvalidUsername");
    }

    @Test(description = "Login with empty username")
    @Description("System should show 'Required' message when username is empty")
    public void login_WithEmptyUsername_ShouldShowRequiredMessage() {
        loginPage.loginAs("", INVALID_PASSWORD);
        Assert.assertEquals(loginPage.getUsernameRequiredError(), "Required");
        takeScreenshot("EmptyUsername");
    }

    @Test(description = "Login with empty password")
    @Description("System should show 'Required' message when password is empty")
    public void login_WithEmptyPassword_ShouldShowRequiredMessage() {
        loginPage.loginAs(VALID_USERNAME ,  "");
        Assert.assertEquals(loginPage.getPasswordRequiredError(), "Required");
        takeScreenshot("EmptyPassword");
    }

    @Test(description = "Login with empty credentials")
    @Description("System should show two 'Required' messages when both fields are empty")
    public void login_WithEmptyCredentials_ShouldShowTwoRequiredMessages() {
        loginPage.loginAs("", "");
        loginPage.verifyRequiredMessagesForEmptyCredentials();
        takeScreenshot("EmptyCredentials");
    }

    @Test(description = "Verify login with valid credentials")
    @Description("User should be able to login with correct username and password")
    public void testLoginSuccess() {
        loginPage.loginAs(VALID_USERNAME , VALID_PASSWORD);
        Assert.assertTrue(loginPage.isLoginSuccess(), "Login failed!");
        takeScreenshot("LoginSuccess");
    }

}
