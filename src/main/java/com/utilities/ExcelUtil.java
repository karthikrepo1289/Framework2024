package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {
	static FileInputStream fis;
	static Workbook wbk;
	static Sheet sht;
	static Object[][] obj;

//	public static void main(String[] args) {
//		ExcelUtil e = new ExcelUtil();
//		e.getDataFromExcel("RegisterUser");
//	}

	public static Object[][] getDataFromExcel(String sheetName) {
		 try {
			fis = new FileInputStream("./src/test/resource/TestData/OpenCartTestData.xls");
			wbk = new HSSFWorkbook(fis);
			sht	= wbk.getSheet(sheetName);
			obj = new Object[sht.getLastRowNum()][sht.getRow(0).getLastCellNum()];
			//System.out.println(sht.getLastRowNum());
			//System.out.println(sht.getRow(0).getLastCellNum());
			for(int i=1; i<=sht.getLastRowNum(); i++) {
				for(int j=0; j<sht.getRow(0).getLastCellNum(); j++) {
					Cell cl = sht.getRow(i).getCell(j);
					cl.setCellType(CellType.STRING);
					System.out.println(cl.getStringCellValue());
				}
				System.out.println(" ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return obj;
	}
}
