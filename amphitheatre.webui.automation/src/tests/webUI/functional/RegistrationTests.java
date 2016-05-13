package tests.webUI.functional;

import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.admin.MyProfile;
import webUI.home.Home;
import webUI.login.Login;
import webUI.login.PreRegistration;
import webUI.login.invite.FirstTimeUserRegistration;
import webUI.login.invite.RegisterComplete;


public class RegistrationTests extends LoadBrowser {
	
	private String OTHER_CITY = new LocaleStrings().getString("OTHER_CITY");
	private String username,pw,otherCity,firstname,lastname,gender;
	private String selectedOrganization, selectedCountry, selectedOccupation, selectedSpecialty, selectedSubSpecialty, selectedInterest;
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies a user can complete the self registration form and then log in")
    public void newUserCanRegisterAndLogin()
    {
		/* Variables for testing */
		Date d = new Date();
    	username = "selfregister_" + d.getTime()+"@bluebox.lotus.com";
    	pw = "Pa88w0rdfvt";
    	otherCity="othercity"+d.getTime();
    	firstname = "f_"+d.getTime();
    	lastname = "l_"+d.getTime();
    	gender = "F";
		
    	/* Go to the login page */
    	Login loginPage = new Login(getDriver());  
    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	/* Click Register now and click start registration */
    	PreRegistration preRegistrationPage = loginPage.clickRegisterNow();
    	FirstTimeUserRegistration firstTimeUserRegistrationPage = preRegistrationPage.clickStartRegistration();    	
    	
    	/* Fill in the form */
    	firstTimeUserRegistrationPage.enterEmailAddress(username);
    	firstTimeUserRegistrationPage.enterPassword(pw);
    	firstTimeUserRegistrationPage.enterConfirmPassword(pw);    	
    	firstTimeUserRegistrationPage.enterFirstName(firstname);
    	firstTimeUserRegistrationPage.enterLastName(lastname);
    	firstTimeUserRegistrationPage.selectGenderByValue(gender);
    	firstTimeUserRegistrationPage.selectYourOccupationByIndex(2);
    	firstTimeUserRegistrationPage.selectPostGraduateExperienceByIndex(1);
    	firstTimeUserRegistrationPage.selectNthSpecialty(1);
    	firstTimeUserRegistrationPage.selectNthSubSpecialty(1);  
    	firstTimeUserRegistrationPage.selectNthInterest(1);  
    	firstTimeUserRegistrationPage.selectCountryByIndex(1);
    	firstTimeUserRegistrationPage.selectCityByVisibleText(OTHER_CITY);
    	firstTimeUserRegistrationPage.enterOtherCity(otherCity);
    	firstTimeUserRegistrationPage.selectOrganizationByIndex(1);    	
    	firstTimeUserRegistrationPage.checkHealthcareProfessionalVerification();
    	firstTimeUserRegistrationPage.checkAcceptTermsAndConditions();
    	firstTimeUserRegistrationPage.checkDisclaimerForCommunicationAndPosts();    	
    	 	
    	/* Get the labels/text of the selected checkboxes/dropdowns */
    	selectedOrganization = firstTimeUserRegistrationPage.getSelectedOrganizationVisibleText();
    	selectedCountry = firstTimeUserRegistrationPage.getSelectedCountryVisibleText();
    	selectedOccupation = firstTimeUserRegistrationPage.getSelectedOccupationVisibleText();
    	selectedSpecialty = firstTimeUserRegistrationPage.getNthSpecialty(1);
    	selectedSubSpecialty = firstTimeUserRegistrationPage.getNthSubSpecialty(1);
    	selectedInterest = firstTimeUserRegistrationPage.getNthInterest(1);
    	
    	/* Click register */
    	firstTimeUserRegistrationPage.clickRegister();    	
 
    	/* Wait for the registration complete page and click continue */
    	RegisterComplete registerCompletePage = new RegisterComplete(getDriver());    	
    	registerCompletePage.clickContinue();
    	
    	/* Enter the username and password on the login page and click login */
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(pw);
    	loginPage.clickLogin();
    	
    	/* Home page should load and then hover over the avatar */
    	new Home(getDriver());
    	Navigation n = new Navigation(getDriver());
    	n.hoverOverAvatar();
    
    	/* Verify the correct logged in username appears */
    	String loggedInUser = n.getLoggedInUsername();    	
    	Assert.assertEquals(loggedInUser, firstname+" "+lastname, "Incorrect logged in username appearing in the top navigation");
    	
    	Reporter.log(username + "/"+ pw+ " created");
    	
    	/* Click the My Profile link */
    	MyProfile myProfilePage = n.clickMyProfile();
    	
    	/* Verify the values of each of the fields on the My Profile page are correct */
    	Assert.assertEquals(myProfilePage.getName(), firstname+" "+lastname, "Incorrect name appearing");
    	Assert.assertEquals(myProfilePage.getPositionDetails(),selectedOrganization+"\n"+otherCity+"\n"+selectedCountry, "Incorrect position details appearing");
    	Assert.assertEquals(myProfilePage.getOccupation(),selectedOccupation, "Incorrect occupation appearing");    	
    	Assert.assertEquals(myProfilePage.getListOfSpecialty().size(),1, "Incorrect number of specialties appearing");
    	Assert.assertEquals(myProfilePage.getListOfSpecialty().get(0),selectedSpecialty, "Incorrect specialty appearing");    	
    	Assert.assertEquals(myProfilePage.getListOfSubSpecialty().size(),1, "Incorrect number of sub-specialties appearing");
    	Assert.assertEquals(myProfilePage.getListOfSubSpecialty().get(0), selectedSubSpecialty, "Incorrect sub-specialty appearing");    	
    	Assert.assertEquals(myProfilePage.getListOfInterests().size(),1, "Incorrect number of interests appearing");
    	Assert.assertEquals(myProfilePage.getListOfInterests().get(0), selectedInterest, "Incorrect interest appearing");    	
    }	
}
