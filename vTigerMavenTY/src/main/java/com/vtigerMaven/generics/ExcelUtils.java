package com.vtigerMaven.generics;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to fetch test data from excel file.
 * @author Baishali
 *
 */
public class ExcelUtils {

	/**
	 * The below method fetches the particular test data from the excel sheet.
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public String fetchDataFromExcel(String sheetName, int rowNum, int colNum) throws Exception, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestCaseData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		String value = cell.getStringCellValue();
		
		return value;
	}
	
}
