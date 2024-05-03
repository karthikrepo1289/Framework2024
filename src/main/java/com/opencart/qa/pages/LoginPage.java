package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.utilities.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil util;

	// Locators information
	private By address_userName = By.id("input-email");
	private By address_password = By.id("input-password");
	private By address_login = By.xpath("//input[@value='Login']");
	private By address_forgotPwd = By.xpath("//input//following-sibling::a[contains(text(),'Password')]");
	private By address_register = By.xpath("//strong[text()='" + AppConstants.registerAccount + "']");
	private By registerLink = By.linkText(AppConstants.registerLinkName);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public boolean isForgotPasswordAvailable() {
		return util.getElement(address_forgotPwd).isDisplayed();
	}

	public boolean checkRegisterAccountSection() {
		return util.waitForPresenceOfElement(address_register, 5).isDisplayed();
	}

	public AccountPage signIn(String userName, String pwd) {
		System.out.println("User Name is "+userName);
		util.dosendKeysWithWait(address_userName, 5, userName);
		util.dosendKeysWithWait(address_password, 5, pwd);
		util.doClick(address_login); // Lands on the Account page

		return new AccountPage(driver);
	}
	
	public RegisterPage newRegistration() {
		util.doClickWithWait(registerLink, 10);
		
		return new RegisterPage(driver);
	}
}
