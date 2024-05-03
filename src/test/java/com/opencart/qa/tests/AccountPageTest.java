package com.opencart.qa.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.constants.AppConstants;
import com.opencart.qa.basetest.BaseTest;
import com.opencart.qa.pages.SearchResultsPage;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void loginSetup() {
		accntPage = lPage.signIn(prop.getProperty("username"),prop.getProperty("password"));
		srchPage = new SearchResultsPage(driver);
	}

	@Test(priority =1)
	public void checkFeaturesOnAccntPage() {
		List<String> act = accntPage.checkAccountPageFeatures();
		List<String> exp = AppConstants.accntPageFeatures;
		Collections.sort(act);
		Collections.sort(exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=2)
	public void searchPageTest() {
		srchPage = accntPage.searchProduct("MacBook");
		List<String> productsDisplayed = srchPage.getProductsDisplayed();
		Assert.assertEquals(productsDisplayed, Arrays.asList("MacBook","MacBook Air","MacBook Pro"));
	}
}

