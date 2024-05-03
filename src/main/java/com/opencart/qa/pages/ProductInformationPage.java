package com.opencart.qa.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utilities.ElementUtil;

public class ProductInformationPage {
	private WebDriver driver;
	private ElementUtil elmntUtil;
	private By productTitle = By.xpath("//div[@id='content']//h1");
	private By address_productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By address_addToCart_btn = By.id("button-cart");
	private HashMap<String, String> productInfo = new HashMap<String, String>();

	public ProductInformationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elmntUtil = new ElementUtil(this.driver);
	}

	public String displayProudctTitle() {
		return elmntUtil.doGetText(productTitle);
	}

	public HashMap<String, String> getProductMetaData() {
		List<String> elementsText = elmntUtil.getElementsText(address_productMetaData);
		for (String info : elementsText) {
			String productLabel = info.split(":")[0].trim();
			String productValue = info.split(":")[1].trim();
			productInfo.put(productLabel, productValue);
		}
		
		return productInfo;
	}
	
	public void addToCart() {
		elmntUtil.doClick(address_addToCart_btn);
		
	}
}
