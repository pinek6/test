package webUI.home.library.mediaTypes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class Video extends MediaType
{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By video = By.cssSelector("div[id^='sls_widgets_components_media_VideoContainer']");
	private By videoLoading = By.cssSelector(".loadStr");
	private By playButton = By.cssSelector("div.iconContainer.playIcon.play");
	private By pauseButton = By.cssSelector("div.iconContainer.playIcon.pause");
	private By fullscreenOnButton = By.cssSelector(".fullScreenIcon");
	private By fullscreenOffButton = By.cssSelector(".fullScreenButton");
	private By timeElapsed = By.cssSelector("div.timeUnit > span");
	private By videoDuration = By.cssSelector(".durationText");
	private By playProgress = By.cssSelector("div.progress.playProgress");
	private By bufferProgress = By.cssSelector("div.progress.bufferProgress");
	private By commentsTab = By.id("commentTab");
	private By transcriptTab = By.id("transcriptTab");
	private By resumeInformationBox = By.cssSelector(".mediaMessageContainer");
	private By resumeInformationBoxResume = By.cssSelector(".mediaMessageContainer > div > div:nth-child(2) > button:nth-child(1)");
	private By resumeInformationBoxStartOver = By.cssSelector(".mediaMessageContainer > div > div:nth-child(2) > button:nth-child(2)");
	private By fullscreenIndicatorFirefox = By.cssSelector(".mainVideoContainer > div > div > div[class='videoContainer'] > div:nth-child(1) > div[class^='fullscreen']");
	private By fullscreenIndicatorOtherBrowsers = By.cssSelector(".mainVideoContainer > div > div > div[class='videoContainer'] > div:nth-child(1) > div > video[class$='fullscreen']");
	
	
	public Video(WebDriver driver)
	{	
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(videoLoading));
		wait.until(ExpectedConditions.presenceOfElementLocated(video));
		wait.until(ExpectedConditions.elementToBeClickable(playButton));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(playButton));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(playButton));
		//wait.until(ExpectedConditions.elementToBeClickable(bufferProgress));
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bufferProgress));
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bufferProgress));	
		wait.until(ExpectedConditions.elementToBeClickable(newCommentButton));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(newCommentButton));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(newCommentButton));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(videoLoading));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isResumeInformationBoxDisplayed()
	{
		return commonMethods.isElementDisplayed(resumeInformationBox, driver);
	}
	
	public Video clickResumeVideoResume()
	{
		driver.findElement(resumeInformationBoxResume).click();
		commonMethods.waitForElementToBeNotVisible(resumeInformationBox, driver);
		return this;
	}
	

	public Video clickResumeVideoStartOver()
	{
		driver.findElement(resumeInformationBoxStartOver).click();
		commonMethods.waitForElementToBeNotVisible(resumeInformationBox, driver);
		return this;
	}
	
	public Boolean isVideoPresent()
	{
		return commonMethods.isElementDisplayed(video, driver);
	}
	
	public void clickPlay()
	{
		driver.findElement(playButton).click();
		commonMethods.waitForElementToBeRefreshed(timeElapsed, driver);
	}
	
	public Boolean isPlayButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(playButton, driver);
	}
	
	public Boolean isPauseButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(pauseButton, driver);
	}
	
	public void clickPause()
	{
		driver.findElement(pauseButton).click();
	}
	
	public String getTimeElapsed()
	{
		return driver.findElement(timeElapsed).getText();
	}
	
	public String getVideoDuration()
	{
		return driver.findElement(videoDuration).getText();
	}
	
	public Boolean isVideoDurationDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(videoDuration, driver);
	}
	
	public Double getProgressBarLocation()
	{
		return Double.parseDouble(driver.findElement(playProgress).getAttribute("style").replace("width: ", "").replace("%;", ""));
	}
	
	public Boolean isCommentsTabDisplayed()
	{
		return commonMethods.isElementDisplayed(commentsTab, driver);
	}
	
	public Boolean isCommentsTabSelected()
	{
		return driver.findElement(commentsTab).getAttribute("class").equals("selected") ? true : false;
	}
	
	public void clickCommentsTab()
	{
		driver.findElement(commentsTab).click();
	}
	
	public Boolean isTranscriptTabDisplayed()
	{
		return commonMethods.isElementDisplayed(transcriptTab, driver);
	}
	
	public Boolean isTranscriptTabSelected()
	{
		return driver.findElement(transcriptTab).getAttribute("class").equals("selected") ? true : false;
	}
	
	public void clickTranscriptTab()
	{
		driver.findElement(transcriptTab).click();
	}
	
	public void clickFullscreenOnButton(){
		driver.findElement(fullscreenOnButton).click();
	}
	
	public void clickFullscreenOffButton(){
		driver.findElement(fullscreenOffButton).click();
	}
	
	public Boolean isFullscreenOn(String browser){
		Boolean result = false;
		
		By indicator;
		
		if (browser.toLowerCase().equals("firefox")||browser.toLowerCase().equals("safari"))
			indicator = fullscreenIndicatorFirefox;
		else
			indicator = fullscreenIndicatorOtherBrowsers;
		
		try 
		{
			if (driver.findElement(indicator).isDisplayed())
				result = true;
			else 
				result = false;
		}
		catch (Exception e)
		{
			result=false;
		}
		return result;	
	}
	
	public int getStateInSeconds(){
		String state=getTimeElapsed();
		return Integer.parseInt(state.substring(3, 5))+60*Integer.parseInt(state.substring(0, 2));
	}
}
