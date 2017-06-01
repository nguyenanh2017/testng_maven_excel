package TestListener;


import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import test.pk.testGetLogin;

public class listenerTestGetLogin implements ITestListener{
	WebDriver driver =null;
	String path ="C:\\chuphinh";
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testcase failed is :"+result.getName());
		System.out.println("---------->"+result.getStatus());
		String methodName=result.getThrowable().toString().trim();
		System.out.println("+++++"+methodName);
		try {
			chuphinh(methodName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void chuphinh(String nameError) throws InterruptedException{
		System.out.println("chup hinh");
		driver=testGetLogin.getDriver();
		System.out.println(driver);
		Thread.sleep(5000);
		System.out.println("loi ne!-chup hinh lai");
		
		/*chup anh trinh duyet, du trinh duyet khong o tren cung nhung van chup anh duoc
		 * khuyet diem la khong chup duoc alert
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("c:\\hinhchup\\hinh_.jpg"));
			
			//click alert()
			//driver.switchTo().alert().accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//chup anh man hinh, khi dang chay code nay man hinh se bi chup lai nen web phai o tren cung
		//nhung no chup duoc alert
		
		BufferedImage image;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			 DateFormat dateFormat = new SimpleDateFormat("dmyyyy_HHmmss");
		       //get current date time with Date()
		     Date date = new Date();
		     String dateTime = dateFormat.format(date).toString().trim();
		     
		     nameError = nameError.substring(0, nameError.indexOf(":"));
		     
		     System.out.println(dateTime+"---"+nameError);
		     
		     ImageIO.write(image, "png", new File("screenshot/"+nameError+"_"+dateTime+".png"));
			 
			
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.quit();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("The name of the testcase failed is :"+result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
