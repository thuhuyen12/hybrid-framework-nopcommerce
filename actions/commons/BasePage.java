package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage { //là các hàm dùng chung cho page object

	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) { 
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	
	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textAlert) {
		driver.switchTo().alert().sendKeys(textAlert);
	}
	
	//Lấy ra tất cả ID của window
	public void getWindowsHandle(WebDriver driver, String windowId) {
		 Set<String> allWindowsId = driver.getWindowHandles();
		 for (String id : allWindowsId) {
			 if (!id.equals(windowId)) { //Nếu duyệt qua, id ko bằng vs giá trị truyền vào là windowId thì switch tới cửa sổ còn lại
				driver.switchTo().window(id);
			break;
			}	
		 }
	}
	
	public void switchWindowByTitle(WebDriver driver, String windowTitle) {
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
	 public void closeAllWindowsWithoutParents(WebDriver driver, String parentID) {
		 Set<String> allWindowsId = driver.getWindowHandles();
		 for (String id : allWindowsId)
			 if(!id.equals(parentID)) {
				 driver.switchTo().window(id).close();
			 }
		 driver.switchTo().window(parentID);
	 }
	
	 private By getByXpath(String xpathLocator) {
		 return By.xpath(xpathLocator);
	 }
	 
	 private WebElement getWebElement(WebDriver driver, String xpathLocator ) {
		 return driver.findElement(getByXpath(xpathLocator));
	 }
	 
	 private List<WebElement> getListElements(WebDriver driver, String xpathLocator ) {
		 return driver.findElements(getByXpath(xpathLocator));
	 }
	 
	 
	 public void clickToElement(WebDriver driver, String xpathLocator) {
		 getWebElement(driver, xpathLocator).click();
	 }
	 
	 public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 element.clear();
		 element.sendKeys(textValue);
	 }
	 
	 public String getElementText(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).getText();
	 }
	 
	 //Chọn item trong default dropdown
	 public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 select.selectByValue(textItem); 
	 }
	 
	 //Lấy item đã chọn ở vị trí đầu tiên
	 public String getFirstSeclectedItem(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.getFirstSelectedOption().getText(); 
	 }
	 
	 //Dropdown có đc chọn nhiều hay ko
	 public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.isMultiple();
	 }
	 
	 //Custom Dropdown
	 public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				break;
				}
			}
		}
	 
	 
	 public void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond * 1000); // vì sleep lấy mili giây
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 
	 public String getElementAttribute(WebDriver driver, String xpathLocator, String name) {
		 return getWebElement(driver, xpathLocator).getAttribute(name);
	 }
	 
	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
	 
	 public String getHexaColorFromRgba(String rgbaValue) {
		 return Color.fromString(rgbaValue).asHex();
	 }
	 
	 public int getElementSize(WebDriver driver, String xpathLocator) {
		 return getListElements(driver, xpathLocator).size();
	 }
	 
	 public void checkTheCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 if (!element.isSelected()) {
			 element.click();
		} 
	 }
	 
	 public void uncheckTheCheckbox(WebDriver driver, String xpathLocator) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 if (element.isSelected()) {
			 element.click();
		} 
	 }
	 
	 public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isDisplayed();
	 }
	 
	 public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isSelected();
	 }
	 
	 public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isEnabled();
	 }
	 
	 public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		 driver.switchTo().frame(getWebElement(driver, xpathLocator));
	 }
	 
	 public void switchToDefaultContent(WebDriver driver) {
		 driver.switchTo().defaultContent();
	 }
	 
	 public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		 Actions action = new Actions(driver);
		 action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	 }
	 
	 //JS executor


		public void scrollToBottomPage(WebDriver driver) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		
		public void highlightElement(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement element = getWebElement(driver, xpathLocator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
		}

		public void clickToElementByJS(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
		}

		public void scrollToElement(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
		}

		
		public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
		}

		public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						return true;
					}
				}
			};

			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
				}
			};

			return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
		}

		public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
		}

		public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
	 
	 public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	 }
	 
	 public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	 }
	 
	 public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		 }
	 
	 public void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver,xpathLocator )));
		 }
	 
	 public void waitForElementClickable(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
		 }
	 
	 public long longTimeout = 30;
	 public long shortTimeout = 5;
}
