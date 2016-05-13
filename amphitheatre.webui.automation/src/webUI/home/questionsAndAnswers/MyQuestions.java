package webUI.home.questionsAndAnswers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class MyQuestions extends QuestionsAndAnswers{
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	private String questionListItems = ".columnsRightItemBig";
	private String questionListItemsTitle = " > div[class='questionLine'] > a";
	private String questionListItemsStatus = " > div[class='answerLine'] > span:nth-child(1)";
	private String questionListItemsContext = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='contextLine sqaQuestionLine'] > span";
	private String questionListItemsAsked = " > div[class='answerLine'] > span[class^='asked']";
	private String questionListItemsLikes = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='likes']";
	private String questionListItemsReplies = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='replies']";
	private String questionListItemsLastUpdatedDate = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='lastUpdated']";
	private String questionListItemsLastUpdatedBy = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='lastUpdated'] > a";
	
	/*  Element Locators */
	private By pagingTextLocator = By.cssSelector(".bodyText5Bold");
	private By noQuestionsFound = By.cssSelector("div[class='columnsLeftMain'] > div:nth-child(3) > div:nth-child(1)");
	private By tipsPanel = By.cssSelector(".columnsRightBody");
	private By leftColumn = By.cssSelector(".columnsLeftMain");
	private By loading = By.cssSelector(".loading");

	public MyQuestions(WebDriver driver)
	{
		super(driver);
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(tipsPanel));
		wait.until(ExpectedConditions.elementToBeClickable(tipsPanel));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(leftColumn));
		wait.until(ExpectedConditions.elementToBeClickable(leftColumn));	
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public Boolean isNoQuestionsFound()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(noQuestionsFound, driver);
	}
	
	public Boolean isPagingTextDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(pagingTextLocator, driver);
	}
	
	public String getPagingText()
	{
		return driver.findElement(pagingTextLocator).getText();
	}
	
	public int getCountOfCurrrentlyDisplayingQuestionsFromPagingText()
	{
		String pagingText = driver.findElement(pagingTextLocator).getText();
		int locationOfHyphen = pagingText.indexOf("-");
		int locationOfSpace = pagingText.indexOf(" ");
		return Integer.parseInt(pagingText.substring(locationOfHyphen+1, locationOfSpace));
	}
	
	public int getTotalCountOfQuestionsFromPagingText()
	{
		String pagingText = driver.findElement(pagingTextLocator).getText();
		int locationOfSpace = pagingText.indexOf(" ");
		pagingText=pagingText.substring(locationOfSpace+1,pagingText.length());
		locationOfSpace = pagingText.indexOf(" ");
		pagingText=pagingText.substring(locationOfSpace+1,pagingText.length());		
		return Integer.parseInt(pagingText);
	}
	
	public int getCountOfQuestionsDisplayed()
	{
		return driver.findElements(By.cssSelector(questionListItems)).size();
	}
	
	public Boolean isAnswersToMyQuestionsListDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems), driver);
	}
	
	public String getNthQuestionTitle(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsTitle)).getText();
	}
	
	public Boolean isNthQuestionTitleDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsTitle), driver);
	}
	
	public void clickNthQuestionTitle(int row)
	{
		driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsTitle)).click();
	}

	public String getNthQuestionStatus(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsStatus)).getText();
	}
	
	public Boolean isNthQuestionStatusDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsStatus), driver);		
	}

	public String getNthQuestionContext(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsContext)).getText();
	}
	
	public Boolean isNthQuestionContextDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsContext), driver);			
	}
	
	public String getNthQuestionAsked(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsAsked)).getText();
	}
	
	public Boolean isNthQuestionAskedDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsAsked), driver);	
	}

	public int getNthQuestionLikes(int row)
	{
		String likes = driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLikes)).getText();
		likes = likes.substring(0,likes.indexOf(" "));
		return Integer.parseInt(likes);
	}
	
	public Boolean isNthQuestionLikesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLikes), driver);
	}
	
	public int getNthQuestionReplies(int row)
	{
		String replies = driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsReplies)).getText();
		replies = replies.substring(0,replies.indexOf(" "));
		return Integer.parseInt(replies);
	}
	
	public Boolean isNthQuestionRepliesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsReplies), driver);
	}

	public Boolean isNthQuestionLastUpdatedDateDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLastUpdatedDate), driver);
	}
	
	public String getNthQuestionLastUpdatedBy(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLastUpdatedBy)).getText();
	}
	
	public Boolean isNthQuestionLastUpdatedByDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLastUpdatedBy), driver);
	}
	
	public void clickNthQuestionLastUpdatedBy(int row)
	{
		driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLastUpdatedBy)).click();
	}
}
