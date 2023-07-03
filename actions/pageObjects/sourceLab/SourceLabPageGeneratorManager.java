package pageObjects.sourceLab;

import org.openqa.selenium.WebDriver;

public class SourceLabPageGeneratorManager {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject (driver);
	}
	
	public static ProductPageObject getProductPage(WebDriver driver) {
		return new ProductPageObject (driver);
	}
	
}
