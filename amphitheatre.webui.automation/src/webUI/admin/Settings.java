package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.login.ChangePassword;


public class Settings extends Profile {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);

	public Settings(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	/*  Element Locators */
	private By changePasswordButtonLocator = By.id("changePw");
	private By changePasswordPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_ChangePasswordDialog_']");
	private By notificationsCheckbox = By.id("notificationsCheckbox");
	private By accessAdditionalSettingsLink = By.cssSelector(".title3");

	
	public void waitForPageToLoad(WebDriver driver)
	{
		/*  Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(changePasswordButtonLocator));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public ChangePassword clickChangePassword()
	{
		driver.findElement(changePasswordButtonLocator).click();
		return new ChangePassword(driver);
	}

	public Boolean isChangePasswordButtonDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(changePasswordButtonLocator, driver);
	}
	
	public Boolean isChangePasswordPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(changePasswordPopupLocator, driver);
	}
	
	public Boolean isNotificationsCheckboxDisplayed()
	{
		return commonMethods.isElementPresent(notificationsCheckbox, driver);
	}
	
	public Settings selectNotificationsCheckbox()
	{
		driver.findElement(notificationsCheckbox).click();
		return this;
	}
	
	public Boolean isNotificationsCheckboxSelected()
	{
		return commonMethods.isCheckboxSelected(notificationsCheckbox, driver);
	}
}
