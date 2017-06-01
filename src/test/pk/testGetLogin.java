package test.pk;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestListener.*;
import read.com.pk.ReadExcel;

@Listeners(TestListener.listenerTestGetLogin.class)

public class testGetLogin {
	public ITestResult result;
	public static WebDriver driver = new FirefoxDriver();
	public void chuphinh(WebDriver driver) throws IOException{
		System.out.println("loi ne!-chup hinh lai");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("c:\\hinhchup\\hinh_.jpg"));
	}
	public static WebDriver getDriver()
    {
        return driver;
    }
	
	@Test
	public void test() throws InterruptedException, IOException, AssertionError {
	  //khoi tao ban dau
	  	//doc file excel + khoi tao doi tuong
	  
		String urlFile = "abc.xlsx";
		ReadExcel fileExcel =new ReadExcel(urlFile);
		fileExcel.setFile(urlFile);
		fileExcel.setLastRow();
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	  //chay white de moi dong duoc 1 cau lenh test
		int i=0;
		while(i <= fileExcel.getLastRow()){
	  //kiem tra lenh test can lam viec
			switch(fileExcel.readFile(i, 0)){
			//kiem tra xem phan dau tien la gi de co su lua chon
				case "get" :
					//neu la get thi phong toi url(o thu 4 co index =3)
					System.out.println("GET ne");
					System.out.println(fileExcel.readFile(i,4));
					String url = fileExcel.readFile(i, 4);
					
					driver.get(url);
					break;
				case "click" :
					//neu la click thi phong toi targer(o thu 2 co index=1)
					System.out.println("CLICK ne");
					System.out.println(fileExcel.readFile(i, 1)+"----"+fileExcel.readFile(i, 2));
					switch(fileExcel.readFile(i, 2)){
					 	case "css" :
					 		System.out.println("select: "+fileExcel.readFile(i, 1)+"  bochon: "+fileExcel.readFile(i, 2));
					 		String select = fileExcel.readFile(i, 1);
					 		driver.findElement(By.cssSelector(select)).click();
					 		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					 		break;
					 	case "xpath":
					 		break;
					}
					break;
				case "sendKeys" :
					//neu la sendKeys thi lay o tager + value(index 1,2)
					System.out.println("sendKeys");
					System.out.println(fileExcel.readFile(i, 1)+"----"+fileExcel.readFile(i, 2)+"----"+fileExcel.readFile(i, 3));
					switch(fileExcel.readFile(i, 2)){
					 	case "css" :
					 		System.out.println("select: "+fileExcel.readFile(i, 1)+"  bochon: "+fileExcel.readFile(i, 2));
					 		String select = fileExcel.readFile(i, 1);
					 		String key = fileExcel.readFile(i, 3);
					 		driver.findElement(By.cssSelector(select)).sendKeys(key);
					 		break;
					 	case "xpath":
					 		break;
					}
					break;
				case "check_login" :
					Thread.sleep(3000);
					System.out.println("check_login"+" ----check key: "+fileExcel.readFile(i, 3));
					//System.out.println(driver.findElement(By.tagName("body")).getText());
					String checkKey = fileExcel.readFile(i, 3);
					String getbody = driver.findElement(By.tagName("body")).getText();

					System.out.println(testGetLogin.getDriver());
					Assert.assertTrue(getbody.contains(checkKey));					
					 
					System.out.println("check_login ok");
					break;
			}//swith
			i++;
		}//while
	
			//don dep sau khi test
			System.out.println("test ok het");
			driver.quit();
		
	 
	}//ham test
	
	
//	@AfterTest
//	public void sauTest(){
//		result = Reporter.getCurrentTestResult();
//		System.out.println("---------"+result.getStatus());
//	
//		if(result.getStatus() == ITestResult.FAILURE){
//			System.out.println("vao loi");
//		}
//		driver.quit();
//	}
		 
/*	@AfterMethod
  public void xulyketqua() throws IOException{
		result = Reporter.getCurrentTestResult();
		switch(result.getStatus()){
		case ITestResult.SUCCESS:
			System.out.println("khong co loi nhe");
			break;
		case ITestResult.FAILURE:
			System.out.println("loi ne!-chup hinh lai");
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("c:\\hinhchup\\hinh_.jpg"));
			break;
		  
		}
		  
  }*/
	
}//class test
