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

public class SharedExpertise {

	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By SharedExpertisePopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_SharedExpertiseDialog_']");
	private By titlePopupLocator = By.cssSelector("[id*=sls_widgets_components_profile_SharedExpertiseDialog_] > span:nth-of-type(1)");
		
	/* Button Locators */
	private By dialogClosePopupLocator = By.cssSelector("h5[id*=sls_widgets_components_profile_SharedExpertiseDialog_] > span:nth-of-type(2) > a > img");
	private By okButtonLocator = By.id("dialogOk");
	
	/* String Element Locator Constructors */
	private String sharedExpertsListLocator = "[id*=sls_widgets_components_profile_SharedExpertiseDialog_] > div:nth-of-type(2) > div > div > div";
	private String counterLocator = "#peopleExpertiseHolder > div:nth-of-type(1)";
	private String next5LinkLocator = "#pagingButtons>span>a";
	private String memberListLocator = "#peopleExpertiseHolder";
	private String sharedExpertiseListLocator = "#peopleExpertiseHolder > div";
	
	public SharedExpertise(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isSharedExpertisePopupDisplayed()
	{
		return commonMethods.isElementDisplayed(SharedExpertisePopupLocator, driver);
	}
	
	public Boolean isOkButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(okButtonLocator, driver);
	}
	
	public Boolean isClosePopupButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(dialogClosePopupLocator, driver);
	}
	
	public Profile clickOkButton()
	{
		//driver.findElement(By.cssSelector(closeButtonLocator)).click();
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
	
	public Profile clickClosePopup()
	{
		driver.findElement(dialogClosePopupLocator).click();

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
		driver.findElement(By.cssSelector(sharedExpertiseListLocator + ":nth-of-type("+ member +")> div:nth-of-type(1)>a")).click();
		
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
	
	public void waitForPopupToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(dialogClosePopupLocator));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sharedExpertsListLocator)));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
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
	
	public Boolean isNext5LinkDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(next5LinkLocator), driver);
	}
	
	public SharedExpertise clickNext5Link()
	{
		driver.findElement(By.cssSelector(next5LinkLocator)).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(counterLocator), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(memberListLocator), driver);
		return this;
	}
	
	public String getNthMemberName(int nth)
	{
		int member = nth+1;
		String name = driver.findElement(By.cssSelector(sharedExpertiseListLocator + ":nth-of-type("+ member +")> div:nth-of-type(1)>a")).getText();
		return name;
	}
	
	public MyProfile clickNthExpertName(int nth)
	{
		driver.findElement(By.cssSelector(sharedExpertsListLocator + ":nth-of-type("+nth+")> div:nth-of-type(1)>a")).click();
		return new MyProfile(driver);
	}
	
	public Boolean isEmailIconDisplayedNthExpert(int nth)
	{
		int x = nth*2+1;
		return commonMethods.isElementDisplayed((By.cssSelector(memberListLocator + "> div:nth-of-type("+ x +") > a")), driver);
	}
	
	public String getTitle()
	{
		return driver.findElement(titlePopupLocator).getText();
	}
	
	public void scrollToNext5Link()
	{
		commonMethods.scrollIntoView(driver, next5LinkLocator);
	}
}
