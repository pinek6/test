package webUI.home.library.mediaTypes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.LocaleStrings;

public class Comments extends MediaType
{	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	private String expertAnswered = new LocaleStrings().getString("EXPERT_ANSWERED");
	private String expertApproved = new LocaleStrings().getString("EXPERT_APPROVED");
	
	/* Element locators */
	private By commentsContainer = By.cssSelector(".commentsContainer");
	private By newCommentWrapper = By.cssSelector(".newCommentWrapper ");
	private By newCommentForm = By.cssSelector(".newCommentForm ");
	private By newCommentButton = By.cssSelector("button[data-dojo-attach-point='newCommentBtn']");
	private By newCommentTitle = By.cssSelector(".title3");
	private By commentBox = By.cssSelector(".newCommentInput");
	private By remainingChars = By.cssSelector("[class='remainingChars']:nth-child(2)");
	private By newCommentSubmitButton = By.cssSelector("button[class='btnStandard btnSmall']");
	private By questionIndicatorCheckbox = By.cssSelector("input[id='cbQuestion']");
	private By anonymousIndicatorCheckbox = By.cssSelector("input[id='cbAnonymous']");
	private By newCommentCancelButton = By.cssSelector("a[data-dojo-attach-event='onclick:commentCancel']");
	private By showTipsLink = By.cssSelector("a[data-dojo-attach-event='onclick:showTips']");
	private By replyBox = By.cssSelector("[class='flyoutWrapper']");
	private By replyCharCount = By.cssSelector("[class='flyoutWrapper'] > div > span[class='charCount']");
	private By currentComment = By.cssSelector("[class='commentEntry current']");
	private By newCommentTips = By.cssSelector(".newCommentTips");
		
	
	
	/* String element locators */	
	//private String commentsWrapper = "//div[contains(@id,'sls_widgets_components_media_CommentWrapper')]";
	private String questionIndicator ="//input[@id='cbQuestion']/../label";
	private String anonymousIndicator ="//input[@id='cbAnonymous']/../label";
	private String commentBody ="[class^='commentEntry']";
	private String replyBody ="[class^='replyEntry']";
	private String commentAuthorname ="[class ='nameNodeLink']";
	private String comment ="[class^='commentBody']";
	private String commentBookmarkIcon ="[class ='btnBookmark']";
	private String commentLikesCounter ="[class^='btnLike']";
	private String commentLike ="[class ='btnLike']";
	private String commentUnlike ="[class ='btnLike selected']";
	private String commentText ="[class ='commentText']";
	private String editLink ="[data-dojo-attach-point='editNode'] > a";
	private String removeLink ="[data-dojo-attach-point='removeNode'] > a";
	//private String answerLink ="[data-dojo-attach-point='answerNode'] > a";
	private String commentNote = "[class='warningText']";
	private String addReplyLink ="[data-dojo-attach-point='addReplyNode'] > a";
	private String answerLink ="[data-dojo-attach-point='answerNode'] > a";
	private String replyContainer ="[class='replyActionContainer']";		
	//private String commentsWrapper = "//div[contains(@id,'sls_widgets_components_media_CommentWrapper')]";
	private String replyTextArea = "[class='flyoutWrapper'] > textarea";
	private String submitReplyButton = "[class='flyoutWrapper'] > div[class='replyAction'] > span:nth-child(1) > a";
	private String cancelReplyButton = "[class='flyoutWrapper'] > div[class='replyAction'] > span:nth-child(2) > a";
	private String replyUsername = "[class='personName1']";
	private String replyEditLink = " > div > span[data-dojo-attach-point='editNode'] > a";
	private String replyDeleteLink = " > div > span[data-dojo-attach-point='removeNode'] > a";
	private String replyApproveLink = " > div > span[data-dojo-attach-point='approveNode'] > a";
	private String replyText = " > div[data-dojo-attach-point='contentNode']";
	private String replyLike = "[class='btnLike']";
	private String replyNote = "[class='authorType']";
	private String replyEditSubmitButton = "[class='flyoutWrapper'] > div[class='replyAction'] > span:nth-child(1) > a";
	//private String replyCancelSubmitButton = "[class='flyoutWrapper'] > div[class='replyAction'] > span:nth-child(2) > a";
	private String moreLessReplies = " > div[class^='repliesListWrapper'] > a";
	
