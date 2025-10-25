package Utils.data;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvReader {
    /**
     * Đọc nội dung thô từ file CSV bằng cách split theo dấu phẩy (delimiter).
     * KHÔNG XỬ LÝ QUOTES (ví dụ: "a,b,c").
     * @param filePath Đường dẫn đến file.
     * @param skipHeader Bỏ qua dòng header đầu tiên nếu true.
     * @return List<String[]> - Mỗi String[] là một dòng dữ liệu thô.
     */
    public static List<String[]> read(Path filePath, boolean skipHeader) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            boolean header = skipHeader;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                if (line.trim().isEmpty()) continue;

                // Trả về mảng chuỗi thô (bao gồm cả dấu ngoặc kép và bị tách sai nếu có)
                data.add(line.split(",", -1));
            }
        }
        return data;
    }
}