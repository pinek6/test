package webUI.home.groups;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditBlock {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	private String saveButtonCSS = "button[data-dojo-attach-point='saveBtn']";
	
	/* Element Locators */
	private By saveButtonLocator = By.cssSelector(saveButtonCSS);
	private By cancelButtonLocator = By.cssSelector("a[data-dojo-attach-point='cancelBtn']");
	private By editBlockClosePopupLocator = By.cssSelector(".btnClosePopup");
	private By customContentTitle = By.id("featuredContentEditorTitleEntry");
	private By titleLocator = By.id("titleId");
	private By ckeditor = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	private By html = By.cssSelector("body[class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']");
	
	public EditBlock(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
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
		return commonMethods.isElementDisplayed(editBlockClosePopupLocator, driver);
	}
	
	public void clickSaveButton()
	{
		commonMethods.scrollIntoView(driver, saveButtonCSS);
		driver.findElement(saveButtonLocator).click();
		commonMethods.waitForElementToBeNotVisible(saveButtonLocator, driver);
		commonMethods.waitForElementToBeNotVisible(cancelButtonLocator, driver);
	}
	
	public void clickCancelButton()
	{
		driver.findElement(cancelButtonLocator).click();
	}
	
	public void clickCloseButton()
	{
		driver.findElement(editBlockClosePopupLocator).click();;
	}	
	
	public EditBlock enterCustomContentTitle(String title)
	{
		driver.findElement(customContentTitle).clear();
		driver.findElement(customContentTitle).sendKeys(title);
		return this;
	}	
	
	public EditBlock enterTitle(String title)
	{
		driver.findElement(titleLocator).clear();
		driver.findElement(titleLocator).sendKeys(title);
		return this;
	}	
	
	public EditBlock enterInformation(String info)
	{
		/* Switch to the iframe */
		driver.switchTo().frame(driver.findElement(ckeditor));
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for CKEditor */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(html));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		driver.findElement(html).click();
		driver.findElement(html).clear();
		//driver.findElement(editBackgroundDetailsLocator).sendKeys(details);
		((JavascriptExecutor) driver).executeScript( "var div = document.getElementsByTagName (\"body\")[0];if (div.contentEditable == \"true\") {div.contentEditable = \"false\";var text=div.innerHTML;div.innerHTML = text+arguments[0];}",info);
		
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
		
//		commonMethods.switchToFrame(driver, ckeditor);
//		driver.findElement(html).clear();
//		commonMethods.switchBackToMainFrame(driver);
//		commonMethods.switchToFrame(driver, ckeditor);
//		driver.findElement(html).sendKeys(info);
//		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public void waitForPopupToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(editBlockClosePopupLocator));
		wait.until(ExpectedConditions.elementToBeClickable(ckeditor));
		wait.until(ExpectedConditions.presenceOfElementLocated(ckeditor));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ckeditor));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
