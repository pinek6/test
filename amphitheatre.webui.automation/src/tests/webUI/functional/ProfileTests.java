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
import webUI.admin.EditAdditionalInformation;
import webUI.admin.EditContactInformation;
import webUI.admin.EditInterests;
import webUI.admin.EditName;
import webUI.admin.EditPosition;
import webUI.admin.EditProfessionalDetails;
import webUI.admin.EditProfilePhoto;
import webUI.admin.EditResearchClinicalInterests;
import webUI.admin.FailedImageUpload;
import webUI.admin.ImageUploaded;
import webUI.admin.LearnAboutExpertAreas;
import webUI.admin.LearnAboutGroups;
import webUI.admin.MyProfile;
import webUI.admin.Settings;
import webUI.admin.SharedExpertise;
import webUI.admin.SharedOccupation;
import webUI.admin.SharedSpecialty;
import webUI.admin.SharedSubSpecialty;
import webUI.home.groups.DeleteThisGroup;
import webUI.home.groups.GroupsView;
import webUI.home.groups.JoinGroups;
import webUI.home.groups.LeaveGroup;
import webUI.home.groups.MyGroups;
import webUI.home.groups.NewGroup;
import webUI.home.groups.NewGroupReady;
import webUI.login.ChangePassword;
import webUI.login.ChangePasswordSuccess;
import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;


public class ProfileTests extends LoadBrowser {
	
	/* Page Headers */
	private String edit_profile = new LocaleStrings().getString("EDIT_PROFILE");
	private String done_editing = new LocaleStrings().getString("DONE_EDITING");
	private String st_status_1 = new LocaleStrings().getString("ST_STATUS_1");
	private String title_error_message = new LocaleStrings().getString("TITLE_ERROR_MESSAGE");
	private String city_error_message = new LocaleStrings().getString("CITY_ERROR_MESSAGE");
	private String organization_error_message = new LocaleStrings().getString("ORGANIZATION_ERROR_MESSAGE");
	private String specialty_error_message = new LocaleStrings().getString("SPECIALTY_ERROR_MESSAGE");
	private String subspecialty_error_message = new LocaleStrings().getString("SUBSPECIALTY_ERROR_MESSAGE");
	private String invalid_photo_format = new LocaleStrings().getString("INVALID_PHOTO_FORMAT");
	private String large_photo = new LocaleStrings().getString("LARGE_PHOTO");
	private String OTHER_CITY = new LocaleStrings().getString("OTHER_CITY");
	private String title_shared_occupation = new LocaleStrings().getString("TITLE_SHARED_OCCUPATION");
	private String title_shared_specialty = new LocaleStrings().getString("TITLE_SHARED_SPECIALTY");
	private String title_shared_expertise = new LocaleStrings().getString("TITLE_SHARED_EXPERTISE");
	private String empty_groups_message = new LocaleStrings().getString("EMPTY_GROUPS_MESSAGE");
	private String empty_interests_message = new LocaleStrings().getString("EMPTY_INTERESTS_MESSAGE");
	private String change_password_success = new LocaleStrings().getString("CHANGE_PASSWORD_SUCCESS");
	private String password_validation_1 = new LocaleStrings().getString("PASSWORD_VALIDATION_1");
	private String password_validation_2 = new LocaleStrings().getString("PASSWORD_VALIDATION_2");
	private String password_validation_3 = new LocaleStrings().getString("PASSWORD_VALIDATION_3");
	private String password_validation_4 = new LocaleStrings().getString("PASSWORD_VALIDATION_4");
	private String password_validation_5 = new LocaleStrings().getString("PASSWORD_VALIDATION_5");
	private String password_validation_6 = new LocaleStrings().getString("PASSWORD_VALIDATION_6");
	private String password_validation_7 = new LocaleStrings().getString("PASSWORD_VALIDATION_7");
	
	/* Declare some variables for creating new members*/			
	private String email, academicPositionTitle, displayname, password, newfname, newlname, newtitle, background, additional_info, new_email, otherCity, new_otherCity, new_otherOrg, country,
		occupation, specialty, subSpecialty, interest, fname, lname, newGroupName, email1, email2, email3, new_password;
	
	public String createNewMember()
	{		
		logIntoAdminConsole();
		
		/* Set values */
		Date d = new Date();
		email = "fvt"+d.getTime()+"@bluebox.lotus.com";
		displayname = "z_fvt_"+d.getTime();
		fname = "f_fname_"+d.getTime();
		lname = "l_fname_"+d.getTime();  
		password="Pa88w0rdfvt";
		academicPositionTitle = "title_"+d.getTime();
		otherCity = "other_city_"+d.getTime();
		
		/* Click Members - People in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		People peoplePage = leftnav.clickPeople();
				
		/* Click Create Member button */
	    CreateMember createMemberPage = peoplePage.clickCreateMember();       
	    
	    /* Fill in the member details */
	    createMemberPage.enterEmail(email)		
		.enterPassword(password)
		.enterConfirmPassword(password)
		.enterFirstName(fname)
		.enterLastName(lname)
		.enterDisplayName(displayname)
		.enterAcademicPositionTitle(academicPositionTitle)
		.selectYourOccupationByIndex(1)
		.selectPostGraduateExperienceByIndex(1)
		.selectNthSpecialty(2)
		.selectNthSubSpecialty(2)
		.selectNthInterest(1)
		.selectCountryByIndex(1)
		.selectCityByIndex(1)		
		.enterOtherCity(otherCity)
		.selectNthExpertise(1);		
	    
	    /* Store dropdown values */
	    occupation = createMemberPage.getSelectedYourOccupationVisibleText();
	    specialty = createMemberPage.getNthSpecialty(2);
	    subSpecialty = createMemberPage.getNthSubSpecialty(2);
	    interest = createMemberPage.getNthInterest(1);
	    
	    /* Click Save */
		createMemberPage.clickSave();
	    		
		Reporter.log("Member successfully created: " + email);
		
		TopNav topnav = new TopNav(getDriver());
		topnav.clickLogout();
		
		return email;
	}
    
 
	@Test(groups={"BVT","FullRegression","IEOnly"}, description="Verifies the Profile Photo changes successfully")
	public void changeProfilePhoto()
	{
		/* Create member */
		email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
    	navigation.hoverOverAvatar();
    	MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Photo button is not visible");
		
		/* Click the Edit link beside the photo */
		EditProfilePhoto editProfilePhotoPopup = profilePage.clickEditProfilePhoto();
		
		/* Upload new photo */
		long a = profilePage.getProfileImageDisplayedIdentifier();
		editProfilePhotoPopup.enterImage("http://upload.wikimedia.org/wikipedia/commons/thumb/5/51/IBM_logo.svg/200px-IBM_logo.svg.png");
		editProfilePhotoPopup.clickSave();
		ImageUploaded imageUploadedPopup = new ImageUploaded(getDriver());
		
		/* Check confirmation dialog is displayed */
		Assert.assertTrue(imageUploadedPopup.isImageUploadedPopupDisplayed(), "Image Uploaded confirmation dialog is not displayed");
		Assert.assertTrue(imageUploadedPopup.isOkButtonDisplayed(), "OK button is not visible");
		imageUploadedPopup.clickOk();
		
		/* Click Done Editing and check the new photo is uploaded */
		profilePage.clickDoneEditing();
		long b = profilePage.getProfileImageDisplayedIdentifier();
		Assert.assertFalse(a > b, "New profile picture is not visible");
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Profile photo button is visible");
		
		/* Navigate to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertFalse(a > b, "New profile picture is not visible");	
		
	}
	
	@Test(groups={"FullRegression","IEOnly"}, dependsOnMethods = {"changeProfilePhoto"}, description="Verifies it's not possible to upload an invalid photo format")
	public void uploadInvalidProfilePhoto()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
    	navigation.hoverOverAvatar();
    	MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Photo button is not visible");
		
		/* Click the Edit link beside the photo */
		EditProfilePhoto editProfilePhotoPopup = profilePage.clickEditProfilePhoto();
		
		/* Upload invalid photo format */
		long a = profilePage.getProfileImageDisplayedIdentifier();
		editProfilePhotoPopup.enterImage("http://upload.wikimedia.org/wikipedia/commons/5/51/IBM_logo.svg");
		editProfilePhotoPopup.clickSave();
		FailedImageUpload failedImageUploadPopup = new FailedImageUpload(getDriver());

		/* Check failed upload dialog is displayed */
		Assert.assertTrue(failedImageUploadPopup.isFailedImageUploadPopupDisplayed(), "Photo Upload Failed dialog is not displayed");
		Assert.assertTrue(failedImageUploadPopup.isCloseButtonDisplayed(), "Close button is not visible");
		Assert.assertEquals(failedImageUploadPopup.getErrorMessage(), invalid_photo_format);
		failedImageUploadPopup.clickClose();
		
		/* Click Done Editing and check no photo is uploaded */
		profilePage.clickDoneEditing();
		long b = profilePage.getProfileImageDisplayedIdentifier();
		Assert.assertEquals(a, b, "New profile picture uploaded");
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Profile photo button is visible");
	}
	
