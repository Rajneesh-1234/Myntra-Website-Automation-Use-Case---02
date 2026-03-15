package utils;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public ExcelUtility(String path) {

        try {

            FileInputStream fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRowCount(String sheetName) {

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            System.out.println("Sheet not found: " + sheetName);
            return 0;
        }

        return sheet.getLastRowNum();
    }

    public String getCellData(String sheetName, int row, int col) {

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            return "";
        }

        if (sheet.getRow(row) == null) {
            return "";
        }

        if (sheet.getRow(row).getCell(col) == null) {
            return "";
        }

        return sheet.getRow(row).getCell(col).toString();
    }
}