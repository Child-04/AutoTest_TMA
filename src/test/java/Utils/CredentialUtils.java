package Utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CredentialUtils {
    private static final String HEADER = "username,password";

    // Append one credential to CSV (create file with header if not exists)
    public static void appendCredentialCsv(Path filePath, String username, String password) throws IOException {
        // tạo thư mục cha nếu chưa có
        Files.createDirectories(filePath.getParent());

        boolean exists = Files.exists(filePath);
        try (BufferedWriter bw = Files.newBufferedWriter(filePath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            if (!exists) {
                bw.write("username,password");
                bw.newLine();
            }

            bw.write(username + "," + password);
            bw.newLine();

            // log ra console để dễ debug
            System.out.println("Saved user: " + username + " to " + filePath.toAbsolutePath());
        }
    }


    // Read CSV and return list of String[]{username,password}
    public static List<String[]> readCredentialsCsv(Path filePath) throws IOException {
        List<String[]> res = new ArrayList<>();
        if (!Files.exists(filePath)) return res;
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; } // skip header
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", 2);
                if (parts.length == 2) res.add(new String[]{parts[0].trim(), parts[1].trim()});
            }
        }
        return res;
    }
}
