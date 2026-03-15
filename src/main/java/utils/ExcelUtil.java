package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();   // safer
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;   // skip blank row
            }

            for (int j = 0; j < colCount; j++) {

                Cell cell = row.getCell(j);

                if (cell == null) {
                    data[i - 1][j] = "";
                } else {
                    data[i - 1][j] = cell.toString();
                }
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}