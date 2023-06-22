package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.AddressPageObject;
import pageObject.nopCommerce.user.CustomerInfoPageObject;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.LoginPageObject;
import pageObject.nopCommerce.user.MyProductReviewPageObject;
import pageObject.nopCommerce.user.RegisterPageObject;
import pageObject.nopCommerce.user.RewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_16_Share_Data extends BaseTest{

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
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
		
		log.info("Login - Step 01: Navigate to login link" );
		loginPage = homePage.clickToLoginLink();	
		
		log.info("Login - Step 02: Enter to email textbox with email is " +  validEmail);
		loginPage.inputToEmailTextbox(validEmail);
		
		log.info("Login - Step 03: Enter to password textbox with email is" + password );
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 04: Click to login button" );
		homePage = 	loginPage.clickToLoginButton();	
		
	}

	@Test
	public void Search_01_Empty_Data() {
		
	}
	
	@Test
	public void Search_02_Relative_Product_Name() {
		
	}
	
	@Test
	public void Search_03_Absolute_Product_Name() {
		
	}
	
	@Test
	public void Search_04_Parent_Category() {
		
	}
	@AfterClass
	public void afterClass() { 
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, validEmail, password;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	
	

}