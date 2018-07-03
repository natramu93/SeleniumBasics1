package com.yesmsystems.automationframework.keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
static XSSFWorkbook wb;
static XSSFSheet sh;
public static void initialize(String workbook, String worksheet)
{
	try {
		wb = new XSSFWorkbook(new FileInputStream(workbook));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sh = wb.getSheet(worksheet);
}
public static Object[][] datatable(int row, int col)
{
	Object[][] data = new Object[row][col];
	for(int i=0;i<row;i++)
		for(int j=0;j<col;j++)
			data[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
	
	return data;
}
}
