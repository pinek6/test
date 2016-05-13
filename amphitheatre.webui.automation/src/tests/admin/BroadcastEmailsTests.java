package tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import admin.communications.broadcastEmails.BroadcastEmails;
import admin.navigation.LeftNav;

public class BroadcastEmailsTests extends LoadBrowser {

	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }	
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Broadcast Emails page displays")
	public void BroadcastEmailPageOpens()
	{		
		/* Click Communications - Broadcast Emails in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickCommunications();
		BroadcastEmails broadcastEmailsPage = leftnav.clickBroadcastEmails();
		
		/* Verify UI elements appear */
		Assert.assertTrue(broadcastEmailsPage.isAllRegisteredMembersRadiobuttonDisplayed(),"All Registered Members radio button failed to display");
		Assert.assertTrue(broadcastEmailsPage.isByOrganizationRadiobuttonDisplayed(),"By Organization radio button failed to display");
		Assert.assertTrue(broadcastEmailsPage.isByRoleRadiobuttonDisplayed(),"By Role radio button failed to display");
		Assert.assertTrue(broadcastEmailsPage.isSubjectDisplayed(),"Subject field failed to display");
		Assert.assertTrue(broadcastEmailsPage.isBodyDisplayed(),"Body field failed to display");
		Assert.assertTrue(broadcastEmailsPage.isSendEmailDisplayed(),"send email button failed to display");		
	}	
}
