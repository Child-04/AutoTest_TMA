package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P07_External_TooltipPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC07_TooltipTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TC07_TooltipTest.class);
    private P07_External_TooltipPage toolTipPage;

    @BeforeMethod
    public void setup() {
        log.info("=== Setup: Navigate to Tooltip demo page ===");
        super.setup();
        page.navigate("https://demoqa.com/tool-tips");
        log.info("Navigated to https://demoqa.com/tool-tips");
        page.evaluate("setTimeout(function(){debugger;}, 3000);");
        log.info("Added debugger delay for tooltip rendering");
        toolTipPage = new P07_External_TooltipPage(page);
        log.info("Initialized P07_External_TooltipPage object");
    }

    @Test(priority = 1, description = "Verify tooltip of Button")
    public void testButtonTooltip() {
        log.info("=== Test 1: Verify tooltip of Button ===");
        toolTipPage.hoverButton();
        log.info("Hovered over Button to trigger tooltip");
        page.evaluate("setTimeout(function(){debugger;}, 3000);");
        log.info("Waited for tooltip to appear via debugger delay");
        String tooltip = toolTipPage.getTooltipTextBTN();
        log.info("Captured tooltip text for Button: {}", tooltip);
        Assert.assertEquals(tooltip, "You hovered over the Button", "Tooltip for Button is incorrect!");
        log.info("Verified tooltip text for Button is correct");
        takeScreenshot("Display tooltip of Button");
        log.info("Screenshot captured: Display tooltip of Button");
    }

    @Test(priority = 2, description = "Verify tooltip of Textbox")
    public void testTextBoxTooltip() {
        log.info("=== Test 2: Verify tooltip of Textbox ===");
        toolTipPage.hoverTextBox();
        log.info("Hovered over Textbox to trigger tooltip");
        String tooltip = toolTipPage.getTooltipTextBox();
        log.info("Captured tooltip text for Textbox: {}", tooltip);
        Assert.assertEquals(tooltip, "You hovered over the text field", "Tooltip for TextBox is incorrect!");
        log.info("Verified tooltip text for Textbox is correct");
        takeScreenshot("Display tooltip of Textbox");
        log.info("Screenshot captured: Display tooltip of Textbox");
    }

    @Test(priority = 3, description = "Verify tooltip of word 'Contrary'")
    public void testContraryTooltip() {
        log.info("=== Test 3: Verify tooltip of text 'Contrary' ===");
        toolTipPage.hoverContraryText();
        log.info("Hovered over 'Contrary' text to trigger tooltip");
        String tooltip = toolTipPage.getTooltipContraryText();
        log.info("Captured tooltip text for 'Contrary': {}", tooltip);
        Assert.assertEquals(tooltip, "Hooray!", "Tooltip for Contrary text is incorrect!");
        log.info("Verified tooltip text for 'Contrary' is correct");
        takeScreenshot("Display tooltip of Contrary Text");
        log.info("Screenshot captured: Display tooltip of Contrary Text");
    }
}
