package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.facebook.LoginPageObject;
import pageObject.facebook.PageGeneratorManager;

public class Topic_13_Element_Undisplayed extends BaseTest {
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		driver = getBrowserDriver(browserName, appURL);
	
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickCreateNewAccountButton();
	
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
	
	}
	

	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		//Không nhập Email textbox - verifyTrue re-enter Email undisplayed hoặc verifyFalse cho displayed
		
		//verifyTrue(loginPage.isRe_enterEmailTextboxUnDisplayed());
		//Hoặc
		//	verifyFalse(loginPage.isRe_enterEmailTextboxDisplayed());
		verifyTrue(loginPage.isEmailAddressTextboxUndisplayed());
	}
	

	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
	//Đóng popup
		loginPage.clickCloseIcon();
		loginPage.sleepInSecond(2);
	
		verifyTrue(loginPage.isEmailAddressTextboxUndisplayed());
	}
	
		@AfterClass
		public void afterClass() { 
			driver.quit();
		}

		private WebDriver driver;
		private LoginPageObject loginPage;
}
