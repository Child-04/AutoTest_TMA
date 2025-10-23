package Pages.ActionsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

/**
 * Page Object cho trang Drag and Drop của Herokuapp.
 * URL: https://the-internet.herokuapp.com/drag_and_drop
 */
public class P16_Herokuapp_DragAndDropPage {

    private final Page page;
    private final Locator columnA;
    private final Locator columnB;
    private final Locator headerA;
    private final Locator headerB;

    // ========================
    // 🔹 Constructor
    // ========================
    public P16_Herokuapp_DragAndDropPage(Page page) {
        this.page = page;
        this.columnA = page.locator("#column-a");
        this.columnB = page.locator("#column-b");
        this.headerA = page.locator("#column-a header");
        this.headerB = page.locator("#column-b header");
    }

    // ========================
    // 🔹 Navigation
    // ========================
    @Step("Navigate to Drag and Drop page")
    public void navigateToPage() {
        page.navigate("https://the-internet.herokuapp.com/drag_and_drop");
        page.waitForLoadState();
    }

    // ========================
    // 🔹 Actions
    // ========================
    @Step("Perform drag and drop from Column A to Column B")
    public void dragAtoB() {
        columnA.dragTo(columnB);
    }

    // ========================
    // 🔹 Getters
    // ========================
    @Step("Get header text of Column A")
    public String getColumnAHeader() {
        return headerA.textContent().trim();
    }

    @Step("Get header text of Column B")
    public String getColumnBHeader() {
        return headerB.textContent().trim();
    }

    @Step("Get class attribute of Column A")
    public String getColumnAClass() {
        return columnA.getAttribute("class");
    }

    @Step("Get style attribute of Column A")
    public String getColumnAStyle() {
        return columnA.getAttribute("style");
    }

    // ========================
    // 🔹 Helper wait (optional)
    // ========================
    @Step("Wait until columns have been swapped (A -> B, B -> A)")
    public void waitUntilSwapped() {
        page.waitForCondition(() ->
                getColumnAHeader().equals("B") && getColumnBHeader().equals("A")
        );
    }
}
