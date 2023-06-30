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
	String dashboardUrl;
	String author = "Thu Huyen Admin";
	String userUrl, adminUrl;
	String dateOfPost= getToday();
	String newPostTitle = "The automation class of Huyen" +getGenerateFakeNumber();
	String newPostBody = "Member Thu Huyen";
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
	public void Post_03_Edit_Post() {
		log.info("Edit 01: Open admin Dashboard page"); 
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit 02: Click Post link in Menu"); 
		adminPostSearchPage = adminDashboardPage.clickToPostLink();
		
		log.info("Edit 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit 04: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit 05: Verify Post title is displayed in table ");
		adminPostSearchPage.isPostSearchTableDisplayedInTable("title", postTitle);
		
		log.info("Edit 06: Click to Title of Post"); 
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleInTable("title", postTitle);
		
		log.info("Edit 07: Edit the Title of post");
		adminPostAddNewPage.editTheTitleOfPost(newPostTitle);
		
		log.info("Edit 08: Edit the body of post");
		adminPostAddNewPage.editTheBodyOfPost(newPostBody);
		
		log.info("Edit 09: Click the Update button");
		adminPostAddNewPage.clickUpdateButton();
		
		log.info("Edit 10: Verify message 'Post Updated' is displayed ");
		adminPostAddNewPage.isPostUpdatedMessageDisplayed("Post updated.");
		
		log.info("Edit 11: Open the Post search page");
		adminPostSearchPage = adminPostAddNewPage.openPostSearchPage(postSearchUrl);
		
		log.info("Edit 12: Enter the new title to Search textbox ");
		adminPostSearchPage.enterToSearchTextbox(newPostTitle);
		
		log.info("Edit 13: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit 14: Verify the New post title is displayed ");
		adminPostSearchPage.isPostSearchTableDisplayedInTable("title", newPostTitle);
		
		log.info("Edit 15: Open User home page");
		userHomePage = adminPostSearchPage.openUserHomePage(driver, this.userUrl);
		
		log.info("Edit 16: Verify The new Post info is displayed on Homepage");
		verifyTrue(userHomePage.isPostTitleDisplayedOnHomePage(newPostTitle));
		verifyTrue(userHomePage.isPostBodyDisplayedOnHomePage(newPostBody));
		verifyTrue(userHomePage.isAuthorDisplayedOnHomePage(newPostTitle, author));
		
		log.info("Edit 17: Click to The new Post title on Homepage");
		userPostDetailPage = userHomePage.clickToPostTitleOnHomePage(newPostTitle);
		
		log.info("Edit 18: Verify The new Post info is displayed on Post Detail page");
		verifyTrue(userPostDetailPage.isPostTitleDisplayedOnPostDetailPage(newPostTitle));
		verifyTrue(userPostDetailPage.isPostBodyDisplayedOnPostDetailPage(newPostBody));
		verifyTrue(userPostDetailPage.isAuthorDisplayedOnPostDetailPage(author));
	}
	
	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete 01: Open admin Dashboard page"); 
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete 02: Click Post link in Menu"); 
		adminPostSearchPage = adminDashboardPage.clickToPostLink();
		
		log.info("Delete 03: Click select Checkbox in table");
		adminPostSearchPage.clickSelectCheckboxInTable(newPostTitle);
		
		log.info("Delete 04: Select Action 'Move To Trash' in dropdown");
		adminPostSearchPage.selectActionInDropdown("trash");
		
		log.info("Delete 05: Click to Apply button");
		adminPostSearchPage.clickToApplyButton();
		
		log.info("Delete 06: Verify '1 post moved to the Trash' message is displayed");
		adminPostSearchPage.isPostTitleMovedToTrash("1 post moved to the Trash. ");
		
		log.info("Delete 07: Enter Post deleted title to the Search textbox");
		adminPostSearchPage.enterToSearchTextbox(newPostTitle);
		
		log.info("Delete 08: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete 09: Verify 'No posts found. message is displayed");
		adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found.");
		
		log.info("Delete 10: Open User Site");
		userHomePage = adminPostSearchPage.openUserHomePage(driver, this.userUrl);
		
		log.info("Delete 11: Enter Post deleted title to the Search textbox ");
		userHomePage.enterToSearchTextbox(newPostTitle);
		
		log.info("Delete 12: Click to Search button");
		userHomePage.clickToSearchButton();
		
		log.info("Delete 13: Verify 'Nothing found' message is displayed");
		userHomePage.isNothingFoundMessageDisplayed();
		
		
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
