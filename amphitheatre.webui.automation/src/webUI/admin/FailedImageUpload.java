package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class FailedImageUpload {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By failedImageUploadPopupLocator = By.cssSelector("div[id^='sls_widgets_components_global_InformationDialog_']");
	private By errorMessageLocator = By.cssSelector(".slsPopupParagraph.errorMessage");
	
	/* Button Locators */
	private By closeButtonLocator = By.id("closeButtonId");
	private By failedImageUploadedClosePopupLocator = By.xpath("//h5[@id='sls_widgets_components_global_InformationDialog_0_title']/span/a/img");
	
	public FailedImageUpload(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isFailedImageUploadPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(failedImageUploadPopupLocator, driver);
	}	
	
	public Boolean isCloseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(closeButtonLocator, driver);
	}

	public Boolean isCloseDialogButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(failedImageUploadPopupLocator, driver);
	}
	
	public String getErrorMessage()
	{
		commonMethods.waitForElementToBeVisibleAndClickable(failedImageUploadPopupLocator, driver);
		return driver.findElement(errorMessageLocator).getText();
	}
	
	public Profile clickClose()
	{
		driver.findElement(closeButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(failedImageUploadedClosePopupLocator));
		
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
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(failedImageUploadedClosePopupLocator));
		wait.until(ExpectedConditions.elementToBeClickable(failedImageUploadedClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
