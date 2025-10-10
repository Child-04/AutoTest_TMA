package Tests;

import Base.BaseTest;
import Pages.P10_External_CheckBoxPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC10_CheckboxesTest extends BaseTest {

    private P10_External_CheckBoxPage checkboxPage;

    @BeforeMethod
    public void openPage() {
        checkboxPage = new P10_External_CheckBoxPage(page);
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void testCheckbox1() {
        // Initial status: not checked
        Assert.assertFalse(checkboxPage.isBox1Checked(), "Checkbox 1 should be unchecked by default");

        // Check checkbox 1
        checkboxPage.checkBox1();
        Assert.assertTrue(checkboxPage.isBox1Checked(), "Checkbox 1 should be checked after checking");
        takeScreenshot("Checkbox1_Checked");

        // Uncheck checkbox 1
        checkboxPage.uncheckBox1();
        Assert.assertFalse(checkboxPage.isBox1Checked(), "Checkbox 1 should be unchecked after unchecking");
        takeScreenshot("Checkbox1_Unchecked");

    }
    @Test
    public void testCheckbox2() {
        // Initial status: not checked
        Assert.assertTrue(checkboxPage.isBox2Checked(), "Checkbox 2 should be unchecked by default");

        // Check checkbox 2
        checkboxPage.checkBox2();
        Assert.assertTrue(checkboxPage.isBox2Checked(), "Checkbox 2 should be checked after checking");
        takeScreenshot("Checkbox2_Checked");

        // Uncheck checkbox 2
        checkboxPage.uncheckBox2();
        Assert.assertFalse(checkboxPage.isBox2Checked(), "Checkbox 2 should be unchecked after unchecking");
        takeScreenshot("Checkbox2_Unchecked");

    }

    @Test(description = "Verify checking and unchecking both checkboxes")
    public void testCheckAndUncheckAll() {

        // Step 1: Uncheck all
        checkboxPage.uncheckAll();
        Assert.assertTrue(checkboxPage.areAllUnchecked(), "All checkboxes should be unchecked at start");
        takeScreenshot("All_Unchecked_Initially");

        // Step 2: Check all
        checkboxPage.checkAll();
        Assert.assertTrue(checkboxPage.areAllChecked(), "Both checkboxes should be checked");
        takeScreenshot("All_Checked");

        // Step 3: Uncheck all
        checkboxPage.uncheckAll();
        Assert.assertTrue(checkboxPage.areAllUnchecked(), "Both checkboxes should be unchecked");
        takeScreenshot("All_Unchecked_Again");
    }
}
