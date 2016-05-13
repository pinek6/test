package tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import webUI.LocaleStrings;
import admin.navigation.LeftNav;
import admin.system.ConfigurationOptions;
import admin.system.CustomMemberFields;
import admin.system.CustomOrganizationFields;
import admin.system.Enumerations;

public class SystemTests extends LoadBrowser {	
	
	private String CONFIGURATION_OPTIONS_PAGE_HEADER = new LocaleStrings().getString("CONFIGURATION_OPTIONS_PAGE_HEADER");
	private String CUSTOMER_MEMBER_FIELDS_PAGE_HEADER = new LocaleStrings().getString("CUSTOMER_MEMBER_FIELDS_PAGE_HEADER");
	private String CUSTOM_ORGANIZATION_FIELDS_PAGE_HEADER = new LocaleStrings().getString("CUSTOM_ORGANIZATION_FIELDS_PAGE_HEADER");
	private String ENUMERATIONS_PAGE_HEADER = new LocaleStrings().getString("ENUMERATIONS_PAGE_HEADER");

	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Configuration Options Page displays")
	public void ConfigurationOptionsPageDisplays()
	{			
		/* Click System - Configuration Options in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickSystem();
		ConfigurationOptions configurationOptionsPage = leftnav.clickConfigurationOptions();
		
		/* Verify header text is correct and table appears */
		Assert.assertEquals(configurationOptionsPage.getHeader(), CONFIGURATION_OPTIONS_PAGE_HEADER,"Incorrect page header");
		Assert.assertTrue(configurationOptionsPage.isConfigurationOptionsTableDisplayed(),"Configuration Options Table failed to display");
		
		/* Verify each row in the table */
		for (int i=1; i<=configurationOptionsPage.getCountOfConfigurationOptions(); i++)
		{
			Assert.assertTrue(configurationOptionsPage.isNthEditLinkDisplayed(i),"Edit Link failed display for row " + i);
			Assert.assertTrue(configurationOptionsPage.isNthGroupDisplayed(i),"Group failed display for row " + i);
			Assert.assertTrue(configurationOptionsPage.isNthNameDisplayed(i),"Name failed display for row " + i);
			Assert.assertTrue(configurationOptionsPage.isNthValueDisplayed(i),"Value failed display for row " + i);
			Assert.assertTrue(configurationOptionsPage.isNthDescriptionDisplayed(i),"Description failed display for row " + i);
		}			
	}
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Custom Member Fields Page displays")
	public void CustomMemberFieldsPageDisplays()
	{			
		/* Click System - Custom Member Fields in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickSystem();
		CustomMemberFields customMemberFieldsOptionsPage = leftnav.clickCustomMemberFields();
		
		/* Verify the page header is correct and some UI fields appear */
		Assert.assertEquals(customMemberFieldsOptionsPage.getHeader(), CUSTOMER_MEMBER_FIELDS_PAGE_HEADER,"Incorrect page header");			
		Assert.assertTrue(customMemberFieldsOptionsPage.isDisplayNameContainsDisplayed()," Display Name Contains field failed to display");
		Assert.assertTrue(customMemberFieldsOptionsPage.isFilterListDisplayed(),"Filter List button failed to display");
		
		/* Verify each cell of each row of the table is displaying */
		for (int i =1; i<=customMemberFieldsOptionsPage.getCountOfMatchingMemberFields(); i++)
		{			
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthEditLinkDisplayed(i),"Edit Link failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthOrderDisplayed(i),"Order failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthDisplayNameDisplayed(i),"Display Name failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthInternalNameDisplayed(i),"Internal Name failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthFormTypeDisplayed(i),"Form Type failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthDefaultDisplayed(i),"Default failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthRequiredDisplayed(i),"Required failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthInRegistrationDisplayed(i),"In Registration failed display for row " + i);
			Assert.assertTrue(customMemberFieldsOptionsPage.isNthAdminEditableDisplayed(i),"Admin Editable failed display for row " + i);
		}
	}

