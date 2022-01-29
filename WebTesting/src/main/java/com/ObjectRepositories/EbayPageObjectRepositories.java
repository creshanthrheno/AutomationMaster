package com.ObjectRepositories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EbayPageObjectRepositories {
	
private static By searchEngineTextArea = By.id("gh-ac");

private static By searchButton = By.id("gh-btn");

private static By rootList = By.xpath("//ul[@class ='srp-results srp-list clearfix']");

private static By allProductLink = By.xpath("//li[@class = 's-item s-item__pl-on-bottom s-item--watch-at-corner']");

private static By productPrice = By.xpath("//div[@class ='s-item__details clearfix']//span[@class ='s-item__price']");


public static WebElement getSearchEngineTextArea(WebDriver driver)
{
	  return driver.findElement(searchEngineTextArea);
}

public static WebElement getSearchButton(WebDriver driver)
{
	  return driver.findElement(searchButton);
}

public static WebElement getrootList(WebDriver driver)
{
	  return driver.findElement(rootList);
}

public static List<WebElement> getProductLink(WebDriver driver)
{
	  return driver.findElements(allProductLink);
}

public static WebElement getProductPrice(WebDriver driver)
{
	return driver.findElement(productPrice);
}


public static By getSearchedResultsLocator()
{
	return rootList;
}

public static String getThePriceOfTheProductByIndex(int productIndex, WebDriver driver)
{
	 return getrootList(driver).findElements(allProductLink).get(productIndex).findElement(productPrice).getText();
}
	
}
