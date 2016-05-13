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


public class EditProfessionalDetails {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);	

	/* Element Locators */
	private By editProfessionalDetailsPopup = By.cssSelector("div[id^='sls_widgets_components_profile_EditProfessionalDetailsDialog_']");	
	private By saveButtonLocator = By.id("btnSaveInterests");
	private By cancelButtonLocator = By.id("editInterestsCancel");	
	private By closePopupLocator =   By.cssSelector("h5[id^='sls_widgets_components_profile_EditProfessionalDetailsDialog_'] > span > a > img");
	private By specialtyDropdownLocator = By.id("specialtiesDropdown");	
	//private By addButtonLocator = By.id("btnAdd");
	private By occupationDropdownLocator = By.id("occupations");
	private By subSpecialtyDropdownLocator = By.id("subspecialties");
	
	/* String Element Locator Constructors */
	//private String occupationDropdownOptions = "select[id='occupations'] > option:not([style='display:none;'])";
	private String occupationDropdownOptions = "select[id='occupations'] > option:not([class='hide'])";
	private String specialtyDropdownOptions = "select[id='specialtiesDropdown'] > option:not([class='hide'])";
	private String subSpecialtyDropdownOptions = "select[id='subspecialties'] > option:not([class='hide'])";
	private String selectedSpecialtyList = "div[id='specialtiesList'] > ul > li";
	private String selectedSubSpecialtyList = "div[id='subspecialtiesList'] > ul > li";
	private String selectedSpecialtyTitle = " > span";
	private String selectedSpecialtyRemove = " > a";
	private String addSpecialtyButton = "button[data-dojo-attach-point='addSpecialityDojo']";
	private String addSubSpecialtyButton = "button[data-dojo-attach-point='addSubSpecialtiesDojo']";
	private String editProfessionalDetailsErrorMessageLocator = ".validationErrorList>li";
	
	public EditProfessionalDetails(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditProfessionalDetailsPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(editProfessionalDetailsPopup, driver);
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
	
	public Boolean isOccupationDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(occupationDropdownLocator, driver);
	}
	
	public Boolean isSpecialtyDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(specialtyDropdownLocator, driver);
	}
	
	public Boolean isSubSpecialtyDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(subSpecialtyDropdownLocator, driver);
	}
	
	public Boolean isAddButtonSpecialtyDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(addSpecialtyButton), driver);
	}
	
	public Boolean isAddButtonSubSpecialtyDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(addSubSpecialtyButton), driver);
	}
	
	public EditProfessionalDetails selectSpecialtyFromDropdownByVisibleText(String interest)
	{
		new Select(driver.findElement(specialtyDropdownLocator)).selectByVisibleText(interest);
		return this;
	}
	
	/**
	 * @param index Starts at 1 to select the first element in the dropdown
	 * @return
	 */
	public EditProfessionalDetails selectSpecialtyFromDropdownByIndex(int index)
	{
		new Select(driver.findElement(specialtyDropdownLocator)).selectByIndex(index-1);
		return this;
	}
	
	public EditProfessionalDetails selectSubSpecialtyFromDropdownByIndex(int index)
	{
		new Select(driver.findElement(subSpecialtyDropdownLocator)).selectByIndex(index-1);
		return this;
	}
	
	public EditProfessionalDetails selectOccupationFromDropdownByIndex(int index)
	{
		new Select(driver.findElement(occupationDropdownLocator)).selectByIndex(index-1);
		return this;
	}
	
	public EditProfessionalDetails selectSpecialtyFromDropdownByValue(String value)
	{
		new Select(driver.findElement(specialtyDropdownLocator)).selectByValue(value);
		return this;
	}
	
	public EditProfessionalDetails clickAddSpecialty()
	{
		driver.findElement(By.cssSelector(addSpecialtyButton)).click();
		return this;
	}
	
	public EditProfessionalDetails clickAddSubSpecialty()
	{
		driver.findElement(By.cssSelector(addSubSpecialtyButton)).click();
		return this;
	}
	
	public int getCountOfOptionsInSpecialtyDropdown()
	{
		return driver.findElements(By.cssSelector(specialtyDropdownOptions)).size();
	}
	
	public int getCountOfOptionsInSubSpecialtyDropdown()
	{
		return driver.findElements(By.cssSelector(subSpecialtyDropdownOptions)).size();
	}
	
	public int getCountOfOptionsInOccupationDropdown()
	{
		return driver.findElements(By.cssSelector(occupationDropdownOptions)).size();
	}
	
	public String getSelectedTextOfOccupationDropdown()
	{
		Select dropdown = new Select(driver.findElement(occupationDropdownLocator));
		return dropdown.getFirstSelectedOption().getText();
	}
	
	public String getTextOfNthOptionInSpecialtyDropdown(int nth)
	{
		return driver.findElement(By.cssSelector(specialtyDropdownOptions + ":nth-child("+nth+")")).getText();
	}
	
	public List<String> getListOfOptionsFromSpecialtyDropdown()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(specialtyDropdownOptions)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfOptionsFromSubSpecialtyDropdown()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(subSpecialtyDropdownOptions)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfSelectedSpecialtyOptions()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(selectedSpecialtyList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	     
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfSelectedSubSpecialtyOptions()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(selectedSubSpecialtyList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	     
        Collections.sort(l);
		return l;
	}
	
	public int getCountOfSelectedSpecialtyOptions()
	{
		return driver.findElements(By.cssSelector(selectedSpecialtyList)).size();
	}
	
	public int getCountOfSelectedSubSpecialtyOptions()
	{
		return driver.findElements(By.cssSelector(selectedSubSpecialtyList)).size();
	}

	public String getTextOfNthSelectedSpecialty(int n)
	{
		return driver.findElement(By.cssSelector(selectedSpecialtyList + ":nth-child("+n+")" + selectedSpecialtyTitle)).getText();
	}
	
	public EditProfessionalDetails removeNthSpecialty(int n)
	{
		driver.findElement(By.cssSelector(selectedSpecialtyList + ":nth-child("+n+")" + selectedSpecialtyRemove)).click();
		return this;
	}
	
	public EditProfessionalDetails removeNthSubSpecialty(int n)
	{
		driver.findElement(By.cssSelector(selectedSubSpecialtyList + ":nth-child("+n+")" + selectedSpecialtyRemove)).click();
		return this;
	}
	
	public Boolean isEditProfessionalDetailsDialogErrorMessageVisible()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(editProfessionalDetailsErrorMessageLocator), driver);
	}
	
	public String getEditProfessionalDetailsDialogErrorMessage()
	{
		
		return driver.findElement(By.cssSelector(editProfessionalDetailsErrorMessageLocator)).getText();
	}
	
	public Profile clickSave()
	{
		driver.findElement(saveButtonLocator).click();

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		if (!isEditProfessionalDetailsDialogErrorMessageVisible()){
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
