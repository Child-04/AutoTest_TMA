package Utils.data;

import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ExcelReader {
    public static List<String[]> read(Path filePath, String sheetName, boolean skipHeader) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (InputStream fis = Files.newInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            boolean header = skipHeader;

            for (Row row : sheet) {
                if (header) { header = false; continue; }
                int cells = row.getPhysicalNumberOfCells();
                String[] values = new String[cells];
                for (int i = 0; i < cells; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    values[i] = cell.toString().trim();
                }
                data.add(values);
            }
        }
        return data;
    }
}
