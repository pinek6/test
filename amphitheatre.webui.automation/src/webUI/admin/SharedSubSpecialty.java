package webUI.admin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class SharedSubSpecialty {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	private By SharedSubSpecialtyPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_SharedSubSpecialties_']");
	private By titlePopupLocator = By.cssSelector("[id*=sls_widgets_components_profile_SharedSubSpecialties_] > span:nth-of-type(1)");
	
	/* Button Locators */
	private By okButtonLocator = By.id("dialogOk");
	private By dialogClosePopupLocator = By.cssSelector("h5[id*=sls_widgets_components_profile_SharedSubSpecialties_] > span:nth-of-type(2) > a > img");
	
	/* String Element Locator Constructors */
	private String sharedSubSpecialtyListLocator = "#peopleSubSpecialtiesHolder > div";
	private String next5LinkLocator = "#pagingButtons>span>a";
	private String counterLocator = "#peopleSubSpecialtiesHolder > div:nth-of-type(1)";
	private String memberListLocator = "#peopleSubSpecialtiesHolder";
	
	public SharedSubSpecialty(WebDriver driver)
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
		wait.until(ExpectedConditions.elementToBeClickable(dialogClosePopupLocator));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sharedSubSpecialtyListLocator)));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isSharedSubSpecialtyPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(SharedSubSpecialtyPopupLocator, driver);
	}
	
	public Boolean isOkButtonDisplayed()
	{
		return commonMethods.isElementDisplayed((okButtonLocator), driver);
	}
	
	public Boolean isClosePopupButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(dialogClosePopupLocator, driver);
	}
	
	public Boolean isNext5LinkDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(next5LinkLocator), driver);
	}
	
	public SharedSubSpecialty clickNext5Link()
	{
		driver.findElement(By.cssSelector(next5LinkLocator)).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(counterLocator), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(memberListLocator), driver);
		return this;
	}
	
	public Boolean isCounterDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(counterLocator), driver);
	}
	
	public int getTotalMembers()
	{
		String counter = driver.findElement(By.cssSelector(counterLocator)).getText();
		String c = counter.substring(7);
		int x = Integer.parseInt(c.replaceAll("[^0-9]", ""));
		return x;
	}

	public Profile clickOk()
	{
		WebElement e = driver.findElement(okButtonLocator);
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		jsx.executeScript("arguments[0].click();", e);

		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new Profile(driver);
	}
	
	public MyProfile clickNthMemberName(int nth)
	{
		int member = nth+1;
		driver.findElement(By.cssSelector(sharedSubSpecialtyListLocator + ":nth-of-type("+ member +")> div:nth-of-type(1)>a")).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new MyProfile(driver);
	}
	
	public String getNthMemberName(int nth)
	{
		int member = nth+1;
		String name = driver.findElement(By.cssSelector(sharedSubSpecialtyListLocator + ":nth-of-type("+ member +")> div:nth-of-type(1)>a")).getText();
		return name;
	}
	
	public String getTitle()
	{
		return driver.findElement(titlePopupLocator).getText();
	}

}
