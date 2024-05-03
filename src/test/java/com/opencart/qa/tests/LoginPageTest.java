package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.qa.basetest.BaseTest;

public class LoginPageTest extends BaseTest {

	@Test
	public void checkForgotPwdAvailable() {
		Assert.assertTrue(lPage.isForgotPasswordAvailable(), "Forgot Password is unavailble");
	}
	
	@Test
	public void checkRegisterAccountSection() {
		Assert.assertTrue(lPage.checkRegisterAccountSection(), "Register Account section is not available");
	}
}

/**
 * Launch Browser Launch application Check forgot password button availability
 * Assert it.
 */
