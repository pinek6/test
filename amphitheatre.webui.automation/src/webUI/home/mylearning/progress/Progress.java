package webUI.home.mylearning.progress;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.LocaleStrings;
import webUI.home.mylearning.MyLearning;


public class Progress extends MyLearning {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);	
	private String headerText = new LocaleStrings().getString("PROGRESS_PAGE_HEADER");
	
	/* Element Locators */
	private By headerLocator = By.cssSelector(".contentBody>div>h1");
	private By printIconLocator = By.id("printTranscript");
	private By allGuidedLearningPathsRadioButton = By.id("rb1");
	private By completedOnlyRadioButton = By.id("rb2");	
	private By notEnrolled = By.cssSelector(".message1");
	
	/* String Element Locator Constructors */
	private String progressTable = "div[data-dojo-attach-point='resultsRowsNode'] > div";
	private String progressTableTitle = " > span > a";
	private String progressTableStatus = " > span:nth-child(2)";
	private String progressTableDownloadCertificateOfCompletion = " > span:nth-child(2) > div:nth-child(2) > a";
	private String progressTableDateCompleted = " > span:nth-child(3)";
	
	public Progress(WebDriver driver)
	{
		super(driver);		
		waitForPageToLoad(driver);
		this.driver=driver;
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(headerLocator, headerText));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLocator));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}	
	
	public Boolean isPrintIconDisplayed()
	{
		return commonMethods.isElementDisplayed(printIconLocator,driver);		
	}
	
	public Progress clickPrintIcon()
	{
		driver.findElement(printIconLocator).click();
		return this;
	}
	
	public Progress clickAllGuidedLearningPathsRadioButton()
	{
		driver.findElement(allGuidedLearningPathsRadioButton).click();
		return this;
	}
	
	public Progress clickCompletedOnlyRadioButton()
	{
		driver.findElement(completedOnlyRadioButton).click();
		return this;
	}

	public int getCountOfRowsInProgressTable()
	{
		return driver.findElements(By.cssSelector(progressTable)).size();
	}
	
	public String getNthTitle(int n)
	{
		return driver.findElement(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableTitle)).getText();
	}

	public String getNthStatus(int n)
	{
		return driver.findElement(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableStatus)).getText();
	}
	
	public String getNthDateCompleted(int n)
	{
		return driver.findElement(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableDateCompleted)).getText();
	}

	public Boolean isNthTitleDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableTitle), driver);
	}

	public Boolean isNthStatusDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableStatus), driver);
	}

	public Boolean isNthDownloadCertificateOfCompletion(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableDownloadCertificateOfCompletion), driver);
	}
	
	public Boolean isNthDateCompletedDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableDateCompleted), driver);
	}

	public void clickNthTitle(int n)
	{
		driver.findElement(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableTitle)).click();
	}

	public void clickNthDownloadCertificateOfCompletion(int n)
	{
		driver.findElement(By.cssSelector(progressTable+":nth-child("+((2*n)-1)+")"+progressTableDownloadCertificateOfCompletion)).click();	
		
	}
	
	public String getNotEnrolledText()
	{
		return driver.findElement(notEnrolled).getText();
	}

	public Boolean isNotEnrolledTextDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(notEnrolled, driver);
	}
}
