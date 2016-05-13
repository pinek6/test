package tests.webUI.nonFunctional;

import org.testng.Assert;
import org.testng.annotations.Test;

import webUI.ConfigProperties;
import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.Home;
import webUI.login.ForgotPassword;
import webUI.login.Login;

public class LoginTests extends LoadBrowser{
	
	/* Read in the configuration file */
	private ConfigProperties config = new ConfigProperties();	
	
	/* Error messages */
	private String incorrectPassword = new LocaleStrings().getString("INCORRECT_PASSWORD");
	private String incompleteInformation = new LocaleStrings().getString("INCOMPLETE_INFORMATION");
	private String noEmailAddress = new LocaleStrings().getString("NO_EMAIL_ADDRESS");
	private String forgotPassword = new LocaleStrings().getString("FORGOT_PASSWORD");
	private String invalidEmailAddress = new LocaleStrings().getString("INVALID_EMAIL_ADDRESS");
	private String URL = new LocaleStrings().getString("URL");
	private String backgroundImage = new LocaleStrings().getString("BG_IMG");
	
	/* Username and password */
	private String username = config.getConfigProperties().getProperty("DOCTOR_USERNAME").toString();
	private String password = config.getConfigProperties().getProperty("DOCTOR_PASSWORD").toString();

	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the scenic background image does not appear")
	public void scenicBackgroundImageDoesNotAppear()
	{
	 	/* Launch the login page */
    	Login loginPage = new Login(getDriver());    	
    	
    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	} 
    	
    	/* Verify the scenic background image does not appear */
    	Assert.assertFalse(loginPage.getBackgroundImage().contains((backgroundImage).toLowerCase()),"Scenic background image appearing in error");
	}	
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies a user can login successfully")
    public void successfulLogin()
    {    	
		/* Launch the login page */
    	Login loginPage = new Login(getDriver());
    	
    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}  
    	
    	/* Check that the username and password fields display */
    	Assert.assertTrue(loginPage.isUsernameFieldDisplayed(),"Username field failed to display");
    	Assert.assertTrue(loginPage.isPasswordFieldDisplayed(),"Password field failed to display");
    	
    	/* Enter a username and password */
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	
    	/* Click Login */
    	loginPage.clickLogin();
  	
    	/* The home page should launch */
    	Home homePage = new Home(getDriver());

    	/* Verify the 2 panels appear */
    	Assert.assertTrue(homePage.isLibraryPanelDisplayed(), "Library Panel failed to display on the home page");
//    	Assert.assertTrue(homePage.isGroupsPanelDisplayed(), "Groups Panel failed to display on the home page");
    	Assert.assertTrue(homePage.isColumn1Displayed(), "Column 1 on the home page failed to load");
    	
    	/* Verify the 2 panel titles appear */
    	Assert.assertTrue(homePage.isLibraryPanelTitleDisplayed(), "Library Panel title failed to display on the home page");
//    	Assert.assertTrue(homePage.isGroupsPanelTitleDisplayed(), "Groups Panel title failed to display on the home page");
    	
    	/* Verify the 2 buttons appear */
    	Assert.assertTrue(homePage.isSearchLibraryButtonDisplayed(), "Search Library button failed to display on the home page");
//    	Assert.assertTrue(homePage.isVisitButtonDisplayed(), "Groups visit button failed to display on the home page");
    	
    	/* Verify the ui elements appears */
		Assert.assertTrue(homePage.isLibraryPanelDisplayed(),"Library panel on home page failed to load");
