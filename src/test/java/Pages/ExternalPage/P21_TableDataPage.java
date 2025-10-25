package Pages.ExternalPage;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * P21_TableDataPage: Page Object cho trang chứa Bảng Dữ Liệu Bán Hàng (Simple-Data-Table).
 */
public class P21_TableDataPage {

    private final Page page;
    public final String PAGE_URL = "https://child-04.github.io/Simple-Data-Table/";

    // Locators Playwright
    private final Locator TABLE_ROWS;
    private final Locator DOWNLOAD_BUTTON;

    public P21_TableDataPage(Page page) {
        this.page = page;
        // Khởi tạo Locators
        this.TABLE_ROWS = page.locator("//table/tbody/tr");
        this.DOWNLOAD_BUTTON = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Tải Xuống CSV"));
    }

    @Step("Open Simple Data Page")
    public void open() {
        page.navigate(PAGE_URL);
        page.waitForLoadState();
    }

    /**
     * Trích xuất tất cả dữ liệu dòng từ Web Table (không bao gồm Header) bằng Playwright.
     * @return List<String[]> - Mỗi String[] là một dòng dữ liệu.
     */
    public List<String[]> getTableDataFromUI() {
        List<String[]> uiData = new ArrayList<>();
        int rowCount = TABLE_ROWS.count();

        for (int i = 0; i < rowCount; i++) {
            // Lấy Locator cho tất cả các ô <td> trong dòng hiện tại
            Locator cells = TABLE_ROWS.nth(i).locator("//td");
            List<String> rowData = cells.allTextContents().stream()
                    .map(String::trim)
                    .toList(); // Java 16+

            uiData.add(rowData.toArray(new String[0]));
        }
        return uiData;
    }

    /**
     * Click vào nút Tải Xuống CSV và xử lý việc tải xuống bằng Playwright.
     * @param targetDirectory Đường dẫn thư mục đích để lưu file.
     * @return Tên file đã được Playwright lưu vào thư mục đích.
     */

    @Step("Click button download CSV File")
    public String downloadCsvFile(Path targetDirectory) {
        // Bắt đầu lắng nghe sự kiện tải xuống
        // Hành động kích hoạt tải xuống
        Download download = page.waitForDownload(DOWNLOAD_BUTTON::click);

        // Playwright tự động chờ tải xuống hoàn tất.
        // Di chuyển file đã tải từ thư mục tạm thời của Playwright sang thư mục đích
        Path downloadedPath = download.path().getFileName();

        try {
            download.saveAs(targetDirectory.resolve(download.suggestedFilename()));
            System.out.println("File saved by Playwright to: " + targetDirectory.resolve(download.suggestedFilename()).toAbsolutePath());
            return download.suggestedFilename();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save downloaded file.", e);
        }
    }
}