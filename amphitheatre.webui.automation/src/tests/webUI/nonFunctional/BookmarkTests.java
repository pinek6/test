package tests.webUI.nonFunctional;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.Global;
import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.Home;
import webUI.home.bookmarks.Bookmarks;
import webUI.home.library.Library;
import webUI.home.library.mediaTypes.MediaType;
import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;

public class BookmarkTests extends LoadBrowser {
	
	private String email1, displayname, fname, lname, occupation, country, city, password;
	private int postGradExperience, specialty, subSpecialty, interest;
	
	/* Page Headers */
	private String bookmarksPageHeader = new LocaleStrings().getString("BOOKSMARKS_PAGE_HEADER");
	private String emptyStateMsg = new LocaleStrings().getString("BOOKSMARKS_EMPTY_STAGE");
	private String otherCity = new LocaleStrings().getString("OTHER_CITY");
	
	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
	public void beforeMethod()
    {
		//getDriverAndLogin();
    }
	public String createNewMember()
	{		
		logIntoAdminConsole();
		
		/* Set the email and display name */
		Date d = new Date();
		String email = "fvt"+d.getTime()+"@bluebox.lotus.com";
		displayname = "z_fvt_"+d.getTime();
		fname = "z_fname";
		lname = "l_fname_"+d.getTime();
		occupation="Surgeon"; 
		country="United States"; 
		city=otherCity; 
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
		//Reporter.log("Member successfully created: " + email);
		
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
				
		return email;
	}
	public void createNewBookmark()
	{
		/* Navigate to library */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		int countOfResults = libraryPage.getCountOfResultsInTileView();
			
		if (countOfResults==0)
			Assert.fail("No contents found in the Library");
		else
		{	
			/* Click the Open button for the first Summary in the results */
			libraryPage.click1stButtonInNthPanel(3);//TODO
			
			/*Launch content page*/
			MediaType mediaType = new MediaType(getDriver());
			mediaType.waitForPageToLoad(getDriver());
			
			/* Setup bookmark note */
			Date d = new Date();
			String bookmarkNote = "!£$%^&*()}{@:?><"+"Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+email1;
			
			/* Click content bookmark icon */
			mediaType.bookmarkContent(bookmarkNote);
							
			/* Navigate to Bookmarks */		
			navigation.hoverOverBookmarks();
			Bookmarks bookmarksPage = navigation.clickBookmarks();
			bookmarksPage.waitForPageToLoad(getDriver());
		}	
	}
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Bookmarks page loads successfully")
	public void BookmarksPageOpens()
	{
		/*Create new user for all bookmark tests*/
		
		/* Navigate to the webUI login page and login */
		getDriverAndLogin();
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();		
		
		/* Navigate to Bookmarks */		
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();		
		
		Assert.assertTrue(bookmarksPage.getHeader().contains(bookmarksPageHeader),"Incorrect header on Bookmarks page");
		Assert.assertTrue(bookmarksPage.isSearchInputDisplayed(),"Search Input field failed to load");
	}
	@Test(alwaysRun=true, groups={"FullRegression"}, dependsOnMethods = { "BookmarksPageOpens" },  description="Verifies that empty bookmark page is correctly displayed")
	public void NoBookmakrsPage() 
	{	
		email1 = createNewMember();
		
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
		
		/* Navigate to Bookmarks */	
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();
		bookmarksPage.waitForPageToLoad(getDriver());
		
		/* Launch bookmarks page */
		Bookmarks bookmarks = new Bookmarks(getDriver());
	
		/* Get bookmakrs counter */
		int bookmarkCounter = bookmarks.getBookmarksCounter();
		int countofBookmakrs = bookmarks.getCountOfBookmarks();
		
		/* Verify that bookmark is 0 and 0 bookmakrs is displayed */
		Assert.assertEquals(bookmarkCounter, 0,"Bookmakrs counter different after cancelation of bookmark removal.");
		Assert.assertEquals(countofBookmakrs, 0,"Count of bookmakrs different after cancelation of bookmark removal.");
		
		/* Verify that empty state message is displayed */
		Assert.assertTrue(bookmarks.isBookmarksEmptyStateMsgDisplayed(),"Empty state message is not displayed");
		Assert.assertEquals(bookmarks.getBookmarksEmptyStateMsgDisplayed(), emptyStateMsg,"Incorrect empty state message displayed");
	}
	@Test(enabled=false, alwaysRun=true, groups={"BVT","FullRegression"}, dependsOnMethods = { "NoBookmakrsPage" }, description="Verifies that user is able to create/open/remove new bookmark from content page.")
	public void CreateOpenRemoveBookmarkFromContentPage() 
	//TODO
	//to be enabled when defects(16912 and 20727) will be fixed
	//and first 6 contents for each type will work on D1 and Q1 
	{	
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
		
		/* Navigate to library */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Load library page */
		Library library = new Library(getDriver());
		
		int countOfTypes = library.getCountOfSubjectsInNthCategory(4);
		
		/* Get different panel for each test environment */
		int usePanel = Global.getDifferentIntForEachEnvironment(platform+currentBrowser+version); ;
		
		for (int i=1;i<=countOfTypes;i++)
		{
			/* Select type check-box in the filters */
			libraryPage.clickMthSubjectInNthCategoryInLeftColumns(4,i);
	
			/* Get list of types selected in filters */
			String currentType = libraryPage.getMthSubjectInNthCategoryInLeftColumns(4, i);
			
			/* Get the count of calculator media in the results */
			int countOfResults = libraryPage.getCountOfResultsInTileView();
			
			if (countOfResults==0)
				Assert.fail("No "+currentType+" media types found in the Library");
			else
			{	
				/* Click the Open button for the first Summary in the results */
				libraryPage.click1stButtonInNthPanel(usePanel);
				
				/* Launch content page */
				MediaType mediaType = new MediaType(getDriver());
				mediaType.waitForPageToLoad(getDriver());
				
				/* Get bookmark counter */
				int bookmarkCounter1 = mediaType.getMediaBookMarksCount();
				
				/* Check that bookmark icon is displayed and not selected */
				Assert.assertTrue(mediaType.isBookmarkDeselected(),"Deselected bookmakr icon is on displayed");
				
				/* Setup bookmark note */
				Date d = new Date();
				String bookmarkNote = "BookmarkNote_"+"Comment_fvt_automation_"+platform+"_"+currentBrowser+"_" + version+"_"+d.getTime()+"|"+email1;
				
				/* Click content bookmark icon */
				mediaType.bookmarkContent(bookmarkNote);
				
				/* Get bookmark counter */
				int bookmarkCounter2 = mediaType.getMediaBookMarksCount();
				
				/* Get content title and description */
				String contentTitle = mediaType.getContentTitle();
				String contentDescription = mediaType.getContentDescription();
				if (contentDescription.length() > 150)
				{
					contentDescription = contentDescription.substring(0, 150);
				}
				
				/* Check that bookmark icon is displayed and selected */
				Assert.assertTrue(mediaType.isBookmarkSelected(),"Bookmark icon is not selected");
				Assert.assertEquals(bookmarkCounter1+1, bookmarkCounter2,"Bookmark counter not increased by one.  "+contentTitle);
				
				/* Navigate to Bookmarks */		
				navigation.hoverOverBookmarks();
				Bookmarks bookmarksPage = navigation.clickBookmarks();
				bookmarksPage.waitForPageToLoad(getDriver());
				
				/* Launch bookmarks page */
				Bookmarks bookmarks = new Bookmarks(getDriver());
				
				/* Get bookmarks counter */
				int bookmakrsCounter = bookmarks.getBookmarksCounter();
				
				/* Get count of bookmarks */
				int countOfBookmarks = bookmarks.getCountOfBookmarks();
				
				int currentBookmark = 1;
				String dateAdded = "1m ago";
				
				/* Verify that bookmark details are correct */
				//Assert.assertEquals(bookmarks.getNthBookmarkIcon(currentBookmark), currentType,"Bookmark icon does not match content type");
				Assert.assertEquals(bookmarks.getNthBookmarkTitle(currentBookmark), contentTitle,"Bookmark title does not match content title");
				//Assert.assertEquals(bookmarks.getNthBookmarkType(currentBookmark), currentType,"Bookmark type does not match content type");
				Assert.assertEquals(bookmarks.getNthBookmarkDate(currentBookmark), dateAdded,"Bookmark date added does not match '1m ago'");
				Assert.assertEquals(bookmarks.getNthBookmarkNotes(currentBookmark), contentDescription,"Bookmark notes does not match content description");
				Assert.assertEquals(bookmakrsCounter, countOfBookmarks,"Bookmark counter is not equal number of bookmakrs");

				/* Click on text bookmark title */
				bookmarks.clickNthBookmarkTitle(currentBookmark);
				
				/* Wait for content page load */
				mediaType.waitForPageToLoad(getDriver());
				
				/* Check that bookmark icon is displayed and selected */
				Assert.assertTrue(mediaType.isBookmarkSelected(),"Bookmark icon is not selected");
								
				/* Click content bookmark icon */
				mediaType.unbookmarkContent();
				
				/* Check that bookmark icon is displayed and not selected */
				Assert.assertTrue(mediaType.isBookmarkDeselected(),"Deselected bookmakr icon is on displayed");
				
				/* Verify that bookmark counter is decreased by one */
				int bookmarkCounter3 = mediaType.getMediaBookMarksCount();
				Assert.assertEquals(bookmarkCounter2-1, bookmarkCounter3,"Bookmark counter not decreased by one.");
								
				/* Verify that correct content is opened */
				Assert.assertEquals(mediaType.getContentTitle(), contentTitle,"Content title does not match bookmark title");
								
				/* Navigate to Bookmarks */		
				navigation.hoverOverBookmarks();
				navigation.clickBookmarks();
				
				/* Get bookmarks counter */
				int bookmakrsCounter2 = bookmarks.getBookmarksCounter();
				
				/* Verify that bookmark does not appear on bookmarks page */
				Assert.assertEquals(bookmakrsCounter2, 0,"Bookmakrs counter is not equal 0");
				
				/* Navigate to library */
				navigation.hoverOverLibrary();
				navigation.clickLibrary();
			}
		}	
	}		
	@Test(groups={"FullRegression"}, dependsOnMethods = { "NoBookmakrsPage" }, alwaysRun=true,  description="Verifies that user is able to cancel bookmark removal.")
	public void CancelRemoveBookmark() 
	{	
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
				
		/* Navigate to Bookmarks */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();
		bookmarksPage.waitForPageToLoad(getDriver());
		
		/* Launch bookmarks page */
		Bookmarks bookmarks = new Bookmarks(getDriver());
		
		int countOfBookmarks = bookmarks.getBookmarksCounter();
		
		/* If no bookmarks found create one and navigate back to Bookmarks */
		if(countOfBookmarks==0)
		{
			createNewBookmark();
		}
		int currentBookmark = 1;
		
		/* Click more details button on Bookmark */
		bookmarks.clickMoreDetailsButtonOnNthBookmark(currentBookmark);
		
		/* Get bookmarks counter */
		int bookmarkCounterBefore = bookmarks.getBookmarksCounter();
		int countofBookmakrsBefore = bookmarks.getCountOfBookmarks();
		
		/* Click remove button on Bookmark */
		bookmarks.clickRemoveButtonOnNthBookmark(currentBookmark);
		
		/* Cancel deletion */
		bookmarks.clickCancelButtonInDeletionPopup();
		
		/* Get bookmarks counter */
		int bookmarkCounterAfter = bookmarks.getBookmarksCounter();
		int countofBookmakrsAfter = bookmarks.getCountOfBookmarks();
		
		/* Verify that bookmark is not deleted */
		Assert.assertEquals(bookmarkCounterBefore, bookmarkCounterAfter,"Bookmakrs counter different after cancelation of bookmar removal.");
		Assert.assertEquals(countofBookmakrsBefore, countofBookmakrsAfter,"Count of bookmakrs different after cancelation of bookmar removal.");
		
		/* Click remove button on Bookmark */
		bookmarks.clickRemoveButtonOnNthBookmark(currentBookmark);
		
		/* Close deletion popup */
		bookmarks.clickCloseButtonInDeletionPopup();
		
		/* Get bookmarks counter */
		int bookmarkCounterAfter2 = bookmarks.getBookmarksCounter();
		int countofBookmakrsAfter2 = bookmarks.getCountOfBookmarks();
		
		/* Verify that bookmark is not deleted */
		Assert.assertEquals(bookmarkCounterBefore, bookmarkCounterAfter2,"Bookmakrs counter different after closing deletion popup");
		Assert.assertEquals(countofBookmakrsBefore, countofBookmakrsAfter2,"Count of bookmakrs different after closing deletion popup");
	}
	@Test(alwaysRun=true, groups={"FullRegression"}, dependsOnMethods = { "CancelRemoveBookmark" },  description="Verifies that user is able to edit bookmark note")
	public void AddNoteToBookmark() 
	{	
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
		
		/* Navigate to Bookmarks */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();
		bookmarksPage.waitForPageToLoad(getDriver());
		
		/* Launch bookmarks page */
		Bookmarks bookmarks = new Bookmarks(getDriver());
		
		int countOfBookmarks = bookmarks.getBookmarksCounter();
		
		/* If no bookmarks found create one and navigate back to Bookmarks */
		if(countOfBookmarks==0)
		{
			createNewBookmark();
		}
		
		int currentBookmark = 1;
		
		/* Verify that edit options are not displayed before edit mode */
		Assert.assertFalse(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link displayed before edit mode");
		Assert.assertFalse(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link displayed before edit mode");
		Assert.assertFalse(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter displayed before edit mode");
		
		/* Click on bookmark note to edit */
		bookmarks.clickNthBookmarkNotes(currentBookmark);
		
		/* Verify that edit options are displayed */
		Assert.assertTrue(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link not displayed in edit mode");
		Assert.assertTrue(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link not displayed in edit mode");
		Assert.assertTrue(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter not displayed in edit mode");
		
		/* Clear Bookmark notes */
		bookmarks.clearNotesBoxOnNthBookmark(currentBookmark);
		
		String newNotes = "notes edited by: "+email1;
		bookmarks.enterNotesIntoNthBookmark(currentBookmark, newNotes);
		
		/* Click Save button */
		bookmarks.clickSaveForNthBookmarkNotes(currentBookmark);
		
		/* Verify that edit options are not displayed before edit mode */
		Assert.assertFalse(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link displayed after edit mode");
		Assert.assertFalse(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link displayed after edit mode");
		Assert.assertFalse(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter displayed after edit mode");
		
		/* Verify that bookmark notes are updated */
		Assert.assertEquals(bookmarks.getNthBookmarkNotes(currentBookmark), newNotes,"Notes updated");
		
	}
	@Test(alwaysRun=true, groups={"FullRegression"}, dependsOnMethods = { "AddNoteToBookmark" },  description="Verifies that user is able to cancel edit bookmark note")
	public void AddNoteToBookmarkCancel() 
	{	
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
		
		/* Navigate to Bookmarks */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();
		bookmarksPage.waitForPageToLoad(getDriver());
		
		/* Launch bookmarks page */
		Bookmarks bookmarks = new Bookmarks(getDriver());
		
		int countOfBookmarks = bookmarks.getBookmarksCounter();
		
		/* If no bookmarks found create one and navigate back to Bookmarks */
		if(countOfBookmarks==0)
		{
			createNewBookmark();
		}
		
		int currentBookmark = 1;
			
		/* Verify that edit options are not displayed before edit mode */
		Assert.assertFalse(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link displayed before edit mode");
		Assert.assertFalse(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link displayed before edit mode");
		Assert.assertFalse(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter displayed before edit mode");
		
		String notesBefore = bookmarks.getNthBookmarkNotes(currentBookmark);
		
		/* Click on bookmark note to edit */
		bookmarks.clickNthBookmarkNotes(currentBookmark);
		
		/* Verify that edit options are displayed */
		Assert.assertTrue(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link not displayed in edit mode");
		Assert.assertTrue(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link not displayed in edit mode");
		Assert.assertTrue(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter not displayed in edit mode");
		
		/* Clear Bookmark notes */
		bookmarks.clearNotesBoxOnNthBookmark(currentBookmark);
		bookmarks.enterNotesIntoNthBookmark(currentBookmark, "12345");
		
		/* Click Cancel button */
		bookmarks.clickCancelForNthBookmarkNotes(currentBookmark);
					
		/* Verify that edit options are not displayed after edit mode */
		Assert.assertFalse(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link displayed after edit mode");
		Assert.assertFalse(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link displayed after edit mode");
		Assert.assertFalse(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter displayed after edit mode");
		
		/* Verify that bookmark notes are not updated */
		Assert.assertEquals(bookmarks.getNthBookmarkNotes(currentBookmark), notesBefore,"Notes does not match");
	}	
	@Test(alwaysRun=true, groups={"FullRegression"}, dependsOnMethods = { "AddNoteToBookmarkCancel" },  description="Verifies that user is able to cancel edit bookmark note")
	public void AddNotePlaceholder() 
	{	
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
		
		/* Navigate to Bookmarks */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();
		bookmarksPage.waitForPageToLoad(getDriver());
		
		/* Launch bookmarks page */
		Bookmarks bookmarks = new Bookmarks(getDriver());
		
		int countOfBookmarks = bookmarks.getBookmarksCounter();
		
		/* If no bookmarks found create one and navigate back to Bookmarks */
		if(countOfBookmarks==0)
		{
			createNewBookmark();
		}
		
		int currentBookmark = 1;
			
		/* Verify that edit options are not displayed before edit mode */
		Assert.assertFalse(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link displayed before edit mode");
		Assert.assertFalse(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link displayed before edit mode");
		Assert.assertFalse(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter displayed before edit mode");
					
		/* Click on bookmark note to edit */
		bookmarks.clickNthBookmarkNotes(currentBookmark);
		
		/* Verify that edit options are displayed */
		Assert.assertTrue(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link not displayed in edit mode");
		Assert.assertTrue(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link not displayed in edit mode");
		Assert.assertTrue(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter not displayed in edit mode");
		
		/* Clear Bookmark notes */
		bookmarks.clearNotesBoxOnNthBookmark(currentBookmark);
		
		/* Click Cancel button */
		bookmarks.clickSaveForNthBookmarkNotes(currentBookmark);
					
		/* Verify that edit options are not displayed after edit mode */
		Assert.assertFalse(bookmarks.isCancelDisplayedForNthBookmarkNotes(currentBookmark),"Cancel link displayed after edit mode");
		Assert.assertFalse(bookmarks.isSaveDisplayedForNthBookmarkNotes(currentBookmark),"Save link displayed after edit mode");
		Assert.assertFalse(bookmarks.isCharCounterDisplayedForNthBookmarkNotes(currentBookmark),"Characters counter displayed after edit mode");
		
		/* Verify that bookmark placeholder is displayed */
		Assert.assertEquals(bookmarks.getNthBookmarkNotesPlaceholder(currentBookmark), "add note","bookmark notes placeholder does not match"+" add note");
	}		
	@Test(alwaysRun=true, groups={"FullRegression"}, dependsOnMethods = { "AddNotePlaceholder" }, description="Verifies that user is remove bookmark on Bookmakrs page.")
	public void RemoveBookmark() 
	{	
		/* Navigate to the webUI login page and login */
		getDriverAndLogin(email1,password);
		
		/* The home page should display */
		Home homePage = new Home(getDriver());
		homePage.waitForPageToLoad();
		
		/* Navigate to Bookmarks */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverBookmarks();
		Bookmarks bookmarksPage = navigation.clickBookmarks();
		bookmarksPage.waitForPageToLoad(getDriver());
		
		/* Launch bookmarks page */
		Bookmarks bookmarks = new Bookmarks(getDriver());
		
		int countOfBookmarks = bookmarks.getBookmarksCounter();
		
		/* If no bookmarks found create one and navigate back to Bookmarks */
		if(countOfBookmarks==0)
		{
			createNewBookmark();
		}
		
		int currentBookmark = 1;
		
		/* Get bookmarks counter */
		int bookmarkCounterBefore = bookmarks.getBookmarksCounter();
		int countofBookmakrsBefore = bookmarks.getCountOfBookmarks();
		
		/* Click more details button on Bookmark */
		bookmarks.clickMoreDetailsButtonOnNthBookmark(currentBookmark);
		
		/* Click remove button on Bookmark */
		bookmarks.clickRemoveButtonOnNthBookmark(currentBookmark);
		
		/* Confirm deletion */
		bookmarks.clickDeleteButtonInDeletionPopup();
		
		/* Get bookmarks counter */
		int bookmarkCounterAfter = bookmarks.getBookmarksCounter();
		int countofBookmakrsAfter = bookmarks.getCountOfBookmarks();
		
		Assert.assertEquals(bookmarkCounterBefore-1, bookmarkCounterAfter,"bookmakrs counter not decreased by one after removing a bookmark");
		Assert.assertEquals(countofBookmakrsBefore-1, countofBookmakrsAfter,"Bookmark not deleted from the list.");
	}
}
