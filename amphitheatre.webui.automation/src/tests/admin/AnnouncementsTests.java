package tests.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.home.Home;
import webUI.login.Login;
import admin.communications.announcements.Announcements;
import admin.communications.announcements.CreateAnnouncement;
import admin.communications.announcements.UploadImage;
import admin.navigation.LeftNav;

public class AnnouncementsTests extends LoadBrowser {
	
	private String activeStatusValue = new LocaleStrings().getString("ANNOUNCEMENT_ACTIVE_KEY");
	private String activeStatusText = new LocaleStrings().getString("ANNOUNCEMENT_ACTIVE_TEXT");
	private String archivedStatusValue = new LocaleStrings().getString("ANNOUNCEMENT_ARCHIVED_KEY");
	private String archivedStatusText = new LocaleStrings().getString("ANNOUNCEMENT_ARCHIVED_TEXT");
	private String DATE_FORMAT = new LocaleStrings().getString("DATE_FORMAT");	
		
	private String uploadImage = new LocaleStrings().getString("IMAGE_LOCATION");
	private String basicAnnouncementInternalTitle = "";
	private String basicAnnouncementHTML = "";
	private String basicAnnouncementOrder = "99";
	private String wspAnnouncementInternalTitle="";
	private String wspAnnouncementHTML="";
	private String wspAnnouncementOrder="5";
	
