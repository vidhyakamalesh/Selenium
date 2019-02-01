package testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.Homepage;
import PageObject.Userpage;
import PageObject.loginp;

public class login {
	WebDriver driver;
	@BeforeMethod(alwaysRun=true) // to run in xml group everytime
	public void Bm()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://magento.com/");
	}
	@AfterMethod(alwaysRun=true)
	public void am()
	{
		driver.quit();
	}
	@Test(enabled=true,dataProvider="negative_data")
	public void negative_login(String email, String pwd)
	{
		driver.findElement(Homepage.user).click();
		driver.findElement(loginp.email).sendKeys(email);
		driver.findElement(loginp.pwd).sendKeys(pwd);
		driver.findElement(loginp.btn).click();
		Assert.assertEquals(driver.findElement(Userpage.msg).getText(), "Invalid login or password.");
		driver.quit();
	}
	@DataProvider(parallel=true)
	public Object[][] negative_data()
	{
		return new Object[][] {
			{"email1@gmail.com","pwd"},
			{"email2@gmail.com","pwd2"},
			{"email3@gmail.com","pwd3"}
			
		};
	}
	
	
	@Test(priority=1,groups= {"S"})// grouping like smpoke or sanity check 
	public void successful_login()
	{
		driver.findElement(Homepage.user).click();
		driver.findElement(loginp.email).sendKeys("natarajan.ramanathan93@gmail.com");
		driver.findElement(loginp.pwd).sendKeys("Welcome123");
		driver.findElement(loginp.btn).click();
		Assert.assertEquals(driver.findElement(Userpage.successmsg).getText(),"ID: MAG003417822");
		
	}

}
