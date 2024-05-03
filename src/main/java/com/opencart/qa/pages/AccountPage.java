package com.opencart.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utilities.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil elmntUtil;
	private By address_features = By.xpath("//aside[@id='column-right']//a");
	private By address_searchBar = By.name("search");
	private By address_srchBtn = By.xpath("//div[@id='search']//button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elmntUtil = new ElementUtil(this.driver);
	}

	public List<String> checkAccountPageFeatures() {
		return elmntUtil.getElementsText(address_features);
	}
	
	public SearchResultsPage searchProduct(String productName) {
		elmntUtil.dosendKeysWithWait(address_searchBar, 5, productName);
		elmntUtil.doClick(address_srchBtn);
		
		return new SearchResultsPage(driver);
	}
}