	@Test(groups={"BVT","FullRegression"}, description="Verifies the Custom Organization Fields Page displays")
	public void CustomOrganizationFieldsPageDisplays()
	{			
		/* Click System - Custom Organization Fields in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickSystem();
		CustomOrganizationFields customOrganizationFieldsOptionsPage = leftnav.clickCustomOrganizationFields();
		
		/* Verify the page header is correct and some UI fields appear */
		Assert.assertEquals(customOrganizationFieldsOptionsPage.getHeader(), CUSTOM_ORGANIZATION_FIELDS_PAGE_HEADER,"Incorrect page header");	
		Assert.assertTrue(customOrganizationFieldsOptionsPage.isDisplayNameContainsDisplayed()," Display Name Contains field failed to display");
		Assert.assertTrue(customOrganizationFieldsOptionsPage.isFilterListDisplayed(),"filter list button failed to display");
		Assert.assertTrue(customOrganizationFieldsOptionsPage.isOrganizationTypeDisplayed(),"Organization Type dropdown failed to display");
		Assert.assertTrue(customOrganizationFieldsOptionsPage.isCreateCustomOrganizationFieldDisplayed(),"Create Custom Organization Field button failed to display");
		
		/* Verify each cell of each row of the table is displaying */
		for (int i =1; i<=customOrganizationFieldsOptionsPage.getCountOfMatchingOrganizationFields(); i++)
		{
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthEditLinkDisplayed(i),"Edit Link failed display for row " + i);;
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthDeleteLinkDisplayed(i),"Delete Link failed display for row " + i);
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthOrderDisplayed(i),"Order failed display for row " + i);
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthDisplayNameDisplayed(i),"Display Name failed display for row " + i);
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthInternalNameDisplayed(i),"Internal Name failed display for row " + i);
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthFormTypeDisplayed(i),"Form Type failed display for row " + i);
			Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthDefaultDisplayed(i),"Default failed display for row " + i);
			//Assert.assertTrue(customOrganizationFieldsOptionsPage.isNthOrganizationTypesDisplayed(i),"Organization Types failed display for row " + i);
		}
	}

	@Test(groups={"BVT","FullRegression"}, description="Verifies the Enumerations Page displays")
	public void EnumerationsPageDisplays()
	{			
		/* Click System - Enumerations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickSystem();
		Enumerations enumerationsPage = leftnav.clickEnumerations();
		
		Assert.assertEquals(enumerationsPage.getHeader(), ENUMERATIONS_PAGE_HEADER,"Incorrect page header");	
		Assert.assertTrue(enumerationsPage.isNameContainsDisplayed(),"Name Contains field failed to display");
		Assert.assertTrue(enumerationsPage.isSimpleViewingModeRadiobuttonDisplayed(),"Simple Viewing Mode Radio button failed to display");
		Assert.assertTrue(enumerationsPage.isFullViewingModeRadiobuttonDisplayed(),"Full Viewing Mode Radio button failed to display");
		Assert.assertTrue(enumerationsPage.isUpdateDisplayOfCustomEnumerationsDisplayed(),"Update Display of Custom Enumerations button failed to display");
		Assert.assertTrue(enumerationsPage.isCreateCustomEnumerationDisplayed(),"Create Custom Enumeration button failed to display");
		
		for (int i=1;i<=enumerationsPage.getCountOfMatchingCustomEnumeration();i++)
		{
			Assert.assertTrue(enumerationsPage.isNthEditLinkDisplayedInMatchingCustomEnumerationsTable(i),"Failed to display Edit link on row " + i);
			Assert.assertTrue(enumerationsPage.isNthTitleDisplayedInMatchingCustomEnumerationsTable(i),"Failed to display Title on row " + i);
			Assert.assertTrue(enumerationsPage.isNthDescriptionDisplayedInMatchingCustomEnumerationsTable(i),"Failed to display Description on row " + i);
		}
		for (int i=1;i<=enumerationsPage.getCountOfSystemEnumerations();i++)
		{
			Assert.assertTrue(enumerationsPage.isNthViewLinkDisplayedInSystemEnumerationsTable(i));
			Assert.assertTrue(enumerationsPage.isNthTitleDisplayedInSystemEnumerationsTable(i));
			Assert.assertTrue(enumerationsPage.isNthDescriptionDisplayedInSystemEnumerationsTable(i));
		}
	}
	
}

