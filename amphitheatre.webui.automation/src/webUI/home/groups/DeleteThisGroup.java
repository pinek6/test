package webUI.home.groups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class DeleteThisGroup
{	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */ 
	private By confirmCheckbox = By.id("confirmCbId");
	private By permanentlyDeleteGroup = By.cssSelector("input[value='permanently delete group']");

	public DeleteThisGroup(WebDriver driver)
	{
		this.driver=driver;
		//waitForPageToLoad(driver);
	}
	
//	public void waitForPageToLoad(WebDriver driver)
//	{
//		/* Load in properties */
//		ConfigProperties config = new ConfigProperties();
//		
//		/* Set the implicit wait to zero */
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		
//		/* Wait for UI elements to appear */
//		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
//		//wait.until(ExpectedConditions.visibilityOfElementLocated(uploadFileRadiobutton));
//		//wait.until(ExpectedConditions.elementToBeClickable(uploadFileRadiobutton));
//		//wait.until(ExpectedConditions.visibilityOfElementLocated(createWebLinkRadiobutton));
//		//wait.until(ExpectedConditions.elementToBeClickable(createWebLinkRadiobutton));
//		
//		/* Reset the implicit wait */
//		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
//	}
	
	public void selectConfirmCheckbox()
	{
		driver.findElement(confirmCheckbox).click();
	}
	
	public MyGroups clickPermanentlyDeleteGroup()
	{
		driver.findElement(permanentlyDeleteGroup).click();
		commonMethods.waitForElementToBeNotVisible(confirmCheckbox, driver);
		commonMethods.waitForElementToBeNotVisible(permanentlyDeleteGroup, driver);
		return new MyGroups(driver);
	}
	
}
