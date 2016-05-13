package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class EditContactInformation {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By editCurrentPositionPopup = By.cssSelector("div[id^='sls_widgets_components_profile_EditContactInfoDialog']");

	/* Button Locators */
	private By saveButtonLocator = By.id("btnSaveContactInfo");
	private By cancelButtonLocator = By.id("editContactInfoCancel");
	private By closePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditContactInfoDialog_')]/span[2]/a/img");
	
	/* Field Locators */
	private By emailCheckboxLocator = By.id("contactInfoEmailCB");
	private By emailFieldLocator = By.id("altEmail");;
	private By chatCheckboxLocator = By.id("contactInfoChatCB");
	private By otherCheckboxLocator = By.id("contactInfoOtherCB");
	private By otherFieldLocator = By.xpath("//div[@id='editContactPopup']/form/div[3]/div[2]/textarea");;
	
	public EditContactInformation(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isEditContactInformationPopupDisplayed()
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
	
	public EditContactInformation enterEmailInformation(String emailInfo)
	{
		driver.findElement(emailFieldLocator).clear();
		driver.findElement(emailFieldLocator).sendKeys(emailInfo);
		return this;
	}
	
	public EditContactInformation enterOtherInformation(String otherInfo)
	{
		driver.findElement(otherFieldLocator).clear();
		driver.findElement(otherFieldLocator).sendKeys(otherInfo);
		return this;
	}
	
	public Boolean isEmailInformationDisplayed()
	{
		return commonMethods.isElementDisplayed(emailFieldLocator,driver);
	}
	
	public Boolean isOtherInformationDisplayed()
	{
		return commonMethods.isElementDisplayed(otherFieldLocator,driver);
	}
	
	public String getEmailInformation()
	{
		return driver.findElement(emailFieldLocator).getAttribute("value");
	}
	
	public String getOtherInformation()
	{
		return driver.findElement(otherFieldLocator).getAttribute("value");
	}
	
	public EditContactInformation checkEmailCheckbox()
	{
		if (!isEmailCheckboxChecked())
			driver.findElement(emailCheckboxLocator).click();
		return this;
	}

	public EditContactInformation checkChatCheckbox()
	{
		if (!isChatCheckboxChecked())
			driver.findElement(chatCheckboxLocator).click();
		return this;
	}
	
	public EditContactInformation checkOtherCheckbox()
	{
		if (!isOtherCheckboxChecked())
			driver.findElement(otherCheckboxLocator).click();
		return this;
	}
	
	public EditContactInformation checkAllCheckboxes()
	{
		checkEmailCheckbox();
		checkChatCheckbox();
		checkOtherCheckbox();
		return this;
	}
	
	public EditContactInformation uncheckAllCheckboxes()
	{
		uncheckEmailCheckbox();
		uncheckChatCheckbox();
		uncheckOtherCheckbox();
		return this;
	}
	
	public Boolean isEmailCheckboxChecked()
	{
		return driver.findElement(emailCheckboxLocator).isSelected();
	}
	
	public Boolean isEmailCheckboxDisplayed()
	{
		return commonMethods.isElementPresent(emailCheckboxLocator,driver);
	}
	
	public Boolean isChatCheckboxChecked()
	{
		return driver.findElement(chatCheckboxLocator).isSelected();
	}
	
	public Boolean isChatCheckboxDisplayed()
	{
		return commonMethods.isElementPresent(chatCheckboxLocator,driver);
	}
	
	public Boolean isOtherCheckboxChecked()
	{
		return driver.findElement(otherCheckboxLocator).isSelected();
	}
	
	public EditContactInformation uncheckEmailCheckbox()
	{
		if (isEmailCheckboxChecked())
			driver.findElement(emailCheckboxLocator).click();
		return this;
	}

	public EditContactInformation uncheckChatCheckbox()
	{
		if (isChatCheckboxChecked())
			driver.findElement(chatCheckboxLocator).click();
		return this;
	}
	
	public EditContactInformation uncheckOtherCheckbox()
	{
		if(isOtherCheckboxChecked())
			driver.findElement(otherCheckboxLocator).click();
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(closePopupLocator));;
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
