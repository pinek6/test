package webUI.home.library.mediaTypes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class MediaType{
	
	public WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */	
	protected By newCommentButton = By.cssSelector("div[id^='sls_widgets_components_media_CommentsContainer'] > div > button.btnStandard");
	protected By mediaTitle = By.cssSelector(".contentTitle");
	protected By mediaBookmark = By.cssSelector("[class='contentMain'] > div > span > [class^='btnBookmark']");
	protected By mediaBookmarkDeselected = By.cssSelector("[class='contentMain'] > div > span > [class='btnBookmark']");
	protected By mediaBookmarkSelected = By.cssSelector("[class='contentMain'] > div > span > [class='btnBookmark selected']");
	protected By mediaLike = By.cssSelector("[class^='contentSocialStats'] > a[class^='btnLike']");
	protected By mediaLiked = By.cssSelector("[class^='contentSocialStats'] > a[class='btnLike selected']");
	protected By mediaUnLiked = By.cssSelector("[class^='contentSocialStats'] > a[class='btnLike']");
	protected By commentsList = By.cssSelector(".commentList.videoComments");
	protected By commentsTitle = By.cssSelector("[class='contentTitle mediaTitle']");
	protected By mediaDescription = By.cssSelector("[class^='contentItemDescription ']");
	protected By contentAuthor = By.cssSelector(".contentItemAuthor > a");
	protected By transcriptTab = By.id("transcriptTab");
	
	protected By bookmarkDialog = By.cssSelector("[class^='bookmarkNotesDialog']");
	protected By bookmarkDialogTextArea = By.cssSelector("[class^='bookmarkNoteTextArea']");
	protected By bookmarkDialogAddButtton = By.id("addButton");
	
	/*String locators*/
	private String alreadyLiked = "btnLike selected";
	
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
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mediaBookmark));
		commonMethods.waitForElementToBeRefreshed(mediaBookmark, driver);
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
		
	public MediaType(WebDriver driver)
	{	
		this.driver = driver;
	}
	
	public void clickNewComment()
	{
		driver.findElement(newCommentButton).click();
	}
	
	public Boolean isNewCommentButtonDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(newCommentButton, driver);
	}	
	
	public String getMediaTitle()
	{
		return driver.findElement(mediaTitle).getText();
	}
	
	public int getMediaBookMarksCount()
	{
		return Integer.parseInt(driver.findElement(mediaBookmark).getText());
	}
	
	public int getMediaLikesCount()
	{
		int count = 0;
		if(driver.findElement(mediaLike).getText().equals(""))
			return count;
		else
			return Integer.parseInt(driver.findElement(mediaLike).getText());
	}
	
	public Boolean isMediaTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(mediaTitle, driver);
	}
	
	public Boolean isMediaBookMarksCountDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(mediaBookmark, driver);
	}
	
	public Boolean isMediaBookMarksIconDisplayed()
	{
		return driver.findElement(mediaBookmark).isDisplayed();
	}
	
	public Boolean isMediaLikesCountDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(mediaLike, driver);
	}
	
	public Boolean isMediaLikesIconDisplayed()
	{
		return driver.findElement(mediaLike).isDisplayed();
	}
	
	public Boolean isCommentsListDisplayed()
	{
		return commonMethods.isElementDisplayed(commentsList, driver);
	}
	
	public MediaType clickContentBookmarksIcon()
	{
		driver.findElement(mediaBookmark).click();
		commonMethods.waitForElementToBeRefreshed(mediaBookmark, driver);
		return this;
	}
	
	public MediaType bookmarkContent(String note)
	{
		driver.findElement(mediaBookmarkDeselected).click();
		commonMethods.waitForElementToBeVisibleAndClickable(bookmarkDialogAddButtton, driver);
		enterBookmarkNote(note);
		clickAddButtonOnBookmakrDialog();
		commonMethods.waitForElementToBeNotVisible(bookmarkDialog, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(mediaBookmarkSelected, driver);
		return this;
	}
	
	public MediaType enterBookmarkNote(String note){
		driver.findElement(bookmarkDialogTextArea).sendKeys(note);
		return this;
	}
	
	public MediaType clickAddButtonOnBookmakrDialog(){
		driver.findElement(bookmarkDialogAddButtton).click();
		return this;
	}
	
	
	public MediaType unbookmarkContent()
	{
		driver.findElement(mediaBookmarkSelected).click();
		driver.findElement(mediaTitle).click();
		commonMethods.waitForElementToBeVisibleAndClickable(mediaBookmarkDeselected, driver);
		return this;
	}	
	
	public MediaType clickLikesIcon()
	{
		driver.findElement(mediaLike).click();
		return this;
	}
	
	public Boolean isBookmarkDeselected()
	{
		return commonMethods.isElementDisplayed(mediaBookmarkDeselected, driver);
	}
	
	public Boolean isBookmarkSelected()
	{
		return commonMethods.isElementDisplayed(mediaBookmarkSelected, driver);
	}
	
	public String getContentTitle()
	{
		return driver.findElement(commentsTitle).getText();
	}
	
	public String getContentDescription()
	{
		String contentDescription =  driver.findElement(mediaDescription).getText();
		contentDescription = contentDescription.replaceAll("\\s+","");
		return contentDescription;
	}
	public Boolean isContentAlreadyLiked(){
		
		return driver.findElement(mediaLike).getAttribute("class").equals(alreadyLiked);
		
	}
	
	public MediaType clickContentLikeButton(){
		
		commonMethods.waitForElementToBeVisibleAndClickable(mediaLike, driver);
		
		Boolean alreadyLiked = isContentAlreadyLiked();
		driver.findElement(mediaLike).click();
		if(alreadyLiked) 
			commonMethods.waitForElementToBeVisibleAndClickable(mediaUnLiked, driver);
		else
			commonMethods.waitForElementToBeVisibleAndClickable(mediaLiked, driver);
		return this;
	}
	
	public MediaType clickContentAuthorLink(){
		driver.findElement(contentAuthor).click();
		return this;
	}
	public String getContentType(){
		String contentURL = driver.getCurrentUrl();
		String contentType = "unknown";
		
		if(contentURL.contains("video"))
			contentType = "video";
		else if(contentURL.contains("pdf"))
			contentType = "pdf";
		else if(contentURL.contains("simulator"))
			contentType = "simulator";
		else if(contentURL.contains("calculator"))
			contentType = "calculator";
		
		return contentType;
	}
}
