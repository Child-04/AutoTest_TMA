package Utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Base64;

public class CredentialUtils {
    private static final String HEADER = "username,password";

    // ===== Helper methods =====
    private static String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    private static String decodePassword(String encoded) {
        try {
            return new String(Base64.getDecoder().decode(encoded));
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to decode password: " + e.getMessage());
            return encoded;
        }
    }

    // Append one credential to CSV (create file with header if not exists)
    public static void appendCredentialCsv(Path filePath, String username, String password) throws IOException {
        Files.createDirectories(filePath.getParent());

        boolean exists = Files.exists(filePath);
        try (BufferedWriter bw = Files.newBufferedWriter(filePath,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            if (!exists) {
                bw.write(HEADER);
                bw.newLine();
            }

            // mã hóa password
            String encodedPassword = encodePassword(password);

            bw.write(username + "," + encodedPassword);
            bw.newLine();

            System.out.println("Saved user: " + username + " (encoded password) → " + filePath.toAbsolutePath());
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
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String decodedPassword = decodePassword(parts[1].trim());
                    res.add(new String[]{username, decodedPassword});
                }
            }
        }
        return res;
    }
}
