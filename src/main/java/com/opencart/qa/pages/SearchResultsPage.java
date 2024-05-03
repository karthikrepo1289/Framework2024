package com.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utilities.ElementUtil;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil elmntUtil;
	private By searchPageTitle = By.cssSelector("div#content>h1");
	private By productTitles = By.xpath("//div[@class='product-thumb']//img[@title]");

	public SearchResultsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elmntUtil = new ElementUtil(this.driver);
	}

	public String searchPageTitle() {
		return elmntUtil.doGetText(searchPageTitle);
	}

	public int displayNumOfImages() {
		return elmntUtil.getElementsCount(searchPageTitle);
	}

	public List<String> getProductsDisplayed() {
		List<WebElement> products = elmntUtil.waitforVisibilityOfElementsLocated(productTitles, 5);
		List<String> productTitle = new ArrayList<String>();
		for(WebElement elmnt : products) {
			productTitle.add(elmnt.getText());
		}
		return productTitle;

	}

	public ProductInformationPage checkProductDetails(String productName) {
		elmntUtil.doClick(By.xpath("//a[text()='" + productName + "']"));
		return new ProductInformationPage(driver);
	}
}
