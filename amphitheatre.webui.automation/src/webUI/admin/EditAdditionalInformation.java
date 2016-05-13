package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class EditAdditionalInformation {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By editAdditionalInfoPopup = By.cssSelector("div[id^='sls_widgets_components_profile_EditAdditionalInfoDialog_']");

	/* Button Locators */
	private By saveButtonLocator = By.id("btnSaveAdditionalInfo");
	private By cancelButtonLocator = By.id("additionalInfoCancel");
	private By closePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditAdditionalInfoDialog_')]/span[2]/a/img");
	
	/* Field Locators */
	//private By editBackgroundDetailsLocator = By.cssSelector(".cke_show_borders > p");
	private By editAdditionalInfoDetailsLocator = By.cssSelector("body[class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']");
	private By iframeLocator = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	
	/* CK Editor fields */
	private By ck_bold = By.xpath("//a[contains(@class, '_bold')]");
	private By ck_italics = By.id("cke_15");
	private By ck_underline = By.id("cke_16");
	private By ck_strikethrough = By.id("cke_17");
	
	public EditAdditionalInformation(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditAdditionalInfoPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editAdditionalInfoPopup,driver);
	}	
	
	public Boolean isSaveButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(saveButtonLocator,driver);
	}
	
	public Boolean isCancelButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(cancelButtonLocator,driver);
	}
	
	public Boolean isCloseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(closePopupLocator,driver);
	}
	
	public String getAdditionalInfoDetails()
	{
		String bgDetails = new String();
		
		/* Switch to the iframe */
		driver.switchTo().frame(driver.findElement(iframeLocator));
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editAdditionalInfoDetailsLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		/* Get the text and save it to the bgDetails string */
		bgDetails = driver.findElement(editAdditionalInfoDetailsLocator).getText();
		
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
		
		return bgDetails;		
	}

	public EditAdditionalInformation enterAdditionalInfoDetails(String details)
	{
		/* Switch to the iframe */
		driver.switchTo().frame(driver.findElement(iframeLocator));
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for CKEditor */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editAdditionalInfoDetailsLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		driver.findElement(editAdditionalInfoDetailsLocator).click();
		driver.findElement(editAdditionalInfoDetailsLocator).clear();
		//driver.findElement(editAdditionalInfoDetailsLocator).sendKeys(details);
		((JavascriptExecutor) driver).executeScript( "var div = document.getElementsByTagName (\"body\")[0];if (div.contentEditable == \"true\") {div.contentEditable = \"false\";var text=div.innerHTML;div.innerHTML = text+arguments[0];}",details);
		
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
		
		return this;
	}

	public Profile clickSave()
	{
		driver.findElement(saveButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(closePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(closePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public Profile clickClose()
	{
		driver.findElement(closePopupLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(closePopupLocator));
		
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
		wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(saveButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButtonLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void clickBold()
	{
		driver.findElement(ck_bold).click();
	}
	
	public void clickItalics()
	{
		driver.findElement(ck_italics).click();
	}
	
	public void clickUnderline()
	{
		driver.findElement(ck_underline).click();
	}
	
	public void clickStrikethrough()
	{
		driver.findElement(ck_strikethrough).click();
	}

}
