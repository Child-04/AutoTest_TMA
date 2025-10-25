package Utils.data;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvReader {
    public static List<String[]> read(Path filePath, boolean skipHeader) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            boolean header = skipHeader;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                if (line.trim().isEmpty()) continue;
                data.add(line.split(",", -1));
            }
        }
        return data;
    }
}
