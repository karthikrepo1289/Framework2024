package com.opencart.qa.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.opencart.exceptions.FrameworkException;

public class DriverFactory {

	private WebDriver driver;
	private Properties prop;
	private OptionsClass optClass;
	//To make sure every thread gets its own instance of WebDriver during parallel execution.
	private static ThreadLocal<WebDriver> tLocal = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optClass = new OptionsClass(prop);
		System.out.println("Browser name is " + prop.getProperty("browser"));
		switch (prop.getProperty("browser").toLowerCase().trim()) {

		case "chrome":
			driver = new ChromeDriver(optClass.getChromeOption());
			tLocal.set(driver);
			break;
		case "firefox":
			driver = new FirefoxDriver(optClass.getFirefoxOption());
			tLocal.set(driver);
			break;
		case "edge":
			driver = new EdgeDriver(optClass.getEdgeOption());
			tLocal.set(driver);
			break;
		case "safari":
			driver = new SafariDriver();
			tLocal.set(driver);
			break;
		default:
			throw new FrameworkException("Kindly select a valid browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public static  WebDriver getDriver() {
		return tLocal.get();
	}

	public Properties initProperty() {
		FileInputStream fis;
		//Capturing the environment details from maven comman. //mvn clean install -Denv="regression"
		String env = System.getProperty("env");
		System.out.println("Enviornment is "+env);
		prop = new Properties();
		
		if(env == null) {
			try {
				fis = new FileInputStream("./src/test/resource/Configuration/regenv.properties");
				try {
					prop.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			switch(env.toLowerCase().trim()) {
			case "regression" : try {
					fis = new FileInputStream("./src/test/resource/Configuration/regenv.properties");
					try {
						prop.load(fis);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			case "uat" :try {
				fis = new FileInputStream("./src/test/resource/Configuration/uatenv.properties");
				try {
					prop.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			default : new FrameworkException("Please pass the right environment");
			}
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshots/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
	
	
