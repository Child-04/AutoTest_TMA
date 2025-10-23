package Tests.OrangeHRM;

import Base.BaseTest;
import Pages.OrangeHRM.*;
import Utils.CredentialUtils;
import Utils.DataProviders;
import Utils.SystemUser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utils.TestData;

import java.io.IOException;
import java.nio.file.Paths;

import static Utils.TestData.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertNotNull;


public class TC17_UserLifecycleTests extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TC17_UserLifecycleTests.class);
    P01_LoginPage loginPage;
    P19_AddEmployeePage addEmployeePage;
    P18_PIMPage pimPage;
    P17_UserManagementPage userManagementPage;
    P20_AddUserPage addUserPage;
    String uniqueFirstName;
    String uniqueMiddleName;
    String uniqueLastName;
    String uniqueFullName;
    String uniqueUserName;
    String uniqueUserId;

    @BeforeClass
    public void generateAccount() {
        log.info("======== Generate unique data for User ========");
        uniqueFirstName = TestData.generateUniqueFirstName();
        log.info("FirstName: " + uniqueFirstName);
        uniqueLastName = TestData.generateUniqueLastName();
        log.info("LastName: " + uniqueLastName);
        uniqueMiddleName = TestData.generateUniqueMiddleName();
        log.info("MiddleName: " + uniqueMiddleName);
        if(uniqueMiddleName == null){
            uniqueFullName = uniqueFirstName + " " + " "+ uniqueLastName;
            log.info("FullName: " + uniqueFullName);
        }
        else {
            uniqueFullName = uniqueFirstName + " " + uniqueMiddleName + " " + uniqueLastName;
            log.info("FullName: " + uniqueFullName);
        }
        uniqueUserName = TestData.generateUniqueUsername();
        log.info("Unique: " + uniqueUserName);
        uniqueUserId = TestData.generateRandomUserId(5);
        log.info("Id: " +uniqueUserId);

    }

    @BeforeMethod
    public void setUpUserLifecycle(){

        loginPage = new P01_LoginPage(page);
        addEmployeePage = new P19_AddEmployeePage(page);
        pimPage = new P18_PIMPage(page);
        userManagementPage = new P17_UserManagementPage(page);
        addUserPage = new P20_AddUserPage(page);
        loginPage.openLoginPage();
        loginPage.enterUserAccount();
    }

    @Test(description = "Test flow: Create Employee & User")
    @Severity(SeverityLevel.NORMAL)
    public void createEmployeeAndUser(){
        createEmployee();
        takeScreenshot("create Employee And User");

        createUser(3);
        log.info("======== Test Login ========");
        // 2. Chạy test login ngay lập tức sau khi tạo user
        loginWithCreatedUsers();
    }

