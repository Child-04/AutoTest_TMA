package Tests.ExternalTest;

import Base.BaseTest;
import Pages.ExternalPage.P06_External_PaginationPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TC06_PaginationTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(TC06_PaginationTest.class);
    private P06_External_PaginationPage paginationPage;

    @BeforeMethod
    public void setup() {
        log.info("=== Setup: Navigate to Pagination demo page ===");
        super.setup();
        page.navigate("https://utkarsh-react-table-demo.netlify.app/pagination");
        log.info("Navigated to https://utkarsh-react-table-demo.netlify.app/pagination");
        paginationPage = new P06_External_PaginationPage(page);
        log.info("Initialized P06_External_PaginationPage object");
    }

    @Test(description = "Display 10 rows/page")
    public void testShow10() {
        log.info("=== Test: Display 10 rows per page ===");
        runPaginationTest("10");
    }

    @Test(description = "Display 25 rows/page")
    public void testShow25() {
        log.info("=== Test: Display 25 rows per page ===");
        runPaginationTest("25");
    }

    @Test(description = "Display 50 rows/page")
    public void testShow50() {
        log.info("=== Test: Display 50 rows per page ===");
        runPaginationTest("50");
    }

    @Step("Execute pagination test with page size: {size}")
    public void runPaginationTest(String size) {
        log.info("=== Running pagination test with page size: {} ===", size);
        paginationPage.selectPageSize(size);
        log.info("Selected page size: {}", size);
        int totalPages = paginationPage.getTotalPages();
        log.info("Total pages detected: {}", totalPages);

        Allure.step("Verify navigation buttons are disabled on first page");
        log.info("Go to first page and verify navigation buttons");
        paginationPage.goToPage(1);
        assert paginationPage.isFirstDisabled() : "First button should be disabled on page 1";
        assert paginationPage.isPreviousDisabled() : "Previous button should be disabled on page 1";
        log.info("Verified first and previous buttons are disabled on first page");

        Allure.step("Verify navigation buttons are disabled on last page");
        log.info("Go to last page and verify navigation buttons");
        paginationPage.goToPage(totalPages);
        assert paginationPage.isNextDisabled() : "Next button should be disabled on last page";
        assert paginationPage.isLastDisabled() : "Last button should be disabled on last page";
        log.info("Verified next and last buttons are disabled on last page");

        Allure.step("Verify inputting page number beyond range stops at last page");
        log.info("Inputting page number beyond total pages to verify boundary behavior");
        paginationPage.goToPage(totalPages + 50);
        int currentPage = paginationPage.getCurrentPageNumber();
        log.info("Current page after overflow input: {}", currentPage);
        assert currentPage == totalPages : "Page should stop at last page when input exceeds range, but is at: " + currentPage;
        log.info("Verified input beyond range stays at last page");

        Allure.step("Verify page transition and data change");
        log.info("Verify that page transition changes data");
        paginationPage.goToPage(1);
        List<String> page1IDs = paginationPage.getVisibleRowIDs();
        log.info("Captured IDs from page 1: {}", page1IDs);

        if (totalPages > 1) {
            paginationPage.goToPage(2);
            List<String> page2IDs = paginationPage.getVisibleRowIDs();
            log.info("Captured IDs from page 2: {}", page2IDs);

            assert !page1IDs.equals(page2IDs) : "Data did not change between page 1 and 2";
            for (String id : page2IDs) {
                assert !page1IDs.contains(id) : "Duplicate ID found between page 1 and 2: " + id;
            }
            log.info("Verified data between page 1 and page 2 are different with no duplicates");
        } else {
            log.info("Only one page detected — skipping multi-page data comparison");
        }

        Allure.step("Verify number of rows does not exceed selected page size");
        int actualRows = paginationPage.getRowCount();
        log.info("Actual rows displayed: {}", actualRows);
        assert actualRows <= Integer.parseInt(size) : "Displayed " + actualRows + " rows, exceeding page size " + size;
        log.info("Verified row count does not exceed selected page size");
    }
}
