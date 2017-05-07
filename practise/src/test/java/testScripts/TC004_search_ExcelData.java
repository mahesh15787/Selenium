package testScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageActions.HomePage;
import reports.ExtentReportFactory;
import testBase.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import excelReader.ExcelReader;

public class TC004_search_ExcelData extends TestBase {
	
	ExtentReports report;
	ExtentTest test;
	
	@DataProvider(name="TestData")
	public static Object[][] TestData(){
		return	ExcelReader.getExcelData("sample.xlsx", "SearchData"); 
	}
	
	@BeforeClass
	public void beforeClass(){
		report =ExtentReportFactory.getExtentInstance();
		test = report.startTest("TC003-SearchTest");
		LoadPage();
		test.log(LogStatus.INFO, "Loaded the app");
	}
	
	@Test(dataProvider="TestData")
	public void search(String searchItem){
		HomePage hp = new HomePage(driver);
		if (hp.searchForItem(searchItem)) {
			test.log(LogStatus.PASS, "Search is success: " +searchItem);
		} else {
			test.log(LogStatus.FAIL, "Search is Fail: " +searchItem);
		}
		hp.retutnToHomePage();
	}
	
	@AfterClass
	public void tearDown(){
		closePage();
		report.endTest(test);
		report.flush();
	}
	
}
