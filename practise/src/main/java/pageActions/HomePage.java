package pageActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']") WebElement searchTextBox;
	@FindBy(xpath="//input[@type='submit']") WebElement searchButton;
	@FindBy(xpath="//select[@id='sort']") WebElement sortComboBox;
	@FindBy(xpath="//a[@class='nav-logo-link']") WebElement homeLink;
	@FindBy(xpath="//a[text()='Sell']") WebElement sellLink;
	
	/**
	 * Parameterized constructor
	 * @param driver
	 */
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * searches for an item returns the search result
	 * @param searchItem
	 * @return
	 */
	public boolean searchForItem(String searchItem){
		setText(searchTextBox, searchItem);
		searchButton.click();
		if (isElementDisplayed(sortComboBox, getProperty("timeOut"))){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean retutnToHomePage(){
		homeLink.click();
		if (isElementDisplayed(sellLink, getProperty("timeOut"))){
			return true;
		} else {
			return false;
		}
	}
}
