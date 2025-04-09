package util;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import orangeHRM.pages.AdminLogin;
import orangeHRM.pages.AdminLogout;

public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeMethod
public static void setUp()throws Throwable
{
	conpro = new Properties();
	//load property file
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//call login page
		AdminLogin lp = PageFactory.initElements(driver, AdminLogin.class);
		lp.adminLogin("Admin", "Qedge123!@#");
		
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		AdminLogin lp = PageFactory.initElements(driver, AdminLogin.class);
		lp.adminLogin("Admin", "Qedge123!@#");
	}
	else
	{
		throw new IllegalArgumentException("Browser value not matching");
	}
}
@AfterMethod
public static void tearDown()
{
	AdminLogout lplogout=PageFactory.initElements(driver, AdminLogout.class);
	lplogout.logout();
	driver.quit();
}
}
