package testScripts;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageActions.HomePage;
import testBase.TestBase;

public class TC002_Search extends TestBase{
	
	@DataProvider(name="TestData")
	public static Object[][] TestData(){
		return new Object[][] {{"Iphone"},{"Apple"}};
	}
	
	@BeforeClass
	public void beforeClass(){
		LoadPage();
	}

	@Test(dataProvider="TestData")
	public void searchForItem(String searchTerm){
		HomePage hp = new HomePage(driver);
		if(hp.searchForItem(searchTerm)){
			Reporter.log("Search pass");
		} else {
			Reporter.log("Search fail");
		}
		hp.retutnToHomePage();
	}
	
	@AfterClass
	public void afterClass(){
		closePage();
	}
}

