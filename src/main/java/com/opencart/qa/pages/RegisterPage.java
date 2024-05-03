package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.constants.AppConstants;
import com.utilities.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil elmntUtil;

	// Locators information
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By Email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By passWord = By.id("input-password");
	private By confPassword = By.id("input-confirm");
	private By yesRadioButton = By.xpath("//label[text()[normalize-space() = 'Yes']]");
	private By noRadioButton = By.xpath("//label[text()[normalize-space() = 'No']]");
	private By privacyChkBox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By regSuccMsg = By.xpath("//h1[text()='" + AppConstants.registrationSuccessMsg + "']");
	private By logOut = By.linkText(AppConstants.logoutLinkName);

	// Constructor
	public RegisterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elmntUtil = new ElementUtil(driver);
	}

	// Page Actions
	public boolean registerAccount(String firstName, String lastName, String email, String telePhone, String passWord,
			String confirmPassword, String confirmSubscription) {
		elmntUtil.dosendKeysWithWait(this.firstName, 10, firstName);
		elmntUtil.doSendKeys(this.lastName, lastName);
		elmntUtil.doSendKeys(this.Email, email);
		elmntUtil.doSendKeys(this.telephone, telePhone);
		elmntUtil.doSendKeys(this.passWord, passWord);
		elmntUtil.doSendKeys(this.confPassword, confirmPassword);

		if (confirmSubscription.equals("Yes"))
			elmntUtil.doClick(yesRadioButton);
		else
			elmntUtil.doClick(noRadioButton);
		elmntUtil.doClick(privacyChkBox);
		elmntUtil.doClick(continueButton);

		if (elmntUtil.waitForPresenceOfElement(regSuccMsg, 10).isDisplayed()) {
			elmntUtil.doClick(logOut);
			return true;
		} else {
			return false;
		}
	}
}