	@BeforeMethod(groups={"BVT","FullRegression","IEOnly","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }	
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies a basic announcement can be created")
	public void CreateABasicAnnouncement()
	{	
		/* Set the internal title and the html */
		Date d = new Date();
		basicAnnouncementInternalTitle = "fvt_announcement_title_"+d.getTime();
		basicAnnouncementHTML = "fvt_html_"+d.getTime();
		
		/* Auto Active/Archive Date variables */
		Calendar autoActiveCalendar=new GregorianCalendar();
		Calendar autoArchiveCalendar=new GregorianCalendar();
		
		/* Add 370 days to todays date for the auto-active date */
		autoActiveCalendar.add(Calendar.DATE, 370);	
		
		/* Add 1050 days to todays date for the auto-archive date */
		autoArchiveCalendar.add(Calendar.DATE, 1050);
		
		int autoActiveDay = autoActiveCalendar.get(Calendar.DAY_OF_MONTH);
		int autoActiveMonth = autoActiveCalendar.get(Calendar.MONTH)+1;
		int autoActiveYear = autoActiveCalendar.get(Calendar.YEAR);
		int autoArchiveDay = autoArchiveCalendar.get(Calendar.DAY_OF_MONTH);
		int autoArchiveMonth = autoArchiveCalendar.get(Calendar.MONTH)+1;
		int autoArchiveYear = autoArchiveCalendar.get(Calendar.YEAR);
		Date formattedAutoActiveDate = autoActiveCalendar.getTime();
		Date formattedAutoArchiveDate = autoArchiveCalendar.getTime();		
	
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
		
		/* Click create announcement */
		CreateAnnouncement createAnnouncement = announcementsPage.clickCreateAnnouncement();
	
		/* Fill in the required fields */
		createAnnouncement.enterInternalTitle(basicAnnouncementInternalTitle);
		createAnnouncement.selectTypeByValue("basic");
		createAnnouncement.enterHTML(basicAnnouncementHTML);	
		createAnnouncement.enterDisplayOrder(basicAnnouncementOrder);
		createAnnouncement.selectStatusByValue(activeStatusValue);
		createAnnouncement.selectAutoActiveDayByValue(autoActiveDay);
		createAnnouncement.selectAutoActiveMonthByValue(autoActiveMonth);
		createAnnouncement.selectAutoActiveYearByValue(autoActiveYear);
		createAnnouncement.selectAutoArchiveDayByValue(autoArchiveDay);
		createAnnouncement.selectAutoArchiveMonthByValue(autoArchiveMonth);
		createAnnouncement.selectAutoArchiveYearByValue(autoArchiveYear);		
		
		/* Click save */
		createAnnouncement.clickSave();

		/* Verify the saved successfully message appears and that no validation messages appear */
		Assert.assertTrue(createAnnouncement.isAnnouncementSavedSuccessfully(),"Announcement saved successfully confirmation message failed to display upon saving announcement");
		Assert.assertFalse(createAnnouncement.isValidationMessageDisplayed(),"Validation message displayed in error upon saving announcement");

		/* Click Announcements in the left hand navigation */
		leftnav.clickAnnouncements();
		
		/* Determine which row the announcement is in */
		int row = announcementsPage.getAnnouncementRow(basicAnnouncementInternalTitle);
		
		/* Verify the new announcement appears in the results grid */
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Assert.assertTrue(announcementsPage.isAnnouncementInternalTitleFound(basicAnnouncementInternalTitle),"New announcement not found in announcements grid");	
		Assert.assertEquals(announcementsPage.getNthAnnouncementStatus(row),activeStatusText);
		Assert.assertEquals(announcementsPage.getNthAnnouncementOrder(row),basicAnnouncementOrder);
		Assert.assertEquals(announcementsPage.getNthAnnouncementAutoActiveDate(row), sdf.format( formattedAutoActiveDate),"Incorrect auto-active date");
		Assert.assertEquals(announcementsPage.getNthAnnouncementAutoArchiveDate(row), sdf.format( formattedAutoArchiveDate),"Incorrect auto-archive date");
	
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the announcements and verify the new announcement is appearing */
		Assert.assertTrue(h.isAnnouncementFound(basicAnnouncementHTML),"New announcement not appearing in announcements panel on the home page");
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"CreateABasicAnnouncement"}, description="Verifies an image can be uploaded to a basic announcement")
	public void UploadAnImageToABasicAnnouncement()
	{			
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
		
		/* Click the edit link for the announcement */
		int row = announcementsPage.getAnnouncementRow(basicAnnouncementInternalTitle);
		CreateAnnouncement editPage = announcementsPage.clickNthEditLink(row);
		
		Assert.assertTrue(editPage.isUploadImageButtonDisplayed());
		Assert.assertFalse(editPage.isUploadedImageDisplayed());
		
		editPage.clickUploadImage();
		UploadImage u = new UploadImage(getDriver());
		u.enterImage(uploadImage);
		u.clickUpload();
		
		Assert.assertFalse(editPage.isUploadImageButtonDisplayed());
		Assert.assertTrue(editPage.isUploadedImageDisplayed());
		
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the announcements and verify the announcement is appearing */
		Assert.assertTrue(h.isAnnouncementFound(basicAnnouncementHTML),"Announcement not appearing in the announcement panel on the home page after uploading an image");
		
		Assert.assertFalse(h.isDefaultAnnouncementImageDisplayed());
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"UploadAnImageToABasicAnnouncement"}, description="Verifies a basic announcement can be archived and does not appear in the announcements on the home page")
	public void ArchiveAnAnnouncement()
	{		
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
	
		/* Determine which row the announcement is in and then click Edit for that row */
		int row = announcementsPage.getAnnouncementRow(basicAnnouncementInternalTitle);
		CreateAnnouncement editAnnouncement = announcementsPage.clickNthEditLink(row);
		
		/* Change the Status to Archived */
		editAnnouncement.selectStatusByValue(archivedStatusValue);
		
		/* Click Save */
		editAnnouncement.clickSave();
	
		/* Verify the saved successfully message appears */
		Assert.assertTrue(announcementsPage.isResultMessageDisplayed(),"Announcement saved successfully confirmation message failed to display upon saving announcement");
		
		/* Click Announcements in the left hand navigation */
		leftnav.clickAnnouncements();
		
		/* Determine which row the announcement is in */
		row = announcementsPage.getAnnouncementRow(basicAnnouncementInternalTitle);
		
		/* Verify the new announcement appears in the results grid */
		Assert.assertTrue(announcementsPage.isAnnouncementInternalTitleFound(basicAnnouncementInternalTitle),"Announcement not found in announcements grid");	
		Assert.assertEquals(announcementsPage.getNthAnnouncementStatus(row),archivedStatusText);
		
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the announcements and verify the announcement is not appearing */
		Assert.assertFalse(h.isAnnouncementFound(basicAnnouncementHTML),"Archived announcement incorrectly appearing in the announcements panel on the home page");
	}
	
	@Test(groups={"BVT","FullRegression","IEOnly"},dependsOnMethods={"ArchiveAnAnnouncement"}, description="Verifies a basic announcement can be edited")
	public void EditABasicAnnouncement()
	{		
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
	
		/* Determine which row the announcement is in and then click Edit for that row */
		int row = announcementsPage.getAnnouncementRow(basicAnnouncementInternalTitle);
		CreateAnnouncement editAnnouncement = announcementsPage.clickNthEditLink(row);
		
		/* Edit the Internal Title and HTML */
		basicAnnouncementInternalTitle = basicAnnouncementInternalTitle + "_EDITED";
		basicAnnouncementHTML = basicAnnouncementHTML + "_EDITED";
		
		/* Update the Internal Title and HTML fields and set status to be active */
		editAnnouncement.enterInternalTitle(basicAnnouncementInternalTitle);
		editAnnouncement.enterHTML(basicAnnouncementHTML);
		editAnnouncement.selectStatusByValue(activeStatusValue);
		
		/* Click Save */
		editAnnouncement.clickSave();
		
		/* Verify the saved successfully message appears */
		Assert.assertTrue(announcementsPage.isResultMessageDisplayed());
		
		/* Click Announcements in the left hand navigation */
		leftnav.clickAnnouncements();
		
		/* Verify the edited announcement appears in the results grid */
		Assert.assertTrue(announcementsPage.isAnnouncementInternalTitleFound(basicAnnouncementInternalTitle),"Edited announcement internal title not found in announcements grid");		
		
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the announcements and verify the edited announcement is appearing */
		Assert.assertTrue(h.isAnnouncementFound(basicAnnouncementHTML),"Edited announcement is not appearing in the announcements panel on the home page");
	}	
	
