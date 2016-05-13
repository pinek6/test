package webUI.home.mylearning.currentActivities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.LocaleStrings;
import webUI.home.mylearning.LearningPathDetail;
import webUI.home.mylearning.MyLearning;


public class CurrentActivities extends MyLearning  {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	By headerLocator = By.cssSelector(".contentBody>div>h1");
	String headerText = new LocaleStrings().getString("CURRENT_ACTIVITIES_PAGE_HEADER");
	private By guidedLearningPathsLocator = By.cssSelector("div[id^='sls_widgets_components_learning_LearningPathWidget']");
	private By titleLocator = By.cssSelector(".title4");
	private By notEnrolled = By.cssSelector(".message1");
	
	/* String Element Locator Contructors */
	private String guidedLearningPaths = "//div[@class='learningPathsContent']/div";
	private String guidedLearningPathsTitle = "/div/h3/a";
	private String guidedLearningPathsModuleCount = "/div/div/span";
	private String guidedLearningPathsPercentageComplete = "/div/div/span[2]";
	private String guidedLearningPathsModule = "/div/div[2]/div/div";
	private String guidedLearningPathsButton = "/button";

	public CurrentActivities(WebDriver driver)
	{
		super(driver);
		waitForPageToLoad(driver);
		this.driver=driver;
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public int getCountOfGuidedLearningPaths()
	{
		return driver.findElements(guidedLearningPathsLocator).size();
	}
	public Boolean isTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(titleLocator, driver);
	}

	public Boolean isGuidedLearningPathTitleDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsTitle),driver);
	}	
	
	public LearningPathDetail clickGuidedLearningPathTitle(int row)
	{
		driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsTitle)).click();
		return new LearningPathDetail(driver);
	}	
	
	public String getGuidedLearningPathTitle(int row)
	{
		return driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsTitle)).getText();		
	}

	public Boolean isGuidedLearningPathModuleCountDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsModuleCount),driver);
	}		
	
	public String getGuidedLearningModuleCountText(int row)
	{
		return driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsModuleCount)).getText();		
	}
	
	public int getGuidedLearningModuleCount(int row)
	{
		String countText = getGuidedLearningModuleCountText(row);
		return Integer.parseInt(countText.substring(countText.lastIndexOf(" ")+1, countText.length()));		
	}

	public Boolean isGuidedLearningPathPercentageCompleteDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsPercentageComplete),driver);
	}		
	
	public String getGuidedLearningPercentageComplete(int row)
	{
		return driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsPercentageComplete)).getText();		
	}
	
	public int getGuidedLearningPercentageCompleteValue(int row)
	{
		String stringValueOfPercentComplete = getGuidedLearningPercentageComplete(row);
		return Integer.parseInt(stringValueOfPercentComplete.substring(stringValueOfPercentComplete.lastIndexOf(" ")+1,stringValueOfPercentComplete.indexOf("%")));
	}
	
	public void clickGuidedLearningButton(int row, int module)
	{
		driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]" + guidedLearningPathsModule + "["+module+"]" + guidedLearningPathsButton)).click();
	}
	
	public String getGuidedLearningButtonType(int row, int module)
	{
		return driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]"+ guidedLearningPathsModule + "["+module+"]"  + guidedLearningPathsButton)).getAttribute("class").equals("btnAlternate btnSmall") 
		? "start" : "continue";
	}
	
	public String getGuidedLearningModuleStatus(int row, int module)
	{
		String status = driver.findElement(By.xpath(guidedLearningPaths + "["+row+"]"+ guidedLearningPathsModule + "["+module+"]")).getAttribute("class");
		if (status.contains("complete"))
			status="complete";
		else if (status.contains("resume"))
			status="resume";
		else if (status.contains("locked"))
			status="locked";
		else if (status.contains("start"))
			status="start";
		else 
			status="status unknown";
		return status;
	}
	
	public String getNotEnrolledText()
	{
		return driver.findElement(notEnrolled).getText();
	}

	public Boolean isNotEnrolledTextDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(notEnrolled, driver);
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(headerLocator,headerText));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLocator));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

}
