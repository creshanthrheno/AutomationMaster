package com.TestExecution;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ManageUtils.BrowserManager;
import com.ManageUtils.ExplicitWaitManager;
import com.ObjectRepositories.LoginPageObjectRepository;

public class GMailLogginValidationTest {
	
	WebDriver driver;
	
	/**
	 * @TestName:gMailLogginValidationTest001
	 * @param:ValidUserGmailId, valid user email Id
	 * @param:ValidUserpwd, valid user password
	 * @param:ValidUserName, user name
	 * @Description:Launching email home page by using vaild user credentials
	 */
	@Parameters({"ValidUserGmailId","ValidUserpwd","ValidUserName"})
	@Test
	public void gMailLogginValidationTest001(String validMailId, String pwd, String userName) {

		try {
			// Navigate to signIn Page
			driver.get("https://accounts.google.com/signin");

			// Get the user text box
			LoginPageObjectRepository.getUserNameInputText(driver).sendKeys(validMailId);

			// Click Next button to navigate to password entering page
			LoginPageObjectRepository.getUserInputNext(driver).click();

			// Perform explicit wait for visibility of password text box in the screen
			ExplicitWaitManager.waitUntillObjectVisibility(driver, LoginPageObjectRepository.getpwdLocator(),
					Duration.ofSeconds(10));

			// Get pwd text box and enter the password
			LoginPageObjectRepository.getuserPwdInputText(driver).sendKeys(pwd);

			// Click the Next button for entering into the account home page
			LoginPageObjectRepository.getUserPwdInputNext(driver).click();

			// Perform explicit wait for visibility of current user Icon in the screen
			ExplicitWaitManager.waitUntillObjectVisibility(driver,
					LoginPageObjectRepository.getCornerSignedUserIconLocator(), Duration.ofSeconds(10));

			// get the signed user icon to verify that the signed user and expected user are
			// same
			String signedUserName = LoginPageObjectRepository.getSignedUserIcon(driver).getAttribute("aria-label")
					.replaceAll("\n", "").replaceAll(" ", "").split(":")[1];
			
			Assert.assertEquals(signedUserName, userName+"("+validMailId+"@gmail.com)");
		} 
		catch (Exception exp) {
			System.out.println("Error occurred Due to:" + exp.getMessage());
		}
	}

	/**
	 * @TestName:gMailLogginValidationTest001
	 * @param:InValidUserGmailId, Invalid user email Id
	 * @param:InValidUserpwd, Invalid user password
	 * @param:ValidUserGmailId, valid user email Id
	 * @Description: using Invalid user credentials verifying email loggin
	 */
	@Parameters({"InValidUserGmailId","InValidUserpwd","ValidUserGmailId"})
	@Test
	public void gMailLogginValidationTest002(String invalidMailId, String invalidpwd, String validMailId) {
		try {
			// Navigate to signIn Page
			driver.get("https://accounts.google.com/signin");

			SoftAssert softAsser = new SoftAssert();

			// Enter invalid user name to verify the error message
			LoginPageObjectRepository.getUserNameInputText(driver).sendKeys(invalidMailId);

			// Click Next button to navigate to password entering page
			LoginPageObjectRepository.getUserInputNext(driver).click();

			// Verify invalid user message showing in screen
			String actualErrMsg = LoginPageObjectRepository.getInvalidUserMsg(driver).getText();

			softAsser.assertEquals(actualErrMsg, "Couldn't find your Google Account");

			// Verify still the page shall not proceeding to pwd page due to invalid user
			softAsser.assertTrue(LoginPageObjectRepository.getUserNameInputText(driver).isDisplayed());

			// Verify pwd text area shall not be displayed in the screen
			softAsser.assertFalse(LoginPageObjectRepository.getuserPwdInputText(driver).isDisplayed());

			// Login as valid user
			LoginPageObjectRepository.getUserNameInputText(driver).clear();
			LoginPageObjectRepository.getUserNameInputText(driver).sendKeys(validMailId);
			LoginPageObjectRepository.getUserInputNext(driver).click();

			// Perform explicit wait for pwd text
			ExplicitWaitManager.waitUntillObjectVisibility(driver, LoginPageObjectRepository.getpwdLocator(),
					Duration.ofSeconds(10));

			// verify user text area shall not visible in screen
			softAsser.assertFalse(LoginPageObjectRepository.getUserNameInputText(driver).isDisplayed());

			// Enter wrong pwd in pwd text area
			LoginPageObjectRepository.getuserPwdInputText(driver).sendKeys(invalidpwd);

			// Click the next button
			LoginPageObjectRepository.getUserPwdInputNext(driver).click();

			// Get the errmsg text area
			softAsser.assertEquals(LoginPageObjectRepository.getInvalidPwdMsg(driver).getText(),
					"Wrong password. Try again or click ‘Forgot password’ to reset it.");

			// Verify still it stays in the same pwd page
			softAsser.assertTrue(LoginPageObjectRepository.getuserPwdInputText(driver).isDisplayed());

			softAsser.assertAll();
		}
		catch (Exception exp) {
			System.out.println("Error occurred Due to:" + exp.getMessage());
		}
	}

	@BeforeMethod
	public void launchBrowser()
	{
		//Invoke Browser
		driver = BrowserManager.launchBrowser();
	}
	
	@AfterMethod
	public void quitBrowser()
	{
		driver.quit();
	}
}
