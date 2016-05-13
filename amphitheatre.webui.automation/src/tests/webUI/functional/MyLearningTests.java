package tests.webUI.functional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.Home;
import webUI.home.library.mediaTypes.Video;
import webUI.home.mylearning.LearningPathDetail;
import webUI.home.mylearning.Quiz;
import webUI.home.mylearning.QuizResult;
import webUI.home.mylearning.SubwayMap;
import webUI.home.mylearning.ViewSummary;
import webUI.home.mylearning.currentActivities.CurrentActivities;
import webUI.home.mylearning.progress.Progress;
import webUI.login.Login;
import admin.learningAdministration.EnrolMemberSearch;
import admin.learningAdministration.LearningAdministrationCurriculum;
import admin.learningAdministration.LearningAdministrationHome;
import admin.learningAdministration.MemberInformation;
import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;

public class MyLearningTests extends LoadBrowser{

	private String email1,email2, email3, email4, email5, displayname, fname, lname, occupation, country, city, password;
	private int postGradExperience, specialty, subSpecialty, interest;
	private String NOT_ENROLLED_CURRENT_ACTIVITY = new LocaleStrings().getString("NOT_ENROLLED_CURRENT_ACTIVITY");
	private String NOT_ENROLLED_PROGRESS = new LocaleStrings().getString("NOT_ENROLLED_PROGRESS");
	private String LEARNING_PATH_COMPLETE = new LocaleStrings().getString("LEARNING_PATH_COMPLETE");
	private String OTHER_CITY = new LocaleStrings().getString("OTHER_CITY");
	private List<String> courseModulesInAdmin, courseModulesInWebUI;
		
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
	    
