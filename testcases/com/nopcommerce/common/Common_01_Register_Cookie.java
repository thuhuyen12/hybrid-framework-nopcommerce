package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.LoginPageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Common_01_Register_Cookie extends BaseTest{

	
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
		
		log.info("Login - Step 09: Navigate to login link" );
		loginPage = homePage.clickToLoginLink();	
		
		log.info("Login - Step 10: Enter to email textbox with email is " +  validEmail);
		loginPage.inputToEmailTextbox(validEmail);
		
		log.info("Login - Step 11: Enter to password textbox with email is" + password );
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 12: Click to login button" );
		homePage = 	loginPage.clickToLoginButton();	
	
		loggedCookies = homePage.getAllCookies(driver);
		for (Cookie cookie : loggedCookies) {
			System.out.println("Cookie at Common class: " + cookie);
	}
		
		
	}
	
	
	
	@AfterTest
	public void afterClass() { 
		driver.quit();
	}

	private WebDriver driver;
	public static String  validEmail, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName;
	private LoginPageObject loginPage;
	public static Set<Cookie> loggedCookies;
	

}
