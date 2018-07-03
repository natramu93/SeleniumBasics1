package com.yesmsystems.automationframework.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yesmsystems.automationframework.keywords.ExcelUtil;
import com.yesmsystems.automationframework.keywords.WebDriverHelper;

import po.GmailHome;
import po.HomePage;
import po.LoginPage;

public class TestCase1 {
@BeforeSuite
public void bs() {
	WebDriverHelper.initialize("gc");
}
@AfterSuite
public void as() {
	WebDriverHelper.quit();
}
@Test(dataProvider="tc1")
public void tc1(String url, String title, String expectation) {
	WebDriverHelper.navigate(url);
	WebDriverHelper.asserttitle(title);
	WebDriverHelper.asserturl(expectation);
	WebDriverHelper.click(HomePage.gmail);
	WebDriverHelper.click(GmailHome.sing_in);
	WebDriverHelper.type(LoginPage.user, "natarajan.test1@gmail.com");
	WebDriverHelper.click(LoginPage.next_btn);
}
@DataProvider(name="tc1")
public Object[][] dp1(){
	ExcelUtil.initialize("Book1.xlsx", "Sheet1");
	return ExcelUtil.datatable(1, 3);
}
}
