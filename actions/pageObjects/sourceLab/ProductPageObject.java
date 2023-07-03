package pageObjects.sourceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sourceLab.ProductPageUIs;

public class ProductPageObject extends BasePage {
	WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String itemText) {
		waitForElementClickable(driver, ProductPageUIs.PRODUCT_SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUIs.PRODUCT_SORT_DROPDOWN, itemText);
	}

	public boolean isProductNameSortByAscending() {
		//Tạo 1 arrayList (mảng) để lưu product name vào
		ArrayList<String> productName = new ArrayList<>();
		
		//Get text của tất cả các product name
		List<WebElement> productNameList = getListElements(driver, ProductPageUIs.PRODUCT_NAME);
		
		//Duyệt qua produuct name list và add từng name vào mảng productName
		for (WebElement name : productNameList) {
			productName.add(name.getText());
		}
		
		//Copy mảng thành 1 cái mới, để sau khi sort so sánh với cái trước đó
		ArrayList<String> productNameSort = new ArrayList<>();
		for (String child : productName) {
			productNameSort.add(child);
		}
	
		//Sort ASC
		Collections.sort(productNameSort);
		
		//Verify 2 array bằng nhau	
		return productNameSort.equals(productName);
	}

	public boolean isProductNameSortByDescending() {
		ArrayList<String> productName = new ArrayList<>();

		List<WebElement> productNameList = getListElements(driver, ProductPageUIs.PRODUCT_NAME);

		for (WebElement name : productNameList) {
			productName.add(name.getText());
		}

		ArrayList<String> productNameSort = new ArrayList<>();
		for (String child : productName) {
			productNameSort.add(child);
		}

		// Sort ASC
		Collections.sort(productNameSort);
		//Sort DESC
		Collections.reverse(productNameSort);

		return productNameSort.equals(productName);
	}

	public boolean isProductPriceSortByAscending() {
		ArrayList<Float> productPriceUI = new ArrayList<Float>();

		List<WebElement> productPriceList = getListElements(driver, ProductPageUIs.PRODUCT_PRICE);

		for (WebElement price : productPriceList) {
			String productPrice = price.getText();
			//Xoa ki tu $
			productPrice.replace("$", "");
			
			//Convert qua kieu Float
			Float priceFloat = Float.parseFloat(productPrice);
			
			productPriceUI.add(priceFloat);
		}
		
		ArrayList<Float> productPriceSort = new ArrayList<Float>();
		for (Float child : productPriceUI) {
			productPriceSort.add(child);
		}

		// Sort ASC
		Collections.sort(productPriceSort);
		
		return productPriceSort.equals(productPriceUI);
	}

	public boolean isProductPriceSortByDescending() {
		ArrayList<Float> productPriceUI = new ArrayList<Float>();

		List<WebElement> productPriceList = getListElements(driver, ProductPageUIs.PRODUCT_PRICE);

		for (WebElement price : productPriceList) {
			String productPrice = price.getText();
			//Xoa ki tu $
			productPrice.replace("$", "");
			
			//Convert qua kieu Float
			Float priceFloat = Float.parseFloat(productPrice);
			
			productPriceUI.add(priceFloat);
		}
		
		ArrayList<Float> productPriceSort = new ArrayList<Float>();
		for (Float child : productPriceUI) {
			productPriceSort.add(child);
		}

		// Sort ASC
		Collections.sort(productPriceSort);
		//Sort DESC
		Collections.reverse(productPriceSort);

		return productPriceSort.equals(productPriceUI);
	}
}
