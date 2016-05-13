package tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import admin.navigation.LeftNav;
import admin.reports.Reports;

public class ReportsTests extends LoadBrowser {	
	
	private String REPORTS_PAGE_HEADER = new LocaleStrings().getString("REPORTS_PAGE_HEADER");

	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Reports page displays")
	public void ReportsPageDisplays()
	{			
		/* Click Reports in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		Reports reportsPage = leftnav.clickReports();
		
		/* Verify the correct page header appears */
		Assert.assertEquals(reportsPage.getHeader(), REPORTS_PAGE_HEADER);				
	}	
}

