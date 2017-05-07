package excelReader;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	static XSSFCell cell;
	
	public static Object[][] getExcelData(String excelSheet , String sheetName){
		Object[][] arr;
		//Load excel to read 
		String basePath = "D:\\Java_WorkSpace\\practise\\src\\main\\java\\data\\";
		String fullPath = basePath+excelSheet;
		try {
			FileInputStream fi = new FileInputStream(fullPath);
			workBook = new XSSFWorkbook(fi);
			sheet = workBook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			arr = new Object[rowCount][colCount];
			for (int i=0;i<rowCount;i++) {
				for(int j=0;j<colCount;j++) {
					arr[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			return arr;
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		
		return null;	
	}
}
