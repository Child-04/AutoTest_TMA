package Tests;

import Base.BaseTest;
import Pages.ReactTableDemoPage.P06_External_PaginationPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TC06_PaginationTest extends BaseTest {
    private P06_External_PaginationPage paginationPage;

    @BeforeMethod
    public void setup() {
        super.setup();
        paginationPage = new P06_External_PaginationPage(page);
        paginationPage.navigateToPage();
    }

        @Test(description = "Display 10 rows/page")
        public void testShow10() {
            String size = "10";
            paginationPage.selectPageSize(size);
            takeScreenshot("Select 10 rows/page");
            paginationPage.verifyFirstPageButtons();
            takeScreenshot("First button and Previous button disabled on page 1");
            paginationPage.verifyLastPageButtons();
            takeScreenshot("Next button and Last button disabled on page 1");
            paginationPage.verifyOutOfRangePageNavigation();
            takeScreenshot("Page stop at last page");
            paginationPage.verifyPageTransitionAndDataChange();
            takeScreenshot("Data did not change between page 1 and 2");
            paginationPage.verifyRowCountDoesNotExceed(size);
            takeScreenshot("Row count does not exceed selected page size");
        }

    @Test(description = "Display 25 rows/page")
    public void testShow25() {
        String size = "25";
        paginationPage.selectPageSize(size);
        takeScreenshot("Select 25 rows/page");
        paginationPage.verifyFirstPageButtons();
        takeScreenshot("First button and Previous button disabled on page 1");
        paginationPage.verifyLastPageButtons();
        takeScreenshot("Next button and Last button disabled on page 1");
        paginationPage.verifyOutOfRangePageNavigation();
        takeScreenshot("Page stop at last page");
        paginationPage.verifyPageTransitionAndDataChange();
        takeScreenshot("Data did not change between page 1 and 2");
        paginationPage.verifyRowCountDoesNotExceed(size);
        takeScreenshot("Row count does not exceed selected page size");
    }

    @Test(description = "Display 50 rows/page")
    public void testShow50() {
        String size = "50";
        paginationPage.selectPageSize(size);
        takeScreenshot("Select 50 rows/page");
        paginationPage.verifyFirstPageButtons();
        takeScreenshot("First button and Previous button disabled on page 1");
        paginationPage.verifyLastPageButtons();
        takeScreenshot("Next button and Last button disabled on page 1");
        paginationPage.verifyOutOfRangePageNavigation();
        takeScreenshot("Page stop at last page");
        paginationPage.verifyPageTransitionAndDataChange();
        takeScreenshot("Data did not change between page 1 and 2");
        paginationPage.verifyRowCountDoesNotExceed(size);
        takeScreenshot("Row count does not exceed selected page size");
    }


}