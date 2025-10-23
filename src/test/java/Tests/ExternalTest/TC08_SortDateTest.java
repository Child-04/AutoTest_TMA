package Tests.ExternalTest;

import Base.BaseTest;
import Pages.ExternalPage.P08_External_SortDatePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class TC08_SortDateTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(TC08_SortDateTest.class);
    private P08_External_SortDatePage sortPage;

    @BeforeMethod
    public void openPage() {
        log.info("=== Setup: Navigate to Sort Date demo page ===");
        page.navigate("https://utkarsh-react-table-demo.netlify.app/sort");
        log.info("Navigated to https://utkarsh-react-table-demo.netlify.app/sort");
        sortPage = new P08_External_SortDatePage(page);
        log.info("Initialized P08_External_SortDatePage object");
    }

    @Test(description = "Verify Date of Birth column sorting (ascending, descending, reset)")
    public void verifySortDate() {
        log.info("=== Test: Verify Date of Birth column sorting (ascending, descending, reset) ===");

        // STEP 1: Ascending
        log.info("[Step 1] Click to sort ascending");
        sortPage.clickSortAscending();
        log.info("Clicked sort ascending button");

        Assert.assertTrue(sortPage.isToggleVisible(), "Toggle should appear after first click");
        log.info("Verified sort toggle is visible after ascending click");

        List<LocalDate> ascDates = sortPage.getAllDates();
        log.info("Captured {} dates in ascending check", ascDates.size());
        for (int i = 0; i < ascDates.size() - 1; i++) {
            Assert.assertFalse(ascDates.get(i).isAfter(ascDates.get(i + 1)),
                    "Dates not in ascending order at row " + (i + 1));
        }
        log.info("Verified all dates are sorted in ascending order");

        // STEP 2: Descending
        log.info("[Step 2] Click to sort descending");
        sortPage.clickSortDescending();
        log.info("Clicked sort descending button");

        List<LocalDate> descDates = sortPage.getAllDates();
        log.info("Captured {} dates in descending check", descDates.size());
        for (int i = 0; i < descDates.size() - 1; i++) {
            Assert.assertFalse(descDates.get(i).isBefore(descDates.get(i + 1)),
                    "Dates not in descending order at row " + (i + 1));
        }
        log.info("Verified all dates are sorted in descending order");

        // STEP 3: Reset
        log.info("[Step 3] Click to reset sort");
        sortPage.clickResetSort();
        log.info("Clicked reset sort button");

        Assert.assertFalse(sortPage.isToggleVisible(), "Toggle should disappear after reset");
        log.info("Verified sort toggle disappeared after reset");

        String firstRowId = sortPage.getFirstRowId();
        log.info("First row ID after reset: {}", firstRowId);
        Assert.assertEquals(firstRowId, "1", "After reset, first ID should be 1");
        log.info("Verified table reset successfully to original order");
    }
}
