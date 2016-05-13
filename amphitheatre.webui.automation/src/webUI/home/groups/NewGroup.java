package webUI.home.groups;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class NewGroup {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);	
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupNew_'] > div > span");
	private By nameLocator = By.id("groupTitle");
	private By saveButtonLocator = By.cssSelector("button[data-dojo-attach-point='saveBtn']");
	private By cancelLinkLocator = By.cssSelector("a[data-dojo-attach-point='cancelBtn']");
	private By subjectDropdownLocator = By.id("groupSubjectId");
	private By ckeditor = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	private By descriptionEditorLocator = By.cssSelector(".cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders");
	private By accessPublicAccessRadiobutton = By.cssSelector("input[value='public']");
	private By accessMembersOnlyRadiobutton = By.cssSelector("input[value='private']");
	private By groupTypeDropdown = By.id("groupTypeId");
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public NewGroup(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.presenceOfElementLocated(headerLocator));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ckeditor));
		wait.until(ExpectedConditions.presenceOfElementLocated(groupTypeDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(groupTypeDropdown));
		wait.until(ExpectedConditions.visibilityOfElementLocated(groupTypeDropdown));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public NewGroup enterName(String name)	
	{
		driver.findElement(nameLocator).sendKeys(name);
		return this;
	}
	
	public NewGroupReady clickSaveButton()
	{
		driver.findElement(saveButtonLocator).click();
		return new NewGroupReady(driver);
	}
	
	public NewGroup clickCancelLink()
	{
		driver.findElement(cancelLinkLocator).click();
		return this;
	}
	
	public Boolean isNameFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(nameLocator, driver);
	}
	
	public Boolean isSubjectDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(subjectDropdownLocator, driver);
	}
	
	public Boolean isPublicAccessRadiobuttonDisplayed()
	{
		return commonMethods.isElementDisplayed(accessPublicAccessRadiobutton, driver);
	}
	
	public Boolean isMembersOnlyRadiobuttonDisplayed()
	{
		return commonMethods.isElementDisplayed(accessMembersOnlyRadiobutton, driver);
	}
	
	public Boolean isSaveButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(saveButtonLocator, driver);
	}

	public Boolean isCancelLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(cancelLinkLocator, driver);
	}
	
	public NewGroup selectPublicAccessRadiobutton()
	{
		driver.findElement(accessPublicAccessRadiobutton).click();
		return this;
	}
	
	public NewGroup selectMembersOnlyRadiobutton()
	{
		driver.findElement(accessMembersOnlyRadiobutton).click();
		return this;
	}	
	
	public NewGroup selectSubjectByVisibleText(String subject)
	{
		new Select(driver.findElement(subjectDropdownLocator)).selectByVisibleText(subject);
		return this;
	}
	
	public NewGroup selectSubjectByIndex(int index)
	{
		new Select(driver.findElement(subjectDropdownLocator)).selectByIndex(index);
		return this;
	}
	
	public NewGroup selectSubjectByValue(String value)
	{
		new Select(driver.findElement(subjectDropdownLocator)).selectByValue(value);
		return this;
	}
	
	public NewGroup selectGroupTypeByIndex(int index)
	{
		new Select(driver.findElement(groupTypeDropdown)).selectByIndex(index);
		return this;
	}

	public NewGroup selectGroupTypeByValue(String value)
	{
		new Select(driver.findElement(groupTypeDropdown)).selectByValue(value);
		return this;
	}

	public NewGroup selectGroupTypeByVisibleText(String visibleText)
	{
		new Select(driver.findElement(groupTypeDropdown)).selectByVisibleText(visibleText);
		return this;
	}
	
	public NewGroup enterDescription(String desc)
	{
		
		/* Switch to the iframe */
		driver.switchTo().frame(driver.findElement(ckeditor));
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		driver.findElement(descriptionEditorLocator).sendKeys(desc);
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
		
		return this;
	}
}