	@Test(groups={"FullRegression","IEOnly"}, dependsOnMethods = {"uploadInvalidProfilePhoto"}, description="Verifies it's not possible to upload a large photo file")
	public void uploadLargeProfilePhoto()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
    	navigation.hoverOverAvatar();
    	MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Photo button is not visible");
		
		/* Click the Edit link beside the photo */
		EditProfilePhoto editProfilePhotoPopup = profilePage.clickEditProfilePhoto();
		
		/* Upload invalid photo format */
		long a = profilePage.getProfileImageDisplayedIdentifier();
		editProfilePhotoPopup.enterImage("http://upload.wikimedia.org/wikipedia/commons/3/3c/Enrique_Simonet_-_Marina_veneciana_6MB.jpg");
		editProfilePhotoPopup.clickSave();
		FailedImageUpload failedImageUploadPopup = new FailedImageUpload(getDriver());

		/* Check failed upload dialog is displayed */
		Assert.assertTrue(failedImageUploadPopup.isFailedImageUploadPopupDisplayed(), "Photo Upload Failed dialog is not displayed");
		Assert.assertTrue(failedImageUploadPopup.isCloseButtonDisplayed(), "Close button is not visible");
		Assert.assertEquals(failedImageUploadPopup.getErrorMessage(), large_photo);
		failedImageUploadPopup.clickClose();
		
