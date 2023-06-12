package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.MyProductReviewPageObject;
import pageObject.AddressPageObject;
import pageObject.CustomerInfoPageObject;
import pageObject.PageGeneratorManager;
import pageObject.RegisterPageObject;
import pageObject.RewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Switch_Page extends BaseTest{

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
	public void User_01_Register() {
		homePage = PageGeneratorManager.getHomePage(driver);

		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
//		registerPage.clickToLogoutLink();

	
		
	}
	
	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();	
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(password);

		homePage = 	loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	
	@Test
	public void User_03_MyAccount() {
		customerInfoPage = homePage.clickToMyAccountLink();

		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());

	}
	
	@Test
	public void User_04_Switch_Page() {
		// Customer Info => Address
		addressPage = customerInfoPage.openAddressPage(driver);
		// Address => My product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		// My product review => Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		// Reward Point => Address
		addressPage = rewardPointPage.openAddressPage(driver);
		// Address => Reward Point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		//Reward Point => My product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	}
	
	@Test
	public void User_05_Switch_Role() {
		//Role User => Admin
		
		
		
		//Role Admin => User

	
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
