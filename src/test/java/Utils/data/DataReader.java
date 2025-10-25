package Utils.data;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DataReader {
    private static final Path PATH = Paths.get("src/test/resources/files");

    public static List<String[]> readCsv(String fileName) {
        try {
            return CsvReader.read(PATH.resolve(fileName), true);
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + fileName, e);
        }
    }

    public static List<String[]> readExcel(String fileName, String sheetName) {
        try {
            return ExcelReader.read(PATH.resolve(fileName), sheetName, true);
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + fileName, e);
        }
    }
}