		/* Click Done Editing and check no photo is uploaded */
		profilePage.clickDoneEditing();
		long b = profilePage.getProfileImageDisplayedIdentifier();
		Assert.assertEquals(a, b, "New profile picture uploaded");
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Profile photo button is visible");
	}

	@Test(groups={"BVT","FullRegression"}, description="Verifies the Profile Name changes successfully")
	public void changeName()
	{
		/* Create member */
		email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Set new display, first and last names */
		Date d = new Date();
		newfname = "fvt_newfirst_"+d.getTime();
		newlname = "fvt_newlast_"+d.getTime(); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();	
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		
		/* Click the Edit link */
		EditName editNamePopup = profilePage.clickEditName();
		
		/* Enter new display, first and last names and click the save button */
		editNamePopup.enterFirst(newfname);
		editNamePopup.enterLast(newlname);
		editNamePopup.clickSave();
		
		/* Click Done Editing and check display name is updated */
		profilePage.clickDoneEditing();
		Assert.assertEquals(profilePage.getName(),newfname+" "+newlname);
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditNameLinkDisplayed(),"Edit name link is visible");
		
		/* Navigate to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertEquals(profilePage.getName(),newfname+" "+newlname);
		
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"changeName"}, description="Verifies if it's possible to add a city and an organization that are not listed on the dialog")
	public void changePositionDetailsToOrgNotListed()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Set new Title and Additional Title */
		Date d = new Date();
		newtitle = "fvt_newtitle_"+d.getTime();
		new_otherCity = "fvt_newOtherCity"+d.getTime();
		new_otherOrg = "fvt_newOtherOrg"+d.getTime();
		Random r = new Random(); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		
		/* Click the Edit link */
		EditPosition editCurrentPosition = profilePage.clickEditTitle();
		
		/* Clear the title field and click the save button */
		editCurrentPosition.clearTitle();
		editCurrentPosition.clickSave();
		Assert.assertEquals(editCurrentPosition.getEditPositionDialogErrorMessage(),title_error_message);
		
		/* Enter new Title, Country, City ('Other'), Organization ('Other') and click the save button */
		editCurrentPosition.enterTitle(newtitle);
		int count_countries = editCurrentPosition.getCountOfOptionsInCountryComboBox();
		int r_country = r.nextInt(count_countries);
		country = editCurrentPosition.getTextOfNthOptionInCountryComboBox(r_country);
		editCurrentPosition.selectCountryByVisibleText(country);
		editCurrentPosition.selectCityByVisibleText(OTHER_CITY);
		editCurrentPosition.clickSave();
		Assert.assertEquals(editCurrentPosition.getEditPositionDialogErrorMessage(),city_error_message);
		editCurrentPosition.enterOtherCity(new_otherCity);
		editCurrentPosition.selectOrganizationByVisibleText(OTHER_CITY);	
		editCurrentPosition.scrollDown();
		editCurrentPosition.clickSave();
		Assert.assertEquals(editCurrentPosition.getEditPositionDialogErrorMessage(),organization_error_message);
		editCurrentPosition.enterOtherOrganization(new_otherOrg);
		editCurrentPosition.clickSave();
		
		/* Click Done Editing and check position details are updated */
		profilePage.clickDoneEditing();
		Assert.assertEquals(profilePage.getTitle(),newtitle , "Title entered on the dialog does not match the one on the profile");
		Assert.assertEquals(profilePage.getPositionDetails(),new_otherOrg+ '\n' +new_otherCity+ '\n' +country , "Position details entered on the dialog don't match the ones on the profile");
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditPositionLinkDisplayed(),"Edit position link is visible");
		
		/* Navigate to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertEquals(profilePage.getTitle(),newtitle , "Title entered on the dialog does not match the one on the profile");
		Assert.assertEquals(profilePage.getPositionDetails(),new_otherOrg+ '\n' +new_otherCity+ '\n' +country , "Position details entered on the dialog don't match the ones on the profile");
		  	
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"changePositionDetailsToOrgNotListed"}, description="Verifies the Research / Clinical Interests area is updated")
	public void updateResearchClinicalInterests()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Set new Background information */
		Date d = new Date();
		background = "fvt_background_"+d.getTime(); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditResearchClinicalInterestsLinkDisplayed(),"Edit Research/Clinical Interests link is not visible");
		
		/* Click the Edit link beside the research/clinical interests section */
		EditResearchClinicalInterests editResearchClinicalInterestsPopup = profilePage.clickEditResearchClinicalInterests();
		
		/* Enter new research/clinical interests and click the save button */
		editResearchClinicalInterestsPopup.enterResearchClinicalInterests(background);
		editResearchClinicalInterestsPopup.clickSave();
		profilePage.waitForResearchClinicalInterestsToLoad(background, getDriver());
		
		/* Click Done Editing and check background information is updated */
		profilePage.clickDoneEditing();
		Assert.assertEquals(profilePage.getResearchClinicalInterests(),background, "The text entered on CKEditor does not match the text on profile");
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditResearchClinicalInterestsLinkDisplayed(),"Edit Research/Clinical Interests link is visible");
		
		/* Navigate to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertEquals(profilePage.getResearchClinicalInterests(),background, "The text entered on CKEditor does not match the text on profile");
		  	
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"updateResearchClinicalInterests"}, description="Verifies the Additional Information area is updated")
	public void updateAdditionalInformation()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Set new Background information */
		Date d = new Date();
		additional_info = "fvt_background_"+d.getTime(); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditAdditionalInfoLinkDisplayed(),"Edit Additional Information link is not visible");
		
		/* Click the Edit link beside the additional info section */
		EditAdditionalInformation editAdditionalInformationPopup = profilePage.clickEditAdditionalInformation();
		
		/* Enter new additional info and click the save button */
		editAdditionalInformationPopup.enterAdditionalInfoDetails(additional_info);
		editAdditionalInformationPopup.clickSave();
		profilePage.waitForAdditionalInfoToLoad(additional_info, getDriver());

		/* Click Done Editing and check additional information is updated */
		profilePage.clickDoneEditing();
		Assert.assertEquals(profilePage.getAdditionalInfo(),additional_info, "The text entered on CKEditor does not match the text on profile");
		Assert.assertEquals(profilePage.getEditButtonText(),edit_profile,"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditAdditionalInfoLinkDisplayed(),"Edit Additional Information link is visible");
		
		/* Navigate to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertEquals(profilePage.getAdditionalInfo(),additional_info, "The text entered on CKEditor does not match the text on profile");
		  	
	}

	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"updateAdditionalInformation"}, description="Verifies the Contact Information changes successfully")
	public void changeContactInformation()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Set new email */
		Date d = new Date();
		new_email = "fvt_new_email_"+d.getTime()+"@bluebox.lotus.com"; 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
	
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditContactInfoLinkDisplayed(),"Edit contact info link is not visible");
		
		/* Click the Edit link beside the contact info section */
		EditContactInformation editContactInfoPopup = profilePage.clickEditContactInfo();
		
		/* Check 'email' checkbox is not ticked */
		Assert.assertFalse(editContactInfoPopup.isEmailCheckboxChecked(), "Email checkbox is checked");
		
		/* Select 'email' checkbox and enter new email */
		editContactInfoPopup.checkEmailCheckbox();
		editContactInfoPopup.enterEmailInformation(new_email);
		editContactInfoPopup.clickSave();
		profilePage.waitForSendEmailIconToBeVisible(getDriver());
		
		/* Click Done Editing and check new contact info section is updated */
		profilePage.clickDoneEditing();
		Assert.assertTrue(profilePage.isSendEmailLinkDisplayed());
		Assert.assertEquals(profilePage.getEmailLinkRef(),new_email);
		Assert.assertTrue(profilePage.isChatLinkDisplayed(), "Chat link is not displayed");
		Assert.assertEquals(profilePage.getChatLinkStatus(),st_status_1);
		
		/* Navigate to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertTrue(profilePage.isSendEmailLinkDisplayed());
		Assert.assertEquals(profilePage.getEmailLinkRef(),new_email);
		Assert.assertTrue(profilePage.isChatLinkDisplayed(), "Chat link is not displayed");
		Assert.assertEquals(profilePage.getChatLinkStatus(),st_status_1);
		
		/* Uncheck email checkbox from Edit Contact Information dialog and check the profile is updated */
		profilePage.clickEditProfile();
		profilePage.clickEditContactInfo();
		editContactInfoPopup.uncheckEmailCheckbox();
		editContactInfoPopup.clickSave();
		profilePage.waitForSendEmailIconToBeNotVisible(getDriver());
		profilePage.clickDoneEditing();
		Assert.assertFalse(profilePage.isSendEmailLinkDisplayed(), "Send email link is displayed");
		
		/* Navigate back to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		Assert.assertFalse(profilePage.isSendEmailLinkDisplayed(), "Send email link is displayed");
    	
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"changeContactInformation"}, description="Verifies the Professional Details change successfully")
	public void changeProfessionalDetails()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
	
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfessionalDetailsLinkDisplayed(),"Edit professional details link is not visible");
		
		/* Check the values of occupation. specialty and sub-specialty set on the admin console are displayed on the profile */
		Random r = new Random();
		Assert.assertEquals(profilePage.getOccupation(),occupation, "Occupation set on the admin console does not match the one on profile");
		List<String> specProfile = profilePage.getListOfSpecialty();
		Assert.assertTrue(specProfile.contains(specialty) && specProfile.size()==1,"Specialty set on the admin console is not visible on the profile");
		List<String> subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertTrue(subSpecProfile.contains(subSpecialty) && subSpecProfile.size()==1,"Sub-specialty set on the admin console is not visible on the profile");
		
		/* Click the Edit link beside the professional details section */
		EditProfessionalDetails editProfessionalDetailsPopup = profilePage.clickEditProfessionalDetails();
		
		/* Check occupation set on admin console is displayed on the dialog and select a different occupation from the dropdown */
		Assert.assertEquals(editProfessionalDetailsPopup.getSelectedTextOfOccupationDropdown(),occupation, "Occupation set on the admin console does not match the one on the dialog");
		int count_occupations = editProfessionalDetailsPopup.getCountOfOptionsInOccupationDropdown();
		int r_occupation = r.nextInt(count_occupations-1)+2;
		editProfessionalDetailsPopup.selectOccupationFromDropdownByIndex(r_occupation);
		String new_occupation = editProfessionalDetailsPopup.getSelectedTextOfOccupationDropdown();
		
		/* Check specialty set on admin console is displayed on the dialog and select a different specialty from the dropdown */
		List<String> specDialog = editProfessionalDetailsPopup.getListOfSelectedSpecialtyOptions();
		Assert.assertTrue(specDialog.contains(specialty) && specDialog.size()==1,"Specialty set on the admin console is not visible on the dialog");
		int count_specialties = editProfessionalDetailsPopup.getCountOfOptionsInSpecialtyDropdown();
		int r_specialty = r.nextInt(count_specialties-1)+2;
		editProfessionalDetailsPopup.selectSpecialtyFromDropdownByIndex(r_specialty);
		editProfessionalDetailsPopup.clickAddSpecialty();
		Assert.assertTrue(editProfessionalDetailsPopup.getCountOfSelectedSpecialtyOptions() == 2);
		
		/* Check sub-specialty set on admin console is displayed on the dialog and select a different sub-specialty from the dropdown */
		List<String> subSpecDialog = editProfessionalDetailsPopup.getListOfSelectedSubSpecialtyOptions();
		Assert.assertTrue(subSpecDialog.contains(subSpecialty) && subSpecDialog.size()==1,"Sub-specialty set on the admin console is not visible on the dialog");
		int count_subspecialties = editProfessionalDetailsPopup.getCountOfOptionsInSubSpecialtyDropdown();
		int r_subspecialty = r.nextInt(count_subspecialties-1)+2;
		editProfessionalDetailsPopup.selectSubSpecialtyFromDropdownByIndex(r_subspecialty);
		editProfessionalDetailsPopup.clickAddSubSpecialty();
		Assert.assertTrue(editProfessionalDetailsPopup.getCountOfSelectedSubSpecialtyOptions() == 2);
		
		/* Click Cancel and check the occupation, specialty and sub-specialty added are not displayed on the profile */
		editProfessionalDetailsPopup.clickCancel();
		Assert.assertEquals(profilePage.getOccupation(), occupation, "Occupation set on the admin console does not match the one on profile");
		specProfile = profilePage.getListOfSpecialty();
		Assert.assertTrue(specProfile.contains(specialty) && specDialog.size()==1,"Specialty set on the admin console is not visible on the profile");
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertTrue(subSpecProfile.contains(subSpecialty) && subSpecDialog.size()==1,"Sub-specialty set on the admin console is not visible on the profile");
		
		/* Open again the Edit Professional Details dialog and click Save */
		profilePage.clickEditProfessionalDetails();
		specDialog = editProfessionalDetailsPopup.getListOfSelectedSpecialtyOptions();
		subSpecDialog = editProfessionalDetailsPopup.getListOfSelectedSubSpecialtyOptions();
		editProfessionalDetailsPopup.clickSave();
		specProfile = profilePage.getListOfSpecialty();
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertEquals(profilePage.getOccupation(),new_occupation, "Occupation set on the dialog does not match the one on profile");
		Assert.assertEquals(specProfile, specDialog, "The specialty added on the dialog is not displayed on the profile");
		Assert.assertEquals(subSpecProfile, subSpecDialog, "The sub-specialty added on the dialog is not displayed on the profile");
		
		/* Navigate back to Home */
		profilePage.clickDoneEditing();
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		specProfile = profilePage.getListOfSpecialty();
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertEquals(profilePage.getOccupation(),new_occupation, "Occupation set on the dialog does not match the one on profile");
		Assert.assertEquals(specProfile, specDialog, "The specialty added on the dialog is not displayed on the profile");
		Assert.assertEquals(subSpecProfile, subSpecDialog, "The sub-specialty added on the dialog is not displayed on the profile");
		
		/* Edit the professional details again and remove one specialties and one sub-specialties */
		profilePage.clickEditProfile();
		profilePage.clickEditProfessionalDetails();
		editProfessionalDetailsPopup.removeNthSpecialty(2);
		specDialog = editProfessionalDetailsPopup.getListOfSelectedSpecialtyOptions();
		Assert.assertTrue(specDialog.contains(specialty) && specDialog.size()==1,"Specialty was not removed from the dialog");
		editProfessionalDetailsPopup.removeNthSubSpecialty(2);
		subSpecDialog = editProfessionalDetailsPopup.getListOfSelectedSubSpecialtyOptions();
		Assert.assertTrue(subSpecDialog.contains(subSpecialty) && subSpecDialog.size()==1,"Sub-specialty was not removed from the dialog");
		
		editProfessionalDetailsPopup.clickSave();
		profilePage.clickDoneEditing();
		Assert.assertEquals(profilePage.getOccupation(),new_occupation,"Occupation set on the dialog does not match the one on profile");
		specProfile = profilePage.getListOfSpecialty();
		Assert.assertTrue(specProfile.contains(specialty) && specProfile.size()==1,"The specialty removed on the dialog is still displayed on the profile");
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertTrue(subSpecProfile.contains(subSpecialty) && subSpecProfile.size()==1,"The sub-specialty removed on the dialog is still displayed on the profile");
		
		/* Navigate back to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		specProfile = profilePage.getListOfSpecialty();
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertEquals(profilePage.getOccupation(),new_occupation, "Occupation set on the dialog does not match the one on profile");
		specProfile = profilePage.getListOfSpecialty();
		Assert.assertTrue(specProfile.contains(specialty) && specProfile.size()==1,"The specialty removed on the dialog is still displayed on the profile");
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertTrue(subSpecProfile.contains(subSpecialty) && subSpecProfile.size()==1,"The sub-specialty removed on the dialog is still displayed on the profile");
		
		/* Remove the last specialty and sub-specialty on the profile and check error messages */
		profilePage.clickEditProfile();
		profilePage.clickEditProfessionalDetails();
		editProfessionalDetailsPopup.removeNthSpecialty(1);
		editProfessionalDetailsPopup.clickSave();
		Assert.assertEquals(editProfessionalDetailsPopup.getEditProfessionalDetailsDialogErrorMessage(),specialty_error_message);
		editProfessionalDetailsPopup.removeNthSubSpecialty(1);
		editProfessionalDetailsPopup.clickSave();
		Assert.assertEquals(editProfessionalDetailsPopup.getEditProfessionalDetailsDialogErrorMessage(),subspecialty_error_message);
		
		/* Select cancel on the dialog and check no changes are made on the profile */
		editProfessionalDetailsPopup.clickCancel();
		profilePage.clickDoneEditing();
		Assert.assertEquals(profilePage.getOccupation(),new_occupation);
		specProfile = profilePage.getListOfSpecialty();
		Assert.assertTrue(specProfile.contains(specialty) && specProfile.size()==1);
		subSpecProfile = profilePage.getListOfSubSpecialty();
		Assert.assertTrue(subSpecProfile.contains(subSpecialty) && subSpecProfile.size()==1);
		
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"changeProfessionalDetails"}, description="Verifies the Learn About Expert Areas popup loads successfully")
	public void learnAboutExpertAreasPopupLoads()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();	
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		
		/* Click the info link */
		LearnAboutExpertAreas learnAboutExpertAreasPopup = profilePage.clickInfoExpertiseArea();
		Assert.assertTrue(learnAboutExpertAreasPopup.isLearnAboutExpertAreasPopupDisplayed(),"Learn About Expert Areas Popup failed to display");
		
		/* Check ok and close button are displayed */
		Assert.assertTrue(learnAboutExpertAreasPopup.isOkButtonDisplayed(),"Ok button failed to display");
		Assert.assertTrue(learnAboutExpertAreasPopup.isCloseButtonDisplayed(),"Close button failed to display");
		
		/* Check the expertise list displayed on the profile matches the list on the dialog */
		List<String> expertise_profile = profilePage.getListOfExpertise();
		List<String> expertise_dialog = learnAboutExpertAreasPopup.getListOfExpertise();
		Assert.assertTrue(expertise_profile.equals(expertise_dialog), "The list of expertise on the profile does not match the list on the dialog");
		
		/* Click ok and close the dialog */
		learnAboutExpertAreasPopup.clickOk();
		profilePage.clickDoneEditing();
		
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"learnAboutExpertAreasPopupLoads"}, description="Verifies the Learn About Groups popup loads successfully")
	public void learnAboutGroupsPopupLoads()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();	
		
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		
		/* Click the info link */
		LearnAboutGroups learnAboutGroupsPopup = profilePage.clickInfoGroups();
		Assert.assertTrue(learnAboutGroupsPopup.isLearnAboutGroupsPopupDisplayed(),"Learn About Groups Popup failed to display");
		
		/* Check ok and close button are displayed */
		Assert.assertTrue(learnAboutGroupsPopup.isOkButtonDisplayed(),"Ok button failed to display");
		Assert.assertTrue(learnAboutGroupsPopup.isCloseButtonDisplayed(),"Close button failed to display");
		
		/* Click ok and close the dialog */
		learnAboutGroupsPopup.clickOk();
		profilePage.clickDoneEditing();
		
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Shared Expertise popup loads successfully and selects a user with a shared expertise")
	public void sharedExpertisePopupLoads()
	{
		/* Create two members */
		email1 = createNewMember();
		email2 = createNewMember();
		getDriverAndLogin(email2,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());	
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();	
				
		/* Click on the first expertise and check the shared expertise dialog is displayed */
		SharedExpertise sharedExpertisePopup = profilePage.clickNthExpertise(1);
		Assert.assertTrue(sharedExpertisePopup.isSharedExpertisePopupDisplayed(),"Shared Expertise Popup failed to display");
		
		/* Check title of the dialog */
		String myProfileExpertise = profilePage.getNthExpertise(1);		
		String expertiseDialogTitle = sharedExpertisePopup.getTitle();
		Assert.assertEquals(title_shared_expertise + " " + myProfileExpertise, expertiseDialogTitle, "Title displayed is not correct");	
		
		/* Check close and ok buttons are displayed */
		Assert.assertTrue(sharedExpertisePopup.isOkButtonDisplayed(),"Ok button failed to display");
		Assert.assertTrue(sharedExpertisePopup.isClosePopupButtonDisplayed(),"Close popup button failed to display");
		
		/* Click the ok button */
		sharedExpertisePopup.clickOkButton();
		
		/* Click again on the first expertise displayed on the profile */
		profilePage.clickNthExpertise(1);
		
		/* Check the counter is displayed */
		Assert.assertTrue(sharedExpertisePopup.isCounterDisplayed(), "Counter is not visible");
		
		/* Check if there is more than five members that share the same expertise and select the first member on the dialog */
		int membersExp = sharedExpertisePopup.getTotalMembers();
		String firstMemberNameExp = "";
		Boolean emailLinkDisplayed = null;
		if (membersExp > 5) {
			Assert.assertTrue(sharedExpertisePopup.isNext5LinkDisplayed(),"Next5 link not visible");
			sharedExpertisePopup.scrollToNext5Link();
			sharedExpertisePopup.clickNext5Link();
			firstMemberNameExp = sharedExpertisePopup.getNthMemberName(1);
			emailLinkDisplayed = sharedExpertisePopup.isEmailIconDisplayedNthExpert(1);
			sharedExpertisePopup.clickNthMemberName(1);
		}
		else {
			Assert.assertFalse(sharedExpertisePopup.isNext5LinkDisplayed(),"Next5 link is visible");
			firstMemberNameExp = sharedExpertisePopup.getNthMemberName(1);
			emailLinkDisplayed = sharedExpertisePopup.isEmailIconDisplayedNthExpert(1);
			sharedExpertisePopup.clickNthMemberName(1);
		}
		
		/* Check the selected member displays the same specialty on profile */
		Assert.assertTrue(profilePage.getListOfExpertise().contains(myProfileExpertise), "The selected member does not have the same specialty displayed on profile");
		
		/* Check the profile page displayed corresponds to the member selected */
		Assert.assertEquals(firstMemberNameExp,profilePage.getName() , "Profile page displayed does not match the user selected on the shared specialty dialog");
		
		/* Check the email link is only displayed on the dialog if the user made his email address public on the profile page */	
		Assert.assertEquals(emailLinkDisplayed,profilePage.isSendEmailLinkDisplayed() , "Email privacy settings on profile do not match the ones on the shared expertise dialog");
		
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"sharedExpertisePopupLoads"}, description="Verifies the Shared Occupation/Specialty/Sub-Specialty popups load successfully and selects a user with a shared Occupation/Specialty/Sub-Specialty")
	public void sharedOccupationSpecialtyPopupLoads()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		
		/* OCCUPATION */
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();	
		
		/* Click on the first occupation and check the shared occupation dialog is displayed */
		SharedOccupation sharedOccupationPopup = profilePage.clickOccupation();
		Assert.assertTrue(sharedOccupationPopup.isSharedOccupationPopupDisplayed(),"Shared Occupation Popup failed to display");
		
		/* Check title of the dialog */
		String myProfileOccupation = profilePage.getOccupation();
		String occupationDialogTitle = sharedOccupationPopup.getTitle();
		Assert.assertEquals(title_shared_occupation + " " + myProfileOccupation, occupationDialogTitle, "Title displayed is not correct");

		/* Check the close and ok buttons are visible */
		Assert.assertTrue(sharedOccupationPopup.isClosePopupButtonDisplayed(),"Close button not visible");
		Assert.assertTrue(sharedOccupationPopup.isOkButtonDisplayed(),"OK button not visible");
		
		/* Click the ok button */
		sharedOccupationPopup.clickOk();
		
		/* Click again on the occupation displayed on the profile */
		profilePage.clickOccupation();
		
		/* Check the counter is displayed */
		Assert.assertTrue(sharedOccupationPopup.isCounterDisplayed(), "Counter is not visible");
		
		/* Check if there is more than five members that share the same occupation and select the first member on the dialog */
		int membersOccup = sharedOccupationPopup.getTotalMembers();
		String firstMemberNameOccup = "";
		if (membersOccup > 5) {
			Assert.assertTrue(sharedOccupationPopup.isNext5LinkDisplayed(),"Next5 link not visible");
			sharedOccupationPopup.clickNext5Link();
			firstMemberNameOccup = sharedOccupationPopup.getNthMemberName(1);
			sharedOccupationPopup.clickNthMemberName(1);
		}
		else {
			Assert.assertFalse(sharedOccupationPopup.isNext5LinkDisplayed(),"Next5 link is visible");
			firstMemberNameOccup = sharedOccupationPopup.getNthMemberName(1);
			sharedOccupationPopup.clickNthMemberName(1);
		}
		
		/* Check the selected member displays the same occupation on profile */
		Assert.assertEquals(myProfileOccupation, profilePage.getOccupation(), "The selected member does not have the same occupation displayed on profile");
		
		/* Check the profile page displayed corresponds to the member selected */
		Assert.assertEquals(firstMemberNameOccup,profilePage.getName() , "Profile page displayed does not match the user selected on the shared occupation dialog");
		
		
		/* SPECIALTY */
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		profilePage = navigation.clickMyProfile();
		
		/* Click on the first specialty and check the shared specialty dialog is displayed */
		SharedSpecialty sharedSpecialtyPopup = profilePage.clickNthSpecialty(1);
		Assert.assertTrue(sharedSpecialtyPopup.isSharedSpecialtyPopupDisplayed(),"Shared Specialty Popup failed to display");
		
		/* Check title of the dialog */
		String myProfileSpecialty = profilePage.getNthSpecialty(1);
		String specialtyDialogTitle = sharedSpecialtyPopup.getTitle();
		Assert.assertEquals(title_shared_specialty + " " + myProfileSpecialty, specialtyDialogTitle, "Title displayed is not correct");
		
		/* Check the close and ok buttons are visible */
		Assert.assertTrue(sharedSpecialtyPopup.isClosePopupButtonDisplayed(),"Close button not visible");
		Assert.assertTrue(sharedSpecialtyPopup.isOkButtonDisplayed(),"OK button not visible");
		
		/* Click the ok button */
		sharedSpecialtyPopup.clickOk();
		
		/* Click again on the first specialty displayed on the profile */
		profilePage.clickNthSpecialty(1);
		
		/* Check the counter is displayed */
		Assert.assertTrue(sharedSpecialtyPopup.isCounterDisplayed(), "Counter is not visible");
		
		/* Check if there is more than five members that share the same specialty and select the first member on the dialog */
		int membersSpec = sharedSpecialtyPopup.getTotalMembers();
		String firstMemberNameSpec = "";
		if (membersSpec > 5) {
			Assert.assertTrue(sharedSpecialtyPopup.isNext5LinkDisplayed(),"Next5 link not visible");
			sharedSpecialtyPopup.clickNext5Link();
			firstMemberNameSpec = sharedSpecialtyPopup.getNthMemberName(1);
			sharedSpecialtyPopup.clickNthMemberName(1);
		}
		else {
			Assert.assertFalse(sharedSpecialtyPopup.isNext5LinkDisplayed(),"Next5 link is visible");
			firstMemberNameSpec = sharedSpecialtyPopup.getNthMemberName(1);
			sharedSpecialtyPopup.clickNthMemberName(1);
		}
		
		/* Check the selected member displays the same specialty on profile */
		Assert.assertTrue(profilePage.getListOfSpecialty().contains(myProfileSpecialty), "The selected member does not have the same specialty displayed on profile");
		
		/* Check the profile page displayed corresponds to the member selected */
		Assert.assertEquals(firstMemberNameSpec,profilePage.getName() , "Profile page displayed does not match the user selected on the shared specialty dialog");
		
		
		/* SUB-SPECIALTY */
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		profilePage = navigation.clickMyProfile();
		
		/* Click on the first sub-specialty and check the shared sub-specialty dialog is displayed */
		SharedSubSpecialty sharedSubSpecialtyPopup = profilePage.clickNthSubSpecialty(1);
		Assert.assertTrue(sharedSubSpecialtyPopup.isSharedSubSpecialtyPopupDisplayed(),"Shared Sub-Specialty Popup failed to display");
		
		/* Check title of the dialog */
		String myProfileSubSpecialty = profilePage.getNthSubSpecialty(1);
		String subSpecialtyDialogTitle = sharedSubSpecialtyPopup.getTitle();
		Assert.assertEquals(title_shared_specialty + " " + myProfileSubSpecialty, subSpecialtyDialogTitle, "Title displayed is not correct");
		
		/* Check the close and ok buttons are visible */
		Assert.assertTrue(sharedSubSpecialtyPopup.isClosePopupButtonDisplayed(),"Close button not visible");
		Assert.assertTrue(sharedSubSpecialtyPopup.isOkButtonDisplayed(),"OK button not visible");
		
		/* Click the ok button */
		sharedSubSpecialtyPopup.clickOk();
		
		/* Click again on the sub-specialty displayed on the profile */
		profilePage.clickNthSubSpecialty(1);
		
		/* Check the counter is displayed */
		Assert.assertTrue(sharedSubSpecialtyPopup.isCounterDisplayed(), "Counter is not visible");
		
		/* Check if there is more than five members that share the same sub-specialty and select the first member on the dialog */
		int membersSubSpec = sharedSubSpecialtyPopup.getTotalMembers();
		String firstMemberNameSubSpec = "";
		if (membersSubSpec > 5) {
			Assert.assertTrue(sharedSubSpecialtyPopup.isNext5LinkDisplayed(),"Next5 link not visible");
			sharedSubSpecialtyPopup.clickNext5Link();
			firstMemberNameSubSpec = sharedSubSpecialtyPopup.getNthMemberName(1);
			sharedSubSpecialtyPopup.clickNthMemberName(1);
		}
		else {
			Assert.assertFalse(sharedSubSpecialtyPopup.isNext5LinkDisplayed(),"Next5 link is visible");
			firstMemberNameSubSpec = sharedSubSpecialtyPopup.getNthMemberName(1);
			sharedSubSpecialtyPopup.clickNthMemberName(1);
		}
		
		/* Check the selected member displays the same sub-specialty on profile */
		Assert.assertTrue(profilePage.getListOfSubSpecialty().contains(myProfileSubSpecialty), "The selected member does not have the same sub-specialty displayed on profile");
		
		/* Check the profile page displayed corresponds to the member selected */
		Assert.assertEquals(firstMemberNameSubSpec, profilePage.getName(), "Profile page displayed does not match the user selected on the shared sub-specialty dialog");
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"sharedOccupationSpecialtyPopupLoads"}, description="Verifies the impact on Profile page of joining/leaving groups")
	public void joinAndLeaveGroups()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Set new group name */
		Date d = new Date();
		newGroupName = "TestGroup_"+d.getTime();
		String groupName = "";
		
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
		
		/* Join the first group, if at least one group exists */
		if (groups_displayed > 0) {
			groupName = joinGroupsPage.getNthGroupTitle(1);
			joinGroupsPage.clickNthGroupJoin(1);
		}
		
		/* Create new group, if there's no group */
		else {
			MyGroups myGroupsPage = joinGroupsPage.clickMyGroupsTab();
			NewGroup newGroupPage = myGroupsPage.clickNewGroup();
			newGroupPage.enterName(newGroupName);
			newGroupPage.selectGroupTypeByIndex(1);
			newGroupPage.selectPublicAccessRadiobutton();
			NewGroupReady newGroupReadyDialog = newGroupPage.clickSaveButton();
			GroupsView groupViewPage = newGroupReadyDialog.clickGetStarted();
			groupViewPage.clickEditGroup();
		}
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Check if the joined group is displayed on the profile page */
		List<String> listGroups = profilePage.getListOfGroups();
		Assert.assertTrue(listGroups.contains(groupName) || listGroups.contains(newGroupName) , "Joined group not displayed on Profile");
		
		/* Click on the group */
		GroupsView groupsViewPage = profilePage.clickNthGroup(1);
		
		/* Leave group */
		if (groups_displayed > 0) {
			LeaveGroup leaveGroupDialog = groupsViewPage.clickLeave();
			MyGroups myGroupsPage = leaveGroupDialog.clickLeaveButton();
			Assert.assertFalse(myGroupsPage.isNthGroupTitleDisplayed(1) , "Group is still visible on MyGroups page");
		}
		
		else {
			DeleteThisGroup deleteGroupDialog = groupsViewPage.clickDelete();
			deleteGroupDialog.selectConfirmCheckbox();
			MyGroups myGroupsPage = deleteGroupDialog.clickPermanentlyDeleteGroup();
			Assert.assertFalse(myGroupsPage.isNthGroupTitleDisplayed(1) , "Group is still visible on MyGroups page");
		}
		
		/* Navigate to MyProfile */
		navigation.hoverOverAvatar();
		profilePage = navigation.clickMyProfile();
		
		/* Check the list of groups is empty */
		listGroups = profilePage.getListOfGroups();
		Assert.assertFalse(listGroups.contains(groupName) && listGroups.contains(newGroupName) , "Group still visible on Profile page");
		Assert.assertEquals(profilePage.getEmptyGroupsMessage(), empty_groups_message);
	}
	
	@Test(groups={"BVT","FullRegression"}, dependsOnMethods = {"joinAndLeaveGroups"}, description="Verifies the impact on Profile page of adding/removing interests")
	public void addAndRemoveInterests()
	{
		/* Create member */
		//email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
	
		/* Click Edit Profile */
		profilePage.clickEditProfile();
		Assert.assertEquals(profilePage.getEditButtonText(),done_editing,"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfessionalDetailsLinkDisplayed(),"Edit professional details link is not visible");
		
		/* Check the interests set on the admin console are displayed on the profile */
		Random r = new Random();
		List<String> interestsProfile = profilePage.getListOfInterests();
		Assert.assertTrue(interestsProfile.contains(interest) && interestsProfile.size()==1,"Interest set on the admin console is not visible on the profile");
		
		/* Click the Edit link beside the interests section */
		EditInterests editInterestsPopup = profilePage.clickEditInterests();
		
		/* Check interest set on admin console is displayed on the dialog and select a different interest from the dropdown */
		List<String> interestsDialog = editInterestsPopup.getListOfSelectedOptions();
		Assert.assertTrue(interestsDialog.contains(interest) && interestsDialog.size()==1,"Interest set on the admin console is not visible on the dialog");
		int count_interests = editInterestsPopup.getCountOfOptionsInInterestsDropdown();
		int r_interest = r.nextInt(count_interests-1)+2;
		editInterestsPopup.selectInterestsFromDropdownByIndex(r_interest);
		editInterestsPopup.clickAddInterests();
		Assert.assertEquals(editInterestsPopup.getCountOfSelectedOptions() , 2);
		
		/* Click Cancel and check the interest added is not displayed on the profile */
		editInterestsPopup.clickCancel();
		interestsProfile = profilePage.getListOfInterests();
		Assert.assertTrue(interestsProfile.contains(interest) && interestsProfile.size()==1,"Interest set on the admin console is not visible on the profile");
		
		/* Open again the Edit Interests dialog and click Save */
		profilePage.clickEditInterests();
		interestsDialog = editInterestsPopup.getListOfSelectedOptions();
		editInterestsPopup.clickSave();
		interestsProfile = profilePage.getListOfInterests();
		Assert.assertEquals(interestsProfile, interestsDialog, "The interest added on the dialog is not displayed on the profile");
		
		/* Navigate back to Home */
		profilePage.clickDoneEditing();
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		interestsProfile = profilePage.getListOfInterests();
		Assert.assertEquals(interestsProfile, interestsDialog, "The interest added on the dialog is not displayed on the profile");
		
		/* Edit interests again by removing one interest */
		profilePage.clickEditProfile();
		profilePage.clickEditInterests();
		editInterestsPopup.removeNthInterests(2);
		interestsDialog = editInterestsPopup.getListOfSelectedOptions();
		Assert.assertTrue(interestsDialog.contains(interest) && interestsDialog.size()==1,"Interest was not removed from the dialog");
				
		editInterestsPopup.clickSave();
		profilePage.clickDoneEditing();
		interestsProfile = profilePage.getListOfInterests();
		Assert.assertTrue(interestsProfile.contains(interest) && interestsProfile.size()==1,"The interest removed on the dialog is still displayed on the profile");
		
		/* Navigate back to Home */
		navigation.hoverOverHome();
		navigation.clickHome();
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Navigate back to My Profile and check profile is updated accordingly */
		navigation.hoverOverAvatar();
		navigation.clickMyProfile();
		interestsProfile = profilePage.getListOfInterests();
		Assert.assertTrue(interestsProfile.contains(interest) && interestsProfile.size()==1,"The interest removed on the dialog is still displayed on the profile");
		
		/* Remove the last interest on the profile */
		profilePage.clickEditProfile();
		profilePage.clickEditInterests();
		editInterestsPopup.removeNthInterests(1);
		editInterestsPopup.clickSave();
		Assert.assertEquals(profilePage.getEmptyInterestsMessage(), empty_interests_message);
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the current password of the user is changed successfully.")
	public void changePassword()
	{
		/* Create member */
		email3 = createNewMember();
		getDriverAndLogin(email3,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click Settings */
		navigation.hoverOverAvatar();
		Settings settingsPage = navigation.clickSettings();
		
		/* Click the change password button */
		ChangePassword changePasswordDialog = settingsPage.clickChangePassword();
		
		/* Enter new password and click cancel */
		new_password = "Passw0rd";
		changePasswordDialog.enterCurrentPassword(password);
		changePasswordDialog.enterNewPassword(new_password);
		changePasswordDialog.enterConfirmPassword(new_password);
		changePasswordDialog.clickCancel();
		
		/* Enter new password and click save */
		settingsPage.clickChangePassword();
		changePasswordDialog.enterCurrentPassword(password);
		changePasswordDialog.enterNewPassword(new_password);
		changePasswordDialog.enterConfirmPassword(new_password);
		ChangePasswordSuccess changePasswordSuccessDialog = changePasswordDialog.clickSave();
		
		/* Check confirmation message and click ok */
		Assert.assertTrue(changePasswordDialog.isChangePasswordSuccessPopupDisplayed(),"Change Password Success dialog was not displayed");
		Assert.assertEquals(changePasswordSuccessDialog.getChangePasswordSuccessMessage(), change_password_success, "Confirmation message was not displayed");
		Assert.assertTrue(changePasswordSuccessDialog.isOkButtonDisplayed(),"Ok button was not displayed");
		changePasswordSuccessDialog.clickOk();
		
		/* Logout  */
		navigation.hoverOverAvatar();
		navigation.clickLogout();
		
		/* Login with the new password */
		getDriverAndLogin(email3,new_password);
		
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the current password of the user is not changed to an invalid password and checks the different password validations.")
	public void changeInvalidPassword()
	{
		/* Create member */
		email1 = createNewMember();
		getDriverAndLogin(email1,password);
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
		
		/* Hover over avatar and click Settings */
		navigation.hoverOverAvatar();
		Settings settingsPage = navigation.clickSettings();
		
		/* Click the change password button */
		ChangePassword changePasswordDialog = settingsPage.clickChangePassword();
		
		/* Enter current password and click save */
		changePasswordDialog.enterCurrentPassword(password);
		changePasswordDialog.clickSave();
		
		/* Check password validations are displayed on the dialog */
		List<String> valPassList = changePasswordDialog.getListOfPasswordValidations();
		Assert.assertEquals(valPassList.size(), 4);
		Assert.assertTrue(valPassList.contains(password_validation_1), "Expected: " + password_validation_1 + " to be found in list of password validations but found: " + valPassList);
		Assert.assertTrue(valPassList.contains(password_validation_2), "Expected: " + password_validation_2 + " to be found in list of password validations but found: " + valPassList);
		Assert.assertTrue(valPassList.contains(password_validation_4), "Expected: " + password_validation_4 + " to be found in list of password validations but found: " + valPassList);
		Assert.assertTrue(valPassList.contains(password_validation_5), "Expected: " + password_validation_5 + " to be found in list of password validations but found: " + valPassList);
		
		/* Enter an incorrect current password and a valid new password */		
		changePasswordDialog.enterCurrentPassword("P1234");
		changePasswordDialog.enterNewPassword("Passw0rd");
		changePasswordDialog.enterConfirmPassword("Passw0rd");
		changePasswordDialog.clickSave();

		/* Check password validation is displayed on the dialog */
		valPassList = changePasswordDialog.getListOfPasswordValidations();
		Assert.assertEquals(valPassList.size(), 1);
		Assert.assertTrue(valPassList.contains(password_validation_7), "Expected: " + password_validation_7 + " but found: " + valPassList);
		changePasswordDialog.enterCurrentPassword("");
		
		/* Enter new invalid password */
		changePasswordDialog.enterNewPassword("P");
		changePasswordDialog.enterConfirmPassword("P");
		changePasswordDialog.clickSave();
		
		/* Check password validations are displayed on the dialog */
		valPassList = changePasswordDialog.getListOfPasswordValidations();
		Assert.assertEquals(valPassList.size(), 2);
		Assert.assertTrue(valPassList.contains(password_validation_1), "Expected: " + password_validation_1 + " to be found in list of password validations but found: " + valPassList);
		Assert.assertTrue(valPassList.contains(password_validation_4), "Expected: " + password_validation_4 + " to be found in list of password validations but found: " + valPassList);
		
		/* Enter new invalid password */
		changePasswordDialog.enterNewPassword("Passw0");
		changePasswordDialog.enterConfirmPassword("Passw0");
		changePasswordDialog.clickSave();
		
		/* Check password validation is displayed on the dialog */
		valPassList = changePasswordDialog.getListOfPasswordValidations();
		Assert.assertEquals(valPassList.size(), 1);
		Assert.assertTrue(valPassList.contains(password_validation_4), "Expected: " + password_validation_4 + " but found: " + valPassList);		
			
		/* Enter new valid password and confirm with a different password */
		changePasswordDialog.enterNewPassword("Passw0rd");
		changePasswordDialog.enterConfirmPassword("P");
		changePasswordDialog.clickSave();
		
		/* Check password validation is displayed on the dialog */
		valPassList = changePasswordDialog.getListOfPasswordValidations();
		Assert.assertEquals(valPassList.size(), 1);
		Assert.assertTrue(valPassList.contains(password_validation_6), "Expected: " + password_validation_6 + " but found: " + valPassList);				
		
		changePasswordDialog.clickCancel();
		
	}
	
}