	//private By firstCommentLocator = By.cssSelector(commentBody+":nth-child("+1+") > div > div"+commentText);
	//private By CommentsLocator = By.cssSelector(commentBody+" > div > div"+commentText);
	private By newReplyLocator = By.cssSelector(commentBody+":nth-child("+1+") > div > div:nth-child("+1+") "+ ">div"+replyBody+" > div[data-dojo-attach-point='contentNode']");
	//private By ReplyLocator = By.cssSelector(commentBody+" > div > div "+ ">div"+replyBody+" > div[data-dojo-attach-point='contentNode']");
	
	
	
	
	public Comments(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public Comments clickNewCommentButton()
	{
		driver.findElement(newCommentButton).click();
		commonMethods.waitForElementToBeRefreshed(newCommentForm,driver);
		return this;
	}
	public Boolean isNewCommentTitleDisplayed()
	{
		return driver.findElement(newCommentTitle).isDisplayed();
		
	}
	public Boolean isCommentBoxDisplayed()
	{
		return driver.findElement(commentBox).isDisplayed();
		
	}
	public Boolean isRemainingCharsCounterDisplayed()
	{
		return driver.findElement(remainingChars).isDisplayed();
		
	}
	public Boolean isQuestionIndicatorDisplayed()
	{
		return driver.findElement(By.xpath(questionIndicator)).isDisplayed();
		
	}
	public Boolean isAnonymousIndicatorDisplayed()
	{
		return driver.findElement(By.xpath(anonymousIndicator)).isDisplayed();
		
	}
	public Boolean isNewCommentSubmitButtonDisplayed()
	{
		return driver.findElement(newCommentSubmitButton).isDisplayed();
		
	}
	public Boolean isNewCommentCancelButtonDisplayed()
	{
		return driver.findElement(newCommentCancelButton).isDisplayed();
		
	}
	public Boolean isShowTipsLinkDisplayed()
	{
		return driver.findElement(showTipsLink).isDisplayed();
		
	}
	
	public String getTipsLinkText()
	{
		return driver.findElement(showTipsLink).getText();
		
	}
	
	public Comments clickTipsLink()
	{
		Boolean state = isNewCommentTipsTextDisplayed();
		
		driver.findElement(showTipsLink).click();
		if(state)
			commonMethods.waitForElementToBeNotVisible(newCommentTips, driver);
		else 
			commonMethods.waitForElementToBeVisibleAndClickable(newCommentTips, driver);
		
		return this;		
	}
	
	public Comments enterTextIntoNewCommentBox(String newCommentText)
	{
		driver.findElement(commentBox).sendKeys(newCommentText);
		return this;
	}
	
	public Comments clearTextInNewCommentBox() 
	{
		driver.findElement(commentBox).clear();
		driver.findElement(newCommentTitle).click();
		return this;
	}
	
	public Comments enterTextIntoNewReplyBox(String newReplyText)
	{
		driver.findElement(By.cssSelector(replyTextArea)).sendKeys(newReplyText);
		return this;
	}
	
	public Comments clearTextInReplyBox()
	{
		driver.findElement(By.cssSelector(replyTextArea)).clear();
		driver.findElement(replyCharCount).click();
		return this;
	}
	
	public Comments clickNewCommentSubmitButton()
	{
		driver.findElement(newCommentSubmitButton).click();
		commonMethods.waitForElementToBeNotVisible(newCommentWrapper, driver);
		commonMethods.waitForElementToBeRefreshed(commentsContainer,driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(commentBody),driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(comment),driver);
		return this;
	}
	
	public Comments clickNewCommentCancelButton()
	{
		driver.findElement(newCommentCancelButton).click();
		commonMethods.waitForElementToBeNotVisible(newCommentWrapper, driver);
		commonMethods.waitForElementToBeRefreshed(commentsContainer,driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(commentBody),driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(comment),driver);
		return this;
	}

	public String getNthCommentAuthor(int nth)
	{
		String commentAuthor = driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentAuthorname)).getText().toLowerCase();
		return commentAuthor;
	}
	public Comments clickNthCommentAuthor(int nth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentAuthorname)).click();
		return this;
	}
	
	public Boolean isBookmarkIconDisplayedForNthComment(int nth)
	{
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentBookmarkIcon), driver);
	}
	public int getLikesCounterForNthComment(int nth)
	{	
		int likeCounter = 0;
		
		if(driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentLikesCounter)).getText().equals(""))
			return 0;
		else 
			likeCounter = Integer.parseInt(driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentLikesCounter)).getText());
		return likeCounter;
	}
	
	public Comments clickLikeForNthComment(int nth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentLike)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentLike), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentUnlike), driver);
		return this;
	}
	
	public Comments clickUnlikeForNthComment(int nth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentUnlike)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentUnlike), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(commentBody+":nth-child("+nth+") > div > div > a"+commentLike), driver);
		return this;
	}
		
	public String getNthCommentText(int nth)
	{
		String likeCounter = driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+commentText)).getText();
		return likeCounter;
	}
	public Boolean isEditLinkDisplayedOnNthComment(int nth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink), driver);
	}
	
	public Comments clickEditLinkOnNthComment(int nth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink)).isDisplayed();
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink)).click();
		commonMethods.waitForElementToBeRefreshed(newCommentForm,driver);
		return this;
	}
	
	public Comments clikEditLinkOnNthComment(int nth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(commentBox, driver);
		return this;
	}
	public Boolean isDeleteLinkDisplayedOnNthComment(int nth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+removeLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+removeLink), driver);
	}
	public Boolean isAddReplyLinkDisplayedOnNthComment(int nth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+addReplyLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+addReplyLink), driver);
	}
	public Comments clickAddReplyLinkOnNthComment(int nth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+addReplyLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(replyBox, driver);
		return this;
	}
	public Boolean isAnswerLinkDisplayedOnNthComment(int nth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+answerLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+answerLink), driver);
	}
	
	public Boolean isShowMoreRepliesLinkDisplayed(int nth){
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+")"+moreLessReplies), driver);
	}
	
	public String getShowMoreRepliesLinkText(int nth){
		return  driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+")"+moreLessReplies)).getText();
	}
	
	
	public Comments clickShowMorelessRepliesOnNthComment(int nth){
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+")"+moreLessReplies)).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(commentBody+":nth-child("+nth+")"+moreLessReplies), driver);
		return this;
	}
	
	public Comments clickAnswerLinkDisplayedOnNthComment(int nth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+answerLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(replyBox, driver);
		return this;
	}
	
	public Comments clickDeleteLinkOnNthComment(int nth)
	{
		int i = getCountOfComments();
		
		String element = commentBody+":nth-child("+nth+") > div > div"+replyContainer+" >span"+removeLink;
		
		commonMethods.scrollIntoView(driver, element);
		driver.findElement(By.cssSelector(element)).click();
		if (i>1){
			commonMethods.waitForElementToBeRefreshed(By.cssSelector(commentBody),driver);
			commonMethods.waitForElementToBeRefreshed(By.cssSelector(comment),driver);}
		else
			commonMethods.waitForElementToBeNotVisible(By.cssSelector(commentBody+":nth-child("+1+") > div"), driver);
		return this;
	}
	public int getCountOfComments()
	{
		int countOfComments = driver.findElements(By.cssSelector(commentBody)).size();
		return countOfComments;
	}
	public Boolean isProfilePhotoLoadedOnNthComment(int nth, String email)
	{
		String commentPhotoEmail = driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > img")).getAttribute("src");
		//commentPhotoEmail = commentPhotoEmail.substring(commentPhotoEmail.indexOf("=")+1,commentPhotoEmail.length());
		if ((commentPhotoEmail).contains(email))
			return true;
		else
			return false;
	}
	
	public Boolean isAnonymousPhotoLoadedOnNthComment(int nth)
	{
		String commentPhotoEmail = driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > img")).getAttribute("src");
		if ((commentPhotoEmail).contains("sls/themes/default/images/default_profile.png"))
			return true;
		else
			return false;
	}
	
	public int getCommentPosition(String comment)
	{
		int commentsCount = getCountOfComments();
		int commentPos = 0;
		for (int i=1; i<=commentsCount; i++)
		{
			String currentCommentText = getNthCommentText(i);
			if (currentCommentText.equals(comment))
			{
				commentPos = i;
				break;
			}	
		}
		return commentPos;
	}
	
	public Comments clickQuestionIndicator()
	{
		driver.findElement(questionIndicatorCheckbox).click();
		return this;
	}
	
	public Comments clickAnonymousIndicator()
	{
		driver.findElement(anonymousIndicatorCheckbox).click();
		return this;
	}
	
	public Comments createNewComment(String question,Boolean anonymous,String commentText)
	{
		clickNewCommentButton();
		enterTextIntoNewCommentBox(commentText);
		if(question.equals("question") || question.equals("questionReply")|| question.equals("questionAnswer")){
			clickQuestionIndicator();}
		if(anonymous==true){
			clickAnonymousIndicator();}
		clickNewCommentSubmitButton();
		commonMethods.waitForElementToBeVisibleAndClickable(currentComment, driver);
		return this;
	}
	
	public Comments createNewReplyforNthComment(String replyText,int nth)
	{
		clickAddReplyLinkOnNthComment(nth);
		enterTextIntoNewReplyBox(replyText);
		clickReplySubmitButton();
		commonMethods.waitForTextToBePresentInElement(newReplyLocator, replyText, driver);
		return this;
	}
	
	public Comments editMthReplyOnNthComment(int nth,int mth,String addText)
	{
		clickEditLinkOnMthReplyInNthComment(nth,mth);
		enterTextIntoNewReplyBox(addText);
		clickReplyEditSubmitButton();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyText), driver);
		return this;
	}
	
	
	public Comments createAnswerForNthQuestion(String replyText,int nth)
	{
		clickAnswerLinkDisplayedOnNthComment(nth);
		enterTextIntoNewReplyBox(replyText);
		clickReplySubmitButton();
		commonMethods.waitForTextToBePresentInElement(newReplyLocator, replyText, driver);
		return this;
	}
	
	
	public Comments clickReplySubmitButton()
	{
		driver.findElement(By.cssSelector(submitReplyButton)).click();
		commonMethods.waitForElementToBeNotVisible(replyBox, driver);
		return this;
	}
	
	public Comments clickReplyCancelButton()
	{
		driver.findElement(By.cssSelector(cancelReplyButton)).click();
		commonMethods.waitForElementToBeNotVisible(replyBox, driver);
		return this;
	}

	
	public Comments clickBlankReplySubmitButton()
	{
		driver.findElement(By.cssSelector(submitReplyButton)).click();
		return this;
	}
	
	public String getNoteOnNthComment(int nth)
	{
		String note = "";
		
		if (commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+commentNote), driver))
			note = driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div"+commentNote)).getText();
		else
			note = "No label displayed";
		
		return note;
	}
	
	public int getCountofRepliesForNthComment(int nth)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		int count = driver.findElements(By.cssSelector(commentBody+":nth-child("+nth+") > div > div >div"+replyBody)).size();
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return count; 
	}
	
	public String getUsernameForMthReplyInNthComment(int nth, int mth)
	{
		return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div > a"+replyUsername)).getText();
	}
	public Comments clickUsernameForMthReplyInNthComment(int nth, int mth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div > a"+replyUsername)).click();
		return this;
	}
	
	public String getMthReplyTextInNthComment(int nth, int mth)
	{
		return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyText)).getText();
	}
	
	public Boolean isMthReplyInNthCommentDisplayed(int nth, int mth)
	{	
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody), driver);

	}
	
	public Boolean isEditLinkDisplayedForMthReplyInNthComment(int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+replyEditLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyEditLink), driver);
		
	}
	public Comments clickEditLinkOnMthReplyInNthComment(int nth, int mth)
	{
		driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyEditLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(replyContainer), driver);
		return this;
	}
	
	public Boolean isDeleteLinkDisplayedForMthReplyInNthComment(int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+replyDeleteLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyDeleteLink), driver);
		
	}
	public Comments clickDeleteLinkForMthReplyInNthComment(int nth, int mth)
	{
		String element = commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyDeleteLink;
		
		commonMethods.scrollIntoView(driver, element);
		driver.findElement(By.cssSelector(element)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody), driver);
		return this;
	}
	
	
	public Boolean isApproveLinkDisplayedForMthReplyInNthComment(int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+replyApproveLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyApproveLink), driver);
	}
	
	public Boolean isLikeCounterDisplayedForMthReplyInNthComment(int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+" > div > a"+replyLike)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div > a"+replyLike), driver);
	}
	public String getNoteDisplayedForMthReplyInNthComment(int nth, int mth)
	{
		return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div"+replyNote)).getText();
	}
	public Comments editNthComment(int nth)
	{
		clikEditLinkOnNthComment(nth);
		String commentText = getTextFromCommentBox();
		enterTextIntoNewCommentBox(commentText+"_edited");
		clickNewCommentSubmitButton();
		return this;
	}
	public String getTextFromCommentBox()
	{
		return driver.findElement(commentBox).getText();
	}
	public Comments clickReplyEditSubmitButton()
	{
		driver.findElement(By.cssSelector(replyEditSubmitButton)).click();
		commonMethods.waitForElementToBeNotVisible(replyBox, driver);
		return this;
	}
	
	public boolean isReplyBoxDisplayed(){
		return driver.findElement(replyBox).isDisplayed();
	}
	
	
	public Boolean isNoteOnNthCommentDisplayed(int nth)
	{
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentBody
				+":nth-child("+nth+") > div > div"+commentNote), driver);
	}
	
	public Boolean isNewCommentSubmitButtonDisabled(){
		Boolean returnvalue;
		try 
		{
			if (driver.findElement(newCommentSubmitButton).getAttribute("disabled").equals("true"))
				returnvalue=true;
			else
				returnvalue=false;
			}
		catch (Exception e)
		{
			returnvalue=false;
		}
		return returnvalue;
		
	}
	
	public String getNewCommentTips(){
		return driver.findElement(newCommentTips).getText();
	}
	
	public Boolean isNewCommentTipsTextDisplayed(){
		//return driver.findElement(newCommentTips).isDisplayed();
		return commonMethods.isElementDisplayedInstant(newCommentTips, driver);
	}
	public int getAnswerPositionForNthQuesion(int nth){
		int repliesCount = getCountofRepliesForNthComment(nth);
		int answerPos =0;
		for(int i=1;i<=repliesCount;i++){
			String note = getNoteDisplayedForMthReplyInNthComment(nth,i);
			if (note.equals(expertAnswered)||note.equals(expertApproved)){
				answerPos = i;
				break;
			}
		}
		return answerPos;
	}
}
