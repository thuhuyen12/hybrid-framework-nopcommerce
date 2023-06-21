package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Common_01_Register extends BaseTest{

	
	@Parameters("browser")
	@BeforeTest(description = "Create new User for all Classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName="Nguyen";
		lastName="Minh Tai";
		password="123456";
		validEmail = "minhtai" + generateFakeNumber() + "@gmail.com";		
		homePage = PageGeneratorManager.getHomePage(driver);
	
		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.clickToRegisterLink();
	
		log.info("Register - Step 02: Enter to first name textbox with value is " + firstName );
		registerPage.inputToFirstnameTextbox(firstName);
	
		log.info("Register - Step 03: Enter to last name textbox with value is " + lastName );
		registerPage.inputToLastnameTextbox(lastName);
	
		log.info("Register - Step 04: Enter to Email textbox with value is " + validEmail );
		registerPage.inputToEmailTextbox(validEmail);
	
		log.info("Register - Step 05: Enter to passoword textbox with value is " + password );
		registerPage.inputToPasswordTextbox(password);
	
		log.info("Register - Step 06: Enter to confirm password textbox with value is " + password );
		registerPage.inputToConfirmPasswordTextbox(password);
	
		log.info("Register - Step 07: Click to Register button" );
		registerPage.clickToRegisterButton();
	
		log.info("Register - Step 08: Verify register success message appear" );
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		driver.quit();
	}
	

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, validEmail, password;

	
	

}
