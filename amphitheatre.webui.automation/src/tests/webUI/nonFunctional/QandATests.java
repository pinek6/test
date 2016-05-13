package tests.webUI.nonFunctional;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;
import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.library.Library;
import webUI.home.library.mediaTypes.Comments;
import webUI.home.library.mediaTypes.MediaType;
import webUI.home.library.mediaTypes.VideoComments;
import webUI.home.questionsAndAnswers.AllQuestions;
import webUI.home.questionsAndAnswers.MyQuestions;
import webUI.home.questionsAndAnswers.QuestionsICanAnswer;

public class QandATests extends LoadBrowser {
	
	/* Page Headers */
	private String questionsAndAnswersPageHeader = new LocaleStrings().getString("ASK_PAGE_HEADER");
	private String allAnswered = new LocaleStrings().getString("ALL_ANSWERED");
	private String allUnanswered = new LocaleStrings().getString("ALL_UNANSWERED");
	private String allQuestions = new LocaleStrings().getString("ALL_QUESTIONS");
	
	private String allMyAnswered = new LocaleStrings().getString("ALL_MY_ANSWERED");
	private String allMyUnanswered = new LocaleStrings().getString("ALL_MY_UNANSWERED");
	private String allMyQuestions = new LocaleStrings().getString("ALL_MY_QUESTIONS");
	
	private String answeredStatus = new LocaleStrings().getString("ANSWERED_STATUS");
	private String unansweredStatus = new LocaleStrings().getString("UNANSWERED_STATUS");
	private String OTHER_CITY = new LocaleStrings().getString("OTHER_CITY");
	private String myQuestionsAllEmptyState = new LocaleStrings().getString("MY_QUESTIONS_ALL_EMPTY_STATE");
	private String myQuestionsUnansweredEmptyState = new LocaleStrings().getString("MY_QUESTIONS_UNANSWERED_EMPTY_STATE");
	private String myQuestionsAnsweredEmptyState = new LocaleStrings().getString("MY_QUESTIONS_ANSWERED_EMPTY_STATE");
	private String asked1mAgo = new LocaleStrings().getString("ASKED_1M_AGO");
	private String lastUpdate1mAgoByYou = new LocaleStrings().getString("LAST_UPDATE_1M_AGO_BY_YOU");
	
	private String expertiseArea = new LocaleStrings().getString("EXPERTISE_AREA");
	
	private String email="", displayname, fname, lname, occupation, country, city, password;
	private int postGradExperience, specialty, subSpecialty, interest;
	
	
	public String createNewMember()
	{		
		logIntoAdminConsole();
		
		/* Set the email and displayname */
		Date d = new Date();
		String email = "fvt"+d.getTime()+"@bluebox.lotus.com";
		displayname = "z_fvt_"+d.getTime();
		fname = "z_fname";
		lname = "l_fname_"+d.getTime();
		occupation="Surgeon"; 
		country="United States"; 
		city=OTHER_CITY; 
		password="Pa88w0rdfvt";
		postGradExperience=1;
		specialty=1;
		subSpecialty=1;
		interest=1;
		
		/* Click Members - People in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		People peoplePage = leftnav.clickPeople();
				
		/* Click Create Member button */
	    CreateMember createMemberPage = peoplePage.clickCreateMember();   
	    
	    /*Select expertise*/
	    int counter = createMemberPage.getNumberOfExpertise();
	    for(int i=1;i<=counter;i++){
	    	createMemberPage.selectNthExpertise(i);
	    }
	    
	    /* Fill in the member details */
		Boolean memberCreated = createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname, occupation, postGradExperience, specialty, subSpecialty, interest,  country, city, password);		

		Assert.assertTrue(memberCreated,"Failed to create new member");		
		//Reporter.log("Member successfully created: " + email);
		
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
				
