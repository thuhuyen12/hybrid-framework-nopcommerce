package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	
	protected long longTimeout = 30;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	protected void openPageUrl(WebDriver driver, String pageUrl) { 
		driver.get(pageUrl);
	}
	
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	
	protected void waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	protected void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	protected String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	protected void sendkeyToAlert(WebDriver driver, String textAlert) {
		driver.switchTo().alert().sendKeys(textAlert);
	}
	
	//Lấy ra tất cả ID của window
	protected void getWindowsHandle(WebDriver driver, String windowId) {
		 Set<String> allWindowsId = driver.getWindowHandles();
		 for (String id : allWindowsId) {
			 if (!id.equals(windowId)) { //Nếu duyệt qua, id ko bằng vs giá trị truyền vào là windowId thì switch tới cửa sổ còn lại
				driver.switchTo().window(id);
			break;
			}	
		 }
	}
	
	protected void switchWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindowsTitle = driver.getWindowHandles();
		for(String title : allWindowsTitle) {
			driver.switchTo().window(title);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(windowTitle)) {
				break;
			}
		}
	}
	
	//Đóng tất cả cửa sổ trừ ParentsWindow
	 protected void closeAllWindowsWithoutParents(WebDriver driver, String parentID) {
		 Set<String> allWindowsId = driver.getWindowHandles();
		 for (String id : allWindowsId)
			 if(!id.equals(parentID)) {
				 driver.switchTo().window(id).close();
			 }
		 driver.switchTo().window(parentID);
	 }

		protected void waitForElementVisible(WebDriver driver, WebElement element) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOf(element));
		}

		protected void waitForAllElementsVisible(WebDriver driver, List<WebElement> elements) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
		}

		protected void waitForElementInvisible(WebDriver driver, WebElement element) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOf(element));
		}

		protected void waitForAllElementsInvisible(WebDriver driver, List<WebElement> elements) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
		}

		protected void waitForElementClickable(WebDriver driver, WebElement element) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(element));
		}

		protected void clickToElement(WebDriver driver, WebElement element) {
			element.click();
		 }
		 
		 protected void sendkeyToElement(WebDriver driver, WebElement element, String textValue) {
			 element.clear();
			 element.sendKeys(textValue);
		 }
		 
		 protected String getElementText(WebDriver driver, WebElement element) {
			 return element.getText();
		 }
		 
		 protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
			 return element.isDisplayed();
		 }
















}
