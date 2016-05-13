package tests.webUI.nonFunctional;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
import webUI.admin.MyProfile;
import webUI.admin.Settings;
import webUI.login.ChangePassword;


public class ProfileTests extends LoadBrowser {
	
	/* Page Headers */
	private String edit_profile = new LocaleStrings().getString("EDIT_PROFILE");
	private String done_editing = new LocaleStrings().getString("DONE_EDITING");


	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod(){
		/* Login to webUI with the new member */
		getDriverAndLogin();
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Verify the search/avatar links appear */
		Assert.assertTrue(navigation.isSearchDisplayed()); 
		Assert.assertTrue(navigation.isAvatarDisplayed());
	}

    @Test(groups={"BVT","FullRegression"}, description="Verifies the My Profile page loads successfully") 
	public void myProfilePageLoads()
	{	
		
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Confirm the profile photo loads */
		Assert.assertTrue(profilePage.profilePhotoLoads());
		
		/* Confirm My Profile tab is selected */
		Assert.assertTrue(profilePage.isMyProfileTabSelected(),"My Profile tab should be selected");
		
		/* Confirm Settings tab is not selected */
		Assert.assertFalse(profilePage.isSettingsTabSelected(),"Settings tab should not be selected");
		
		/* Confirm edit profile button is visible */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(),"Edit Profile button should be visible");
		Assert.assertTrue((profilePage.getEditButtonText()).equals("edit profile"),"Label of the Edit Profile button should be "+edit_profile);
		
	}
    
    @Test(groups={"BVT","FullRegression"}, description="Verifies the Settings page loads successfully") 
	public void settingsPageLoads()
	{
    	
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());        	
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();	
		
		/* Click Settings */
		Settings settingsPage = navigation.clickSettings();
		
		/* Confirm UI elements appear */
		Assert.assertTrue(settingsPage.isChangePasswordButtonDisplayed(),"Change Password button failed to display");
		Assert.assertFalse(settingsPage.isMyProfileTabSelected(),"My Profile tab should not be selected");
		Assert.assertTrue(settingsPage.isSettingsTabSelected(),"Settings tab should be selected");
		Assert.assertTrue(settingsPage.isNotificationsCheckboxDisplayed(),"Notifications checkbox is not displayed");
	}
    
    @Test(groups={"BVT","FullRegression"}, description="Verifies the Change Password popup loads successfully from the Settings page") 
	public void changePasswordPopupLoads()
	{	
    	
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver());        	
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();

		/* Click Settings  */
		Settings settingsPage = navigation.clickSettings();
		
		/* Click Change Password */
		ChangePassword changePasswordPopup = settingsPage.clickChangePassword();		
		
		/* Verify the Change Password popup is displayed */
		Assert.assertTrue(settingsPage.isChangePasswordPopupDisplayed(),"Change Password popup failed to load");
		
		/* Verify UI Elements appear */
		Assert.assertTrue(changePasswordPopup.isCurrentPasswordFieldDisplayed(),"Current Password field failed to display");
		Assert.assertTrue(changePasswordPopup.isNewPasswordFieldDisplayed(),"New Password field failed to display");
		Assert.assertTrue(changePasswordPopup.isConfirmPasswordFieldDisplayed(),"Confirm Password field failed to display");
		Assert.assertTrue(changePasswordPopup.isSaveButtonDisplayed(),"Save Button failed to display");
		Assert.assertTrue(changePasswordPopup.isCancelButtonDisplayed(),"Cancel Link failed to display");
		Assert.assertTrue(changePasswordPopup.isCloseButtonDisplayed(),"Close Button failed to display");
		
		/* Click the Close button */
		changePasswordPopup.clickClose();
		
		/* Verify the Change Password popup is not displayed */
		Assert.assertFalse(settingsPage.isChangePasswordPopupDisplayed(),"Change Password popup failed to close");		
	}
 
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Profile Photo popup loads successfully")
	public void editProfilePhotoPopupLoads()
	{	
    	/* Declare a navigation variable */
    	Navigation navigation = new Navigation(getDriver());
		
		/* Hover over avatar and click My Profile */
    	navigation.hoverOverAvatar();
    	MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile and check edit photo button is visible */
    	Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit Photo button is not visible");
		
		/* Click the Edit link beside the photo and check the cancel button */
		EditProfilePhoto editProfilePhotoPopup = profilePage.clickEditProfilePhoto();
		Assert.assertTrue(editProfilePhotoPopup.isEditProfilePhotoPopupDisplayed(),"Edit Profile Photo Popup failed to display");
		Assert.assertTrue(editProfilePhotoPopup.isCancelButtonDisplayed(),"Cancel button failed to display");
		editProfilePhotoPopup.clickCancel();
		
		/* Click the Edit link beside the photo and check the close, browse and save buttons */
		profilePage.clickEditProfilePhoto();
		Assert.assertTrue(editProfilePhotoPopup.isEditProfilePhotoPopupDisplayed(),"Edit Profile Photo Popup failed to display");
		Assert.assertTrue(editProfilePhotoPopup.isBrowseButtonDisplayed(),"Browse button failed to display");
		Assert.assertTrue(editProfilePhotoPopup.isSaveButtonDisplayed(),"Save button failed to display");	
		Assert.assertTrue(editProfilePhotoPopup.isCloseButtonDisplayed(), "Close button is not visible");
		editProfilePhotoPopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditProfilePhotoButtonDisplayed(),"Edit profile photo button is visible");
	}

	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Name popup loads successfully")
	public void editNamePopupLoads()
	{
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();	
		
		/* Click Edit Profile and check edit name link is visible */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditNameLinkDisplayed(),"Edit name link is not visible");
		
		/* Click the Edit link beside the name and check the cancel button */
		EditName editNamePopup = profilePage.clickEditName();
		Assert.assertTrue(editNamePopup.isEditNamePopupDisplayed(),"Edit Name Popup failed to display");
		Assert.assertTrue(editNamePopup.isCancelButtonDisplayed(), "Cancel button is not visible");
		editNamePopup.clickCancel();
		
		/* Click the Edit link beside the name and check fields (first name, last name) and buttons (save, close) */
		profilePage.clickEditName();
		Assert.assertTrue(editNamePopup.isEditNamePopupDisplayed(),"Edit Name Popup failed to display");
		Assert.assertTrue(editNamePopup.isFirstDisplayed(), "First name field is not visible");
		Assert.assertTrue(editNamePopup.isLastDisplayed(), "Last name field is not visible");
		Assert.assertTrue(editNamePopup.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editNamePopup.isCloseButtonDisplayed(), "Close button is not visible");
		editNamePopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditNameLinkDisplayed(),"Edit name link is visible");
		
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Current Position popup loads successfully")
	public void editCurrentPositionPopupLoads()
	{
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
			
		/* Click Edit Profile and check edit position link is visible */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditPositionLinkDisplayed(),"Edit position link is not visible");
		
		/* Click the Edit link beside the position and check the cancel button */
		EditPosition editCurrentPosition = profilePage.clickEditTitle();
		Assert.assertTrue(editCurrentPosition.isEditPositionPopupDisplayed(),"Edit Position Popup failed to display");
		Assert.assertTrue(editCurrentPosition.isCancelButtonDisplayed(), "Cancel button is not visible");
		editCurrentPosition.clickCancel();
		
		/* Click the Edit link beside the position and check the close and save buttons, title field and country combo-box*/
		profilePage.clickEditTitle();
		Assert.assertTrue(editCurrentPosition.isEditPositionPopupDisplayed(),"Edit Position Popup failed to display");
		Assert.assertTrue(editCurrentPosition.isTitleDisplayed(), "Title field is not visible");
		Assert.assertTrue(editCurrentPosition.isCountryComboBoxDisplayed(), "Country combo-box is not visible");
		Assert.assertTrue(editCurrentPosition.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editCurrentPosition.isCloseButtonDisplayed(), "Close button is not visible");
		editCurrentPosition.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditPositionLinkDisplayed(),"Edit position link is visible");  	
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Contact Information popup loads successfully")
	public void editContactInfoPopupLoads()
	{	
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
	
		/* Click Edit Profile and then click the Edit link beside the contact info section */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditContactInfoLinkDisplayed(),"Edit contact info link is not visible");
		
		/* Click the Edit link beside the contact info section and check the cancel buttons */
		EditContactInformation editContactInfoPopup = profilePage.clickEditContactInfo();
		Assert.assertTrue(editContactInfoPopup.isEditContactInformationPopupDisplayed(),"Edit contact info popup failed to display");
		Assert.assertTrue(editContactInfoPopup.isCancelButtonDisplayed(), "Cancel button is not visible");
		editContactInfoPopup.clickCancel();
		
		/* Click the Edit link beside the contact info section and check the save and close buttons, the email and chat checkboxes and the email field */
		profilePage.clickEditContactInfo();
		Assert.assertTrue(editContactInfoPopup.isEditContactInformationPopupDisplayed(),"Edit contact info popup failed to display");
		Assert.assertTrue(editContactInfoPopup.isEmailCheckboxDisplayed(), "Email checkbox is not visible");
		Assert.assertTrue(editContactInfoPopup.isEmailInformationDisplayed(), "Email field is not visible");
		Assert.assertTrue(editContactInfoPopup.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editContactInfoPopup.isCloseButtonDisplayed(), "Close button is not visible");
		editContactInfoPopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditContactInfoLinkDisplayed(),"Edit contact link is visible");
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Professional Details popup loads successfully")
	public void editProfessionalDetailsPopupLoads()
	{	
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile and then click the Edit link beside the professional details section */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditProfessionalDetailsLinkDisplayed(),"Edit professional details link is not visible");
		
		/* Click the Edit link beside the professional details section and check the cancel buttons */
		EditProfessionalDetails editProfessionalDetailsPopup = profilePage.clickEditProfessionalDetails();
		Assert.assertTrue(editProfessionalDetailsPopup.isEditProfessionalDetailsPopupDisplayed(),"Edit professional details popup failed to display");
		Assert.assertTrue(editProfessionalDetailsPopup.isCancelButtonDisplayed(), "Cancel button is not visible");
		editProfessionalDetailsPopup.clickCancel();

		/* Click the Edit link beside the professional details section and check the save and close buttons, the specialty/sub-specialty dropdowns and the add specialty/sub-specialty buttons */
		profilePage.clickEditProfessionalDetails();
		Assert.assertTrue(editProfessionalDetailsPopup.isEditProfessionalDetailsPopupDisplayed());
		Assert.assertTrue(editProfessionalDetailsPopup.isOccupationDropdownDisplayed(), "Occupation dropdown is not visible");
		Assert.assertTrue(editProfessionalDetailsPopup.isSpecialtyDropdownDisplayed(), "Specialty dropdown is not visible");
		Assert.assertTrue(editProfessionalDetailsPopup.isAddButtonSpecialtyDisplayed(), "Add specialty button is not visible");
		Assert.assertTrue(editProfessionalDetailsPopup.isSubSpecialtyDropdownDisplayed(), "Sub-specialty dropdown is not visible");
		Assert.assertTrue(editProfessionalDetailsPopup.isAddButtonSubSpecialtyDisplayed(), "Add sub-specialty button is not visible");
		Assert.assertTrue(editProfessionalDetailsPopup.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editProfessionalDetailsPopup.isCloseButtonDisplayed(), "Close button is not visible");		
		editProfessionalDetailsPopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditProfessionalDetailsLinkDisplayed(),"Edit professional details link is visible");
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Research / Clinical Interests popup loads successfully")
	public void editResearchClinicalInterestsPopupLoads()
	{	
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile and then click the Edit link beside the research/clinical interests section */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditResearchClinicalInterestsLinkDisplayed(),"Edit research/clinical interests link is not visible");
		
		/* Click the Edit link beside the research/clinical interests section and check the cancel button */
		EditResearchClinicalInterests editResearchClinicalInterestsPopup = profilePage.clickEditResearchClinicalInterests();
		Assert.assertTrue(editResearchClinicalInterestsPopup.isEditBackgroundPopupDisplayed(),"Edit Background Popup failed to display");
		Assert.assertTrue(editResearchClinicalInterestsPopup.isCancelButtonDisplayed(), "Cancel button is not visible");
		editResearchClinicalInterestsPopup.clickCancel();
		
		/* Click the Edit link beside the research/clinical interests section and check the save and close buttons */
		profilePage.clickEditResearchClinicalInterests();
		Assert.assertTrue(editResearchClinicalInterestsPopup.isEditBackgroundPopupDisplayed(),"Edit Background Popup failed to display");
		Assert.assertTrue(editResearchClinicalInterestsPopup.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editResearchClinicalInterestsPopup.isCloseButtonDisplayed(), "Close button is not visible");
		editResearchClinicalInterestsPopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditResearchClinicalInterestsLinkDisplayed(),"Edit research/clinical interests link is visible");	  	
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Additional Information popup loads successfully")
	public void editAdditionalInformationPopupLoads()
	{	
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile and then click the Edit link beside the additional information section */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isAdditionalContactInfoLinkDisplayed(),"Edit Additional Info link is not visible");
		
		/* Click the Edit link beside the additional information section and check the cancel button */
		EditAdditionalInformation editAdditionalInfoPopup = profilePage.clickEditAdditionalInformation();
		Assert.assertTrue(editAdditionalInfoPopup.isEditAdditionalInfoPopupDisplayed(),"Edit Additional Info Popup failed to display");
		Assert.assertTrue(editAdditionalInfoPopup.isCancelButtonDisplayed(), "Cancel button is not visible");
		editAdditionalInfoPopup.clickCancel();
		
		/* Click the Edit link beside the additional information section and check the save and close buttons */
		profilePage.clickEditAdditionalInformation();
		Assert.assertTrue(editAdditionalInfoPopup.isEditAdditionalInfoPopupDisplayed(),"Edit Additional Info Popup failed to display");
		Assert.assertTrue(editAdditionalInfoPopup.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editAdditionalInfoPopup.isCloseButtonDisplayed(), "Close button is not visible");
		editAdditionalInfoPopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isAdditionalContactInfoLinkDisplayed(),"Edit additional info link is visible");	  	
	}

	@Test(groups={"BVT","FullRegression"}, description="Verifies the Edit Interests popup loads successfully")
	public void editInterestsPopupLoads()
	{
		/* Declare a navigation variable */
		Navigation navigation = new Navigation(getDriver()); 		
		
		/* Hover over avatar and click My Profile */
		navigation.hoverOverAvatar();
		MyProfile profilePage = navigation.clickMyProfile();
		
		/* Click Edit Profile and then click the Edit link beside the interests section */
		Assert.assertTrue(profilePage.isEditProfileButtonDisplayed(), "Edit Profile button is not visible");
		profilePage.clickEditProfile();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(done_editing),"Label of the Edit Profile button should be "+done_editing);
		Assert.assertTrue(profilePage.isEditInterestsLinkDisplayed(),"Edit interests link is not visible");
		
		/* Click the Edit link beside the interests section and check the cancel buttons */
		EditInterests editInterestsPopup = profilePage.clickEditInterests();
		Assert.assertTrue(editInterestsPopup.isEditInterestsPopupDisplayed(),"Edit interests popup failed to display");
		Assert.assertTrue(editInterestsPopup.isCancelButtonDisplayed(), "Cancel button is not visible");
		editInterestsPopup.clickCancel();

		/* Click the Edit link beside the interests section and check the save and close buttons, the interests dropdown and the add interest button */
		profilePage.clickEditInterests();
		Assert.assertTrue(editInterestsPopup.isEditInterestsPopupDisplayed());
		Assert.assertTrue(editInterestsPopup.isInterestsDropdownDisplayed(), "Interests dropdown is not visible");
		Assert.assertTrue(editInterestsPopup.isAddButtonInterestsDisplayed(), "Add interest button is not visible");
		Assert.assertTrue(editInterestsPopup.isSaveButtonDisplayed(), "Save button is not visible");
		Assert.assertTrue(editInterestsPopup.isCloseButtonDisplayed(), "Close button is not visible");		
		editInterestsPopup.clickClose();
		
		/* Click Done Editing */
		profilePage.clickDoneEditing();
		Assert.assertTrue((profilePage.getEditButtonText()).equals(edit_profile),"Label of the Edit Profile button should be "+edit_profile);
		Assert.assertFalse(profilePage.isEditInterestsLinkDisplayed(),"Edit interests link is visible");
	}
}
