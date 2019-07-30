package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utilities.PropertyFileUtil;

public class Library
{
	WebDriver driver;
String res;
	//appLaunch
	public String appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		//validation
		if (driver.findElement(By.id("username")).isDisplayed()) 
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
		return res;
	}
	
	//appLogin()
	public String appLogin(String username,String password)
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		//validation
		if(driver.findElement(By.xpath("//a[@id='logout']")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
		return res;	
		
	}
	
	//appLogout
	public String appLogout()
	{
		driver.findElement(By.xpath("//a[@id='logout']")).click();
		
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		//validation
				if (driver.findElement(By.id("username")).isDisplayed()) 
				{
					res="Pass";
				}else
				{
					res="Fail";
				}
				return res;
	}
	//appClose
	public void appClose()
	{
		driver.close();
	}
	public static void main(String[] args) throws Exception, Throwable
	{
		Library app=new Library();
		app.appLaunch(PropertyFileUtil.getValueForKey("URL"));
	}
}
