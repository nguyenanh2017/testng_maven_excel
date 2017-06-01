package read.com.pk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private String url;
	private int lastRow;
	private int lastCellInRow;
	private static XSSFWorkbook file;
	
	public void setFile(String url){
		try{
			File src = new File(url);
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			this.file = wb;
		}catch(Exception e){
			System.out.println("co loi o file");
		}
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow() {
		//get sheet1
		XSSFSheet s1 = ReadExcel.file.getSheetAt(0);
		int last = s1.getLastRowNum();
		this.lastRow = last;
	}
	public int getLastCellInRow() {
		return lastCellInRow;
	}

	public void setLastCellInRow(int Row) {
		XSSFSheet s1 = ReadExcel.file.getSheetAt(0);
		Row r = s1.getRow(Row);
		int lastCell = r.getLastCellNum();
		this.lastCellInRow = lastCell;
	}
	public ReadExcel(String url) {
		super();
		this.url = url;
	}

	public ReadExcel() {
		super();
	}
	
	public String readFile(int row,int cell){
		String kq="endFile";
		//get sheet1
		XSSFSheet s1 = ReadExcel.file.getSheetAt(0);
		Row r = s1.getRow(row);
		int lastCell = r.getLastCellNum();
		
		//neu cai hang yeu cau lon hon cai hang cuoi cung trong file thi la het file
		if(row < s1.getLastRowNum()||cell < lastCell){
		kq = s1.getRow(row).getCell(cell).getStringCellValue();
		}
		return kq;
	}


	public void read() throws Exception {
		File src= new File("C:\\Users\\nguyenanh\\Documents\\abc.xlsx");
		FileInputStream fi = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
//		HSSFWorkbook wb1 = new HSSFWorkbook(fi);
//		HSSFSheet s2 = wb1.getSheetAt(0);
		
		XSSFSheet s1 =  wb.getSheetAt(0);
		//dong cuoi cung
		int lastRow = s1.getLastRowNum();
		
		String data0 = s1.getRow(0).getCell(0).getStringCellValue();
//		o cuoi cung trong dong ne
		Row r = s1.getRow(4);
		int lastCell = r.getLastCellNum();
		
		System.out.println("data= "+data0+"  dong cuoi cung la: "+lastRow+"   cell cuoi cung la: "+lastCell);	
	}

}
