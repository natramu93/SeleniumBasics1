package com.yesmsystems.automationframework.keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WebDriverHelper {
static WebDriver driver=null;
static WebDriverWait wait=null;
public static void initialize(String browser)
{
	switch(browser)
	{
	case "gc": case "chrome":
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		break;
	case "ff": case "firefox":
		System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
		driver = new FirefoxDriver();
		break;
	case "ie":
		System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		break;
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	wait = new WebDriverWait(driver, 60);
}
public static void navigate(String url)
{
	Reporter.log("before navigate, url = "+driver.getCurrentUrl());
	driver.get(url);
	Reporter.log("after navigate, url = "+driver.getCurrentUrl());
}
public static void asserturl(String url)
{
	Assert.assertEquals(driver.getCurrentUrl(), url);
}
public static void asserttitle(String title)
{
	Assert.assertEquals(driver.getTitle(), title);
}
public static void click(By locator)
{
	Reporter.log("url before click = "+driver.getCurrentUrl());
	try {
	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	wait.until(ExpectedConditions.elementToBeClickable(locator));
	driver.findElement(locator).click();
	Reporter.log("click per formed on "+locator.toString());
	}
	catch(StaleElementReferenceException e)
	{
		driver.navigate().back();
		click(locator);
	}
	catch(NotFoundException e)
	{
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		click(locator);
	}
	catch(WebDriverException e)
	{
		Actions act = new Actions(driver);
		act.click(driver.findElement(locator)).build().perform();
	}
	Reporter.log("url after click = "+driver.getCurrentUrl());
}
public static void type(By locator, String value)
{
	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	driver.findElement(locator).clear();
	driver.findElement(locator).sendKeys(value);
	Reporter.log("updated "+locator.toString()+" with input value as \""+value+"\"");
}
public static void quit()
{
	driver.quit();
}
}
