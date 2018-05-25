package com.qa.quickstart.DemoSite;

import static org.junit.Assert.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DemoSiteTest {

	WebDriver driver;
	WebElement checkElement;

	static ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public static void startReport() {

		report = new ExtentReports(Constants.extentReportLocation, true);

	}

	@Before
	public void setup() {
		ExcelUtils.setExcelFile(Constants.filePath + Constants.fileData, 0);
		System.setProperty(Constants.chromeDriver, Constants.chromeDriverLocation);
		driver = new ChromeDriver();
	}

	@Test
	public void loginTest() throws IOException {

		// FileInputStream file = new FileInputStream(Constants.filePath +
		// Constants.fileData);
		//
		// XSSFWorkbook workbook = new XSSFWorkbook(file);
		//
		// XSSFSheet sheet = workbook.getSheetAt(0);

		test = report.startTest("Verify registration and login");
		
		DemoSiteHomePage homePage = null;
		DemoSiteRegisterPage registerPage = null;
		DemoSiteLoginPage loginPage = null;
		
		

		for (int i = 1; i < ExcelUtils.getExcelWSheet().getPhysicalNumberOfRows(); i++) {

			// Cell username = sheet.getRow(i).getCell(0);
			// Cell password = sheet.getRow(i).getCell(1);
			//
			// String user = username.getStringCellValue();
			// String pass = password.getStringCellValue();

			test.log(LogStatus.INFO, "Browser has started");

			driver.manage().window().maximize();

			test.log(LogStatus.INFO, "Navigating to web page");

			driver.get(Constants.websiteURL);
			
			if(homePage == null) {
			homePage = PageFactory.initElements(driver, DemoSiteHomePage.class);	
			}
			
			
			homePage.goToAddPage();

			test.log(LogStatus.INFO, "Creating a new username");
			
			if(registerPage == null) {
			registerPage = PageFactory.initElements(driver, DemoSiteRegisterPage.class);	
			}
		
			
			registerPage.createUser(ExcelUtils.getCellData(i, 0));

			test.log(LogStatus.INFO, "Creating a new password");

			registerPage.createPass(ExcelUtils.getCellData(i, 1));

			test.log(LogStatus.INFO, "Saving the user details");
			
			registerPage.saveDetails();
			
			registerPage.goToLoginPage();

			test.log(LogStatus.INFO, "Attempting log in with saved details");
			
			if(loginPage == null) {
			loginPage = PageFactory.initElements(driver, DemoSiteLoginPage.class);	
			}
			
			loginPage.insertUser(ExcelUtils.getCellData(i, 0));
			
			loginPage.insertPass(ExcelUtils.getCellData(i, 1));
			
			loginPage.submitDetails();
			
			checkElement = driver
					.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));

			if (checkElement.getText().equals("**Successful Login**")) {

				ExcelUtils.setCellData("Login Successful", i, 2);
				test.log(LogStatus.PASS, "Successfully logged in");

			} else {
				ExcelUtils.setCellData("Fail", i, 2);
				test.log(LogStatus.FAIL, "Login failed");
			}
			assertEquals("**Successful Login**", checkElement.getText());

		}

	}

	@After
	public void teardown() {
		report.endTest(test);
		driver.quit();
	}

	@AfterClass
	public static void endReport() {
		report.flush();

	}

}
