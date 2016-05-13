package webUI.home.questionsAndAnswers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class QuestionsICanAnswer extends QuestionsAndAnswers{
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Contructors */
	private String questionListItems = ".columnsRightItemBig";
	private String questionListItemsTitle = " > div[class='questionLine'] > a";
	private String questionListItemsStatus = " > div[class='answerLine'] > span:nth-child(1)";
	private String questionListItemsContext = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='contextLine sqaQuestionLine'] > span";
	private String questionListItemsAsked = " > div[class='answerLine'] > span[class^='asked']";
	private String questionListItemsLikes = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='likes']";
	private String questionListItemsReplies = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='replies']";
	private String questionListItemsLastUpdatedDate = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='lastUpdated']";
	private String questionListItemsLastUpdatedBy = " >div[data-dojo-attach-point='lessMoreScope'] > div[class='miscLine sqaQuestionLine'] > span[class^='lastUpdated'] > a";
	private String questionListItemsExpertizeArea = " > div[data-dojo-attach-point='lessMoreScope'] > div[class='topicLine sqaQuestionLine'] > span";
	private String expertisesList = ".facetOptionList > li";
	private String expertises = "> label";
	private String expertisesInput = "> input";
	private String myExpertiseAreas = ".slsPopupParagraphQA";
	private String unansweredQuestions = " > div[data-dojo-attach-point='unansweredQuestionsLinkNode'] > a";
	
	/*  Element Locators */
	private By noQuestionsFound = By.cssSelector("div[class='columnsLeftMain'] > div:nth-child(3) > div:nth-child(1)");

	public QuestionsICanAnswer(WebDriver driver)
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
		wait.until(ExpectedConditions.visibilityOfElementLocated(showDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(showDropdown));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagingTextLocator));
		wait.until(ExpectedConditions.elementToBeClickable(pagingTextLocator));	
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public Boolean isNoQuestionsFound()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(noQuestionsFound, driver);
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

	public String getNthQuestionLikes(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLikes)).getText();
	}
	
	public Boolean isNthQuestionLikesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLikes), driver);
	}
	
	public String getNthQuestionReplies(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsReplies)).getText();
	}
	
	public Boolean isNthQuestionRepliesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsReplies), driver);
	}
	
	public String getNthQuestionLastUpdatedDate(int row)
	{
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+row+")" + questionListItemsLastUpdatedDate)).getText();
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
	
	public int getCountOfExpertises(){
		
		return driver.findElements(By.cssSelector(expertisesList+expertises)).size();
	}
	
	public QuestionsICanAnswer clickNthExpertize(int nth){
		String element = expertisesList+":nth-child("+nth+")"+expertises;
		commonMethods.scrollIntoView(driver, element);
		driver.findElement(By.cssSelector(element)).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
	public String getNthExpertizeText(int nth){
		
		return driver.findElement(By.cssSelector(expertisesList+":nth-child("+nth+")"+expertises)).getText();
	}
	
	public Boolean isNthExpertizeSelected(int nth){
		
		return driver.findElement(By.cssSelector(expertisesList+":nth-child("+nth+")"+expertisesInput)).isSelected()? true : false;
	}
	
	public String getNthQuestionExpertize(int nth){
		return driver.findElement(By.cssSelector(questionListItems+":nth-child("+nth+")"+questionListItemsExpertizeArea)).getText();
	}
	
	public QuestionsICanAnswer clickNthUnansweredQuestionLink(int nth){
		String element = myExpertiseAreas+":nth-child("+nth+")"+unansweredQuestions;
		commonMethods.scrollIntoView(driver, element);
		driver.findElement(By.cssSelector(element)).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
public int getCountOfExpertisesFromExpertiseAreas(){
		
		return driver.findElements(By.cssSelector(myExpertiseAreas)).size();
	}
}
