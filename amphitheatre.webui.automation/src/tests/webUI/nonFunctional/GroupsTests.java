package tests.webUI.nonFunctional;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.groups.GroupsView;
import webUI.home.groups.GroupsViewDiscussionsTab;
import webUI.home.groups.GroupsViewMembersTab;
import webUI.home.groups.GroupsViewResourcesTab;
import webUI.home.groups.JoinGroups;
import webUI.home.groups.MyGroups;
import webUI.home.groups.NewGroup;
import webUI.home.groups.NewGroupReady;

public class GroupsTests extends LoadBrowser {
	
	/* Page Headers */
	private String groupsPageHeader = new LocaleStrings().getString("GROUPS_PAGE_HEADER");
	private String newGroupPageHeader = new LocaleStrings().getString("NEW_GROUP_PAGE_HEADER");
	
	String newPublicGroupName, newPrivateGroupName;
	
	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
	public void beforeMethod()
    {
		/* Before each test, login */
		getDriverAndLogin();		
    }

	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Groups page loads successfully")
	public void GroupsPageOpens()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");
		
		Assert.assertTrue(groupsPage.isNewGroupButtonDisplayed(),"New Group button failed to load");
		Assert.assertTrue(groupsPage.isMyInvitationsPanelDisplayed(),"My Invitations panel failed to load");		
	}

	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the My Groups page loads successfully") 
	public void MyGroupsPageOpens()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups myGroupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(myGroupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(myGroupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(myGroupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");

		Assert.assertTrue(myGroupsPage.isNewGroupButtonDisplayed(),"New Group button failed to load");
		Assert.assertTrue(myGroupsPage.isMyInvitationsPanelDisplayed(),"My Invitations panel failed to load");
	}
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Join Groups page loads successfully")
	public void JoinGroupsPageOpens()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		JoinGroups joinGroupsPage = navigation.clickJoinGroups();
		
		Assert.assertTrue(joinGroupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertFalse(joinGroupsPage.isMyGroupsTabSelected(),"My Groups tab should not be selected");
		Assert.assertTrue(joinGroupsPage.isJoinGroupsTabSelected(),"Join Groups tab should be selected");
		
		Assert.assertTrue(joinGroupsPage.isSearchFieldDisplayed(),"Search field should be displayed");
		Assert.assertTrue(joinGroupsPage.isMyInvitationsPanelDisplayed(),"My Invitations panel failed to load");
	}
	
	@Test(groups={"FullRegression"},dependsOnMethods = { "GroupsPageOpens" }, description="Verifies the facet list at the top of the page is populated when a subject is chosen in the left column")
	public void FacetListIsPopulated()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();

		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");
		
		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		
		/* Get the total number of subjects in the left column */
		int countOfSubjectsInLeftColumn = groupsPage.getCountOfSubjectsInLeftColumn();
		
		if (countOfSubjectsInLeftColumn==0)
			Assert.fail("No subjects found in Subject list");
		
		/* Loop through and click the checkbox for each subject */
		for (int i=1; i<countOfSubjectsInLeftColumn; i++)
		{
			groupsPage.clickNthSubjectInLeftColumn(i);
		}
		
		/* Verify the facet list is the same as those subjects checked in the left column*/
		Assert.assertEquals(groupsPage.getListOfSubjectsCheckedInLeftColumn(), groupsPage.getListOfSubjectsInFacetList());
	}
	
	@Test(groups={"FullRegression"},dependsOnMethods = { "FacetListIsPopulated" }, description="Verifies that when items are removed from the facet list at the top, they are also unchecked in the left column")
	public void SelectedSubjectsCanBeRemovedUsingFacetList()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		
		groupsPage.clickJoinGroupsTab();
		groupsPage.clickMyGroupsTab();
		
		/* Get the total number of subjects in the left column */
		int countOfSubjectsInLeftColumn = groupsPage.getCountOfSubjectsInLeftColumn();
		
		/* Loop through and click the checkbox for each subject */
		for (int i=1; i<=countOfSubjectsInLeftColumn; i++)
		{
			groupsPage.clickNthSubjectInLeftColumn(i);
		}
		
		/* Verify the facet list is the same as those subjects checked in the left column*/
		Assert.assertEquals(groupsPage.getListOfSubjectsCheckedInLeftColumn(), groupsPage.getListOfSubjectsInFacetList());
		
		/* If there are more than 1 subjects, remove the first and last item in the facet list */
		if (countOfSubjectsInLeftColumn>1)
			groupsPage.clickNthSubjectInFacetList(countOfSubjectsInLeftColumn);
		groupsPage.clickNthSubjectInFacetList(1);
		
		/* Verify the facet list is the same as those subjects checked in the left column*/
		Assert.assertEquals(groupsPage.getListOfSubjectsCheckedInLeftColumn(), groupsPage.getListOfSubjectsInFacetList());
	}
	
	@Test(groups={"FullRegression"}, dependsOnMethods = { "FacetListIsPopulated" }, description="Verifies subjects deselected from the left hand column are also removed from the facet list")
	public void SelectedSubjectsCanBeRemovedUsingLeftColumn()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		
		groupsPage.clickJoinGroupsTab();
		groupsPage.clickMyGroupsTab();
		
		/* Get the total number of subjects in the left column */
		int countOfSubjectsInLeftColumn = groupsPage.getCountOfSubjectsInLeftColumn();
		
		/* Loop through and click the checkbox for each subject */
		for (int i=1; i<=countOfSubjectsInLeftColumn; i++)
		{
			groupsPage.clickNthSubjectInLeftColumn(i);
		}
		
		/* Verify the facet list is the same as those subjects checked in the left column*/
		Assert.assertEquals(groupsPage.getListOfSubjectsCheckedInLeftColumn(), groupsPage.getListOfSubjectsInFacetList());
		
		/* If there are more than 1 subjects, remove the first and last item in the facet list */
		if (countOfSubjectsInLeftColumn>1)
			groupsPage.clickNthSubjectInLeftColumn(countOfSubjectsInLeftColumn);
		groupsPage.clickNthSubjectInLeftColumn(1);
		
		/* Verify the facet list is the same as those subjects checked in the left column*/
		Assert.assertEquals(groupsPage.getListOfSubjectsCheckedInLeftColumn(), groupsPage.getListOfSubjectsInFacetList());
	}	
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "GroupsPageOpens" }, description="Verify the Groups View page opens")
	public void GroupsViewPageLoads()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		/* Click the first group in the list */
		GroupsView groupView = groupsPage.clickNthGroupTitle(1);
		
		/* Verify the tabs appear and the first tab is selected */
		Assert.assertTrue(groupView.isOverviewTabDisplayed(),"Overview tab is not displayed");
		Assert.assertTrue(groupView.isMembersTabDisplayed(),"Members tab is not displayed");
		Assert.assertTrue(groupView.isResourcesTabDisplayed(),"Resources tab is not displayed");
		Assert.assertTrue(groupView.isDiscussionsTabDisplayed(),"Discussions tab is not displayed");
		
		Assert.assertTrue(groupView.isOverviewTabSelected(),"Overview tab is not selected");
		Assert.assertFalse(groupView.isMembersTabSelected(),"Members tab is selected in error");
		Assert.assertFalse(groupView.isResourcesTabSelected(),"Resources tab is selected in error");
		Assert.assertFalse(groupView.isDiscussionsTabSelected(),"Discussions tab is selected in error");				
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "GroupsViewPageLoads" }, description="Verify the Members tab of the Groups View page opens")
	public void MembersTabOnGroupsViewPageLoads()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		
		
		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		/* Click the first group in the list */
		GroupsView groupView = groupsPage.clickNthGroupTitle(1);
		
		/* Click the Members tab */
		GroupsViewMembersTab membersTab = groupView.clickMembersTab();
		
		/* Verify the search field and add members button appears */
		Assert.assertTrue(membersTab.isSearchInputDisplayed(),"Search field is not displayed");
		Assert.assertTrue(membersTab.isMemberCountDisplayed(),"Member Count is not displayed");
		Assert.assertTrue(membersTab.isOwnersPanelLocatorDisplayed(),"Owners Panel failed to load");
	}
	
	@Test(groups={"FullRegression"}, description="Verify the information for each member on the Members page loads")
	public void MemberInfoDisplaysOnMembersTabOnGroupsViewPage()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();			

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		
		/* Click the first group in the list */
		GroupsView groupView = groupsPage.clickNthGroupTitle(1);
		
		/* Click the Members tab */
		GroupsViewMembersTab membersTab = groupView.clickMembersTab();
		
		/* Get total number of members displayed */
		int countOfCategories = membersTab.getCountOfMemberListItems();
		
		/* Loop through each member and verify all information appears */
		for (int i=1; i<=countOfCategories; i++)
		{
			Assert.assertTrue(membersTab.isNthMemberImageDisplayed(i),"Image not displaying for member " + membersTab.getNthMemberName(i));
			
			Assert.assertTrue(membersTab.isNthMemberNameDisplayed(i));			
			Assert.assertNotEquals(membersTab.getNthMemberName(i).length(),0,"Member Name length should be >0, Actual " + membersTab.getNthMemberName(i).length());
	
			Assert.assertTrue(membersTab.isNthMemberTypeDisplayed(i),"Member Type not displaying for member " + membersTab.getNthMemberName(i));
			Assert.assertNotEquals(membersTab.getNthMemberType(i).length(),0,"Member Type length should be >0, Actual " + membersTab.getNthMemberType(i).length());
		}
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "GroupsViewPageLoads" }, description="Verify the Resources tab of the Groups View page opens")
	public void ResourcesTabOnGroupsViewPageLoads()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();			
		
		Assert.assertTrue(groupsPage.getHeader().contains(groupsPageHeader),"Incorrect header on groups page");
		Assert.assertTrue(groupsPage.isMyGroupsTabSelected(),"My Groups tab should be selected");
		Assert.assertFalse(groupsPage.isJoinGroupsTabSelected(),"Join Groups tab should not be selected");

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		
		/* Click the first group in the list */
		GroupsView groupView = groupsPage.clickNthGroupTitle(1);
		
		/* Click the Resources tab */
		GroupsViewResourcesTab resourcesTab = groupView.clickResourcesTab();
	
		/* Verify various UI page elements appear */ 
		Assert.assertTrue(resourcesTab.isSearchInputDisplayed(),"Search Input failed to load");
		Assert.assertTrue(resourcesTab.isAddItemButtonDisplayed(),"Add Item button failed to load");
		Assert.assertTrue(resourcesTab.isFeaturedPanelDisplayed(),"Featured Panel link failed to load");	
	}
	
	@Test(groups={"FullRegression"}, dependsOnMethods = { "GroupsPageOpens" }, description="Verify the Discussions tab of the Groups View page opens")
	public void DiscussionsTabOnGroupsViewPageLoads()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		

		/* If no groups are found, create one */
		if (groupsPage.getCountOfGroupsDisplayed()==0)
		{
			CreateNewGroup();
		}
		
			
		/* Click the first group in the list */
		GroupsView groupView = groupsPage.clickNthGroupTitle(1);
		
		/* Click the Discussions tab */
		GroupsViewDiscussionsTab discussionsTab = groupView.clickDiscussionsTab();
		
		/* Verify UI elements appear */
		Assert.assertTrue(discussionsTab.isNewDicussionButtonDisplayed(),"New Discussion button failed to load");		
		
		/* Get the total number of discussions displaying on the page */
		int countOfDicussions = discussionsTab.getCountOfDiscussionsDisplayed();
		
		/* Loop through and verify UI elements appear for each discussion topic */
		for (int i = 1; i<=countOfDicussions; i++)
		{
			Assert.assertTrue(discussionsTab.isNthDiscussionTitleDisplayed(i));
			Assert.assertTrue(discussionsTab.isNthDiscussionStartedByDisplayed(i));
			Assert.assertTrue(discussionsTab.isNthDiscussionLikesDisplayed(i));
			Assert.assertTrue(discussionsTab.isNthDiscussionRepliesDisplayed(i));
			Assert.assertTrue(discussionsTab.isNthDiscussionLastUpdatedByDisplayed(i));
		}
	}

	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = { "GroupsPageOpens" }, description="Verifies the New Group page opens")
	public void NewGroupPageOpens()
	{
		//Date d = new Date();
		//String newGroupName = "fvtGroup"+d.getTime();
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverGroups();
		MyGroups groupsPage = navigation.clickMyGroups();		

		/* Click the New Group button */
		NewGroup newGroupPage = groupsPage.clickNewGroup();
		
		/* Verify page header is correct */
		Assert.assertTrue(newGroupPage.getHeader().contains(newGroupPageHeader),"Incorrect header on New Group page");
		
		/* Verify UI elements appear */
		Assert.assertTrue(newGroupPage.isNameFieldDisplayed(),"Name field failed to display on the New Group page");
		Assert.assertTrue(newGroupPage.isSubjectDropdownDisplayed(),"Subject dropdown failed to display on the New Group page");
		Assert.assertTrue(newGroupPage.isPublicAccessRadiobuttonDisplayed(),"Public Access radiobutton failed to display on the New Group page");
		Assert.assertTrue(newGroupPage.isMembersOnlyRadiobuttonDisplayed(),"Members Only radiobutton failed to display on the New Group page");
		Assert.assertTrue(newGroupPage.isSaveButtonDisplayed(),"Save Button failed to display on the New Group page");
		Assert.assertTrue(newGroupPage.isCancelLinkDisplayed(),"Cancel link failed to display on the New Group page");
	}

	public void CreateNewGroup()
	{
		Date d = new Date();
		newPublicGroupName = "public fvtGroup "+d.getTime();
		
		/* Click the New Group button */
		MyGroups myGroupsPage = new MyGroups(getDriver());
		
		/* Click the New Group button */
		NewGroup newmyGroupsPage = myGroupsPage.clickNewGroup();	
		
		/* Enter a group name */
		newmyGroupsPage.enterName(newPublicGroupName);
		newmyGroupsPage.selectGroupTypeByIndex(1);
		newmyGroupsPage.selectSubjectByIndex(1);
		
		/* Click Save */
		NewGroupReady newGroupReadyDialog = newmyGroupsPage.clickSaveButton();
		
		newGroupReadyDialog.clickGetStarted();
		
		Navigation n = new Navigation(getDriver());
		n.hoverOverGroups();
		n.clickMyGroups();
	}
	
}
