package tests.webUI.nonFunctional;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.peopleSearch.Directory;
import webUI.home.peopleSearch.ImFollowing;

public class PeopleSearchTests extends LoadBrowser{

	/* Page Headers */
	private String peopleSearchPageHeader = new LocaleStrings().getString("PEOPLE_SEARCH");
	private String noPeopleErrorMessage = new LocaleStrings().getString("NO_PEOPLE_ERROR_MESSAGE");
	
	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
	public void before()
    {
		getDriverAndLogin();
    }
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the People Search Directory page loads successfully")
	public void PeopleSearchDirectoryPageOpens()
	{
		/* Hover of People Search in the left navigation and click Directory */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverPeopleSearch();
		Directory peopleSearchDirectoryPage = navigation.clickPeopleSearchDirectory();
	
		/* Verify the page header and loading doesn't appear */
		Assert.assertEquals(peopleSearchDirectoryPage.getHeader(), peopleSearchPageHeader);
		Assert.assertFalse(peopleSearchDirectoryPage.isLoadingDisplayed(),"Loading dialog failed to disappear");
		
		/* Verify UI fields appear */
		Assert.assertTrue(peopleSearchDirectoryPage.isSearchInputDisplayed(),"Search Input field failed to load on the People Search page");
		Assert.assertTrue(peopleSearchDirectoryPage.isClearSearchDisplayed(),"Clear Search link failed to load on the People Search page");

		/* Verify I'm Following tab isn't selected and Directory is */
		Assert.assertTrue(peopleSearchDirectoryPage.isDirectoryTabSelected(),"DIRECTORY tab not selected");
		Assert.assertFalse(peopleSearchDirectoryPage.isImFollowingTabSelected(),"I'M FOLLOWING tab selected in error");	
	}
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the People Search I'm Following page loads successfully")
	public void PeopleSearchImFollowingPageOpens()
	{
		/* Hover of People Search in the left navigation and click I'm Following */
		Navigation navigation = new Navigation(getDriver());	
		navigation.hoverOverPeopleSearch();		
		ImFollowing peopleSearchImFollowingPage = navigation.clickPeopleSearchImFollowing();

		
		/* Verify the page header and loading doesn't appear*/
		Assert.assertEquals(peopleSearchImFollowingPage.getHeader(), peopleSearchPageHeader);
		Assert.assertFalse(peopleSearchImFollowingPage.isLoadingDisplayed(),"Loading dialog failed to disappear");
		
		/* Verify I'm Following tab is selected and Directory tab isn't */
		Assert.assertFalse(peopleSearchImFollowingPage.isDirectoryTabSelected(),"DIRECTORY tab selected in error");
		Assert.assertTrue(peopleSearchImFollowingPage.isImFollowingTabSelected(),"I'M FOLLOWING tab not selected");
		Assert.assertTrue(peopleSearchImFollowingPage.isRecommendedPeoplePanelDisplayed(),"Recommended People panel failed to display");
	}
	
	@Test(groups={"FullRegression"}, description="Verifies the users displayed on Directory page are all experts")
	public void PeopleSearchExperts()
	{
		
		/* Hover of People Search in the left navigation and click Directory */
		Navigation navigation = new Navigation(getDriver());	
		navigation.hoverOverPeopleSearch();		
		Directory peopleSearchDirectoryPage = navigation.clickPeopleSearchDirectory();
		
		/* Check expert checkbox is visible and select it */
		peopleSearchDirectoryPage.clickExpertsOnlyCheckbox();
		
		/* Check there are experts registered on the system */
		if (peopleSearchDirectoryPage.getNoPeopleErrorMessage().equals(noPeopleErrorMessage)) Assert.fail("No experts registered on the system.");
		
		/* Check no tictac tab is added to the Directory page */
		Assert.assertFalse(peopleSearchDirectoryPage.isTicTacTabDisplayed(), "TicTac tab is displayed.");
		
		/* Scroll down on Directory page */
		peopleSearchDirectoryPage.scrollDownPeopleList();
		
		/* Get the total number of experts from the counter */
		int counter = peopleSearchDirectoryPage.getCounter();
		
		/* Get all expert members displayed */
		int total_members = peopleSearchDirectoryPage.getTotalExperts();
		
		/* Check the members on the counter match the number of members listed */
		Assert.assertEquals(counter , total_members , "Number of experts on the list don't match the counter.");
		
	}
	
