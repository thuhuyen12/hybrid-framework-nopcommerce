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

import pageObject.AddressPageObject;
import pageObject.MyProductReviewPageObject;
import pageObject.PageGeneratorManager;
import pageObject.RewardPointPageObject;
import pageUIs.BasePageUI;
import pageUIs.CustomerInfoPageUI;

public class BasePage { //là các hàm dùng chung cho page object

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
	
	 private By getByXpath(String xpathLocator) {
		 return By.xpath(xpathLocator);
	 }
	 
	 private WebElement getWebElement(WebDriver driver, String xpathLocator ) {
		 return driver.findElement(getByXpath(xpathLocator));
	 }
	 
	 private List<WebElement> getListElements(WebDriver driver, String xpathLocator ) {
		 return driver.findElements(getByXpath(xpathLocator));
	 }
	 
	 
	 protected void clickToElement(WebDriver driver, String xpathLocator) {
		 getWebElement(driver, xpathLocator).click();
	 }
	 
	 protected void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 element.clear();
		 element.sendKeys(textValue);
	 }
	 
	 protected String getElementText(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).getText();
	 }
	 
	 //Chọn item trong default dropdown
	 protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 select.selectByValue(textItem); 
	 }
	 
	 //Lấy item đã chọn ở vị trí đầu tiên
	 protected String getFirstSeclectedItem(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.getFirstSelectedOption().getText(); 
	 }
	 
	 //Dropdown có đc chọn nhiều hay ko
	 protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.isMultiple();
	 }
	 
	 //Custom Dropdown
	 protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
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
	 
	 
	 protected void sleepInSecond(long timeinsecond) {
		try {
			Thread.sleep(timeinsecond * 1000); // vì sleep lấy mili giây
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 
	 protected String getElementAttribute(WebDriver driver, String xpathLocator, String name) {
		 return getWebElement(driver, xpathLocator).getAttribute(name);
	 }
	 
	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
	 
	 protected String getHexaColorFromRgba(String rgbaValue) {
		 return Color.fromString(rgbaValue).asHex();
	 }
	 
	 protected int getElementSize(WebDriver driver, String xpathLocator) {
		 return getListElements(driver, xpathLocator).size();
	 }
	 
	 protected void checkTheCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 if (!element.isSelected()) {
			 element.click();
		} 
	 }
	 
	 protected void uncheckTheCheckbox(WebDriver driver, String xpathLocator) {
		 WebElement element = getWebElement(driver, xpathLocator);
		 if (element.isSelected()) {
			 element.click();
		} 
	 }
	 
	 protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isDisplayed();
	 }
	 
	 protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isSelected();
	 }
	 
	 protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		 return getWebElement(driver, xpathLocator).isEnabled();
	 }
	 
	 protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		 driver.switchTo().frame(getWebElement(driver, xpathLocator));
	 }
	 
	 protected void switchToDefaultContent(WebDriver driver) {
		 driver.switchTo().defaultContent();
	 }
	 
	 protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		 Actions action = new Actions(driver);
		 action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	 }
	 
	 //JS executor


		protected void scrollToBottomPage(WebDriver driver) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		
		protected void highlightElement(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement element = getWebElement(driver, xpathLocator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
		}

		protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
		}

		protected void scrollToElement(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
		}

		
		protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
		}

		protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
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

		protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
		}

		protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
	 
	 protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	 }
	 
	 protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	 }
	 
	 protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		 }
	 
	 protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver,xpathLocator )));
		 }
	 
	 protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
		 }
	 
	 public AddressPageObject openAddressPage(WebDriver driver) {
			waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
			clickToElement(driver, BasePageUI.ADDRESS_LINK);
			return PageGeneratorManager.getAddressPage(driver);
		}
	 
	 public MyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
			waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
			clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
			return PageGeneratorManager.getMyProductReviewPage(driver);
		}
	 public RewardPointPageObject openRewardPointPage(WebDriver driver) {
			waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
			clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
			return PageGeneratorManager.getRewardPointPage(driver);
		}
	 
	 
	 protected long longTimeout = 30;
}
