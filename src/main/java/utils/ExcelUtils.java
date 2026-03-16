package utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static int rowCount = 0;
	public static void writeTestResult(String testName, String status) {
		try {
			if (workbook == null) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("Test Results");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Test Case");
				header.createCell(1).setCellValue("Result");
				rowCount = 1;
			}

			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(testName);
			row.createCell(1).setCellValue(status);
			FileOutputStream fileOut = new FileOutputStream("TestExecutionResults.xlsx");
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}