	@Test(groups={"FullRegression"}, description="Verifies the users displayed on Directory page are all experts on the selected interests")
	public void PeopleSearchInterests()
	{
		
		/* Hover of People Search in the left navigation and click Directory */
		Navigation navigation = new Navigation(getDriver());	
		navigation.hoverOverPeopleSearch();		
		Directory peopleSearchDirectoryPage = navigation.clickPeopleSearchDirectory();
		
		/* Get the value on the people search counter */
		Assert.assertFalse(peopleSearchDirectoryPage.isTicTacTabDisplayed(), "TicTac tab is displayed.");
		int initial_counter = peopleSearchDirectoryPage.getCounter();
		
		/* Select interest */
		peopleSearchDirectoryPage.clickNthInterestInLeftColumns(1);
		String selected_interest1 = peopleSearchDirectoryPage.getNthInterestInLeftColumns(1);
		
		List<String> interests = new ArrayList<String>();
		interests.add(selected_interest1);
		
		/* Check there are experts on the selected interest */
		if (peopleSearchDirectoryPage.getNoPeopleErrorMessage().equals(noPeopleErrorMessage)) Assert.fail("No experts registered on the system on the selected interest.");
		
		/* Scroll down on Directory page */
		peopleSearchDirectoryPage.scrollDownPeopleList();
				
		/* Expand members */
		peopleSearchDirectoryPage.scrollUp();
		peopleSearchDirectoryPage.clickExpandAll();
		
		/* Check only the expert members on the interest selected are displayed */
		Assert.assertTrue(peopleSearchDirectoryPage.isInterestDisplayedUnderExpertiseArea(interests) , "Some members displayed on the list are not experts on " + selected_interest1);
		
		/* Check tictac tab is added to the Directory page */	
		Assert.assertTrue(peopleSearchDirectoryPage.isTicTacTabDisplayed(), "TicTac tab is not displayed.");
		Assert.assertTrue((peopleSearchDirectoryPage.getTicTacListDisplayed()).contains(selected_interest1), "TicTac not displayed");
		
		/* Close tictac */
		peopleSearchDirectoryPage.scrollUp();
		
		peopleSearchDirectoryPage.clickNthTicTacCloseButton(1);
		interests.remove(selected_interest1);
		Assert.assertFalse((peopleSearchDirectoryPage.getTicTacListDisplayed()).contains(selected_interest1), "TicTac still displayed");
		Assert.assertEquals(initial_counter , peopleSearchDirectoryPage.getCounter() , "Number of members displayed don't match the initial value.");
		
		/* Select more than one interest */
		peopleSearchDirectoryPage.clickNthInterestInLeftColumns(1);
		selected_interest1 = peopleSearchDirectoryPage.getNthInterestInLeftColumns(1);
		Assert.assertTrue((peopleSearchDirectoryPage.getTicTacListDisplayed()).contains(selected_interest1), "TicTac not displayed");
		interests.add(selected_interest1);
		
		int last_interest = peopleSearchDirectoryPage.getTotalInterestsInLeftColumns();
		peopleSearchDirectoryPage.clickNthInterestInLeftColumns(last_interest);
		String selected_interest2 = peopleSearchDirectoryPage.getNthInterestInLeftColumns(last_interest);
		Assert.assertTrue((peopleSearchDirectoryPage.getTicTacListDisplayed()).contains(selected_interest2), "TicTac not displayed");
		interests.add(selected_interest2);
	
		/* Scroll down on Directory page */
		peopleSearchDirectoryPage.scrollDownPeopleList();
		
		/* Expand members */
		peopleSearchDirectoryPage.scrollUp();
		peopleSearchDirectoryPage.clickExpandAll();
		
		/* Check only the expert members on the interests selected are displayed on the list */
		Assert.assertTrue(peopleSearchDirectoryPage.isInterestDisplayedUnderExpertiseArea(interests) , "Members displayed on the list are not experts on one of the selected interests");
		
		/* Untick all interest checkboxes */
		peopleSearchDirectoryPage.scrollUp();
		
		peopleSearchDirectoryPage.clickNthInterestInLeftColumns(1);
		interests.remove(selected_interest1);
		Assert.assertFalse((peopleSearchDirectoryPage.getTicTacListDisplayed()).contains(selected_interest1), "TicTac still displayed");
		
		peopleSearchDirectoryPage.clickNthInterestInLeftColumns(last_interest);
		interests.remove(selected_interest2);
		Assert.assertFalse((peopleSearchDirectoryPage.getTicTacListDisplayed()).contains(selected_interest2), "TicTac still displayed");
		
		/* Check the counter is reset to the initial value */
		Assert.assertEquals(initial_counter , peopleSearchDirectoryPage.getCounter() , "Number of members displayed don't match the initial value.");

	}

}