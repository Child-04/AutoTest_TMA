package Utils.data;

import Utils.security.SecurityUtil; // Import class mới
import java.io.*;
import java.nio.file.*;

import static Utils.data.TestData.BASE_PATH;

public class CredentialWriter {
    private static final String HEADER = "username,password";
    // Chỉ tập trung vào việc ghi/thêm dữ liệu
    public static void appendCredentialCsv(String fileName, String username, String password) throws IOException {

        Path filePath = BASE_PATH.resolve(fileName);

        Files.createDirectories(filePath.getParent());

        boolean exists = Files.exists(filePath);
        try (BufferedWriter bw = Files.newBufferedWriter(filePath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            if (!exists) {
                bw.write(HEADER);
                bw.newLine();
            }

            String encodedPassword = SecurityUtil.encode(password);

            bw.write(username + "," + encodedPassword);
            bw.newLine();

            System.out.println("Saved user: " + username + " (encoded password) → " + filePath.toAbsolutePath());
        }
    }

    /**
     * Xóa tất cả dữ liệu trong file CSV, chỉ giữ lại header.
     * Phương thức này được gọi sau khi hoàn tất chu trình test.
     */
    public static void clearCredentialsCsv(String fileName) throws IOException {
        Path filePath = BASE_PATH.resolve(fileName);

        if (!Files.exists(filePath)) {
            System.out.println("File not found, nothing to clear: " + filePath.toAbsolutePath());
            return;
        }

        // Ghi đè file (StandardOpenOption.TRUNCATE_EXISTING) bằng duy nhất header.
        // TRUNCATE_EXISTING đảm bảo file bị cắt ngắn về 0 byte trước khi ghi.
        try (BufferedWriter bw = Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING)) {
            bw.write(HEADER);
            bw.newLine();
            System.out.println("Cleared all data from file, header preserved: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            throw new IOException("Failed to clear data in file: " + fileName, e);
        }
    }
}