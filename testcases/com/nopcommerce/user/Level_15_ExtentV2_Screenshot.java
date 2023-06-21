package com.nopcommerce.user;

import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.LogStatus;

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
//import reportConfig.ExtentManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_15_ExtentV2_Screenshot extends BaseTest{

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName="Nguyen";
		lastName="Minh Tai";
		password="123456";
		validEmail = "minhtai" + generateFakeNumber() + "@gmail.com";		
	}

	@Test
	public void User_01_Register(Method method) {
		homePage = PageGeneratorManager.getHomePage(driver);
	
//		ExtentManager.startTest(method.getName(), "User_01_Register");
//	
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to 'Register' page");
//		registerPage = homePage.clickToRegisterLink();
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 02: Enter to first name textbox with value is " + firstName );
//		registerPage.inputToFirstnameTextbox(firstName);
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 03: Enter to last name textbox with value is " + lastName );
//		registerPage.inputToLastnameTextbox(lastName);
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 04: Enter to Email textbox with value is " + validEmail );
//		registerPage.inputToEmailTextbox(validEmail);
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 05: Enter to passoword textbox with value is " + password );
//		registerPage.inputToPasswordTextbox(password);
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 06: Enter to confirm password textbox with value is " + password );
//		registerPage.inputToConfirmPasswordTextbox(password);
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 07: Click to Register button" );
//		registerPage.clickToRegisterButton();
//	
//		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 08: Verify register success message appear" );
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
//		
//		ExtentManager.endTest();
	}
	
	@Test
	public void User_02_Login(Method method) {
//		ExtentManager.startTest(method.getName(), "User_02_Login");
//
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 01: Navigate to login link" );
//		loginPage = homePage.clickToLoginLink();	
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 02: Enter to email textbox with email is " +  validEmail);
//		loginPage.inputToEmailTextbox(validEmail);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 03: Enter to password textbox with email is" + password );
//		loginPage.inputToPasswordTextbox(password);
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 04: Click to login button" );
//		homePage = 	loginPage.clickToLoginButton();	
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 05: Verify 'My account' link is displayed" );
//		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 06: Navigate to My account page" );
//		customerInfoPage = homePage.clickToMyAccountLink();
//		
//		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 07: Verify Customer Info page is displayed" );
//		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
//	
//		ExtentManager.endTest();

	}
	
	
	@AfterClass
	public void afterClass() { 
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, validEmail, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressPageObject addressPage;
	private MyProductReviewPageObject myProductReviewPage;
	private RewardPointPageObject rewardPointPage;


	
	

}