		return email;
	}
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the My Questions page loads successfully")
	public void myQuestionsPageOpens()
	{
		getDriverAndLogin();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		MyQuestions myQuestionsPage = navigation.clickMyQuestions();

		Assert.assertTrue(myQuestionsPage.getHeader().contains(questionsAndAnswersPageHeader),"Incorrect header on the Questions and Answers page");
		Assert.assertTrue(myQuestionsPage.isMyQuestionsTabSelected(),"My Questions tab should be selected");
		Assert.assertFalse(myQuestionsPage.isQuestionsICanAnswerTabSelected(),"Questions I Can Answer tab should not be selected");
		Assert.assertFalse(myQuestionsPage.isAllQuestionsTabSelected(),"All Questions tab should not be selected");

		/* Get the number of questions displayed */
		int numberOfQuestionsDisplayed = myQuestionsPage.getCountOfQuestionsDisplayed();

		if (numberOfQuestionsDisplayed==0)
		{
			/* If no questions are found, verify that text appears in it's place */
			Assert.assertTrue(myQuestionsPage.isNoQuestionsFound(),"No Results text not found");
		}
		else
		{
			/* Loop through the questions and verify UI elements appears */
			for (int i = 1; i<=numberOfQuestionsDisplayed; i++)
			{
				Assert.assertTrue(myQuestionsPage.isNthQuestionTitleDisplayed(i),"Title not displaying for question #" + i);
				Assert.assertTrue(myQuestionsPage.isNthQuestionStatusDisplayed(i),"Status not displaying for question #" + i);
				Assert.assertTrue(myQuestionsPage.isNthQuestionLikesDisplayed(i),"Likes count not displaying for question #" + i);
				Assert.assertTrue(myQuestionsPage.isNthQuestionRepliesDisplayed(i),"Replies count not displaying for question #" + i);
			}
		}	
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the My Questions page loads successfully")
	public void allQuestionsPageOpens()
	{
		getDriverAndLogin();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		AllQuestions allQuestionsPage = navigation.clickAllQuestions();

		Assert.assertTrue(allQuestionsPage.getHeader().contains(questionsAndAnswersPageHeader),"Incorrect header on the Questions and Answers page");
		Assert.assertTrue(allQuestionsPage.isAllQuestionsTabSelected(),"All Questions tab should be selected");
		Assert.assertFalse(allQuestionsPage.isQuestionsICanAnswerTabSelected(),"Questions I Can Answer tab should not be selected");
		Assert.assertFalse(allQuestionsPage.isMyQuestionsTabSelected(),"My Questions tab should not be selected");

		/* Get the number of questions displayed */
		int numberOfQuestionsDisplayed = allQuestionsPage.getCountOfQuestionsDisplayed();

		if (numberOfQuestionsDisplayed==0)
		{
			/* If no questions are found, verify that text appears in it's place */
			Assert.assertTrue(allQuestionsPage.isNoQuestionsFound(),"No Results text not found");
		}
		else
		{
			/* Loop through the questions and verify UI elements appears */
			for (int i = 1; i<=numberOfQuestionsDisplayed; i++)
			{
				//Assert.assertTrue(allQuestionsPage.isNthQuestionTitleDisplayed(i),"Title not displaying for question #" + i);
				Assert.assertTrue(allQuestionsPage.isNthQuestionStatusDisplayed(i),"Status not displaying for question #" + i);
				Assert.assertTrue(allQuestionsPage.isNthQuestionLikesDisplayed(i),"Likes count not displaying for question #" + i);
				Assert.assertTrue(allQuestionsPage.isNthQuestionRepliesDisplayed(i),"Replies count not displaying for question #" + i);
			}
		}	
	}
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Questions I Can Answer page loads successfully")
	public void questionsICanAnswerPageOpens()
	{
		getDriverAndLogin();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		QuestionsICanAnswer questionsICanAnswerPage = navigation.clickQuestionsICanAnswer();

		Assert.assertTrue(questionsICanAnswerPage.getHeader().contains(questionsAndAnswersPageHeader),"Incorrect header on the Questions and Answers page");
		Assert.assertTrue(questionsICanAnswerPage.isQuestionsICanAnswerTabSelected(),"Questions I Can Answer tab should be selected");
		Assert.assertFalse(questionsICanAnswerPage.isAllQuestionsTabSelected(),"All Questions tab should not be selected");
		Assert.assertFalse(questionsICanAnswerPage.isMyQuestionsTabSelected(),"My Questions tab should not be selected");

		/* Get the number of questions displayed */
		int numberOfQuestionsDisplayed = questionsICanAnswerPage.getCountOfQuestionsDisplayed();

		if (numberOfQuestionsDisplayed==0)
		{
			/* If no questions are found, verify that text appears in it's place */
			Assert.assertTrue(questionsICanAnswerPage.isNoQuestionsFound(),"No Results text not found");
		}
		else
		{
			/* Loop through the questions and verify UI elements appears */
			for (int i = 1; i<=numberOfQuestionsDisplayed; i++)
			{
				Assert.assertTrue(questionsICanAnswerPage.isNthQuestionTitleDisplayed(i),"Title not displaying for question #" + i);
				Assert.assertTrue(questionsICanAnswerPage.isNthQuestionStatusDisplayed(i),"Status not displaying for question #" + i);
				Assert.assertTrue(questionsICanAnswerPage.isNthQuestionLikesDisplayed(i),"Likes count not displaying for question #" + i);
				Assert.assertTrue(questionsICanAnswerPage.isNthQuestionRepliesDisplayed(i),"Replies count not displaying for question #" + i);
			}
		}	
	}
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods={"allQuestionsPageOpens"}, description="Verifies that user can select only answered questions in dropdown.")
	public void AllQuestionsAnsweredOnly()
	{
		getDriverAndLogin();
		//Open all questions tab in QandA
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		AllQuestions allQuestionsPage = navigation.clickAllQuestions();
		
		//Expand show dropdown
		allQuestionsPage.clickOnShowDropDown();
		
		//Verify that correct options appears in dropdown		
		Assert.assertEquals(allQuestionsPage.getNthOptionFromShowDropdown(1),allQuestions,"First option in show dropdown not displayed as expected.");
		Assert.assertEquals(allQuestionsPage.getNthOptionFromShowDropdown(2),allAnswered,"Second option in show dropdown not displayed as expected.");
		Assert.assertEquals(allQuestionsPage.getNthOptionFromShowDropdown(3),allUnanswered,"Third option in show dropdown not displayed as expected.");
		
		//click All answered option in dropdown
		allQuestionsPage.selectOptionInDropdown(allAnswered);
		
		//Get Count of Displayed Items
		int questionsCounter = allQuestionsPage.getCountOfDisplayedQuestions();
		
		//Fail test if there is not answered questions displayed
		if(questionsCounter==0)
			Assert.fail("No answered questions on the list");
		
		//Verify that correct elements appears in displayed results
		for(int i=1;i<=questionsCounter;i++){
			Assert.assertEquals(allQuestionsPage.getNthQuestionStatus(i),answeredStatus,"Status on question #"+i+" is not marked as answered");
			Assert.assertTrue(allQuestionsPage.isNthQuestionTitleDisplayed(i),"Title not displaying for question #" + i);
			Assert.assertTrue(allQuestionsPage.isNthQuestionLikesDisplayed(i),"Likes count not displaying for question #" + i);
			Assert.assertTrue(allQuestionsPage.isNthQuestionRepliesDisplayed(i),"Replies count not displaying for question #" + i);
			Assert.assertTrue(allQuestionsPage.isNthQuestionShowAnswerLinkDisplayed(i),"Show answer link is not displayed for question #" + i);		
		}
	}
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods={"allQuestionsPageOpens"}, description="Verifies that user can select only unanswered questions in dropdown.")
	public void AllQuestionsUnansweredOnly()
	{
		getDriverAndLogin();
		//Open all questions tab in QandA
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		AllQuestions allQuestionsPage = navigation.clickAllQuestions();
		
		//Expand show dropdown
		allQuestionsPage.clickOnShowDropDown();
		
		//Verify that correct options appears in dropdown		
		Assert.assertEquals(allQuestionsPage.getNthOptionFromShowDropdown(1),allQuestions,"First option in show dropdown not displayed as expected.");
		Assert.assertEquals(allQuestionsPage.getNthOptionFromShowDropdown(2),allAnswered,"Second option in show dropdown not displayed as expected.");
		Assert.assertEquals(allQuestionsPage.getNthOptionFromShowDropdown(3),allUnanswered,"Third option in show dropdown not displayed as expected.");
		
		//click All answered option in dropdown
		allQuestionsPage.selectOptionInDropdown(allUnanswered);
		
		//Get Count of Displayed Items
		int questionsCounter = allQuestionsPage.getCountOfDisplayedQuestions();
		
		//Fail test if there is not unanswered questions displayed
		if(questionsCounter==0)
			Assert.fail("No unanswered questions on the list");
		
		//Verify that correct elements appears in displayed results
		for(int i=1;i<=questionsCounter;i++){
			Assert.assertEquals(allQuestionsPage.getNthQuestionStatus(i),unansweredStatus,"Status on question #"+i+" is not marked as unanswered");
			Assert.assertTrue(allQuestionsPage.isNthQuestionTitleDisplayed(i),"Title not displaying for question #" + i);
			Assert.assertTrue(allQuestionsPage.isNthQuestionLikesDisplayed(i),"Likes count not displaying for question #" + i);
			Assert.assertTrue(allQuestionsPage.isNthQuestionRepliesDisplayed(i),"Replies count not displaying for question #" + i);
			Assert.assertFalse(allQuestionsPage.isNthQuestionShowAnswerLinkDisplayed(i),"Show answer link is not displayed for question #" + i);		
		}
	}	
	@Test(enabled = false, groups={"BVT","FullRegression"}, dependsOnMethods={"allQuestionsPageOpens"}, description="Verifies that user can open answered question from All Questions tab.")
	public void OpenAnsweredQuestionFromAllQuestionsTab()
	{
		//FIXME
		getDriverAndLogin();
		//Open all questions tab in QandA
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		AllQuestions allQuestionsPage = navigation.clickAllQuestions();
		
		//click All answered option in dropdown
		allQuestionsPage.selectOptionInDropdown(allAnswered);
		
		//Get count of Displayed Items
		int questionsCounter = allQuestionsPage.getCountOfDisplayedQuestions();
		
		//Fail test if there is none answered questions displayed
		if(questionsCounter==0)
			Assert.fail("No answered questions on the list in 'All questions' tab");
		
		//Set variable for question used in test
		int row = 1;
		
		//Get test question details
		String questionText = allQuestionsPage.getNthQuestionTitle(row);
		Boolean isAnswered= allQuestionsPage.getNthQuestionStatus(row).equals(answeredStatus);
		allQuestionsPage.clickNthQuestionShowAnswerLink(row);
		String answer = allQuestionsPage.getNthQuestionAnswerText(row);
		String contentTitle = allQuestionsPage.getNthQuestionContentTitle(row);
		String contentType=allQuestionsPage.getNthQuestionContentType(row);
		int likes = allQuestionsPage.getNthQuestionLikes(row);
		int replies = allQuestionsPage.getNthQuestionReplies(row);

		//Click on test question
		allQuestionsPage.clickNthQuestionTitle(row);
		
		//Load Media page
		MediaType mediaType = new MediaType(getDriver());
		mediaType.waitForPageToLoad(getDriver());
		
		//Load comments panels
		Comments comments = new Comments(getDriver());
		VideoComments videoComments = new VideoComments(getDriver());
		
		//Get comment position 
		int commentPos=0,commentCluster=0;
		if(contentType.equals("video")){
			commentCluster = videoComments.getClusterForCommentText(questionText);
			commentPos = videoComments.getCommentPosition(questionText);
		}
		else
			commentPos = comments.getCommentPosition(questionText);
		
		//Get data for test question from comments panel
		String questionText2;
		Boolean isAnswered2;
		String answer2;
		String contentTitle2 = mediaType.getContentTitle();
		String contentType2 = mediaType.getContentType();
	
		int likes2=0, replies2=0, answerPos=0;
		
		if(contentType.equals("video")){
			questionText2 = videoComments.getNthCommentText(commentCluster, commentPos);
			isAnswered2 = 	videoComments.getNoteOnNthComment(commentCluster,commentPos).equals("Answered");
			answerPos = 	videoComments.getAnswerPositionForNthQuesion(commentCluster,commentPos);
			answer2 = 		videoComments.getMthReplyTextInNthComment(commentCluster,commentPos, answerPos);
			likes2 = 		videoComments.getLikesCounterForNthComment(commentCluster, commentPos);
			replies2 = 		videoComments.getCountofRepliesForNthComment(commentCluster, commentPos);
		}
		else{
			questionText2 = comments.getNthCommentText(commentPos);
			isAnswered2 = 	comments.getNoteOnNthComment(commentPos).equals("Answered");
			answerPos = 	comments.getAnswerPositionForNthQuesion(commentPos);
			answer2 = 		comments.getMthReplyTextInNthComment(commentPos, answerPos);
			likes2 = 		comments.getLikesCounterForNthComment(commentPos);
			replies2 = 		comments.getCountofRepliesForNthComment(commentPos);
		}
		
		/*Verify that correct elements appears*/
		Assert.assertEquals(questionText, questionText2,"Question text does not match.");
		Assert.assertEquals(isAnswered, isAnswered2,"Answered status does not match.");
		Assert.assertEquals(answer, answer2,"Answer text does not match.");
		Assert.assertEquals(contentTitle, contentTitle2,"Content title does not match.");
		Assert.assertEquals(contentType, contentType2,"Content type does not match.");
		Assert.assertEquals(likes, likes2,"Like counter does not match.");
		Assert.assertEquals(replies, replies2,"Replies counter does not match.");
	}
	@Test(groups={"FullRegression"}, dependsOnMethods={"myQuestionsPageOpens"}, description="Verifies empty state pages for My Question tab")
	public void myQuestionsEmptyStates()
	{
		
		/*Create new user if not already created*/
		if(email.equals(""))
			email = createNewMember();
		
		/*Login with new user*/
		getDriverAndLogin(email,password);
		
		/*Navigate to My Questions*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		MyQuestions myQuestionsPage = navigation.clickMyQuestions();
		
		/*Verify that empty state message is correct on My questions-All my questions*/
		Assert.assertEquals(myQuestionsPage.getEmptyStateMessage(),myQuestionsAllEmptyState,"Empty state message on My Questions tab all My question is not correct");
		
		/*Select My Unanswered questions in dropdown */
		myQuestionsPage.selectOptionInDropdown(allMyUnanswered);
		
		/*Verify that empty state message is correct on My questions my unanswered questions*/
		Assert.assertEquals(myQuestionsPage.getEmptyStateMessage(),myQuestionsUnansweredEmptyState,"Empty state message on My Questions tab all My question is not correct");
		
		/*Select My Answered questions in dropdown */
		myQuestionsPage.selectOptionInDropdown(allMyAnswered);
		
		/*Verify that empty state message is correct on My questions my unanswered questions*/
		Assert.assertEquals(myQuestionsPage.getEmptyStateMessage(),myQuestionsAnsweredEmptyState,"Empty state message on My Questions tab all My question is not correct");
		
	}
	@Test(groups={"FullRegression"}, dependsOnMethods={"myQuestionsEmptyStates"}, alwaysRun = true, description="Verifies that new posted question appears in QandA pages")
	public void NewUnansweredQuestion()
	{
		/*Create new user if not already created*/
		if(email.equals(""))
			email = createNewMember();
		
		/*Login with new user*/
		getDriverAndLogin(email,password);
				
		/*Navigate to My Questions*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library library = navigation.clickLibrary();
		
		/*Select calculator checkbox*/
		library.selectCalculatorCheckbox();
		
		/*Open first content on the list*/
		int panel=1;
		library.click1stButtonInNthPanel(panel);
		
		/*Load comments panel*/
		MediaType mediaType = new MediaType(getDriver());
		Comments comments = new Comments(getDriver());
		
		/*Setup comments with special characters*/
		Date d = new Date();
		String commentText = "Question_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+email;
		
		//Create new comment with given text
		comments.createNewComment("question", false, commentText);
		
		/*Navigate to QandA*/
		navigation.hoverOverAsk();
		AllQuestions allQuestionsPage = navigation.clickAllQuestions();
		
		/*Get question position*/
		int questionPosition1 = allQuestionsPage.getQuestionPositionByText(commentText);
		
		/*Fail test if new question not found*/
		if(questionPosition1==0)
			Assert.fail("New question not found in All quesions tab");
		
		/*Verify that new question has unanswered status*/
		Assert.assertEquals(allQuestionsPage.getNthQuestionStatus(questionPosition1),unansweredStatus,"New question is not marked as unanswered in All Questions tab.");
		Assert.assertEquals(allQuestionsPage.getNthQuestionAsked(questionPosition1),asked1mAgo,"Asked date displayed incorrrectly.");
		Assert.assertEquals(allQuestionsPage.getNthQuestionLastUpdatedDate(questionPosition1),lastUpdate1mAgoByYou,"Last update displayed incorrrectly.");
		Assert.assertEquals(allQuestionsPage.getNthQuestionLikes(questionPosition1), 0,"Icorrect likes counter displayed");
		Assert.assertEquals(allQuestionsPage.getNthQuestionReplies(questionPosition1), 0,"Icorrect replies counter displayed");
		//FIXME add context check when defect 23571: Q1:webUI:QandA: Incorrect question context will be fixed
		
		/*Click my questions tab*/
		MyQuestions myQestionsPage = allQuestionsPage.clickMyQuestionsTab();
		
		/*Get question position*/
		int questionPosition2 = myQestionsPage.getQuestionPositionByText(commentText);
		
		/*Fail test if new question not found*/
		if(questionPosition2==0)
			Assert.fail("New question not found in My quesions tab");
		
		/*Verify that new question has unanswered status*/
		Assert.assertEquals(myQestionsPage.getNthQuestionStatus(questionPosition2),unansweredStatus,"New question is not marked as unanswered in My Questions tab.");
		Assert.assertEquals(myQestionsPage.getNthQuestionAsked(questionPosition2),asked1mAgo,"Asked date displayed incorrrectly.");
		Assert.assertEquals(myQestionsPage.getNthQuestionLastUpdatedDate(questionPosition2),lastUpdate1mAgoByYou,"Last update displayed incorrrectly.");
		Assert.assertEquals(myQestionsPage.getNthQuestionLikes(questionPosition2), 0,"Icorrect likes counter displayed");
		Assert.assertEquals(myQestionsPage.getNthQuestionReplies(questionPosition2), 0,"Icorrect replies counter displayed");
		//FIXME add context check when defect 23571: Q1:webUI:QandA: Incorrect question context will be fixed
		
		/*Delete test comment*/
		myQestionsPage.clickNthQuestionTitle(questionPosition2);
		mediaType.waitForPageToLoad(getDriver());
		int commentPos = comments.getCommentPosition(commentText);
		comments.clickDeleteLinkOnNthComment(commentPos);
		
		/*Navigate to QandA*/
		navigation.hoverOverAsk();
		navigation.clickAllQuestions();
		
		/*Verify that deleted question in not displayed in QandA Pages*/
		Assert.assertTrue(allQuestionsPage.getQuestionPositionByText(commentText)==0,"Deleted question found in QandA My questions tab.");
		
		
	}
	@Test(groups={"FullRegression"}, dependsOnMethods={"NewUnansweredQuestion"}, alwaysRun = true, description="Verifies that new answered question appears correctly in QandA pages")
	public void NewAnsweredQuestion()
	{
		/*Create new user if not already created*/
		if(email.equals(""))
			email = createNewMember();
		
		/*Login with new user*/
		getDriverAndLogin(email,password);
				
		/*Navigate to My Questions*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library library = navigation.clickLibrary();
		
		/*Select simulator checkbox*/
		library.selectSimulationCheckbox();
		
		/*Open first content on the list*/
		int panel=1;
		library.click1stButtonInNthPanel(panel);
		
		/*Load comments panel*/
		MediaType mediaType = new MediaType(getDriver());
		Comments comments = new Comments(getDriver());
		
		/*Setup comments with special characters*/
		Date d = new Date();
		String commentText = "Question_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+email;
		String answerText = "Answer_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+email;
		
		//Create new comment with given text
		comments.createNewComment("question", false, commentText);
		
		//Get question position
		int questionPos = comments.getCommentPosition(commentText);
		
		//Answer above question
		comments.createAnswerForNthQuestion(answerText,questionPos);
		
		/*Navigate to QandA*/
		navigation.hoverOverAsk();
		AllQuestions allQuestionsPage = navigation.clickAllQuestions();
		
		/*Get question position*/
		int questionPosition1 = allQuestionsPage.getQuestionPositionByText(commentText);
		
		/*Fail test if new question not found*/
		if(questionPosition1==0)
			Assert.fail("New answered question not found in All quesions tab");
		
		//Expand answer
		allQuestionsPage.clickNthQuestionShowAnswerLink(questionPosition1);
		
		/*Verify that correct elements appears*/
		Assert.assertEquals(allQuestionsPage.getNthQuestionStatus(questionPosition1),answeredStatus,"New question is not marked as unanswered in All Questions tab.");
		Assert.assertEquals(allQuestionsPage.getNthQuestionLastUpdatedDate(questionPosition1),lastUpdate1mAgoByYou,"Last update displayed incorrrectly.");
		Assert.assertEquals(allQuestionsPage.getNthQuestionLikes(questionPosition1), 0,"Icorrect likes counter displayed");
		Assert.assertEquals(allQuestionsPage.getNthQuestionReplies(questionPosition1), 1,"Icorrect replies counter displayed");
		Assert.assertEquals(allQuestionsPage.getNthQuestionAnswerText(questionPosition1), answerText,"Displayed answer is not correct");
		
		//FIXME add context check when defect 23571: Q1:webUI:QandA: Incorrect question context will be fixed
		
		/*Click my questions tab*/
		MyQuestions myQestionsPage = allQuestionsPage.clickMyQuestionsTab();
		
		/*Get question position*/
		int questionPosition2 = myQestionsPage.getQuestionPositionByText(commentText);
		
		/*Fail test if new question not found*/
		if(questionPosition2==0)
			Assert.fail("New question not found in My quesions tab");
		
		//Expand answer
		myQestionsPage.clickNthQuestionShowAnswerLink(questionPosition1);
		
		/*Verify that new question has unanswered status*/
		Assert.assertEquals(myQestionsPage.getNthQuestionStatus(questionPosition2),answeredStatus,"New question is not marked as unanswered in My Questions tab.");
		Assert.assertEquals(myQestionsPage.getNthQuestionLastUpdatedDate(questionPosition2),lastUpdate1mAgoByYou,"Last update displayed incorrrectly.");
		Assert.assertEquals(myQestionsPage.getNthQuestionLikes(questionPosition2), 0,"Icorrect likes counter displayed");
		Assert.assertEquals(myQestionsPage.getNthQuestionReplies(questionPosition2), 1,"Icorrect replies counter displayed");
		Assert.assertEquals(myQestionsPage.getNthQuestionAnswerText(questionPosition2), answerText,"Displayed answer is not correct");
		//FIXME add context check when defect 23571: Q1:webUI:QandA: Incorrect question context will be fixed
		
		/*Delete test answer and comment*/
		myQestionsPage.clickNthQuestionTitle(questionPosition2);
		mediaType.waitForPageToLoad(getDriver());
		comments.clickDeleteLinkForMthReplyInNthComment(comments.getCommentPosition(commentText), 1);
		comments.clickDeleteLinkOnNthComment(comments.getCommentPosition(commentText));
		
		/*Navigate to QandA*/
		navigation.hoverOverAsk();
		navigation.clickAllQuestions();
		
		/*Verify that deleted question in no more displayed in QandA Pages*/
		Assert.assertTrue(allQuestionsPage.getQuestionPositionByText(commentText)==0,"Deleted question found in QandA My questions tab.");
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = {"questionsICanAnswerPageOpens"}, description="Verifies Expertise Area filter works correctly on question I can answer page.")
	public void QuestionsICanAnswerExpertiseAreaFilter()
	{
		//Login into webUI
		getDriverAndLogin();
		
		//navigate to questions I can answer page
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		QuestionsICanAnswer questionsICanAnswerPage = navigation.clickQuestionsICanAnswer();
	
		//Get questions counter counter
		int qCounter = questionsICanAnswerPage.getCountOfCurrrentlyDisplayingQuestionsFromPagingText();
		int qCounterVer =0;
		
		//Get expertizes counter
		int exCounter = questionsICanAnswerPage.getCountOfExpertises();

		//Loop over expertises
		for(int i=1;i<=exCounter;i++){
			
			//select nth expertise
			questionsICanAnswerPage.clickNthExpertize(i);
			
			//Get Nth expertise text from filters
			String currentEx = questionsICanAnswerPage.getNthExpertizeText(i);
			
			//Get count of questions displayed 
			int qCount = questionsICanAnswerPage.getCountOfQuestionsDisplayed();
			
			//if count of displayed results is less than counter in paging number scroll to the end of the page and update qCount
			if(qCount!=questionsICanAnswerPage.getCountOfCurrrentlyDisplayingQuestionsFromPagingText()){
				questionsICanAnswerPage.scrollEndOfResults();
				qCount = questionsICanAnswerPage.getCountOfQuestionsDisplayed();
			}
			
			//Loop over displayed questions
			for(int j=1;j<=qCount;j++){
				
				//Verify that expertise for current question match expertise selected in the filters
				Assert.assertEquals(questionsICanAnswerPage.getNthQuestionExpertize(j), expertiseArea+currentEx,"Expertise area for question #"+j+" does not match expertise selected in filters #"+currentEx);
				
			}
			
			qCounterVer=qCounterVer+qCount;
			
			//De-select nth expertise
			questionsICanAnswerPage.clickNthExpertize(i);
		}
		Assert.assertEquals(qCounter,qCounterVer,"Total question counter does not match questions displayed summed from each expertise.");
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = {"QuestionsICanAnswerExpertiseAreaFilter"}, description="Verifies that expert can use unanswered questions links for viewing questions by expertises.")
	public void QuestionsICanAnswerUnansweredQuestionsLinks()
	{
		//Login into webUI
		getDriverAndLogin();
		
		//navigate to questions I can answer page
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverAsk();
		QuestionsICanAnswer questionsICanAnswerPage = navigation.clickQuestionsICanAnswer();
	
		//Get questions counter counter
		int qCounter = questionsICanAnswerPage.getCountOfCurrrentlyDisplayingQuestionsFromPagingText();
		int qCounterVer =0;
		
		//Get expertizes counter
		int exCounter = questionsICanAnswerPage.getCountOfExpertises();
		
		//Verify that count of expertizes in filters matches expertises in my expertise areas
		Assert.assertEquals(exCounter,questionsICanAnswerPage.getCountOfExpertisesFromExpertiseAreas(),"Count of expertises in  filter does not match count of expertises in My Expertise Areas");

		//Loop over expertises
		for(int i=1;i<=exCounter;i++){
			
			//Click nth unanswered link
			questionsICanAnswerPage.clickNthUnansweredQuestionLink(i);
			
			//Verify that correct filter is selected
			Assert.assertTrue(questionsICanAnswerPage.isNthExpertizeSelected(i),"#"+i+" expertise is not selected in the filters");
			
			//Get Nth expertise text from filters
			String currentEx = questionsICanAnswerPage.getNthExpertizeText(i);
			
			//Get count of questions displayed 
			int qCount = questionsICanAnswerPage.getCountOfQuestionsDisplayed();
			
			//if count of displayed results is less than counter in paging number scroll to the end of the page and update qCount
			if(qCount!=questionsICanAnswerPage.getCountOfCurrrentlyDisplayingQuestionsFromPagingText()){
				questionsICanAnswerPage.scrollEndOfResults();
				qCount = questionsICanAnswerPage.getCountOfQuestionsDisplayed();
			}
			
			//Loop over displayed questions
			for(int j=1;j<=qCount;j++){
				
				//Verify that expertise for current question match expertise selected in the filters
				Assert.assertEquals(questionsICanAnswerPage.getNthQuestionExpertize(j), expertiseArea+currentEx,"Expertise area for question #"+j+" does not match expertise selected in filters #"+currentEx);	
			}
			qCounterVer=qCounterVer+qCount;
		}
		Assert.assertEquals(qCounter,qCounterVer,"Total question counter does not match questions displayed summed from each expertise.");
	}
}
