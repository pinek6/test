package tests.webUI.functional;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.groups.AddItem;
import webUI.home.groups.DeleteThisGroup;
import webUI.home.groups.Discussion;
import webUI.home.groups.EditBlock;
import webUI.home.groups.GroupsView;
import webUI.home.groups.GroupsViewDiscussionsTab;
import webUI.home.groups.GroupsViewOverviewTab;
import webUI.home.groups.GroupsViewResourcesTab;
import webUI.home.groups.JoinGroups;
import webUI.home.groups.MyGroups;
import webUI.home.groups.NewGroup;
import webUI.home.groups.NewGroupReady;
import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;

public class GroupsTests extends LoadBrowser{

	/* Page Headers */
	private String newGroupPageHeader = new LocaleStrings().getString("NEW_GROUP_PAGE_HEADER");
	private String question_answered_warning = new LocaleStrings().getString("QUESTION_ANSWERED_WARNING");
	private String question_unanswered_warning = new LocaleStrings().getString("QUESTION_UNANSWERED_WARNING");
	private String accept_answer_link = new LocaleStrings().getString("ACCEPT_ANSWER_LINK");
	private String decline_answer_link = new LocaleStrings().getString("DECLINE_ANSWER_LINK");
	
	private String email1, email2, displayname, fname, lname, occupation, country, city, password, newPublicGroupName, newPrivateGroupName, newPublicDiscussionGroupName, newPrivateDiscussionGroupName,
	customContentTitle, customContentText, featureResourcesTitle, featureResourcesText, featurePeopleTitle, featurePeopleText, newsTitle, newsText, newPublicQuestionGroupName, newPublicQuestionGroupName2,
	newPrivateQuestionGroupName, newDiscussionDescription, newDiscussionTitle, newReplyDescription, newReplyDescription2, newReplyTitle;
	private int postGradExperience, specialty, subSpecialty, interest, groupPositionInTable;
	
	private String OTHER_CITY = new LocaleStrings().getString("OTHER_CITY");
		
//	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
//	public void beforeMethod()
//    {
		/* Before each test, login */
//		getDriverAndLogin();		
//    }
	
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
		
		/* Log out */ 
		TopNav n = new TopNav(getDriver());
		n.clickLogout();
		
