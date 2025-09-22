package generic;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public double getDataFromExcel(String sheetName, int rownum,int celNum) throws Exception {
		FileInputStream fis=new FileInputStream("./testData/FacebookTesData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String num = RandomStringUtils.randomNumeric(3);
		double data=wb.getSheet(sheetName).getRow(rownum).getCell(celNum).getNumericCellValue();
		wb.close();
		return data;
	}
	
	public String getDataFromExcel1(String sheetName, int rownum,int celNum) throws Exception {
		FileInputStream fis=new FileInputStream("./testData/FacebookTesData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String num = RandomStringUtils.randomNumeric(3);
		String data=wb.getSheet(sheetName).getRow(rownum).getCell(celNum).toString();
		wb.close();
		return data;
	}
	
	public List<List<String>> getDataFromExcel(String sheetName) throws Exception {
		 List<List<String>> data = new ArrayList<>();
		 
		FileInputStream fis=new FileInputStream("./testData/FacebookTesData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet s=wb.getSheet(sheetName);
		int r=s.getPhysicalNumberOfRows();
		
		for(int i=0;i<r;i++) {
			List<String> d = new ArrayList<>();
			
			int c=s.getRow(i).getPhysicalNumberOfCells();
			for(int j=0;j<c;j++) {
				d.add(s.getRow(i).getCell(j).toString());
				
				
			}
			data.add(d);
			
		}
		
		
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName) throws Exception, IOException {
		FileInputStream fis=new FileInputStream("./testData/FacebookTesData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
		
	}
	
	
	public void setDataIntoExcel(String sheetName, int rownum,int celNum,String value) throws Exception {
		FileInputStream fis=new FileInputStream("./testData/FacebookTesData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		//wb.getSheet(sheetName).createRow(rownum).createCell(celNum).setStringCellValue();
		wb.getSheet(sheetName).createRow(rownum).createCell(celNum).setCellValue(value);
		FileOutputStream fos=new FileOutputStream("./testData/FacebookTesData.xlsx");
		wb.write(fos);
		wb.close();
		fis.close();
		return ;
		

}}
