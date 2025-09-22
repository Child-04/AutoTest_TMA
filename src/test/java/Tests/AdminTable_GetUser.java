package Tests;

import Base.BaseTest;
import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

public class AdminTable_GetUser extends BaseTest {
    @Test
    public void AdminTable_GetUser()
    {
        //navigage admin page
        page.locator("//span[text()='Admin']").click();
        page.waitForURL("**/admin/viewSystemUsers");
        page.waitForSelector("//div[@class='oxd-table']");
        page.waitForTimeout(5000);

        //Get row list, expect header row
        Locator rows = page.locator("//div[@class='oxd-table-body']/div[@class='oxd-table-card']");
        int rowCount = rows.count();
        System.out.println("Tổng số hàng: " + rowCount);

        //interate through each row and get data on each column

        for (int i = 0; i < rowCount; i++) {
            Locator row = rows.nth(i);
            String username = row.locator("div[role='cell']:nth-child(2)").textContent().trim();
            String userRole = row.locator("div[role='cell']:nth-child(3)").textContent().trim();
            String empName  = row.locator("div[role='cell']:nth-child(4)").textContent().trim();
            String status   = row.locator("div[role='cell']:nth-child(5)").textContent().trim();

            System.out.println("Row " + (i + 1) + " → " +
                    "Username: " + username +
                    ", Role: " + userRole +
                    ", Employee: " + empName +
                    ", Status: " + status);
        }



    }
}
