package tests.webUI.nonFunctional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.ConfigProperties;
import webUI.Global;
import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.admin.Profile;
import webUI.home.library.Library;
import webUI.home.library.ListView;
import webUI.home.library.TileView;
import webUI.home.library.mediaTypes.Calculator;
import webUI.home.library.mediaTypes.Comments;
import webUI.home.library.mediaTypes.MediaType;
import webUI.home.library.mediaTypes.Simulator;
import webUI.home.library.mediaTypes.Summary;
import webUI.home.library.mediaTypes.Video;
import webUI.home.library.mediaTypes.VideoComments;

public class LibraryTests extends LoadBrowser {
	
	/* Read in the configuration file */
	 ConfigProperties config = new ConfigProperties();
	
	/* Page Headers */
	private String libraryPageHeader = new LocaleStrings().getString("LIBRARY_PAGE_HEADER");
	private String calculatorContentName = new LocaleStrings().getString("CALCULATOR_CONTENT_NAME");
	//private String simulatorContentName = new LocaleStrings().getString("SIMULATOR_CONTENT_NAME");
	private String summaryContentName = new LocaleStrings().getString("SUMMARY_CONTENT_NAME");
	private String videoContentName = new LocaleStrings().getString("VIDEO_CONTENT_NAME");
	private String watchButttonText = new LocaleStrings().getString("WATCH_BUTTON");
	private String openButtonText = new LocaleStrings().getString("OPEN_BUTTON");
	private String viewButtonText = new LocaleStrings().getString("VIEW_BUTTON");
	private String downloadButtonText = new LocaleStrings().getString("DOWNLOAD_BUTTON");
	
	String username = config.getConfigProperties().getProperty("DOCTOR_USERNAME");
	
	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
	public void beforeMethod()
    {
		getDriverAndLogin();
    }	
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Library page loads successfully")
	public void LibraryPageOpens()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");
		
		/* Check that results appear in the Library */
		int countOfLibraryResults = libraryPage.getCountOfResultsInTileView();
		if (countOfLibraryResults==0)
			Assert.fail("No results found in the Library");
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies the Clear Search clears the search field on the Library page")
	public void ClearSearchLinkFunctionOnTheLibraryPage()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		/* Enter a search term */
		libraryPage.enterSearchTerm("video");
		
		/* Click Search */
		libraryPage.clickSearch();
		
		/* Click Clear Search */
		libraryPage.clickClearSearch();
		
