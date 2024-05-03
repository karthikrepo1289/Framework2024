package com.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencart.exceptions.FrameworkException;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public By getBylocator(String locatorType, String locatorAddress) {
		By locator = null;
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			locator = By.id(locatorAddress);
			break;
		case "name":
			locator = By.name(locatorAddress);
			break;
		case "xpath":
			locator = By.xpath(locatorAddress);
			break;
		case "class":
			locator = By.className(locatorAddress);
			break;
		case "css":
			locator = By.cssSelector(locatorAddress);
			break;
		case "linktext":
			locator = By.linkText(locatorAddress);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorAddress);
			break;
		default:
			throw new FrameworkException("Invalid locator");
		}

		return locator;
	}

	public WebElement getElement(String locatorType, String locatoAddress) {
		return driver.findElement(getBylocator(locatorType, locatorType));
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public boolean checkSingleElementPresent(By locator) {
		return getElements(locator).size() == 1 ? true : false;
	}

	public boolean checkElementsPresent(By locator) {
		return getElements(locator).size() >= 1 ? true : false;
	}

	public List<String> getElementsText(By locator) {
		List<WebElement> totElements = getElements(locator);
		List<String> arr = new ArrayList<String>();

		for (WebElement elmnt : totElements) {
			if (elmnt.getText().length() != 0) {
				arr.add(elmnt.getText());
			}
		}
		return arr;
	}

	public List<String> getElementsAttributeList(By locator, String attributeName) {
		List<WebElement> totElements = getElements(locator);
		List<String> arr = new ArrayList<String>();

		for (WebElement elmnt : totElements) {
			arr.add(elmnt.getAttribute(attributeName));
		}
		return arr;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorAddress, String value) {
		getElement(getBylocator(locatorType, locatorAddress)).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	public void doSearchAndClickSuggestion(By srchBox, By srchElmnts, String srchKey, String expLinkToClick) {
		doSendKeys(srchBox, srchKey);
		List<WebElement> results = getElements(srchElmnts);

		for (WebElement elmnt : results) {
			if (elmnt.getText().length() != 0 && elmnt.getText().equals(expLinkToClick)) {
				elmnt.click();
				break;
			}
		}
	}

	public void clickOnElement(By locator, String expectedItemToClick) {
		List<WebElement> lnks = getElements(locator);
		// System.out.println("Languages supported by google are :");
		for (WebElement lnk : lnks) {
			if (lnk.getText().equals(expectedItemToClick)) {
				lnk.click();
				break;
			}
		}
	}

	public void selectDropDownValue(By locator, String valueToSelect) {
		List<WebElement> allOptions = getElements(locator);

		for (WebElement option : allOptions) {
			if (option.getText().equals(valueToSelect)) {
				option.click();
				break;
			}
		}
	}

	/********************************************************************************************************
	 * Drop Down Utilities
	 *****************************************************/

	private Select createSelect(By locator) {
		Select slct = new Select(getElement(locator));
		return slct;
	}

	public void selectElementByValue(By locator, String valueToSelect) {

		createSelect(locator).selectByValue(valueToSelect);
	}

	public void selectElementByIndex(By locator, int index) {
		createSelect(locator).selectByIndex(index);
	}

	public void selectElementByVisibleText(By locator, String visibleText) {
		createSelect(locator).selectByVisibleText(visibleText);
	}

	public int getDropDownOptionsCount(By locator) {
		return createSelect(locator).getOptions().size();
	}

	public List<String> getDropDownValues(By locator) {
		Select dropDown = new Select(getElement(locator));
		List<WebElement> elmnts = dropDown.getOptions();
		List<String> options = new ArrayList<String>();

		for (WebElement option : elmnts) {
			options.add(option.getText());
		}
		return options;
	}

	/***************************************************************************************************
	 * Multi Dropdown
	 ************************************************************************************************
	 */
	public boolean isDropDownMultiple(By locator) {
		Select slct = new Select(getElement(locator));
		return slct.isMultiple();
	}

	/**
	 * This method is used to select values from multi selection drop down. 1.
	 * Support single selection 2. Support multi selection 3. Select all elements :
	 * Please pass "all" if you want to select all elements
	 */
	public void selectDropDownMulValues(By locator, String... valuesListToSelect) {
		Select slct = new Select(getElement(locator));
		if (isDropDownMultiple(locator) && valuesListToSelect[0].equalsIgnoreCase("all")) {
			slct.getOptions().forEach(e -> e.click());
		} else {
			for (String value : valuesListToSelect) {
				slct.selectByVisibleText(value);
			}
		}
	}

	/**********************************************************************************************************************
	 * User Interactions - Keyboard and Mouse Actions using "Acions" class
	 **********************************************************************************************************************
	 */

	public void actionsMenuAndSubMenu(By mainMenu, By subMenu) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(mainMenu)).click(getElement(subMenu)).build().perform();
	}

	public void doactionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	// Move to element and click
	public void doactionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public void doactionsSendKeyswithPause(By locator, String value) {
		Actions act = new Actions(driver);
		char[] c = value.toCharArray();
		for (char m : c) {
			act.sendKeys(getElement(locator), String.valueOf(m)).pause(500).build().perform();
		}
	}

	/*******************************************************************************************************
	 * Explicitly Wait - WebDriverWait and FluentWait
	 **********************************************************************************************************/
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForPresenceOfElement(By locator, int timeOut, int pollingInterval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),
				Duration.ofSeconds(pollingInterval));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForVisibilityOfElement(By locator, int timeOut, int pollingInterval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),
				Duration.ofSeconds(pollingInterval));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void doClickWithWait(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
	}

	public void dosendKeysWithWait(By locator, int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
	}

	public List<WebElement> waitforVisibilityOfElementsLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitforPresenceOfElementsLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public String waitForTitleContains(int timeOut, String titleToCheck) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {

			if (wait.until(ExpectedConditions.titleContains("titleToCheck"))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Unable to find the title");
		}
		return "";
	}

	public String waitForTitleIS(int timeOut, String titleToCheck) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {

			if (wait.until(ExpectedConditions.titleIs(titleToCheck))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Unable to find the title");
		}
		return "";
	}

	public String waitForURLContains(int timeOut, String titleToCheck) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(titleToCheck))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println("Unable to find the url");
		}
		return "";
	}

	public String waitForURLToBe(int timeOut, String titleToCheck) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(titleToCheck))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println("Unable to find the url");
		}
		return "";
	}

	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException e) {
			System.out.println("Alert is not present");
		}
		return null;
	}

	public void acceptJSAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.alertIsPresent()).accept();
		} catch (TimeoutException e) {
			System.out.println("Alert is not present");
		}
	}

	public void dismissJSAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		} catch (TimeoutException e) {
			System.out.println("Alert is not present");
		}
	}

	public String getJSAlertText(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.alertIsPresent()).getText();
		} catch (TimeoutException e) {
			System.out.println("Alert is not present");
		}
		return "";
	}

	public void enterValueOnJSAlert(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.alertIsPresent()).sendKeys(value);
		} catch (TimeoutException e) {
			System.out.println("Alert is not present");
		}
	}

	public String waitForFrameByLocator(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		} catch (TimeoutException e) {
			System.out.println("Unable to find the frame");
		}
		return "";
	}

	public String waitForFrameByIndex(int timeOut, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		} catch (TimeoutException e) {
			System.out.println("Unable to find the frame");
		}
		return "";
	}

	public String waitForFrameByStringID(int timeOut, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		} catch (TimeoutException e) {
			System.out.println("Unable to find the frame");
		}
		return "";
	}

	public String waitForFrameByWebElement(int timeOut, WebElement elmnt) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elmnt));
		} catch (TimeoutException e) {
			System.out.println("Unable to find the frame");
		}
		return "";
	}

	public boolean waitForWindowsToOpen(int timeOut, int numberOfWindowsExpected) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindowsExpected));
		} catch (TimeoutException e) {
			System.out.println("Window is not present");
		}
		return false;
	}

	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e) {
			System.out.println("Unable to find the element");
		}
	}

	/***************************************************************************************************
	 * Fluent Wait
	 ****************************************************************************************************/
	public WebElement waitForElementWithFluentWait(By locator, int totalTimeOut, int pollingTme, String errMessage) {
		Wait wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(totalTimeOut))
				.pollingEvery(Duration.ofSeconds(pollingTme)).ignoring(NoSuchElementException.class)
				.withMessage(errMessage);

		return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/************************************************************************************************
	 * Custom Wait
	 ***********************************************************************************************/
	public WebElement retryElement(By locator, int totalAttempts) {
		int attempt = 0;
		WebElement elmnt = null;

		while (attempt < totalAttempts) {
			try {
				elmnt = getElement(locator);
				System.out.println("able to find the element and number of attempts made is " + attempt);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("Element is not found and current attempt is " + attempt);

				try {
					Thread.sleep(1000); // polling time
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			attempt++;
		}

		if (elmnt == null) {
			System.out.println("Unable to locate the element");
			throw new FrameworkException("Unable to find the elment");
		}
		return elmnt;
	}

	public WebElement retryElement(By locator, int totalAttempts, int pollingTime) {
		int attempt = 0;
		getElement(locator);
		WebElement elmnt = null;

		while (attempt < totalAttempts) {
			try {
				elmnt = getElement(locator);
				System.out.println("able to find the element and number of attempts made is " + attempt);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("Element is not found and current attempt is " + attempt);

				try {
					Thread.sleep(pollingTime); // polling time
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			attempt++;
		}

		if (elmnt == null) {
			System.out.println("Unable to locate the element");
			throw new FrameworkException("Unable to find the elment");
		}
		return elmnt;
	}

	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String state= wait.until(ExpectedConditions.jsReturnsValue("document.readyState === 'complete'")).toString();
		return Boolean.valueOf(state);
	}
	
	
}