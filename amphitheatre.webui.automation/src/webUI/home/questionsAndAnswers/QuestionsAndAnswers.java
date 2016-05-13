package webUI.home.questionsAndAnswers;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class QuestionsAndAnswers {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/*  Element Locators */
	private By headerLocator = By.cssSelector("div[id^='sls_widgets_views_qanda_QandA'] > div.contentBody > div > h1");
	private By allQuestionsTabLocator = By.id("ALL_QUESTIONS");
	private By myQuestionsTabLocator = By.id("MY_QUESTIONS");
	private By questionsICanAnswerTabLocator = By.id("QUESTIONS_I_CAN_ANSWER");
	public By showDropdown = By.id("ddId");
	public By pagingTextLocator = By.cssSelector(".bodyText1Bold");
	public By loading = By.cssSelector(".loading");
	public By emptyState = By.cssSelector("[class='messageBox messageBoxInfo']");
	
	private String questionListItemsLastUpdatedDate = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='lastUpdated']";
	private String questionListItemsAnswer = " > div[class='answerLine'] > span[class='pinAndLikeAmountQA']";
	private String questionListItems = ".columnsRightItemBig";
	private String showDropdownOptions = "[id='ddId'] > option";
	private String questionListItemsTitle = " > div[class='questionLine'] > a";
	private String questionListItemsShowAnswer = " > div[class='answerLine'] > a";
	
	public QuestionsAndAnswers(WebDriver driver)
	{
		this.driver=driver;
		//waitForPageToLoad(driver);
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/*  Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLocator));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(myQuestionsTabLocator));
		wait.until(ExpectedConditions.elementToBeClickable(myQuestionsTabLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(questionsICanAnswerTabLocator));
		wait.until(ExpectedConditions.elementToBeClickable(questionsICanAnswerTabLocator));			
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isAllQuestionsTabSelected()
	{
		return driver.findElement(allQuestionsTabLocator).getAttribute("class").contains("selected") ? true : false;
	}
	
	public Boolean isMyQuestionsTabSelected()
	{
		return driver.findElement(myQuestionsTabLocator).getAttribute("class").contains("selected") ? true : false;
	}
	
	public Boolean isQuestionsICanAnswerTabSelected()
	{
		return driver.findElement(questionsICanAnswerTabLocator).getAttribute("class").contains("selected") ? true : false;
	}	
	
	public MyQuestions clickMyQuestionsTab()
	{
		driver.findElement(myQuestionsTabLocator).click();
		return new MyQuestions(driver);
	}
	
	public AllQuestions clickAllQuestionsTab()
	{
		driver.findElement(allQuestionsTabLocator).click();
		return new AllQuestions(driver);
	}
	
	public QuestionsICanAnswer clickQuestionsICanAnswerTab()
	{
		driver.findElement(questionsICanAnswerTabLocator).click();
		return new QuestionsICanAnswer(driver);
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
	
	public String getNthOptionFromShowDropdown(int row){
		return driver.findElement(By.cssSelector(showDropdownOptions+":nth-child("+row+")")).getText();
	}
	public QuestionsAndAnswers selectOptionInDropdown(String option){
		new Select(driver.findElement(showDropdown)).selectByValue(option);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	public int getCountOfDisplayedQuestions(){
		return driver.findElements(By.cssSelector(questionListItems)).size();
	}
	public String getEmptyStateMessage(){
		return driver.findElement(emptyState).getText();
	}
	
	public int getCountOfQuestionsDisplayed()
	{
		return driver.findElements(By.cssSelector(questionListItems)).size();
	}
	
	public int getQuestionPositionByText(String questionText){
		int counter = getCountOfQuestionsDisplayed();
		int pos=0;
		
		for(int i=1;i<=counter;i++){
			if(questionText.equals(getNthQuestionTitle(i))){
				pos = i;
				break;
			}
		}
		
		return pos;
	}
	
	public String getNthQuestionTitle(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsTitle)).getText();
	}
	public String getNthQuestionLastUpdatedDate(int row)
	{
		String updateText = driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLastUpdatedDate)).getText();
		String updateText1 = removeNullString(updateText);
		updateText1=updateText1.replaceAll("\\n", "");
		return updateText1;
	}
	
    private static  Pattern pattern = Pattern.compile("(?i)[(\\[{]?null[)\\]}]?");

    public static String removeNullString(String value) {
        if (StringUtils.isEmpty(value)) {
            return StringUtils.EMPTY;
        }

        Matcher matcher = pattern.matcher(value);
        return matcher.replaceAll(StringUtils.EMPTY);
    }
	public String getNthQuestionAnswerText(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsAnswer)).getText();
	}
	
	public QuestionsAndAnswers clickNthQuestionShowAnswerLink(int row)
	{
		driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsShowAnswer)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsAnswer), driver);
		
		return this;		
	}
	public QuestionsAndAnswers scrollEndOfResults()
	{
		int totalItemsCount = getCountOfCurrrentlyDisplayingQuestionsFromPagingText();
		int divisor = 12;
		int scrollCounter = 0;
		
		if (totalItemsCount%divisor==0)
			 scrollCounter = (totalItemsCount/divisor)-1;
		else
			 scrollCounter = (totalItemsCount/divisor);	

		for(int i=0;i<scrollCounter;i++)
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
					+ "document.body.scrollHeight,document.documentElement.clientHeight));");

			commonMethods.waitForElementToBeNotVisible(loading, driver);

		}
		return this;
	}
}
