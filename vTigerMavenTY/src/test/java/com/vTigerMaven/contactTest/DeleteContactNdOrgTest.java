package com.vTigerMaven.contactTest;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Iterator;
import java.util.List;

import com.vtigerMaven.generics.BaseClass;
/**
 * 
 * @author Baishali
 *
 */
public class DeleteContactNdOrgTest extends BaseClass {
	/**
	 * Creates contact with organization.
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void deleteContactNdOrg() throws Throwable
	{
		String org_Name = exUtils.fetchDataFromExcel("Organization", 1, 2) + "_" + wUtils.generateRandomNumber();
		String org_Industry = exUtils.fetchDataFromExcel("Organization", 1, 3);
		String org_Type = exUtils.fetchDataFromExcel("Organization", 1, 4);
		String searchContactTxt = exUtils.fetchDataFromExcel("Contact", 4, 2);
		String searchContactField = exUtils.fetchDataFromExcel("Contact", 4, 3);
		
		//Create an organization.
		createOrg(org_Name, org_Industry, org_Type);		
		//Create contact with that organization.
		createContact(org_Name);
		//Navigate to contacts.
		navigateToContacts();
		//Search the created contact in contacts.
		searchContact(searchContactTxt, searchContactField);
		//Delete the created contact and its respectective organization.
		deleteContact(searchContactTxt);
	}
	
	/**
	 * Used to create an organization
	 * @param orgName
	 * @param orgIndustry
	 * @param orgType
	 */
	public void createOrg(String orgName, String orgIndustry, String orgType)
	{
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement wbIndustry = driver.findElement(By.name("industry"));
		wUtils.ddlSelectByVisibleText(wbIndustry, orgIndustry);
		
		WebElement wbType = driver.findElement(By.name("accounttype"));
		wUtils.ddlSelectByValue(wbType, orgType);
		
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		String actSuccessMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actSuccessMsg.contains(orgName));
		System.out.println(orgName + " created successfully");
	}
	
	/**
	 * Used to navigate to contacts module
	 */
	public void navigateToContacts()
	{
		driver.findElement(By.linkText("Contacts")).click();
	}
	
	/**
	 * Used to create a contact with organization
	 * @param orgName
	 * @throws Throwable
	 */
	public void createContact(String orgName) throws Throwable
	{
		String salutationType = exUtils.fetchDataFromExcel("Contact", 1, 4);
		String fname = exUtils.fetchDataFromExcel("Contact", 1, 5);
		String lname = exUtils.fetchDataFromExcel("Contact", 1, 6);
		String searchField = exUtils.fetchDataFromExcel("Contact", 1, 3);		
		
		navigateToContacts();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		WebElement wbSalType = driver.findElement(By.name("salutationtype"));
		wUtils.ddlSelectByValue(wbSalType, salutationType);
		
		driver.findElement(By.name("firstname")).sendKeys(fname);
		driver.findElement(By.name("lastname")).sendKeys(lname);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		Set<String> sessionIds = driver.getWindowHandles();
		Iterator<String> it = sessionIds.iterator();
		
		String parentWinID = it.next();
		String childWinID = it.next();
		
		driver.switchTo().window(childWinID);
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		
		WebElement wbSearchField = driver.findElement(By.name("search_field"));
		wUtils.ddlSelectByVisibleText(wbSearchField, searchField);
		
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgName)).click();
		
		driver.switchTo().window(parentWinID);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		String actSuccessMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String expSuccessMsg = lname + " " + fname;
		
		Assert.assertTrue(actSuccessMsg.contains(expSuccessMsg));
		System.out.println("Contact created successfully");
	}	

	/**
	 * Used to search contact.
	 * @param search_contact_txt
	 * @param search_contact_field
	 * @throws Throwable
	 */
	public void searchContact(String search_contact_txt, String search_contact_field) throws Throwable
	{		
		driver.findElement(By.name("search_text")).sendKeys(search_contact_txt);
		WebElement wb = driver.findElement(By.name("search_field"));
		wUtils.ddlSelectByVisibleText(wb, search_contact_field);
		driver.findElement(By.name("submit")).click();		
	}
	
	/**
	 * Used to delete the selected contact and also delete the contact's organization
	 * @param delContact
	 * @throws Throwable
	 */
	public void deleteContact(String delContact) throws Throwable
	{
		String delContactOrg = driver.findElement(By.xpath("//a[text()='" + delContact + "']/../following-sibling::td[2]/a")).getText();
		driver.findElement(By.xpath("//a[text()='" + delContact + "']/../following-sibling::td[last()]/a[last()]")).click();
		System.out.println(delContactOrg);
		driver.switchTo().alert().accept();		
		
		String expNoContactMsg = exUtils.fetchDataFromExcel("Contact", 4, 7);
		String actNoContactMsg = driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
		Assert.assertTrue(actNoContactMsg.contains(expNoContactMsg));
		System.out.println("Contact deleted successfully.");
		
		driver.findElement(By.linkText("Organizations")).click();		
		
		searchOrg(delContactOrg);			
		deleteOrg(delContactOrg);
		
		//searchOrg(delContactOrg);	
	}

	/**
	 * Used to search organization.
	 * @param orgName
	 * @param orgSearchField
	 */
	public void searchOrg(String orgName) throws Throwable
	{
		String orgSearchField = exUtils.fetchDataFromExcel("Contact", 4, 8);
		
		WebElement searchTxt = driver.findElement(By.name("search_text"));
		searchTxt.sendKeys(orgName);
		WebElement wb = driver.findElement(By.name("search_field"));
		wUtils.ddlSelectByVisibleText(wb, orgSearchField);
		driver.findElement(By.name("submit")).click();	
	}
	/**
	 * Used to delete selected organization.
	 * @param orgName
	 * @throws Exception 
	 * @throws IOException 
	 */
	public void deleteOrg(String orgName) throws Throwable
	{
		String expNoOrgMsg = exUtils.fetchDataFromExcel("Contact", 4, 9);
		
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../following-sibling::td[last()]/a[last()]")).click();
		driver.switchTo().alert().accept();
		
		String actNoOrgMsg = driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
		Assert.assertTrue(actNoOrgMsg.contains(expNoOrgMsg));
		System.out.println("Organization deleted successfully.");
	}
}
