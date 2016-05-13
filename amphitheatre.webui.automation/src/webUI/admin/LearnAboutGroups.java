package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class LearnAboutGroups {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By LearnAboutGroupsPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_LearnAboutGroups_']");
	
	/* Button Locators */
	private By okButtonLocator = By.id("groupsOk");
	private By dialogClosePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_LearnAboutGroups_')]/span[2]/a/img");
	
	public LearnAboutGroups(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isLearnAboutGroupsPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(LearnAboutGroupsPopupLocator, driver);
	}
	
	public Boolean isOkButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(okButtonLocator, driver);
	}
	
	public Boolean isCloseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(dialogClosePopupLocator, driver);
	}
	
	public Profile clickOk()
	{
		driver.findElement(okButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public Profile clickClose()
	{
		driver.findElement(dialogClosePopupLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public void waitForPopupToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(dialogClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
