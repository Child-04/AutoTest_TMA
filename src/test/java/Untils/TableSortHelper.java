package Untils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TableSortHelper {
    public static void verifySorting(Page page, String tableSelector, int columnIndex, boolean ascending) {
        Locator header = page.locator(tableSelector + " thead tr th:nth-child(" + columnIndex + ")");
        header.click(); // click to sort ascending

        if (!ascending) {
            header.click(); // click to sort descending
        }

        Locator cells = page.locator(tableSelector + " tbody tr td:nth-child(" + columnIndex + ")");
        List<String> actual = cells.allInnerTexts().stream().map(String::trim).collect(Collectors.toList());

        List<String> expected = new ArrayList<>(actual);
        expected.sort(String::compareToIgnoreCase);
        if (!ascending) {
            Collections.reverse(expected);
        }

        if (!actual.equals(expected)) {
            throw new AssertionError("Sorting failed. Expected: " + expected + ", but got: " + actual);
        }
    }
}
