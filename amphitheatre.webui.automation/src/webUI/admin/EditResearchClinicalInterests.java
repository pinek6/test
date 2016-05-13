package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditResearchClinicalInterests {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By editCurrentPositionPopup = By.cssSelector("div[id^='sls_widgets_components_profile_EditBackgroundDialog']");

	/* Button Locators */
	private By saveButtonLocator = By.id("btnSaveBackground");
	private By cancelButtonLocator = By.id("bgeditCancel");
	private By closePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditBackgroundDialog_')]/span[2]/a/img");
	
	/* Field Locators */
	//private By editBackgroundDetailsLocator = By.cssSelector(".cke_show_borders > p");
	private By editBackgroundDetailsLocator = By.cssSelector("body[class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']");
	private By iframeLocator = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	
	/* CK Editor fields */
	private By ck_bold = By.xpath("//a[contains(@class, '_bold')]");
	private By ck_italics = By.id("cke_15");
	private By ck_underline = By.id("cke_16");
	private By ck_strikethrough = By.id("cke_17");
	
	public EditResearchClinicalInterests(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditBackgroundPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editCurrentPositionPopup,driver);
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
	
	public String getBackgroundDetails()
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
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editBackgroundDetailsLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		/* Get the text and save it to the bgDetails string */
		bgDetails = driver.findElement(editBackgroundDetailsLocator).getText();
		
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
		
		return bgDetails;		
	}

	public EditResearchClinicalInterests enterResearchClinicalInterests(String details)
	{
		/* Switch to the iframe */
		driver.switchTo().frame(driver.findElement(iframeLocator));
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for CKEditor */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editBackgroundDetailsLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		driver.findElement(editBackgroundDetailsLocator).click();
		driver.findElement(editBackgroundDetailsLocator).clear();
		//driver.findElement(editBackgroundDetailsLocator).sendKeys(details);
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
		wait.until(ExpectedConditions.elementToBeClickable(closePopupLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(closePopupLocator));
		
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
