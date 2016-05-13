package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import admin.communications.announcements.UploadImage;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditProfilePhoto {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By editProfilePhotoPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_EditPhotoDialog']");
	
	/* Button Locators */
	private By browseButtonLocator = By.name("photo");
	private By saveButtonLocator = By.cssSelector("button[data-dojo-attach-point='saveBtn']");
	private By cancelButtonLocator = By.cssSelector("a[data-dojo-attach-point='cancelBtn']");
	private By editProfilePhotoClosePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditPhotoDialog_')]/span[2]/a/img");
	
	public EditProfilePhoto(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditProfilePhotoPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editProfilePhotoPopupLocator, driver);
	}	
	
	public Boolean isBrowseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(browseButtonLocator, driver);
	}
	
	public Boolean isSaveButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(saveButtonLocator, driver);
	}
	
	public Boolean isCancelButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(cancelButtonLocator, driver);
	}
	
	public Boolean isCloseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(editProfilePhotoClosePopupLocator, driver);
	}
	
	public EditProfilePhoto clickBrowse()
	{
		driver.findElement(browseButtonLocator).click();
		return this;
	}
	
	public EditProfilePhoto enterImage(String imageLocation)
	{
		driver.findElement(browseButtonLocator).sendKeys(imageLocation);
		return this;
	}
	
	public void clickSave()
	{
		driver.findElement(saveButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(editProfilePhotoClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
	}
	
	public Profile clickCancel()
	{
		driver.findElement(cancelButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(editProfilePhotoClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public Profile clickClose()
	{
		driver.findElement(editProfilePhotoClosePopupLocator).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(editProfilePhotoClosePopupLocator));
		
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
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editProfilePhotoClosePopupLocator));
		wait.until(ExpectedConditions.elementToBeClickable(editProfilePhotoClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
}
