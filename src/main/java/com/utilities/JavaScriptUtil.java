package com.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
	}

	public String getTitleByJS() {
		return js.executeScript("return document.title").toString();
	}

	public String getURLByJS() {
		return js.executeScript("return document.url").toString();
	}

	public void jsAlert(String msg) {
		js.executeScript("alert(" + msg + ")");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert();
	}

	public void jsConfirm(String msg) {
		js.executeScript("confirm(" + msg + ")");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert();
	}

	public void jsPrompt(String msg) {
		js.executeScript("prompt(" + msg + ")");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().alert();
	}

	public void jsScrollDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void jsScrollUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void jsScrollToElement(By locator) {
		WebElement elmnt = driver.findElement(locator);
		js.executeScript("arguments[0].scrollIntoView(true)", elmnt);
	}

	public void jsRefresh() {
		js.executeScript("history.go(0)");
	}

	public void jsBack() {
		js.executeScript("history.go(-1)");
	}

	
	public void jsForward() {
		js.executeScript("history.go(1)");
	}
	
	public void clickJSElement(By locator) {
		WebElement elmnt = driver.findElement(locator);
		js.executeScript("arguments[0].click();", elmnt);
	}
	
	public void jsEnterValue(By locator, String value) {
		WebElement elmnt = driver.findElement(locator);
		js.executeScript("arguments[0].value="+value, elmnt);
	}
}
