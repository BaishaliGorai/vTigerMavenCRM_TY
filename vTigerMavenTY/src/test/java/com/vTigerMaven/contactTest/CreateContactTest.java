package com.vTigerMaven.contactTest;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Iterator;

import com.vtigerMaven.generics.BaseClass;
/**
 * 
 * @author Baishali
 *
 */
public class CreateContactTest extends BaseClass {
	/**
	 * Creates contact with organization.
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void createContact() throws Throwable
	{
		String salutationType = exUtils.fetchDataFromExcel("Contact", 1, 4);
		String fname = exUtils.fetchDataFromExcel("Contact", 1, 5);;
		String lname = exUtils.fetchDataFromExcel("Contact", 1, 6);;			
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		WebElement wbSalType = driver.findElement(By.name("salutationtype"));
		wUtils.ddlSelectByValue(wbSalType, salutationType);
		
		driver.findElement(By.name("firstname")).sendKeys(fname);
		driver.findElement(By.name("lastname")).sendKeys(lname);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		/*
		Set<String> sessionIds = driver.getWindowHandles();
		String mainSessionId = driver.getWindowHandle();
		
		for(String s: sessionIds)
		{			
			if(!mainSessionId.equals(s)) {
				driver.switchTo().window(s);
				handleOrgNamePopup();
				
				driver.switchTo().window(mainSessionId);
				driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
				
				String actSuccessMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				String expSuccessMsg = lname + " " + fname + " -  Contact Information";
				
				Assert.assertTrue(actSuccessMsg.contains(expSuccessMsg));
				System.out.println("Contact created successfully");
			}
		}	
		OR......*/
		
		Set<String> sessionIds = driver.getWindowHandles();
		Iterator<String> it = sessionIds.iterator();
		
		String parentWinID = it.next();
		String childWinID = it.next();
		
		driver.switchTo().window(childWinID);
		handleOrgNamePopup();
		
		driver.switchTo().window(parentWinID);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		String actSuccessMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String expSuccessMsg = lname + " " + fname;
		
		Assert.assertTrue(actSuccessMsg.contains(expSuccessMsg));
		System.out.println("Contact created successfully");
	}
	
	public void handleOrgNamePopup() throws IOException, Exception
	{
		String org_name = exUtils.fetchDataFromExcel("Contact", 1, 2);;	
		String search_field = exUtils.fetchDataFromExcel("Contact", 1, 3);;
		
		driver.findElement(By.id("search_txt")).sendKeys(org_name);
		
		WebElement wbSearchField = driver.findElement(By.name("search_field"));
		wUtils.ddlSelectByVisibleText(wbSearchField, search_field);
		
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(org_name)).click();
	}
}
