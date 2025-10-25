package Utils.utils;

import Utils.data.TestData;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class DownloadUtils {

    /**
     * Initialize or clean up the download folder.
     */
    public static void setupDownloadDirectory() throws IOException {
        Path downloadDir = TestData.DOWNLOAD_PATH; // Sử dụng hằng số từ TestData
        if (Files.exists(downloadDir)) {
            // Xóa tất cả nội dung cũ để đảm bảo không bị lẫn file
            Files.walk(downloadDir)
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .forEach(File::delete);
        } else {
            // Create directory if it does not exist
            Files.createDirectories(downloadDir);
        }
        System.out.println("Download directory prepared: " + downloadDir.toAbsolutePath());
    }

    /**
     * Wait for the file to complete downloading
     */
    public static Path waitForFileToExist(String fileName, int timeoutSeconds) {
        Path finalPath = TestData.DOWNLOAD_PATH.resolve(fileName);
        long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeoutSeconds);

        while (System.currentTimeMillis() < endTime) {
            if (Files.exists(finalPath)) {
                System.out.println("Download file found: " + finalPath.getFileName());
                return finalPath;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        throw new RuntimeException("File was not found or download did not complete within " + timeoutSeconds + " seconds: " + fileName);
    }
}