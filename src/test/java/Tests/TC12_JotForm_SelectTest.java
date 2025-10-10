package Tests;

import Base.BaseTest;
import Pages.P12_JotForm_SelectPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC12_JotForm_SelectTest extends BaseTest {
    private P12_JotForm_SelectPage selectPage;

    @BeforeMethod
    public void openPage() {
        selectPage = new P12_JotForm_SelectPage(page);
        page.navigate("https://automationfc.github.io/multiple-fields/");
    }

    @Test(description = "Check the Female selection in the Gender dropdown")
    public void testSelectFemale() {

        // Select Female
        selectPage.selectGender("Female");

        // Verify selected value
        String selected = selectPage.getSelectedGender();
        Assert.assertEquals(selected, "Female", "The selected value is incorrect!");
        takeScreenshot("Female selection");
    }

    @Test(description = "Check the Male selection in the Gender dropdown")
    public void testSelectMale() {
        // Select Male
        selectPage.selectGender("Male");

        // Verify selected value
        String selected = selectPage.getSelectedGender();
        Assert.assertEquals(selected, "Male", "The selected value is incorrect!");
        takeScreenshot("Male selection");
    }

    @Test(description = "Check the Empty selection in the Gender dropdown")
    public void testSelectEmpty() {

        // Select Empty
        selectPage.selectGender("");

        // Verify selected value
        String selected = selectPage.getSelectedGender();
        Assert.assertEquals(selected, "", "The selected value is incorrect!");
        takeScreenshot("Empty selection");
    }
}
