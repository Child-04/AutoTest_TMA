package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P10_External_CheckBoxPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TC10_CheckboxesTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC10_CheckboxesTest.class);
    private P10_External_CheckBoxPage checkboxPage;

    @BeforeMethod
    public void openPage() {
        checkboxPage = new P10_External_CheckBoxPage(page);
        log.info("Navigating to Checkboxes page...");
        page.navigate("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test(description = "Verify Checkbox 1 checking and unchecking behavior")
    public void testCheckbox1() {
        log.info("===== Starting test: testCheckbox1 =====");

        // Initial status
        log.info("Step 1: Verify Checkbox 1 is unchecked by default");
        Assert.assertFalse(checkboxPage.isBox1Checked(), "Checkbox 1 should be unchecked by default");

        // Check checkbox 1
        log.info("Step 2: Checking Checkbox 1...");
        checkboxPage.checkBox1();
        Assert.assertTrue(checkboxPage.isBox1Checked(), "Checkbox 1 should be checked after checking");
        takeScreenshot("Checkbox1_Checked");

        // Uncheck checkbox 1
        log.info("Step 3: Unchecking Checkbox 1...");
        checkboxPage.uncheckBox1();
        Assert.assertFalse(checkboxPage.isBox1Checked(), "Checkbox 1 should be unchecked after unchecking");
        takeScreenshot("Checkbox1_Unchecked");

        log.info("===== Finished test: testCheckbox1 =====");
    }

    @Test(description = "Verify Checkbox 2 checking and unchecking behavior")
    public void testCheckbox2() {
        log.info("===== Starting test: testCheckbox2 =====");

        // Initial status
        log.info("Step 1: Verify Checkbox 2 is checked by default");
        Assert.assertTrue(checkboxPage.isBox2Checked(), "Checkbox 2 should be checked by default");

        // Uncheck checkbox 2
        log.info("Step 2: Unchecking Checkbox 2...");
        checkboxPage.uncheckBox2();
        Assert.assertFalse(checkboxPage.isBox2Checked(), "Checkbox 2 should be unchecked after unchecking");
        takeScreenshot("Checkbox2_Unchecked");

        // Check checkbox 2 again
        log.info("Step 3: Checking Checkbox 2 again...");
        checkboxPage.checkBox2();
        Assert.assertTrue(checkboxPage.isBox2Checked(), "Checkbox 2 should be checked after checking again");
        takeScreenshot("Checkbox2_Checked");

        log.info("===== Finished test: testCheckbox2 =====");
    }

    @Test(description = "Verify checking and unchecking both checkboxes")
    public void testCheckAndUncheckAll() {
        log.info("===== Starting test: testCheckAndUncheckAll =====");

        // Step 1: Uncheck all
        log.info("Step 1: Unchecking all checkboxes...");
        checkboxPage.uncheckAll();
        Assert.assertTrue(checkboxPage.areAllUnchecked(), "All checkboxes should be unchecked at start");
        takeScreenshot("All_Unchecked_Initially");

        // Step 2: Check all
        log.info("Step 2: Checking all checkboxes...");
        checkboxPage.checkAll();
        Assert.assertTrue(checkboxPage.areAllChecked(), "Both checkboxes should be checked");
        takeScreenshot("All_Checked");

        // Step 3: Uncheck all again
        log.info("Step 3: Unchecking all checkboxes again...");
        checkboxPage.uncheckAll();
        Assert.assertTrue(checkboxPage.areAllUnchecked(), "Both checkboxes should be unchecked again");
        takeScreenshot("All_Unchecked_Again");

        log.info("===== Finished test: testCheckAndUncheckAll =====");
    }
}
