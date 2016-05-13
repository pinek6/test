package tests.admin;

import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.login.invite.FirstTimeUserRegistration;
import admin.members.invitations.InvitationResults;
import admin.members.invitations.Invitations;
import admin.members.invitations.InviteNewMembers;
import admin.navigation.LeftNav;
import bluebox.BlueboxHomePage;
import bluebox.BlueboxInbox;

public class InvitationsTests extends LoadBrowser {
	
	String email,password,confirmPassword,firstname,lastname,age,gender,hospital,position,title,experience;
	String email1,email2,firstname1,firstname2,lastname1,lastname2;
	
	private String SUCCESSFULLYCREATEDINVITATION = new LocaleStrings().getString("INVITATIONRESULTS");
	private String STATUS_INVITED = new LocaleStrings().getString("INVITED");
	private String STATUS_INACTIVE = new LocaleStrings().getString("INACTIVE");
	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(enabled=false, groups={"FullRegression"}, description="Verifies an invitation can be created")
	public void CreateAnInvitation()
	{	
		/* Set the internal title and the html */
		Date d = new Date();
		email = "fvt_invite"+d.getTime()+"@bluebox.lotus.com";
		
		/* Click Members - Invitations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Invitations invitationsPage = leftnav.clickInvitations();
		
		/* Verify the Invite More Users button is displayed */
		Assert.assertTrue(invitationsPage.isInviteMoreUsersDisplayed());
		
		/* Click the Invite More Users button */
		InviteNewMembers inviteNewMembersPage = invitationsPage.clickInviteMoreUsers();
		
		/* Verify the Email Addresses textarea and the Add Members button are displayed */
		Assert.assertTrue(inviteNewMembersPage.isEmailAddressesDisplayed());
		Assert.assertTrue(inviteNewMembersPage.isAddMembersButtonDisplayed());
		
		/* Enter an email address and click add members button */
		inviteNewMembersPage.enterEmailAddress(email);
		inviteNewMembersPage.clickAddMembers();
		
		/* Verify the send invitations and remove all buttons are displayed */
		Assert.assertTrue(inviteNewMembersPage.isSendInvitationsButtonDisplayed());
		Assert.assertTrue(inviteNewMembersPage.isRemoveAllButtonDisplayed());
		
		/* Click send invitations button */
		InvitationResults invitationResultsPage = inviteNewMembersPage.clickSendInvitations();
		
		Reporter.log(email + " invited");
		
		/* Verify the success message displays and the invite more members button is diplayed */
		Assert.assertEquals(invitationResultsPage.getSuccessMessage(), SUCCESSFULLYCREATEDINVITATION);
		Assert.assertTrue(invitationResultsPage.isInviteMoreMembersDisplayed());
		
		/* Navigate to the blue box homepage */
		BlueboxHomePage blueboxHomePage = new BlueboxHomePage(getDriver());
		
		/* Enter the email address and click submit */
		blueboxHomePage.enterEmailAddress(email);
		BlueboxInbox inbox = blueboxHomePage.clickSubmit();
		
		/* Keep refreshing the inbox until an email appears in the inbox */
		inbox.clickRefreshUntilNEmails(1);		
	}
	
