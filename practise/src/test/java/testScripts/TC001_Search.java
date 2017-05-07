package testScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageActions.HomePage;
import reports.ExtentReportFactory;
import testBase.TestBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC001_Search extends TestBase{
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void beforeClass(){
		report = ExtentReportFactory.getExtentInstance();
		test = report.startTest("TC001_Search");
		LoadPage();
		test.log(LogStatus.INFO, "Home page loaded");
	}

	@Parameters("searchTerm")
	@Test(priority=1)
	public void searchForItem(String searchItem){
		HomePage hp = new HomePage(driver);
		if(hp.searchForItem(searchItem)){
			test.log(LogStatus.PASS, "Search Success");
		} else {
			test.log(LogStatus.ERROR, "Search Fail");
		}
	}
	
	@AfterClass
	public void afterClass(){
		closePage();
		test.log(LogStatus.INFO, "Closed the page");
		report.endTest(test);
		report.flush();
	}
}

