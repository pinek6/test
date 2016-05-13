package webUI.home.groups;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class AddItem
{	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */ 
	private By uploadFileRadiobutton = By.cssSelector("input[id*='uploadItem']");
	private By createWebLinkRadiobutton = By.cssSelector("input[id*='createLink']");
	private By webLinkTitleForLinkInput = By.cssSelector("input[id$='TextBoxWeb1']");
	private By webLinkWebAddressInput = By.cssSelector("input[id$='TextBoxWeb2']");
	private By webLinkDescriptionInput = By.cssSelector("input[id$='TextBoxWeb3']");
	private By addButton = By.cssSelector("button[data-dojo-attach-event='ondijitclick:_clickAdd']");

	public AddItem(WebDriver driver)
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
		
		/* Wait for UI elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(uploadFileRadiobutton));
		//wait.until(ExpectedConditions.elementToBeClickable(uploadFileRadiobutton));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(createWebLinkRadiobutton));
		//wait.until(ExpectedConditions.elementToBeClickable(createWebLinkRadiobutton));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public AddItem clickUploadFileRadiobutton()
	{
		driver.findElement(uploadFileRadiobutton).click();
		return this;
	}
	
	public AddItem clickCreateWebLinkRadiobutton()
	{
		driver.findElement(createWebLinkRadiobutton).click();
		return this;
	}
	
	public AddItem enterTitleForLink(String title)
	{
		driver.findElement(webLinkTitleForLinkInput).sendKeys(title);
		return this;
	}
	
	public AddItem enterWebAddress(String webAddress)
	{
		driver.findElement(webLinkWebAddressInput).sendKeys(webAddress);
		return this;
	}
	
	public AddItem enterDescription(String description)
	{
		driver.findElement(webLinkDescriptionInput).sendKeys(description);
		return this;
	}
	
	public void clickAdd()
	{
		driver.findElement(addButton).click();
		GroupsViewResourcesTab g = new GroupsViewResourcesTab(driver);
		g.waitForResultsToRefresh();
	}
	
}
