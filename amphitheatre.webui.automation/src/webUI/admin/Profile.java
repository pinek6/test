package webUI.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.LocaleStrings;


public class Profile {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);

	public Profile(WebDriver driver)
	{
		this.driver=driver;
	}
	
	/*  Element Locators */
	protected String headerText = new LocaleStrings().getString("PROFILE_PAGE_HEADER");
	private By profilePhotoLocator = By.cssSelector(".profileHeroImg");
		
	/* Tabs */
	private By myProfileTabLocator = By.id("Profile");
	private By settingsTabLocator = By.id("Settings");

	public MyProfile clickMyProfileTab()
	{
		driver.findElement(myProfileTabLocator).click();
		return new MyProfile(driver);
	}
	
	public Settings clickSettingsTab()
	{
		driver.findElement(settingsTabLocator).click();
		return new Settings(driver);
	}
	
	public Boolean isMyProfileTabSelected()
	{
		return driver.findElement(myProfileTabLocator).getAttribute("class").equals("pressed") ? true : false;
	}	
	
	public Boolean isSettingsTabSelected()
	{
		return driver.findElement(settingsTabLocator).getAttribute("class").equals("pressed") ? true : false;
	}
	
	public Boolean profilePhotoLoads()
	{
		return commonMethods.isElementDisplayed(profilePhotoLocator, driver);
	}
}
