package com.jaga.hybrid.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.lang.String;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String[][] getExcelData(String excelLocation, String sheetName) {
		try {
			String dataset[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum() + 1;
			int columnCount = sheet.getRow(0).getLastCellNum();
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
							System.out.println(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							dataset[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							dataset[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							dataset[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						}
					}
					System.out.println(" ");
				}
			}
			file.close();
			return dataset;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
