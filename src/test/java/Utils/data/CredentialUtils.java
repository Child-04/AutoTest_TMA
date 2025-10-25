package Utils.data;

import Utils.security.SecurityUtil; // Import class mới
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class    CredentialUtils {


    // Rút gọn bằng CsvReader + GỌI SecurityUtil để giải mã
    public static List<String[]> readCredentialsCsv(String fileName) throws IOException {

        // 1.Xử lý logic tạo Path bên trong, chỉ nhận String fileName.
        Path filePath = TestData.BASE_PATH.resolve(fileName);

        List<String[]> res = new ArrayList<>();
        if (!Files.exists(filePath)) return res;

        // 2. Dùng CsvReader để đọc thô
        // CsvReader đã xử lý I/O và bỏ qua header
        List<String[]> rawData = CsvReader.read(filePath, true);

        // 3. Lặp qua và GIẢI MÃ
        for (String[] parts : rawData) {
            // Đảm bảo đủ 2 phần tử (username, password)
            if (parts.length >= 2) {
                String username = parts[0].trim();
                // GỌI Class SecurityUtil để giải mã
                String decodedPassword = SecurityUtil.decode(parts[1].trim());
                res.add(new String[]{username, decodedPassword});
            }
        }
        return res;
    }
}