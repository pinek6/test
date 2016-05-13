package webUI.admin;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditPosition {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By editCurrentPositionPopup = By.cssSelector("div[id^='sls_widgets_components_profile_EditTitleDialog']");
	private By validationErrorMsg = By.cssSelector(".validationErrorMsg");
	private By validationMessagesLocator = By.cssSelector(".validationErrorList > li");
	
	/* Button Locators */
	private By saveButtonLocator = By.id("btnSavePositionInfo");
	private By cancelButtonLocator = By.id("editPositionInfoCancel");
	private By closePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditTitleDialog_')]/span[2]/a/img");
	
	/* Field Locators */
	private By titleLocator = By.id("currentTitle");
	private By organizationLocator = By.id("currentOrganization");
	private By locationLocator = By.id("currentLocation");
	private By additionalTitlesLocator = By.xpath("//div[@id='editPositionPopup']/form/div[4]/textarea");  //TODO Should have an id/name
	private By countryComboBoxLocator = By.id("countries");
	private By cityComboBoxLocator = By.id("city");
	private By organizationComboBoxLocator = By.id("hospital");
	private By otherCityLocator = By.id("otherCityText");
	private By otherOrganizationLocator = By.id("otherHospitalText");
	
	/* String Element Locator Constructors */
	private String editPositionErrorMessageLocator = ".validationErrorList>li";
	private String countryComboBoxOptions = "select[id='countries'] > option:not([style='display:none;'])";
	
	public EditPosition(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public void scrollDown()
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250)");
	}
	
	public Boolean isEditPositionPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editCurrentPositionPopup, driver);
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
		return commonMethods.isElementDisplayed(closePopupLocator, driver);
	}
	
	public Boolean isTitleDisplayed()
	{
		return commonMethods.isElementDisplayed(titleLocator, driver);
	}
	
	public Boolean isOrganizationDisplayed()
	{
		return commonMethods.isElementDisplayed(organizationLocator, driver);
	}
	
	public Boolean isLocationDisplayed()
	{
		return commonMethods.isElementDisplayed(locationLocator, driver);
	}
	
	public Boolean isAdditionalTitlesDisplayed()
	{
		return commonMethods.isElementDisplayed(additionalTitlesLocator, driver);
	}
	
	public Boolean isCountryComboBoxDisplayed()
	{
		return commonMethods.isElementDisplayed(countryComboBoxLocator, driver);
	}
	
	public EditPosition selectCountryByVisibleText(String c)
	{
		new Select(driver.findElement(countryComboBoxLocator)).selectByVisibleText(c);
		return this;
	}
	
	public EditPosition selectCountryByIndex(int index)
	{
		new Select(driver.findElement(countryComboBoxLocator)).selectByIndex(index);
		return this;
	}
	
	public String getTextOfNthOptionInCountryComboBox(int nth)
	{
		return driver.findElement(By.cssSelector(countryComboBoxOptions + ":nth-child("+nth+")")).getText();
	}
	
	public int getCountOfOptionsInCountryComboBox()
	{
		return driver.findElements(By.cssSelector(countryComboBoxOptions)).size();
	}
	
	public EditPosition selectCityByVisibleText(String c)
	{
		new Select(driver.findElement(cityComboBoxLocator)).selectByVisibleText(c);
		return this;
	}
	
	public EditPosition selectOrganizationByVisibleText(String c)
	{
		new Select(driver.findElement(organizationComboBoxLocator)).selectByVisibleText(c);
		return this;
	}
	
	public EditPosition enterOtherCity(String oc)
	{
		driver.findElement(otherCityLocator).clear();
		driver.findElement(otherCityLocator).sendKeys(oc);
		return this;
	}
	
	public EditPosition enterOtherOrganization(String oo)
	{
		driver.findElement(otherOrganizationLocator).clear();
		driver.findElement(otherOrganizationLocator).sendKeys(oo);
		return this;
	}
	
	public EditPosition clearTitle()
	{
		driver.findElement(titleLocator).clear();
		return this;
	}
	
	public String getEditPositionDialogErrorMessage()
	{
		
		return driver.findElement(By.cssSelector(editPositionErrorMessageLocator)).getText();
	}
	
	public EditPosition enterTitle(String title)
	{
		driver.findElement(titleLocator).clear();
		driver.findElement(titleLocator).sendKeys(title);
		return this;
	}
	
	public EditPosition enterOrganization(String organization)
	{
		driver.findElement(organizationLocator).sendKeys(organization);
		return this;
	}
	
	public EditPosition enterLocation(String location)
	{
		driver.findElement(locationLocator).sendKeys(location);
		return this;
	}
	
	public EditPosition enterAdditionalTitles(String additionalTitles)
	{
		driver.findElement(additionalTitlesLocator).sendKeys(additionalTitles);
		return this;
	}
	
	public String getTitle()
	{
		return driver.findElement(titleLocator).getAttribute("value");
	}
	
	public String getOrganization()
	{
		return driver.findElement(organizationLocator).getAttribute("value");
	}
	
	public String getLocation()
	{
		return driver.findElement(locationLocator).getAttribute("value");
	}
	
	public String getAdditionalTitlesLocator()
	{
		return driver.findElement(additionalTitlesLocator).getAttribute("value");
	}
	
	public Profile clickSave()
	{
		driver.findElement(saveButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		if (!isEditPositionDialogErrorMessageVisible()){
			WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(closePopupLocator));
			
		}
		
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
	
	public Boolean isValidationErrorMessageVisible()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(validationErrorMsg, driver);
	}
	
	public Boolean isEditPositionDialogErrorMessageVisible()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(editPositionErrorMessageLocator), driver);
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
		wait.until(ExpectedConditions.elementToBeClickable(closePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
