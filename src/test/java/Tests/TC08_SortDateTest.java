package Tests;

import Base.BaseTest;
import Pages.P08_External_SortDatePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class TC08_SortDateTest extends BaseTest {

    private P08_External_SortDatePage sortPage;

    @BeforeMethod
    public void openPage() {
        page.navigate("https://utkarsh-react-table-demo.netlify.app/sort");
        sortPage = new P08_External_SortDatePage(page);
    }

    @Test(description = "Verify Date of Birth column sorting (ascending, descending, reset)")
    public void verifySortDate() {

        // STEP 1: Ascending
        sortPage.clickSortAscending();
        Assert.assertTrue(sortPage.isToggleVisible(), "Toggle should appear after first click");

        List<LocalDate> ascDates = sortPage.getAllDates();
        for (int i = 0; i < ascDates.size() - 1; i++) {
            Assert.assertFalse(ascDates.get(i).isAfter(ascDates.get(i + 1)),
                    "Dates not in ascending order at row " + (i + 1)); //If == true =>> the upper day is bigger than the lower day
        }

        // STEP 2: Descending
        sortPage.clickSortDescending();
        List<LocalDate> descDates = sortPage.getAllDates();
        for (int i = 0; i < descDates.size() - 1; i++) {
            Assert.assertFalse(descDates.get(i).isBefore(descDates.get(i + 1)),
                    "Dates not in descending order at row " + (i + 1));
        }

        // STEP 3: Reset
        sortPage.clickResetSort();
        Assert.assertFalse(sortPage.isToggleVisible(), "Toggle should disappear after reset");
        Assert.assertEquals(sortPage.getFirstRowId(), "1", "After reset, first ID should be 1");
    }
}
