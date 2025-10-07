package Tests;

import Base.BaseTest;
import Pages.TooltipPage;
import Utils.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC07_TooltipTest extends BaseTest {
    private TooltipPage toolTipPage;
    private Utils.ScreenshotUtil screenshot;

    @BeforeMethod
    public void setup() {
        super.setup();
        page.navigate("https://demoqa.com/tool-tips");
        page.evaluate("setTimeout(function(){debugger;}, 3000);");
        toolTipPage = new TooltipPage(page);
        screenshot = new ScreenshotUtil(page);
    }

    @Test(priority = 1, description = "Verify tooltip of Button")
    public void testButtonTooltip() {
        toolTipPage.hoverButton();
        page.evaluate("setTimeout(function(){debugger;}, 3000);");
        String tooltip = toolTipPage.getTooltipTextBTN();
        Assert.assertEquals(tooltip, "You hovered over the Button", "Tooltip for Button is incorrect!");
        screenshot.takeScreenshot("Display tooltip of Button");
    }

    @Test(priority = 2, description = "Verify tooltip of Textbox")
    public void testTextBoxTooltip() {
        toolTipPage.hoverTextBox();
        String tooltip = toolTipPage.getTooltipTextBox();
        Assert.assertEquals(tooltip, "You hovered over the text field", "Tooltip for TextBox is incorrect!");
    }

    @Test(priority = 3, description = "Verify tooltip of word 'Contrary'")
    public void testContraryTooltip() {
        toolTipPage.hoverContraryText();
        String tooltip = toolTipPage.getTooltipContraryText();
        Assert.assertEquals(tooltip, "Hooray!", "Tooltip for Contrary text is incorrect!");
    }

}
