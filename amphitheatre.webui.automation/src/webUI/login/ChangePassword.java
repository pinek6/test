package webUI.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.admin.Settings;


public class ChangePassword {

	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);	
	
	/* Element Locators */
	private By currentPasswordLocator = By.id("oldPassword");
	private By newPasswordLocator = By.id("newPassword");
	private By confirmPasswordLocator = By.id("confirmPassword");
	private By saveButtonLocator = By.id("btnSaveName");
	private By cancelButtonLocator = By.id("nameEditCancel");
	private By closePopupLocator = By.cssSelector(".btnClosePopup");
	private By changePasswordSuccessPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_ChangePasswordSuccess_']");
	
	/* String Element Locator Constructors */
	private String validationsList = "div[id='parentValidation'] > ul > li";

	public ChangePassword(WebDriver driver)
	{	
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public void waitForPopupToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(currentPasswordLocator));
		wait.until(ExpectedConditions.elementToBeClickable(newPasswordLocator));
		wait.until(ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
		wait.until(ExpectedConditions.elementToBeClickable(saveButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(closePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
		
	public Boolean isCurrentPasswordFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(currentPasswordLocator, driver);
	}

	public Boolean isNewPasswordFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(newPasswordLocator, driver);
	}

	public Boolean isConfirmPasswordFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(confirmPasswordLocator, driver);
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

	public ChangePassword enterCurrentPassword(String currentPassword)
	{
		driver.findElement(currentPasswordLocator).clear();
		driver.findElement(currentPasswordLocator).sendKeys(currentPassword);
		return this;
	}
	
	public ChangePassword enterNewPassword(String newPassword)
	{
		driver.findElement(newPasswordLocator).clear();
		driver.findElement(newPasswordLocator).sendKeys(newPassword);
		return this;
	}
	
	public ChangePassword enterConfirmPassword(String confirmPassword)
	{
		driver.findElement(confirmPasswordLocator).clear();
		driver.findElement(confirmPasswordLocator).sendKeys(confirmPassword);
		return this;
	}
	
	public ChangePasswordSuccess clickSave()
	{
		driver.findElement(saveButtonLocator).click();
		return new ChangePasswordSuccess(driver);
	}
	
	public void clickCancel()
	{
		driver.findElement(cancelButtonLocator).click();
		commonMethods.waitForElementToBeNotVisible(cancelButtonLocator, driver);
		commonMethods.waitForElementToBeNotVisible(currentPasswordLocator, driver);
		commonMethods.waitForElementToBeNotVisible(newPasswordLocator, driver);
		commonMethods.waitForElementToBeNotVisible(saveButtonLocator, driver);
		commonMethods.waitForElementToBeNotVisible(confirmPasswordLocator, driver);
	}
	
	public Settings clickClose()
	{
		driver.findElement(closePopupLocator).click();
		return new Settings(driver);
	}
	
	public Boolean isChangePasswordSuccessPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(changePasswordSuccessPopupLocator, driver);
	}
	
	public List<String> getListOfPasswordValidations()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(validationsList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
}
