package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtils;

public class DriverScript 
{
WebDriver driver;
ExtentReports report;
ExtentTest test;

	public  void startTest() throws Throwable
	{
		ExcelFileUtils excel=new ExcelFileUtils();
		
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++) 
		 {
			String ModuleStatus="";
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=excel.getData("MasterTestCases", i, 1);
				int rowcount=excel.rowCount(TCModule);
				report=new ExtentReports("./Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
				test=report.startTest(TCModule);
			
				for (int j = 1; j <=rowcount; j++) 
				{
					
					String Description=excel.getData(TCModule, j, 0);
					String Object_Type=excel.getData(TCModule, j, 1);
					
					String Locator_Type=excel.getData(TCModule, j, 2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					try
					{
						
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver=FunctionLibrary.startBrowser(driver);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("openApplication"))
					{
						FunctionLibrary.openApplication(driver);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						FunctionLibrary.wiatForElement(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						FunctionLibrary.closeBrowser(driver);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("captureData"))
					{
						FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("pageDown"))
					{
						FunctionLibrary.pageDown(driver);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("tableValidation"))
					{
						FunctionLibrary.tableValidation(driver, Test_Data);
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("stockCategories"))
					{
						FunctionLibrary.stockCategories(driver);
						test.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("tableValidation1")) 
					{
					FunctionLibrary.tableValidation1(driver, Test_Data);
					test.log(LogStatus.INFO, Description);
					}
					excel.setData(TCModule, j, 5, "PASS");
					ModuleStatus="true";
					test.log(LogStatus.PASS, Description);
					}catch(Exception e)
					{
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						
						FileUtils.copyFile(srcFile, new File("D:\\StockAccountingVasu\\StockAccounting\\ScreenShots\\"+" "+Description+FunctionLibrary.generateDate()+".png"));
						excel.setData(TCModule, j, 5, "FAIL");
						ModuleStatus="false";
						test.log(LogStatus.FAIL, Description);
						break;
					}
					
				}
				if (ModuleStatus.equalsIgnoreCase("true"))
				{
				excel.setData("MastertestCases", i, 3, "PASS");	
				}else
					if(ModuleStatus.equalsIgnoreCase("false"))
					{
						excel.setData("MastertestCases", i, 3, "FAIL");
					}
				report.endTest(test);
				report.flush();
			}
			
			else
			{
				excel.setData("MastertestCases", i, 3, "Not Executed");	
			}
			
		}
	}
}
