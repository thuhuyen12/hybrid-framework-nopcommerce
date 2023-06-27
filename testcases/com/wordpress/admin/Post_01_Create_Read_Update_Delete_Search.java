package com.wordpress.admin;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.AdminPostAddNewPO;
import pageObjects.wordpress.admin.AdminDashboardPO;
import pageObjects.wordpress.admin.AdminLoginPO;
import pageObjects.wordpress.admin.AdminPageGeneratorManager;
import pageObjects.wordpress.admin.AdminPostSearchPO;

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

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{

	String username = "automationfc";
	String password = "automationfc";
	String postTitle = "The automation testing class " + getGenerateFakeNumber();
	String postBody = "1. Le Nhat Minh \n 2. Tran To Uyen \n 3. Nguyen Minh Tai \n Total is " + getGenerateFakeNumber();
	String postSearchUrl;
	
	
	@Parameters({"browser","urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin) {
		log.info("Pre - 01: Open browser and admin URL");
		driver = getBrowserDriver(browserName, urlAdmin);
		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre - 02: Enter to Username textbox value is " + username);
		adminLoginPage.enterToUsernameTextbox(username);
		
		log.info("Pre - 03: Enter to Password textbox value is " + password);
		adminLoginPage.enterToPasswordTextbox(password);
		
		log.info("Pre - 04: Click Login button"); 
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Creat_New_Post() {
		log.info("Create 01: Click to Posts in menu links"); 
		adminPostSearchPage = adminDashboardPage.clickToPostLink();
		postSearchUrl = adminPostSearchPage.getPageUrl(driver);
		
		log.info("Create 02: Click to 'Add new' button"); 
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
		
		log.info("Create 03: Enter to Add Title block");
		adminPostAddNewPage.enterToTitleBlock(postTitle);
			
		log.info("Create 04: Click to Body block");
		adminPostAddNewPage.clickToBodyBlock();
		
		log.info("Create 05: Enter to Body block");
		adminPostAddNewPage.enterToBodyBlock(postBody);
		
		log.info("Create 06: Click to Publish button");
		adminPostAddNewPage.clickToPublishButton();
		
		log.info("Create 07: Verify message 'Post published' is displayed");
		Assert.assertTrue(adminPostAddNewPage.isPostPublishedMessageDisplayed("Post published."));
	
	}
	
	@Test
	public void Post_02_Search_Post() {
		log.info("Search 01: Open Post Search Page URL");
		adminPostSearchPage = adminPostAddNewPage.openPostSearchPage(postSearchUrl); 
	}


	@Test
	public void Post_03_View_Post() {
		
	}

	@Test
	public void Post_04_Edit_Post() {
		
	}
	
	@Test
	public void Post_05_Delete_Post() {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() { 
		closeBrowserDriver();
	}
	
	public int getGenerateFakeNumber() {
		Random randomNumber = new Random();
		return randomNumber.nextInt(9999);
	}

	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewPO adminPostAddNewPage;

}
