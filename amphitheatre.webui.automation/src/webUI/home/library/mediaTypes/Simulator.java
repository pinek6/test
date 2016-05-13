package webUI.home.library.mediaTypes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class Simulator extends MediaType
{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By contentItemDescription = By.cssSelector(".contentItemDescription.bodyText1");
	private By contentItemAuthor = By.cssSelector(".contentItemAuthor.bodyText1");
	private By contentItemDate = By.cssSelector(".contentItemDate.bodyText1");
	private By simulatorContainer = By.cssSelector("div[id*='sls_widgets_components_media_SimulatorContainer_']");	
	private By loadingMsg = By.cssSelector(".loadStr");								
	
	public Simulator(WebDriver driver) {
		super(driver);
		this.driver = super.driver;
		waitForPageToLoad(driver);
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(newCommentButton));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(newCommentButton));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(newCommentButton));
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(contentItemAuthor));
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(contentItemDate));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(simulatorContainer));		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingMsg));
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public Boolean isContentDesriptionDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(contentItemDescription, driver);
	}
	
	public Boolean isContentAuthorDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(contentItemAuthor, driver);
	}
	
	public Boolean isContentDateDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(contentItemDate, driver);
	}
	
	public Boolean isSimulatorDisplayed()
	{
		return commonMethods.isElementDisplayed(simulatorContainer, driver);
	}
	
	public Boolean isLoadingMsgDisplayed()
	{
		return commonMethods.isElementDisplayed(loadingMsg, driver);
	}
	
}
