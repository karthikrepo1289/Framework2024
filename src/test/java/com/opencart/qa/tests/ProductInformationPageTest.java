package com.opencart.qa.tests;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.basetest.BaseTest;

public class ProductInformationPageTest extends BaseTest {

	@BeforeClass
	public void searchSetUp() {
		accntPage = lPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
				
	}

	@DataProvider
	public void getProductName() {
		Object[][] data = new Object[][] { {"MacBook", "Apple", "Product 15", "600", "In Stock" } };
	}

	@Test
	public void validateProductMetaData(String productName, String brand, String productCode, String price, String availability) {
		
		prodtInfoPage=accntPage.searchProduct(productName).checkProductDetails(productName);
		HashMap<String, String> productMetaData = prodtInfoPage.getProductMetaData();
		softAssert.assertEquals(productMetaData.get("Brand"), brand);
		softAssert.assertEquals(productMetaData.get("Product Code"), productCode);
		softAssert.assertEquals(productMetaData.get("Reward Points"), price);
		softAssert.assertEquals(productMetaData.get("Availability"), availability
				);
		softAssert.assertAll();
	}
}
