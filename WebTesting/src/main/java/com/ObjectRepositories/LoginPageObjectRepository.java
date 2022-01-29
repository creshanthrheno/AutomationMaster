package com.ObjectRepositories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObjectRepository {

	private static By useremailInputText = By.id("identifierId");
	
	private static By userInputNextButton = By.id("identifierNext");
	
	private static By userPwdInputText = By.cssSelector("input[type='password']");
	
	private static By pwdInputNextButton = By.id("passwordNext");
	
	private static By signedInUserIcon = By.xpath("//img[@class = 'gb_Aa gbii']/parent::a");
	
	private static By userErrorMessage = By.cssSelector("div.o6cuMc");
	
	private static By pwdErrorMessage = By.cssSelector("div[class = 'OyEIQ uSvLId']>div>span");
	
	
	
	
	public static WebElement getUserNameInputText(WebDriver driver)
	{
		  return driver.findElement(useremailInputText);
	}
	
	public static WebElement getuserPwdInputText(WebDriver driver)
	{
		return driver.findElement(userPwdInputText);
	}


	public static WebElement getUserInputNext(WebDriver driver)
	{
		  return driver.findElement(userInputNextButton);
	}
	
	public static WebElement getUserPwdInputNext(WebDriver driver)
	{
		  return driver.findElement(pwdInputNextButton);
	}
	
	
	public static WebElement getSignedUserIcon(WebDriver driver)
	{
		  return driver.findElement(signedInUserIcon);
	}
	

	public static WebElement getInvalidUserMsg(WebDriver driver)
	{
		  return driver.findElement(userErrorMessage);
	}
	
	public static WebElement getInvalidPwdMsg(WebDriver driver)
	{
		  return driver.findElement(pwdErrorMessage);
	}
	
	public static By getpwdLocator()
	{
		return userPwdInputText;
	}
	
	public static By getCornerSignedUserIconLocator()
	{
	    return signedInUserIcon;	
	}
}
