package Tests;

import Base.BaseTest;
import Pages.P16_Herokuapp_DrapandDropPage;
import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Herokuapp Automation")
@Feature("Drag and Drop Test")
public class TC16_Herokuapp_DrapanDropTest extends BaseTest {
    private P16_Herokuapp_DrapandDropPage dragPage;

    @BeforeMethod
    public void openPage() {
        dragPage = new P16_Herokuapp_DrapandDropPage(page);
        dragPage.navigateToPage();
    }

    @Test(description = "Verify drag and drop swaps columns A and B")
    public void testDragAndDrop() {

        takeScreenshot("Before drag and drop swaps columns A and B");
        // Perform drag and drop
        dragPage.dragAtoB();

        // Verify swapped
        dragPage.verifySwapped();
        takeScreenshot("dragAndDrop");
    }
}
