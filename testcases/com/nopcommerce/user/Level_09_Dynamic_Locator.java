package com.nopcommerce.user;

import org.testng.annotations.Test;

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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_09_Dynamic_Locator extends BaseTest{

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
	public void User_01_Register_Login() {
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

		loginPage = homePage.clickToLoginLink();	
		loginPage.inputToEmailTextbox(validEmail);
		loginPage.inputToPasswordTextbox(password);

		homePage = 	loginPage.clickToLoginButton();	
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	
	@Test
	public void User_02_Dynamic_Page() {
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
	public void User_03_Dynamic_Page_01() {	
		// My product review => Reward Point
		rewardPointPage = (RewardPointPageObject) myProductReviewPage.openPageAtMyAccountByName(driver,"Reward points");
		
		// Reward Point => Address
		addressPage = (AddressPageObject) rewardPointPage.openPageAtMyAccountByName(driver,"Addresses");
		// Address => Reward Point
		rewardPointPage = (RewardPointPageObject) addressPage.openPageAtMyAccountByName(driver,"Reward points");
		//Reward Point => My product review
		myProductReviewPage = (MyProductReviewPageObject) rewardPointPage.openPageAtMyAccountByName(driver,"My product reviews");
		//My product review => Customer Info
		customerInfoPage = (CustomerInfoPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Customer info");
	
	}
	@Test
	public void User_03_Dynamic_Page_02() {	
		//Customer Info => Product review
		 customerInfoPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		 myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		
		 // My product review => Reward Point
		 myProductReviewPage.openPageAtMyAccountByName(driver, "Reward points");
		 rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);

		// Reward Point => Address
		 rewardPointPage.openPageAtMyAccountByName(driver,"Addresses");
		 addressPage = PageGeneratorManager.getAddressPage(driver);
		 
		// Address => Reward Point
		addressPage.openPageAtMyAccountByName(driver,"Reward points");
		rewardPointPage = PageGeneratorManager.getRewardPointPage(driver);
		
		//Reward Point => My product review
		rewardPointPage.openPageAtMyAccountByName(driver,"My product reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		
		//My product review => Customer Info
		myProductReviewPage.openPageAtMyAccountByName(driver, "Customer info");
		customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
	
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