		/* Confirm the field is cleared */
		Assert.assertEquals(libraryPage.getSearchFieldText(), "","Clear Search did not clear the search input field on the Library page");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies the Library results can be displayed in List View")
	public void LibraryListViewDisplays()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		libraryPage.clickListView();
		Assert.assertTrue(libraryPage.isListViewDisplayed(),"List View failed to load");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies the Library results can be displayed in Tile View")
	public void TileView()
	{
		//Navigate to Library
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		//Wait for page to load
		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		//Loop iterator setup
		int tilesCounter = 12;
		if(libraryPage.getPagingNumber()<12)
			tilesCounter = libraryPage.getPagingNumber();

		//Load tile view page
		TileView tileView = new TileView(getDriver());
		
		//loop over first 12 tiles or less if there is less than 12 contents in library
		for(int i=1;i<=tilesCounter;i++){
			
			/* Verify correct UI elements appear */
			Assert.assertTrue(tileView.isSwitchToBackDisplayed(i),"Switch To Back button failed to load on panel " + i);
			Assert.assertTrue(tileView.isIconDisplayenOnNthTile(i),"Icon failed to load on panel" + i);
			Assert.assertTrue(tileView.isTitleDisplayenOnNthTile(i),"Title failed to load on panel" + i);
			
			
			//Verify that correct buttons are displayed for Calculator
			if(tileView.getIconTypeForNthTile(i).equals("typeIconCalculator")){
				Assert.assertEquals(tileView.getFirstButtonTextOnNthTile(i),openButtonText,"Text on first button panel "+i+" not displayed as expected");
				Assert.assertFalse(tileView.isSecondButtonDisplayedOnNthPanel(i),"Second button displayed");
			}
			//Verify that correct buttons are displayed for Simulator
			else if(tileView.getIconTypeForNthTile(i).equals("typeIconSimulator")){ 
				Assert.assertEquals(tileView.getFirstButtonTextOnNthTile(i),openButtonText,"Text on first button panel "+i+" not displayed as expected");
				Assert.assertFalse(tileView.isSecondButtonDisplayedOnNthPanel(i),"Second button displayed");
			}
			//Verify that correct buttons are displayed for Summary
			else if(tileView.getIconTypeForNthTile(i).equals("typeIconSummary")){ 
				Assert.assertEquals(tileView.getFirstButtonTextOnNthTile(i),viewButtonText,"Text on first button panel "+i+" not displayed as expected");
				Assert.assertEquals(tileView.getSecondButtonTextOnNthTile(i),downloadButtonText,"Text on second button panel "+i+" not displayed as expected");
			}
			//Verify that correct buttons are displayed for Video
			else if(tileView.getIconTypeForNthTile(i).equals("typeIconVideo")){ 
				Assert.assertEquals(tileView.getFirstButtonTextOnNthTile(i),watchButttonText,"Text on first button panel "+i+" not displayed as expected");
				Assert.assertFalse(tileView.isSecondButtonDisplayedOnNthPanel(i),"Second button displayed");
			}
			else 
				Assert.fail("Unsupported image displayed");
			
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies the switch to back button functions on the Tile View")
	public void ResultsSwitchToBackButtonFunctions()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		Assert.assertTrue(libraryPage.isTileViewDisplayed(),"Tile View is not displayed");
		TileView tileView = new TileView(getDriver());		
		
		/* Get the count of summary media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No media found in the Library");
		else
		{	
				/* Results panel being tested */
			Integer panel = 1;
			
			/* Verify correct UI elements appear */
			Assert.assertTrue(tileView.isSwitchToBackDisplayed(panel),"Switch To Back button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isSwitchToFrontDisplayed(panel),"Switch To Front button loaded in error on panel " + panel);
			Assert.assertTrue(tileView.isWatchDisplayed(panel),"Watch button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isBookmarkDisplayed(panel),"Bookmark link loaded in error on panel" + panel);
			Assert.assertFalse(tileView.isLikesDisplayed(panel),"Likes count loaded in error on panel" + panel);
			Assert.assertFalse(tileView.isCommentsDisplayed(panel),"Comments count loaded in error on panel" + panel);
			
			/* Click Switch To Back */
			tileView.clickNthSwitchToBack(panel);
			
			/* Verify correct UI elements appear */
			Assert.assertTrue(tileView.isSwitchToFrontDisplayed(panel),"Switch To Front button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isSwitchToBackDisplayed(panel),"Switch To Back button loaded in error on panel " + panel);
			Assert.assertFalse(tileView.isWatchDisplayed(panel),"Watch button loaded in error on panel " + panel);
			Assert.assertTrue(tileView.isBookmarkDisplayed(panel),"Bookmark link failed to load on panel" + panel);
			Assert.assertTrue(tileView.isLikesDisplayed(panel),"Likes count failed to load on panel" + panel);
			Assert.assertTrue(tileView.isCommentsDisplayed(panel),"Comments count failed to load on panel" + panel);
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "ResultsSwitchToBackButtonFunctions" }, description="Verifies the switch to front button functions on the Tile View")
	public void ResultsSwitchToFrontButtonFunctions()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		Assert.assertTrue(libraryPage.isTileViewDisplayed(),"Tile View is not displayed");
		TileView tileView = new TileView(getDriver());		
		
		/* Get the count of summary media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No media found in the Library");
		else
			{			
			/* Results panel being tested */
			Integer panel = 1;
			
			/* Verify correct UI elements appear */
			Assert.assertTrue(tileView.isSwitchToBackDisplayed(panel),"Switch To Back button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isSwitchToFrontDisplayed(panel),"Switch To Front button loaded in error on panel " + panel);
			Assert.assertTrue(tileView.isWatchDisplayed(panel),"Watch button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isBookmarkDisplayed(panel),"Bookmark link loaded in error on panel" + panel);
			Assert.assertFalse(tileView.isLikesDisplayed(panel),"Likes count loaded in error on panel" + panel);
			Assert.assertFalse(tileView.isCommentsDisplayed(panel),"Comments count loaded in error on panel" + panel);
			
			/* Click Switch To Back */
			tileView.clickNthSwitchToBack(panel);
			
			/* Verify correct UI elements appear */
			Assert.assertTrue(tileView.isSwitchToFrontDisplayed(panel),"Switch To Front button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isSwitchToBackDisplayed(panel),"Switch To Back button loaded in error on panel " + panel);
			Assert.assertFalse(tileView.isWatchDisplayed(panel),"Watch button loaded in error on panel " + panel);
			Assert.assertTrue(tileView.isBookmarkDisplayed(panel),"Bookmark link failed to load on panel" + panel);
			Assert.assertTrue(tileView.isLikesDisplayed(panel),"Likes count failed to load on panel" + panel);
			Assert.assertTrue(tileView.isCommentsDisplayed(panel),"Comments count failed to load on panel" + panel);
			
			/* Click Switch To Front */
			tileView.clickNthSwitchToFront(panel);
			
			/* Verify correct UI elements appear */
			Assert.assertTrue(tileView.isSwitchToBackDisplayed(panel),"Switch To Back button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isSwitchToFrontDisplayed(panel),"Switch To Front button loaded in error on panel " + panel);
			Assert.assertTrue(tileView.isWatchDisplayed(panel),"Watch button failed to load on panel " + panel);
			Assert.assertFalse(tileView.isBookmarkDisplayed(panel),"Bookmark link loaded in error on panel" + panel);
			Assert.assertFalse(tileView.isLikesDisplayed(panel),"Likes count loaded in error on panel" + panel);
			Assert.assertFalse(tileView.isCommentsDisplayed(panel),"Comments count loaded in error on panel" + panel);
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies the categories in subject list in the left hand column can be expanded/collapsed")
	public void CategoryListCanBeExpandedCollapsed()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		/* Verify each category is expanded */
		int countOfCategories = libraryPage.getCountOfCategories();
		for (int i = 1; i<=countOfCategories; i++)
		{			
			Assert.assertTrue(libraryPage.isNthCategoryExpanded(i),libraryPage.getNthCategoryInLeftColumns(i) + " Category is not expanded");		
		}
		
		/* Collapse each of the categories */
		for (int i = 1; i<=countOfCategories; i++)
		{			
			libraryPage.collapseNthCategory(i);		
		}
		
		/* Verify each category is not expanded */
		for (int i = 1; i<=countOfCategories; i++)
		{			
			Assert.assertFalse(libraryPage.isNthCategoryExpanded(i),libraryPage.getNthCategoryInLeftColumns(i) + " Category is expanded");		
		}
		
		/* Expand each of the categories */
		for (int i = 1; i<=countOfCategories; i++)
		{			
			libraryPage.expandNthCategory(i);	
		}
		
		/* Verify each category is expanded */
		for (int i = 1; i<=countOfCategories; i++)
		{			
			Assert.assertTrue(libraryPage.isNthCategoryExpanded(i),libraryPage.getNthCategoryInLeftColumns(i) + " Category is not expanded");		
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies the facet list at the top of the page is populated when a subject is chosen in the left column")
	public void FacetListIsPopulated()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		/* Select all Audience options */
		int countOfSubjects = 1;

		countOfSubjects = libraryPage.getCountOfSubjectsInNthCategory(1);
		for (int j=1; j<=countOfSubjects; j++)
		{
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(1, j);
		}

		
		/* Verify the list of subjects in the facet list is the same as those checked in the left column */
		Assert.assertEquals(libraryPage.getListOfSubjectsInFacetList(), libraryPage.getListOfSubjectsCheckedInLeftColumn(),"Selected subjects in left hand column do not equal those in the facet list at the top of the page");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "FacetListIsPopulated" }, description="Verifies the facet list at the top of the page is populated when a subject is chosen in the left column")
	public void SelectedSubjectsCanBeRemovedUsingFacetList()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		/* Select all Audience options */
		int countOfSubjects = 1;
		int totalSelected = 0;

		countOfSubjects = libraryPage.getCountOfSubjectsInNthCategory(1);
		for (int j=1; j<=countOfSubjects; j++)
		{
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(1, j);
			totalSelected+=1;
		}
		
		/* Click the last subject in the facet list */
		libraryPage.clickNthCategoryInFacetList(totalSelected);
		
		/* Verify the list of subjects in the facet list is the same as those checked in the left column */
		Assert.assertEquals(libraryPage.getListOfSubjectsInFacetList(), libraryPage.getListOfSubjectsCheckedInLeftColumn(),"Selected subjects in left hand column do not equal those in the facet list at the top of the page");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "FacetListIsPopulated" }, description="Verifies subjects deselected from the left hand column are also removed from the facet list")
	public void SelectedSubjectsCanBeRemovedUsingLeftColumn()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		/* Select all Audience options */
		int countOfSubjects = 1;
		//int totalSelected = 0;

		countOfSubjects = libraryPage.getCountOfSubjectsInNthCategory(1);
		for (int j=1; j<=countOfSubjects; j++)
		{
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(1, j);
			//totalSelected+=1;
		}
		
		/* Verify the list of subjects in the facet list is the same as those checked in the left column */
		Assert.assertEquals(libraryPage.getListOfSubjectsInFacetList(), libraryPage.getListOfSubjectsCheckedInLeftColumn(),"Selected subjects in left hand column do not equal those in the facet list at the top of the page");
	
		/* Remove the first subject in Audience category using the left column */
		libraryPage.clickMthSubjectInNthCategoryInLeftColumns(1, 1);

		
		/* Verify the list of subjects in the facet list is the same as those checked in the left column */
		Assert.assertEquals(libraryPage.getListOfSubjectsInFacetList(), libraryPage.getListOfSubjectsCheckedInLeftColumn(),"Selected subjects in left hand column do not equal those in the facet list at the top of the page");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryListViewDisplays" }, description="Twistie button functions in List View")
	public void TwistieButtonFunctionsInListView()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		libraryPage.waitForPageToLoad(getDriver());
		Assert.assertTrue(libraryPage.getHeader().contains(libraryPageHeader),"Incorrect header on the Library page");		
		
		/* Click List View */
		ListView listView = libraryPage.clickListView();
		
		/* Get the number of results */
		int countOfResults = listView.getCountOfResultsInTileView();
		
		/* Click the twistie of the last result to expand */
		if (countOfResults == 0)
			Assert.fail("No courses found in results");
		listView.clickTwistieExpand(countOfResults);
		
		/* Verify correct UI elements appear */
		Assert.assertTrue(listView.isRowDetailExpanded(countOfResults),"Row detail not expanded");
		Assert.assertTrue(listView.isWatchButtonDisplayed(countOfResults),"Watch button not displayed");
		Assert.assertTrue(listView.isBookmarksDisplayed(countOfResults),"Bookmarks not displayed");
		Assert.assertTrue(listView.isLikesDisplayed(countOfResults),"Likes text not displayed");
		Assert.assertTrue(listView.isDiscussionsDisplayed(countOfResults),"Discussions text not displayed");		
	
		/* Click twistie again to collapse the row */
		listView.clickTwistieCollapse(countOfResults);
		
		/* Verify UI elements no longer appear */
		Assert.assertFalse(listView.isRowDetailExpanded(countOfResults),"Row failed to collapse after clicking twistie");
		Assert.assertFalse(listView.isWatchButtonDisplayed(countOfResults),"open button failed to disappear after clicking twistie");
		Assert.assertFalse(listView.isBookmarksDisplayed(countOfResults),"Bookmarks count failed to disappear after clicking twistie");
		Assert.assertFalse(listView.isLikesDisplayed(countOfResults),"Likes count failed to disappear after clicking twistie");
		Assert.assertFalse(listView.isDiscussionsDisplayed(countOfResults),"Comments count failed to disappear after clicking twistie");		
	
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a video page loads from the Library")
	public void VideoPageLoads()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Video checkbox in the left hand column filter */
		libraryPage.selectVideoCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Video media types found in the Library");
		else
		{		
			/* Click the Open button for the first Video in the results */
			libraryPage.click1stButtonInNthPanel(1);
			
			/* Launch a video page */
			Video video = new Video(getDriver()) ;		
	
			/* Verify the video is present */
			Assert.assertTrue(video.isVideoPresent(),"Video failed to load");
			
			/* Verify new comment button appears */
			Assert.assertTrue(video.isNewCommentButtonDisplayed(),"New comment button failed to load");
			
			/* Verify Comments tab appears and is selected */
			Assert.assertTrue(video.isCommentsTabDisplayed(),"Comments tab failed to load on the video page");
			Assert.assertTrue(video.isCommentsTabSelected(),"Comments tab was not selected on the video page when it should have been");
			
			/* Verify Transcript tab appears and is not selected */
			Assert.assertTrue(video.isTranscriptTabDisplayed(),"Transcript tab failed to load on the video page");
			Assert.assertFalse(video.isTranscriptTabSelected(),"Transcript tab was selected in error on the video page when it should not have been");
	
			/* Verify duration of the video displays and is not empty */
			Assert.assertTrue(video.isVideoDurationDisplayed(),"Video duration text failed to display");
			
			Assert.assertTrue(video.isMediaTitleDisplayed(),"Video Title failed to display");
			Assert.assertTrue(video.isMediaBookMarksIconDisplayed(),"Video bookmarks icon failed to display");
			Assert.assertTrue(video.isMediaLikesIconDisplayed(),"Video Likes icon failed to display");
		}
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"},dependsOnMethods = { "VideoPageLoads" }, description="Verifies a video plays (that the elapsed times changes from 00:00)")
	public void VideoPlays() throws InterruptedException
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Video checkbox in the left hand column filter */
		libraryPage.selectVideoCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Video media types found in the Library");
		else
		{		
			/* Click the Open button for the first Video in the results */
			libraryPage.click1stButtonInNthPanel(1);

			Video video = new Video(getDriver());
			
			/* Verify play button does display and pause button doesn't */
			Assert.assertTrue(video.isPlayButtonDisplayed(),"Play button failed to load");
			Assert.assertFalse(video.isPauseButtonDisplayed(),"Pause button appeared in error");
			
			/* Check if the resume video dialog appears, if it does, click start over */
			if (video.isResumeInformationBoxDisplayed())
				video.clickResumeVideoStartOver();
			else
				video.clickPlay();
	
			/* Wait for 5 seconds for the video to start */ 
			Thread.sleep(5000);
			
			/* Verify the time elapsed is > 00:00 */
			Assert.assertNotEquals("00:00", video.getTimeElapsed(),"Video failed to play");
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "VideoPlays" }, description="Verifies a video can be paused")
	public void VideoPauses() throws InterruptedException
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Video checkbox in the left hand column filter */
		libraryPage.selectVideoCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Video media types found in the Library");
		else
		{		
			/* Click the Open button for the first Video in the results */
			libraryPage.click1stButtonInNthPanel(1);			

			Video video = new Video(getDriver());
			
			/* Check if the resume video dialog appears, if it does, click start over */
			if (video.isResumeInformationBoxDisplayed())
				video.clickResumeVideoStartOver();
			else
				video.clickPlay();
			
			/* Wait for 5 seconds for the video to start */ 
			Thread.sleep(5000);
	
			/* Click pause */
			video.clickPause();
			
			/* Get the elapsed time */
			String elapsedTime=video.getTimeElapsed();
			
			/* Wait for 2 seconds to ensure the video has paused */ 
			Thread.sleep(2000);
			
			/* Verify elapsed time is still equal to the elapsed time 2 seconds ago */
			Assert.assertEquals(video.getTimeElapsed(), elapsedTime,"Video failed to pause");
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "VideoPlays" }, description="Verifies the progress indicator functions when a video is playing")
	public void ProgressIndicatorFunctions() throws InterruptedException
	{
		Double progressBarAtStart = 0.0;
		Double progressBarWhenVideoPaused = 0.0;		
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Video checkbox in the left hand column filter */
		libraryPage.selectVideoCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Video media types found in the Library");
		else
		{		
			/* Click the Open button for the first Video in the results */
			libraryPage.click1stButtonInNthPanel(1);

			Video video = new Video(getDriver());			
			
			/* Verify the progress bar starts at 0 */
			Assert.assertEquals(video.getProgressBarLocation(), progressBarAtStart,"Progress bar was not at the start before the video started");
			
			/* Check if the resume video dialog appears, if it does, click start over */
			if (video.isResumeInformationBoxDisplayed())
				video.clickResumeVideoStartOver();
			else
				video.clickPlay();		
			
			/* Wait for 5 seconds for the video to start */ 
			Thread.sleep(5000);
			
			/* Click pause */
			video.clickPause();
			
			/* Wait for 2 seconds to ensure the video has paused */ 
			Thread.sleep(2000);
			
			/* Get the current value of the progress indicator */
			progressBarWhenVideoPaused = video.getProgressBarLocation();
			
			/* Verify the progress bars location is greater than 0 */
			Assert.assertTrue(progressBarWhenVideoPaused>progressBarAtStart,"Progress bar failed to move");
		}
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a simulator page loads from the Library")
	public void SimulatorPageLoads()
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Simulator checkbox in the left hand column filter */
		libraryPage.selectSimulationCheckbox();	
		
		/* Get the count of Simulator media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Summary media types found in the Library");
		else
		{	
			/* Click the Open button for the last Simulator in the results */
			libraryPage.click1stButtonInNthPanel(countOfResults);
			
			/* Launch a simulator page */
			Simulator simulator = new Simulator(getDriver()) ;		
			
//			Assert.assertTrue(simulator.isContentAuthorDisplayed(),"Simulator Author failed to display");
//			Assert.assertTrue(simulator.isContentDateDisplayed(),"Simulator Date failed to display");
			Assert.assertTrue(simulator.isMediaTitleDisplayed(),"Simulator Title failed to display");
			Assert.assertTrue(simulator.isMediaBookMarksIconDisplayed(),"Simulator Bookmarks icon failed to display");
			Assert.assertTrue(simulator.isMediaLikesIconDisplayed(),"Simulator Likes icon failed to display");
			Assert.assertTrue(simulator.isNewCommentButtonDisplayed(),"New Comment button failed to display");	
			Assert.assertTrue(simulator.isCommentsListDisplayed(),"Comments list failed to display");
			Assert.assertTrue(simulator.isSimulatorDisplayed(),"Simulator failed to display");
			//Assert.assertFalse(simulator.isLoadingMsgDisplayed(),"Loading message displayed");
		}
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" },description="Verifies a calculator page loads from the Library")
	/*"UAT-BVT" removed from groups because there is no calculators in UAT env*/
	public void CalculatorPageLoads() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Calculator checkbox in the left hand column filter */
		libraryPage.selectCalculatorCheckbox();
		
		/* Get the count of Calculator media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Calculator media types found in the Library");
		else
		{	
			/* Click the Open button for the last Calculator in the results */
			libraryPage.click1stButtonInNthPanel(countOfResults);
			
			/* Launch a Calculator page */
			Calculator calculatorPage = new Calculator(getDriver());
			
			Assert.assertTrue(calculatorPage.isCommentsListDisplayed(),"Comments list failed to display");
			Assert.assertTrue(calculatorPage.isMediaBookMarksIconDisplayed(),"Calculator Bookmarks icon failed to display");
			Assert.assertTrue(calculatorPage.isMediaLikesIconDisplayed(),"Calculator Likes icon failed to display");
			Assert.assertTrue(calculatorPage.isMediaTitleDisplayed(),"Calculator Title failed to display");
			Assert.assertTrue(calculatorPage.isNewCommentButtonDisplayed(),"New Comment button failed to display");
			Assert.assertTrue(calculatorPage.isCalculatorDisplayed(),"Calculator failed to display");
//			Assert.assertTrue(calculatorPage.isContentAuthorDisplayed(),"Calculator Author failed to display");
//			Assert.assertTrue(calculatorPage.isContentDateDisplayed(),"Calculator Date failed to display");
		}
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a summary page loads from the Library")
	public void SummaryPageLoads() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click the Summary checkbox in the left hand column filter */
		libraryPage.selectSummaryCheckbox();		
		
		/* Get the count of summary media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Summary media types found in the Library");
		else
		{		
			/* Click the Open button for the first Summary in the results */
			libraryPage.click1stButtonInNthPanel(1);
			
			/* Launch a Summary page */
			Summary summaryPage = new Summary(getDriver());
			
			Assert.assertTrue(summaryPage.isCommentsListDisplayed(),"New Comment button failed to display");
			Assert.assertTrue(summaryPage.isMediaBookMarksIconDisplayed(),"Summary Bookmarks icon failed to display");
			Assert.assertTrue(summaryPage.isMediaLikesIconDisplayed(),"Summary Likes icon failed to display");
			Assert.assertTrue(summaryPage.isMediaTitleDisplayed(),"Summary Title failed to display");
			Assert.assertTrue(summaryPage.isNewCommentButtonDisplayed(),"New Comment button failed to display");
			Assert.assertTrue(summaryPage.isSummaryDisplayed(),"Summary failed to display");
//			Assert.assertTrue(summaryPage.isContentAuthorDisplayed(),"Summary Author failed to display");
//			Assert.assertTrue(summaryPage.isContentDateDisplayed(),"Summary Date failed to display");
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a profile page loads from the Library Tile Front View")
	public void ProfilePageLoadsFromTileFrontView()  
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
				
		TileView tileView = new TileView(getDriver());

		/* Get count of authors in Library Page Tile View*/
		int countOfResults = tileView.getCountOfContentsWithAuthors();
	
		if (countOfResults==0)
			Assert.fail("No media with authors found in the Library");
		else
		{				
			/*Click first author name link*/
			tileView.clickFirstAuthorFront();
			
			/* Launch a Profiles page */
			Profile profilePage = new Profile(getDriver());
			
			
			/*Verify that profile photo loads*/
			Assert.assertTrue(profilePage.profilePhotoLoads(),"Profile photo not loaded on Profile Page");
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a author profile page loads from content page")
	public void LinkToAuthorProfileFromContentPage()  
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
				
		TileView tileView = new TileView(getDriver());

		/* Get count of authors in Library Page Tile View*/
		int countOfResults = tileView.getCountOfContentsWithAuthors();
	
		if (countOfResults==0)
			Assert.fail("No media with authors found in the Library");
		else
		{				
			//Get first panel with existing author 
			int panel = tileView.getPanelWithFirstExistingAuthor();
			
			//Open content page
			tileView.click1stButtonInNthPanel(panel);
			
			//Load media page and wait  for page to load
			MediaType mediaType = new MediaType(getDriver());
			mediaType.waitForPageToLoad(getDriver());
			
			//Click content author link
			mediaType.clickContentAuthorLink();
			
			/* Launch a Profiles page */
			Profile profilePage = new Profile(getDriver());
			
			/*Verify that profile photo loads*/
			Assert.assertTrue(profilePage.profilePhotoLoads(),"Profile photo not loaded on Profile Page");
		}
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a comment author profile page loads")
	public void LinkToCommentAuthorProfile()  
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
				
		Library library = new Library(getDriver());
		
		//Simulator in filters
		library.selectSimulationCheckbox();
		
		//select simulator in filters
		library.click1stButtonInNthPanel(1);
		
		//Load simulator page and Comments panel
		Simulator simulator = new Simulator(getDriver());
		Comments comments = new Comments(getDriver());
		
		//Create new comment
		Date d = new Date();
		String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		comments.createNewComment("comment", false, commentText);
		
		//Get new comment position
		int commentPos = comments.getCommentPosition(commentText);
		
		//click comment author name
		comments.clickNthCommentAuthor(commentPos);
		
		/* Launch a Profiles page */
		Profile profilePage = new Profile(getDriver());
		
		/*Verify that profile photo loads*/
		Assert.assertTrue(profilePage.profilePhotoLoads(),"Profile photo not loaded on Profile Page");
		
		//Delete test comment
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
		library.waitForPageToLoad(getDriver());
		library.selectSimulationCheckbox();
		library.click1stButtonInNthPanel(1);
		simulator.waitForPageToLoad(getDriver());
		commentPos = comments.getCommentPosition(commentText);
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a reply author profile page loads")
	public void LinkToReplyAuthorProfile()  
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
				
		Library library = new Library(getDriver());
		
		//Simulator in filters
		library.selectSimulationCheckbox();
		
		//select simulator in filters
		library.click1stButtonInNthPanel(1);
		
		//Load simulator page and Comments panel
		Simulator simulator = new Simulator(getDriver());
		Comments comments = new Comments(getDriver());
		
		//Create new comment
		Date d = new Date();
		String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		String replyText = "Reply_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		comments.createNewComment("comment", false, commentText);
		
		//Get new comment position
		int commentPos = comments.getCommentPosition(commentText);
		
		//Create new reply for above comment
		comments.createNewReplyforNthComment(replyText, commentPos);
		
		//click reply author name
		commentPos = comments.getCommentPosition(commentText);
		comments.clickUsernameForMthReplyInNthComment(commentPos,1);
		
		/* Launch a Profiles page */
		Profile profilePage = new Profile(getDriver());
		
		/*Verify that profile photo loads*/
		Assert.assertTrue(profilePage.profilePhotoLoads(),"Profile photo not loaded on Profile Page");
		
		//Delete test comment
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
		library.waitForPageToLoad(getDriver());
		library.selectSimulationCheckbox();
		library.click1stButtonInNthPanel(1);
		simulator.waitForPageToLoad(getDriver());
		commentPos = comments.getCommentPosition(commentText);
		comments.clickDeleteLinkForMthReplyInNthComment(commentPos, 1);
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies a author profile page loads from library list view")
	public void LinkToAuthorProfileFromListView()  
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
				
		TileView tileView = new TileView(getDriver());

		/* Get count of authors in Library Page Tile View*/
		int countOfResults = tileView.getCountOfContentsWithAuthors();
	
		if (countOfResults==0)
			Assert.fail("No media with authors found in the Library");
		else
		{				
			//Get first panel with existing author 
			int panel = tileView.getPanelWithFirstExistingAuthor();
			
			//Open list view
			tileView.clickListView();
			
			//Load list view page
			ListView listView = new ListView(getDriver());
						
			//Click content author link
			listView.clickNthCourseAuthorName(panel);
			
			/* Launch a Profiles page */
			Profile profilePage = new Profile(getDriver());
			
			/*Verify that profile photo loads*/
			Assert.assertTrue(profilePage.profilePhotoLoads(),"Profile photo not loaded on Profile Page");
		}
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies push scrolling works in Tile View")
	public void PushScrollingTileView()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/*Scroll to the end of the library*/
		libraryPage.scrollEndOfResults();
		
		/*Go to top of the page*/
		libraryPage.goToTopOfThePage();
		
		/*Get items counter displayed at the the of the page*/
		int itemsCounter = libraryPage.getPagingNumber();
		
		/*Get items number displayed*/
		int itemsDisplayed = libraryPage.getCountOfResultsInTileView();
		
		/*Check that total counter match total items displayed*/
		Assert.assertEquals(itemsDisplayed, itemsCounter, "After scrolling to the end of library total number of items does not equal total items displayed at the top of the page");
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies push scrolling works in Row View")
	public void PushScrollingRowView()
	{	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/*Click row view button*/
		libraryPage.clickListView();
	
		/*Scroll to the end of the library*/
		libraryPage.scrollEndOfResults();
		
		/*Go to top of the page*/
		libraryPage.goToTopOfThePage();
		
		/*Get items counter displayed at the the of the page*/
		int itemsCounter = libraryPage.getPagingNumber();
		
		/*Get items number displayed*/
		int itemsDisplayed = libraryPage.getCountOfResultsInListView();
		
		/*Check that total counter match total items displayed*/
		Assert.assertEquals(itemsDisplayed, itemsCounter, "After scrolling to the end of library total numebr of items does not equal total items displayed at the top of the page");
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to filter by type")
	public void SearchByType() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		/* Get count of results in Library*/
		int countOfALLResults = libraryPage.getPagingNumber();
		int typeNumber = 4;
		
		if (countOfALLResults==0)
			Assert.fail("No media found in the Library");
		else
		{				
			/*Click Calculator in Filters*/
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(typeNumber,3);
			String selectedSubject = libraryPage.getMthSubjectInNthCategoryInLeftColumns(typeNumber, 3);
			
			/*Scroll to display all results*/
			libraryPage.scrollEndOfResults();
			
			/*Go to top of the page*/
			libraryPage.goToTopOfThePage();
			
			/*Get count of Calculators*/
			int countOfResutls = libraryPage.getPagingNumber();
			
			
			/*Check that Filter return some calculators*/
			if (countOfResutls==0)
				Assert.fail("No Calculators found");
			else
			{
				for (int i=1; i<=countOfResutls; i++)
				{
					String typeTile = libraryPage.getLibraryResultType(i);
					/*Check that Content icon displayed is consistent with selected filter*/
					Assert.assertEquals(typeTile, selectedSubject,"Content icon displayed do not match type selected in filters");
				}
			}
			
			ListView listView = new ListView(getDriver());
			
			/*Click row view button*/
			libraryPage.clickListView();

			/*Scroll to display all results*/
			libraryPage.scrollEndOfResults();
			
			/*Go to top of the page*/
			libraryPage.goToTopOfThePage();
			
			for (int j=1; j<=countOfResutls;j++)
			{
				String typeList = listView.getNthCourseIcon(j);
				/*Check that Content icon displayed is consistent with selected filter*/
				Assert.assertEquals(typeList, selectedSubject,"Content icon displayed do not match type selected in filters");
			}
		}
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies filter for Subjects")
	public void FilterBySubject() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click list view button */
		libraryPage.clickListView();
		
		/* Get total number of contents in Library */
		int totalItems = libraryPage.getPagingNumber();
		int totalCounter = 0;
		int subjectNumber = 3;
		
		/* Get count of subjects*/
		int subjectCounter = libraryPage.getCountOfSubjectsInNthCategory(subjectNumber);
		
		/* Loop over subjects*/
		for (int i=1; i<=subjectCounter; i++)
		{	
			/* Select nth subject*/
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(subjectNumber,i);
			
			/* Get nth subject name*/
			String currentSubject = libraryPage.getMthSubjectInNthCategoryInLeftColumns(subjectNumber,i);
			
			/* Display all filtered results*/
			libraryPage.scrollEndOfResults();
			
			/*Back to top of the page*/
			libraryPage.goToTopOfThePage();
			
			/* Get number of items within subject */
			int itemsInSubjectCounter = libraryPage.getPagingNumber();
			
			/* Loop over items within subject*/
			for (int j=1; j<=itemsInSubjectCounter; j++)
			{
				ListView listView = new ListView(getDriver());
				/* Get current item subject*/
				String currentItemSubject = listView.getNthCourseSubjectArea(j);
				/* Check current item subject with subject in filters */
				Assert.assertEquals(currentItemSubject, currentSubject, "Current selected filter: "+currentSubject+" does not match subject in results: "+currentItemSubject+".");
			}
			
			/* De-select nth subject */
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(subjectNumber,i);
			
			/* Increase counter*/
			totalCounter = totalCounter + itemsInSubjectCounter;
		}
		
		/* Check that sum of items over subjects match total number of items in library */
		Assert.assertEquals(totalCounter, totalItems,"Sum of items per subject does not equal total counter");
		
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies multiple filter")
	public void MultipleFilter() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Click list view button */
		libraryPage.clickListView();
		
		int subject = 1;
		
		/* Select first subject */
		libraryPage.clickMthSubjectInNthCategoryInLeftColumns(3,subject);
		String selectedSubject = libraryPage.getMthSubjectInNthCategoryInLeftColumns(3, subject);
		/* Select summary type */
		libraryPage.selectSummaryCheckbox();
		String selectedType1 = summaryContentName;
		/* Select video type */
		libraryPage.selectVideoCheckbox();
		String selectedType2 = videoContentName;
		
		/* Display all filtered results*/
		libraryPage.scrollEndOfResults();
		
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		
		/* Get number of filtered items */
		int itemsCounter = libraryPage.getPagingNumber();
		
		/*Loop over items*/
		for (int i=1; i<=itemsCounter; i++)
		{	
			ListView listView = new ListView(getDriver());
			/* Get current item subject*/
			String currentSubject = listView.getNthCourseSubjectArea(i);
			
			/* Get current item type*/
			String currentType = listView.getNthCourseType(i);
			
			/*Check that current subject match filter selected*/
			Assert.assertEquals(currentSubject,selectedSubject);
			
			/* Check that current type matches one of selected in filters*/	
			if (!(currentType.equals(selectedType1)) && !(currentType.equals(selectedType2)))
				Assert.fail("Current type does not match any type selected in the filter. Current type: '"+currentType+"' Expected type: '"+selectedType1+"' or '"+selectedType2+"'");
		}
	}	
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies default sorting in tile view")
	public void DefaultSortTileView() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		
		TileView tileView = new TileView(getDriver());
		
		/*Get list of all titles*/
		List<String> contentTitles= tileView.getListOfContentTitles();
		/*Copy title list to new list for sorting*/
		List<String> sortedTitles = new ArrayList<String>(contentTitles);
		
		/*Sort list ascending */
		sortedTitles = libraryPage.sortList(sortedTitles, "asc");
		
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentTitles, sortedTitles,"Tiles are not sorted by title ascending on library page");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies sorting in row view")
	public void SortingRowView() throws ParseException 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		ListView listView = new ListView(getDriver());
		/*Click row view button */
		libraryPage.clickListView();
		
	/*----------------Default sorting test - title ascending-------------------*/
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentTitles= listView.getListOfContentTitles();
		/*Copy title list to new list for sorting*/
		List<String> sortedTitles = new ArrayList<String>(contentTitles);
		/*Sort list ascending */
		sortedTitles = libraryPage.sortList(sortedTitles, "asc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isAscSortIconDisplayed(1),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentTitles, sortedTitles,"Rows are not sorted by title ascending on library page");
		
	/*----------------Sort by title descending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(1);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentTitlesDesc= listView.getListOfContentTitles();
		/*Copy title list to new list for sorting*/
		List<String> sortedTitlesDesc = new ArrayList<String>(contentTitlesDesc);
		/*Sort list ascending */
		sortedTitlesDesc = libraryPage.sortList(sortedTitlesDesc, "desc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isDescSortIconDisplayed(1),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentTitlesDesc, sortedTitlesDesc,"Rows are not sorted by title descending on library page");
		
	/*----------------Sort by type ascending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(2);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentTypes= listView.getListOfContentTypes();
		/*Copy title list to new list for sorting*/
		List<String> sortedTypes = new ArrayList<String>(contentTypes);
		/*Sort list ascending */
		sortedTypes = libraryPage.sortList(sortedTypes, "asc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isAscSortIconDisplayed(2),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentTypes, sortedTypes,"Rows are not sorted by type ascending on library page");
		
	/*----------------Sort by type descending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(2);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentTypesDesc= listView.getListOfContentTypes();
		/*Copy title list to new list for sorting*/
		List<String> sortedTypesDesc = new ArrayList<String>(contentTypesDesc);
		/*Sort list ascending */
		sortedTypesDesc = libraryPage.sortList(sortedTypesDesc, "desc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isDescSortIconDisplayed(2),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentTypesDesc, sortedTypesDesc,"Rows are not sorted by type descending on library page");
		
	/*----------------Sort by subject ascending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(3);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentSubjects= listView.getListOfContentSubjects();
		/*Copy title list to new list for sorting*/
		List<String> sortedSubjects = new ArrayList<String>(contentSubjects);
		/*Sort list ascending */
		sortedSubjects = libraryPage.sortList(sortedSubjects, "asc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isAscSortIconDisplayed(3),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentSubjects, sortedSubjects,"Rows are not sorted by subject ascending on library page");
		
	/*----------------Sort by subject descending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(3);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentSubjectsDesc= listView.getListOfContentSubjects();
		/*Copy title list to new list for sorting*/
		List<String> sortedSubjectsDesc = new ArrayList<String>(contentSubjectsDesc);
		/*Sort list ascending */
		sortedSubjectsDesc = libraryPage.sortList(sortedSubjectsDesc, "desc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isDescSortIconDisplayed(3),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentSubjectsDesc, sortedSubjectsDesc,"Rows are not subject by type descending on library page");
		
	/*----------------Sort by author name ascending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(4);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentAuthors= listView.getListOfContentAuthors();
		/*Copy title list to new list for sorting*/
		List<String> sortedAuthors = new ArrayList<String>(contentAuthors);
		/*Sort list ascending */
		sortedAuthors = libraryPage.sortList(sortedAuthors, "asc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isAscSortIconDisplayed(4),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentAuthors, sortedAuthors,"Rows are not sorted by author name ascending on library page");
		
	/*----------------Sort by author name descending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(4);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<String> contentAuthorsDesc= listView.getListOfContentAuthors();
		/*Copy title list to new list for sorting*/
		List<String> sortedAuthorsDesc = new ArrayList<String>(contentAuthorsDesc);
		/*Sort list ascending */
		sortedAuthorsDesc = libraryPage.sortList(sortedAuthorsDesc, "desc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isDescSortIconDisplayed(4),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentAuthorsDesc, sortedAuthorsDesc,"Rows are not author name by type descending on library page");
		
	/*----------------Sort by date added ascending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(5);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<Date> contentDates= listView.getListOfContentDatesAdded();
		/*Copy title list to new list for sorting*/
		List<Date> sortedDates = new ArrayList<Date>(contentDates);
		/*Sort list ascending */
		sortedDates = libraryPage.sortDateList(sortedDates, "asc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isAscSortIconDisplayed(5),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentDates, sortedDates,"Rows are not sorted by date added ascending on library page");
		
	/*----------------Sort by date added descending-------------------*/
		/* Click on title label*/
		listView.clickNthColumnLabel(5);
		/* Display all results*/
		libraryPage.scrollEndOfResults();
		/*Back to top of the page*/
		libraryPage.goToTopOfThePage();
		/*Get list of all titles*/
		List<Date> contentDatesDesc= listView.getListOfContentDatesAdded();
		/*Copy title list to new list for sorting*/
		List<Date> sortedDatesDesc = new ArrayList<Date>(contentDatesDesc);
		/*Sort list ascending */
		sortedDatesDesc = libraryPage.sortDateList(sortedDatesDesc, "desc");
		/*Check that correct sorting icon is displayed*/
		Assert.assertTrue(listView.isDescSortIconDisplayed(5),"Sorting icon not displayed");
		/*Check that there is no change before and after sorting*/
		Assert.assertEquals(contentDatesDesc, sortedDatesDesc,"Rows are not date added by type descending on library page");
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able search using search box mixed with filters")
	public void FilterPlusSearchBox() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		ListView listView = new ListView(getDriver());
		/*Click row view button */
		libraryPage.clickListView();
		
		/*Click calculator in the filters*/
		libraryPage.selectCalculatorCheckbox();
		
		/*Enter search term into search box*/
		String searchTerm = "fvt";
		libraryPage.enterSearchTerm(searchTerm);
		
		/*Click search icon*/
		libraryPage.clickSearchButton();
		
		/*Get lists of content titles, subjects and types*/
		List<String> contentTitles= listView.getListOfContentTitles();
		List<String> contentSubjects= listView.getListOfContentSubjects();
		List<String> contentTypes= listView.getListOfContentTypes();
		
		/*Get count of displayed elements*/
		int itemsCounter = libraryPage.getPagingNumber();
		
		/*Loop over displayed contents*/		
		for (int i=0; i<=itemsCounter-1; i++)
		{	
			/*Check that only Calculators are displayed*/
			Assert.assertEquals(contentTypes.get(i), calculatorContentName,"Type for current item does not match type selected in filters");
			
			/*Check that results contains search term in title or subject*/
			if (!(contentTitles.get(i).contains(searchTerm)) && !(contentSubjects.get(i).contains(searchTerm)))
				Assert.fail("Content ["+i+"] does not have search term ["+searchTerm+"] "
						+ "in title ["+contentTitles.get(i)+"[ or in subject ["+contentSubjects.get(i)+"]");
		}
		
		/*Click Tile View button*/
		libraryPage.clickTileView();
		TileView tileView = new TileView(getDriver());
		
		/*Get list of content titles*/
		List<String> contentTitlesTile = tileView.getListOfContentTitles();
		/*Check that filter is kept when switch to tile view*/
		Assert.assertEquals(contentTitlesTile, contentTitles,"Filter not kept when switch to tile view.");
	}	
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to create a comment on calculator")
	public void CreateCommentOnCalculator() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Read in the configuration file */
		ConfigProperties config = new ConfigProperties();
		String userEmail = config.getConfigProperties().getProperty("DOCTOR_USERNAME");
		
		/*get user name*/
		navigation.hoverOverAvatar();
		String currentUser = navigation.getLoggedInUsername();
		
		/* Select Calculator checkbox in the filters*/
		libraryPage.selectCalculatorCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Calculator media types found in the Library");
		else
		{		
			/* Click the Open button for the first Calculator in the results */
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch calculator page*/
			Calculator calculator = new Calculator(getDriver());
			
			/*Launch comments panel*/
			Comments comments = new Comments(getDriver());
			
			/* Click 'new comment' button */
			comments.clickNewCommentButton();
			
			/* Verify that new comment panel title is displayed*/
			Assert.assertTrue(comments.isNewCommentTitleDisplayed(),"New comment panel title is not displayed");
			/* Verify that comment input is displayed*/
			Assert.assertTrue(comments.isCommentBoxDisplayed(),"New comment input box is not displayed");
			/* Verify that remaining chars counter is displayed*/
			Assert.assertTrue(comments.isRemainingCharsCounterDisplayed(),"New comment remaining chars counter is not displayed");
			/* Verify that question indicator is displayed*/
			Assert.assertTrue(comments.isQuestionIndicatorDisplayed(),"New comment question indicator is not displayed");
			/* Verify that anonymous indicator is displayed*/
			Assert.assertTrue(comments.isAnonymousIndicatorDisplayed(),"New comment anonymous indicator is not displayed");
			/* Verify that new comment submit button is displayed*/
			Assert.assertTrue(comments.isNewCommentSubmitButtonDisplayed(),"New comment submit button is not displayed");
			/* Verify that new comment cancel button is displayed*/
			Assert.assertTrue(comments.isNewCommentCancelButtonDisplayed(),"New comment cancel button is not dispalyed");
			/* Verify that show tips link is displayed*/
			Assert.assertTrue(comments.isShowTipsLinkDisplayed(),"New comment show tips link is not displayed");
			
			Date d = new Date();
			String newComment = "fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime();
			/*Input test comment text into new comment box*/
			comments.enterTextIntoNewCommentBox(newComment);
			
			/*Click submit button*/
			comments.clickNewCommentSubmitButton();
			
			/*Search for comment position*/
			int currentComment = comments.getCommentPosition(newComment);
			
			/*Get comment author name created above*/
			String commentAuthor = comments.getNthCommentAuthor(currentComment);
			/*Get comment likes counter name created above*/
			int commentLikeCounter = comments.getLikesCounterForNthComment(currentComment);
			/*Get comment text created above*/
			String commentDisplayed = comments.getNthCommentText(currentComment);
			
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, userEmail),"Profile photo not loaded on test comment");
						
			/*Navigate to homepage*/
			navigation.hoverOverHome();
			navigation.clickHome();
			/*Navigate back to the same content*/
			navigation.hoverOverLibrary();
			navigation.clickLibrary();
			libraryPage.selectCalculatorCheckbox();
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch Calculator page*/
			calculator.waitForPageToLoad(getDriver());
			
			/*Search for test comment*/
			currentComment = comments.getCommentPosition(newComment);
			
			/*Repeat all comments checks after refresh*/
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, userEmail),"Profile photo not loaded on test comment");
			
			/*Clear data Delete test comment*/
			comments.clickDeleteLinkOnNthComment(currentComment);	
		}			
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to create a comment on simulator")
	public void CreateCommentOnSimulator() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		/* Read in the configuration file */
		ConfigProperties config = new ConfigProperties();
		String userEmail = config.getConfigProperties().getProperty("DOCTOR_USERNAME");
		
		/*get user name*/
		navigation.hoverOverAvatar();
		String currentUser = navigation.getLoggedInUsername();
		
		/* Select Simulator checkbox in the filters*/
		libraryPage.selectSimulationCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Simulator media types found in the Library");
		else
		{		
			/* Click the Open button for the first Calculator in the results */
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch simulator to load*/
			Simulator simulator = new Simulator(getDriver());
			
			/*Launch comments panel*/		
			Comments comments = new Comments(getDriver());
			
			/* Click 'new comment' button */
			comments.clickNewCommentButton();
			
			/* Verify that new comment panel title is displayed*/
			Assert.assertTrue(comments.isNewCommentTitleDisplayed(),"New comment panel title is not displayed");
			/* Verify that comment input is displayed*/
			Assert.assertTrue(comments.isCommentBoxDisplayed(),"New comment input box is not displayed");
			/* Verify that remaining chars counter is displayed*/
			Assert.assertTrue(comments.isRemainingCharsCounterDisplayed(),"New comment remaining chars counter is not displayed");
			/* Verify that question indicator is displayed*/
			Assert.assertTrue(comments.isQuestionIndicatorDisplayed(),"New comment question indicator is not displayed");
			/* Verify that anonymous indicator is displayed*/
			Assert.assertTrue(comments.isAnonymousIndicatorDisplayed(),"New comment anonymous indicator is not displayed");
			/* Verify that new comment submit button is displayed*/
			Assert.assertTrue(comments.isNewCommentSubmitButtonDisplayed(),"New comment submit button is not displayed");
			/* Verify that new comment cancel button is displayed*/
			Assert.assertTrue(comments.isNewCommentCancelButtonDisplayed(),"New comment cancel button is not dispalyed");
			/* Verify that show tips link is displayed*/
			Assert.assertTrue(comments.isShowTipsLinkDisplayed(),"New comment show tips link is not displayed");
			
			Date d = new Date();
			String newComment = "fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime();
			/*Input test comment text into new comment box*/
			comments.enterTextIntoNewCommentBox(newComment);
						
			/*Click submit button*/
			comments.clickNewCommentSubmitButton();
			
			/*Search for test comment*/
			int currentComment = comments.getCommentPosition(newComment);
			
			/*Get comment author name created above*/
			String commentAuthor = comments.getNthCommentAuthor(currentComment);
			/*Get comment likes counter name created above*/
			int commentLikeCounter = comments.getLikesCounterForNthComment(currentComment);
			/*Get comment text created above*/
			String commentDisplayed = comments.getNthCommentText(currentComment);
			
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, userEmail),"Profile photo not loaded on test comment");
						
			/*Navigate to homepage*/
			navigation.hoverOverHome();
			navigation.clickHome();
			/*Navigate back to the same content*/
			navigation.hoverOverLibrary();
			navigation.clickLibrary();
			libraryPage.selectSimulationCheckbox();
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch simulator page*/
			simulator.waitForPageToLoad(getDriver());
			
			/*Search for test comment*/
			currentComment = comments.getCommentPosition(newComment);
			
			/*Repeat all comments checks after refresh*/
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, userEmail),"Profile photo not loaded on test comment");
			
			/*Delete test comment*/
			comments.clickDeleteLinkOnNthComment(currentComment);
		}			
	}
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to create a comment on summary")
	public void CreateCommentOnSummary() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		/* Read in the configuration file */
		ConfigProperties config = new ConfigProperties();
		String userEmail = config.getConfigProperties().getProperty("DOCTOR_USERNAME");
		
