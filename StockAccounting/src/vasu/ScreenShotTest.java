package vasu;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScreenShotTest 
{

	public static void main(String[] args) throws Throwable 
	{
		System.setProperty("webdriver.chrome.driver", 
			"C:\\Users\\rangareddy.QEDGE\\Downloads\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("Http://Gmail.com");
		driver.manage().window().maximize();
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(srcFile, new 
				
			File("C:\\Users\\rangareddy.QEDGE\\Desktop\\New folder (2)\\vasu.png"));
		
		
		
		
		

	}

}