	@Test(enabled=false, groups={"FullRegression"},dependsOnMethods={"CreateAnInvitation"}, description="Verifies an invitation can be withdrawn")
	public void WithdrawAnInvitation() 
	{			
		/* Click Members - Invitations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Invitations invitationsPage = leftnav.clickInvitations();

		/* Show all the invitations */
		invitationsPage.showFullListOfInvitations();
		
		/* Find which row the invitation appears */
		int row = invitationsPage.findRowWithEmail(email);
		
		/* Verify the status is Invited and that the Resend Invite and Withdraw Invite links appear */
		Assert.assertEquals(invitationsPage.getNthInviteStatus(row), STATUS_INVITED);
		Assert.assertTrue(invitationsPage.isNthInviteResendInviteDisplayed(row));
		Assert.assertTrue(invitationsPage.isNthInviteWithdrawInviteDisplayed(row));
		
		/* Click Withdraw Invite */
		invitationsPage.clickNthInviteWithdrawInvite(row);
		
		/* Click Invitations in the left hand navigation */
		leftnav.clickInvitations();	
		
		/* Show all the invitations */
		invitationsPage.showFullListOfInvitations();
		
		/* Find which row the invitation appears */
		row = invitationsPage.findRowWithEmail(email);
		
		/* Verify the status is Inactive and that the Resend Invite and Withdraw Invite links do not appear */
		Assert.assertEquals(invitationsPage.getNthInviteStatus(row), STATUS_INACTIVE);
		Assert.assertFalse(invitationsPage.isNthInviteResendInviteDisplayed(row));
		Assert.assertFalse(invitationsPage.isNthInviteWithdrawInviteDisplayed(row));
	}	
	
	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"CreateAnInvitation"}, description="Verifies an invitation can be completed")
	public void CompleteInvitation()  //TODO INCOMPLETE
	{			
		/* Navigate to the blue box homepage */
		BlueboxHomePage blueboxHomePage = new BlueboxHomePage(getDriver());
		
		/* Enter the email address and click submit */
		blueboxHomePage.enterEmailAddress(email);
		BlueboxInbox inbox = blueboxHomePage.clickSubmit();
		
		/* Keep refreshing the inbox until an email appears in the inbox */
		inbox.clickRefreshUntilNEmails(1);	
		
		inbox.clickNthEmailSubjectField(1);
		
		FirstTimeUserRegistration firstTimeUserRegistrationPage = inbox.clickRegisterLinkInInvitationEmail();
		
		/* Verify UI Elements appear */
		Assert.assertTrue(firstTimeUserRegistrationPage.isEmailAddressDisplayed(),"emailAddress failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isPasswordDisplayed(),"password failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isConfirmPasswordDisplayed(),"confirmPassword failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isFirstnameDisplayed(),"firstname failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isLastNameDisplayed(),"lastName failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isGenderDisplayed(),"gender failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isHospitalDisplayed(),"hospital failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isPositionDisplayed(),"position failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isTitleDisplayed(),"title failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isPediatricsCheckboxDisplayed(),"pediatricsCheckbox failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isGeneralMedicineCheckboxDisplayed(),"generalMedicineCheckbox failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isAnesthesiaCheckboxDisplayed(),"anesthesiaCheckbox failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isCriticalCareSubspecialtyTrainingCheckboxDisplayed(),"criticalCareSubspecialtyTrainingCheckbox failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isNeonatologyCheckboxDisplayed(),"neonatologyCheckbox failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isOtherCheckboxDisplayed(),"otherCheckbox failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isOtherInputFieldDisplayed(),"otherInputField failed to display");
//		Assert.assertTrue(firstTimeUserRegistrationPage.isExperienceDisplayed(),"experience failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isInterestsDisplayed(),"interests failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isAcceptTermsAndConditionsDisplayed(),"acceptTermsAndConditions failed to display");
		Assert.assertTrue(firstTimeUserRegistrationPage.isRegisterButtonDisplayed(),"registerButton failed to display");		
	}
	
	@Test(enabled=false, groups={"FullRegression"}, description="Verifies an invitation can be sent to multiple members")
	public void CreateAnInvitationToMultiple()
	{	
		/* Set the internal title and the html */
		Date d = new Date();
		email1 = "fvt_invitationstest1"+d.getTime()+"@bluebox.lotus.com";
		email2 = "fvt_invitationstest2"+d.getTime()+"@bluebox.lotus.com";
		firstname1="firstname1"+d.getTime();
		firstname2="firstname2"+d.getTime();
		lastname1="lastname1"+d.getTime();
		lastname2="lastname2"+d.getTime();
		
		/* Click Members - Invitations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Invitations invitationsPage = leftnav.clickInvitations();
		
		/* Verify the Invite More Users button is displayed */
		Assert.assertTrue(invitationsPage.isInviteMoreUsersDisplayed());
		
		/* Click the Invite More Users button */
		InviteNewMembers inviteNewMembersPage = invitationsPage.clickInviteMoreUsers();
		
		/* Verify the Email Addresses textarea and the Add Members button are displayed */
		Assert.assertTrue(inviteNewMembersPage.isEmailAddressesDisplayed());
		Assert.assertTrue(inviteNewMembersPage.isAddMembersButtonDisplayed());
		
		/* Enter an email address and click add members button */
		inviteNewMembersPage.enterEmailAddress(email1+","+email2);
		inviteNewMembersPage.clickAddMembers();
		
		inviteNewMembersPage.clickNthInviteeEdit(1);
		inviteNewMembersPage.clickNthInviteeEdit(2);
		
		inviteNewMembersPage.enterNthFirstname(1, firstname1);
		inviteNewMembersPage.enterNthLastName(1, lastname1);
		inviteNewMembersPage.enterNthFirstname(2, firstname2);
		inviteNewMembersPage.enterNthLastName(2, lastname2);
		
		inviteNewMembersPage.clickNthInviteeSave(1);
		inviteNewMembersPage.clickNthInviteeSave(2);
		
		/* Verify the send invitations and remove all buttons are displayed */
		Assert.assertTrue(inviteNewMembersPage.isSendInvitationsButtonDisplayed());
		Assert.assertTrue(inviteNewMembersPage.isRemoveAllButtonDisplayed());
		
		/* Click send invitations button */
		InvitationResults invitationResultsPage = inviteNewMembersPage.clickSendInvitations();
		
		Reporter.log(email1 + " invited");
		Reporter.log(email2 + " invited");
		
		/* Verify the success message displays and the invite more members button is diplayed */
		Assert.assertEquals(invitationResultsPage.getSuccessMessage(), SUCCESSFULLYCREATEDINVITATION);
		Assert.assertTrue(invitationResultsPage.isInviteMoreMembersDisplayed());
		
		/* Navigate to the blue box homepage */
		BlueboxHomePage blueboxHomePage = new BlueboxHomePage(getDriver());
		
		/* Enter the email address and click submit */
		blueboxHomePage.enterEmailAddress(email1);
		BlueboxInbox inbox = blueboxHomePage.clickSubmit();
		
		/* Keep refreshing the inbox until an email appears in the inbox */
		inbox.clickRefreshUntilNEmails(1);
		
		/* Click the first email in the list */
		inbox.clickNthEmailSubjectField(1);
		
		/* Verify the register link and the salutation is displayed in the email */
		Assert.assertTrue(inbox.isRegisterLinkInInvitationEmailDisplayed());
		Assert.assertTrue(inbox.isSalutationInInvitationEmailDisplayed());
		
		/* Verify the salutation contains the firstname followed by the lastname */
		Assert.assertTrue(inbox.getSalutationInvitationEmail().contains(firstname1 + " " + lastname1), inbox.getSalutationInvitationEmail() + " did not contain " + firstname1 + " " + lastname1 );
		
		/* Enter the second email address and click submit */
		inbox.enterEmailAddress(email2);
		inbox.clickSubmit();
		
		/* Keep refreshing the inbox until an email appears in the inbox */
		inbox.clickRefreshUntilNEmails(1);
		
		/* Click the first email in the list */
		inbox.clickNthEmailSubjectField(1);
		
		/* Verify the register link and the salutation is displayed in the email */
		Assert.assertTrue(inbox.isRegisterLinkInInvitationEmailDisplayed());
		Assert.assertTrue(inbox.isSalutationInInvitationEmailDisplayed());

		/* Verify the salutation contains the firstname followed by the lastname */
		Assert.assertTrue(inbox.getSalutationInvitationEmail().contains(firstname2 + " " + lastname2), inbox.getSalutationInvitationEmail() + " did not contain " + firstname2 + " " + lastname2 );
	}

	
}