		/*get user name*/
		navigation.hoverOverAvatar();
		String currentUser = navigation.getLoggedInUsername();
		
		/* Select Summary checkbox in the filters*/
		libraryPage.selectSummaryCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Summary media types found in the Library");
		else
		{		
			/* Click the Open button for the first Calculator in the results */
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch summary page*/
			Summary summary = new Summary(getDriver());
			
			/*Launch comments panel*/
			Comments comments = new Comments(getDriver());
			
			/* Click 'new comment' button */
			comments.clickNewCommentButton();
			
			/* Verify that new comment panel title is displayed*/
			Assert.assertTrue(comments.isNewCommentTitleDisplayed(),"New comment panel title is not displayed");
			/* Verify that comment input is displayed*/
			Assert.assertTrue(comments.isCommentBoxDisplayed(),"New comment input box is not displayed");
			/* Verify that remaining chars counter is displayed*/
			Assert.assertTrue(comments.isRemainingCharsCounterDisplayed(),"New comment remaining chars counter is not displayed");
			/* Verify that question indicator is displayed*/
			Assert.assertTrue(comments.isQuestionIndicatorDisplayed(),"New comment question indicator is not displayed");
			/* Verify that anonymous indicator is displayed*/
			Assert.assertTrue(comments.isAnonymousIndicatorDisplayed(),"New comment anonymous indicator is not displayed");
			/* Verify that new comment submit button is displayed*/
			Assert.assertTrue(comments.isNewCommentSubmitButtonDisplayed(),"New comment submit button is not displayed");
			/* Verify that new comment cancel button is displayed*/
			Assert.assertTrue(comments.isNewCommentCancelButtonDisplayed(),"New comment cancel button is not dispalyed");
			/* Verify that show tips link is displayed*/
			Assert.assertTrue(comments.isShowTipsLinkDisplayed(),"New comment show tips link is not displayed");
			
			Date d = new Date();
			String newComment = "fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime();
			/*Input test comment text into new comment box*/
			comments.enterTextIntoNewCommentBox(newComment);
			
			/*Click submit button*/
			comments.clickNewCommentSubmitButton();
			
			/*Search for test comment*/
			int currentComment = comments.getCommentPosition(newComment);
			
			/*Get comment author name created above*/
			String commentAuthor = comments.getNthCommentAuthor(currentComment);
			/*Get comment likes counter name created above*/
			int commentLikeCounter = comments.getLikesCounterForNthComment(currentComment);
			/*Get comment text created above*/
			String commentDisplayed = comments.getNthCommentText(currentComment);
			
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, userEmail),"Profile photo not loaded on test comment");
						
			/*Navigate to homepage*/
			navigation.hoverOverHome();
			navigation.clickHome();
			/*Navigate back to the same content*/
			navigation.hoverOverLibrary();
			navigation.clickLibrary();
			libraryPage.selectSummaryCheckbox();
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch summary page*/
			summary.waitForPageToLoad(getDriver());
			
			/*Search for test comment*/
			currentComment = comments.getCommentPosition(newComment);
			
			/*Repeat all comments checks after refresh*/
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(comments.isBookmarkIconDisplayedForNthComment(currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(comments.isEditLinkDisplayedOnNthComment(currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(comments.isDeleteLinkDisplayedOnNthComment(currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(comments.isAddReplyLinkDisplayedOnNthComment(currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(comments.isProfilePhotoLoadedOnNthComment(currentComment, userEmail),"Profile photo not loaded on test comment");
			
			/*Delete test comment*/
			comments.clickDeleteLinkOnNthComment(currentComment);
		}			
	}	
	@Test(groups={"BVT","FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to create a comment on video")
	public void CreateCommentOnVideo() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		/* Read in the configuration file */
		ConfigProperties config = new ConfigProperties();
		String userEmail = config.getConfigProperties().getProperty("DOCTOR_USERNAME");
		
		/*get user name*/
		navigation.hoverOverAvatar();
		String currentUser = navigation.getLoggedInUsername();
		
		/* Select Video checkbox in the filters*/
		libraryPage.selectVideoCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No Video media types found in the Library");
		else
		{		
			/* Click the Open button for the first Video in the results */
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch summary page*/
			Video video = new Video(getDriver());
			
			/*Launch comments panel*/
			Comments comments = new Comments(getDriver());
			VideoComments videoComments = new VideoComments(getDriver());
			
			/* Click 'new comment' button */
			comments.clickNewCommentButton();
			
			/* Verify that new comment panel title is displayed*/
			Assert.assertTrue(comments.isNewCommentTitleDisplayed(),"New comment panel title is not displayed");
			/* Verify that comment input is displayed*/
			Assert.assertTrue(comments.isCommentBoxDisplayed(),"New comment input box is not displayed");
			/* Verify that remaining chars counter is displayed*/
			Assert.assertTrue(comments.isRemainingCharsCounterDisplayed(),"New comment remaining chars counter is not displayed");
			/* Verify that question indicator is displayed*/
			Assert.assertTrue(comments.isQuestionIndicatorDisplayed(),"New comment question indicator is not displayed");
			/* Verify that anonymous indicator is displayed*/
			Assert.assertTrue(comments.isAnonymousIndicatorDisplayed(),"New comment anonymous indicator is not displayed");
			/* Verify that new comment submit button is displayed*/
			Assert.assertTrue(comments.isNewCommentSubmitButtonDisplayed(),"New comment submit button is not displayed");
			/* Verify that new comment cancel button is displayed*/
			Assert.assertTrue(comments.isNewCommentCancelButtonDisplayed(),"New comment cancel button is not dispalyed");
			/* Verify that show tips link is displayed*/
			Assert.assertTrue(comments.isShowTipsLinkDisplayed(),"New comment show tips link is not displayed");
			
			Date d = new Date();
			String newComment = "fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime();
			/*Input test comment text into new comment box*/
			comments.enterTextIntoNewCommentBox(newComment);
			
			/*Click submit button*/
			comments.clickNewCommentSubmitButton();
			
			int currentCluster = videoComments.getClusterForCommentText(newComment);;
			int currentComment = videoComments.getPosForCommentText(newComment);
			
			/*Get comment author name created above*/
			String commentAuthor = videoComments.getNthCommentAuthor(currentCluster,currentComment);
			/*Get comment likes counter name created above*/
			int commentLikeCounter = videoComments.getLikesCounterForNthComment(currentCluster,currentComment);
			/*Get comment text created above*/
			String commentDisplayed = videoComments.getNthCommentText(currentCluster,currentComment);
			
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(videoComments.isBookmarkIconDisplayedForNthComment(currentCluster,currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(videoComments.isEditLinkDisplayedOnNthComment(currentCluster,currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(videoComments.isDeleteLinkDisplayedOnNthComment(currentCluster,currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(videoComments.isAddReplyLinkDisplayedOnNthComment(currentCluster,currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(videoComments.isProfilePhotoLoadedOnNthComment(currentCluster,currentComment, userEmail),"Profile photo not loaded on test comment");
						
			/*Navigate to homepage*/
			navigation.hoverOverHome();
			navigation.clickHome();
			/*Navigate back to the same content*/
			navigation.hoverOverLibrary();
			navigation.clickLibrary();
			libraryPage.selectVideoCheckbox();
			libraryPage.click1stButtonInNthPanel(1);
			
			/*Launch summary page*/
			video.waitForPageToLoad(getDriver());
						
			/*Search for test comment*/
			currentCluster = videoComments.getClusterForCommentText(newComment);
			currentComment = videoComments.getPosForCommentText(newComment);
									
			/*Repeat all comments checks after refresh*/
			/*Verify comment author*/
			Assert.assertEquals(commentAuthor, currentUser,"Comment author does not match current user");
			/*Verify that likes counter exist and is equal zero*/
			Assert.assertEquals(commentLikeCounter, 0,"Like counter on new comment is not equal zero");
			/*Verify that bookmark icon exist*/
			Assert.assertTrue(videoComments.isBookmarkIconDisplayedForNthComment(currentCluster,currentComment),"Bookmark icon not displayed");
			/*Verify that comment text is as entered above*/
			Assert.assertEquals(commentDisplayed, newComment,"Comment text displayed does not match comment text entered.");
			/*Verify that Edit link is displayed*/
			Assert.assertTrue(videoComments.isEditLinkDisplayedOnNthComment(currentCluster,currentComment),"Edit link is not displayed");
			/*Verify that Delete link is displayed*/
			Assert.assertTrue(videoComments.isDeleteLinkDisplayedOnNthComment(currentCluster,currentComment),"Delete link is not displayed");
			/*Verify that add reply link is displayed*/
			Assert.assertTrue(videoComments.isAddReplyLinkDisplayedOnNthComment(currentCluster,currentComment),"Add reply is not displayed");
			/*Verify that profile photo appears*/
			Assert.assertTrue(videoComments.isProfilePhotoLoadedOnNthComment(currentCluster,currentComment, userEmail),"Profile photo not loaded on test comment");
						
			/*Delete test comment*/
			videoComments.clickDeleteLinkOnNthComment(currentCluster,currentComment);
		}			
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to clear search by executing 'blank' search")
	public void BlankSearch() 
	{
		
		/*Navigate to library*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();

		/*Scroll to the end*/
		libraryPage.scrollEndOfResults();
		libraryPage.goToTopOfThePage();
		
		/* Get the count of results and paging number*/
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		int pagingNumber = libraryPage.getPagingNumber();
		
		if (countOfResults==0)
			Assert.fail("No contents found in the Library");
		else
		{	
			/*Enter search term into search box*/
			String searchTerm = "fvt";
			libraryPage.enterSearchTerm(searchTerm);
			
			/* Click Search */
			libraryPage.clickSearch();
			
			/*Scroll to the end*/
			libraryPage.scrollEndOfResults();
			libraryPage.goToTopOfThePage();
			
			/* Get the count of results */
			int countOfResults2 = libraryPage.getCountOfResultsInTileView();
			int pagingNumber2 = libraryPage.getPagingNumber();
			
			/*Verify that results are updated after search*/
			Assert.assertTrue(countOfResults>countOfResults2,"Results not refreshed after blank search");
			Assert.assertTrue(pagingNumber>pagingNumber2,"Paging number not refreshed after search");
			
			/* Click Clear Search */
			libraryPage.clickClearSearch();
			
			/* Click Search */
			libraryPage.clickSearch();
			
			/*Scroll to the end*/
			libraryPage.scrollEndOfResults();
			libraryPage.goToTopOfThePage();
			
			/* Get the count of results */
			int countOfResults3 = libraryPage.getCountOfResultsInTileView();
			int pagingNumber3 = libraryPage.getPagingNumber();
			
			/*Verify that blank search shows all results*/
			Assert.assertTrue(countOfResults==countOfResults3,"All results not displayed after blank search");
			Assert.assertTrue(pagingNumber==pagingNumber3,"Paging number not refreshed after blank search");
			
		}	
	}
	@Test(groups={"FullRegression"},dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is able to cancel adding new comment")
	public void CancelNewComment() 
	{
		String newCommentText = "This comment will not be saved.";
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/*Select summary checkbox*/
		libraryPage.selectSummaryCheckbox();
		
		/* Get the count of Video media in the results */
		int countOfResults = libraryPage.getCountOfResultsInTileView();
		
		if (countOfResults==0)
			Assert.fail("No media found in the Library");
		else
		{		
			/* Click the Open button for the first Video in the results */
			libraryPage.click1stButtonInNthPanel(1);

			/*Launch comments panel*/
			Comments comments = new Comments(getDriver());
						
			/* Click 'new comment' button */
			comments.clickNewCommentButton();
			
			/*Enter some text into new comment box*/
			comments.enterTextIntoNewCommentBox(newCommentText);
			
			/*Check questioin checkbox*/
			comments.clickQuestionIndicator();
			
			/*Click anonymous indicator*/
			comments.clickAnonymousIndicator();
			
			/*Click cancel button*/
			comments.clickNewCommentCancelButton();
			
			/*Verify than new comment box is not displayed*/
			Assert.assertFalse(comments.isCommentBoxDisplayed());
			
			/*Verify than new comment button is displayed*/
			Assert.assertTrue(comments.isNewCommentButtonDisplayed());
			
			/*Verify than new comment is not added to the list*/
			Assert.assertEquals(comments.getCommentPosition(newCommentText),0,"Comment displayed n the list after click new comment cancel button");
		}
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that sorting in library is only in row view")
	public void SortRowViewOnly() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/*Check that sorting row is not displayed*/
		Assert.assertFalse(libraryPage.isSortingPanelDisplayed(),"Sorting row dispalyed in tile view");
		
		/*Click row view button*/
		libraryPage.clickListView();
		
		/*Check that sorting row is displayed*/
		Assert.assertTrue(libraryPage.isSortingPanelDisplayed(),"Sorting row is not dispalyed in row view");
		
		/*Click row view button*/
		libraryPage.clickTileView();
		
		/*Check that sorting row is not displayed*/
		Assert.assertFalse(libraryPage.isSortingPanelDisplayed(),"Sorting row dispalyed in tile view");
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is not able to create blank comment or edit comment to be blank")
	public void BlankComment()  
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		Date d = new Date();
		String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		
		/*Select summary checkbox*/
		libraryPage.selectSummaryCheckbox();
		
		/* Click the Open button for the first Video in the results */
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/* Click 'new comment' button */
		comments.clickNewCommentButton();

		/*Check that submit button is disabled*/
		Assert.assertTrue(comments.isNewCommentSubmitButtonDisabled(),"Submit button enabled");
				
		/*Enter some text into new comment box*/
		comments.enterTextIntoNewCommentBox(commentText);
		
		/*Check that submit button is NOT disabled*/
		Assert.assertFalse(comments.isNewCommentSubmitButtonDisabled(),"");
		
		/*Clear comment box*/
		comments.clearTextInNewCommentBox();
	
		/*Check that submit button is enabled*/
		Assert.assertTrue(comments.isNewCommentSubmitButtonDisabled(),"Submit button enabled");
		
		/*Enter some text into new comment box*/
		comments.enterTextIntoNewCommentBox(commentText);
		
		/*Click Submit button*/
		comments.clickNewCommentSubmitButton();
		
		/*Search for test comment*/
		int commentPos = comments.getCommentPosition(commentText);
		
		/*Click edit link on comment*/
		comments.clickEditLinkOnNthComment(commentPos);
		
		/*Clear comment box*/
		comments.clearTextInNewCommentBox();
		
		/*Check that submit button is enabled*/
		Assert.assertTrue(comments.isNewCommentSubmitButtonDisabled(),"Submit button enabled");
		
		/*Click cancel button*/
		comments.clickNewCommentCancelButton();
		
		/*Search for test comment*/
		commentPos = comments.getCommentPosition(commentText);
		
		/*Click edit link on comment*/
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "LibraryPageOpens" }, description="Verifies that user is not able to create blank reply or edit reply to be blank")
	public void BlankReply() 
	{
	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		
		Date d = new Date();
		String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		
		/*Select summary checkbox*/
		libraryPage.selectSummaryCheckbox();
		
		/* Click the Open button for the first Video in the results */
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/*Create new comment*/
		comments.createNewComment("comment", false, commentText);
		
		/*Search for test comment*/
		int commentPos = comments.getCommentPosition(commentText);
		
		/*Click add reply link*/
		comments.clickAddReplyLinkOnNthComment(commentPos);
		
		/*click reply submit button*/
		comments.clickBlankReplySubmitButton();
		
		/*check that reply box is still displayed*/
		Assert.assertTrue(comments.isReplyBoxDisplayed(),"Reply box is not displayed");
		
		/*Enter text into reply box*/
		comments.enterTextIntoNewReplyBox(commentText);
	
		/*click reply submit button*/
		comments.clickReplySubmitButton();
		
		/*Search for test comment*/
		commentPos = comments.getCommentPosition(commentText);
		
		/*Edit reply*/
		comments.clickEditLinkOnMthReplyInNthComment(commentPos, 1);
		
		/*Clear reply text*/
		comments.clearTextInReplyBox();
		
		/*click reply submit button*/
		comments.clickBlankReplySubmitButton();
		
		/*check that reply box is still displayed*/
		Assert.assertTrue(comments.isReplyBoxDisplayed(),"Reply box is not displayed");
		
		/*Click reply cancel button*/
		comments.clickReplyCancelButton();
		
		/*Delete reply and comment*/
		commentPos = comments.getCommentPosition(commentText);
		comments.clickDeleteLinkForMthReplyInNthComment(commentPos, 1);
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
	@Test(groups={"FullRegression","BVT"}, dependsOnMethods = { "VideoPageLoads" },description="Verifies that resume previous state works for videos")
	public void ResumePreviousVideoState() throws InterruptedException 
	{
		/*Navigate to library*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/*select video in the filters*/
		libraryPage.selectVideoCheckbox();

		int panel=Global.getDifferentIntForEachEnvironment(platform+currentBrowser+version);
		
		//Click Open button for the selected Video in the results 
		libraryPage.click1stButtonInNthPanel(panel);

		//Load video page
		Video video = new Video(getDriver());
		
		 //Check if the resume video dialog appears, if it does, click start over 
		if (video.isResumeInformationBoxDisplayed())
		{
			video.clickResumeVideoStartOver();
			video.clickPause();
		}
		
		//Get initial video state
		int initialState = video.getStateInSeconds();
		
		 //Click play on the video 
		video.clickPlay();
		
		//Wait 5 second to play video
		Thread.sleep(3000);
		
		//Click play on the video 
		video.clickPause();
		
		//Get new video state
		int newState = video.getStateInSeconds();
		
		//Verify that video is playing
		Assert.assertFalse(initialState==newState,"Video does not play");

		//Navigate to homepage and back to same video
		navigation.clickHome();
		navigation.hoverOverLibrary();
		navigation.clickLibrary();
		libraryPage.selectVideoCheckbox();
		libraryPage.click1stButtonInNthPanel(panel);
		video.waitForPageToLoad(getDriver());
		
		//Check that resume previous state popup is displayed
		Assert.assertTrue(video.isResumeInformationBoxDisplayed(),"Resume previous state popup not displayed");
		
		//Click resume button and pause video
		video.clickResumeVideoResume();
		video.clickPause();
		
		//Get video state
		int resumedState = video.getStateInSeconds();
		
		//Verify that video is resumed to the correct state
		Assert.assertTrue(resumedState>=newState,"Resumed video state is not correct."+" Video left at: "+newState+" But resumed state is: "+resumedState);
	}	
	@Test(enabled = false, groups={"FullRegression","BVT"},description="Verifies that video can be played in fullscreen")
	public void VideoFullscreenOnOff() throws InterruptedException 
	{
		//TODO 
		//this test can be enabled when issue with playing vidoes on MAC/safari will be resolved, currently videos stop playing there after 2 seconds
		
		//Navigate to library
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		long wait = 3000;
		
		//select video in the filters
		libraryPage.selectVideoCheckbox();
		
		//Click Open button for the selected Video in the results 
		libraryPage.click1stButtonInNthPanel(1);

		//Load video page
		Video video = new Video(getDriver());
		
		//Check if the resume video dialog appears, if it does, click start over 
		if (video.isResumeInformationBoxDisplayed())
			video.clickResumeVideoStartOver();
		else
			video.clickPlay();
		
		//Get initial video state
		int previousState = video.getStateInSeconds();
		
		//Wait
		Thread.sleep(wait);
		
		//Check that video is playing
		Assert.assertTrue(previousState < video.getStateInSeconds(),"Video failed to play in window mode. ");
		
		//Update previous state
		previousState = video.getStateInSeconds();
		
		//Open fullscreen
		video.clickFullscreenOnButton();
		
		//Wait
		Thread.sleep(wait);
		
		//Check that video is playing and fullscreen in on
		Assert.assertTrue(video.isFullscreenOn(currentBrowser),"Fullscreen is not on. ");
		Assert.assertTrue(previousState < video.getStateInSeconds(),"Video is not playing in fullscreen mode. ");
		
		//Update previous state
		previousState = video.getStateInSeconds();
		
		//Exit fullscreen
		video.clickFullscreenOffButton();
		
		//Wait
		Thread.sleep(wait);
		
		//Check that video is playing and fullscreen in off
		Assert.assertTrue(previousState < video.getStateInSeconds(),"Video is not playing in window mode. ");
		Assert.assertFalse(video.isFullscreenOn(currentBrowser),"Fullscreen is not off. ");		
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "LibraryPageOpens" },description="Verifies user can read tips for new comment")
	public void NewCommentTips() 
	{
		String showTipsText = new LocaleStrings().getString("SHOW_TIPS");
		String hideTipsText = new LocaleStrings().getString("HIDE_TIPS");
		String newCommentTips = new LocaleStrings().getString("NEW_COMMENT_TIPS");
		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
				
		/*Select summary checkbox*/
		libraryPage.selectSummaryCheckbox();
		
		/* Click the Open button for the first Video in the results */
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/*Click new comment button*/
		comments.clickNewCommentButton();
		
		/*Check that show tips link is displayed*/
		Assert.assertTrue(comments.isShowTipsLinkDisplayed(),"Show tips link is not displayed");
		
		/*Verify that tips link text is correct*/
		Assert.assertEquals(comments.getTipsLinkText(), showTipsText,"Show tips link is not correct");
		
		/*Click show tips link*/
		comments.clickTipsLink();
		
		/*Verify that tips link text is correct*/
		Assert.assertEquals(comments.getTipsLinkText(), hideTipsText,"Show tips link is not correct");
		
		/*Verify that tips appear*/
		Assert.assertEquals(comments.getNewCommentTips(), newCommentTips,"Show tips link is not correct");
		
		/*Click hide tips link*/
		comments.clickTipsLink();
		
		/*Check that tips are hidden*/
		Assert.assertFalse(comments.isNewCommentTipsTextDisplayed(),"Tips displayed afer hide tips user action");
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "LibraryPageOpens" }, description="Verifies user can like a comment")
	public void LikeAComment() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
				
		/*Select summary checkbox*/
		libraryPage.selectSummaryCheckbox();
		
		/* Click the Open button for the first summary in the results */
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/*Create new comment*/
		Date d = new Date();
		String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		comments.createNewComment("comment", false, commentText);
		
		/*Search for test comment*/
		int commentPos = comments.getCommentPosition(commentText);
		
		/*Check that like counter is equal zero */
		Assert.assertEquals(comments.getLikesCounterForNthComment(commentPos), 0,"Likes counter for new comment is not equal 0");
		
		/*Click like link*/
		comments.clickLikeForNthComment(commentPos);
		
		/*Check that like counter is increased by one*/
		Assert.assertEquals(comments.getLikesCounterForNthComment(commentPos), 1,"Likes counter for new comment is not equal 0");
		
		/*Click unlike link*/
		comments.clickUnlikeForNthComment(commentPos);
		
		/*Check that like counter is decreased by one*/
		Assert.assertEquals(comments.getLikesCounterForNthComment(commentPos), 0,"Likes counter for new comment is not equal 0");
		
		/*Delete comment*/
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "CreateCommentOnSummary" }, description="Verifies user can show more/less recent replies.")
	public void ShowMoreLessReplies() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
				
		/*Select summary checkbox*/
		libraryPage.selectSummaryCheckbox();
		
		/* Click the Open button for the first summary in the results */
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/*Strings setup*/
		Date d = new Date();
		String commentText = "Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		String replyText1 = "Reply1_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		String replyText2 = "Reply2_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		String show1MoreReplies = new LocaleStrings().getString("SHOW_MORE_REPLIES");
		String showMostRecent = new LocaleStrings().getString("SHOW_MOST_RECENT");
		
		
		/*Create new comment*/
		comments.createNewComment("comment", false, commentText);
		
		/*Search for test comment*/
		int commentPos = comments.getCommentPosition(commentText);
		
		/*Create first reply to above comment*/
		comments.createNewReplyforNthComment(replyText1, commentPos);
		
		/*Verify that show more replies link is not displayed*/
		Assert.assertFalse(comments.isShowMoreRepliesLinkDisplayed(commentPos),"Show more replies link is displayed");
		
		/*Create second reply to above comment*/
		comments.createNewReplyforNthComment(replyText2, commentPos);
		
		/*navigate to hompage and back to same content*/
		libraryPage = navigation.clickLibrary();
		libraryPage.selectSummaryCheckbox();
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Update Comment position*/
		commentPos = comments.getCommentPosition(commentText);
		
		/*Verify presence of UI elements*/
		Assert.assertTrue(comments.isShowMoreRepliesLinkDisplayed(commentPos),"Show more replies link is not displayed.");
		Assert.assertEquals(comments.getShowMoreRepliesLinkText(commentPos),show1MoreReplies,"Show 1 more replies link not displayed.");
		Assert.assertTrue(comments.isMthReplyInNthCommentDisplayed(commentPos, 1),"Most recent reply is not displayed.");
		Assert.assertFalse(comments.isMthReplyInNthCommentDisplayed(commentPos, 2),"Older reply is displayed.");
		Assert.assertEquals(comments.getMthReplyTextInNthComment(commentPos,1),replyText2,"Reply text for most recent reply is not correct.");
			
		/*Click show more/less replies*/
		comments.clickShowMorelessRepliesOnNthComment(commentPos);
		
		/*Update Comment position*/
		commentPos = comments.getCommentPosition(commentText);
		
		/*Verify presence of UI elements*/
		Assert.assertTrue(comments.isShowMoreRepliesLinkDisplayed(commentPos),"Show only most recnet link is not displayed");
		Assert.assertEquals(comments.getShowMoreRepliesLinkText(commentPos),showMostRecent,"Show only most recent link not displayed");
		Assert.assertTrue(comments.isMthReplyInNthCommentDisplayed(commentPos, 1),"Most recent reply is not displayed.");
		Assert.assertTrue(comments.isMthReplyInNthCommentDisplayed(commentPos, 2),"Older reply is not displayed.");
		Assert.assertEquals(comments.getMthReplyTextInNthComment(commentPos,1),replyText2,"Reply text for most recent reply is not correct.");
		Assert.assertEquals(comments.getMthReplyTextInNthComment(commentPos,2),replyText1,"Reply text for older reply is not correct.");
		
		/*Click show more/less replies*/
		comments.clickShowMorelessRepliesOnNthComment(commentPos);
		
		/*Update Comment positiont*/
		commentPos = comments.getCommentPosition(commentText);
		
		/*Verify presence of UI elements*/
		Assert.assertTrue(comments.isShowMoreRepliesLinkDisplayed(commentPos),"Show more replies link is not displayed.");
		Assert.assertEquals(comments.getShowMoreRepliesLinkText(commentPos),show1MoreReplies,"Show 1 more replies link not displayed.");
		Assert.assertTrue(comments.isMthReplyInNthCommentDisplayed(commentPos, 1),"Most recent reply is not displayed.");
		Assert.assertFalse(comments.isMthReplyInNthCommentDisplayed(commentPos, 2),"Older reply is displayed.");
		Assert.assertEquals(comments.getMthReplyTextInNthComment(commentPos,1),replyText2,"Reply text for most recent reply is not correct.");

		/*Click show more/less replies*/
		comments.clickShowMorelessRepliesOnNthComment(commentPos);
		
		/*Update Comment position*/
		commentPos = comments.getCommentPosition(commentText);
		
		/*Clear test data*/
		comments.clickDeleteLinkForMthReplyInNthComment(commentPos, 2);
		comments.clickDeleteLinkForMthReplyInNthComment(commentPos, 1);
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "LibraryPageOpens" }, description="Verifies user can like a content and content likes counters are consistend accorss library")
	public void ContentLike() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		//Load Tile view page
		TileView tile = new TileView(getDriver());
		
		//use different content for each test-browser
		int panel = Global.getDifferentIntForEachEnvironment(platform+currentBrowser+version); 

		/* Click Switch To Back */
		tile.clickNthSwitchToBack(panel);
		
		//Get likes counter
		int backTileLikes = Integer.parseInt(tile.getLikesCount(panel));
	
		//Click library list view button
		libraryPage.clickListView();
		
		//Load list view page
		ListView list = new ListView(getDriver());
		
		//click twistie button to show more details
		list.clickTwistieExpand(panel);
		
		//Get likes counter
		int listViewLikes = list.getLikesCounterForNthItem(panel);
		
		//Open content
		list.clickNthCourseTitle(panel);
		
		//Load content page
		MediaType mediaType = new MediaType(getDriver());
		mediaType.waitForPageToLoad(getDriver());
		
		//Get likes counter
		int contentPageLikes = mediaType.getMediaLikesCount();
		
		//Verify that all like counters match
		Assert.assertEquals(backTileLikes, listViewLikes,"Likes counter on back tile does not match like counter on list view.");
		Assert.assertEquals(listViewLikes, contentPageLikes,"Likes counter on list view does not match like counter on content page.");
		
		//If content already liked click unlike and update all counters
		if(mediaType.isContentAlreadyLiked()){
			mediaType.clickContentLikeButton();
			backTileLikes--;
			listViewLikes--;
			contentPageLikes--;
		}
		
		//Click like icon 
		mediaType.clickContentLikeButton();
		
		//Get likes counter
		int contentPageLikes2 = mediaType.getMediaLikesCount();
		
		//Verify that content likes status and counter are updated
		Assert.assertEquals(contentPageLikes2, contentPageLikes+1,"Likes counter not increased by one");
		Assert.assertTrue(mediaType.isContentAlreadyLiked(),"Like status not updated");
		
		//Navigate back to library and check that counter are increased
		navigation.hoverOverLibrary();
		libraryPage = navigation.clickLibrary();

		//Click Switch To Back 
		tile.clickNthSwitchToBack(panel);
		
		//Verify that counter is increased by one
		Assert.assertEquals(Integer.parseInt(tile.getLikesCount(panel)), backTileLikes+1,"Likes counter on back tile view not increased by one");
		
		//Click library list view button
		libraryPage.clickListView();
		
		//click twistie button to show more details
		list.clickTwistieExpand(panel);
		
		//Verify that counter is increased by one
		Assert.assertEquals(list.getLikesCounterForNthItem(panel), listViewLikes+1,"Likes counter on list view not increased by one");
		
		//Open content
		list.clickNthCourseTitle(panel);
		
		//Click like icon 
		mediaType.clickContentLikeButton();
		
		//Verify that content likes status and counter are updated
		Assert.assertEquals(mediaType.getMediaLikesCount(), contentPageLikes2-1,"Likes counter not decreased by one");
		Assert.assertFalse(mediaType.isContentAlreadyLiked(),"Like status not updated");
		
		
		//Navigate back to library and check that counter are decreased
		navigation.hoverOverLibrary();
		libraryPage = navigation.clickLibrary();

		//Click Switch To Back 
		tile.clickNthSwitchToBack(panel);
		
		//Verify that counter is increased by one
		Assert.assertEquals(Integer.parseInt(tile.getLikesCount(panel)), backTileLikes,"Likes counter on back tile view not decreased by one");
		
		//Click library list view button
		libraryPage.clickListView();
		
		//click twistie button to show more details
		list.clickTwistieExpand(panel);
		
		//Verify that counter is increased by one
		Assert.assertEquals(list.getLikesCounterForNthItem(panel), listViewLikes,"Likes counter on list view not decreased by one");
		
	}
	@Test(groups={"FullRegression"}, dependsOnMethods = { "CreateCommentOnSimulator" }, description="Verifies that user can use special characters in comments.")
	public void SpecialCharactersInComments() 
	{
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
				
		/*Select summary checkbox*/
		libraryPage.selectSimulationCheckbox();
		
		/* Click the Open button for the first summary in the results */
		libraryPage.click1stButtonInNthPanel(1);
		
		/*Launch comments panel*/
		Comments comments = new Comments(getDriver());
		
		/*Setup comments with special characters*/
		Date d = new Date();
		String commentText = "!£$%^&*()}{@:?><"+"Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+username;
		
		//Create new comment with given text
		comments.createNewComment("comment", false, commentText);
		
		//search test comment
		int commentPos = comments.getCommentPosition(commentText);
	
		//Get comment text
		String commentTextPosted = comments.getNthCommentText(commentPos);
		
		//Check that comments text displayed as expected
		Assert.assertEquals(commentTextPosted, commentText);
		
		//delete test data
		comments.clickDeleteLinkOnNthComment(commentPos);
	}
}