		return email;
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies a new public group can be created")
	public void NewPublicGroupSaved()
	{
		/* Create member */
		email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		Date d = new Date();
		newPublicGroupName = "public fvtGroup "+d.getTime();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups mymyGroupsPage = navigation.clickMyGroups();		

		/* Click the New Group button */
		NewGroup newmyGroupsPage = mymyGroupsPage.clickNewGroup();
		
		/* Verify page header is correct */
		Assert.assertTrue(newmyGroupsPage.getHeader().contains(newGroupPageHeader),"Incorrect header on New Group page");
		
		/* Verify UI elements appear */
		Assert.assertTrue(newmyGroupsPage.isNameFieldDisplayed(),"Name field failed to display on the New Group page");
		Assert.assertTrue(newmyGroupsPage.isSubjectDropdownDisplayed(),"Subject dropdown failed to display on the New Group page");
		Assert.assertTrue(newmyGroupsPage.isPublicAccessRadiobuttonDisplayed(),"Public Access radiobutton failed to display on the New Group page");
		Assert.assertTrue(newmyGroupsPage.isMembersOnlyRadiobuttonDisplayed(),"Members Only radiobutton failed to display on the New Group page");
		Assert.assertTrue(newmyGroupsPage.isSaveButtonDisplayed(),"Save Button failed to display on the New Group page");
		Assert.assertTrue(newmyGroupsPage.isCancelLinkDisplayed(),"Cancel link failed to display on the New Group page");
		
		/* Enter a group name */
		newmyGroupsPage.enterName(newPublicGroupName);
		newmyGroupsPage.selectGroupTypeByIndex(1);
		
		/* Click Save */
		NewGroupReady newGroupReadyDialog = newmyGroupsPage.clickSaveButton();
		
		/* Click get started */
		GroupsView newGroup = newGroupReadyDialog.clickGetStarted();
		
		/* Verify Group is saved */
		Assert.assertEquals(newGroup.getHeader(), newPublicGroupName);
		Assert.assertTrue(newGroup.isPublicAccessTextDisplayed());
		
		/* Verify panels appear */
		GroupsViewOverviewTab groupsViewOverviewTab = new GroupsViewOverviewTab(getDriver());
		Assert.assertTrue(groupsViewOverviewTab.isCustomContentPanelDisplayed(),"Custom Content panel failed to display after saving new group");
		Assert.assertTrue(groupsViewOverviewTab.isFeaturedResourcesPanelDisplayed(),"Featured Resources panel failed to display after saving new group");
		Assert.assertTrue(groupsViewOverviewTab.isFeaturedPeoplePanelDisplayed(),"Featured People panel failed to display after saving new group");
		Assert.assertTrue(groupsViewOverviewTab.isNewsPanelDisplayed(),"News panel failed to display after saving new group");
		Assert.assertTrue(groupsViewOverviewTab.isRecentUpdatesPanelDisplayed(),"Recent Updates panel failed to display after saving new group");
		
		Reporter.log("Group successfully created: " + newPublicGroupName);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "NewPublicGroupSaved" }, description="Verify the links can be added in the Resources tab of a group")
	public void AddItemLinkToResources()
	{	
		/* Log in */
		getDriverAndLogin(email1, password);
		
		Date d = new Date();
		String titleOfLink = "link title "+d.getTime();
		String webAddress = "www.ibm.com/"+d.getTime();
		String description = "link description "+d.getTime();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		

		/* Click the group in the list */
		groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		GroupsView groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click the Resources tab */
		GroupsViewResourcesTab resourcesTab = groupView.clickResourcesTab();
		
		/* Click Add Item */
		AddItem addItemPopup = resourcesTab.clickAddItemButton();
		
		/* Slect the Web Link radio button */
		addItemPopup.clickCreateWebLinkRadiobutton();
		
		/* Enter the details of the link */
		addItemPopup.enterTitleForLink(titleOfLink);
		addItemPopup.enterWebAddress(webAddress);
		addItemPopup.enterDescription(description);
		
		/* Click Add */
		addItemPopup.clickAdd();
		
		/* Verify link is found in the results */
		Assert.assertTrue(resourcesTab.isResourceInResultsDisplayed(titleOfLink),"Link not found in resources list");			
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "AddItemLinkToResources" }, description="Verifies the custom content can be edited on the Groups Overview page")
	public void EditCustomContentPublicGroup()
	{	
		/* Log in */
		getDriverAndLogin(email1, password);
		
		Date d = new Date();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		
		/* Click the group in the list */
		int groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		GroupsView groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		GroupsViewOverviewTab groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());

		/* Click edit custom content */
		EditBlock editBlockPopup =  groupsViewOverviewTab.clickEditCustomContent();
		 
		/* Set value of new title and text */
		customContentTitle = "cc_group " + d.getTime();
		customContentText = "cc_text "+ d.getTime();
		
		/* Enter the new custom content title and text */
		editBlockPopup.enterCustomContentTitle(customContentTitle);
		editBlockPopup.enterInformation(customContentText);
		
		/* Click save */
		editBlockPopup.clickSaveButton();
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getCustomContentTitle(), customContentTitle);
		Assert.assertEquals(groupsViewOverviewTab.getCustomContentText(), customContentText);
		
		navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		 groupsPage = navigation.clickMyGroups();	
		
		/* Click the group in the list */
		groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getCustomContentTitle(), customContentTitle);
		Assert.assertEquals(groupsViewOverviewTab.getCustomContentText(), customContentText);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "EditCustomContentPublicGroup" }, description="Verifies the Featured Resources can be edited on the Groups Overview page")
	public void EditFeaturedResourcesPublicGroup()
	{	
		/* Log in */
		getDriverAndLogin(email1, password);

		Date d = new Date();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		
		/* Click the group in the list */
		int groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		GroupsView groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		GroupsViewOverviewTab groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());

		/* Click edit custom content */
		EditBlock editBlockPopup =  groupsViewOverviewTab.clickEditFeaturedResources();
		 
		/* Set value of new title and text */
		featureResourcesTitle = "fr_group " + d.getTime();
		featureResourcesText = "fr_text "+ d.getTime();
		
		/* Enter the new custom content title and text */
		editBlockPopup.enterTitle(featureResourcesTitle);
		editBlockPopup.enterInformation(featureResourcesText);
		
		/* Click save */
		editBlockPopup.clickSaveButton();
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedResourcesTitle(), featureResourcesTitle);
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedResourcesText(), featureResourcesText);
		
		navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		 groupsPage = navigation.clickMyGroups();	
		
		/* Click the group in the list */
		groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedResourcesTitle(), featureResourcesTitle);
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedResourcesText(), featureResourcesText);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "EditFeaturedResourcesPublicGroup" }, description="Verifies the Featured People can be edited on the Groups Overview page")
	public void EditFeaturedPeoplePublicGroup()
	{	
		/* Log in */
		getDriverAndLogin(email1, password);

		Date d = new Date();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		
		/* Click the group in the list */
		int groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		GroupsView groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		GroupsViewOverviewTab groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());

		/* Click edit custom content */
		EditBlock editBlockPopup =  groupsViewOverviewTab.clickEditFeaturedPeople();
		 
		/* Set value of new title and text */
		featurePeopleTitle = "fp_group " + d.getTime();
		featurePeopleText = "fp_text "+ d.getTime();
		
		/* Enter the new custom content title and text */
		editBlockPopup.enterTitle(featurePeopleTitle);
		editBlockPopup.enterInformation(featurePeopleText);
		
		/* Click save */
		editBlockPopup.clickSaveButton();
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedPeopleTitle(), featurePeopleTitle);
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedPeopleText(), featurePeopleText);
		
		navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		 groupsPage = navigation.clickMyGroups();	
		
		/* Click the group in the list */
		groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedPeopleTitle(), featurePeopleTitle);
		Assert.assertEquals(groupsViewOverviewTab.getFeaturedPeopleText(), featurePeopleText);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "EditFeaturedPeoplePublicGroup" }, description="Verifies the News can be edited on the Groups Overview page")
	public void EditNewsPublicGroup()
	{	
		/* Log in */
		getDriverAndLogin(email1, password);

		Date d = new Date();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		
		/* Click the group in the list */
		int groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		GroupsView groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		GroupsViewOverviewTab groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());

		/* Click edit custom content */
		EditBlock editBlockPopup =  groupsViewOverviewTab.clickEditNews();
		 
		/* Set value of new title and text */
		newsTitle = "nws_group " + d.getTime();
		newsText = "nws_text "+ d.getTime();
		
		/* Enter the new custom content title and text */
		editBlockPopup.enterTitle(newsTitle);
		editBlockPopup.enterInformation(newsText);
		
		/* Click save */
		editBlockPopup.clickSaveButton();		
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getNewsTitle(), newsTitle);
		Assert.assertEquals(groupsViewOverviewTab.getNewsText(), newsText);
		
		navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		 groupsPage = navigation.clickMyGroups();	
		
		/* Click the group in the list */
		groupPositionInTable = groupsPage.findGroup(newPublicGroupName);
		groupView = groupsPage.clickNthGroupTitle(groupPositionInTable);
		
		/* Click edit group */
		groupView.clickEditGroup();		
		groupsViewOverviewTab= new GroupsViewOverviewTab(getDriver());
		
		/* Verify new content title and text is saved */
		Assert.assertEquals(groupsViewOverviewTab.getNewsTitle(), newsTitle);
