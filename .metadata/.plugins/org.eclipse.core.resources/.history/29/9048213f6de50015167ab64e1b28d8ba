package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import haufe.LoadBrowser_Grid;
import haufe.LoadBrowser_WebDriver;
import haufe.home.Home;

public class BasicTests extends LoadBrowser_Grid{

	@Test
	public void changeLanguage()
	{
		Home home = new Home(getDriver());
		
		home.clickLangDropdown();
		home.selectLanguage("[en]");
		
		
		Assert.assertEquals(home.getSloganText(),"Successful Talent Management Solutions Start with People","Slogan not as expected");
	}

}
