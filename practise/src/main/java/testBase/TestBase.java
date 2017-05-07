package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class TestBase {
	
	public WebDriver driver;
	JavascriptExecutor js;
	Actions act;
	static Logger log = Logger.getLogger(TestBase.class);
	
	/**
	 * Clears value in textbox and Enters text in to a textbox.
	 * @param ele
	 * @param text
	 */
	public void setText(WebElement ele, String text){
		ele.clear();
		ele.sendKeys(text);
	}
	/**
	 * Returns whether the element is displayed in page or not
	 * @param ele
	 * @param timeOut
	 * @return
	 */
	public boolean isElementDisplayed(WebElement ele,int timeOut){
		try{
			ele.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void waitForPageLoad(){
		js = (JavascriptExecutor)driver;
		String res = null;
		
		do{
			res= (String) js.executeScript("return document.readyState");
		}while(!res.equalsIgnoreCase("complete"));
		
	}
	
	public void executeJavaScript(String script){
		js = (JavascriptExecutor)driver;
		js.executeScript(script);
	}
	/**
	 * Returns whether alert is present or not
	 * @return
	 */
	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Performs action on alert 
	 * @param decision
	 */
	public void actionOnAlert(boolean decision){
		if (isAlertPresent()){
			Alert alt = driver.switchTo().alert();
			String alertText = alt.getText();
			if (decision){
				alt.accept();
				log.info("Clicked on Accept button for Alert: " +alertText);
			} else{
				alt.dismiss();
				log.info("Clicked on Accept button for Alert: " +alertText);
			}
		}
	}
	/**
	 * Loads Properties file
	 * @param filePath
	 * @return
	 */
	public Properties loadPropertyFile(String filePath){
		try{
			Properties prop = new Properties();
			File f = new File(filePath);
			FileReader fI = new FileReader(f);
			prop.load(fI);
			return prop;
			
		} catch (IOException e) {
			System.out.println("File Not Found");
			return null;
		}		
	}

	public String getStringProprty(String key){
		Properties prop =loadPropertyFile("D:\\Java_WorkSpace\\practise\\src\\main\\java\\config\\config.properties");
		return prop.getProperty(key).toString();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public int getProperty(String key){
		Properties prop =loadPropertyFile("D:\\Java_WorkSpace\\practise\\src\\main\\java\\config\\config.properties");
		return Integer.parseInt(prop.getProperty(key));
	}
	
	public void LoadPage(){
		String browser = getStringProprty("browserType");
		if (browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(getProperty("implictTimeOut"), TimeUnit.SECONDS);
			driver.get(getStringProprty("url"));
		}
	}
	
	public void closePage(){
		driver.quit();
	}
	
	public String getScreenShot(WebDriver driver , String fileName){
		String directory = "D:\\Java_WorkSpace\\practise\\src\\main\\java\\";
		String filePath = directory+fileName+".png";
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file,new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
