package webUI.home.peopleSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class PeopleSearch {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("div[id^='sls_widgets_views_social_PeopleSearch_'] > div.contentBody > div > h1");
	private By imFollowingTabLocator = By.id("PEOPLE_SEARCH_IM_FOLLOWING");
	private By directoryTabLocator = By.id("PEOPLE_SEARCH_DIRECTORY");
	private By loading = By.cssSelector(".loading");
	
	public PeopleSearch(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}

	public Boolean isImFollowingTabDisplayed()
	{
		return commonMethods.isElementDisplayed(imFollowingTabLocator, driver);
	}
	
	public Boolean isDirectoryTabDisplayed()
	{
		return commonMethods.isElementDisplayed(directoryTabLocator, driver);
	}
	
	public Boolean isImFollowingTabSelected()
	{
		return driver.findElement(imFollowingTabLocator).getAttribute("class").contains("selected");
	}
	
	public Boolean isDirectoryTabSelected()
	{
		return driver.findElement(directoryTabLocator).getAttribute("class").contains("selected");
	}
	
	public Directory clickDirectoryTab()
	{
		driver.findElement(directoryTabLocator).click();
		return new Directory(driver);
	}
	
	public ImFollowing clickImFollowingTab()
	{
		driver.findElement(imFollowingTabLocator).click();
		return new ImFollowing(driver);
	}
	
	public Boolean isLoadingDisplayed()
	{
		return commonMethods.isElementDisplayed(loading, driver);
	}
}
