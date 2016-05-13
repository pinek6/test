package webUI.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class Login {

	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	public Login(WebDriver driver)
	{	
		/* Read in the configuration file */
		ConfigProperties config = new ConfigProperties();
		this.driver=driver;
		driver.navigate().to(config.getConfigProperties().getProperty("URL") + "webUI");	
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS); 
	}
	
	/* Element Locators */
	private By usernameLocator = By.id("username");
	private By usernamePlaceholderText = By.cssSelector("input[data-placeholder-shown='true'][id='username']");
	private By passwordLocator = By.id("password");
	private By passwordPlaceholderText = By.cssSelector("input[data-placeholder-shown='true'][id='password']");
	private By loginLocator = By.id("btnLogin");
	private By registerNow = By.cssSelector("a[data-dojo-attach-point='requestAccountAP']");
	private By forgotPasswordLocator = By.id("forgotPswdLink");
	private By errorMessageLocator = By.cssSelector(".messageText.errorMessage");
	private By messageTextLocator = By.cssSelector(".messageText");
	private By backgroundImage = By.tagName("html");
	
	public String getBackgroundImage()
	{
		return driver.findElement(backgroundImage).getCssValue("background-image").toLowerCase();
	}
	
	public void enterUsername(String username)
	{
		commonMethods.waitForElementToBeVisibleAndClickable(usernamePlaceholderText, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(usernameLocator, driver);
		clickUsernameField();
		commonMethods.waitForElementToBeNotVisible(usernamePlaceholderText, driver);
		driver.findElement(usernameLocator).sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		commonMethods.waitForElementToBeVisibleAndClickable(passwordPlaceholderText, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(passwordLocator, driver);
		clickPasswordField();
		commonMethods.waitForElementToBeNotVisible(passwordPlaceholderText, driver);
		driver.findElement(passwordLocator).sendKeys(password);
	}
	
	public ForgotPassword clickForgotPassword()
	{
		driver.findElement(forgotPasswordLocator).click();
		return new ForgotPassword(driver);
	}
	
	public String getErrorMessage()
	{
		return driver.findElement(errorMessageLocator).getText();
	}
	
	public String getMessageText()
	{
		return driver.findElement(messageTextLocator).getText();		
	}
	
	public void clickLogin()
	{
		driver.findElement(loginLocator).click();
	}
	
	public Boolean isUsernameFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(usernameLocator, driver);
	}
	
	public Boolean isPasswordFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(passwordLocator, driver);
	}
	
	public PreRegistration clickRegisterNow()
	{
		driver.findElement(registerNow).click();
		return new PreRegistration(driver);
	}
	
	public Login clickUsernameField()
	{
		driver.findElement(usernameLocator).click();
		return this;
	}
	
	public Login clickPasswordField()
	{
		driver.findElement(passwordLocator).click();
		return this;
	}
	
	
}
