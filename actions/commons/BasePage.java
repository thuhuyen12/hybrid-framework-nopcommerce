package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.user.AddressPageObject;
import pageObject.nopCommerce.user.CustomerInfoPageObject;
import pageObject.nopCommerce.user.HomePageObject;
import pageObject.nopCommerce.user.MyProductReviewPageObject;
import pageObject.nopCommerce.user.RewardPointPageObject;
import pageUIs.jquery.uploadFiles.HomePageUIsUploadFile;
import pageUIs.user.BasePageUI;
import pageUIs.user.BasePageUploadFileUI;
import pageUIs.user.CustomerInfoPageUI;

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
	
	 
	 //locatorType: id= /name= /class= /css= /xpath=
	 private By getByLocator (String locatorType) {
		 By by = null;
		 if (locatorType.startsWith("id=")||locatorType.startsWith("ID=") ||locatorType.startsWith("Id=")) {
			 locatorType = locatorType.substring(3);
			 by = By.id(locatorType);
		} else if (locatorType.startsWith("class=")||locatorType.startsWith("CLASS=") ||locatorType.startsWith("Class=")) {
			locatorType = locatorType.substring(6);
			by = By.className(locatorType);
		} else if (locatorType.startsWith("name=")||locatorType.startsWith("NAME=") ||locatorType.startsWith("Name=")) {
			locatorType = locatorType.substring(5);
			by = By.name(locatorType);
		} else if (locatorType.startsWith("css=")||locatorType.startsWith("CSS=") ||locatorType.startsWith("Css=")) {
			locatorType = locatorType.substring(4);
			by = By.cssSelector(locatorType);
		} else if (locatorType.startsWith("xpath=")||locatorType.startsWith("XPATH=") ||locatorType.startsWith("Xpath=")) {
			locatorType = locatorType.substring(6);
			by = By.xpath(locatorType);
		} else {
			throw new RuntimeException("Locator type is not support!");
		}
		return by;	 
	 }
	 
	 private String getDynamicXpath(String locatorType, String...values) {
		 if (locatorType.startsWith("xpath=")||locatorType.startsWith("XPATH=") ||locatorType.startsWith("Xpath=")) {
			 locatorType = String.format(locatorType, (Object[]) values);
		} 
		 return locatorType;
	 }
	 
	 
	 private WebElement getWebElement(WebDriver driver, String locatorType ) {
		 return driver.findElement(getByLocator(locatorType));
	 }
	 
	 public List<WebElement> getListElements(WebDriver driver, String locatorType ) {
		 return driver.findElements(getByLocator(locatorType));
	 }
	 
	 
	 public void clickToElement(WebDriver driver, String locatorType) {
		 getWebElement(driver, locatorType).click();
	 }
	 
	 public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		 getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	 }
	 
	 public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		 WebElement element = getWebElement(driver, locatorType);
		 element.clear();
		 element.sendKeys(textValue);
	 }
	 public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String...dynamicValues) {
		 WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		 element.clear();
		 element.sendKeys(textValue);
	 }
	 public String getElementText(WebDriver driver, String locatorType) {
		 return getWebElement(driver, locatorType).getText();
	 }
	 public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		 return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	 }
	 //Chọn item trong default dropdown
	 public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		 Select select = new Select(getWebElement(driver, locatorType));
		 select.selectByValue(textItem); 
	 }
	 public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String...dynamicValues) {
		 Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		 select.selectByValue(textItem); 
	 }
	 //Lấy item đã chọn ở vị trí đầu tiên
	 public String getFirstSeclectedItem(WebDriver driver, String locatorType) {
		 Select select = new Select(getWebElement(driver, locatorType));
		 return select.getFirstSelectedOption().getText(); 
	 }
	 
	 //Dropdown có đc chọn nhiều hay ko
	 public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		 Select select = new Select(getWebElement(driver, locatorType));
		 return select.isMultiple();
	 }
	 
	 //Custom Dropdown
	 public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

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
	 
	 public String getElementAttribute(WebDriver driver, String locatorType, String name) {
		 return getWebElement(driver, locatorType).getAttribute(name);
	 }
	 
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	 
	 public String getHexaColorFromRgba(String rgbaValue) {
		 return Color.fromString(rgbaValue).asHex();
	 }
	 
	 public int getElementSize(WebDriver driver, String locatorType) {
		 return getListElements(driver, locatorType).size();
	 }
	 public int getElementSize(WebDriver driver, String locatorType, String...dynamicValues ) {
		 return getListElements(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	 }
	 public void checkTheCheckboxOrRadio(WebDriver driver, String locatorType) {
		 WebElement element = getWebElement(driver, locatorType);
		 if (!element.isSelected()) {
			 element.click();
		} 
	 }
	 
	 public void uncheckTheCheckbox(WebDriver driver, String locatorType) {
		 WebElement element = getWebElement(driver, locatorType);
		 if (element.isSelected()) {
			 element.click();
		} 
	 }
	 
	 public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		 return getWebElement(driver, locatorType).isDisplayed();
	 }
	 public boolean isElementDisplayed(WebDriver driver, String locatorType, String...dynamicValues) {
		 return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	 }
	 
	 public boolean isElementSelected(WebDriver driver, String locatorType) {
		 return getWebElement(driver, locatorType).isSelected();
	 }
	 
	 public boolean isElementEnabled(WebDriver driver, String locatorType) {
		 return getWebElement(driver, locatorType).isEnabled();
	 }
	 
	 public void switchToFrameIframe(WebDriver driver, String locatorType) {
		 driver.switchTo().frame(getWebElement(driver, locatorType));
	 }
	 
	 public void switchToDefaultContent(WebDriver driver) {
		 driver.switchTo().defaultContent();
	 }
	 
	 public void hoverMouseToElement(WebDriver driver, String locatorType) {
		 Actions action = new Actions(driver);
		 action.moveToElement(getWebElement(driver, locatorType)).perform();
	 }
	 public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		 Actions action = new Actions(driver);
		 action.sendKeys(getWebElement(driver, locatorType), key).perform();
	 }
	 
	 public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dynamicValues) {
		 Actions action = new Actions(driver);
		 action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	 }
	 //JS executor


		public void scrollToBottomPage(WebDriver driver) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		
		public void highlightElement(WebDriver driver, String locatorType) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement element = getWebElement(driver, locatorType);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
		}

		public void clickToElementByJS(WebDriver driver, String locatorType) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
		}

		public void scrollToElement(WebDriver driver, String locatorType) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
		}

		
		public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
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

		public String getElementValidationMessage(WebDriver driver, String locatorType) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
		}

		public boolean isImageLoaded(WebDriver driver, String locatorType) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
			if (status) {
				return true;
			} else {
				return false;
			}
		}

		public boolean isImageLoaded(WebDriver driver, String locatorType, String...dynamicValues) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
	 public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	 }
	 public void waitForElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		 }
	 public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	 }
	 public void waitForAllElementsVisible(WebDriver driver, String locatorType, String...dynamicValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		 }
		 
	 public void waitForElementInvisible(WebDriver driver, String locatorType) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		 }
	 
	 public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver,locatorType )));
		 }
	 
	 public void waitForElementClickable(WebDriver driver, String locatorType) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
		 }
	 public void waitForElementClickable(WebDriver driver, String locatorType, String...dynamicValues) {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		 }
	 
	 //Level -07
	 public CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
			waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
			clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
			return PageGeneratorManager.getCustomerInfoPage(driver);
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
	 
	 //Level -09 - Cách 1
	 public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		 waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT, pageName);
		 clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT, pageName);
		 switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getAddressPage(driver);
		case "Reward points":
			return PageGeneratorManager.getRewardPointPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getMyProductReviewPage(driver);
		default:
			throw new RuntimeException("Invalid Page name at My account area");
		}
	 }
	 // Level -09- Cách 2
	 public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		 waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT, pageName);
		 clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT, pageName);
	 }
	 
	public HomePageObject clickLogoutAtUserPage(WebDriver driver) {
		 waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		 clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		 return PageGeneratorManager.getHomePage(driver);
	 }


	public AdminLoginPageObject clickLogoutAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	 }
	
	//Upload File
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		//Đường dẫn của thư mục Upload file: Win/Mac/Linux
		String filePath = GlobalConstants.UPLOAD_FILE ;
		
		//Đường dẫn của tất cả file
		String fullFileName = "";
		
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		
		fullFileName = fullFileName.trim();//Cắt bỏ khoảng trắng
		getWebElement(driver, HomePageUIsUploadFile.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	
	 public long longTimeout = GlobalConstants.LONG_TIMEOUT;
}