	@Test(groups={"BVT","FullRegression"},dependsOnMethods={"EditABasicAnnouncement"}, description="Verifies a basic announcement can be deleted", alwaysRun = true)
	public void DeleteABasicAnnouncement()
	{		
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
		
		/* Click the Delete link for the announcement */
		announcementsPage.deleteAnnouncement(basicAnnouncementInternalTitle);
		
		/* Verify the saved successfully message appears */
		Assert.assertTrue(announcementsPage.isResultMessageDisplayed());
		
		/* Verify the announcement is not found in the announcements grid */
		Assert.assertFalse(announcementsPage.isAnnouncementInternalTitleFound(basicAnnouncementInternalTitle));	
		
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the announcements and verify the announcement is not appearing */
		Assert.assertFalse(h.isAnnouncementFound(basicAnnouncementHTML),"Deleted announcement appearing in the announcement panel on the home page in error");
	}	

	@Test(groups={"BVT","FullRegression"}, description="Verifies a World Shared Practice announcement can be created")
	public void CreateAWorldSharedPracticeAnnouncement()
	{	
		/* Set the internal title and the html */
		Date d = new Date();
		wspAnnouncementInternalTitle = "wsp_fvt_announcement_title_"+d.getTime();
		wspAnnouncementHTML = "wsp_fvt_html_"+d.getTime();				
	
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
		
		/* Click create announcement */
		CreateAnnouncement createAnnouncement = announcementsPage.clickCreateAnnouncement();
	
		/* Fill in the required fields */
		createAnnouncement.enterInternalTitle(wspAnnouncementInternalTitle);
		createAnnouncement.selectTypeByValue("WSP");
		createAnnouncement.enterHTML(wspAnnouncementHTML);	
		createAnnouncement.enterDisplayOrder(wspAnnouncementOrder);		
		
		/* Click save */
		createAnnouncement.clickSave();

		/* Verify the saved successfully message appears and that no validation messages appear */
		Assert.assertTrue(createAnnouncement.isAnnouncementSavedSuccessfully(),"Announcement saved successfully confirmation message failed to display upon saving announcement");
		Assert.assertFalse(createAnnouncement.isValidationMessageDisplayed(),"Validation message displayed in error upon saving announcement");

		/* Click Announcements in the left hand navigation */
		leftnav.clickAnnouncements();
		
		/* Determine which row the announcement is in */
		int row = announcementsPage.getAnnouncementRow(wspAnnouncementInternalTitle);
		
		/* Verify the new Global Community announcement appears in the results grid */
		Assert.assertTrue(announcementsPage.isAnnouncementInternalTitleFound(wspAnnouncementInternalTitle),"New Global Community announcement not found in Global Community announcements grid");	
		//Assert.assertEquals(announcementsPage.getNthAnnouncementStatus(row),activeStatusText);
		Assert.assertEquals(announcementsPage.getNthAnnouncementOrder(row),wspAnnouncementOrder);
	
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the Global Community announcements and verify the new announcement is appearing */
		Assert.assertTrue(h.isGlobalCommunityFound(wspAnnouncementHTML),"New announcement not appearing in Global Community panel on the home page");
	}

	@Test(groups={"BVT","FullRegression"},dependsOnMethods={"CreateAWorldSharedPracticeAnnouncement"}, description="Verifies a World Shared Practice announcement can be deleted from the Global Community panel", alwaysRun = true)
	public void DeleteAWorldSharedPracticeAnnouncement()
	{		
		/* Click Communications - Announcements in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		Announcements announcementsPage = leftnav.clickAnnouncements();
		
		/* Click the Delete link for the announcement */
		announcementsPage.deleteAnnouncement(wspAnnouncementInternalTitle);
		
		/* Verify the saved successfully message appears */
		Assert.assertTrue(announcementsPage.isResultMessageDisplayed());
		
		/* Verify the World Shared Practice announcement is not found in the announcements grid */
		Assert.assertFalse(announcementsPage.isAnnouncementInternalTitleFound(wspAnnouncementInternalTitle));	
		
		/* Go the webUI */
		new Login(getDriver());
		Home h = new Home(getDriver());
		
		/* Loop through the World Shared Practice announcements and verify the announcement is not appearing */
		Assert.assertFalse(h.isGlobalCommunityFound(wspAnnouncementHTML),"Deleted World Shared Practice announcement appearing in the Global Community announcement panel on the home page in error");
	}
}
