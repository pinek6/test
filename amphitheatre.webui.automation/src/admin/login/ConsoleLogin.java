package admin.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class ConsoleLogin {

	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private String adminLoginURL = new ConfigProperties().getConfigProperties().getProperty("URL") + "admin";
	
	public ConsoleLogin(WebDriver driver)
	{	
		/* Read in the configuration file */
		ConfigProperties config = new ConfigProperties();
		this.driver=driver;
		driver.navigate().to(adminLoginURL);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	/* Element Locators */
	private By usernameLocator = new ConsoleLoginFields().getUserNameLocator();
	private By passwordLocator =  new ConsoleLoginFields().getPasswordLocator();
	private By loginButtonLocator = new ConsoleLoginFields().getLoginButtonLocator();
	
	public void goToLoginPage(WebDriver driver)
	{
		driver.navigate().to(adminLoginURL);
	}
	
	public void enterUsername(String username)
	{
		driver.findElement(usernameLocator).sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		driver.findElement(passwordLocator).sendKeys(password);
	}
	
	public ConsoleLoginAdministration clickLogin()
	{
		driver.findElement(loginButtonLocator).click();
		commonMethods.waitForElementToBeNotVisible(loginButtonLocator, driver);
		return new ConsoleLoginAdministration(driver);
	}	
}
