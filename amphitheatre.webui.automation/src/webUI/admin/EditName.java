package webUI.admin;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditName {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By editNamePopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_EditNameDialog']");
	private By validationErrorMsg = By.cssSelector(".validationErrorMsg");
	private By validationMessagesLocator = By.cssSelector(".validationErrorList > li");
	
	/* Button Locators */
	private By saveButtonLocator = By.id("btnSaveName");
	private By cancelButtonLocator = By.id("nameEditCancel");
	private By editNameClosePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditNameDialog_')]/span[2]/a/img");
	
	/* Field Locators */
	private By nameLocator = By.id("prefix");
	private By firstLocator = By.id("first");
	private By lastLocator = By.id("last");
	
	public EditName(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditNamePopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editNamePopupLocator, driver);
	}	
	
	public Boolean isNameDisplayed()
	{
		return commonMethods.isElementDisplayed(nameLocator, driver);
	}
	
	public Boolean isFirstDisplayed()
	{
		return commonMethods.isElementDisplayed(firstLocator, driver);
	}

	public Boolean isLastDisplayed()
	{
		return commonMethods.isElementDisplayed(lastLocator, driver);
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
		return commonMethods.isElementDisplayed(editNameClosePopupLocator, driver);
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(editNameClosePopupLocator));
		
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(editNameClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public Profile clickClose()
	{
		driver.findElement(editNameClosePopupLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(editNameClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public EditName enterName(String prefix)
	{
		driver.findElement(nameLocator).clear();
		driver.findElement(nameLocator).sendKeys(prefix);
		return this;
	}
	
	public EditName enterFirst(String first)
	{
		driver.findElement(firstLocator).clear();
		driver.findElement(firstLocator).sendKeys(first);
		return this;
	}

	public EditName enterLast(String last)
	{
		driver.findElement(lastLocator).clear();
		driver.findElement(lastLocator).sendKeys(last);
		return this;
	}
	
	public String getName()
	{
		return driver.findElement(nameLocator).getAttribute("value");
	}
	
	public String getFirst()
	{
		return driver.findElement(firstLocator).getAttribute("value");
	}	

	public String getLast()
	{
		return driver.findElement(lastLocator).getAttribute("value");
	}
	
	public Boolean isValidationErrorMessageVisible()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(validationErrorMsg, driver);
	}
	
	public List<String> getValidationErrorMessages()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(validationMessagesLocator); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }		 
		return l;
	}
	
	public void waitForPopupToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(editNameClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
