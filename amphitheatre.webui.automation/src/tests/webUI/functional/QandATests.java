package tests.webUI.functional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.Navigation;
import webUI.home.Home;
import webUI.home.library.Library;
import webUI.home.library.TileView;
import webUI.home.library.mediaTypes.Calculator;
import webUI.home.library.mediaTypes.Comments;
import webUI.home.library.mediaTypes.MediaType;
import webUI.home.library.mediaTypes.Simulator;
import webUI.home.library.mediaTypes.Summary;
import webUI.home.library.mediaTypes.Video;
import webUI.home.library.mediaTypes.VideoComments;
import webUI.home.questionsAndAnswers.QandAdataProvider;
import webUI.login.Login;
import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;


public class QandATests extends LoadBrowser {
	
	/*Parameters setup*/
	private String displayname, fname, lname, occupation, country, city, password="Pa88w0rdfvt";
	private String user_learner = "",user_contentSME = "",user_subjectSME = "",user_contentAuthor = "",anotherUser = "";
	private String user_learnerName = "",user_contentSMEName = "",user_subjectSMEName = "",user_contentAuthorName = "";
	private String calculatorContentSME = "",simulatorContentSME = "",summaryContentSME = "",videoContentSME = "";
	private String calculatorContentAuthor = "",simulatorContentAuthor = "",summaryContentAuthor = "",videoContentAuthor = "";
	private String calculatorContent = "",simulatorContent = "",summaryContent = "",videoContent = "";	
	private String anotherUserType ="contentSME";
	private int postGradExperience, specialty, subSpecialty, interest;
	
	//private List<String> contentsUsed =new ArrayList<String>();
	
	private Set<String> contentsUsed = new HashSet<String>();
	
	
	public List<String> createNewLearner()//create new learner function
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
		city="Other"; 
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
	    
	    /* Fill in the member details */
		Boolean memberCreated = createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname, occupation, postGradExperience, specialty, subSpecialty, interest,  country, city, password);		

		Assert.assertTrue(memberCreated,"Failed to create new member");		
		System.out.println("New learner successfully created: " + email);
		
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		List<String> outputs = new ArrayList<String>();
		outputs.add(email);
		outputs.add(displayname);
		
		return outputs;
	}
	public String createAnotherUser()//create another user function to cover myYN cases
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
		city="Other"; 
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
	    createMemberPage.selectNthExpertise(7);
		    
	    /* Fill in the member details */
		Boolean memberCreated = createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname, occupation, postGradExperience, specialty, subSpecialty, interest,  country, city, password);		

		Assert.assertTrue(memberCreated,"Failed to create new member");		
		System.out.println("Another User successfully created: " + email);
		
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();

		return email;
	}
	public List<String> createNewSubjectSME()//create new subjectSME function
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
		city="Other"; 
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
	    createMemberPage.selectNthExpertise(7);
	    String expertise = createMemberPage.getNthExpertise(7);
	   	    
	    /* Fill in the member details */
		Boolean memberCreated = createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname, occupation, postGradExperience, specialty, subSpecialty, interest,  country, city, password);		

		Assert.assertTrue(memberCreated,"Failed to create new member");		
		//System.out.println("Member successfully created: " + email);
		
		System.out.println("New Subject SME successfully created: " + email);
		
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		List<String> outputs = new ArrayList<String>();
		
		outputs.add(email);
		outputs.add(displayname);
		outputs.add(expertise);
		
		return outputs;
	}
	public List<String> createNewContentSME()//create new contentSME function
	{		
		String email = "piotr@bluebox.lotus.com";
		String contentCalculator = "CalculatorAir";
		String contentSimulator = "SimulatorAir";
		String contentSummary = "Summary Air";
		String contentVideo = "Video Air";
		displayname = "Piotr Bogdanski";
		
		List<String> outputs = new ArrayList<String>();
		
		outputs.add(email);
		outputs.add(displayname);
		outputs.add(contentCalculator);
		outputs.add(contentSimulator);
		outputs.add(contentSummary);
		outputs.add(contentVideo);
		
		System.out.println("New content SME successfully initialized: " + email);
		
		return outputs;
	}
	public List<String> createNewContentAuthor()//create new content author function
	{		
		String email = "contentauthor_q1@bluebox.lotus.com";
		String contentCalculator = "CalculatorAir";
		String contentSimulator = "SimulatorAir";
		String contentSummary = "Summary Air";
		String contentVideo = "Video Air";
		displayname = "Content Author";

		List<String> outputs = new ArrayList<String>();
		outputs.add(email);
		outputs.add(displayname);
		outputs.add(contentCalculator);
		outputs.add(contentSimulator);
		outputs.add(contentSummary);
		outputs.add(contentVideo);
		
		System.out.println("New content Author successfully initialized: " + email);
		
		return outputs;
	}
	public String userSetup(String userType)//user setup function - sets correct user depend on userType input, creates new user if not already created
	{
		/*Create test user if not exists yet*/
		String user = "";
		
		if (userType.equals("learner"))
		{	
			if (user_learner.equals(""))
			{	List<String> outputs = new ArrayList<String>();
				outputs = createNewLearner();
				user_learner = outputs.get(0);
				user_learnerName = outputs.get(1);
			}
			user = user_learner;
			displayname = user_learnerName;
		}
		if (userType.equals("contentSME"))
		{	
			if (user_contentSME.equals(""))
			{	
				List<String> outputs = new ArrayList<String>();
				outputs = createNewContentSME();
				user_contentSME = outputs.get(0);
				user_contentSMEName = outputs.get(1);
				calculatorContentSME = outputs.get(2);
				simulatorContentSME = outputs.get(3);
				summaryContentSME = outputs.get(4);
				videoContentSME = outputs.get(5);
			}	
			user = user_contentSME;
			displayname = user_contentSMEName;
			calculatorContent = calculatorContentSME;
			simulatorContent = simulatorContentSME;
			summaryContent = summaryContentSME;
			videoContent = videoContentSME;
		}
		if (userType.equals("subjectSME"))
		{	
			if (user_subjectSME.equals(""))
			{	
				List<String> outputs = new ArrayList<String>();
				outputs = createNewSubjectSME();
				user_subjectSME = outputs.get(0);
				user_subjectSMEName = outputs.get(1);
			}
			user = user_subjectSME;
			displayname = user_subjectSMEName;
		}
		if (userType.equals("contentAuthor"))
		{	
			if (user_contentAuthor.equals(""))
			{	
				List<String> outputs = new ArrayList<String>();
				outputs = createNewContentAuthor();
				user_contentAuthor = outputs.get(0);
				user_contentAuthorName = outputs.get(1);
				calculatorContentAuthor = outputs.get(2);
				simulatorContentAuthor = outputs.get(3);
				summaryContentAuthor = outputs.get(4);
				videoContentAuthor = outputs.get(5);
			}	
			user = user_contentAuthor;
			displayname = user_contentAuthorName;
			calculatorContent = calculatorContentAuthor;
			simulatorContent = simulatorContentAuthor;
			summaryContent = summaryContentAuthor;
			videoContent = videoContentAuthor;
		}
		return user;
	}
	public void loginAsDifferentUserAndNavigateToThisPage(String anotherUser)//function to re-login using passed user and navigate to the same content
	{
		String contentURL=getDriver().getCurrentUrl();
		Navigation navigation = new Navigation(getDriver());
		
		navigation.hoverOverAvatarAndClickLogout();
		//navigation.hoverOverAvatar();
		//Thread.sleep(1000);
		//navigation.clickLogout();

		/* Launch the login page */
     	Login loginPage = new Login(getDriver());
		loginPage.enterUsername(anotherUser);     	
		loginPage.enterPassword(password);
		loginPage.clickLogin();		
		
		Home homePage = new Home(getDriver());
		
		getDriver().navigate().to(contentURL);
		//getDriver().navigate().refresh();
		
		MediaType mediaType = new MediaType(getDriver());
		mediaType.waitForPageToLoad(getDriver());
	}
	public String differentUserSetup()//create new 'another' user if not already created
	{
		if (anotherUser.equals(""))
			anotherUser = createAnotherUser();
		
		return anotherUser;
	}
	
