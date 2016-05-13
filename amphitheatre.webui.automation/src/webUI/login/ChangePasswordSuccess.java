package webUI.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.admin.Settings;

public class ChangePasswordSuccess {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);	
	
	/* Element Locators */
	private By okButtonLocator = By.id("dialogOk");
	private By changePasswordSuccessMessageLocator = By.cssSelector("#messageHolder > div");

	public ChangePasswordSuccess(WebDriver driver)
	{	
		this.driver=driver;
	}
	
	public Boolean isOkButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(okButtonLocator, driver);
	}
	
	public String getChangePasswordSuccessMessage()
	{
		return driver.findElement(changePasswordSuccessMessageLocator).getText();
	}
	
	public Settings clickOk()
	{
		driver.findElement(okButtonLocator).click();
		return new Settings(driver);
	}

}
