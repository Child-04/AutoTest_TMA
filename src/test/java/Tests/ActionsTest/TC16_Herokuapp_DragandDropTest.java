package Tests.ActionsTest;

import Base.BaseTest;
import Pages.ActionsPage.P16_Herokuapp_DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC16_Herokuapp_DragandDropTest extends BaseTest {
    private P16_Herokuapp_DragAndDropPage dragPage;

    @BeforeMethod
    public void openPage() {
        System.out.println("Opening Drag and Drop page...");
        dragPage = new P16_Herokuapp_DragAndDropPage(page);
        dragPage.navigateToPage();
        System.out.println("Page loaded successfully.");
    }

    @Test(description = "Verify drag and drop swaps columns A and B successfully")
    public void testDragAndDrop() {
        System.out.println("===== Starting test: testDragAndDrop =====");

        takeScreenshot("Before_DragAndDrop");

        // Step 1: Perform drag and drop
        System.out.println("Performing drag and drop from Column A to Column B...");
        dragPage.dragAtoB();
        System.out.println("Drag and drop action completed.");

        // Step 2: Get values after swapping
        String headerA = dragPage.getColumnAHeader();
        String headerB = dragPage.getColumnBHeader();
        String classA = dragPage.getColumnAClass();
        String styleA = dragPage.getColumnAStyle();

        System.out.println("Header A after drag: " + headerA);
        System.out.println("Header B after drag: " + headerB);
        System.out.println("Class attribute of Column A: " + classA);
        System.out.println("Style attribute of Column A: " + styleA);

        // Step 3: Assertions
        Assert.assertEquals(headerA, "B", "Column A should now contain 'B'.");
        Assert.assertEquals(headerB, "A", "Column B should now contain 'A'.");
        Assert.assertTrue(classA.contains("column"), "Class attribute should contain 'column'.");
        Assert.assertTrue(styleA == null || styleA.contains("opacity"), "Style attribute should contain 'opacity' or be null.");

        takeScreenshot("After_DragAndDrop");

        System.out.println("All verifications passed. Drag and drop was successful.");
        System.out.println("===== Finished test: testDragAndDrop =====");
    }
}
