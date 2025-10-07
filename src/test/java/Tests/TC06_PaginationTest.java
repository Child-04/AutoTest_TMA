package Tests;

import Base.BaseTest;
import Pages.P06_External_PaginationPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TC06_PaginationTest extends BaseTest {
    private P06_External_PaginationPage paginationPage;

    @BeforeMethod
    public void setup() {
        super.setup();
        page.navigate("https://utkarsh-react-table-demo.netlify.app/pagination");
        paginationPage = new P06_External_PaginationPage(page);
    }

    @Test(description = "Display 10 rows/page")
    public void testShow10() {
        runPaginationTest("10");
    }

    @Test(description = "Display 25 rows/page")
    public void testShow25() {
        runPaginationTest("25");
    }

    @Test(description = "Display 50 rows/page")
    public void testShow50() {
        runPaginationTest("50");
    }

    @Step("Execute pagination test with page size: {size}")
    public void runPaginationTest(String size) {
        paginationPage.selectPageSize(size);
        int totalPages = paginationPage.getTotalPages();

        Allure.step("Verify navigation buttons are disabled on first page");
        paginationPage.goToPage(1);
        assert paginationPage.isFirstDisabled() : "First button should be disabled on page 1";
        assert paginationPage.isPreviousDisabled() : "Previous button should be disabled on page 1";

        Allure.step("Verify navigation buttons are disabled on last page");
        paginationPage.goToPage(totalPages);
        assert paginationPage.isNextDisabled() : "Next button should be disabled on last page";
        assert paginationPage.isLastDisabled() : "Last button should be disabled on last page";

        //If enter number over the maximum pages then the page display is the last page
        Allure.step("Verify inputting page number beyond range stops at last page");
        paginationPage.goToPage(totalPages + 50);
        int currentPage = paginationPage.getCurrentPageNumber();
        assert currentPage == totalPages : "Page should stop at last page when input exceeds range, but is at: " + currentPage;

        Allure.step("Verify page transition and data change");
        paginationPage.goToPage(1);
        List<String> page1IDs = paginationPage.getVisibleRowIDs();

        if (totalPages > 1) {
            paginationPage.goToPage(2);
            List<String> page2IDs = paginationPage.getVisibleRowIDs();

            assert !page1IDs.equals(page2IDs) : "Data did not change between page 1 and 2";
            for (String id : page2IDs) {
                assert !page1IDs.contains(id) : "Duplicate ID found between page 1 and 2: " + id;
            }
        }

        Allure.step("Verify number of rows does not exceed selected page size");
        int actualRows = paginationPage.getRowCount();
        assert actualRows <= Integer.parseInt(size) : "Displayed " + actualRows + " rows, exceeding page size " + size;
    }

}