//	@Test	
//	public void clearAllComments(){
//		
//		getDriverAndLogin();
//		/*Navigate to library*/
//		Navigation navigation = new Navigation(getDriver());
//		navigation.hoverOverLibrary();
//		Library libraryPage = navigation.clickLibrary();
//		
//		/*Content setup*/
//		libraryPage.clickMthSubjectInNthCategoryInLeftColumns(3, 6);
//		libraryPage.selectSummaryCheckbox();
//		libraryPage.click1stButtonInNthPanel(7);
//		boolean isVideo = false;
//		
//		
//		/*Launch comments panel*/
//		Comments comments = new Comments(getDriver());
//		
//		/*Launch video comments panel*/
//		VideoComments videoComments = new VideoComments(getDriver());
//
//		
//		//for (int i = 0;i<10;i++)
//		if(isVideo)
//		{
//			int c = 1;
//			
//			int commentsCount = videoComments.getCountOfCommentsInNthCluster(c);
//			String previousAuthor="piotr@bluebox.lotus.com";
//			System.out.println(commentsCount);
//			
//			for (int i = commentsCount; i>=1;i--){
//	
//				int repliesCount = videoComments.getCountofRepliesForNthComment(c,i);
//				System.out.print("i = "+i);
//				
//				String commentEmail = videoComments.getNthCommentAuthor(c,i);
//				
//				if (commentEmail.substring(0,5).equals("z_fvt")){
//					commentEmail="fvt"+commentEmail.substring(6,commentEmail.length())+"@bluebox.lotus.com";
//					System.out.println(" "+commentEmail);
//				
//				
//					if (repliesCount>0){
//						for (int j = repliesCount; j>=1;j--){
//							System.out.print("j = "+j);
//							
//							String replyEmail = videoComments.getUsernameForMthReplyInNthComment(c,i, j);
//							
//							if (replyEmail.substring(0,5).equals("z_fvt")){
//								replyEmail="fvt"+replyEmail.substring(6,replyEmail.length())+"@bluebox.lotus.com";
//								System.out.println(" "+replyEmail);
//								if(!replyEmail.equals(previousAuthor))	
//									loginAsDifferentUserAndNavigateToThisPage(replyEmail);
//								if(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(c,i, j)){
//									videoComments.clickDeleteLinkForMthReplyInNthComment(c,i, j);
//								}
//								previousAuthor = replyEmail;
//							}						
//						}
//					}
//					if(!commentEmail.equals(previousAuthor))
//						loginAsDifferentUserAndNavigateToThisPage(commentEmail);
//					if(videoComments.isDeleteLinkDisplayedOnNthComment(c,i)){
//						videoComments.clickDeleteLinkOnNthComment(c,i);
//					}
//					previousAuthor = commentEmail;
//				}	
//			}
//		}
//		else{
//			
//			
//			int commentsCount = comments.getCountOfComments();
//			String previousAuthor="piotr@bluebox.lotus.com";
//			System.out.println(commentsCount);
//			
//			for (int i = commentsCount; i>=1;i--){
//				
//				int repliesCount = comments.getCountofRepliesForNthComment(i);
//				System.out.print("i = "+i);
//				
//				String commentEmail = comments.getNthCommentAuthor(i);
//				
//				if (commentEmail.substring(0,5).equals("z_fvt")){
//					commentEmail="fvt"+commentEmail.substring(6,commentEmail.length())+"@bluebox.lotus.com";
//					System.out.println(" "+commentEmail);
//				
//				
//					if (repliesCount>0){
//						for (int j = repliesCount; j>=1;j--){
//							System.out.print("j = "+j);
//							
//							String replyEmail = comments.getUsernameForMthReplyInNthComment(i, j);
//							
//							if (replyEmail.substring(0,5).equals("z_fvt")){
//								replyEmail="fvt"+replyEmail.substring(6,replyEmail.length())+"@bluebox.lotus.com";
//								System.out.println(" "+replyEmail);
//								if(!replyEmail.equals(previousAuthor))	
//									loginAsDifferentUserAndNavigateToThisPage(replyEmail);
//								if(comments.isDeleteLinkDisplayedForMthReplyInNthComment(i, j)){
//									comments.clickDeleteLinkForMthReplyInNthComment(i, j);
//								}
//								previousAuthor = replyEmail;
//							}						
//						}
//					}
//					if(!commentEmail.equals(previousAuthor))
//						loginAsDifferentUserAndNavigateToThisPage(commentEmail);
//					if(comments.isDeleteLinkDisplayedOnNthComment(i)){
//						comments.clickDeleteLinkOnNthComment(i);
//					}
//					previousAuthor = commentEmail;
//				}	
//			}
//		}
//	}
//	
			
	@Test(enabled=false, groups={""}, dataProvider = "qandaInputs", dataProviderClass = QandAdataProvider.class)
	public void QandAtest(QandAdataProvider QandAdata) throws Exception{
		
		//TODO
		//this and following test will be enabled when below defects are fixed
		//21746
		//21666
		//21154
		//21725


		
		/*Get test inputs*/
		String userType 	= QandAdata.getUserType();
		String contentType 	= QandAdata.getContentType();
		String CRUD 		= QandAdata.getCRUD();
		Boolean Anonymous 	= QandAdata.getAnonymous();
		String itemType 	= QandAdata.getItemType();
		Boolean myYN	    = QandAdata.getMyYN();
				
		int panel = 1;
		String anonymousName = "anonymous";
		String unansweredlabel = "Unanswered";
		String answeredlabel = "Answered";
		String expertAnsweredlabel = "Expert Answered";
		String authorlabel = "Author";
		String expertlabel = "Expert";
		
		String editText = "_edited";
		
		/*create new user if not already created*/
		String user = userSetup(userType);
		
		if(!myYN)
			anotherUser = differentUserSetup();
		
		/*Login into webUI*/
		if(myYN)
			getDriverAndLogin(user,password);
		else 
			getDriverAndLogin(anotherUser,password);
		
		String userEmail = anotherUser;
		
		if(myYN)
			userEmail = user;

		
		/*Navigate to library*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/*Select Fvt in filters*/
		libraryPage.clickMthSubjectInNthCategoryInLeftColumns(3, 6);
		
		/*Initiate content type*/
		String currentContentType = "";
		
		/*Select appropriate content type in the filters*/
		if (contentType.equals("calculator")){
			libraryPage.selectCalculatorCheckbox();
			currentContentType = calculatorContent;}
		if (contentType.equals("simulator")){
			libraryPage.selectSimulationCheckbox();
			currentContentType = simulatorContent;}
		if (contentType.equals("summary")){
			libraryPage.selectSummaryCheckbox();
			currentContentType = summaryContent;}
		if (contentType.equals("video")){
			libraryPage.selectVideoCheckbox();
			currentContentType = videoContent;}
		
		/*Load Tile View page*/
		TileView tileView = new TileView(getDriver());
		
		/*If content SME or content Author find that content*/
		if(userType.equals("contentSME") || userType.equals("contentAuthor"))
			panel = tileView.getPanelNumberForTitle(currentContentType);
		
		/*Open correct content on the list*/
		libraryPage.click1stButtonInNthPanel(panel);
		
		/*wait for page to load depends on content type*/
		if (contentType.equals("calculator")){
			Calculator calculator = new Calculator(getDriver());
			calculator.waitForPageToLoad(getDriver());}
		if (contentType.equals("simulator")){
			Simulator simulator = new Simulator(getDriver());
			simulator.waitForPageToLoad(getDriver());}
		if (contentType.equals("summary")){
			Summary summary = new Summary(getDriver());
			summary.waitForPageToLoad(getDriver());}
		if (contentType.equals("video")){
			Video video = new Video(getDriver());
			video.waitForPageToLoad(getDriver());}
		
//		/*Launch content page*/
//		MediaType mediaType = new MediaType(getDriver());
//		mediaType.waitForPageToLoad(getDriver());
		
		contentsUsed.add(getDriver().getCurrentUrl());
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/*Launch video comments panel*/
		VideoComments videoComments = new VideoComments(getDriver());

				
		if (CRUD.equals("create")) //main if for user action: Create,Update,Delete - current create
		{	
			/*Set texts which will be used in creating comments/replies/answers*/
			Date d = new Date();
			String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
			String replyText = "Reply_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
			String answerText = "Answer_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
			
			if((itemType.equals("comment")) || (itemType.equals("question"))) 
			{	
				if(contentType.equals("video"))
				{	
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = videoComments.getCommentPosition(commentText);
					int commentCluster = videoComments.getClusterForCommentText(commentText);
			
					/*Verify correct item appears*/
					Assert.assertTrue(videoComments.isAddReplyLinkDisplayedOnNthComment(commentCluster, currentComment),"Add reply link is not displayed");
					Assert.assertTrue(videoComments.isDeleteLinkDisplayedOnNthComment(commentCluster, currentComment),"Delete link is not displayed");
					Assert.assertTrue(videoComments.isEditLinkDisplayedOnNthComment(commentCluster, currentComment),"Edit link is not displayed");
					Assert.assertEquals(videoComments.getLikesCounterForNthComment(commentCluster, currentComment), 0,"Like counter on new comment is not equal zero");
					Assert.assertTrue(videoComments.isBookmarkIconDisplayedForNthComment(commentCluster, currentComment),"Bookmark icon not displayed");
					Assert.assertEquals(videoComments.getNthCommentText(commentCluster, currentComment), commentText,"Comment text displayed does not match comment text entered.");
					
					/*Verify that answer link is displayed*/
					if(itemType.equals("question") && !userType.equals("learner"))
						Assert.assertTrue(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link not displayed for expert user");
					else
						Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
					
					/*Verify that correct user name is displayed*/
					if(Anonymous){
						Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), anonymousName,"Anonymous name is not displayed");
						Assert.assertTrue(videoComments.isAnonymousPhotoLoadedOnNthComment(commentCluster, currentComment),"Anonymous photo not loaded on test comment");
					}
					else{
						Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), displayname,"Name displayed does not match user name");
						Assert.assertTrue(videoComments.isProfilePhotoLoadedOnNthComment(commentCluster, currentComment, user),"Profile photo not loaded on test comment");
					}
					
					/*Verify that correct label is displayed for questions and comments*/
					if(itemType.equals("question"))
						Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), unansweredlabel,"Unanswered label not displayed");
					else{
						if (userType.equals("learner"))
							Assert.assertFalse(videoComments.isNoteOnNthCommentDisplayed(commentCluster, currentComment),"Label displayed on comment.");
						else if (userType.equals("contentAuthor"))
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), authorlabel,"Author label not displayed");
						else
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), expertlabel,"Expert label not displayed");
					}
					
					/*Clear test data*/
					videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
				}
				else //non-video content type
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = comments.getCommentPosition(commentText);
					
					/*Verify correct item appears*/
					Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply link is not displayed");
					Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
					Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
					Assert.assertEquals(comments.getLikesCounterForNthComment(currentComment), 0,"Like counter on new comment is not equal zero");
					Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
					Assert.assertEquals(comments.getNthCommentText(currentComment), commentText,"Comment text displayed does not match comment text entered.");
					
					/*Verify that answer link is displayed*/
					if(itemType.equals("question") && !userType.equals("learner"))
						Assert.assertTrue(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link not displayed for expert user");
					else
						Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
					
					/*Verify that correct user name and photo is displayed*/
					if(Anonymous){
						Assert.assertEquals(comments.getNthCommentAuthor(currentComment), anonymousName,"Anonymous name is not displayed");
						Assert.assertTrue(comments.isAnonymousPhotoLoadedOnNthComment(currentComment),"Anonymous photo not loaded on test comment");
					}
					else{
						Assert.assertEquals(comments.getNthCommentAuthor(currentComment), displayname,"Name displayed does not match user name");
						Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, user),"Profile photo not loaded on test comment");
					}
					
					/*Verify that unanswered label is displayed for question and not for comment*/
					if(itemType.equals("question"))
						Assert.assertEquals(comments.getNoteOnNthComment(currentComment), unansweredlabel,"Unanswered label not displayed");
					else{
						if (userType.equals("learner"))
							Assert.assertFalse(comments.isNoteOnNthCommentDisplayed(currentComment),"Label displayed on comment.");
						else if (userType.equals("contentAuthor"))
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), authorlabel,"Author label not displayed");
						else
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), expertlabel,"Expert label not displayed");
					}
					
					/*Clear test data*/
					comments.clickDeleteLinkOnNthComment(currentComment);
				}
			}
			else if (itemType.equals("commentAnswer")) 
			{
				if(contentType.equals("video"))
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = videoComments.getCommentPosition(commentText);
					int commentCluster = videoComments.getClusterForCommentText(commentText);
					
					/*Verify that answer link is not displayed for comments*/
					Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for comment");
					
					/*Clear test data*/
					videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
				}
				else //non-video content type
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = comments.getCommentPosition(commentText);
					
					/*Verify that answer link is not displayed for comments*/
					Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for comment");
					
					/*Clear test data*/
					comments.clickDeleteLinkOnNthComment(currentComment);
				}
			}
			else if((itemType.equals("commentReply")) || (itemType.equals("questionReply")))
			{
				if(contentType.equals("video"))
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = videoComments.getCommentPosition(commentText);
					int commentCluster = videoComments.getClusterForCommentText(commentText);
					
					int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
					
					/*Create reply for comment/question*/
					videoComments.createNewReplyforNthComment(replyText, commentCluster, currentComment);
					
					/*Verify that reply is added*/
					Assert.assertEquals(repliesCounterBefore+1,
										videoComments.getCountofRepliesForNthComment(commentCluster, currentComment),
										"Reply not added");
					
					/*Set current reply factor*/
					int currentReply = 1;
					
					/*Verify that correct items appears*/
					Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), replyText,"Reply text is not correct");
					Assert.assertEquals(videoComments.getUsernameForMthReplyInNthComment(commentCluster, currentComment, currentReply), displayname,"Name displayed does not match user name");
					Assert.assertTrue(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
					Assert.assertTrue(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
					Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
					
					/*Verify that approve link is displayed if expert is logged*/
					if(itemType.equals("questionReply") && !userType.equals("learner"))
						Assert.assertTrue(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Approve link not displayed for expert user");
					else
						Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");
					
					/*Clear test data*/
					videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
					videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
				}
				else //non video
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = comments.getCommentPosition(commentText);
					int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
					
					/*create new reply*/
					comments.createNewReplyforNthComment(replyText, currentComment);
					
					/*Get count fo replies*/
					int repliesCounterAfter = comments.getCountofRepliesForNthComment(currentComment);
					
					/*Verify that reply is added*/
					Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Reply not added");
					
					/*Set current reply position*/
					int currentReply = 1;
					
					/*Verify correct items presence*/
					Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), replyText,"Reply text is not correct");
					Assert.assertEquals(comments.getUsernameForMthReplyInNthComment(currentComment, currentReply), displayname,"Name displayed does not match user name");
					Assert.assertTrue(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
					Assert.assertTrue(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
					Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
					
					/*Verify that approve link is displayed for experts*/
					if(itemType.equals("questionReply") && !userType.equals("learner"))
						Assert.assertTrue(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Approve link not displayed for expert user");
					else
						Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
					
					/*Clear test data*/
					comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
					comments.clickDeleteLinkOnNthComment(currentComment);
				}
			}
			else if (itemType.equals("questionAnswer"))
			{
				if(contentType.equals("video"))
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = videoComments.getCommentPosition(commentText);
					int commentCluster = videoComments.getClusterForCommentText(commentText);
					
					/*Verify if that is a learner answer link is not populated*/
					if (userType.equals("learner"))
						Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
					else
					{	
						/*Get count of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Create new answer*/
						videoComments.createAnswerForNthQuestion(answerText,commentCluster, currentComment);
						
						/*Verify that answer is added*/
						Assert.assertEquals(repliesCounterBefore+1,videoComments.getCountofRepliesForNthComment(commentCluster, currentComment),"Answer not added");
						
						/*Set current answer position*/
						int currentReply =1;
						
						/*Verify presence of expected items*/
						Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), answerText,"Answer text is not correct");		
						Assert.assertEquals(comments.getUsernameForMthReplyInNthComment(currentComment, currentReply), displayname,"Name displayed does not match user name");
						Assert.assertTrue(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertTrue(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
						Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");
						Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), answeredlabel,"Question not udpated to answered status");
						Assert.assertEquals(videoComments.getNoteDisplayedForMthReplyInNthComment(commentCluster, currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
						
						/*Clear test data*/
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
				}
				else //non-video content types
				{
					/*Create new item*/
					comments.createNewComment(itemType, Anonymous, commentText);
					
					/*Search for test comment*/
					int currentComment = comments.getCommentPosition(commentText);
					
					/*Verify if current user is learner answer link is not populated*/
					if (userType.equals("learner"))
						Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
					else
					{
						/*Get count of replies*/
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create answer*/
						comments.createAnswerForNthQuestion(answerText,currentComment);
						
						/*Verify that answer is added*/
						Assert.assertEquals(repliesCounterBefore+1,comments.getCountofRepliesForNthComment(currentComment),"Answer not added");
						
						/*Set answer position*/
						int currentReply =1;
						
						/*Verify that correct items appears*/
						Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), answerText,"Answer text is not correct");		
						Assert.assertEquals(comments.getUsernameForMthReplyInNthComment(currentComment, currentReply), displayname,"Name displayed does not match user name");
						Assert.assertTrue(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertTrue(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
						Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
						Assert.assertEquals(comments.getNoteOnNthComment(currentComment), answeredlabel,"Question not udpated to answered status");
						Assert.assertEquals(comments.getNoteDisplayedForMthReplyInNthComment(currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
						
						/*Clear test data*/
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
			}
			else
			{
				Assert.fail("Scenario not covered please investigate test inputs.");
			}
		}
		else if (CRUD.equals("update"))
		{
			if(myYN)
			{	
				/*Set texts which will be used in creating comments/replies/answers*/
				Date d = new Date();
				String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String replyText = "Reply_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String answerText = "Answer_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				
				if((itemType.equals("comment")) || (itemType.equals("question")))
				{
					if(contentType.equals("video"))
					{	
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
					
						/*Edit comment*/
						videoComments.editNthComment(currentComment);
						
						/*Verify that correct item appears*/
						Assert.assertEquals(videoComments.getNthCommentText(commentCluster, currentComment), commentText+editText,"Edited comments text not displayed as expected.");
						Assert.assertTrue(videoComments.isAddReplyLinkDisplayedOnNthComment(commentCluster, currentComment),"Add reply link is not displayed");
						Assert.assertTrue(videoComments.isDeleteLinkDisplayedOnNthComment(commentCluster, currentComment),"Delete link is not displayed");
						Assert.assertTrue(videoComments.isEditLinkDisplayedOnNthComment(commentCluster, currentComment),"Edit link is not displayed");
						Assert.assertEquals(videoComments.getLikesCounterForNthComment(commentCluster, currentComment), 0,"Like counter on new comment is not equal zero");
						Assert.assertTrue(videoComments.isBookmarkIconDisplayedForNthComment(commentCluster, currentComment),"Bookmark icon not displayed");
											
						/*Verify that answer link is displayed if that is a question and current user is an expert*/
						if(itemType.equals("question") && !userType.equals("learner"))
							Assert.assertTrue(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link not displayed for expert user");
						else
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct photo loaded and correct name displayed depend of anonymous input*/
						if(Anonymous){
							Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), anonymousName,"Anonymous name is not displayed");
							Assert.assertTrue(videoComments.isAnonymousPhotoLoadedOnNthComment(commentCluster, currentComment),"Anonymous photo not loaded on test comment");
						}
						else{
							Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), displayname,"Name displayed does not match user name");
							Assert.assertTrue(videoComments.isProfilePhotoLoadedOnNthComment(commentCluster, currentComment, user),"Profile photo not loaded on test comment");
						}
						
						/*Verify that unanswered note is displayed for questions and not for comments*/
						if(itemType.equals("question"))
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), unansweredlabel,"Unanswered label not displayed");
						else{
							if (userType.equals("learner"))
								Assert.assertFalse(videoComments.isNoteOnNthCommentDisplayed(commentCluster, currentComment),"Label displayed on comment.");
							else if (userType.equals("contentAuthor"))
								Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), authorlabel,"Author label not displayed");
							else
								Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), expertlabel,"Expert label not displayed");
						}
						
						/*Clear test data*/
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else //non-video content types
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
					
						/*Edit comment*/
						comments.editNthComment(currentComment);
						
						/*Verify that correct items appears*/
						Assert.assertEquals(comments.getNthCommentText(currentComment), commentText+editText,"Edited comments text not displayed as expected.");
						Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply link is not displayed");
						Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
						Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
						Assert.assertEquals(comments.getLikesCounterForNthComment(currentComment), 0,"Like counter on new comment is not equal zero");
						Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
											
						/*Verify that answer link is displayed for experts only*/
						if(itemType.equals("question") && !userType.equals("learner"))
							Assert.assertTrue(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link not displayed for expert user");
						else
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct photo and name loaded -depends on anonymous input*/
						if(Anonymous){
							Assert.assertEquals(comments.getNthCommentAuthor(currentComment), anonymousName,"Anonymous name is not displayed");
							Assert.assertTrue(comments.isAnonymousPhotoLoadedOnNthComment(currentComment),"Anonymous photo not loaded on test comment");
						}
						else{
							Assert.assertEquals(comments.getNthCommentAuthor(currentComment), displayname,"Name displayed does not match user name");
							Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, user),"Profile photo not loaded on test comment");
						}
						
						/*Verify that correct label displayed for comments and questions*/
						if(itemType.equals("question"))
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), unansweredlabel,"Unanswered label not displayed");
						else{
							if (userType.equals("learner"))
								Assert.assertFalse(comments.isNoteOnNthCommentDisplayed(currentComment),"Label displayed on comment.");
							else if (userType.equals("contentAuthor"))
								Assert.assertEquals(comments.getNoteOnNthComment(currentComment), authorlabel,"Author label not displayed");
							else
								Assert.assertEquals(comments.getNoteOnNthComment(currentComment), expertlabel,"Expert label not displayed");
						}
						
						/*Clear test data*/
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if((itemType.equals("commentReply")) || (itemType.equals("questionReply")))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*get counts of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Create new reply*/
						videoComments.createNewReplyforNthComment(replyText,commentCluster,  currentComment);
						
						/*Verify that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,videoComments.getCountofRepliesForNthComment(commentCluster, currentComment),"Reply not added");
						
						/*set reply position*/
						int currentReply = 1;
						
						/*Edit reply*/
						videoComments.editMthReplyOnNthComment(commentCluster, currentComment, currentReply,editText);
									
						/*Verify presence of items*/
						Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), replyText+editText,"Reply text is not correct");
						Assert.assertEquals(videoComments.getUsernameForMthReplyInNthComment(commentCluster, currentComment, currentReply), displayname,"Name displayed does not match user name");
						Assert.assertTrue(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertTrue(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
						
						/*Verify that approve link is displayed for experts*/
						if(itemType.equals("questionReply") && !userType.equals("learner"))
							Assert.assertTrue(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Approve link not displayed for expert user");
						else
							Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");
						
						/*Clear test data*/
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else //non-video content types
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create new reply*/
						comments.createNewReplyforNthComment(replyText, currentComment);
						
						/*Verify that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,comments.getCountofRepliesForNthComment(currentComment),"Reply not added");
						
						/*set reply position*/
						int currentReply = 1;
						
						/*Edit reply*/
						comments.editMthReplyOnNthComment(currentComment, currentReply, editText);
						
						/*Verify that correct items appears*/
						Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), replyText+editText,"Reply text is not correct");
						Assert.assertEquals(comments.getUsernameForMthReplyInNthComment(currentComment, currentReply), displayname,"Name displayed does not match user name");
						Assert.assertTrue(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertTrue(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
						
						/*Verify that approve link is displayed for experts*/
						if(itemType.equals("questionReply") && !userType.equals("learner"))
							Assert.assertTrue(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Approve link not displayed for expert user");
						else
							Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
						
						/*Clear test data*/
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if (itemType.equals("questionAnswer"))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Verify if current user is a learner answer link is not populated*/
						if (userType.equals("learner"))
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
						else
						{
							/*Get count of replies*/
							int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
							
							/*Create an answer*/
							videoComments.createAnswerForNthQuestion(answerText,commentCluster, currentComment);
							
							/*Verify that answer is added*/
							Assert.assertEquals(repliesCounterBefore+1,videoComments.getCountofRepliesForNthComment(commentCluster, currentComment),"Answer not added");
							
							/*set answer position*/
							int currentReply =1;
							
							/*Edit answer*/
							videoComments.editMthReplyOnNthComment(commentCluster, currentComment, currentReply,editText);
							
							/*Verify that correct items appears*/
							Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), answerText+editText,"Answer text is not correct");
							Assert.assertEquals(comments.getUsernameForMthReplyInNthComment(currentComment, currentReply), displayname,"Name displayed does not match user name");
							Assert.assertTrue(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
							Assert.assertTrue(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
							Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
							Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), answeredlabel,"Question not udpated to answered status");
							Assert.assertEquals(videoComments.getNoteDisplayedForMthReplyInNthComment(commentCluster, currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
							
							/*Clear test data*/
							videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
							videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);					
						}
					}
					else //non-video content types
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						/*Verify that answer link is not populater fo learner*/
						if (userType.equals("learner"))
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						else
						{
							/*Get count of replies*/
							int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
							
							/*Create an answer*/
							comments.createAnswerForNthQuestion(answerText,currentComment);
							
							/*Verify that answer is added*/
							Assert.assertEquals(repliesCounterBefore+1,comments.getCountofRepliesForNthComment(currentComment),"Answer not added");
							
							/*Set answer position*/
							int currentReply =1;
							
							/*Edit answer*/
							comments.editMthReplyOnNthComment(currentComment, currentReply, editText);
							
							/*Check that correct items appears*/
							Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), answerText+editText,"Answer text is not correct");
							Assert.assertEquals(comments.getUsernameForMthReplyInNthComment(currentComment, currentReply), displayname,"Name displayed does not match user name");
							Assert.assertTrue(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
							Assert.assertTrue(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
							Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
							Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), answeredlabel,"Question not udpated to answered status");
							Assert.assertEquals(comments.getNoteDisplayedForMthReplyInNthComment(currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
							
							/*Clear test data*/
							comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
							comments.clickDeleteLinkOnNthComment(currentComment);					
						}
					}
				}
				else
				{
					Assert.fail("Scenario not covered - please investigate inputs");
				}
			}
			else //myYN = false
			{
				Date d = new Date();
				String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String replyText = "Reply_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String answerText = "Answer_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				
				if((itemType.equals("comment")) || (itemType.equals("question")))
				{
					if(contentType.equals("video"))
					{	
						/*Create new item using 'another' user*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*login as current user and navigate to the same content*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment/question*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Verify presence of items*/
						Assert.assertTrue(videoComments.isAddReplyLinkDisplayedOnNthComment(commentCluster, currentComment),"Add reply link is not displayed");
						Assert.assertFalse(videoComments.isDeleteLinkDisplayedOnNthComment(commentCluster, currentComment),"Delete link is displayed");
						Assert.assertFalse(videoComments.isEditLinkDisplayedOnNthComment(commentCluster, currentComment),"Edit link is not displayed");
						Assert.assertEquals(videoComments.getLikesCounterForNthComment(commentCluster, currentComment), 0,"Like counter on new comment is not equal zero");
						Assert.assertTrue(videoComments.isBookmarkIconDisplayedForNthComment(commentCluster, currentComment),"Bookmark icon not displayed");
											
						/*Verify that answer link is displayed for experts*/
						if(itemType.equals("question") && !userType.equals("learner"))
							Assert.assertTrue(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link not displayed for expert user");
						else
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct photo and name is displayed depend on anonymous input*/
						if(Anonymous){
							Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), anonymousName,"Anonymous name is not displayed");
							Assert.assertTrue(videoComments.isAnonymousPhotoLoadedOnNthComment(commentCluster, currentComment),"Anonymous photo not loaded on test comment");
						}
						else{
							Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), displayname,"Name displayed does not match user name");
							Assert.assertTrue(videoComments.isProfilePhotoLoadedOnNthComment(commentCluster, currentComment, user),"Profile photo not loaded on test comment");
						}
						
						/*Verify that correct label is displayed*/
						if(itemType.equals("question"))
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), unansweredlabel,"Unanswered label not displayed");
						else{
							if (anotherUserType.equals("learner"))
								Assert.assertFalse(videoComments.isNoteOnNthCommentDisplayed(commentCluster, currentComment),"Label displayed on comment.");
							else if (anotherUserType.equals("contentAuthor"))
								Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), authorlabel,"Author label not displayed");
							else
								Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), expertlabel,"Expert label not displayed");
						}
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else //non video content
					{
						/*Create new item using another user - test preparation*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Login as current user and navigate to the same content*/
						loginAsDifferentUserAndNavigateToThisPage(user);
											
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						/*Verify presence of correct items*/
						Assert.assertEquals(comments.getNthCommentText(currentComment), commentText,"Comments text not displayed as expected.");
						Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply link is not displayed");
						Assert.assertFalse(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
						Assert.assertFalse(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
						Assert.assertEquals(comments.getLikesCounterForNthComment(currentComment), 0,"Like counter on new comment is not equal zero");
						Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
											
						/*Verify that answer link is displayed if current user is an expert*/
						if(itemType.equals("question") && !userType.equals("learner"))
							Assert.assertTrue(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link not displayed for expert user");
						else
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct name and photo is displayed, depend on anonymous input*/
						if(Anonymous){
							Assert.assertEquals(comments.getNthCommentAuthor(currentComment), anonymousName,"Anonymous name is not displayed");
							Assert.assertTrue(comments.isAnonymousPhotoLoadedOnNthComment(currentComment),"Anonymous photo not loaded on test comment");
						}
						else{
							Assert.assertEquals(comments.getNthCommentAuthor(currentComment), displayname,"Name displayed does not match user name");
							Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, user),"Profile photo not loaded on test comment");
						}
						
						/*Verify that correct label is displayed*/
						if(itemType.equals("question"))
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), unansweredlabel,"Unanswered label not displayed");
						else{
							if (anotherUserType.equals("learner"))
								Assert.assertFalse(comments.isNoteOnNthCommentDisplayed(currentComment),"Label displayed on comment.");
							else if (anotherUserType.equals("contentAuthor"))
								Assert.assertEquals(comments.getNoteOnNthComment(currentComment), authorlabel,"Author label not displayed");
							else
								Assert.assertEquals(comments.getNoteOnNthComment(currentComment), expertlabel,"Expert label not displayed");
						}
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = comments.getCommentPosition(commentText);
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if((itemType.equals("commentReply")) || (itemType.equals("questionReply")))
				{
					if(contentType.equals("video"))
					{
						/*Create new comment/question using another user*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment/question*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Get count of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Create new reply - still using another user*/
						videoComments.createNewReplyforNthComment(replyText,commentCluster,  currentComment);
						
						/*Check that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,videoComments.getCountofRepliesForNthComment(commentCluster, currentComment),"Reply not added");
						
						/*set position for current reply*/
						int currentReply = 1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Check presence of items*/
						Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), replyText+editText,"Reply text is not correct");
						Assert.assertFalse(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
						
						/*Check that approve link is displayed for experts*/
						if(itemType.equals("questionReply") && !userType.equals("learner"))
							Assert.assertTrue(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Approve link not displayed for expert user");
						else
							Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");

						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else
					{	
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create new reply*/
						comments.createNewReplyforNthComment(replyText, currentComment);
						
						/*Check that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,comments.getCountofRepliesForNthComment(currentComment),"Reply not added");
						
						/*Set reply position*/
						int currentReply = 1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = comments.getCommentPosition(commentText);
						repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Verify that correct items appears*/
						Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), replyText,"Reply text is not correct");
						Assert.assertFalse(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
						
						/*Verify that approve link is displayed for experts*/
						if(itemType.equals("questionReply") && !userType.equals("learner"))
							Assert.assertTrue(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Approve link not displayed for expert user");
						else
							Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = comments.getCommentPosition(commentText);
						repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if (itemType.equals("questionAnswer"))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						
						/*Get count of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Create new answer*/
						videoComments.createAnswerForNthQuestion(answerText,commentCluster, currentComment);
						
						/*Verify that answer is added*/
						Assert.assertEquals(repliesCounterBefore+1,videoComments.getCountofRepliesForNthComment(commentCluster, currentComment),"Answer not added");
						
						/*set answer position*/
						int currentReply =1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Verify that answer link is not available for learners*/
						if (userType.equals("learner"))
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
							
						/*Verify that correct items appears*/
						Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), answerText+editText,"Answer text is not correct");
						Assert.assertFalse(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
						Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");
						Assert.assertEquals(videoComments.getNoteDisplayedForMthReplyInNthComment(commentCluster, currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);					
					}
					else //non-video content type
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						/*Get count of comments*/
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create new answer*/
						comments.createAnswerForNthQuestion(answerText,currentComment);
						
						/*Check that answer is added*/
						Assert.assertEquals(repliesCounterBefore+1,comments.getCountofRepliesForNthComment(currentComment),"Answer not added");
						
						/*Set answer position*/
						int currentReply =1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Get test comment position*/
						currentComment = comments.getCommentPosition(commentText);
						
						/*Verify that answer link is not displayed for learners*/
						if (userType.equals("learner"))
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct items appears*/
						Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), answerText,"Answer text is not correct");
						Assert.assertFalse(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
						Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
						Assert.assertEquals(comments.getNoteOnNthComment(currentComment), answeredlabel,"Question not udpated to answered status");
						Assert.assertEquals(comments.getNoteDisplayedForMthReplyInNthComment(currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = comments.getCommentPosition(commentText);
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						comments.clickDeleteLinkOnNthComment(currentComment);					
					}
				}
				else
				{
					Assert.fail("Scenario not covered - please investigate inputs");
				}
			}
		}
		else if (CRUD.equals("delete"))
		{
			if(myYN)
			{	
				/*Set test strings*/
				Date d = new Date();
				String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String replyText = "Reply_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String answerText = "Answer_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				
				if((itemType.equals("comment")) || (itemType.equals("question")))
				{	
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Verify correct item appears*/
						Assert.assertEquals(videoComments.getNthCommentText(commentCluster, currentComment), commentText,"Comment text displayed does not match comment text entered.");
						Assert.assertTrue(videoComments.isDeleteLinkDisplayedOnNthComment(commentCluster, currentComment),"Delete link is not displayed");
						
						/*Delete comment*/
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
						
						/*Verify that comment/question is deleted from the list*/
						Assert.assertEquals(videoComments.getPosForCommentText(commentText), 0, itemType+" is not deleted from the list");
					}
					else //non-video content type
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						/*Verify correct item appears*/
						Assert.assertEquals(comments.getNthCommentText(currentComment), commentText,"Comment text displayed does not match comment text entered.");
						Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
						
						/*Delete comment*/
						comments.clickDeleteLinkOnNthComment(currentComment);
						
						/*Verify that comment/question is deleted from the list*/
						Assert.assertEquals(comments.getCommentPosition(commentText), 0, itemType+" is not deleted from the list");
					}
				}
				else if((itemType.equals("commentReply")) || (itemType.equals("questionReply")))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Get count of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Create new reply*/
						videoComments.createNewReplyforNthComment(replyText,commentCluster,  currentComment);
						
						/*Get count of replies*/
						int repliesCounterAfter = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Verify that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Reply not added");
						
						/*Set reply position*/
						int currentReply = 1;
						
						/*Delete reply*/
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						
						/*Get count of replies*/
						int repliesCounterAfterDeletion = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Check that reply is deleted*/
						Assert.assertEquals(repliesCounterAfterDeletion+1,repliesCounterAfter,"Reply not deleted");
						
						/*Clear test data*/
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else //non-video content type
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create new reply*/
						comments.createNewReplyforNthComment(replyText, currentComment);
						
						/*Get count of replies*/
						int repliesCounterAfter = comments.getCountofRepliesForNthComment(currentComment);
						
						/*check that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Reply not added");
						
						/*set reply position*/
						int currentReply = 1;
						
						/*Delete reply*/
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						
						/*Get count of replies*/
						int repliesCounterAfterDeletion = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Check that reply is deleted*/
						Assert.assertEquals(repliesCounterAfterDeletion+1,repliesCounterAfter,"Reply not deleted");
						
						/*Clear test data*/
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if (itemType.equals("questionAnswer"))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						
						/*Check that answer link is not populated for learners*/
						if (userType.equals("learner"))
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
						else
						{
							/*Get count of replies*/
							int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(currentComment);
							
							/*Create new reply*/
							videoComments.createAnswerForNthQuestion(answerText,commentCluster, currentComment);
							
							/*Get count of replies*/
							int repliesCounterAfter = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
							
							/*Verify that reply is added*/
							Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Answer not added");
							
							/*set reply position*/
							int currentReply =1;
							
							/*Delete reply*/
							videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
							
							/*Get count of replies*/
							int repliesCounterAfterDeletion = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
							
							/*Verify that answer is deleted*/
							Assert.assertEquals(repliesCounterAfterDeletion+1,repliesCounterAfter,"Reply not deleted");
							
							/*Verify that question note is changed to unanswered*/
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), unansweredlabel,"Question not udpated to unanswered status");
							
							/*Clear test data*/
							videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
						}
					}
					else
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						/*Check that answer link is not populated for learners*/
						if (userType.equals("learner"))
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						else
						{
							/*Get count of replies*/
							int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
							
							/*create an answer*/
							comments.createAnswerForNthQuestion(answerText,currentComment);
							
							/*Get count of replies*/
							int repliesCounterAfter = comments.getCountofRepliesForNthComment(currentComment);
							
							/*Verify that answer is added*/
							Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Answer not added");
							
							/*set reply position*/
							int currentReply =1;
							
							/*Delete reply*/
							comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
							
							/*Get count of replies*/
							int repliesCounterAfterDeletion = comments.getCountofRepliesForNthComment(currentComment);
							
							/*Verify that answer is deleted*/
							Assert.assertEquals(repliesCounterAfterDeletion+1,repliesCounterAfter,"Reply not deleted");
							
							/*Verify that question note is changed to unanswered*/
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), unansweredlabel,"Question not udpated to unanswered status");
							
							/*Clear test data*/
							comments.clickDeleteLinkOnNthComment(currentComment);
						}
					}
				}
				else
				{
					Assert.fail("Scenario not covered - please investigate inputs");
				}
			}
			else //myYN false
			{
				/*Set test strings*/
				Date d = new Date();
				String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String replyText = "Reply_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				String answerText = "Answer_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+userEmail;
				
				if((itemType.equals("comment")) || (itemType.equals("question")))
				{
					if(contentType.equals("video"))
					{	
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
					
						/*Verify presence of items*/
						Assert.assertTrue(videoComments.isAddReplyLinkDisplayedOnNthComment(commentCluster, currentComment),"Add reply link is not displayed");
						Assert.assertFalse(videoComments.isDeleteLinkDisplayedOnNthComment(commentCluster, currentComment),"Delete link is not displayed");
						Assert.assertFalse(videoComments.isEditLinkDisplayedOnNthComment(commentCluster, currentComment),"Edit link is not displayed");
						Assert.assertEquals(videoComments.getLikesCounterForNthComment(commentCluster, currentComment), 0,"Like counter on new comment is not equal zero");
						Assert.assertTrue(videoComments.isBookmarkIconDisplayedForNthComment(commentCluster, currentComment),"Bookmark icon not displayed");
											
						/*Verify that answer link is displayed for experts*/
						if(itemType.equals("question") && !userType.equals("learner"))
							Assert.assertTrue(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link not displayed for expert user");
						else
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct photo and name are displayed  -depend on anonymous input*/
						if(Anonymous){
							Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), anonymousName,"Anonymous name is not displayed");
							Assert.assertTrue(videoComments.isAnonymousPhotoLoadedOnNthComment(commentCluster, currentComment),"Anonymous photo not loaded on test comment");
						}
						else{
							Assert.assertEquals(videoComments.getNthCommentAuthor(commentCluster, currentComment), displayname,"Name displayed does not match user name");
							Assert.assertTrue(videoComments.isProfilePhotoLoadedOnNthComment(commentCluster, currentComment, user),"Profile photo not loaded on test comment");
						}
						
						/*Verify that correct label is displayed*/
						if(itemType.equals("question"))
							Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), unansweredlabel,"Unanswered label not displayed");
						else{
							if (anotherUserType.equals("learner"))
								Assert.assertFalse(videoComments.isNoteOnNthCommentDisplayed(commentCluster, currentComment),"Label displayed on comment.");
							else if (anotherUserType.equals("contentAuthor"))
								Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), authorlabel,"Author label not displayed");
							else
								Assert.assertEquals(videoComments.getNoteOnNthComment(commentCluster, currentComment), expertlabel,"Expert label not displayed");
						}

						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else //non video content
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
											
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						/*Verify presence of correct items*/
						Assert.assertEquals(comments.getNthCommentText(currentComment), commentText,"Edited comments text not displayed as expected.");
						Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply link is not displayed");
						Assert.assertFalse(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
						Assert.assertFalse(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
						Assert.assertEquals(comments.getLikesCounterForNthComment(currentComment), 0,"Like counter on new comment is not equal zero");
						Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
											
						/*Verify that answer link is displayed for experts*/
						if(itemType.equals("question") && !userType.equals("learner"))
							Assert.assertTrue(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link not displayed for expert user");
						else
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						
						/*Verify that correct photo and name are displayed depend on anonymous input*/
						if(Anonymous){
							Assert.assertEquals(comments.getNthCommentAuthor(currentComment), anonymousName,"Anonymous name is not displayed");
							Assert.assertTrue(comments.isAnonymousPhotoLoadedOnNthComment(currentComment),"Anonymous photo not loaded on test comment");
						}
						else{
							Assert.assertEquals(comments.getNthCommentAuthor(currentComment), displayname,"Name displayed does not match user name");
							Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, user),"Profile photo not loaded on test comment");
						}

						/*Verify that correct label is displayed*/
						if(itemType.equals("question"))
							Assert.assertEquals(comments.getNoteOnNthComment(currentComment), unansweredlabel,"Unanswered label not displayed");
						else{
							if (anotherUserType.equals("learner"))
								Assert.assertFalse(comments.isNoteOnNthCommentDisplayed(currentComment),"Label displayed on comment.");
							else if (anotherUserType.equals("contentAuthor"))
								Assert.assertEquals(comments.getNoteOnNthComment(currentComment), authorlabel,"Author label not displayed");
							else
								Assert.assertEquals(comments.getNoteOnNthComment(currentComment), expertlabel,"Expert label not displayed");
						}

						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = comments.getCommentPosition(commentText);
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if((itemType.equals("commentReply")) || (itemType.equals("questionReply")))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);

						/*Get count of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);

						/*Create new reply*/
						videoComments.createNewReplyforNthComment(replyText,commentCluster,  currentComment);

						/*Get count of replies*/
						int repliesCounterAfter = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);

						/*Check that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Reply not added");
						
						/*Set reply position*/
						int currentReply = 1;
						
						/*login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Check presence of items*/
						Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), replyText,"Reply text is not correct");
						Assert.assertFalse(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
						
						/*Check that approve link is displayed for experts*/
						if(itemType.equals("questionReply") && !userType.equals("learner"))
							Assert.assertTrue(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Approve link not displayed for expert user");
						else
							Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");

						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);
					}
					else //non-video content type
					{	
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create new reply*/
						comments.createNewReplyforNthComment(replyText, currentComment);
						
						/*Get count of replies*/
						int repliesCounterAfter = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Check that reply is added*/
						Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Reply not added");
						
						/*set reply position*/
						int currentReply = 1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = comments.getCommentPosition(commentText);
						repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
									
						/*Verify presence of items*/
						Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), replyText,"Reply text is not correct");
						Assert.assertFalse(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
						
						/*Check approve link is displayed for experts*/
						if(itemType.equals("questionReply") && !userType.equals("learner"))
							Assert.assertTrue(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Approve link not displayed for expert user");
						else
							Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = comments.getCommentPosition(commentText);
						repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						comments.clickDeleteLinkOnNthComment(currentComment);
					}
				}
				else if (itemType.equals("questionAnswer"))
				{
					if(contentType.equals("video"))
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = videoComments.getCommentPosition(commentText);
						int commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Get count of replies*/
						int repliesCounterBefore = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Create new answer*/
						videoComments.createAnswerForNthQuestion(answerText,commentCluster, currentComment);
						
						/*Get count of replies*/
						int repliesCounterAfter = videoComments.getCountofRepliesForNthComment(commentCluster, currentComment);
						
						/*Check that answer is added*/
						Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Answer not added");
						
						/*set answer position*/
						int currentReply =1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						
						/*Check that answer link is not displayed for learner*/
						if (userType.equals("learner"))
							Assert.assertFalse(videoComments.isAnswerLinkDisplayedOnNthComment(commentCluster, currentComment),"Answer link displayed for learner user");

						/*Verify presence of items*/
						Assert.assertEquals(videoComments.getMthReplyTextInNthComment(commentCluster, currentComment, currentReply), answerText+editText,"Answer text is not correct");
						Assert.assertFalse(videoComments.isEditLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(videoComments.isLikeCounterDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Like counter is not displayed on reply");
						Assert.assertFalse(videoComments.isApproveLinkDisplayedForMthReplyInNthComment(commentCluster, currentComment, currentReply),"Aapprove link displayed for learner user");
						Assert.assertEquals(videoComments.getNoteDisplayedForMthReplyInNthComment(commentCluster, currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = videoComments.getCommentPosition(commentText);
						commentCluster = videoComments.getClusterForCommentText(commentText);
						videoComments.clickDeleteLinkForMthReplyInNthComment(commentCluster, currentComment, currentReply);
						videoComments.clickDeleteLinkOnNthComment(commentCluster, currentComment);					
					}
					else
					{
						/*Create new item*/
						comments.createNewComment(itemType, Anonymous, commentText);
						
						/*Search for test comment*/
						int currentComment = comments.getCommentPosition(commentText);
						
						if (currentComment==0)
							Assert.fail("No comment found");
						
						/*Get count of replies*/
						int repliesCounterBefore = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Create new answer*/
						comments.createAnswerForNthQuestion(answerText,currentComment);
						
						/*Get count of replies*/
						int repliesCounterAfter = comments.getCountofRepliesForNthComment(currentComment);
						
						/*Verify that answer is added*/
						Assert.assertEquals(repliesCounterBefore+1,repliesCounterAfter,"Answer not added");
						
						/*Set answer position*/
						int currentReply =1;
						
						/*Login as test user*/
						loginAsDifferentUserAndNavigateToThisPage(user);
						
						/*Search for test comment*/
						currentComment = comments.getCommentPosition(commentText);
						
						/*Verify that answer link is not displayed for learners*/
						if (userType.equals("learner"))
							Assert.assertFalse(comments.isAnswerLinkDisplayedOnNthComment(currentComment),"Answer link displayed for learner user");
						
						/*Verify items presence*/
						Assert.assertEquals(comments.getMthReplyTextInNthComment(currentComment, currentReply), answerText,"Answer text is not correct");
						Assert.assertFalse(comments.isEditLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Edit link is not displayed on reply");
						Assert.assertFalse(comments.isDeleteLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Delete link is not displayed on reply");
						Assert.assertTrue(comments.isLikeCounterDisplayedForMthReplyInNthComment(currentComment, currentReply),"Like counter is not displayed on reply");
						Assert.assertFalse(comments.isApproveLinkDisplayedForMthReplyInNthComment(currentComment, currentReply),"Aapprove link displayed for learner user");
						Assert.assertEquals(comments.getNoteOnNthComment(currentComment), answeredlabel,"Question not udpated to answered status");
						Assert.assertEquals(comments.getNoteDisplayedForMthReplyInNthComment(currentComment,currentReply), expertAnsweredlabel,"Answer not marked as expert answered.");
						
						/*Clear test data*/
						loginAsDifferentUserAndNavigateToThisPage(anotherUser);
						currentComment = comments.getCommentPosition(commentText);
						comments.clickDeleteLinkForMthReplyInNthComment(currentComment, currentReply);
						comments.clickDeleteLinkOnNthComment(currentComment);					
					}
				}
				else
				{
					Assert.fail("Scenario not covered - please investigate inputs");
				}
			}
		}
		else 
			Assert.fail("Scenario not covered - please investigate inputs");
	return;	
	}
	
	@Test(enabled=false,groups={"test"},dependsOnMethods="QandAtest",alwaysRun=true)	
	public void clearAllCommentsAfterQandATest(){
		
		//TODO
		//enable this when defects listed in above test are fixed
		
		getDriverAndLogin();
		
		System.out.println(contentsUsed);
		
		for(String s: contentsUsed){
			
			System.out.println(s);
			
			getDriver().navigate().to(s);
			getDriver().navigate().refresh();
			
			//Video video = new Video(getDriver());
			
			//boolean isVideo = video.isTranscriptTabDisplayed();
			boolean isVideo = s.toString().contains("\"mediaType\":\"video\"");
			
			//"mediaType":"video"
			
			/*Launch comments panel*/
			Comments comments = new Comments(getDriver());
			
			/*Launch video comments panel*/
			VideoComments videoComments = new VideoComments(getDriver());
	
			
			//for (int i = 0;i<10;i++)
			if(isVideo)
			{
				int c = 1;
				
				int commentsCount = videoComments.getCountOfCommentsInNthCluster(c);
				String previousAuthor="unknown@bluebox.lotus.com";
				//System.out.println(commentsCount);
				
				for (int i = commentsCount; i>=1;i--){
		
					int repliesCount = videoComments.getCountofRepliesForNthComment(c,i);
					//System.out.print("i = "+i);
					
					String commentEmail = videoComments.getNthCommentAuthor(c,i);
					String commentText = videoComments.getNthCommentText(c,i);
					
					if (commentText.contains("fvt_automation")&&commentText.contains("|")){
						commentEmail=commentText.substring(commentText.lastIndexOf("|")+1, commentText.length());
						//System.out.println(" "+commentEmail);
					
					
						if (repliesCount>0){
							for (int j = repliesCount; j>=1;j--){
								//System.out.print("j = "+j);
								
								String replyEmail = videoComments.getUsernameForMthReplyInNthComment(c,i, j);
								String replyText = videoComments.getMthReplyTextInNthComment(c,i, j);
								
								if (replyText.contains("fvt_automation")&&replyText.contains("|")){
									replyEmail=replyText.substring(replyText.lastIndexOf("|")+1, replyText.length());
									System.out.println(" "+replyEmail);
									if(!replyEmail.equals(previousAuthor))	
										loginAsDifferentUserAndNavigateToThisPage(replyEmail);
									if(videoComments.isDeleteLinkDisplayedForMthReplyInNthComment(c,i, j)){
										videoComments.clickDeleteLinkForMthReplyInNthComment(c,i, j);
									}
									previousAuthor = replyEmail;
								}						
							}
						}
						if(!commentEmail.equals(previousAuthor))
							loginAsDifferentUserAndNavigateToThisPage(commentEmail);
						if(videoComments.isDeleteLinkDisplayedOnNthComment(c,i)){
							videoComments.clickDeleteLinkOnNthComment(c,i);
						}
						previousAuthor = commentEmail;
					}	
				}
			}
			else{
				
				
				int commentsCount = comments.getCountOfComments();
				String previousAuthor="unknown@bluebox.lotus.com";
				System.out.println(commentsCount);
				
				for (int i = commentsCount; i>=1;i--){
					
					int repliesCount = comments.getCountofRepliesForNthComment(i);
					//System.out.print("i = "+i);
					
					String commentEmail = comments.getNthCommentAuthor(i);
					String commentText = comments.getNthCommentText(i);
					
					if (commentText.contains("fvt_automation")&&commentText.contains("|")){
						commentEmail=commentText.substring(commentText.lastIndexOf("|")+1, commentText.length());
						//System.out.println(" "+commentEmail);
					
					
						if (repliesCount>0){
							for (int j = repliesCount; j>=1;j--){
								//System.out.print("j = "+j);
								
								String replyEmail = comments.getUsernameForMthReplyInNthComment(i, j);
								String replyText = comments.getMthReplyTextInNthComment(i, j);
								
								if (replyText.contains("fvt_automation")&&replyText.contains("|")){
									replyEmail=replyText.substring(replyText.lastIndexOf("|")+1, replyText.length());
									System.out.println(" "+replyEmail);
									if(!replyEmail.equals(previousAuthor))	
										loginAsDifferentUserAndNavigateToThisPage(replyEmail);
									if(comments.isDeleteLinkDisplayedForMthReplyInNthComment(i, j)){
										comments.clickDeleteLinkForMthReplyInNthComment(i, j);
									}
									previousAuthor = replyEmail;
								}						
							}
						}
						if(!commentEmail.equals(previousAuthor))
							loginAsDifferentUserAndNavigateToThisPage(commentEmail);
						if(comments.isDeleteLinkDisplayedOnNthComment(i)){
							comments.clickDeleteLinkOnNthComment(i);
						}
						previousAuthor = commentEmail;
					}	
				}
			}
		}
	}
}
