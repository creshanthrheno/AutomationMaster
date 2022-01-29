package com.TestExecution;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ManageUtils.BrowserManager;
import com.ManageUtils.ExplicitWaitManager;
import com.ObjectRepositories.EbayPageObjectRepositories;

public class EbayProductValidationTest {
	
	WebDriver driver;
	
	/**
	 * @TestName:eBayValidationTest001
	 * @Descritpion:Print the price of the 1 product in search result into the console and verify the price with expected one
	 */
	@Test
	public void eBayValidationTest001() {

		try {
			
			//Invoke Browser
			driver = BrowserManager.launchBrowser();
			
			// Navigate to signIn Page
			driver.get("https://www.ebay.com/");
			
			//Perform search operation in search engine
			EbayPageObjectRepositories.getSearchEngineTextArea(driver).sendKeys("Electric Guitar");
			
			//Click search  button
			EbayPageObjectRepositories.getSearchButton(driver).click();
			
			//Perform explicit wait to get search result
			ExplicitWaitManager.waitUntillObjectVisibility(driver, EbayPageObjectRepositories.getSearchedResultsLocator(),
					Duration.ofSeconds(10));
			
			//get the first product price from the searched result
			System.out.println(EbayPageObjectRepositories.getThePriceOfTheProductByIndex(0,driver));
			
		} 
		catch (Exception exp) {
			System.out.println("Error occurred Due to:" + exp.getMessage());
		}
		finally
		{
			driver.quit();
		}
	}
	
}
