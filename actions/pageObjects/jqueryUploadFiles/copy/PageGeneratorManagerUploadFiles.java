package pageObjects.jqueryUploadFiles.copy;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManagerUploadFiles {
	public static HomePageObjectUploadFiles getHomePage(WebDriver driver) {
		return new HomePageObjectUploadFiles(driver);
	}



}