	    /* Fill in the member details */
		Boolean memberCreated = createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname, occupation, postGradExperience, specialty, subSpecialty, interest,  country, city, password);		

		Assert.assertTrue(memberCreated,"Failed to create new member");		
		Reporter.log("Member successfully created: " + email);
		
		return email;
	}
	
	public void AssignCourse(String email, String course)
	{
		LeftNav leftnav = new LeftNav(getDriver());
		
		/* Click Learning Administration in the left nav */
		LearningAdministrationHome learningAdministrationHomeTab = leftnav.clickLearningAdministration();
		
		/* Enter email address into the Search For field and click Go */
		learningAdministrationHomeTab.enterSearchFor(email);
		learningAdministrationHomeTab.clickGo();
		
		/* When the results appear, click the first item in the results */
		MemberInformation memberInformationPage = learningAdministrationHomeTab.clickNthNameInMembersResults(1);
		
		/* Get the number of courses the member is enrolled in */	
		int countOfInitialEnrollments = memberInformationPage.getCountOfCurrentEnrollments();
		
		/* Click the Enrol button */
		EnrolMemberSearch enrolMemberSearchPage = memberInformationPage.clickEnrol();
		
		/* Enter the course name in the For field and click Search */
		enrolMemberSearchPage.enterFor(course);
		enrolMemberSearchPage.clickSearch();
		
		/* Click the title of the first course in the results */
		LearningAdministrationCurriculum learningAdministrationCurriculumPage = enrolMemberSearchPage.clickNthTitle(1);
	
		/* Get the list of modules */
		courseModulesInAdmin = learningAdministrationCurriculumPage.getAllCourseTitles();

		/* Click Enrol */
		learningAdministrationCurriculumPage.clickEnrol();
		
		/* Click Learning Administration in the left nav */
		leftnav.clickLearningAdministration();
		
		/* Enter email address into the Search For field and click Go */
		learningAdministrationHomeTab.enterSearchFor(email);
		learningAdministrationHomeTab.clickGo();
		
		/* When the results appear, click the first item in the results */
		memberInformationPage = learningAdministrationHomeTab.clickNthNameInMembersResults(1);
		
		/* Verify 1 course appears in the enrollments grid and the title is correct */
		Assert.assertEquals(memberInformationPage.getCountOfCurrentEnrollments(), countOfInitialEnrollments+1);
		
		Reporter.log(course + " assigned to " + email);
	}

	@Test(groups={"BVT","FullRegression","IEOnly"}, description="Verifies the start button functions for a course from the Home page and changes to a continue button afterwards")
	public void startButtonFunctionsFromHomePage()
	{
		/* Create a new member and assign the Healthy FVT Course V1 to the member */
		String course = "Healthy FVT Course V1";
		email1 = createNewMember();
		AssignCourse(email1, course);
		
		/* Click Logout */
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		/* Navigate to the webUI login page */
		Login loginPage = new Login(getDriver());
		
		/* Enter the email and password and click login */
		loginPage.enterUsername(email1);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		
		/* Verify the count of My Learning courses is 1 and the button is a start button */
		Assert.assertEquals(homePage.getCountOfMyLearningCourses(),1);
		Assert.assertEquals(homePage.getNthMyLearningButtonType(1),"start");

		/* Click the start button for the course in the My Learning panel */
		homePage.clickNthMyLearningButton(1);
		
		/* Verify the Quiz displays */
		new Quiz(getDriver());
		
		/* Verify the subway map displays */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		Assert.assertTrue(subwayMap.isSubwayMapDisplayed(), "Subway Map failed to display");
		Assert.assertTrue(subwayMap.isOpenArrowDisplayed(), "Open icon failed to display on subway map");
		Assert.assertTrue(subwayMap.isCourseNameDisplayed(), "Course name failed to display on subway map");
		Assert.assertTrue(subwayMap.isPercentCompleteDisplayed(), "Percentage complete failed to display on subway map");
		
		/* Expand the subway map and get list of module names */		
		subwayMap.clickOpenArrow();
		courseModulesInWebUI=subwayMap.getAllModuleNames();
		subwayMap.clickCloseArrow();
		
		/* Verify the same modules appear in admin and webUI */
		Assert.assertEquals(courseModulesInWebUI, courseModulesInAdmin);
		
		/* Click the Home link in the navigation */
		Navigation n = new Navigation(getDriver());
		n.clickHome();
		
		/* Verify the button for the course in the My Learning panel is a continue button */
		Assert.assertEquals(homePage.getNthMyLearningButtonType(1),"continue");
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"startButtonFunctionsFromHomePage"}, description="Verifies questions and answers are shuffled.  Also verifies i don't know is registed as answer even if correct answer is selected")
	public void shuffleQuestionsAndShuffleAnswers()
	{	
		Boolean answersShuffled=false;
		
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());

		/* Click the continue button for the course in the My Learning panel */
		homePage.clickNthMyLearningButton(1);
		
		/* Verify the Quiz displays */
		Quiz quizPage = new Quiz(getDriver());
		
		/* Get the total count of questions */
		int countOfQuestions = quizPage.getTotalCountOfQuestions();
		int countOfAnswers;
		
		/* Declare list variables to track questions and answers */
		List<Integer> questionsList = new ArrayList<Integer>();
		List<Integer> sortedQuestionsList = new ArrayList<Integer>();
		List<Integer> answersList = new ArrayList<Integer>();
		List<Integer> sortedAnswersList = new ArrayList<Integer>();
		
		/* Loop through all the questions and click I Don't Know in each case */
		for (int i=1;i<=countOfQuestions;i++)			
		{
			questionsList.add(quizPage.getQuestionNumberFromQuestionText());
			sortedQuestionsList.add(i);
			
			countOfAnswers=quizPage.getCountOfAnswers();
			/* Loop through questions and check that they are shuffled */
			for (int j=1;j<=countOfAnswers;j++){
				answersList.add(quizPage.getAnswerNumber(j));
				sortedAnswersList.add(j);
			}
			
			/* If the answers are ever not in numerical order, then set answersShuffled to be true */
			if (!answersList.equals(sortedAnswersList))
				answersShuffled=true;
			
			/* Clear each answers list for each question */
			answersList.clear();
			sortedAnswersList.clear();
			
			/* Click the correct answer and click I don't know */
			quizPage.clickCorrectAnswer();
			
			/* Check if it's the last question */
			if (i==countOfQuestions)
				quizPage.clickIDontKnowLastQuestion();
			else				
				quizPage.clickIDontKnow();
		}

		/* Verify the results page appears at the end of the pre-test */
		QuizResult quizResultPage = new QuizResult(getDriver());
		Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
		Assert.assertEquals(quizResultPage.getChartPercentageCorrect(), 0);		
		
		/* Verify the questions are shuffled */
		Assert.assertNotEquals(questionsList, sortedQuestionsList,"Questions may not be shuffled");
		
		/* Verify answers are shuffled */
		Assert.assertTrue(answersShuffled,"Answers may not be shuffled");
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"shuffleQuestionsAndShuffleAnswers"}, description="Verifies the post test is locked until all lesson activities completed and Overall GLP Score only increases when you Complete a Module")
	public void postTestLockedUntilAllLessonsCompleted()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify course is 0% complete */
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningPercentageCompleteValue(1), 0, "Incorrect percentage complete on Current Activities page");
		
		/* Click the title of the course */
		LearningPathDetail learningPathDetailPage =currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify course is 0% complete */
		Assert.assertEquals(learningPathDetailPage.getPercentCompleteValue(), 0, "Incorrect percentage complete on Learning Path Details page");
		
		learningPathDetailPage.clickNthModuleTwistie(1);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		int countOfLessonObjects = learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(1, 1);
		
		/* Click the post test */
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, countOfLessonObjects);	
		
		/* Verify learning path detail page is still displayed */
		Assert.assertTrue(learningPathDetailPage.isCourseTitleDisplayed(),"Course Title failed to display");
		Assert.assertTrue(learningPathDetailPage.isPercentCompleteDisplayed(),"Percentage Complete failed to display");
		
		/* Loop through each lesson/learning object */
		for (int i = 2; i <=countOfLessonObjects-1 ; i++)
		{
			/* Click Current Activities in the left nav */
			navigationBar = new Navigation(getDriver());		
			navigationBar.hoverOverMyLearning();
			currentActivitiesPage = navigationBar.clickCurrentActivities();
			
			/* Click the title of the course */
			learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
			
			/* Expand module 1 and lesson 1 */
			learningPathDetailPage.clickNthModuleTwistie(1);
			learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
			
			/* Click last learning object in lesson, i.e. post test */
			learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, countOfLessonObjects);
			
			/* Verify the status of the ith learning object is start, i.e. this will verify the post test did not open when clicked above */
			Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(1,1 , i), "start", "Module:" + i);
			if (i<countOfLessonObjects)
				Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(1,1 , countOfLessonObjects), "locked", "Module:" + i);
			else
				Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(1,1 , countOfLessonObjects), "start", "Module:" + i);
			
			/* Click the ith learning object */
			learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, i);
			
			/* Open the subway map */
			SubwayMap subwayMap = new SubwayMap(getDriver());
			subwayMap.clickOpenArrow();
			
			/* Expand module 1 and lesson 1 */
			subwayMap.clickNthModuleTwistie(1);
			subwayMap.clickMthShowLessonPlanOfNthModule(1, 1);
			
			/* Don't click on the post-quiz for the last loop, otherwise click it */
			if (i!=countOfLessonObjects-1)
				subwayMap.clickOthLearningObjectNameOfMthLessonOfNthModule(1, 1, countOfLessonObjects);
			
			/* Verify the current lesson/learning object is in a status of complete, Verify the current lesson/learning object is in a status of start */
			Assert.assertEquals(subwayMap.getOthLearningObjectStatusOfMthLessonOfNthModule(1, 1, i),"complete", "Module:" + i);
			Assert.assertEquals(subwayMap.getOthLearningObjectStatusOfMthLessonOfNthModule(1, 1, i+1),"start", "Module:" + i);
			
			/* Verify course is 0% complete */
			Assert.assertEquals(subwayMap.getPercentCompleteValue(), 0, "Incorrect percentage complete on Subway Map");
		}		
		
		/* Click Current Activities in the left nav */
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the course */
		learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Expand module 1 and lesson 1 */
		learningPathDetailPage.clickNthModuleTwistie(1);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		/* Click last learning object in lesson, i.e. post test */
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, countOfLessonObjects);
		
		/* The pre-test opens */
		Quiz q = new Quiz(getDriver());
		
		/* Get the total count of questions */
		int n = q.getTotalCountOfQuestions();
		
		/* Loop through all the questions and answer each one correctly */
		for (int i=1;i<=n;i++)			
		{
			q.clickCorrectAnswer();
			if (i==n)
				q.clickNextLastQuestion();
			else
				q.clickNext();
		}
		
		/* Verify the results page appears at the end of the pre-test */
		QuizResult quizResultPage = new QuizResult(getDriver());
		Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
		Assert.assertEquals(quizResultPage.getChartPercentageCorrect(),100);
		
		/* Navigate to the Current Activities page */
		navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify course is not 0% complete */
		Assert.assertNotEquals(currentActivitiesPage.getGuidedLearningPercentageCompleteValue(1), 0, "Incorrect percentage complete on Current Activities page");
		
		/* Click the title of the course */
		learningPathDetailPage =currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify course is not 0% complete */
		Assert.assertNotEquals(learningPathDetailPage.getPercentCompleteValue(), 0, "Incorrect percentage complete on Learning Path Details page");
		
		/* Click to start module 2 */
		learningPathDetailPage.clickNthModuleButton(2);
		
		/* View the subway map */
		SubwayMap subwayMap = new SubwayMap(getDriver());

		/* Verify course is not 0% complete */
		Assert.assertNotEquals(subwayMap.getPercentCompleteValue(), 0, "Incorrect percentage complete on Subway Map");
	}
	
	@Test(groups={"FullRegression","IEOnly"},dependsOnMethods={"postTestLockedUntilAllLessonsCompleted"}, description="Verifies summary can always be viewed even if lesson locked")
	public void summariesCanBeViewedIfLessonLocked() 
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the guided learning path */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);	
		
		/* Get the count of modules */
		int mods = (int)learningPathDetailPage.getCountOfModules();
		int currentMod = 1;
		int lessons, currentLesson = 1;
		ViewSummary viewSummaryPopup;
		
		/* Loop through all modules */
		while (currentMod <= mods)
		{
			/* Expand the current module */
			learningPathDetailPage.clickNthModuleTwistie(currentMod);
			
			/* Get the count of lessons for the current module */
			lessons = learningPathDetailPage.getCountOfLessonsInNthModule(currentMod);
			currentLesson = 1;
			
			/* Loop through all lessons */
			while (currentLesson<=lessons)
			{
				/* Verify the view summary link is displayed */
				Assert.assertTrue(learningPathDetailPage.isMthViewSummaryLinkInNthModuleDisplayed(currentMod, currentLesson), "view summary link failed to display on Learning Path Detail page for module:" + currentMod + ", lesson:" + currentLesson);
				
				/* Click view summary */
				viewSummaryPopup = learningPathDetailPage.clickMthViewSummaryInNthModule(currentMod, currentLesson);
				
				/* Verify summary popup displays */
				Assert.assertTrue(viewSummaryPopup.isViewSummaryPopupDisplayed(), "view summary popup failed to display on Learning Path Detail page for module:" + currentMod + ", lesson:" + currentLesson);
				
				/* Click close */
				viewSummaryPopup.clickClose();				
				currentLesson++;
			}
			
			/* Collapse the current module */
			learningPathDetailPage.clickNthModuleTwistie(currentMod);
			currentMod++;
		}		
		
		/* Click continue button */
		learningPathDetailPage.clickNthModuleButton(2);
		
		/* Open the subway map */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		subwayMap.clickOpenArrow();		
		
		/* Get the count of modules */
		int modules = subwayMap.getCountOfModules();
		int currentModule = 1;
		currentLesson = 1;
		
		/* Loop through all modules */
		while (currentModule <= modules)
		{
			/* Expand the current module */
			currentLesson = 1;
			subwayMap.clickNthModuleTwistie(currentModule);
			lessons = subwayMap.getCountOfLessonsInNthModule(currentModule);
			
			/* Loop through all lessons */
			while (currentLesson<=lessons)
			{
				/* Verify the view summary link displays */
				Assert.assertTrue(subwayMap.isMthLessonViewSummaryOfNthModuleDisplayed(currentModule, currentLesson), "view summary link failed to display on subway map for module:" + currentMod + ", lesson:" + currentLesson);
				currentLesson++;
			}
			
			/* Collapse the current module */
			subwayMap.clickNthModuleTwistie(currentModule);
			currentModule++;
		}	
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, description="Verifies error message appears on home page, activites page and progress page if a user is not enrolled for any courses")
	public void errorMessageAppearsIfUserNotEnrolledInAnyCourses()
	{		
		/* Create a new member  */
		email2 = createNewMember();
		
		/* Click Logout */
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		/* Navigate to the webUI login page */
		Login loginPage = new Login(getDriver());
		
		/* Enter the email and password and click login */
		loginPage.enterUsername(email2);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		/* The home page should display */
		Home homePage = new Home(getDriver());

		/* Verify the not enrolled to any courses message appears in the My Learning panel */
		Assert.assertEquals(homePage.getMyLearningNotEnrolledText(),NOT_ENROLLED_CURRENT_ACTIVITY);
		
		/* Navigate to the Current Activities page */	
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify the not enrolled to any courses message appears on the Current Activities page */
		Assert.assertEquals(currentActivitiesPage.getNotEnrolledText(),NOT_ENROLLED_CURRENT_ACTIVITY);
		
		/* Click the Progress tab */
		Progress progressPage = currentActivitiesPage.clickProgressTab();
		
		/* Verify the not enrolled to any courses message appears on the Progress page */
		Assert.assertEquals(progressPage.getNotEnrolledText(),NOT_ENROLLED_PROGRESS);
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"errorMessageAppearsIfUserNotEnrolledInAnyCourses"}, description="Verifies the start button functions for a course from the Current Activities page and changes to a continue button afterwards")
	public void startButtonFunctionsFromCurrentActivitiesPage()
	{
		logIntoAdminConsole();
		
		/* Create a new member and assign the Healthy FVT Course V1 to the member */
		String course = "Healthy FVT Course V1";
		AssignCourse(email2, course);
		
		/* Click Logout */
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		/* Navigate to the webUI login page */
		Login loginPage = new Login(getDriver());		
		
		/* Enter the email and password and click login */
		loginPage.enterUsername(email2);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		/* The home page should display */
		new Home(getDriver());
		
		/* Navigate to the Current Activities page */	
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the first course listed */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify the start button appears for Module 1 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(1), "start");
		
		/* Verify percentage complete is 0 */
		Assert.assertEquals(learningPathDetailPage.getPercentCompleteValue(), 0);
		
		/* Verify each module, lesson and course is not started */
		for (int i=1;i<=learningPathDetailPage.getCountOfModules();i++)
		{
			/* Verify first module is in start status but all other modules are locked */
			if (i==1)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"start", "Module:" + i);
			else
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"locked", "Module:" + i);
			
			/* Click the twistie to expand the module to view the lessons */
			learningPathDetailPage.clickNthModuleTwistie(i);
			for (int m=1; m<=learningPathDetailPage.getCountOfLessonsInNthModule(i); m++)
			{
				/* Verify each lesson is in start status  */
				Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(i, m),"start", "Module:" + i + ", Lesson:" + m);
				
				/* Click the show lesson plan link to show the lesson objects for the current lesson */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
				
				/* Loop through all the lesson objects and confirm the status */
				for (int j=1; j<=learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(i, m); j++)
					/* Verify the first learning object of the first lesson of the first module has a status of start, all others should be locked */
					if (i==1&&m==1&&j==1)
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"start", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
					else
					{
						/* Click the current lesson object to confirm it is locked and focus stays on current page */
						learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(i,m,j);
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"locked", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
					}
				
				/* Click the hide lesson plan link */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
			}
			/* Click the twistie to collapse the module */
			learningPathDetailPage.clickNthModuleTwistie(i);
		}
		
		/* Navigate to the Current Activities page */	
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify only 1 course appears and the button is a start button */
		currentActivitiesPage = new CurrentActivities(getDriver());
		Assert.assertEquals(currentActivitiesPage.getCountOfGuidedLearningPaths(),1);
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningButtonType(1,1),"start");
		
		/* Click the start button */
		currentActivitiesPage.clickGuidedLearningButton(1,1);
		new Quiz(getDriver());
		
		/* Verify the subway map displays */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		Assert.assertTrue(subwayMap.isSubwayMapDisplayed(), "Subway Map failed to display");
		Assert.assertTrue(subwayMap.isOpenArrowDisplayed(), "Open icon failed to display on subway map");
		Assert.assertTrue(subwayMap.isCourseNameDisplayed(), "Course name failed to display on subway map");
		Assert.assertTrue(subwayMap.isPercentCompleteDisplayed(), "Percentage complete failed to display on subway map");
		
		/* Click the home link in the navigation */
		navigationBar.clickHome();
		
		/* Navigate to the Current Activities page */	
		navigationBar.hoverOverMyLearning();
		navigationBar.clickCurrentActivities();
		
		/* Verify only one course appears */
		Assert.assertEquals(currentActivitiesPage.getCountOfGuidedLearningPaths(),1);
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningButtonType(1,1),"continue");
	}	
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"startButtonFunctionsFromCurrentActivitiesPage"}, description="Verifies all content is marked as complete for a lesson if the pre-test is passed 100%")
	public void allLessonsCompletedAfterPassingPretest()
	{
		/* Log in */
		getDriverAndLogin(email2, password);
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the course */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify the continue button appears for Module 1 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(1), "continue");
		
		/* Get the count of modules */
		double moduleCount = learningPathDetailPage.getCountOfModules();
		
		/* On the home page, click the continue button in the My Learning panel */
		navigationBar.clickHome();
		Home homePage = new Home(getDriver());
		homePage.clickNthMyLearningButton(1);
		
		/* Verify the subway map displays and correct percentage complete */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		Assert.assertTrue(subwayMap.isSubwayMapDisplayed(), "Subway Map failed to display");
		Assert.assertEquals(subwayMap.getPercentCompleteValue(),0);
		
		/* The pre-test opens */
		Quiz q= new Quiz(getDriver());
		
		/* Get the total count of questions */
		int n = q.getTotalCountOfQuestions();
		
		/* Loop through all the questions and answer each one correctly */
		for (int i=1;i<=n;i++)			
		{
			/* Select the correct answer and click Next */
			q.clickCorrectAnswer();
			if (i==n)
				q.clickNextLastQuestion();
			else
				q.clickNext();
		}
		
		/* Verify the results page appears at the end of the pre-test */
		QuizResult quizResultPage = new QuizResult(getDriver());
		Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
		
		/* Verify the subway map displays and correct percentage complete */
		Assert.assertTrue(subwayMap.isSubwayMapDisplayed(), "Subway Map failed to display");
		Assert.assertEquals(subwayMap.getPercentCompleteValue(),(int)Math.round(Math.floor(1/moduleCount*100)));
		
		/* Navigate to the Current Activities page */		
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the course */
		learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);	
		
		/* Verify the start button appears for Module 2 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(2), "start");

		/* Verify the status of each module, lesson and course */
		for (int i=1;i<=moduleCount;i++)
		{
			/* Verify first module is in complete status, the 2nd module has a status of start, and remaining modules is locked */
			if (i==1)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"complete", "Module:" + i);
			else if (i==2)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"start", "Module:" + i);
			else
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"locked", "Module:" + i);
			
			/* Click the twistie to expand the module to view the lessons */
			learningPathDetailPage.clickNthModuleTwistie(i);
			for (int m=1; m<=learningPathDetailPage.getCountOfLessonsInNthModule(i); m++)
			{
				if (i==1&&m==1)
					Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(i, m),"complete", "Module:" + i + ", Lesson:" + m);
				else
					Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(i, m),"start", "Module:" + i + ", Lesson:" + m);
				
				/* Click the show lesson plan link to show the lesson objects for the current lesson */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
				
				/* Loop through all the lesson objects and confirm the status */
				for (int j=1; j<=learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(i, m); j++)
					/* Verify the first learning object of the first lesson of the first module has a status of start, all others should be locked */
					if (i==1&&m==1)
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"complete", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
					else if (i==2&&j==1)
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"start", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
					else
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"locked", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
				/* Click the hide lesson plan link */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
			}
			/* Click the twistie to collapse the module */
			learningPathDetailPage.clickNthModuleTwistie(i);
		}
		
		/* Verify the correct percentage complete appears */
		Assert.assertEquals(learningPathDetailPage.getPercentCompleteValue(), (int)Math.round(Math.floor(1/moduleCount*100)));
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"allLessonsCompletedAfterPassingPretest"}, description="Verifies pre-tests can only be taken once")
	public void preTestCanOnlyBeTakenOnce()
	{
		/* Log in */
		getDriverAndLogin(email2, password);
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the course */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Expand the module and lesson */
		learningPathDetailPage.clickNthModuleTwistie(1);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		/* Click the pre-test title */
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1,1,1);
		
		/* Verify the Learning Paths page is still displayed */
		Assert.assertTrue(learningPathDetailPage.isCourseTitleDisplayed());
		Assert.assertTrue(learningPathDetailPage.isPercentCompleteDisplayed());
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, description="Verifies all lesson objects are locked until the pretest is completed")
	public void allLessonsLockedUntilPretestCompleted()
	{
		/* Create a new member and assign the Healthy FVT Course V1 to the member */
		String course = "Healthy FVT Course V1";
		email3 = createNewMember();
		AssignCourse(email3, course);
		
		/* Click Logout */
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();

		/* Navigate to the webUI login page */
		Login loginPage = new Login(getDriver());
		
		/* Enter the email and password and click login */
		loginPage.enterUsername(email3);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the course */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify the start button appears for Module 1 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(1), "start");
		
		/* Get the count of modules */
		double moduleCount = learningPathDetailPage.getCountOfModules();
		
		/* Verify the status of each module, lesson and lesson object */
		for (int i=1;i<=moduleCount;i++)
		{
			/* Verify first module is in start status, and remaining modules are locked */
			if (i==1)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"start", "Module:" + i);
			else
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"locked", "Module:" + i);
			
			/* Click the twistie to expand the module to view the lessons */
			learningPathDetailPage.clickNthModuleTwistie(i);
			
			for (int m=1; m<=learningPathDetailPage.getCountOfLessonsInNthModule(i); m++)
			{
				Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(i, m),"start", "Module:" + i + ", Lesson:" + m);
				
				/* Click the show lesson plan link to show the lesson objects for the current lesson */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
				
				/* Loop through all the lesson objects and confirm the status */
				for (int j=1; j<=learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(i, m); j++)
				{
					/* Verify the first learning object of the first lesson of the first module has a status of start, all others should be locked */
					if (i==1&&m==1&&j==1)
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"start", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
					else
					{
						/* Click the current lesson object to confirm it is locked and focus stays on current page */
						learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(i,m,j);
						
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"locked", "Module:" + i + ", Lesson:" + m + ", Lesson Object" + j);
					}
				}
				/* Click the hide lesson plan link */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
			}
			/* Click the twistie to collapse the module */
			learningPathDetailPage.clickNthModuleTwistie(i);
		}		
		
		/* On the home page, click the start button in the My Learning panel */
		navigationBar.clickHome();
		homePage = new Home(getDriver());
		homePage.clickNthMyLearningButton(1);
		
		/* Verify the subway map displays and correct percentage complete */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		Assert.assertTrue(subwayMap.isSubwayMapDisplayed(), "Subway Map failed to display");
		Assert.assertEquals(subwayMap.getPercentCompleteValue(),0);
		
		/* The pre-test opens */
		Quiz q= new Quiz(getDriver());
		
		/* Get the total count of questions */
		int n = q.getTotalCountOfQuestions();
		
		/* Loop through all the questions and answer each one correctly, except one */
		for (int i=1;i<=n;i++)			
		{
			/* Answer the first question incorrectly */
			if (i==1)
			{
				q.clickIDontKnow();
			}
			else
			{
				/* Select the correct answer and click Next */
				q.clickCorrectAnswer();
				if (i==n)
					q.clickNextLastQuestion();
				else
					q.clickNext();
			}
		}
		
		/* Verify the results page appears at the end of the pre-test */
		QuizResult quizResultPage = new QuizResult(getDriver());
		Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
		
		/* Verify the subway map displays and correct percentage complete */
		Assert.assertTrue(subwayMap.isSubwayMapDisplayed(), "Subway Map failed to display");
		Assert.assertEquals(subwayMap.getPercentCompleteValue(),0);
		
		/* Navigate to the Current Activities page */		
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Click the title of the course */
		learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);	
		
		/* Verify the start button appears for Module 2 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(1), "continue");

		/* Verify the status of each module, lesson and course */
		for (int i=1;i<=moduleCount;i++)
		{
			/* Verify first module is in resume status, and remaining modules are locked */
			if (i==1)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"resume");
			else
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"locked");
			
			/* Click the twistie to expand the module to view the lessons */
			learningPathDetailPage.clickNthModuleTwistie(i);
			for (int m=1; m<=learningPathDetailPage.getCountOfLessonsInNthModule(i); m++)
			{
				if (i==1&&m==1)
					Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(i, m),"resume");
				else
					Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(i, m),"start");
				
				/* Click the show lesson plan link to show the lesson objects for the current lesson */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
				
				int countOfLessonObjects=learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(i, m);
				/* Loop through all the lesson objects and confirm the status */
				for (int j=1; j<=countOfLessonObjects; j++)
					/* Verify the first learning object of the first lesson of the first module has a status of start, all others should be locked */
					if (i==1&&m==1&&j==1)
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"complete");
					else if (i==1&&m==1&&j<countOfLessonObjects)
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"start");
					else
						Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(i,m,j),"locked");
				/* Click the hide lesson plan link */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(i, m);
			}
			/* Click the twistie to collapse the module */
			learningPathDetailPage.clickNthModuleTwistie(i);
		}
		
		/* Verify the correct percentage complete appears */
		Assert.assertEquals(learningPathDetailPage.getPercentCompleteValue(), 0);
	}

	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"allLessonsLockedUntilPretestCompleted"}, description="Verifies lesson objects can be taken out of order")
	public void lessonObjectsCanBeTakenOutOfOrder()
	{
		/* Log in */
		getDriverAndLogin(email3, password);
		Home homePage = new Home(getDriver());
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		

		/* Click the home icon in the navigation */
		navigationBar.clickHome();
		
		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningTitle(1);		
		LearningPathDetail learningPathDetailPage = new LearningPathDetail(getDriver());		
		
		/* Click the twistie to expand the module */
		learningPathDetailPage.clickNthModuleTwistie(1);
		
		/* Click the show lesson plan link */
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		/* Get the count of lesson objects for the current lesson */
		int countOfLessonObjects = learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(1, 1);
		
		/* Click home */
		navigationBar.clickHome();
		
		/* Loop through and navigate to each of the lesson objects in reverse */
		for (int i = countOfLessonObjects-1; i>1; i--)
		{
			/* Click the home icon in the navigation */
			navigationBar.clickHome();
			
			/* Click the title of the course in the My Learning panel */
			homePage.clickNthMyLearningTitle(1);
			
			/* Click the twistie to expand the module */
			learningPathDetailPage.clickNthModuleTwistie(1);
			
			/* Click the show lesson plan link */
			learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
			
			/* Verify the post-test is locked */
			Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(1,1,countOfLessonObjects),"locked");
			
			/* Click the current lesson object title */
			learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, i);
			
		}
		/* Click the home icon in the navigation */
		navigationBar.clickHome();
		
		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningTitle(1);
		
		/* Click the twistie to expand the module */
		learningPathDetailPage.clickNthModuleTwistie(1);
		
		/* Click the show lesson plan link */
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		/* Verify the post-test is unlocked */
		Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(1,1,countOfLessonObjects),"start");
	}

	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"lessonObjectsCanBeTakenOutOfOrder"}, description="Verifies failed post-tests can be retaken and the last score is saved")
	public void canRetakeFailedPostTestsAndLastScoreIsSaved()
	{
		/* Log in */
		getDriverAndLogin(email3, password);
		Home homePage = new Home(getDriver());
		Navigation navigationBar = new Navigation(getDriver());		
		
		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningTitle(1);
		
		/* The Learning Path Detail page opens */
		LearningPathDetail learningPathDetailPage = new LearningPathDetail(getDriver());
		
		/* Expand the first lesson of the first module */
		learningPathDetailPage.clickNthModuleTwistie(1);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		/* Get the count of lesson objects in the first lesson */
		int countOfLessonObjects = learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(1, 1);
		
		/* Click on the post-test */
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, countOfLessonObjects);
		
		/* The post-test opens */
		Quiz quiz= new Quiz(getDriver());
		
		/* Get the total count of questions */
		double totalQuestions = quiz.getTotalCountOfQuestions();
		
		/* Loop through all the questions and answer the first one correctly, and the rest incorrectly */
		for (int i=1;i<=totalQuestions;i++)			
		{
			/* Answer the first question correctly */
			if (i==1)
			{
				quiz.clickCorrectAnswer();
				quiz.clickNext();
			}
			else
			{
				/* Select the correct answer and click Next */
				quiz.clickIncorrectAnswer();
				if (i==totalQuestions)
					quiz.clickNextLastQuestion();
				else
					quiz.clickNext();
			}
		}
		
		/* The Results page opens */
		QuizResult quizResultPage = new QuizResult(getDriver());

		/* Get the expected percentage correct */
		int expectedPercentageCorrect = (int)Math.round(1/totalQuestions*100);
		
		/* Verify the correct percentage correct appears */
		Assert.assertEquals(quizResultPage.getChartPercentageCorrect(), expectedPercentageCorrect);

		/* Click Home */
		navigationBar.clickHome();

		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningTitle(1);		
		
		/* Expand the first lesson of the first module */
		learningPathDetailPage.clickNthModuleTwistie(1);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);		
		
		/* Verify the correct percentage correct appears for the post-test */
		Assert.assertEquals(Integer.parseInt(learningPathDetailPage.getOthLearningObjectPercentageCorrectInMthLessonInNthModule(1, 1, countOfLessonObjects)),expectedPercentageCorrect);
	}

	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"canRetakeFailedPostTestsAndLastScoreIsSaved"}, description="Verifies Module is completed if 100% is achieved in all lessons for the module")
	public void moduleCompletedIf100PercentAchievedInAllLessonPreTests()
	{
		/* Log in */
		getDriverAndLogin(email3, password);
		Home homePage = new Home(getDriver());
		Navigation navigationBar = new Navigation(getDriver());		
		
		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningTitle(1);
		
		/* The Learning Path Detail page opens */
		LearningPathDetail learningPathDetailPage = new LearningPathDetail(getDriver());
		
		/* Expand the first lesson of the first module */
		learningPathDetailPage.clickNthModuleTwistie(1);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(1, 1);
		
		/* Get the count of lesson objects in the first lesson */
		int countOfLessonObjects = learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(1, 1);
		
		/* Click on the post-test */
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(1, 1, countOfLessonObjects);
		
		/* The post-test opens */
		Quiz quiz= new Quiz(getDriver());
		
		/* Get the total count of questions */
		double totalQuestions = quiz.getTotalCountOfQuestions();
		
		/* Loop through all the questions and answer all correctly */
		for (int i=1;i<=totalQuestions;i++)			
		{
			quiz.clickCorrectAnswer();
			if (i==totalQuestions)
				quiz.clickNextLastQuestion();
			else
				quiz.clickNext();			
		}
		
		/* The Results page opens */
		QuizResult quizResultPage = new QuizResult(getDriver());

		/* Get the expected percentage correct */
		int expectedPercentageCorrect = 100;
		
		/* Verify the correct percentage correct appears */
		Assert.assertEquals(quizResultPage.getChartPercentageCorrect(), expectedPercentageCorrect, "Incorrect % correct on the Quiz Result page.");
		
		/* Click Home and navigate to the Current Activities page */
		navigationBar.clickHome();
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify a start button appears for the 2nd module */
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningButtonType(1,2), "start", "Incorrect button type on Current Activities page.");
		
		/* Click the course title */
		currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify a start button appears for the 2nd module */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(2), "start", "Incorrect button type on Current Activities page.");
		
		/* Expand the 2nd module */
		learningPathDetailPage.clickNthModuleTwistie(2);
		
		/* Get the count of lessons in the module */
		int lessons = learningPathDetailPage.getCountOfLessonsInNthModule(2);
		
		/* Loop through all the lessons and get 100% in each pre-test */
		for (int i = 1; i<= lessons; i++)
		{
			/* Navigate to the Current Activities page */
			navigationBar.hoverOverMyLearning();
			currentActivitiesPage = navigationBar.clickCurrentActivities();
			
			/* Click the start/continue button */
			currentActivitiesPage.clickGuidedLearningButton(1, 2);
			
			/* The pre-test opens */
			quiz = new Quiz(getDriver());
			
			/* Get the total count of questions */
			totalQuestions = quiz.getTotalCountOfQuestions();
			
			/* Loop through all the questions and answer all correctly */
			for (int j=1;j<=totalQuestions;j++)			
			{
				quiz.clickCorrectAnswer();
				if (j==totalQuestions)
					quiz.clickNextLastQuestion();
				else
					quiz.clickNext();			
			}
			
			/* The Results page opens */
			quizResultPage = new QuizResult(getDriver());
			
			/* Set the expected percentage correct */
			expectedPercentageCorrect = 100;
			
			/* Verify the correct percentage correct appears */
			Assert.assertEquals(quizResultPage.getChartPercentageCorrect(), expectedPercentageCorrect, "Incorrect % correct on the Quiz Result page.");			
		}
		
		/* Navigate to the Current Activities page */
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify start button appears for module 3 and module 1 & 2 are marked as complete */
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningButtonType(1, 3),"start", "Incorrect button type for lesson 3 of module 1 on the Current Activities page");
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1,1), "complete", "Incorrect status for lesson 1 of module 1 on the Current Activities page");
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1,2), "complete", "Incorrect status for lesson 2 of module 1 on the Current Activities page");
		Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1,3), "start", "Incorrect status for lesson 3 of module 1 on the Current Activities page");
		
		/* Click the title of the course */
		currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify start button appears for module 3 and module 1 & 2 are marked as complete */
		Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(1),"complete", "Incorrect status for module 1 on the Learning Path Detail page");
		Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(2),"complete", "Incorrect status for module 2 on the Learning Path Detail page");
		Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(3),"start", "Incorrect status for module 3 on the Learning Path Detail page");
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(3),"start", "Incorrect button type for module 3 on the Learning Path Detail page");
	}

	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"moduleCompletedIf100PercentAchievedInAllLessonPreTests"}, description="Verifies a pre-test cannot be retaken once completed.")
	public void preTestCannotBeRetakenOnceCompleted()
	{
		/* Log in */
		getDriverAndLogin(email3, password);
		Home homePage = new Home(getDriver());
		Navigation navigationBar = new Navigation(getDriver());		
		
		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningButton(1);	
		
		/* The pre-test opens */
		Quiz quiz = new Quiz(getDriver());
		
		/* Get the total count of questions */
		double totalQuestions = quiz.getTotalCountOfQuestions();
		
		/* Loop through all the questions */
		int questionsToAnswerIncorrectly = 2;
		for (int i=1;i<=totalQuestions;i++)			
		{
			/* Answer first two questions incorrectly, the remaining one correctly */
			if (i<=questionsToAnswerIncorrectly)
				quiz.clickIncorrectAnswer();
			else
				quiz.clickCorrectAnswer();
			if (i==totalQuestions)
				quiz.clickNextLastQuestion();
			else
				quiz.clickNext();			
		}
		
		/* The Results page opens */
		QuizResult quizResultPage = new QuizResult(getDriver());
		
		/* Set the expected result from the pre-test */	
		int expectedResult = (int)Math.round(((totalQuestions-questionsToAnswerIncorrectly)/totalQuestions*100));
		
		/* Open the subway map and expand the module and lesson */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		subwayMap.clickOpenArrow();
		subwayMap.clickNthModuleTwistie(3);
		subwayMap.clickMthShowLessonPlanOfNthModule(3, 1);
		
		/* Verify % correct is correct for the pre-test */
		Assert.assertEquals(subwayMap.getPercentCorrectOfOthLearningObjectOfMthLessonOfNthModule(3, 1, 1), expectedResult,"Incorrect % correct on Subway Map for pre-test");		
		
		/* Close the subway map */
		subwayMap.clickCloseArrow();
		
		/* Click the re-take pre-test button */
		quizResultPage.clickLearningPathButton();
		
		/* The pre-test opens */
		quiz = new Quiz(getDriver());
		
		/* Get the total count of questions */
		totalQuestions = quiz.getTotalCountOfQuestions();
		
		/* Loop through all the questions */
		questionsToAnswerIncorrectly = 1;
		for (int i=1;i<=totalQuestions;i++)			
		{
			/* Answer the first question incorrectly, the remaining ones correctly */
			if (i<=questionsToAnswerIncorrectly)
				quiz.clickIncorrectAnswer();
			else
				quiz.clickCorrectAnswer();
			if (i==totalQuestions)
				quiz.clickNextLastQuestion();
			else
				quiz.clickNext();			
		}
		
		/* The Results page opens */
		quizResultPage = new QuizResult(getDriver());
		
		/* Set the expected % correct */
		expectedResult = (int)Math.round(((totalQuestions-questionsToAnswerIncorrectly)/totalQuestions*100));
		
		/* Open the subway map and expand the module and lesson */
		subwayMap = new SubwayMap(getDriver());
		subwayMap.clickOpenArrow();
		subwayMap.clickNthModuleTwistie(3);
		subwayMap.clickMthShowLessonPlanOfNthModule(3, 1);
		
		/* Verify % correct is correct for the pre-test */
		Assert.assertEquals(subwayMap.getPercentCorrectOfOthLearningObjectOfMthLessonOfNthModule(3, 1, 1), expectedResult,"Incorrect % correct on Subway Map for pre-test");
		
		/* Click Home and navigate to the Current Activities page */
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Navigate to the course */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Expand the module and lesson */
		learningPathDetailPage.clickNthModuleTwistie(3);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(3, 1);
		
		/* Click the pre-test */
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(3, 1, 1);
		
		/* Verify the pre-test does not display */
		Assert.assertFalse(quiz.isIDontKnowButtonDisplayed(),"quiz is displayed in error.");		
	}	
	
	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"preTestCannotBeRetakenOnceCompleted"}, description="Verifies completed only radiobutton correctly shows only completed courses on the Progress page and that completed courses no longer appear in the Current Activities tab and Learning Path is complete button appears when you complete the last Post Test")
	public void completedCoursesAppearInCompletedOnlyProgress()
	{
		/* Log in */
		getDriverAndLogin(email3, password);
		Home homePage = new Home(getDriver());
		Navigation navigationBar = new Navigation(getDriver());		
		
		/* Navigate to the Progress page */
		navigationBar.hoverOverMyLearning();
		Progress progressPage = navigationBar.clickProgress();
		
		/* Verify 1 row appears in the table */
		Assert.assertEquals(progressPage.getCountOfRowsInProgressTable(), 1, "Incorrect number of rows appearing in the Progress table.");
		
		/* Click the completed only radio button */
		progressPage.clickCompletedOnlyRadioButton();

		/* Verify error message appears indicating that no courses have been completed */
		Assert.assertTrue(progressPage.isNotEnrolledTextDisplayed(), "Error message not displaying indicating that no learning has been completed.");
		
		/* Click home */
		navigationBar.clickHome();
		
		/* Click the title of the course in the My Learning panel */
		homePage.clickNthMyLearningButton(1);	
		
		/*Verify video displays */
		Video videoPage = new Video(getDriver());
		Assert.assertTrue(videoPage.isPlayButtonDisplayed(),"Play button failed to display for video.");
		
		/* Click Home and navigate to the Current Activities page */
		navigationBar.clickHome();
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Navigate to the course */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);		
		
		/* Expand the module and lesson and click to open the post-test */
		learningPathDetailPage.clickNthModuleTwistie(3);
		learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(3, 1);
		learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(3, 1, learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(3, 1));
		
		/* The post-test opens */
		Quiz quiz = new Quiz(getDriver());
		
		/* Get the total count of questions */
		double totalQuestions = quiz.getTotalCountOfQuestions();
		
		/* Loop through all the questions */
		for (int i=1;i<=totalQuestions;i++)			
		{
			/* Answer all questions correctly */
			quiz.clickCorrectAnswer();
			if (i==totalQuestions)
				quiz.clickNextLastQuestion();
			else
				quiz.clickNext();			
		}
		
		/* The Results page opens */
		QuizResult quizResultPage = new QuizResult(getDriver());
		
		/* Verify the chart percentage is 100% */
		Assert.assertEquals(quizResultPage.getChartPercentageCorrect(), 100, "Incorrect % correct on the Quiz Result page.");
		
		/* Verify the Learning Path button is disabled and the text indicates the learning path is complete */
		Assert.assertTrue(quizResultPage.isLearningPathButtonDisabled(), "Learning Path button is not disabled, it should be");
		Assert.assertEquals(quizResultPage.getLearningPathButtonText(),LEARNING_PATH_COMPLETE, "Incorrect Learning Path button text");
		
		/* Verify % complete is 100% on the subway map */
		SubwayMap subwayMap = new SubwayMap(getDriver());		
		Assert.assertEquals(subwayMap.getPercentCompleteValue(),100, "Incorrect % correct on the subway map.");
		
		/* Navigate to the Progress page */
		navigationBar.hoverOverMyLearning();
		progressPage = navigationBar.clickProgress();
		
		/* Verify certificate of completion appears for the course */
		Assert.assertTrue(progressPage.isNthDownloadCertificateOfCompletion(1),"Certificate of Completion link failed to display.");
		
		/* Click the completed only radio button */
		progressPage.clickCompletedOnlyRadioButton();

		/* Verify the error message indicating that you have not completed any enrolled learning does not appear */
		Assert.assertFalse(progressPage.isNotEnrolledTextDisplayed(), "Message appearing indicating you have not yet completed any enrolled learning, in error.");
		
		/* Verify certificate of completion appears for the course */
		Assert.assertTrue(progressPage.isNthDownloadCertificateOfCompletion(1),"Download Certificate Of Completion link failed to appear");
		Assert.assertEquals(progressPage.getCountOfRowsInProgressTable(),1);
		
		/* Click the Current Activities tab */
		progressPage.clickCurrentActivitesTab();
		CurrentActivities currentActivitesPage = new CurrentActivities(getDriver()) ;
		
		/* Verify message appears indicating that you are not enrolled in any courses */
		Assert.assertTrue(currentActivitesPage.isNotEnrolledTextDisplayed(),"Message failed to display to indicate that you are not enrolled in any courses.");
		
		/* Click home */
		homePage = navigationBar.clickHome();
		
		/* Verify message appears in the My Learning panel indicating you are not enrolled in any learning */
		Assert.assertTrue(homePage.isMyLearningNotEnrolledTextDisplayed(),"Message failed to display in My Learning panel indicating that you are not enrolled in any courses.");
	}
	
	@Test(groups={"FullRegression","IEOnly"}, description="Verifies modules can only be taken in sequence however lessons can be completed in any order")
	public void lessonsCanBeTakenInAnyOrder()
	{
		/* Create a new member and assign the Healthy FVT Course V1 to the member */
		String course = "Healthy FVT Course V1";
		email4 = createNewMember();
		AssignCourse(email4, course);
		
		/* Click Logout */
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		/* Navigate to the webUI login page */
		Login loginPage = new Login(getDriver());
		
		/* Enter the email and password and click login */
		loginPage.enterUsername(email4);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		/* The home page should display */
		new Home(getDriver());
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();	
		
		for (int i = 1; i<currentActivitiesPage.getGuidedLearningModuleCount(1); i++)
			if (i==1)
				Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, 1), "start", "Incorrect module status for module 1" + " on Current Activities page");
			else
				Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, i), "locked", "Incorrect module status for module " + i + " on Current Activities page");
		
		/* Click the title of the course */
		LearningPathDetail learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify the start button appears for Module 1 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(1), "start", "Incorrect button type for module 1");
		
		/* Get the count of modules */
		double moduleCount = learningPathDetailPage.getCountOfModules();
		
		/* Verify the first module is in a start state, all others are locked */
		for (int i = 1; i<moduleCount; i++)
		{
			if (i==1)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"start", "Incorrect module status for module " + i + " on Learning Path Detail page");
			else
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i), "locked", "Incorrect module status for module " + i + " on Learning Path Detail page");
		}
		
		/* Click the start button for the first module */
		learningPathDetailPage.clickNthModuleButton(1);
		
		/* The pre-test opens */
		Quiz q= new Quiz(getDriver());
		
		/* Get the total count of questions */
		int n = q.getTotalCountOfQuestions();
		
		/* Loop through all the questions and answer each one correctly */
		for (int i=1;i<=n;i++)			
		{
			/* Select the correct answer and click Next */
			q.clickCorrectAnswer();
			if (i==n)
				q.clickNextLastQuestion();
			else
				q.clickNext();
		}
		
		/* Verify the results page appears at the end of the pre-test */
		QuizResult quizResultPage = new QuizResult(getDriver());
		Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
		
		/* Navigate to the Current Activities page */	
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();	
		
		for (int i = 1; i<currentActivitiesPage.getGuidedLearningModuleCount(1); i++)
		{
			if (i==1)
				Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, 1), "complete", "Incorrect module status for module " + i + " on Current Activities page");
			else if (i==2)
				Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, i), "start", "Incorrect module status for module " + i + " on Current Activities page");
			else
				Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, i), "locked", "Incorrect module status for module " + i + " on Current Activities page");
		}
		
		/* Click the title of the course */
		learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
		
		/* Verify the start button appears for Module 2 */
		Assert.assertEquals(learningPathDetailPage.getNthModuleButtonType(2), "start", "start button failed to appear for Module 2 on Learning Path Detail page");
		
		/* Get the count of modules */
		moduleCount = learningPathDetailPage.getCountOfModules();
		
		/* Verify the first module is in a complete state, the second module is in a start states, all others are locked */
		for (int i = 1; i<moduleCount; i++)
		{
			if (i==1)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"complete", "Incorrect module status for module " + i + " on Learning Path Detail page");
			else if (i==2)
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i),"start", "Incorrect module status for module " + i + " on Learning Path Detail page");
			else
				Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(i), "locked", "Incorrect module status for module " + i + " on Learning Path Detail page");
		}
		
		/* Expand Module 2 */
		learningPathDetailPage.clickNthModuleTwistie(2);
		
		/* Get the count of Lessons for Module 2 */
		int lessonCount = learningPathDetailPage.getCountOfLessonsInNthModule(2);
		
		/* Loop through each lesson and confirm the status of each lesson object, the pre-test should be unlocked for each lesson and all other lesson objects should be locked */
		for (int lesson=1; lesson<=lessonCount; lesson++)
		{
			/* Expand the lesson plan */
			learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(2, lesson);
			
			/* Loop through each lesson object and confirm the status */
			for (int lessonObject=1; lessonObject<learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(2, lesson); lessonObject++)
			{
				if (lessonObject==1)
					Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(2, lesson, lessonObject),"start", "Incorrect learning object status for lesson object " + lessonObject + " of lesson " + lesson+ " of module 2 on Learning Path Detail page");
				else
					Assert.assertEquals(learningPathDetailPage.getOthLearningObjectStatusInMthLessonInNthModule(2, lesson, lessonObject),"locked", "Incorrect learning object status for lesson object " + lessonObject + " of lesson " + lesson+ " of module 2 on Learning Path Detail page");
			}	
			/* Hide the lesson plan */
			learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(2, lesson);
		}
		
		/* Loop through each lesson, starting at the last lesson and working backwards, completing each lesson object */
		for (int lesson=lessonCount; lesson>=1; lesson--)
		{
			/* Navigate to the Current Activities page */
			navigationBar.hoverOverMyLearning();
			currentActivitiesPage = navigationBar.clickCurrentActivities();	
			
			/* Click the title of the course */
			learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
			
			/* Expand Module 2 */
			learningPathDetailPage.clickNthModuleTwistie(2);
			
			/* Expand the lesson plan */
			learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(2, lesson);
			
			/* Get the count of lesson objects for the current lesson */
			int countOfLessonObjects = learningPathDetailPage.getCountOfLearningObjectsInMthLessonInNthModule(2, lesson);
			
			/* Loop through each lesson object in the lesson */
			for (int lessonObject=1; lessonObject<=countOfLessonObjects; lessonObject++)
			{
				/* Navigate to the Current Activities page */
				navigationBar.clickHome();
				navigationBar.hoverOverMyLearning();
				currentActivitiesPage = navigationBar.clickCurrentActivities();	
				
				/*  */
				if (lesson==lessonCount && lessonObject==1)
					Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, 2), "start", "Incorrect module status on Current Activities page for lesson " + lesson + ", lesson object " + lessonObject + " on the Current Activities page");
				else
					Assert.assertEquals(currentActivitiesPage.getGuidedLearningModuleStatus(1, 2), "resume", "Incorrect module status on Current Activities page for lesson " + lesson + ", lesson object " + lessonObject + " on the Current Activities page");
					
				/* Click the title of the course */
				learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
				
				/*  */
				if (lesson==lessonCount && lessonObject==1)
					Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(2), "start", "Incorrect module status on Current Activities page for lesson " + lesson + ", lesson object " + lessonObject + " on the Learning Path Detail page");
				else
					Assert.assertEquals(learningPathDetailPage.getNthModuleStatus(2), "resume", "Incorrect module status on Current Activities page for lesson " + lesson + ", lesson object " + lessonObject + " on the Learning Path Detail page");
				
				/* Expand Module 2 */
				learningPathDetailPage.clickNthModuleTwistie(2);
				
				/* Expand the lesson plan */
				learningPathDetailPage.clickMthShowHideLessonPlanLinkInNthModule(2, lesson);
				
				/* Click the lesson object title */
				learningPathDetailPage.clickOthLearningObjectNameInMthLessonInNthModule(2, lesson, lessonObject);
				
				/* If it's a pre-test or post-test */
				if (lessonObject==1 || lessonObject==countOfLessonObjects)
				{
					/* The pre-test opens */
					Quiz quizPage= new Quiz(getDriver());
					
					/* Get the total count of questions */
					int numberOfQuestions = quizPage.getTotalCountOfQuestions();
					
					/* Loop through all the questions and answer each incorrectly for pre-test, correctly for post-test */
					for (int currentQuestion=1;currentQuestion<=numberOfQuestions;currentQuestion++)			
					{
						/* If the lesson object is a pre-test, answer the questions incorrectly, if post-test, answer correctly */
						if (lessonObject==1)						
							quizPage.clickIncorrectAnswer();
						else 
							quizPage.clickCorrectAnswer();
						
						
						if (currentQuestion==numberOfQuestions)
							quizPage.clickNextLastQuestion();
						else
							quizPage.clickNext();
					}
					
					/* Verify the results page appears at the end of the pre-test */
					quizResultPage = new QuizResult(getDriver());
					Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
				}	
				
				new SubwayMap(getDriver());
			}
			/* Navigate to the Current Activities page */
			navigationBar.hoverOverMyLearning();
			currentActivitiesPage = navigationBar.clickCurrentActivities();	
			
			/* Click the title of the course */
			learningPathDetailPage = currentActivitiesPage.clickGuidedLearningPathTitle(1);
			
			/* Expand Module 2 */
			learningPathDetailPage.clickNthModuleTwistie(2);
			
			/* Verify the lesson is marked as complete */
			Assert.assertEquals(learningPathDetailPage.getMthLessonStatusInNthModule(2,lesson ), "complete", "Lesson not marked as complete");
		}
	}
	
	@Test(groups={"FullRegression","IEOnly"}, description="Verifies an entire course can be navigated via the subway map and the subway map is displayed during the entire course")
	public void entireCourseNavigatedViaSubwayMap()
	{
		/* Create a new member and assign the Healthy FVT Course V1 to the member */
		String course = "Healthy FVT Course V1";
		email5 = createNewMember();
		AssignCourse(email5, course);
		
		/* Click Logout */
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
		
		/* Navigate to the webUI login page */
		Login loginPage = new Login(getDriver());
		
		/* Enter the email and password and click login */
		loginPage.enterUsername(email5);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		/* The home page should display */
		new Home(getDriver());
		
		/* Navigate to the Current Activities page */
		Navigation navigationBar = new Navigation(getDriver());		
		navigationBar.hoverOverMyLearning();
		CurrentActivities currentActivitiesPage = navigationBar.clickCurrentActivities();	
		
		/* Get the count of modules */
		int totalModules = currentActivitiesPage.getGuidedLearningModuleCount(1);
		
		/* Click the start button */
		currentActivitiesPage.clickGuidedLearningButton(1, 1);
		
		/* Subway Map should appear */
		SubwayMap subwayMap = new SubwayMap(getDriver());
		
		/* Loop through all modules, lessons and lesson objects */
		for (int module = 1; module<=totalModules; module++)
		{
			subwayMap.clickOpenArrow();
			subwayMap.clickNthModuleTwistie(module);
			subwayMap.clickNthModuleTwistie(module);
			subwayMap.clickCloseArrow();
			
			int totalLessons = subwayMap.getCountOfLessonsInNthModule(module);
			for (int lesson = 1; lesson<=totalLessons; lesson++)
			{
				subwayMap.clickOpenArrow();
				subwayMap.clickNthModuleTwistie(module);
				subwayMap.clickMthShowLessonPlanOfNthModule(module, lesson);
				
				int totalLessonObjects = subwayMap.getCountOfLearningObjectsInMthLessonOfNthModule(module, lesson);
				subwayMap.clickMthHideLessonPlanOfNthModule(module, lesson);
				subwayMap.clickNthModuleTwistie(module);
				subwayMap.clickCloseArrow();
				for (int lessonObject = 1; lessonObject<=totalLessonObjects; lessonObject++)
				{
					subwayMap.clickOpenArrow();
					subwayMap.clickNthModuleTwistie(module);
					subwayMap.clickMthShowLessonPlanOfNthModule(module, lesson);
					subwayMap.clickOthLearningObjectNameOfMthLessonOfNthModule(module, lesson, lessonObject);

					Assert.assertTrue(subwayMap.isSubwayMapDisplayed());
					
					if (lessonObject==1||lessonObject==totalLessonObjects)
					{
						Quiz quizPage = new Quiz(getDriver());
						
						/* Get the total count of questions */
						int n = quizPage.getTotalCountOfQuestions();
						
						/* Loop through all the questions and answer each one */
						for (int i=1;i<=n;i++)			
						{
							Assert.assertTrue(subwayMap.isSubwayMapDisplayed());
//							subwayMap.clickOpenArrow();							
//							subwayMap.clickCloseArrow();
							
							/* Select the incorrect answer for pre-tests and the correct answer for post-tests and click Next */
							if (lessonObject==1)
								quizPage.clickIncorrectAnswer();
							else
								quizPage.clickCorrectAnswer();
							if (i==n)
								quizPage.clickNextLastQuestion();
							else
								quizPage.clickNext();
						}
						
						/* Verify the results page appears at the end of the pre-test */
						QuizResult quizResultPage = new QuizResult(getDriver());
						Assert.assertTrue(quizResultPage.isPieChartDisplayed(),"Pie Chart failed to display on the pre-test result page");
					}	
					else
					{
						Assert.assertTrue(subwayMap.isSubwayMapDisplayed());
					}
				}
			}	
			/* Verify percentage complete */
			Assert.assertEquals(subwayMap.getPercentCompleteValue(), (int)Math.round(Math.floor((double)module/(double)totalModules*100)));
		}
		
		/* Navigate to the progress page */
		navigationBar.clickHome();
		navigationBar.hoverOverMyLearning();
		currentActivitiesPage = navigationBar.clickCurrentActivities();
		
		/* Verify the not enrolled to any courses message appears on the Current Activities page */
		Assert.assertEquals(currentActivitiesPage.getNotEnrolledText(),NOT_ENROLLED_CURRENT_ACTIVITY);
	}

}
