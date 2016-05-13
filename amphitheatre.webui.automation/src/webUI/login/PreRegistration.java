package webUI.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.login.invite.FirstTimeUserRegistration;

public class PreRegistration {

	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	private By startRegistration = By.id("registrationBtn");
	private By returnToLogin = By.id("returnToLogin");
	
	public PreRegistration(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/*  Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(startRegistration));
		wait.until(ExpectedConditions.presenceOfElementLocated(startRegistration));
		wait.until(ExpectedConditions.elementToBeClickable(startRegistration));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public FirstTimeUserRegistration clickStartRegistration()
	{
		driver.findElement(startRegistration).click();
		return new FirstTimeUserRegistration(driver);
	}
	
	public Login clickReturnToLogin()
	{
		driver.findElement(returnToLogin).click();
		return new Login(driver);
	}
}
