package com.vtigerMaven.generics;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.vtigerMaven.generics.WebDriverUtils;
import com.vtigerMaven.generics.PropertyUtils;
/**
 * This class is used to handle generic methods.
 * @author Baishali
 *
 */
public class BaseClass {

	PropertyUtils pObj = new PropertyUtils();
	public ExcelUtils exUtils = new ExcelUtils();
	public WebDriverUtils wUtils = new WebDriverUtils();
	public DatabaseUtils dbUtlis = new DatabaseUtils();
	public static WebDriver driver = null;
	
	/**
	 * Used to connect to the database initially.
	 * @throws Exception
	 * @throws IOException
	 */
	@BeforeSuite
	public void connectDB() throws Exception, IOException
	{
		dbUtlis.connectDB();
	}
	
	/**
	 * Used to launch the browser.
	 * @throws IOException
	 */
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		System.out.println("Launching the browser.");
		String url = pObj.fetchDataFromFile("url");
		String browser = pObj.fetchDataFromFile("browser");
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
				
		driver.get(url);
		driver.manage().window().maximize();
		wUtils.handleImplicitWait(driver, 20);
	}
	
	/**
	 * Used for logging into the application before test case execution starts.
	 * @throws IOException
	 */
	@BeforeMethod
	public void login() throws IOException
	{
		System.out.println("Logging to application.");
		String user_name = pObj.fetchDataFromFile("username");
		String password = pObj.fetchDataFromFile("password");
		
		driver.findElement(By.name("user_name")).sendKeys(user_name);
		driver.findElement(By.name("user_password")).sendKeys(password, Keys.ENTER);
	}
	
	/**
	 * Used to log out of the application
	 */
	@AfterMethod
	public void logout()
	{
		System.out.println("Logging out of the application");
		WebElement wb = driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td/img"));
		wUtils.handleMouseHover(wb);
		driver.findElement(By.linkText("Sign Out")).click();
	}
	
	/**
	 * Used to close the browser once the test case execution is done.
	 */
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("Closing the browser");
		driver.close();
	}
	
	/**
	 * Used to disconnect from database.
	 * @throws SQLException
	 */
	@AfterSuite
	public void disconnectDB() throws SQLException
	{
		dbUtlis.disconnectDB();
	}
}
