package admin.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import admin.login.ConsoleLoginFields;

public class TopNav {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By topnav = By.cssSelector("frame[name='frame_top']");
	private By logout = By.cssSelector("div[id='adm_header_logout'] > a");
	
	public TopNav(WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public void clickLogout()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, topnav);
		driver.findElement(logout).click();
		//commonMethods.waitForElementToBeNotVisible(logout, driver);
		commonMethods.waitForElementToBeNotVisible(topnav, driver);
		commonMethods.switchBackToMainFrame(driver);
		ConsoleLoginFields l = new ConsoleLoginFields();
		commonMethods.waitForElementToBeVisibleAndClickable(l.getUserNameLocator(), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(l.getPasswordLocator(), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(l.getLoginButtonLocator(), driver);
	}
	
	public Boolean isLogoutDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, topnav);
		Boolean found = commonMethods.isElementDisplayed(logout, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
}
