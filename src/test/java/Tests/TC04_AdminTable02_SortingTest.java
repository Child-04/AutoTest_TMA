package Tests;

import Base.LoggedInBaseTest;
import Pages.P04_AdminPage_02_StringandLocatorParameter;
import com.microsoft.playwright.Locator;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static Data.TestData.*;

public class  TC04_AdminTable02_SortingTest extends LoggedInBaseTest {

    private P04_AdminPage_02_StringandLocatorParameter adminPage;
    private Utils.ScreenshotUtil screenshot;

    @BeforeMethod
    public void goToAdminPage() {
        adminPage = new P04_AdminPage_02_StringandLocatorParameter(page);
        adminPage.openAdminPage();
    }

   // @Test
//    public void printHeader(){
//        List<String> headers = adminPage.getTableHeaders();
//        System.out.println("Headers: " + headers);
//    }

    @Test(description = "Verify sorting of Username column in ascending order")
    @Description("System should correctly sort the Username column in ascending order")
    public void TC07_testSortUserNameAsc() {
        String columnName = "Username";
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        screenshot.takeStepScreenshot("Sort Username asc");
    }

    @Test(description = "Verify that sorting of Username column in descending order")
    @Description("System should sort correctly the Username in descending order")
    public void TC08_testSortUserNameDesc() {
        String columnName = USERNAME;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        screenshot.takeStepScreenshot("sort Username desc");
    }

    @Test(description = "Verify that sorting of Employee Name in ascending order")
    @Description("System should sort correctly the Employee Name in ascending order")
    public void TC09_testSortEmployeeNameAsc() {
        String columnName = EMPLOYEE_NAME;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        screenshot.takeStepScreenshot("sortEmployeeName asc");
    }
    @Test(description = "Verify that sorting of Employee Name in descending order")
    @Description("System should sort correctly the Employee Name in descending order")
    public void TC10_testSortEmployeeNameDesc() {
        String columnName = EMPLOYEE_NAME;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        screenshot.takeStepScreenshot("sortEmployeeName desc");
    }

    @Test(description = "Verify that sorting of User Role in ascending order")
    @Description("System should sort correctly the User Role in ascending order ")
    public void TC11_testSortUserRoleAsc() {
        String columnName = USER_ROLE;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        screenshot.takeStepScreenshot("Sort User Role asc");
    }
    @Test(description = "Verify that sorting of User Role in descending order")
    @Description("System should sort correctly the User Role in descending order ")
    public void TC12_testSortUserRoleDesc() {
        String columnName = USER_ROLE;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        screenshot.takeStepScreenshot("sort User Role desc");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC13_testSortStatusAsc() {
        String columnName = STATUS;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "asc");
        screenshot.takeStepScreenshot("Sort Status asc");
    }

    @Test(description = "Verify that sorting of Status in ascending order")
    @Description("System should sort correctly the Status in ascending order ")
    public void TC14_testSortStatusDesc() {
        String columnName = STATUS;
        Locator columnLocator = adminPage.getColumnLocatorByName(columnName);
        adminPage.VerifySort(columnName, columnLocator, "desc");
        screenshot.takeStepScreenshot("sort Status desc");
    }
}