//    @Test(description = "Test flow: Delete Empolyee & User",
//            dependsOnMethods = {"createEmployeeAndUser"})
//    @Description("Clean up data after test")
//    @Severity(SeverityLevel.NORMAL)
//    public void deleteEmployeeAndUser(){
//        deleteUser();
//        deleteEmployee();
//    }

    private void createEmployee(){
        log.info("======== Create Employee ========");
        pimPage.clickPIMSideBarButton();
        pimPage.navigateToAddEmployeePage();
        addEmployeePage.addEmployee(uniqueFirstName,uniqueMiddleName,uniqueLastName,uniqueUserId);
        takeScreenshot("create Employee");
        addEmployeePage.clickSaveButton();
        //addEmployeePage.clickCreateLoginDetailsButton();
        //addEmployeePage.addDetailsUser(uniqueUserName, AccountData.EMPLOYEEPASSWORD, AccountData.EMPLOYEEPASSWORD);
        Assert.assertTrue(addEmployeePage.isCreateSuccessfully(), "Create fail");
        log.info("Create Successfully");

    }

    private void createUser(int count) {
        log.info("======== Create " + count + " users ========");

        // Open Admin Page
        userManagementPage.clickAdminSideBarButton();

        for (int i = 1; i <= count; i++) {
            log.info("---- Creating user " + i + " of " + count + " ----");

            // 1 Generate unique username for each user
            String newUserName = TestData.generateUniqueUsername();
            String password = EMPLOYEEPASSWORD;

            // 2 Open form Add User
            addUserPage.clickAddButton();
            Assert.assertTrue(addUserPage.isAddUserFormVisible(), "Form Add User must be visible");

            // 3 Enter user information
            addUserPage.selectUserRole();
            addUserPage.selectStatus();
            addUserPage.inputUserInfo(uniqueFirstName, uniqueFullName, newUserName, password, password);
            takeScreenshot("add user " + i + " of " + count + " ----");
            addUserPage.clickSaveButton();

            // 4 Verify user creation is successful
            Assert.assertTrue(addEmployeePage.isCreateSuccessfully(), "Create fail");
            log.info("Created Successfully: " + newUserName);

            // 5 Verify user appears in table
            userManagementPage.clickAdminSideBarButton();
            userManagementPage.searchUsername(newUserName);
            userManagementPage.waitForSearchResult();
            Assert.assertTrue(userManagementPage.isUserPresentInTable(newUserName),
                    "User search result should return exactly one record");

            takeScreenshot("search user " + i + " of " + count + " ----");

            // 6 Save user information to CSV file
            try {
                CredentialUtils.appendCredentialCsv(
                        Paths.get("src/test/resources/files/users.csv"),
                        newUserName,
                        password
                );
                System.out.println("Saved user " + newUserName + " to CSV");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            // 7 Return to the Admin page to prepare to create the next user
            userManagementPage.clickAdminSideBarButton();
        }

        log.info("======== Finished creating " + count + " users ========");


    }

    /**
     * Method to perform Data-Driven login test using accounts saved in users.csv.
     */
    private void loginWithCreatedUsers() {
        try {
            // Read data from users.csv
            var credentials = CredentialUtils.readCredentialsCsv(Paths.get("src/test/resources/files/users.csv"));

            Assert.assertFalse(credentials.isEmpty(), "CSV file users.csv cannot be empty.");

            for (String[] cred : credentials) {
                String username = cred[0];
                String password = cred[1];

                log.info("---- Attempting login for user: " + username + " ----");

                // 1. Return to login page
                loginPage.logOut();

                // 2. Perform login
                loginPage.loginAs(username, password);

                // 3. Check login success
                try {
                    // Chờ URL chuyển sang trang Dashboard (OrangeHRM)
                    page.waitForURL("**/dashboard/index", new Page.WaitForURLOptions().setTimeout(5000));
                    log.info("Login Successful for user: " + username);
                    Assert.assertTrue(true, "Login Successful cho user: " + username);
                } catch (TimeoutError e) {
                    log.error("Login FAILED for user: " + username + ". Please check your credentials or logical login information again.", e);
                    Assert.fail("Login FAILED for user: " + username);
                }
                takeScreenshot("login successful User : " + username);
            }
            log.info("======== Finished Login Test for all created users ========");

        } catch (IOException e) {
            log.error("Failed to read credentials from CSV.", e);
            Assert.fail("Unable to continue login test due to CSV file reading error.");
        }
    }



//    private void deleteUser(){
//        log.info("======== Delete user ========");
//        userManagementPage.clickAdminSideBarButton();
//        userManagementPage.searchUsername(uniqueUserName);
//        userManagementPage.waitForSearchResult();
//        userManagementPage.deleteUser();
//        Assert.assertTrue(userManagementPage.isDeleteSuccessfully(), "Delete fail");
//        log.info("Delete user Successfully");
//
//        log.info("======== Check user ========");
//        userManagementPage.searchUsername(uniqueUserName);
//
//        Assert.assertTrue(userManagementPage.isUsernameInvisibleAfterDelete(), "Notification No Record Found must be visible");
//        userManagementPage.waitForSearchResult();
//        Assert.assertTrue(userManagementPage.isUserNotVisibleInTable(uniqueUserName), "User should not be visible in table after deletion");
//        log.info("No record is found");
//    }
//
//    private void deleteEmployee(){
//        log.info("======== Delete employee ========");
//        pimPage.clickPIMSideBarButton();
//        pimPage.navigateToEmployeeListPage();
//        pimPage.searchEmployeeByFirstname(uniqueFirstName,uniqueFullName);
//        pimPage.waitForSearchResult();
//        pimPage.deleteEmployee();
//        Assert.assertTrue(pimPage.isDeleteSuccessfully(), "Delete fail");
//        log.info("Delete Employee Successfully");
//
//        log.info("======== Check employee ========");
//        pimPage.searchEmployeeByFirstname(uniqueFirstName,uniqueFullName);
//        Assert.assertTrue(pimPage.isEmployeeInvisibleAfterDelete(), "Notification No Record Found must be visible");
//        pimPage.waitForSearchResult();
//        Assert.assertTrue(pimPage.isEmployeeNotVisibleInTable(uniqueFullName), "Employee should not be visible in table after deletion");
//
//        log.info("No record is found");
//    }
}
