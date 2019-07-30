package vasu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VasuDeva implements Deva,Yadav
{

	
	public void msg() 
	{
		System.out.println("Vasu");
		
	}
public static void main(String[] args)
{
	Yadav v=new VasuDeva();
	v.msg();
}
	

}
