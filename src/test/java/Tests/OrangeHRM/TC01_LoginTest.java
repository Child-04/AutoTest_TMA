package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.P01_LoginPage;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Utils.TestData.*;

public class TC01_LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TC01_LoginTest.class);
    private P01_LoginPage loginPage;

    @BeforeMethod
    @Step("Step 1: Open login page")
    public void setUpTest() {
        log.info("Opening OrangeHRM login page...");
        loginPage = new P01_LoginPage(page);
        loginPage.openLoginPage();
        log.info("Login page opened successfully.");
    }

    @Test(description = "Verify login with valid credentials")
    @Description("User should be able to login with correct username and password")
    public void login_WithWrongUsername_ShouldShowErrorMessage() {
        log.info("Attempting login with VALID username and INVALID password.");
        loginPage.loginAs(VALID_USERNAME, INVALID_PASSWORD);
        log.info("Verifying error message after invalid login attempt.");
        Assert.assertEquals(loginPage.getInvalidError(), "Invalid credentials");
        log.info("Error message validated successfully.");
        takeScreenshot("InvalidUsername");
    }

    @Test(description = "Verify login with valid credentials")
    @Description("User should be able to login with correct username and password")
    public void login_WithWrongPassword_ShouldShowErrorMessage() {
        log.info("Attempting login with INVALID username and VALID password.");
        loginPage.loginAs(INVALID_USERNAME, VALID_PASSWORD);
        log.info("Verifying error message after invalid login attempt.");
        Assert.assertEquals(loginPage.getInvalidError(), "Invalid credentials");
        log.info("Error message validated successfully.");
        takeScreenshot("Invalid Password");
    }

    @Test(description = "Login with empty username")
    @Description("System should show 'Required' message when username is empty")
    public void login_WithEmptyUsername_ShouldShowRequiredMessage() {
        log.info("Attempting login with empty username and invalid password.");
        loginPage.loginAs("", INVALID_PASSWORD);
        log.info("Verifying required message for username.");
        Assert.assertEquals(loginPage.getUsernameRequiredError(), "Required");
        log.info("Required message for username validated successfully.");
        takeScreenshot("EmptyUsername");
    }

    @Test(description = "Login with empty password")
    @Description("System should show 'Required' message when password is empty")
    public void login_WithEmptyPassword_ShouldShowRequiredMessage() {
        log.info("Attempting login with valid username and empty password.");
        loginPage.loginAs(VALID_USERNAME, "");
        log.info("Verifying required message for password.");
        Assert.assertEquals(loginPage.getPasswordRequiredError(), "Required");
        log.info("Required message for password validated successfully.");
        takeScreenshot("EmptyPassword");
    }

    @Test(description = "Login with empty credentials")
    @Description("System should show two 'Required' messages when both fields are empty")
    public void login_WithEmptyCredentials_ShouldShowTwoRequiredMessages() {
        log.info("Attempting login with both username and password empty.");
        loginPage.loginAs("", "");
        log.info("Verifying both required messages are displayed.");
        loginPage.verifyRequiredMessagesForEmptyCredentials();
        log.info("Both required messages validated successfully.");
        takeScreenshot("EmptyCredentials");
    }

    @Test(description = "Verify login successful")
    @Description("User should be able to login with correct username and password")
    public void testLoginSuccess() {
        log.info("Attempting login with valid credentials.");
        loginPage.loginAs(VALID_USERNAME, VALID_PASSWORD);
        log.info("Verifying successful login.");
        Assert.assertTrue(loginPage.isLoginSuccess(), "Login failed!");
        log.info("Login successful, taking screenshot.");
        takeScreenshot("LoginSuccess");

        log.info("Navigating back to login page to perform logout.");
        loginPage.openLoginPage();
        loginPage.logOut();
        log.info("Logout completed successfully.");
    }
}
