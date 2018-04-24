package com.jaga.hybrid.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.lang.String;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jaga.hybrid.helperfunctions.LoggerHelper;

/**
 * 
 * @author Jagatheshwaran
 * @since 23/3/2018
 * @Modified 24/4/2018
 *
 */
public class ExcelReader {

	public static final Logger logger = LoggerHelper.getLogger(ExcelReader.class);

	public static FileInputStream file;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String dataset[][] = null;
	public int rowCount = 0;
	public int columnCount = 0;

	public String[][] getExcelData(String excelLocation, String sheetName) {
		try {

			file = new FileInputStream(new File(excelLocation));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			rowCount = sheet.getLastRowNum() + 1;
			columnCount = sheet.getRow(0).getLastCellNum();
			dataset = new String[rowCount - 1][columnCount];
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			int t = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (i++ != 0) {
					int k = t;
					t++;
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						switch (cell.getCellType()) {

						case Cell.CELL_TYPE_NUMERIC:
							dataset[k][j++] = cell.getStringCellValue();
							logger.info("The Data from Excel Sheet is : " + cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							dataset[k][j++] = cell.getStringCellValue();
							logger.info("The Data from Excel Sheet is : " + cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							dataset[k][j++] = cell.getStringCellValue();
							logger.info("The Data from Excel Sheet is : " + cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							dataset[k][j++] = cell.getStringCellValue();
							logger.info("The Data from Excel Sheet is : " + cell.getStringCellValue());
							break;
						}
					}
					logger.info(" ");
				}
			}
			file.close();
			return dataset;

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

}
