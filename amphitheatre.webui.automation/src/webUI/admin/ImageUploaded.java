package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class ImageUploaded {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By imageUploadedPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_ProfilePictureSuccess_']");
	
	/* Button Locators */
	private By okButtonLocator = By.id("dialogOk");
	private By imageUploadedClosePopupLocator = By.xpath("//h5[@id='sls_widgets_components_profile_ProfilePictureSuccess_0_title']/span/a/img");
	
	public ImageUploaded(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isImageUploadedPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(imageUploadedPopupLocator, driver);
	}	
	
	public Boolean isOkButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(okButtonLocator, driver);
	}

	public Boolean isCloseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(imageUploadedClosePopupLocator, driver);
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(imageUploadedClosePopupLocator));
		
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
		
		/* Wait for elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(imageUploadedClosePopupLocator));
		wait.until(ExpectedConditions.elementToBeClickable(imageUploadedClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
