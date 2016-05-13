package webUI.home.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class Discussion {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	//private String discussionHeaderLocator = "div[id*=sls_widgets_components_groups_DiscussionsTab_]";
	private String initialDiscussionThreadLocator = ".groupCommentItem.bodyText1";
	private String editInitialDiscussionThreadButtonLocator = ".groupCommentItem.bodyText1>div>div:nth-of-type(2)>div:nth-of-type(4)>span:nth-of-type(3)>a";
	private String replyInitialDiscussionThreadButtonLocator = ".groupCommentItem.bodyText1>div>div:nth-of-type(2)>div:nth-of-type(4)>span:nth-of-type(1)>a";
	private String deleteInitialDiscussionThreadButtonLocator = ".groupCommentItem.bodyText1>div>div:nth-of-type(2)>div:nth-of-type(4)>span:nth-of-type(4)>a";
	private String initialDiscussionThreadDescriptionLocator = ".groupCommentItem.bodyText1>div>div:nth-of-type(2)>div:nth-of-type(3)";
	private String initialDiscussionThreadSubjectLocator = ".groupCommentItem.bodyText1>div>div:nth-of-type(2)>div:nth-of-type(2)>span:nth-of-type(2)>span";
	private String initialDiscussionThreadTitleLocator = ".groupCommentItem.bodyText1>div>div>h3";
	private String discussionSubjectDropdownLocator = "select[id='discussionSubjectId'] > option";
	private String changeTitleLocator = "div[id*=sls_widgets_components_groups_DiscussionDetailsCommentEditReply]>div>div>div:nth-of-type(1)>";
	private String questionAnsweredWarningLocator = ".groupCommentItem.bodyText1>div>div:nth-of-type(2)>div:nth-of-type(1)";
	private String discussionResultsLocator = "#groupDiscussionResults>div";
	private String countDiscussionRepliesLocator = "div[id*='sls_widgets_components_groups_DiscussionDetails_'] > div:nth-of-type(1) > div > span:nth-of-type(1)";
	
	private String discussionReplyListItemLocator = "#groupDiscussionResults > div:nth-of-type(2) > div";
	private String discussionReplyListItemReplyButtonLocator = "> div > div > div > div:nth-of-type(2) > div:nth-of-type(4) > span:nth-of-type(1)";
	private String discussionReplyListItemDeleteButtonLocator = "> div > div > div > div:nth-of-type(2) > div:nth-of-type(4) > span:nth-of-type(4)>a";
	private String discussionReplyListItemTitleLocator = "> div > div > div > div > h3";
	private String discussionReplyListItemAcceptAnswerLocator = "> div > div > div > div:nth-of-type(2) > div:nth-of-type(4) > span:nth-of-type(2)>a";
	private String discussionReplyListItemAcceptedAnswerLocator = "> div > div > div > div:nth-of-type(2) > div:nth-of-type(2)>div>div.acceptedAnswer";
	
	/* Element Locators */ 
	private By discussionThreadLocator = By.id("groupDiscussionResults");
	private By ckeditor = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	private By descriptionEditorLocator = By.cssSelector(".cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders");
	private By saveButtonLocator = By.cssSelector("button[data-dojo-attach-point='sendBtn']");
	private By cancelLinkLocator = By.cssSelector("a[data-dojo-attach-event='ondijitclick:_clickCancel']");
	private By discussionSubjectSelectLocator = By.id("discussionSubjectId");
	
	
	public Discussion(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}

	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for UI elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(discussionThreadLocator));
		wait.until(ExpectedConditions.elementToBeClickable(discussionThreadLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(editInitialDiscussionThreadButtonLocator)));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(editInitialDiscussionThreadButtonLocator)));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isInitialDiscussionThreadDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(initialDiscussionThreadLocator), driver);
	}
	
	public Boolean isEditInitialDiscussionThreadButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(editInitialDiscussionThreadButtonLocator), driver);
	}
	
	public void clickEditInitialDiscussionThreadButton()
	{
		driver.findElement(By.cssSelector(editInitialDiscussionThreadButtonLocator)).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ckeditor));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void clickReplyInitialDiscussionThreadButton()
	{
		driver.findElement(By.cssSelector(replyInitialDiscussionThreadButtonLocator)).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ckeditor));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public GroupsViewDiscussionsTab clickDeleteInitialDiscussionThreadButton()
	{
		driver.findElement(By.cssSelector(deleteInitialDiscussionThreadButtonLocator)).click();
		return new GroupsViewDiscussionsTab(driver);
	}
	
	public Discussion enterDescription(String desc)
	{
		
		/* Switch to the iframe */
		driver.switchTo().frame(driver.findElement(ckeditor));
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				
		//driver.findElement(descriptionEditorLocator).click();
		driver.findElement(descriptionEditorLocator).clear();
		//driver.findElement(editBackgroundDetailsLocator).sendKeys(desc);
		((JavascriptExecutor) driver).executeScript( "var div = document.getElementsByTagName (\"body\")[0];if (div.contentEditable == \"true\") {div.contentEditable = \"false\";var text=div.innerHTML;div.innerHTML = text+arguments[0];}",desc);
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
		
		return this;
	}
	
	public Boolean isSaveButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(saveButtonLocator, driver);
	}
	
	/**
	 * 
	 * @param browser accepted values are firefox and other
	 * @return Returns a new discussion page
	 */
	public Discussion clickSaveButton(String browser)
	{
		if (browser.equals("firefox")) {
		driver.findElement(saveButtonLocator).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(countDiscussionRepliesLocator), driver);
		}
		else if (browser.equals("other")) {
			driver.findElement(saveButtonLocator).click();
			commonMethods.waitForElementToBeNotVisible(By.cssSelector(discussionResultsLocator), driver);
			commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(discussionResultsLocator), driver);	
		}
		
		return this;
	}
	
	public int getCountOfReplies()
	{
		String str = driver.findElement(By.cssSelector(countDiscussionRepliesLocator)).getText();
		String replies = str.substring(0,str.indexOf(" "));
		int n = Integer.parseInt(replies);
		return n;
	}
	
	public Boolean isChangeTitleLinkDisplayed()
	{
		return commonMethods.isElementDisplayed((By.cssSelector(changeTitleLocator + "h2>a")),driver);
	}
	
	public Boolean isChangeTitleFieldDisplayed()
	{
		return commonMethods.isElementDisplayed((By.cssSelector(changeTitleLocator + "input")),driver);
	}
	
	public Boolean isCancelLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(cancelLinkLocator, driver);
	}
	
	public Boolean isQuestionAnsweredWarningDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(questionAnsweredWarningLocator),driver);
	}
	
	public void clickChangeTitleLink()
	{
		driver.findElement(By.cssSelector(changeTitleLocator + "h2>a")).click();
	}
	
	public void enterNewTitle(String title)
	{
		driver.findElement(By.cssSelector(changeTitleLocator + "input")).clear();
		driver.findElement(By.cssSelector(changeTitleLocator + "input")).sendKeys(title);
	}
	
	public Discussion clickCancelLink()
	{
		driver.findElement(cancelLinkLocator).click();
		return this;
	}
	
	public String getInitialDiscussionThreadTitle()
	{
		return driver.findElement(By.cssSelector(initialDiscussionThreadTitleLocator)).getText();
	}
	
	public String getInitialDiscussionThreadDescription()
	{
		return driver.findElement(By.cssSelector(initialDiscussionThreadDescriptionLocator)).getText();
	}
	
	public String getQuestionAnsweredWarning()
	{
		return driver.findElement(By.cssSelector(questionAnsweredWarningLocator)).getText();
	}
	
	public Discussion selectSubjectFromDropdownByIndex(int index)
	{
		new Select(driver.findElement(discussionSubjectSelectLocator)).selectByIndex(index-1);
		return this;
	}
	
	public String getSelectedTextOfSubjectDropdown()
	{
		Select dropdown = new Select(driver.findElement(discussionSubjectSelectLocator));
		return dropdown.getFirstSelectedOption().getText().toLowerCase();
	}
	
	public int getCountOfOptionsInSubjectDropdown()
	{
		return driver.findElements(By.cssSelector(discussionSubjectDropdownLocator)).size();
	}
	
	public String getInitialDiscussionThreadSubject()
	{
		return driver.findElement(By.cssSelector(initialDiscussionThreadSubjectLocator)).getText().substring(9);
	}
	
	public Boolean isNthQuestionAcceptAnswerButtonDisplayed(int n)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptAnswerLocator),driver);
	}
	
	public String getNthQuestionAcceptAnswerButtonLabel(int n)
	{
		return driver.findElement(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptAnswerLocator)).getText();
	}
	
	public Boolean isNthQuestionAcceptedAnswerMessageDisplayed(int n)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptedAnswerLocator),driver);
	}
	
	public void clickNthQuestionAcceptAnswerButton(int n)
	{
		driver.findElement(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptAnswerLocator)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptedAnswerLocator),driver);
	}
	
	public void clickNthQuestionDeclineAnswerButton(int n)
	{
		driver.findElement(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptAnswerLocator)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemAcceptedAnswerLocator),driver);
	}
	
	public void clickNthQuestionDeleteButton(int n)
	{
		driver.findElement(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemDeleteButtonLocator)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemDeleteButtonLocator), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(deleteInitialDiscussionThreadButtonLocator), driver);
	}
	
	public void clickNthQuestionReplyButton(int n)
	{
		driver.findElement(By.cssSelector(discussionReplyListItemLocator + ":nth-of-type("+n+")" + discussionReplyListItemReplyButtonLocator)).click();
	}
	
	public List<String> getListOfReplyItemTitles()
	{	
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(discussionReplyListItemLocator + discussionReplyListItemTitleLocator)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
		return l;
	}
}
