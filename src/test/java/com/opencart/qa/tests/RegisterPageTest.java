package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.constants.AppConstants;
import com.opencart.qa.basetest.BaseTest;
import com.utilities.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUp() {
		System.out.println("TESTING");
		rgPage = lPage.newRegistration();	
	}
	@DataProvider
	public Object[][] getNewUserDetails() {
		//return new Object[][] { { "QAFN", "QALN", "1234567890", "test", "test", "true" } };
		return ExcelUtil.getDataFromExcel(AppConstants.sheetName);
	}
	
	public String getEmailAddress() {
		return "test"+System.currentTimeMillis()+"@opencart.com";
	}

	@Test(dataProvider = "getNewUserDetails")
	public void registrationPageTest(String firstName, String lastName, String telePhone, String pwd, String confPassword, String subscription) {
		boolean regStatus = rgPage.registerAccount(firstName, lastName, getEmailAddress(), telePhone, pwd, confPassword, subscription.toLowerCase()
				);
	Assert.assertTrue(regStatus,"User is not registered");
	}
}
