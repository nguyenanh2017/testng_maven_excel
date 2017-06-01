package test.pk;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;
import read.com.pk.ReadExcel;


public class testGetLogin {
  @Test
  public void test() throws InterruptedException {
	  //khoi tao ban dau
	  	//doc file excel + khoi tao doi tuong
		String urlFile = "abc.xlsx";
		ReadExcel fileExcel =new ReadExcel(urlFile);
		fileExcel.setFile(urlFile);
		fileExcel.setLastRow();
		//chuan bi driver test
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
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
					Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(checkKey));
					System.out.println("check_login ok");
					break;
			}//swith
			i++;
		}//while
	  //don dep sau khi test
		System.out.println("test ok het");
		Thread.sleep(5000);
		driver.quit();
	}//ham test
}//class test
