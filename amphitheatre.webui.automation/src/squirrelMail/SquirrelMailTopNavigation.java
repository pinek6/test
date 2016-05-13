package squirrelMail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class SquirrelMailTopNavigation 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	private By rightnavframe = By.cssSelector("frame[name='right']");
	private By compose = By.cssSelector("table > tbody > tr:nth-of-type(2) > td > a:nth-of-type(1)");
	private By addresses = By.cssSelector("table > tbody > tr:nth-of-type(2) > td > a:nth-of-type(2)");
	private By folders = By.cssSelector("table > tbody > tr:nth-of-type(2) > td > a:nth-of-type(3)");
	private By options = By.cssSelector("table > tbody > tr:nth-of-type(2) > td > a:nth-of-type(4)");
	//private By search = By.cssSelector("table > tbody > tr:nth-of-type(2) > td > a:nth-of-type(5)");
	private By search = By.cssSelector("a[href*='search']");
	private By help = By.cssSelector("table > tbody > tr:nth-of-type(2) > td > a:nth-of-type(6)");
	
	public SquirrelMailTopNavigation (WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad();
	}
	
	public void waitForPageToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(rightnavframe));
		wait.until(ExpectedConditions.visibilityOfElementLocated(rightnavframe));
		wait.until(ExpectedConditions.presenceOfElementLocated(rightnavframe));	
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);		
	}
	
	public void clickSearch()
	{
		commonMethods.switchToFrame(driver, rightnavframe);
		driver.findElement(search).click();
	}
}
