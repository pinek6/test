package webUI.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditInterests {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);	

	/* Element Locators */
	private By editInterestsPopup = By.cssSelector("div[id^='sls_widgets_components_profile_EditInterestsDialog_']");	
	private By saveButtonLocator = By.id("btnSaveInterests");
	private By cancelButtonLocator = By.id("editInterestsCancel");	
	private By closePopupLocator =   By.cssSelector("h5[id^='sls_widgets_components_profile_EditInterestsDialog_'] > span > a > img");
	private By interestsDropdownLocator = By.id("interests");	
	//private By addButtonLocator = By.id("btnAdd");
	
	/* String Element Locator Constructors */
	private String interestsDropdownOptions = "select[id='interests'] > option:not([style='display:none;'])";
	private String selectedInterestsList = "div[id='interestsList'] > ul > li";
	private String selectedInterestsTitle = " > span";
	private String selectedInterestsRemove = " > a";
	private String addInterestsButton = "button[data-dojo-attach-point='addInterestsDojo']";
	
	public EditInterests(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditInterestsPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editInterestsPopup, driver);
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
	
	public Boolean isInterestsDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(interestsDropdownLocator, driver);
	}
	
	public Boolean isAddButtonInterestsDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(addInterestsButton), driver);
	}
	
	public EditInterests selectInterestsFromDropdownByVisibleText(String interest)
	{
		new Select(driver.findElement(interestsDropdownLocator)).selectByVisibleText(interest);
		return this;
	}
	
	/**
	 * @param index Starts at 1 to select the first element in the dropdown
	 * @return
	 */
	public EditInterests selectInterestsFromDropdownByIndex(int index)
	{
		new Select(driver.findElement(interestsDropdownLocator)).selectByIndex(index-1);
		return this;
	}
	
	public EditInterests selectInterestsFromDropdownByValue(String value)
	{
		new Select(driver.findElement(interestsDropdownLocator)).selectByValue(value);
		return this;
	}
	
	public EditInterests clickAddInterests()
	{
		driver.findElement(By.cssSelector(addInterestsButton)).click();
		return this;
	}
	
	public int getCountOfOptionsInInterestsDropdown()
	{
		return driver.findElements(By.cssSelector(interestsDropdownOptions)).size();
	}
	
	public String getTextOfNthOptionInInterestsDropdown(int nth)
	{
		return driver.findElement(By.cssSelector(interestsDropdownOptions + ":nth-child("+nth+")")).getText();
	}
	
	public List<String> getListOfOptionsFromInterestsDropdown()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(interestsDropdownOptions)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfSelectedOptions()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(selectedInterestsList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	     
        Collections.sort(l);
		return l;
	}
	
	public int getCountOfSelectedOptions()
	{
		return driver.findElements(By.cssSelector(selectedInterestsList)).size();
	}

	public String getTextOfNthSelectedInterests(int n)
	{
		return driver.findElement(By.cssSelector(selectedInterestsList + ":nth-child("+n+")" + selectedInterestsTitle)).getText();
	}
	
	public EditInterests removeNthInterests(int n)
	{
		driver.findElement(By.cssSelector(selectedInterestsList + ":nth-child("+n+")" + selectedInterestsRemove)).click();
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
	
	private void waitForPopupToLoad()
	{	
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(saveButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
