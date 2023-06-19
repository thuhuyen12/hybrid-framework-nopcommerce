package pageObjects.jqueryUploadFiles.copy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.uploadFiles.HomePageUIsUploadFile;

public class HomePageObjectUploadFiles extends BasePage{
	WebDriver driver;
	
	public HomePageObjectUploadFiles(WebDriver driver) {
		this.driver = driver;
	}

	
	public boolean isFileLoadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUIsUploadFile.FILE_NAME_LOADING, fileName);
		return isElementDisplayed(driver, HomePageUIsUploadFile.FILE_NAME_LOADING, fileName);
	}

	public void clickStartButtonAtEachFile() {
		List<WebElement> startButtons = getListElements(driver,HomePageUIsUploadFile.START_BUTTON);
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}
		
	}

	public boolean isFileUploadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUIsUploadFile.FILE_NAME_UPLOADED, fileName);
		return isElementDisplayed(driver, HomePageUIsUploadFile.FILE_NAME_UPLOADED, fileName);
	}

	public boolean isFileLinkUploadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUIsUploadFile.FILE_NAME_UPLOADED_LINK, fileName);
		return isImageLoaded(driver, HomePageUIsUploadFile.FILE_NAME_UPLOADED_LINK, fileName);
	}

	
	
	
	
	
}
