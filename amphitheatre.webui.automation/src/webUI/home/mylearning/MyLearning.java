package webUI.home.mylearning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.LocaleStrings;
import webUI.home.mylearning.progress.Progress;


public class MyLearning {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	private String LOADING_CURRICULUM = new LocaleStrings().getString("LOADING_CURRICULUM");
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("div[id='viewContainer'] > div .contentBody > div > h1");	
	private By currentActivitiesTabLocator = By.id("currentActivities");
	private By progressTabLocator = By.id("transcript");
	

	public MyLearning(WebDriver driver)
	{
		this.driver=driver;
		commonMethods.waitForTextNotToBe(headerLocator, LOADING_CURRICULUM, driver);
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}

	public Boolean isCurrentActivitesTabDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(currentActivitiesTabLocator, driver);
	}	
	
	public MyLearning clickCurrentActivitesTab()
	{
		driver.findElement(currentActivitiesTabLocator).click();
		return this;
	}
	
	public Boolean isCurrentActivitesTabSelected()
	{
		return driver.findElement(currentActivitiesTabLocator).getAttribute("class").contains("selected") ? true : false;
	}

	public Boolean isProgressTabDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(progressTabLocator, driver);
	}	
	
	public Progress clickProgressTab()
	{
		driver.findElement(progressTabLocator).click();
		return new Progress(driver);
	}
	
	public Boolean isProgressTabSelected()
	{
		return driver.findElement(progressTabLocator).getAttribute("class").contains("selected") ? true : false;
	}

	
}
