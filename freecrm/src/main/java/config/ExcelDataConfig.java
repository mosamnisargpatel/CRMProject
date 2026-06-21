package config;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelDataConfig {
	Workbook wb;
	Sheet sheet1;
	String data;
	public ExcelDataConfig(String excelpath) throws Exception
	{
		File src = new File(excelpath);
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);	
	}
	
	public String getdata(int row, int column)
	{
		
		sheet1 = wb.getSheetAt(0);
		Row row1 = sheet1.getRow(row);
         Cell col1 = row1.getCell(column);
        
		if (col1 != null) {
		   data  = sheet1.getRow(row).getCell(column).getStringCellValue();
		} else {
		    // Handle the blank/null cell scenario here
		}

		return data;	
	}
	
	
	public int getrownum(int SheetIndex)
	{
		int rowcount = wb.getSheetAt(0).getLastRowNum();
		rowcount = rowcount+1;
		return rowcount;
	}

}
