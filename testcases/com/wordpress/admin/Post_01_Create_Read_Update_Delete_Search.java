package com.wordpress.admin;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.WordpressPageGeneratorManager;

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
	String postTitle = "The automation testing class" + getGenerateFakeNumber();
	String postBody = "Member Le Nhat Minh" + getGenerateFakeNumber();
	String postSearchUrl;
	String author = "Thu Huyen Admin";
	String userUrl, adminUrl;
	String dateOfPost= getToday();
	
	@Parameters({"browser", "urlAdmin", "urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin, String urlUser) {
		this.userUrl = urlUser;
		this.adminUrl = urlAdmin;
		log.info("Pre - 01: Open browser and admin URL");
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = WordpressPageGeneratorManager.getAdminLoginPage(driver);
		
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
		verifyTrue(adminPostAddNewPage.isPostPublishedMessageDisplayed("Post published."));
	
	}
	
	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search 01: Open Post Search Page URL");
		adminPostSearchPage = adminPostAddNewPage.openPostSearchPage(postSearchUrl); 
		
		log.info("Search 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search 03: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search 04: Verify Post title is displayed in table");
		adminPostSearchPage.isPostSearchTableDisplayedInTable("title", postTitle);
		
		log.info("Search 05: Verify Author is displayed in table");
		adminPostSearchPage.isPostSearchTableDisplayedInTable("author", author);
		
		log.info("View 01: Open User home page");
		userHomePage = adminPostSearchPage.openUserHomePage(driver, this.userUrl);
	
		log.info("View 02: Verify Post info is displayed on Homepage");
		verifyTrue(userHomePage.isPostTitleDisplayedOnHomePage(postTitle));
		verifyTrue(userHomePage.isPostBodyDisplayedOnHomePage(postBody));
		verifyTrue(userHomePage.isAuthorDisplayedOnHomePage(postTitle, author));
		verifyTrue(userHomePage.isDateOfPostDisplayedOnHomePage(postTitle, dateOfPost));

		log.info("View 03: Click to Post title on Homepage");
		userPostDetailPage = userHomePage.clickToPostTitleOnHomePage(postTitle);
		
		log.info("View 04: Verify Post info is displayed on Post Detail page");
		verifyTrue(userPostDetailPage.isPostTitleDisplayedOnPostDetailPage(postTitle));
		verifyTrue(userPostDetailPage.isPostBodyDisplayedOnPostDetailPage(postBody));
		verifyTrue(userPostDetailPage.isAuthorDisplayedOnPostDetailPage(author));
		verifyTrue(userPostDetailPage.isDateOfPostDisplayedOnPostDetailPage(dateOfPost));
		
	}


	@Test
	public void Post_03_View_Post() {
		
	}

	@Test
	public void Post_03_Edit_Post() {
		
	}
	
	@Test
	public void Post_04_Delete_Post() {
		
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
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
}
