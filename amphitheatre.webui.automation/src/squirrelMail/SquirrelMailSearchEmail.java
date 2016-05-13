package squirrelMail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class SquirrelMailSearchEmail 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	private By rightnavframe = By.cssSelector("frame[name='right']");
	private By emailBody = By.cssSelector("pre");	
	
	public SquirrelMailSearchEmail (WebDriver driver)
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
		wait.until(ExpectedConditions.elementToBeClickable(emailBody));
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailBody));
		wait.until(ExpectedConditions.presenceOfElementLocated(emailBody));	
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);		
	}
	
	public String getEmailBody()
	{
		return driver.findElement(emailBody).getText();
	}
	
	public String getPassword()
	{
		String email = getEmailBody();		
		String splits[] = email.split("\\n");
		return splits[6].trim();
	}
	
}
