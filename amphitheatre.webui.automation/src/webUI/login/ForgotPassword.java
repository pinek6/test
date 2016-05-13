package webUI.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;


public class ForgotPassword {

	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	public ForgotPassword(WebDriver driver)
	{	
		this.driver=driver;		
	}
	
	/* Element Locators */
	private By enterEmailLocator = By.id("forgotPassword");
	private By resetPasswordButtonLocator = By.id("forgotPasswordBtn");
	private By returnToLoginLinkLocator = By.id("returnToLogin");
	private By errorMessageLocator = By.cssSelector(".messageText.errorMessage");

	
	public void enterUsername(String username)
	{
		driver.findElement(enterEmailLocator).sendKeys(username);
	}
	
	public void clickResetPassword()
	{
		driver.findElement(resetPasswordButtonLocator).click();
	}
	
	public void clickReturntologin()
	{
		driver.findElement(returnToLoginLinkLocator).click();
	}
	
	public Boolean isEnterEmailFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(enterEmailLocator, driver);
	}
	
	public Boolean isResetPasswordButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(resetPasswordButtonLocator, driver);
	}
	
	public Boolean isReturnToLoginLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(returnToLoginLinkLocator, driver);
	}
	
	public String getErrorMessage()
	{
		return driver.findElement(errorMessageLocator).getText();
	}

}
