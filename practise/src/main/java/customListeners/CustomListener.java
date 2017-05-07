package customListeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testBase.TestBase;

public class CustomListener extends TestBase implements ITestListener{
	WebDriver driver1;

	public void onFinish(ITestContext arg0) {
		
	}

	public void onStart(ITestContext arg0) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("TestCase Failed: "+result.getName() );
		try {
			TestBase tb = new TestBase();
			driver1 = tb.getDriver();
			takeScreenShot(driver1,result.getName());
		} catch (IOException e) {
			System.out.println("Takescreenshot method throws an error");
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("TestCase Skipped: "+result.getName() );
	}

	public void onTestStart(ITestResult result) {
		System.out.println("TestCase Execution Started: "+result.getName() );
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("TestCase Execution finished: "+result.getName() );
	}
	
	public void takeScreenShot(WebDriver driver,String fileName) throws IOException{
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("D:\\Java_WorkSpace\\practise\\src\\main\\java\\screenShots\\"+fileName+".png"));
	}
	
}
