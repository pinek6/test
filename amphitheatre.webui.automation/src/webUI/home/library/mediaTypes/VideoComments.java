package webUI.home.library.mediaTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class VideoComments extends Comments
{	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	
	/* String element locators */	
	private String commentBody ="[class^='commentEntry']";
	private String commentAuthorname ="[class ='nameNodeLink']";
	private String commentBookmarkIcon ="[class ='btnBookmark']";
	private String commentLike ="[class ='btnLike']";
	private String commentText ="[class ='commentText']";
	private String editLink ="[data-dojo-attach-point='editNode'] > a";
	private String removeLink ="[data-dojo-attach-point='removeNode'] > a";
	private String answerLink ="[data-dojo-attach-point='answerNode'] > a";
	private String addReplyLink ="[data-dojo-attach-point='addReplyNode'] > a";
	private String replyContainer ="[class='replyActionContainer']";		
	private String commentsWrapper = "//div[contains(@id,'sls_widgets_components_media_CommentWrapper')]";
	private String commentClusters = "[class^='clusterEntry']";
	private String commentNote = "[class='warningText']";
	private By replyBox = By.cssSelector("[class='flyoutWrapper']");
	private String replyBody ="[class^='replyEntry']";
	private String replyText = " > div[data-dojo-attach-point='contentNode']";	
	private String replyUsername = "[class='personName1']";
	private String replyEditLink = " > div > span[data-dojo-attach-point='editNode'] > a";
	private String replyDeleteLink = " > div > span[data-dojo-attach-point='removeNode'] > a";
	private String replyApproveLink = " > div > span[data-dojo-attach-point='approveNode'] > a";
	private String replyLike = "[class='btnLike']";
	private String replyNote = "[class='authorType']";
	private String replyEditSubmitButton = "[class='flyoutWrapper'] > div[class='replyAction'] > span:nth-child(1) > a";
	private String replyCancelSubmitButton = "[class='flyoutWrapper'] > div[class='replyAction'] > span:nth-child(2) > a";
	
	private By newCommentLocator = By.cssSelector(commentClusters+":nth-child("+1+") > div > div"+commentBody+":nth-child("+1+") > div > div"+commentText);
	private By newReplyLocator = By.cssSelector(commentClusters+":nth-child("+1+") > div > div"+commentBody+":nth-child("+1+") > div > div:nth-child("+1+") "+ ">div"+replyBody+" > div[data-dojo-attach-point='contentNode']");

		
	public VideoComments(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}

	public String getNthCommentAuthor(int cluster, int nth)
	{
		String commentAuthor = driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div > a"+commentAuthorname)).getText().toLowerCase();
		return commentAuthor;
	}
	public Boolean isBookmarkIconDisplayedForNthComment(int cluster, int nth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div > a"+commentBookmarkIcon)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div > a"+commentBookmarkIcon), driver);
	}
	public int getLikesCounterForNthComment(int cluster, int nth)
	{
		int likeCounter = 0;
		if(driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div > a"+commentLike)).getText().equals(""))
			return 0;
		else 
			likeCounter = Integer.parseInt(driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div > a"+commentLike)).getText());
		return likeCounter;
	}
	public String getNthCommentText(int cluster, int nth)
	{
		String commentText1 = driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+commentText)).getText();
		return commentText1;
	}
	public Boolean isEditLinkDisplayedOnNthComment(int cluster, int nth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+editLink), driver);
	}
	public Boolean isAnswerLinkDisplayedOnNthComment(int cluster, int nth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+answerLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+answerLink), driver);
	}
	
	public Boolean isDeleteLinkDisplayedOnNthComment(int cluster, int nth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+removeLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+removeLink), driver);
	}
	public Boolean isAddReplyLinkDisplayedOnNthComment(int cluster, int nth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+addReplyLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+addReplyLink), driver);
		
	}
	public VideoComments clickDeleteLinkOnNthComment(int cluster, int nth)
	{
		driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+removeLink)).click();
		commonMethods.waitForElementToBeRefreshed(By.xpath(commentsWrapper),driver);
		return this;
	}
	public int getCountOfComments()
	{
		int countOfComments = driver.findElements(By.cssSelector(commentBody)).size();
		return countOfComments;
	}
	public Boolean isProfilePhotoLoadedOnNthComment(int cluster, int nth, String email)
	{
		String commentPhotoEmail = driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > img")).getAttribute("src");
		//commentPhotoEmail = commentPhotoEmail.substring(commentPhotoEmail.indexOf("=")+1,commentPhotoEmail.length());
		if ((commentPhotoEmail).contains(email))
			return true;
		else
			return false;
	}
	public int getCountOfCommentClusters()
	{
		int clusterCounter = driver.findElements(By.cssSelector(commentClusters)).size();
		return clusterCounter;
	}
	
	public int getCountOfCommentsInNthCluster(int nth)
	{
		int clusterCounter = driver.findElements(By.cssSelector(commentClusters+":nth-child("+nth+") > div > div")).size();
		return clusterCounter;
	}
	
	public List<Integer> getListOfCommentsInClusters()
	{	
		List<Integer> commentsList=new ArrayList<Integer>();
	
		int clusterCounter = getCountOfCommentClusters();
		for (int i=1;i<=clusterCounter;i++)
			{
				commentsList.add(getCountOfCommentsInNthCluster(i));
			}
		return commentsList; 
	}
	
	public Boolean isCommentExist(String comment)
	{
		List<Integer> commentsList = getListOfCommentsInClusters();
		
		int clusterCounter = commentsList.size();
		int existsIndicator = 0;
		
		for (int i=1;i<=clusterCounter;i++)
		{
			int commentsCounter = commentsList.get(i-1);
			for (int j=1;j<=commentsCounter;j++)
			{
				String currentComment = driver.findElement(By.cssSelector(commentClusters+":nth-child("+i+") > div > div"+commentBody+":nth-child("+j+") > div > div"+commentText)).getText();
				if (comment.equals(currentComment))
					existsIndicator++;
			}
		}
		if (existsIndicator == 0)
			return true;
		else
			return false;
	}
	public int getClusterForCommentText(String comment)
	{
		List<Integer> commentsList = getListOfCommentsInClusters();
		
		int clusterCounter = commentsList.size();
		int cluster = 0;
		outerloop:
		for (int i=1;i<=clusterCounter;i++)
		{
			int commentsCounter = commentsList.get(i-1);
			for (int j=1;j<=commentsCounter;j++)
			{
				String currentComment = driver.findElement(By.cssSelector(commentClusters+":nth-child("+i+") > div > div"+commentBody+":nth-child("+j+") > div > div"+commentText)).getText();
				if (comment.equals(currentComment))
					{
						cluster = i;
						break outerloop;
					}	
			}
		}
		return cluster;
	}	
	public int getPosForCommentText(String comment)
	{
		List<Integer> commentsList = getListOfCommentsInClusters();
		
		int clusterCounter = commentsList.size();
		int commentPos = 0;
		outerloop:
		for (int i=1;i<=clusterCounter;i++)
		{
			int commentsCounter = commentsList.get(i-1);
			for (int j=1;j<=commentsCounter;j++)
			{
				String currentComment = driver.findElement(By.cssSelector(commentClusters+":nth-child("+i+") > div > div"+commentBody+":nth-child("+j+") > div > div"+commentText)).getText();
				if (comment.equals(currentComment))
					{
					commentPos = j;
					break outerloop;
					}	
			}
		}
		return commentPos;
	}
	public Boolean isAnonymousPhotoLoadedOnNthComment(int cluster, int nth)
	{
		String commentPhotoEmail = driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > img")).getAttribute("src");
		if ((commentPhotoEmail).contains("sls/themes/default/images/default_profile.png"))
			return true;
		else
			return false;
	}
	public String getNoteOnNthComment(int cluster, int nth)
	{
		String note = "";
		
		if (commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+commentNote), driver))
			note = driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+commentNote)).getText();
		else
			note = "No label displayed";
		
		return note;
	}
	public Boolean isNoteOnNthCommentDisplayed(int cluster, int nth)
	{
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody
				+":nth-child("+nth+") > div > div"+commentNote), driver);
	}
	
	public int getCountofRepliesForNthComment(int cluster, int nth)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		int count = driver.findElements(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+
				  commentBody+":nth-child("+nth+") > div > div >div"+replyBody)).size();
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return count; 
	}
	public Comments createNewReplyforNthComment(String replyText,int cluster, int nth)
	{
		clickAddReplyLinkOnNthComment(cluster, nth);
		enterTextIntoNewReplyBox(replyText);
		clickReplySubmitButton();
		commonMethods.waitForTextToBePresentInElement(newReplyLocator, replyText, driver);
		return this;
	}
	public Comments clickAddReplyLinkOnNthComment(int cluster, int nth)
	{
		driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+addReplyLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(replyBox, driver);
		return this;
	}
	public String getMthReplyTextInNthComment(int cluster, int nth, int mth)
	{
		return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyText)).getText();
	}
	public String getUsernameForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div > a"+replyUsername)).getText();
	}
	public Boolean isEditLinkDisplayedForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+replyEditLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyEditLink), driver);
	}
	public Boolean isDeleteLinkDisplayedForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+replyDeleteLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyDeleteLink), driver);
	}
	public Boolean isLikeCounterDisplayedForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+" > div > a"+replyLike)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div > a"+replyLike), driver);
	}
	public Boolean isApproveLinkDisplayedForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		//return driver.findElement(By.cssSelector(commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
		//		+ ">div"+replyBody+replyApproveLink)).isDisplayed();
		return commonMethods.isElementDisplayedInstant(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyApproveLink), driver);
	}
	public Comments clickDeleteLinkForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyDeleteLink)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody), driver);
		return this;
	}
	public Comments createAnswerForNthQuestion(String replyText,int cluster, int nth)
	{
		clickAnswerLinkDisplayedOnNthComment(cluster, nth);
		enterTextIntoNewReplyBox(replyText);
		clickReplySubmitButton();
		commonMethods.waitForTextToBePresentInElement(newReplyLocator, replyText, driver);
		return this;
	}
	public Comments clickAnswerLinkDisplayedOnNthComment(int cluster, int nth)
	{
		driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div"+replyContainer+" span"+answerLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(replyBox, driver);
		return this;
	}
	public String getNoteDisplayedForMthReplyInNthComment(int cluster, int nth, int mth)
	{
		return driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+" > div"+replyNote)).getText();
	}
	
	public Comments editMthReplyOnNthComment(int cluster, int nth, int mth, String editText)
	{
		clickEditLinkOnMthReplyInNthComment(cluster,nth,mth);
		enterTextIntoNewReplyBox(editText);
		clickReplyEditSubmitButton();
		return this;
	}
	public Comments clickEditLinkOnMthReplyInNthComment(int cluster, int nth, int mth)
	{
		driver.findElement(By.cssSelector(commentClusters+":nth-child("+cluster+") > div > div"+commentBody+":nth-child("+nth+") > div > div:nth-child("+mth+") "
				+ ">div"+replyBody+replyEditLink)).click();
		
		return this;
	}
	public int getAnswerPositionForNthQuesion(int cluster,int comment){
		int repliesCount = getCountofRepliesForNthComment(cluster,comment);
		int answerPos =0;
		for(int i=1;i<=repliesCount;i++){
			String note = getNoteDisplayedForMthReplyInNthComment(cluster,comment,i);
			if (note.equals("Expert Answered")){
				answerPos = i;
				break;
			}
		}
		return answerPos;
	}

}
