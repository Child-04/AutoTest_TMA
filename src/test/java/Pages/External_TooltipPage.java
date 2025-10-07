package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class External_TooltipPage {
    private final Page page;

    // Locators
    private final Locator buttonTooltip;
    private final Locator textBoxTooltip;
    private final Locator contraryText;
    private final Locator tooltipContentBTN;
    private final Locator tooltipContentTextBox;
    private final Locator tooltipContentContrary;

    // Constructor
    public External_TooltipPage(Page page) {
        this.page = page;
        this.buttonTooltip = page.locator("//button[@id='toolTipButton']");
        this.textBoxTooltip = page.locator("//*[@id='toolTipTextField']");
        this.contraryText = page.locator("//a[text()='Contrary']");
        this.tooltipContentContrary = page.locator("//*[@class='tooltip-inner']");
        this.tooltipContentTextBox = page.locator("//*[@id='textFieldToolTip']/div[2]");
        this.tooltipContentBTN = page.locator("//*[@id='buttonToolTip']/div[2]");

    }
    // Actions
    public void hoverButton() {
        buttonTooltip.hover();
    }

    public void hoverTextBox() {
        textBoxTooltip.hover();
    }

    public void hoverContraryText() {
        contraryText.hover();
    }

    // Getter tooltip text
    public String getTooltipTextBTN() {
        tooltipContentBTN.waitFor();
        return tooltipContentBTN.innerText();
    }

    public String getTooltipTextBox() {
        tooltipContentTextBox.waitFor();
        return tooltipContentTextBox.innerText();
    }
    public String getTooltipContraryText() {
        tooltipContentContrary.waitFor();
        return tooltipContentContrary.innerText();
    }
}
