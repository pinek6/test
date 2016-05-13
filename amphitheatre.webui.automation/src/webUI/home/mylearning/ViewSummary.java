package webUI.home.mylearning;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class ViewSummary {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By viewSummaryPopup = By.cssSelector("div[id^='sls_widgets_components_learning_SummaryDialog_']");
	private By closeSummary = By.cssSelector("img[class='btnClosePopup']");

	public ViewSummary(WebDriver driver)
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
		
		/* Wait for Close Popup button to be gone */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(closeSummary));
		wait.until(ExpectedConditions.presenceOfElementLocated(closeSummary));
		wait.until(ExpectedConditions.elementToBeClickable(closeSummary));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isViewSummaryPopupDisplayed()
	{
		return commonMethods.isElementDisplayed(viewSummaryPopup, driver);
	}
	
	public void clickClose()
	{
		driver.findElement(closeSummary).click();
		commonMethods.waitForElementToBeNotVisible(closeSummary, driver);
		commonMethods.waitForElementToBeNotVisible(viewSummaryPopup, driver);
	}

	
}
