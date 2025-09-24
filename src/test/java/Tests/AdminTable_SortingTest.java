package Tests;

import Base.LoggedInBaseTest;
import Untils.TableSortHelper;
import org.testng.annotations.Test;

public class AdminTable_SortingTest extends LoggedInBaseTest {
    @Test
    void testUserTableSortByUsernameAsc() {
        TableSortHelper.verifySorting(page, "#userTable", 2, true); // column 2 = Username
    }

    @Test
    void testEmployeeTableSortByNameDesc() {
        TableSortHelper.verifySorting(page, "#employeeTable", 3, false); // column 3 = Name
    }
}