//		Assert.assertEquals(groupsViewOverviewTab.getNewsText(), newsText);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "EditNewsPublicGroup" }, description="Verifies a public group can be deleted")
	public void DeletePublicGroup()
	{	
		/* Log in */
		getDriverAndLogin(email1, password);

		/* Navigate to the groups page */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();				
		
		/* Loop through the groups and find the group to be deleted */
		int groupPositionInTable = myGroupsPage.findGroup(newPublicGroupName);
		GroupsView groupsViewPage = myGroupsPage.clickNthGroupTitle(groupPositionInTable);		
		
		/* Click delete */
		DeleteThisGroup deleteThisGroupPopup =groupsViewPage.clickDelete();
		
		/* Select the confirm checkbox and click permanently delete group button */
		deleteThisGroupPopup.selectConfirmCheckbox();
		deleteThisGroupPopup.clickPermanentlyDeleteGroup();		
	
		/* Verify group doesn't appear */
		Assert.assertTrue(myGroupsPage.findGroup(newPublicGroupName)==-1,"Group failed to delete");
		
		/* Navigate away from groups page and then back to it */
		navigation.clickHome();
		navigation.hoverOverGroups();
		myGroupsPage = navigation.clickMyGroups();
		
		/* Verify group doesn't appear */
		Assert.assertTrue(myGroupsPage.findGroup(newPublicGroupName)==-1,"Group failed to delete");
		
		Reporter.log("Group deleted: " + newPublicGroupName);
	}
	
	@Test(groups={"FullRegression"}, dependsOnMethods = { "NewPublicGroupSaved" }, description="Verifies a new private group can be created")
	public void NewPrivateGroupSaved()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		Date d = new Date();
		newPrivateGroupName = "private fvtGroup "+d.getTime();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();	

		/* Click the New Group button */
		NewGroup newGroupPage = myGroupsPage.clickNewGroup();
		
		/* Verify page header is correct */
		Assert.assertTrue(newGroupPage.getHeader().contains(newGroupPageHeader),"Incorrect header on New Group page");
		
		/* Enter a group name */
		newGroupPage.enterName(newPrivateGroupName);
		newGroupPage.selectGroupTypeByIndex(1);
		
		/* Select the Members Only checkbox */
		newGroupPage.selectMembersOnlyRadiobutton();
		
		/* Click Save */
		NewGroupReady newGroupReadyDialog = newGroupPage.clickSaveButton();
		
		/* Click get started */
		GroupsView newGroup = newGroupReadyDialog.clickGetStarted();
		
		/* Verify Group is saved */
		Assert.assertEquals(newGroup.getHeader(), newPrivateGroupName);
		Assert.assertTrue(newGroup.isMembersOnlyTextDisplayed());
	}

	@Test(groups={"FullRegression"}, dependsOnMethods = { "NewPrivateGroupSaved" }, description="Verifies a private group can be deleted")
	public void DeletePrivateGroup()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Navigate to the groups page */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();		
		
		/* Loop through the groups and find the group to be deleted */		
		int groupPositionInTable = myGroupsPage.findGroup(newPrivateGroupName);
		GroupsView groupsViewPage = myGroupsPage.clickNthGroupTitle(groupPositionInTable);		
		
		/* Click delete */
		DeleteThisGroup deleteThisGroupPopup =groupsViewPage.clickDelete();
		
		/* Select the confirm checkbox and click permanently delete group button */
		deleteThisGroupPopup.selectConfirmCheckbox();
		deleteThisGroupPopup.clickPermanentlyDeleteGroup();		
	
		navigation.clickHome();
		navigation.hoverOverGroups();
		myGroupsPage = navigation.clickMyGroups();
		
		Assert.assertTrue(myGroupsPage.findGroup(newPrivateGroupName)==-1,"Group failed to delete");
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies a discussion/question can be created on a public group")
	public void CreatePublicDiscussion()
	{
		/* Create member */
		email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Set new discussion name */
		Date d = new Date();
		newPublicGroupName = "TestPublicGroup_"+d.getTime();
		newPublicDiscussionGroupName = "TestPublicDiscussionGroup_"+d.getTime();
		newPublicQuestionGroupName = "TestPublicQuestionGroup_"+d.getTime();
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over Groups and click Join Groups */
		navigation.hoverOverGroups();
		JoinGroups joinGroupsPage = navigation.clickJoinGroups();	
		
		/* Get the number of groups displayed */
		int groups_displayed = joinGroupsPage.getCountOfGroupsDisplayed();
		
		GroupsView groupViewPage;
		
		/* Join the first group, if at least one group exists */
		if (groups_displayed > 0) {
			groupViewPage = joinGroupsPage.clickNthGroupJoin(1);
		}
		
		/* Create new group, if there's no group */
		else {
			MyGroups myGroupsPage = joinGroupsPage.clickMyGroupsTab();
			NewGroup newGroupPage = myGroupsPage.clickNewGroup();
			newGroupPage.enterName(newPublicGroupName);
			newGroupPage.selectGroupTypeByIndex(1);
			newGroupPage.selectPublicAccessRadiobutton();
			NewGroupReady newGroupReadyDialog = newGroupPage.clickSaveButton();
			groupViewPage = newGroupReadyDialog.clickGetStarted();
			groupViewPage.clickEditGroup();
		}
		
		/* Navigate to discussions tab and click new discussion button */
		GroupsViewDiscussionsTab groupDiscussionsTab = groupViewPage.clickDiscussionsTab();
		groupDiscussionsTab.clickNewDicussionButton();
		
		
		/* Create new discussion on the group */
		groupDiscussionsTab.enterDiscussionName(newPublicDiscussionGroupName);
		groupDiscussionsTab.selectDiscussionSubjectDropdownByIndex(1);
		groupDiscussionsTab.clickSave();
		
		/* Check the new discussion is displayed/not displayed on the different discussion type lists */
		groupDiscussionsTab.selectAllDiscussionsMeTypeDropdown();
		List<String> discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicDiscussionGroupName), "Discussion "+ newPublicDiscussionGroupName +" not displayed on the list");
		
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicDiscussionGroupName), "Discussion "+ newPublicDiscussionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectMyAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicDiscussionGroupName), "Discussion "+ newPublicDiscussionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectAllDiscussionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicDiscussionGroupName), "Discussion "+ newPublicDiscussionGroupName +" not displayed on the list");
		
		groupDiscussionsTab.selectAllUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicDiscussionGroupName), "Discussion "+ newPublicDiscussionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectAllAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicDiscussionGroupName), "Discussion "+ newPublicDiscussionGroupName +" displayed on the list");
		
		
		/* Create new question on the group */
		groupDiscussionsTab.clickNewDicussionButton();
		groupDiscussionsTab.enterDiscussionName(newPublicQuestionGroupName);
		groupDiscussionsTab.selectDiscussionSubjectDropdownByIndex(1);
		groupDiscussionsTab.selectQuestionCheckbox();
		groupDiscussionsTab.clickSave();
		
		/* Check the new question is displayed/not displayed on the different discussion type lists */
		groupDiscussionsTab.selectAllDiscussionsMeTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" not displayed on the list");
		int n = discussionsList.indexOf(newPublicQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label not attached to the question"+ newPublicQuestionGroupName);	
		
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" not displayed on the list");
		n = discussionsList.indexOf(newPublicQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label not attached to the question"+ newPublicQuestionGroupName);
		
		groupDiscussionsTab.selectMyAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectAllDiscussionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" not displayed on the list");
		n = discussionsList.indexOf(newPublicQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label not attached to the question"+ newPublicQuestionGroupName);
		
		groupDiscussionsTab.selectAllUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" not displayed on the list");
		n = discussionsList.indexOf(newPublicQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label not attached to the question"+ newPublicQuestionGroupName);
		
		groupDiscussionsTab.selectAllAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" displayed on the list");
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "CreatePublicDiscussion" }, description="Verifies it's possible to edit a discussion")
	public void EditDiscussion()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Set new discussion title and description */
		Date d = new Date();
		newDiscussionDescription = "TestDiscussionDescription_"+d.getTime();
		newDiscussionTitle = "TestDiscussionTitle_"+d.getTime();
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over Groups and click My Groups */
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();
		
		/* Navigate to Discussions tab */
		GroupsView groupsViewPage = myGroupsPage.clickNthGroupTitle(1);
		GroupsViewDiscussionsTab groupDiscussionsTab = groupsViewPage.clickDiscussionsTab();
		
		/* Open the first discussion create by my user */
		groupDiscussionsTab.selectAllDiscussionsMeTypeDropdown();
		Discussion discussionPage = groupDiscussionsTab.clickNthDiscussionTitle(2);
		
		/* Edit the discussion */
		Assert.assertTrue(discussionPage.isInitialDiscussionThreadDisplayed(), "Initial discussion thread not displayed");
		discussionPage.clickEditInitialDiscussionThreadButton();
		
		/* Edit the  title */
		Assert.assertTrue(discussionPage.isChangeTitleLinkDisplayed(), "Change Title link not displayed");
		discussionPage.clickChangeTitleLink();
		Assert.assertTrue(discussionPage.isChangeTitleFieldDisplayed(), "Change Title field not displayed");
		discussionPage.enterNewTitle(newDiscussionTitle);
		
		/* Edit the subject */
		Random r = new Random();
		int n = discussionPage.getCountOfOptionsInSubjectDropdown();
		int r_subject = r.nextInt(n-1)+2;
		discussionPage.selectSubjectFromDropdownByIndex(r_subject);
		String selectedSubject = discussionPage.getSelectedTextOfSubjectDropdown();
		
		/* Edit the description */
		discussionPage.enterDescription(newDiscussionDescription);
		if (currentBrowser.equals("firefox")) discussionPage.clickSaveButton("firefox");
		else discussionPage.clickSaveButton("other");
		
		/* Check all fields are updated */
		Assert.assertEquals(discussionPage.getInitialDiscussionThreadTitle() , newDiscussionTitle , "Title not updated");
		Assert.assertEquals(discussionPage.getInitialDiscussionThreadDescription() , newDiscussionDescription , "Description not updated");
		Assert.assertEquals(discussionPage.getInitialDiscussionThreadSubject() , selectedSubject , "Subject not updated");
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "EditDiscussion" }, description="Verifies it's possible to reply to my question")
	public void CreateReplyToMyQuestion()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Set new reply title and description */
		Date d = new Date();
		newReplyTitle = "TestReplyTitle_"+d.getTime();
		newReplyDescription = "TestReplyDescription_"+d.getTime();
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over Groups and click My Groups */
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();
		
		/* Navigate to Discussions tab */
		GroupsView groupsViewPage = myGroupsPage.clickNthGroupTitle(1);
		GroupsViewDiscussionsTab groupDiscussionsTab = groupsViewPage.clickDiscussionsTab();
		
		/* Open the first unanswered question create by my user */
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		Discussion discussionPage = groupDiscussionsTab.clickNthDiscussionTitle(1);
		Assert.assertTrue(discussionPage.isQuestionAnsweredWarningDisplayed(), "Question answered/unanswered warning not displayed");
		Assert.assertEquals(discussionPage.getQuestionAnsweredWarning(), question_unanswered_warning);
		
		/* Reply to the question */
		Assert.assertTrue(discussionPage.isInitialDiscussionThreadDisplayed(), "Initial discussion thread not displayed");
		discussionPage.clickReplyInitialDiscussionThreadButton();
		
		/* Enter reply */
		Assert.assertTrue(discussionPage.isChangeTitleLinkDisplayed(), "Change Title link not displayed");
		discussionPage.clickChangeTitleLink();
		Assert.assertTrue(discussionPage.isChangeTitleFieldDisplayed(), "Change Title field not displayed");
		discussionPage.enterNewTitle(newReplyTitle);
		discussionPage.enterDescription(newReplyDescription);
		if (currentBrowser.equals("firefox")) discussionPage.clickSaveButton("firefox");
		else discussionPage.clickSaveButton("other");
		
		/* Accept the answer */
		List<String> l = discussionPage.getListOfReplyItemTitles();
		int i = l.indexOf(newReplyTitle);		
		Assert.assertTrue(discussionPage.isNthQuestionAcceptAnswerButtonDisplayed(i+1), "Accept/Decline Answer link not displayed");
		Assert.assertEquals(discussionPage.getNthQuestionAcceptAnswerButtonLabel(i+1), accept_answer_link);
		discussionPage.clickNthQuestionAcceptAnswerButton(i+1);
		Assert.assertEquals(discussionPage.getQuestionAnsweredWarning(), question_answered_warning);
		
		/* Navigate do Discussions tab */
		groupDiscussionsTab.clickDiscussionsTab();
		groupDiscussionsTab.selectMyAnsweredQuestionsTypeDropdown();
		
		/* Check the question is now displayed on the answered questions list */
		List<String> discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" not displayed on the list");
		int n = discussionsList.indexOf(newPublicQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthAnsweredQuestionDisplayed(n), "Answered question label not attached to the question"+ newPublicQuestionGroupName);
		
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "CreateReplyToMyQuestion" }, description="Verifies it's possible to delete a question")
	public void DeleteQuestion()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Set details for new discussion */
		Date d = new Date();
		newPublicQuestionGroupName2 = "TestPublicQuestionGroup2_"+d.getTime();
		newReplyDescription2 = "TestReplyDescription2_"+d.getTime();
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over Groups and click My Groups */
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();
		
		/* Navigate to Discussions tab */
		GroupsView groupsViewPage = myGroupsPage.clickNthGroupTitle(1);
		GroupsViewDiscussionsTab groupDiscussionsTab = groupsViewPage.clickDiscussionsTab();
		
		/* Create new question on the group */
		groupDiscussionsTab.clickNewDicussionButton();
		groupDiscussionsTab.enterDiscussionName(newPublicQuestionGroupName2);
		groupDiscussionsTab.selectDiscussionSubjectDropdownByIndex(1);
		groupDiscussionsTab.selectQuestionCheckbox();
		groupDiscussionsTab.clickSave();
		
		/* Navigate to my unanswered questions */
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		
		/* Check the new question created is displayed on the list */
		List<String> discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPublicQuestionGroupName2), "Question "+ newPublicQuestionGroupName2 +" not displayed on the list");
		int n = discussionsList.indexOf(newPublicQuestionGroupName2);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label not attached to the question"+ newPublicQuestionGroupName2);
		
		/* Open the question */
		Assert.assertEquals(groupDiscussionsTab.getNthDiscussionTitle(1) , newPublicQuestionGroupName2);
		Discussion discussionPage = groupDiscussionsTab.clickNthDiscussionTitle(1);
		
		/* Enter reply */
		Assert.assertTrue(discussionPage.isInitialDiscussionThreadDisplayed(), "Initial discussion thread not displayed");
		discussionPage.clickReplyInitialDiscussionThreadButton();
		discussionPage.enterDescription(newReplyDescription2);
		if (currentBrowser.equals("firefox")) discussionPage.clickSaveButton("firefox");
		else discussionPage.clickSaveButton("other");
		
		/* Delete reply */
		Assert.assertEquals(discussionPage.getCountOfReplies() , 1);
		discussionPage.clickNthQuestionDeleteButton(1);
		Assert.assertEquals(discussionPage.getCountOfReplies() , 0 , "Reply not deleted");
		
		/* Delete unanswered question without replies */
		discussionPage.clickDeleteInitialDiscussionThreadButton();
		
		/* Check the unanswered questions is removed */
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicQuestionGroupName2), "Question "+ newPublicQuestionGroupName2 +" displayed on the list");
		
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "DeleteQuestion" }, description="Verifies it's possible to delete a question with reply")
	public void DeleteQuestionWithReply()
	{
		/* Log in */
		getDriverAndLogin(email1, password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over Groups and click My Groups */
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();
		
		/* Navigate to Discussions tab */
		GroupsView groupsViewPage = myGroupsPage.clickNthGroupTitle(1);
		GroupsViewDiscussionsTab groupDiscussionsTab = groupsViewPage.clickDiscussionsTab();
		
		/* Navigate to my answered questions */
		groupDiscussionsTab.selectMyAnsweredQuestionsTypeDropdown();
		
		/* Open question */
		Assert.assertEquals(groupDiscussionsTab.getNthDiscussionTitle(1) , newPublicQuestionGroupName);
		Discussion discussionPage = groupDiscussionsTab.clickNthDiscussionTitle(1);
		Assert.assertEquals(discussionPage.getQuestionAnsweredWarning(), question_answered_warning);
		
		/* Decline Answer */
		List<String> l = discussionPage.getListOfReplyItemTitles();
		int i = l.indexOf(newReplyTitle);
		Assert.assertTrue(discussionPage.isNthQuestionAcceptAnswerButtonDisplayed(i+1), "Accept/Decline Answer link not displayed");
		Assert.assertEquals(discussionPage.getNthQuestionAcceptAnswerButtonLabel(i+1), decline_answer_link);
		discussionPage.clickNthQuestionDeclineAnswerButton(i+1);
		Assert.assertEquals(discussionPage.getQuestionAnsweredWarning(), question_unanswered_warning);
		
		/* Delete unanswered question with replies */
		discussionPage.clickDeleteInitialDiscussionThreadButton();
		
		/* Check my unanswered questions is empty */
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		List<String> discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPublicQuestionGroupName), "Question "+ newPublicQuestionGroupName +" displayed on the list");		
	}
	
	
	@Test(groups={"FullRegression"}, description="Verifies a discussion/question can be created on a private group")
	public void CreatePrivateDiscussion()
	{
		/* Create member */
		email2 = createNewMember();
		getDriverAndLogin(email2,password);
		
		/* Set new group name */
		Date d = new Date();
		newPrivateGroupName = "TestPrivateGroup_"+d.getTime();
		newPrivateDiscussionGroupName = "TestPrivateDiscussionGroup_"+d.getTime();
		newPrivateQuestionGroupName = "TestPrivateQuestionGroup_"+d.getTime();
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over Groups and click My Groups */
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();	
		
		/* Create private group */
		NewGroup newGroupPage = myGroupsPage.clickNewGroup();
		newGroupPage.enterName(newPrivateGroupName);
		newGroupPage.selectGroupTypeByIndex(1);
		newGroupPage.selectMembersOnlyRadiobutton();
		NewGroupReady newGroupReadyDialog = newGroupPage.clickSaveButton();
		GroupsView groupViewPage = newGroupReadyDialog.clickGetStarted();
		groupViewPage.clickEditGroup();
		
		/* Navigate to discussions tab and click new discussion button */
		GroupsViewDiscussionsTab groupDiscussionsTab = groupViewPage.clickDiscussionsTab();
		groupDiscussionsTab.clickNewDicussionButton();
		
		
		/* Create new discussion on the group */
		groupDiscussionsTab.enterDiscussionName(newPrivateDiscussionGroupName);
		groupDiscussionsTab.selectDiscussionSubjectDropdownByIndex(1);
		groupDiscussionsTab.clickSave();
		
		/* Check the new discussion is displayed/not displayed on the different discussion type lists */
		groupDiscussionsTab.selectAllDiscussionsMeTypeDropdown();
		List<String> discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPrivateDiscussionGroupName), "Discussion "+ newPrivateDiscussionGroupName +" not displayed on the list");
		
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPrivateDiscussionGroupName), "Discussion "+ newPrivateDiscussionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectMyAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPrivateDiscussionGroupName), "Discussion "+ newPrivateDiscussionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectAllDiscussionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPrivateDiscussionGroupName), "Discussion "+ newPrivateDiscussionGroupName +" not displayed on the list");
		
		groupDiscussionsTab.selectAllUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPrivateDiscussionGroupName), "Discussion "+ newPrivateDiscussionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectAllAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPrivateDiscussionGroupName), "Discussion "+ newPrivateDiscussionGroupName +" displayed on the list");
		
		
		/* Create new question on the group */
		groupDiscussionsTab.clickNewDicussionButton();
		groupDiscussionsTab.enterDiscussionName(newPrivateQuestionGroupName);
		groupDiscussionsTab.selectDiscussionSubjectDropdownByIndex(1);
		groupDiscussionsTab.selectQuestionCheckbox();
		groupDiscussionsTab.clickSave();
		
		/* Check the new question is displayed/not displayed on the different discussion type lists */
		groupDiscussionsTab.selectAllDiscussionsMeTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPrivateQuestionGroupName), "Question "+ newPrivateQuestionGroupName +" not displayed on the list");
		int n = discussionsList.indexOf(newPrivateQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label no attached to the question"+ newPrivateQuestionGroupName);
		
		groupDiscussionsTab.selectMyUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPrivateQuestionGroupName), "Question "+ newPrivateQuestionGroupName +" not displayed on the list");
		n = discussionsList.indexOf(newPrivateQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label no attached to the question"+ newPrivateQuestionGroupName);
		
		groupDiscussionsTab.selectMyAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPrivateQuestionGroupName), "Question "+ newPrivateQuestionGroupName +" displayed on the list");
		
		groupDiscussionsTab.selectAllDiscussionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPrivateQuestionGroupName), "Question "+ newPrivateQuestionGroupName +" not displayed on the list");
		n = discussionsList.indexOf(newPrivateQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label no attached to the question"+ newPrivateQuestionGroupName);
		
		groupDiscussionsTab.selectAllUnansweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertTrue(discussionsList.contains(newPrivateQuestionGroupName), "Question "+ newPrivateQuestionGroupName +" not displayed on the list");
		n = discussionsList.indexOf(newPrivateQuestionGroupName);
		Assert.assertTrue(groupDiscussionsTab.isNthUnansweredQuestionDisplayed(n), "Unanswered question label no attached to the question"+ newPrivateQuestionGroupName);
		
		groupDiscussionsTab.selectAllAnsweredQuestionsTypeDropdown();
		discussionsList = groupDiscussionsTab.getListOfDiscussionTitles();
		Assert.assertFalse(discussionsList.contains(newPrivateQuestionGroupName), "Question "+ newPrivateQuestionGroupName +" displayed on the list");
	}
}
