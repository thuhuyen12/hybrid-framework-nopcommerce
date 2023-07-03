package com.sourcelab.sort;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.sourceLab.LoginPageObject;
import pageObjects.sourceLab.ProductPageObject;
import pageObjects.sourceLab.SourceLabPageGeneratorManager;

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

public class Level_19_Sort_Asc_Desc extends BaseTest{

	@Parameters({"browser", "appUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String sourceLabUrl) {
		driver = getBrowserDriver(browserName, sourceLabUrl);
		loginPage = SourceLabPageGeneratorManager.getLoginPage(driver);
		
		log.info("Pre: Login successful");
		loginPage.enterToUserNameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		productPage = loginPage.clickLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		log.info("Sort Name - 01: Sort Name Ascending");
		productPage.selectItemInProductSortDropdown("az");
		Assert.assertTrue(productPage.isProductNameSortByAscending());
		
		
		log.info("Sort Name - 02: Sort Name Descending");
		productPage.selectItemInProductSortDropdown("za");
		Assert.assertTrue(productPage.isProductNameSortByDescending());
		
	}
		
	@Test
	public void Sort_02_Price() {
		log.info("Sort Price - 01: Sort Price Ascending");
		productPage.selectItemInProductSortDropdown("lohi");
		Assert.assertTrue(productPage.isProductPriceSortByAscending());
		
		
		log.info("Sort Price - 02: Sort Price Descending");
		productPage.selectItemInProductSortDropdown("hilo");
		Assert.assertTrue(productPage.isProductPriceSortByDescending());
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() { 
		closeBrowserDriver();
	}

	private WebDriver driver;
	private LoginPageObject loginPage;
	private ProductPageObject productPage;


	
	

}
