package bluebox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class BlueboxHomePage 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By checkYourInboxField = By.id("Email");
	private By submitButton = By.cssSelector("input[value='Submit']");
	
	public BlueboxHomePage (WebDriver driver)
	{
		this.driver=driver;
		driver.navigate().to("http://bluebox.lotus.com/bluebox/index_oneui.jsp");
	}	
	
	public BlueboxHomePage enterEmailAddress(String emailAddress)
	{
		driver.findElement(checkYourInboxField).clear();
		driver.findElement(checkYourInboxField).sendKeys(emailAddress);
		return this;
	}
	
	public BlueboxInbox clickSubmit()
	{
		driver.findElement(submitButton).click();
		return new BlueboxInbox(driver);
	}
}
