package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class P16_Herokuapp_DrapandDropPage {
    private final Page page;
    private final Locator columnA;
    private final Locator columnB;
    private final Locator headerA;
    private final Locator headerB;

    // Constructor
    public P16_Herokuapp_DrapandDropPage(Page page) {
        this.page = page;
        columnA = page.locator("//div[@id='column-a']");
        columnB = page.locator("//div[@id='column-b']");
        headerA = page.locator("//div[@id='column-a']/header");
        headerB = page.locator("//div[@id='column-b']/header");
    }

    @Step("Navigate to Drag and Drop page")
    public void navigateToPage() {
        page.navigate("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @Step("Perform drag and drop from A to B")
    public void dragAtoB() {
        columnA.dragTo(columnB);
    }

    public String getColumnAHeader() {
        return headerA.innerText().trim();
    }

    public String getColumnBHeader() {
        return headerB.innerText().trim();
    }

    @Step("Get class attribute of column A")
    public String getColumnAClass() {
        return columnA.getAttribute("class");
    }

    @Step("Get style attribute of column A")
    public String getColumnAStyle() {
        return columnA.getAttribute("style");
    }

    @Step("Verify drag and drop successfully swapped columns")
    public void verifySwapped() {
        String headerA = getColumnAHeader();
        String headerB = getColumnBHeader();

        Assert.assertEquals(headerA, "B", "Column A should now contain 'B'");
        Assert.assertEquals(headerB, "A", "Column B should now contain 'A'");

        // Verify class/style change
        String classA = getColumnAClass();
        String styleA = getColumnAStyle();

        Assert.assertTrue(classA.contains("column"), "Class attribute should contain 'column'");
        Assert.assertTrue(styleA.contains("opacity"), "Style attribute should contain 'opacity'");
    }
}
