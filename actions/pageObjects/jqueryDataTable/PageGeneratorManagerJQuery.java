package pageObjects.jqueryDataTable;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManagerJQuery {
	public static HomePageObjectJQuery getHomePage(WebDriver driver) {
		return new HomePageObjectJQuery(driver);
	}



}
