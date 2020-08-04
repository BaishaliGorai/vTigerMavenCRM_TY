package com.vTigerMaven.orgTest;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.vtigerMaven.generics.BaseClass;

/**
 * 
 * @author Baishali
 *
 */
public class DeleteOrganizationTest extends BaseClass{

	/**
	 * Used to delete organization
	 * @throws Exception 
	 * @throws IOException 
	 */
	@Test
	public void deleteOrganization() throws Throwable
	{
		String org_Name = exUtils.fetchDataFromExcel("Organization", 1, 2) + "_" + wUtils.generateRandomNumber();
		String org_Industry = exUtils.fetchDataFromExcel("Organization", 1, 3);
		String org_Type = exUtils.fetchDataFromExcel("Organization", 1, 4);
		String org_search_field = exUtils.fetchDataFromExcel("Organization", 7, 2);
		String expNoOrgMsg = exUtils.fetchDataFromExcel("Organization", 10, 2);
		
		navigateToOrg();
		createOrg(org_Name, org_Industry, org_Type);
		navigateToOrg();
		searchOrg(org_Name, org_search_field);
		
		Thread.sleep(2000);		
		deleteOrg(org_Name);
		Thread.sleep(2000);
		searchOrg(org_Name, org_search_field);
		
		String actNoOrgMsg = driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
		Assert.assertTrue(actNoOrgMsg.contains(expNoOrgMsg));
		System.out.println("Organization deleted successfully.");
	}
	
	/**
	 * Used to navigate to organizations.
	 */
	public void navigateToOrg()
	{
		driver.findElement(By.linkText("Organizations")).click();
	}
	
	/**
	 * Used to create a organization.
	 * @param orgName
	 * @param orgIndustry
	 * @param orgType
	 */
	public void createOrg(String orgName, String orgIndustry, String orgType)
	{
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
	 * Used to search organization.
	 * @param orgName
	 * @param orgSearchField
	 */
	public void searchOrg(String orgName, String orgSearchField)
	{
		WebElement searchTxt = driver.findElement(By.name("search_text"));
		searchTxt.clear();
		searchTxt.sendKeys(orgName);
		WebElement wb = driver.findElement(By.name("search_field"));
		wUtils.ddlSelectByVisibleText(wb, orgSearchField);
		driver.findElement(By.name("submit")).click();	
	}

	/**
	 * Used to delete selected organization.
	 * @param orgName
	 */
	public void deleteOrg(String orgName)
	{
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../following-sibling::td[last()]/a[last()]")).click();
		driver.switchTo().alert().accept();
	}
}
