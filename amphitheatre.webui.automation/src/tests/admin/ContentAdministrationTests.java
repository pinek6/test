package tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import admin.contentAdministration.ContentAdministration;
import admin.navigation.LeftNav;

public class ContentAdministrationTests extends LoadBrowser {	
	
	private String CONTENT_ADMINISTRATION_PAGE_HEADER = new LocaleStrings().getString("CONTENT_ADMINISTRATION_PAGE_HEADER");

	
	@BeforeMethod(groups={"BVT","FullRegression","UAT-BVT","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(groups={"BVT","FullRegression","UAT-BVT"}, description="Verifies the Content Administration page displays")
	public void ContentAdministrationPageDisplays()
	{			
		/* Click Content Administration in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		ContentAdministration contentAdministrationPage = leftnav.clickContentAdministration();
		
		/* Navigate to the first tab on the page */
		contentAdministrationPage.clickFirstTab();
		
		/* Verify the correct page header appears */
		Assert.assertEquals(contentAdministrationPage.getHeader(), CONTENT_ADMINISTRATION_PAGE_HEADER);
				
	}

}

