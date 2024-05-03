package com.opencart.qa.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.opencart.qa.driverfactory.DriverFactory;
import com.opencart.qa.driverfactory.OptionsClass;
import com.opencart.qa.pages.AccountPage;
import com.opencart.qa.pages.LoginPage;
import com.opencart.qa.pages.ProductInformationPage;
import com.opencart.qa.pages.RegisterPage;
import com.opencart.qa.pages.SearchResultsPage;
import com.utilities.ExcelUtil;

public class BaseTest {
	protected WebDriver driver;
	protected LoginPage lPage;
	protected AccountPage accntPage;
	protected SearchResultsPage srchPage;
	protected ProductInformationPage prodtInfoPage;
	protected RegisterPage rgPage;
	protected Properties prop;
	protected SoftAssert softAssert;
	private DriverFactory driverFactory;
	private ExcelUtil excUtil;
	//private OptionsClass optClass;

	@Parameters({"browserType"}) //If we want to pass the browser type from test runner instead of config.properties.
	@BeforeTest
	public void setUp(String browserName) {
		driverFactory = new DriverFactory();
		prop = driverFactory.initProperty();
		if(browserName != null) {
		prop.setProperty("browser", browserName);
		}//This step is required when we are passing the browser from test runner.
		driver = driverFactory.initDriver(prop);
		//optClass = new OptionsClass(prop);
		softAssert = new SoftAssert();
		
		lPage = new LoginPage(driver);
		
	}

	@AfterTest
	public void tearDown() {
		 driver.close();
	}
}
