package com.vtigerMaven.generics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Baishali
 *
 */
public class WebDriverUtils{

	/**
	 * Wait for all elements to load in the application
	 * @param driver
	 * @param waitTime
	 */
	public void handleImplicitWait(WebDriver driver, long waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}
	
	/**
	 * This method selects value from listbox by its text
	 * @param wb
	 * @param selText
	 */
	public void ddlSelectByVisibleText(WebElement wb, String selText)
	{
		Select s = new Select(wb);
		s.selectByVisibleText(selText);		
	}
	
	/**
	 * This method selects value from listbox by its value parameter
	 * @param wb
	 * @param value
	 */
	public void ddlSelectByValue(WebElement wb, String value)
	{
		Select s = new Select(wb);
		s.selectByVisibleText(value);		
	}
	
	/**
	 * This method handles the mouse hover action on an element
	 * @param wb
	 */
	public void handleMouseHover(WebElement wb)
	{
		Actions act = new Actions(BaseClass.driver);
		act.moveToElement(wb).perform();
	}
	
	public int generateRandomNumber()
	{
		Random rNum = new Random();
		int num = rNum.nextInt(5000);
		return num;
	}
}
