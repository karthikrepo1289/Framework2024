package com.opencart.qa.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsClass {

	private Properties prop;
	private ChromeOptions chrmOptions;
	private FirefoxOptions firfoxOptions;
	private EdgeOptions edgeOptions;

	public OptionsClass(Properties prop) {
		this.prop = prop;

	}

	public ChromeOptions getChromeOption() {
		chrmOptions = new ChromeOptions();
		if (Boolean.valueOf(prop.getProperty("headless"))) {
			chrmOptions.addArguments("--headless");
		}
		if (Boolean.valueOf(prop.getProperty("incognito"))) {
			chrmOptions.addArguments("--incognito");
		}

		return chrmOptions;
	}

	public FirefoxOptions getFirefoxOption() {
		firfoxOptions = new FirefoxOptions();
		if (Boolean.valueOf(prop.getProperty("headless"))) {
			chrmOptions.addArguments("--headless");
		}
		if (Boolean.valueOf(prop.getProperty("incognito"))) {
			firfoxOptions.addArguments("--incognito");
		}

		return firfoxOptions;
	}
	
	public EdgeOptions getEdgeOption() {
		edgeOptions = new EdgeOptions();
		if (Boolean.valueOf(prop.getProperty("headless"))) {
			chrmOptions.addArguments("--headless");
		}
		if (Boolean.valueOf(prop.getProperty("incognito"))) {
			edgeOptions.addArguments("--incognito");
		}

		return edgeOptions;
	}
}
