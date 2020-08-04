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
public class CreateOrganizationTest extends BaseClass {

	/**
	 * Creates organization with required and mandatory fields.
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void createOrganization() throws Throwable
	{
		String orgName = exUtils.fetchDataFromExcel("Organization", 1, 2);
		String orgIndustry = exUtils.fetchDataFromExcel("Organization", 1, 4);
		String orgType = exUtils.fetchDataFromExcel("Organization", 2, 3);
		
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
}
