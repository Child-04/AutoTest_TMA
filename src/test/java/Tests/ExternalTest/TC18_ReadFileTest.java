package Tests.ExternalTest;

import Base.BaseTest; // Giả định BaseTest đã khởi tạo Playwright Page 'page'
import Pages.ExternalPage.P21_TableDataPage;
import Utils.data.CsvReader;
import Utils.data.TestData;
import Utils.utils.DownloadUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Lưu ý: Đổi TC13_Jquery_DatePickerTest.class thành TC18_ReadFileTest.class nếu nó là lớp hiện tại
public class TC18_ReadFileTest extends BaseTest {
    // Khai báo Logger (Đảm bảo Class name chính xác)
    private static final Logger log = LoggerFactory.getLogger(TC18_ReadFileTest.class);
    private P21_TableDataPage dataPage;

    @BeforeClass
    public void setupTestEnvironment() throws IOException {
        log.info("===== Starting setup for Read file from Table Data =====");

        // Khởi tạo Page Object (Dùng page từ BaseTest)
        dataPage = new P21_TableDataPage(page);

        // Dọn dẹp thư mục tải xuống
        DownloadUtils.setupDownloadDirectory();

        dataPage.open();
        log.info("Navigated to Table Data Page successfully.");
    }

    /**
     * Logic làm sạch dữ liệu CSV thô (7 cột sai) về 5 cột chuẩn.
     * Logic này KHÔNG tái sử dụng, nó chỉ dùng cho cấu trúc file CSV này.
     */
    private List<String[]> cleanCsvData(List<String[]> rawData) {
        List<String[]> cleanedData = new ArrayList<>();

        for (String[] parts : rawData) {
            if (parts.length == 7) { // Trường hợp bị tách sai 7 cột

                // Ghép 3 phần tử giá trị sai parts[3], parts[4], parts[5]
                String pricePart = parts[3] + parts[4] + parts[5];

                // Loại bỏ dấu ngoặc kép, khoảng trắng và dấu phẩy (chuẩn hóa giá trị số)
                pricePart = pricePart.replace("\"", "").trim().replaceAll("\\s+", "");
                pricePart = pricePart.replace(",", "");

                String[] cleanedParts = new String[5];
                cleanedParts[0] = parts[0].trim();
                cleanedParts[1] = parts[1].trim();
                cleanedParts[2] = parts[2].trim();
                cleanedParts[3] = pricePart;
                cleanedParts[4] = parts[6].trim();

                cleanedData.add(cleanedParts);

            } else if (parts.length == 5) {
                // Trường hợp file CSV khác (không có quoted field) hoặc đã đúng 5 cột
                String[] cleanedParts = new String[5];
                for(int i = 0; i < 5; i++) {
                    cleanedParts[i] = parts[i].trim();
                    if (i == 3) cleanedParts[i] = cleanedParts[i].replace(",", ""); // Chuẩn hóa giá
                }
                cleanedData.add(cleanedParts);

            } else {
                // Log thông tin để debug nếu số cột bất thường
                System.err.println("Warning: CSV Row has unusual column count: " + parts.length + ". Skipping row.");
            }
        }
        return cleanedData;
    }

    @Test(description = "Verify Web Table data after downloading CSV. Compare UI and CSV line by line.")
    public void verifyDownloadedCsvDataMatchesUITable() {
        log.info("--- Starting TC18: Verify Downloaded CSV Data ---");

        // 2. Get data from UI (Expected)
        List<String[]> uiData = dataPage.getTableDataFromUI();
        log.info("Extracted " + uiData.size() + " data stream from UI.");

        // 3. Download and Verify Path
        String downloadedFileName = dataPage.downloadCsvFile(TestData.DOWNLOAD_PATH);
        log.info("Download file: " + downloadedFileName);
        Path downloadedFilePath = DownloadUtils.waitForFileToExist(downloadedFileName, 5);
        log.info("Verified file at: " + downloadedFilePath.toAbsolutePath());

        // 5. Read raw data from CSV
        List<String[]> rawCsvData;
        try {
            rawCsvData = CsvReader.read(downloadedFilePath, true);
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file.", e);
        }

        // 5.5. CLEAN AND NORMALIZE RAW DATA
        List<String[]> csvData = cleanCsvData(rawCsvData);
        log.info("Read and cleaned " + csvData.size() + " data stream from CSV.");

        // 6. Compare data
        System.out.println("... Comparing UI and CSV data ...");
        Assert.assertEquals(csvData.size(), uiData.size(),
                "Number of data rows does not match between CSV and UI!");

        for (int i = 0; i < uiData.size(); i++) {
            String[] expected = uiData.get(i);
            String[] actual = csvData.get(i);

            // 6.1. Check column count (This should pass now)
            if (expected.length != actual.length) {
                System.err.println("--- CSV STRUCTURE ERROR ---");
                System.err.println("UI Row " + (i + 1) + ": " + java.util.Arrays.toString(expected));
                System.err.println("CSV Row " + (i + 1) + ": " + java.util.Arrays.toString(actual));
                Assert.fail("Data in row " + (i + 1) + " has a column mismatch. " +
                        "UI has " + expected.length + " columns, CSV has " + actual.length + " columns.");
            }

            // 6.2. Detailed column-by-column comparison
            for (int j = 0; j < expected.length; j++) {
                String expectedValue = expected[j].trim();
                String actualValue = actual[j].trim();

                // Normalize price column (index 3)
                if (j == 3) {
                    expectedValue = expectedValue.replace(",", "");
                    actualValue = actualValue.replace(",", "");
                }

                Assert.assertEquals(actualValue, expectedValue,
                        "Data in row " + (i + 1) + ", column " + (j + 1) + " (Value: " + expected[j] + ") DOES NOT MATCH. \n" +
                                "  > UI (Expected): '" + expectedValue + "'\n" +
                                "  > CSV (Actual):  '" + actualValue + "'");
            }
            System.out.println("Row " + (i + 1) + " matches successfully.");
        }

        System.out.println("TC18: Test COMPLETE. CSV data fully matches Web Table UI.");
    }
}