//		Assert.assertTrue(homePage.isGroupsPanelDisplayed(),"Groups panel on home page failed to load");
		Assert.assertTrue(homePage.isMyLearningPanelDisplayed(),"My Learning panel on home page failed to load");   	
   	
    	/* Declare a navigation variable */
    	Navigation navigation = new Navigation(getDriver());    	
    	
    	/* Verify the search/avatar links appear */
    	Assert.assertTrue(navigation.isSearchDisplayed(), "Search icon failed to display");
    	Assert.assertTrue(navigation.isAvatarDisplayed(), "Profile Picture failed to display"); 
    	Assert.assertTrue(navigation.isAppNameDisplayed(),"Application Name failed to display");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the correct error message appears if a user tries to log in with an incorrect password")
    public void failedLoginIncorrectPassword()
    {
    	Login loginPage = new Login(getDriver());

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	    	
    	loginPage.enterUsername(username);
    	loginPage.enterPassword("wRoNg");
    	loginPage.clickLogin();
    	Assert.assertEquals(loginPage.getErrorMessage(),incorrectPassword,"Incorrect error message when entering an incorrect password");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the correct error message appears if a user tries to log in entering a valid username but no password")
    public void failedLoginNoPassword()
    {
    	Login loginPage = new Login(getDriver());

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	loginPage.enterUsername(username);
    	loginPage.clickLogin();
    	Assert.assertEquals(loginPage.getErrorMessage(),incompleteInformation,"Incorrect error message when no password entered");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the correct error message appears if a user does not enter a username or password and clicks Login")
    public void failedLoginNoUsernameOrPassword()
    {
    	Login loginPage = new Login(getDriver());

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	loginPage.clickLogin();
    	Assert.assertEquals(loginPage.getErrorMessage(),incompleteInformation,"Incorrect error message when no username or password entered");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the correct error message appears if a user does not enter a valid username and clicks Forgot Password")
    public void forgotPasswordNoEmailEntered()
    {
    	Login loginPage = new Login(getDriver()); 

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	} 
    	
    	ForgotPassword forgotPasswordPage = loginPage.clickForgotPassword();
    	
    	forgotPasswordPage.clickResetPassword();
    	
    	Assert.assertEquals(forgotPasswordPage.getErrorMessage(),noEmailAddress,"Incorrect error message when no email address entered");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the correct error message appears if a user enters an invalid email on the Forgot Password page")
    public void forgotPasswordInvalidEmailEntered()
    {
    	Login loginPage = new Login(getDriver()); 

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	ForgotPassword forgotPasswordPage = loginPage.clickForgotPassword();
    	
    	forgotPasswordPage.enterUsername("invalidemailaddress");
    	forgotPasswordPage.clickResetPassword();
    	Assert.assertEquals(forgotPasswordPage.getErrorMessage(),invalidEmailAddress,"Incorrect error message when invalid email address entered");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Forgot Password page loads")
    public void forgotPasswordPageLoads()
    {
    	Login loginPage = new Login(getDriver());  

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	loginPage.enterUsername("ted@ted.com");
    	ForgotPassword forgotPasswordPage = loginPage.clickForgotPassword();
    	
    	Assert.assertTrue(forgotPasswordPage.isEnterEmailFieldDisplayed(),"Email Address field failed to load on the Forgot Password page");
    	Assert.assertTrue(forgotPasswordPage.isResetPasswordButtonDisplayed(),"Reset Password button failed to load on the Forgot Password page");
    	Assert.assertTrue(forgotPasswordPage.isReturnToLoginLinkDisplayed(),"Return to login link failed to load on the Forgot Password page");
    }
    
    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies a user can be logged out successfully")
    public void logout()
    {
		/* Launch the login page */
    	Login loginPage = new Login(getDriver());

    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}
    	
    	/* Enter a username and password */
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	
    	/* Click Login */
    	loginPage.clickLogin();
  	
    	/* The home page should launch */
    	new Home(getDriver());
    	
    	/* Declare a navigation variable */
    	Navigation navigation = new Navigation(getDriver());
    	
    	/* Wait for Sametime dock to be loaded */
    	navigation.waitForSametimeDockToBeDisplayed();
    	
    	/* Hover over the avatar and click logout */
    	navigation.hoverOverAvatar();
    	navigation.clickLogout();
   
    	/* Verify the username and password fields are displayed */
    	Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
    	Assert.assertTrue(loginPage.isPasswordFieldDisplayed());     	
    }   

    @Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the footer links display")
    public void footerLinksDisplay()
    {    	
		/* Launch the login page */
    	Login loginPage = new Login(getDriver());
    	
    	if (currentBrowser.equals("internet explorer")&& getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}  
    	
    	/* Enter a username and password */
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	
    	/* Click Login */
    	loginPage.clickLogin();
  	
    	/* The home page should launch */
    	new Home(getDriver());
    	Navigation navigation = new Navigation(getDriver());    	
    	
    	/* Verify footer links appear */
    	Assert.assertTrue(navigation.getCountOfFooterListItems()>0);
    	Assert.assertTrue(navigation.isFooterHelpLinkDisplayed(), "Help Link failed to display in footer");
      	Assert.assertTrue(navigation.isFooterTermsOfUseLinkDisplayed(), "Terms of Use Link failed to display in footer");
    	Assert.assertTrue(navigation.isFooterPrivacyLinkDisplayed(), "Privacy Link failed to display in footer");
    	Assert.assertTrue(navigation.isFooterFeedbackLinkDisplayed(), "Feedback Link failed to display in footer");
     }
}
