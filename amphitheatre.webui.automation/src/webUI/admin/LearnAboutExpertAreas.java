package webUI.admin;

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

public class LearnAboutExpertAreas {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private By LearnAboutExpertAreasPopupLocator = By.cssSelector("div[id^='sls_widgets_components_profile_EditExpertiseDialog_']");
	
	/* String Element Locator Constructors */
	public String expertiseList = "ul[class='profileItemList slsPopupParagraph'] > li";
	
	/* Button Locators */
	private By okButtonLocator = By.id("editExpertiseOk");
	private By dialogClosePopupLocator = By.xpath("//h5[contains(@id, 'sls_widgets_components_profile_EditExpertiseDialog_')]/span[2]/a/img");
	
	public LearnAboutExpertAreas(WebDriver driver)
	{
		this.driver=driver;
		waitForPopupToLoad();
	}
	
	public Boolean isLearnAboutExpertAreasPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(LearnAboutExpertAreasPopupLocator, driver);
	}
	
	public Boolean isOkButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(okButtonLocator, driver);
	}
	
	public Boolean isCloseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(dialogClosePopupLocator, driver);
	}
	
	public Profile clickOk()
	{
		driver.findElement(okButtonLocator).click();

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
	
	public Profile clickClose()
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
	
	public void waitForPopupToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(dialogClosePopupLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public List<String> getListOfExpertise()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(expertiseList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
}
