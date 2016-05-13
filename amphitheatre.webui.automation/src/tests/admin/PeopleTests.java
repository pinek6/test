package tests.admin;

import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.Home;
import webUI.login.Login;
import admin.learningAdministration.EnrolMemberSearch;
import admin.learningAdministration.LearningAdministrationCurriculum;
import admin.learningAdministration.LearningAdministrationHome;
import admin.learningAdministration.MemberInformation;
import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;
import bluebox.BlueboxHomePage;
import bluebox.BlueboxInbox;

public class PeopleTests extends LoadBrowser {
	
	/* Declare some variables for creating the new member*/			
	String password,email,fname,lname,academicPositionTitle,displayname,courseName = "Healthy FVT Course V1",   audienceText, yourOccupationText, postGradExperienceText, organizationText,countryText, cityText, othrCty;
	int country, organization,postGradExperience, specialty, yourOccupation,audience , subSpecialty, interest;
	
	private String OTHER_CITY = new LocaleStrings().getString("OTHER_CITY");
	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies a new member can be created in the admin")
	public void CreateANewMember() throws InterruptedException
	{		
		/* Set the email and displayname */
		Date d = new Date();
		email = "fvt"+d.getTime()+"@bluebox.lotus.com";
		password="Pa88w0rdfvt";
		displayname = "z_fvt_"+d.getTime();
		fname = "first"+d.getTime();
		lname = "last"+d.getTime();
		academicPositionTitle = "title";
		courseName = "Healthy FVT Course V1";
		audience=1;
		yourOccupation=1;
		organization=1;
		country=1;
		othrCty="othrCty"+d.getTime();
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
	    
	    /* Get the number of expertises, interests and audiences */
	    int expertises = createMemberPage.getNumberOfExpertise();
	    int interests = createMemberPage.getNumberOfInterests();
	    int audiences = createMemberPage.getNumberOfAudiences();
	    int specialties = createMemberPage.getCountOfSpecialties();
	    int subSpecialties = createMemberPage.getCountOfSubSpecialties();
	    
	    /* Fill in the member details */
		createMemberPage.enterEmail(email)		
		.enterPassword(password)
		.enterConfirmPassword(password)
		.enterFirstName(fname)
		.enterLastName(lname)
		.selectCountryByIndex(country)
		.enterDisplayName(displayname)
		.enterAcademicPositionTitle(academicPositionTitle)
		.selectYourOccupationByIndex(yourOccupation)
		.selectCityByVisibleText(OTHER_CITY)	
		.selectPostGraduateExperienceByIndex(postGradExperience)
		.selectAllSpecialties()
		.selectAllSubSpecialties()
		.selectNthInterest(1) //FIXME 20328
//		.selectAllInterests() FIXME 20328			
		.enterOtherCity(othrCty)
		.selectContentMasterAuthorCheckbox()
		.selectContentMasterReviewerCheckbox()
		.selectMasterAdminCheckbox()
		.selectSiteAdminCheckbox()
		.selectNthExpertise(1) //FIXME 20328
//		.selectAllExpertises() FIXME 20328
		.selectAllAudiences();
	    
		yourOccupationText=createMemberPage.getSelectedYourOccupationVisibleText();
		postGradExperienceText=createMemberPage.getSelectedPostGraduateExperienceVisibleText();
		countryText=createMemberPage.getSelectedCountryVisibleText();
		cityText=createMemberPage.getSelectedCityVisibleText();
		organizationText=createMemberPage.getSelectedOrganizationVisibleText();
		
	    /* Click Save */
		createMemberPage.clickSave();
		
		/* Verify confirmation message appears and error message doesn't */
		Assert.assertFalse(createMemberPage.isErrorMessageDisplayed(),"Error message appeared on saving new member, " + createMemberPage.getErrorMessages());
		Assert.assertTrue(createMemberPage.isResultMessageDisplayed(),"Member saved confirmation message failed to display");
		Assert.assertTrue(createMemberPage.isEmailAddressReadOnlyDisplayed(),"Email address not appearing as read-only after saving member");		
		
		/* Click People in the left navigation */
		leftnav.clickPeople();
		
		/* Enter the display name into the Name Contains field */
		peoplePage.enterNameContains(displayname);
		
		/* Click Update Display button */
		peoplePage.clickUpdateDisplay();
		
		/* Click the Edit link for the first row in the results */
		peoplePage.clickNthEdit(1);
		
		/* Verify fields are correctly populated */
		Assert.assertEquals(createMemberPage.getEmailAddressReadOnly(), email);
		Assert.assertEquals(createMemberPage.getFirstName(), fname);
		Assert.assertEquals(createMemberPage.getLastName(), lname);
		Assert.assertEquals(createMemberPage.getDisplayName(), displayname);
		Assert.assertEquals(createMemberPage.getAcademicPositionTitle(), academicPositionTitle);
		Assert.assertEquals(createMemberPage.getSelectedYourOccupationIndex(), yourOccupation);
		Assert.assertEquals(createMemberPage.getSelectedPostGraduateExperienceVisibleText(), postGradExperienceText);
		Assert.assertEquals(createMemberPage.getSelectedCountryIndex(), country);
		Assert.assertEquals(createMemberPage.getSelectedCityVisibleText(), OTHER_CITY);

		for (int i=1; i<=specialties; i++)
			Assert.assertTrue(createMemberPage.isNthSpecialtyCheckboxChecked(i),createMemberPage.getNthSpecialty(i) + " is not checked, it should be.");
		for (int i=1; i<=subSpecialties; i++)
			Assert.assertTrue(createMemberPage.isNthSubSpecialtyCheckboxChecked(i),createMemberPage.getNthSubSpecialty(i) + " is not checked, it should be.");
//		for (int i=1; i<=interests; i++) FIXME 20328
//			Assert.assertTrue(createMemberPage.isNthInterestCheckboxChecked(i),createMemberPage.getNthInterest(i) + " is not checked, it should be."); FIXME 20328
		for (int i=1; i<=audiences; i++)
			Assert.assertTrue(createMemberPage.isNthAudienceCheckboxChecked(i),createMemberPage.getNthAudience(i) + " is not checked, it should be.");		
//		for (int i=1; i<=expertises; i++)	 FIXME 20328	
//			Assert.assertTrue(createMemberPage.isNthExpertiseCheckboxChecked(i),createMemberPage.getNthExpertise(i) + " is not checked, it should be.");	 FIXME 20328			
		
		Reporter.log("Member successfully created: " + email);
		
		/* Log out of the admin */
		TopNav topNavigation = new TopNav(getDriver());
		topNavigation.clickLogout();		
		
		Login loginPage = new Login(getDriver());		
		
		/* Enter username and password into the fields */
		loginPage.enterUsername(email);
		loginPage.enterPassword(password);

		/* Click Login */
		loginPage.clickLogin();
		
		Home h = new Home(getDriver());	
		
		/* Log out of the current user */
		Navigation n = new Navigation(getDriver());		
		
		/* Verify the ui elements appears */
//		Assert.assertTrue(h.isLibraryPanelDisplayed(),"Library panel on home page failed to load");
//		Assert.assertTrue(h.isGroupsPanelDisplayed(),"Groups panel on home page failed to load");
//		Assert.assertTrue(h.isMyLearningPanelDisplayed(),"My Learning panel on home page failed to load");
		Assert.assertTrue(h.isColumn1Displayed(), "Column 1 on the home page failed to load");
		
		/* Hover over the avatar image in the top navigation */
		n.hoverOverAvatar();
		
		/* Verify the logged in user is the correct user */
		Assert.assertEquals(n.getLoggedInUsername(), fname + " " + lname);
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"CreateANewMember"}, description="Assign FVT course to member")
	public void AssignCourseToMember()
	{		
		/* Click Learning Administration in the left nav */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministrationHome learningAdministrationHomeTab = leftnav.clickLearningAdministration();
		
		/* Enter email address into the Search For field and click Go */
		learningAdministrationHomeTab.enterSearchFor(email);
		learningAdministrationHomeTab.clickGo();
		
		/* When the results appear, click the first item in the results */
		MemberInformation memberInformationPage = learningAdministrationHomeTab.clickNthNameInMembersResults(1);
		
		/* Verify the member is not enrolled on any courses */
		Assert.assertEquals(memberInformationPage.getCountOfCurrentEnrollments(), 0);		
		
		/* Click the Enrol button */
		EnrolMemberSearch enrolMemberSearchPage = memberInformationPage.clickEnrol();
		
		/* Enter the course name in the For field and click Search */
		enrolMemberSearchPage.enterFor(courseName);
		enrolMemberSearchPage.clickSearch();
		
		/* Click the title of the first course in the results */
		LearningAdministrationCurriculum learningAdministrationCurriculumPage = enrolMemberSearchPage.clickNthTitle(1);
		
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
		Assert.assertEquals(memberInformationPage.getCountOfCurrentEnrollments(), 1);
		Assert.assertEquals(memberInformationPage.getNthEnrollmentTitle(1), courseName);	
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"}, dependsOnMethods={"AssignCourseToMember"}, description="Withdraw FVT course from member")
	public void WithdrawCourseFromMember()
	{			
		/* Click Learning Administration in the left nav */
		LeftNav leftnav = new LeftNav(getDriver());	
		LearningAdministrationHome learningAdministrationHomeTab = leftnav.clickLearningAdministration();
		
		/* Enter email address into the Search For field and click Go */
		learningAdministrationHomeTab.enterSearchFor(email);
		learningAdministrationHomeTab.clickGo();
		
		/* When the results appear, click the first item in the results */
		MemberInformation memberInformationPage = learningAdministrationHomeTab.clickNthNameInMembersResults(1);
		
		/* Verify the member is enrolled on one course */
		Assert.assertEquals(memberInformationPage.getCountOfCurrentEnrollments(), 1);
		
		/* Click the checkbox for the course and click Withdraw */
		memberInformationPage.selectNthEnrollment(1);
		memberInformationPage.clickWithdraw();

		/* Click Learning Administration in the left nav */
		learningAdministrationHomeTab = leftnav.clickLearningAdministration();
		
		/* Enter email address into the Search For field and click Go */
		learningAdministrationHomeTab.enterSearchFor(email);
		learningAdministrationHomeTab.clickGo();
		
		/* When the results appear, click the first item in the results */
		memberInformationPage = learningAdministrationHomeTab.clickNthNameInMembersResults(1);
		
		/* Verify the member is not enrolled on any courses */
		Assert.assertEquals(memberInformationPage.getCountOfCurrentEnrollments(), 0);
	}

	@Test(groups={"BVT","FullRegression"}, description="Verifies when a new member is created, a system generated password is sent to their email inbox")
	public void PasswordEmailSentToNewUser()
	{		
		/* Set the email and displayname */
		Date d = new Date();
		email = "fvt"+d.getTime()+"@bluebox.lotus.com";
		displayname = "z_fvt_"+d.getTime();
		fname = "first"+d.getTime();
		lname = "last"+d.getTime();
		int yourOccupation=1;
		int country=1;
		int city=1;
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
	    createMemberPage.checkSendMemberPasswordEmail();
	    Assert.assertTrue(createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname,yourOccupation, postGradExperience, specialty, subSpecialty,interest, country, city),"Failed to create new member" + createMemberPage.getErrorMessages());		
		
	    Reporter.log("User created: " + email);
		
		/* Click People in the left navigation */
		leftnav.clickPeople();
		
		/* Enter the display name into the Name Contains field */
		peoplePage.enterEmailContains(email);
		
		/* Click Update Display button */
		peoplePage.clickUpdateDisplay();

		/* Click the Edit link for the first row in the results */
		Assert.assertEquals(peoplePage.getNthEmailInResultsGrid(1),email);
		
		/* Navigate to the blue box homepage */
		BlueboxHomePage blueboxHomePage = new BlueboxHomePage(getDriver());
		
		/* Enter the email address and click submit */
		blueboxHomePage.enterEmailAddress(email);
		BlueboxInbox inbox = blueboxHomePage.clickSubmit();
		
		/* Keep refreshing the inbox until an email appears in the inbox */
		inbox.clickRefreshUntilNEmails(1);
		
		/* Click the first email in the list */
		inbox.clickNthEmailSubjectField(1);
		
		/* Extract the password from the email */
		password = inbox.getPassword();
	
		Reporter.log("Password: " + password);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods={"PasswordEmailSentToNewUser"}, description="Verifies when a new member is created, they can log in")
	public void NewMemberCanLogin()
	{
		/* Click Logout */
		TopNav t = new TopNav(getDriver());
		t.clickLogout();
		
		/* Go the webUI */
		Login l = new Login(getDriver());
				
		/* Enter username and password into the fields */
		l.enterUsername(email);
		l.enterPassword(password);

		/* Click Login */
		l.clickLogin();
		
		Home h = new Home(getDriver());	
		
		/* Log out of the current user */
		Navigation n = new Navigation(getDriver());		
		
		/* Verify the ui elements appears */
//		Assert.assertTrue(h.isLibraryPanelDisplayed(),"Library panel on home page failed to load");
//		Assert.assertTrue(h.isGroupsPanelDisplayed(),"Groups panel on home page failed to load");
//		Assert.assertTrue(h.isMyLearningPanelDisplayed(),"My Learning panel on home page failed to load");
		Assert.assertTrue(h.isColumn1Displayed(), "Column 1 on the home page failed to load");
		
		/* Hover over the avatar image in the top navigation */
		n.hoverOverAvatar();
		
		/* Verify the logged in user is the correct user */
		Assert.assertEquals(n.getLoggedInUsername(), fname + " " + lname);
	}
}

