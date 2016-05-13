package tests.webUI.nonFunctional;

import org.testng.Assert;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.login.Login;
import webUI.login.PreRegistration;
import webUI.login.invite.FirstTimeUserRegistration;


public class RegistrationTests extends LoadBrowser {
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies self user registration page loads")
    public void registrationPageLoads()
    {		
    	/* Go to the login page */
    	Login loginPage = new Login(getDriver());  
    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	/* Click Register now and click start registration */
    	PreRegistration preRegistrationPage = loginPage.clickRegisterNow();
    	FirstTimeUserRegistration firstTimeUserRegistrationPage = preRegistrationPage.clickStartRegistration();    	
    	    	    	
    	/* Verify UI elements appear */
    	Assert.assertTrue(firstTimeUserRegistrationPage.isEmailAddressDisplayed(),"Email address field failed to display");    	
    	Assert.assertTrue(firstTimeUserRegistrationPage.isPasswordDisplayed(),"Password field failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isConfirmPasswordDisplayed(),"Re-enter password field failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isFirstnameDisplayed(),"First name field failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isLastNameDisplayed(),"Last name field failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isGenderDisplayed(),"Gender dropdown failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isYourOccupationDisplayed(),"Your Occupation dropdown failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isYearsOfPostGradExperienceDisplayed(),"Years Of Post Grad Experience dropdown failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isSpecialtyDisplayed(),"Specialty checkboxes failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isSubSpecialtyDisplayed(),"SubSpecialty checkboxes failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isResearchAndClinicalInterestsDisplayed(),"Research and Clinical Interests field failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isInterestsDisplayed(),"Content Interests checkboxes failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isCountryDisplayed(),"Country dropdown failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isCityDisplayed(),"City dropdown failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isHospitalOrInstitutionWhereYouWorkDisplayed(), "Hospital or institution where you work dropdown failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isHealthcareProfessionalVerificationDisplayed(),"Healthcare Professional Verification checkbox failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isAcceptTermsAndConditionsDisplayed(),"Terms and Conditions checkbox failed to display");
    	Assert.assertTrue(firstTimeUserRegistrationPage.isDisclaimerForCommunicationAndPostsDisplayed(),"Disclaimer for Communication and Posts checkbox failed to display");
    }	
}
