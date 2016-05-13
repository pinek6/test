package tests.admin;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webUI.LoadBrowser;
import admin.members.organizations.CreateOrganization;
import admin.members.organizations.CreateOrganizationHospital;
import admin.members.organizations.CreateOrganizationSite;
import admin.members.organizations.Organizations;
import admin.navigation.LeftNav;

public class OrganizationsTests extends LoadBrowser {

	/* Variables */
	Date d = new Date();
	String basicOrganizationName, basicOrganizationCountry, basicOrganizationCity,
	siteOrganizationName, siteOrganizationType, sametimeStatus, additionalInfoOrganizationType, siteStatus,
	hospitalOrganizationName, hospitalOrganizationType, hospitalStreetAddress1, hospitalStreetAddress2, hospitalCity, hospitalState, hospitalCountry
	,hospitalType, universityName, pediatricICUBeds,  pediatricICUType, hospitalSize, hospitalStatus;
	int basicOrgCountry, basicOrgCity;
	Boolean countryDisplayed, cityDisplayed;
	
	
	@BeforeMethod(groups={"BVT","FullRegression","test"})
	public void beforeMethod()
    {
		logIntoAdminConsole();
    }	
	
	@Test(groups={"BVT","FullRegression"}, description="Verifies the Organizations page displays")
	public void OrganizationsPageOpens()
	{		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();
		
		/* Verify the create organization button displays */
		Assert.assertTrue(organizations.isCreateOrganizationDisplayed(),"Create Organization button failed to load on the Organizations page");		
	}	
	
	@Test(groups={"BVT","FullRegression"},dependsOnMethods={"OrganizationsPageOpens"}, description="Verifies a basic organization can be created")
	public void CreateBasicOrganization()
	{		
		/* Set the organization name */
		Date d = new Date();
		basicOrganizationName = "fvt basic organization "+d.getTime();
		basicOrgCountry=1; 
		basicOrgCity=1;
		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();
		
		/* Click create organization button */
		CreateOrganization createOrganizationPage = organizations.clickCreateOrganization();
		
		/* Verify various UI fields appear */
		Assert.assertTrue(createOrganizationPage.isNameDisplayed(),"Name field failed to display on the Create Orgnanization page");
		Assert.assertTrue(createOrganizationPage.isParentOrganizationDisplayed(),"Parent Organization dropdown failed to display on the Create Orgnanization page");
		Assert.assertTrue(createOrganizationPage.isSubmitDisplayed(),"submit button failed to display on the Create Orgnanization page");
		Assert.assertTrue(createOrganizationPage.isSaveDisplayed(),"save button failed to display on the Create Orgnanization page");
		Assert.assertTrue(createOrganizationPage.isCancelDisplayed(),"cancel button failed to display on the Create Orgnanization page");
		
		/* Fill in the required information */
		createOrganizationPage.enterName(basicOrganizationName);		
		
		/* Check if the country and city fields are displayed */
		countryDisplayed = createOrganizationPage.isCountryDropdownDisplayed();
		cityDisplayed = createOrganizationPage.isCityDropdownDisplayed();
		
		/* If country and city fields are displayed, select a value from the dropdown */
		if (countryDisplayed)
			createOrganizationPage.selectCountryByIndex(basicOrgCountry);
		if (cityDisplayed)
			createOrganizationPage.selectCityByIndex(basicOrgCity);
		
		if (countryDisplayed)
			basicOrganizationCountry = createOrganizationPage.getSelectedCountryVisibleText();
		if (cityDisplayed)
			basicOrganizationCity = createOrganizationPage.getSelectedCityVisibleText();
		
		/* Click save */
		createOrganizationPage.clickSave();
		
		/* Verify Organization saved confirmation box appears */
		Assert.assertTrue(createOrganizationPage.isResultMessageDisplayed(),"Organization saved confirmation message failed to display, "/* + createOrganizationPage.getValidationMessage()*/ );
		
		/* Click Organizations in the left hand navigation */
		leftnav.clickOrganizations();
		
		/* Verify new organization appears in the Organizations table */
		Assert.assertTrue(organizations.isOrganizationNameFound(basicOrganizationName),"New organization not found in organizations table");
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(basicOrganizationName);
		
		/* Verify values are correct */
		if (countryDisplayed)
			Assert.assertEquals(organizations.getNthCountryInOrganizationsTable(row), basicOrganizationCountry);
		else
			Assert.assertEquals(organizations.getNthCountryInOrganizationsTable(row), "");
		if (cityDisplayed)
			Assert.assertEquals(organizations.getNthCityInOrganizationsTable(row), basicOrganizationCity);
		else
			Assert.assertEquals(organizations.getNthCityInOrganizationsTable(row), "");
	}
	
		
	@Test(groups={"BVT","FullRegression"},dependsOnMethods={"CreateBasicOrganization"}, description="Verifies a basic organization can be edited")
	public void EditBasicOrganization()
	{	
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();		
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(basicOrganizationName);
		
		/* Click edit */
		organizations.clickEditNthOrganizationsInTable(row);
		CreateOrganization editOrganizationPage = new CreateOrganization(getDriver());
		
		/* Verify UI elements appear */
		Assert.assertTrue(editOrganizationPage.isNameDisplayed(),"Name field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isParentOrganizationDisplayed(),"Parent Organization dropdown failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isSubmitDisplayed(),"submit button failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isSaveDisplayed(),"save button failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isCancelDisplayed(),"cancel button failed to display on the Edit Orgnanization page");
		if (countryDisplayed)
			Assert.assertEquals(editOrganizationPage.getSelectedCountryVisibleText(),basicOrganizationCountry);
		if (cityDisplayed)
			Assert.assertEquals(editOrganizationPage.getSelectedCityVisibleText(),basicOrganizationCity);
		
		
		basicOrgCountry=2;
		basicOrgCity=1;		
		basicOrganizationName = basicOrganizationName + "_EDITED";		
		
		editOrganizationPage.enterName(basicOrganizationName);
		if (countryDisplayed)
			editOrganizationPage.selectCountryByIndex(basicOrgCountry);
		if (cityDisplayed)
			editOrganizationPage.selectCityByIndex(basicOrgCity);
		
		if (countryDisplayed)
			basicOrganizationCountry = editOrganizationPage.getSelectedCountryVisibleText();
		if (cityDisplayed)
			basicOrganizationCity = editOrganizationPage.getSelectedCityVisibleText();
		
		/* Click submit */
		editOrganizationPage.clickSaveAndClose();		
		
		/* Locate which row the edited organization appears */
		row = organizations.getOrganizationRow(basicOrganizationName);
		
		/* Click edit */
		organizations.clickEditNthOrganizationsInTable(row);
		
		/* Verify UI elements appear */
		Assert.assertEquals(editOrganizationPage.getNameValue(), basicOrganizationName);
		if (countryDisplayed)
			Assert.assertEquals(editOrganizationPage.getSelectedCountryVisibleText(),basicOrganizationCountry);
		if (cityDisplayed)
			Assert.assertEquals(editOrganizationPage.getSelectedCityVisibleText(),basicOrganizationCity);
	}
	
	@Test(groups={"BVT","FullRegression"},dependsOnMethods={"EditBasicOrganization"}, description="Verifies a basic organization can be deleted", alwaysRun = true)
	public void DeleteBasicOrganization()
	{	
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();		
		
		/* Locate which row the edited organization appears */
		int row = organizations.getOrganizationRow(basicOrganizationName);
		
		/* Click Delete */
		organizations.clickDeleteNthOrganizationsInTable(row);
		
		/* Click Organizations in the left hand navigation */
		leftnav.clickOrganizations();
		
		/* Verify organization has been deleted */
		Assert.assertFalse(organizations.isOrganizationNameFound(basicOrganizationName),"Organization failed to delete");		
	}

	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"OrganizationsPageOpens"}, description="Verifies a site organization can be created")
	public void CreateSiteOrganization()
	{		
		/* Set the organization name */
		Date d = new Date();
		siteOrganizationName = "fvt Site organization "+d.getTime();
		siteOrganizationType = "Site";
		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();
		
		/* Click create organization button */
		CreateOrganization createOrganizationPage = organizations.clickCreateOrganization();
		
		/* Fill in the required information */
		createOrganizationPage.enterName(siteOrganizationName);
		createOrganizationPage.selectOrganizationTypeByValue(siteOrganizationType);		
		
		/* Click submit */
		createOrganizationPage.clickSaveAndClose();	
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(siteOrganizationName);
		
		/* Verify new organization appears in the Organizations table */
		Assert.assertTrue(organizations.isOrganizationNameFound(siteOrganizationName),"New organization not found in organizations table");
	}
	
	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"CreateSiteOrganization"}, description="Verifies a Site organization can be edited")
	public void EditSiteOrganization()
	{		
		siteStatus = "active";
		additionalInfoOrganizationType = "Group";
		sametimeStatus = "Active";		
		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();		
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(siteOrganizationName);
		
		/* Click edit */
		organizations.clickEditNthOrganizationsInTable(row);
		CreateOrganizationSite editOrganizationPage = new CreateOrganizationSite(getDriver());
		
		/* Verify UI Elements */
		Assert.assertEquals(editOrganizationPage.getNameValue(),siteOrganizationName);
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInOrganizationTypeDropdown(siteOrganizationType),"Incorrect option selected in Organizations Type dropdown");
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInSametimeStatusDropdown(sametimeStatus),"Incorrect option selected in Sametime Status dropdown");
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInOrganizationTypeAdditionalInformationDropdown(additionalInfoOrganizationType),"Incorrect option selected in Organizations Type dropdown in the Additional Information section");
		Assert.assertTrue(editOrganizationPage.isSubmitDisplayed(),"submit button failed to display");
		Assert.assertTrue(editOrganizationPage.isSaveDisplayed(),"save button failed to display");
		Assert.assertTrue(editOrganizationPage.isCancelDisplayed(),"cancel button failed to display");
		
		/* Update some fields */
		siteOrganizationName = siteOrganizationName + "_EDITED";
		siteStatus = "disabled";
		sametimeStatus = "Disabled";
		
		editOrganizationPage.enterName(siteOrganizationName);
		editOrganizationPage.selectSametimeStatusByValue(sametimeStatus);
		
		/* Click save and close */
		editOrganizationPage.clickSaveAndClose();
		
		/* Locate which row the organization appears */
		row = organizations.getOrganizationRow(siteOrganizationName);
		
		/* Click edit */
		organizations.clickEditNthOrganizationsInTable(row);
		
		/* Verify UI Elements */
		Assert.assertEquals(editOrganizationPage.getNameValue(),siteOrganizationName);
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInOrganizationTypeDropdown(siteOrganizationType),"Incorrect option selected in Organization Type dropdown");
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInSametimeStatusDropdown(sametimeStatus),"Incorrect option selected in Sametime Status dropdown");
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInOrganizationTypeAdditionalInformationDropdown(additionalInfoOrganizationType),"Incorrect option selected in Organization Type dropdown in the Additional Information section");
	}

	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"EditSiteOrganization"}, description="Verifies a Site organization can be deleted", alwaysRun = true)
	public void DeleteSiteOrganization()
	{		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();		
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(siteOrganizationName);
		
		/* Click Delete */
		organizations.clickDeleteNthOrganizationsInTable(row);
		
		/* Click Organizations in the left hand navigation */
		leftnav.clickOrganizations();
		
		/* Verify organization has been deleted */
		Assert.assertFalse(organizations.isOrganizationNameFound(siteOrganizationName),"Organization failed to delete");		
	}

	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"OrganizationsPageOpens"}, description="Verifies a Hospital organization can be created")
	public void CreateHospitalOrganization()
	{		
		/* Set the organization name */
		Date d = new Date();
		hospitalOrganizationName = "fvt Hospital organization "+d.getTime();
		hospitalOrganizationType = "Hospital";
		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();
		
		/* Click create organization button */
		CreateOrganization createOrganizationPage = organizations.clickCreateOrganization();
		
		/* Fill in the required information */
		createOrganizationPage.enterName(hospitalOrganizationName);
		createOrganizationPage.selectOrganizationTypeByValue(hospitalOrganizationType);		
		
		/* Click submit */
		createOrganizationPage.clickSaveAndClose();
		
		/* Verify new organization appears in the Organizations table */
		Assert.assertTrue(organizations.isOrganizationNameFound(hospitalOrganizationName),"New organization not found in organizations table");
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(hospitalOrganizationName);
		
		/* Verify UI Elements */
		Assert.assertEquals(organizations.getNthNameInOrganizationsTable(row),hospitalOrganizationName);
	}

	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"CreateHospitalOrganization"}, description="Verifies a Hospital organization can be edited")
	public void EditHospitalOrganization()
	{		
		/* Variables */
		hospitalStreetAddress1 = "hospital_street_ad1_"+d.getTime();
		hospitalStreetAddress2 = "hospital_street_ad2_"+d.getTime();
		hospitalCity = "hospital_city_"+d.getTime();
		hospitalState = "hospital_state_"+d.getTime();
		hospitalCountry = "hospital_country_"+d.getTime();
		hospitalStatus = "active";		
		
		/* Click Members - Members in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();		
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(hospitalOrganizationName);
		
		/* Click edit */
		organizations.clickEditNthOrganizationsInTable(row);
		CreateOrganizationHospital editOrganizationPage = new CreateOrganizationHospital(getDriver());
		
		/* Verify UI elements appear */
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInOrganizationTypeDropdown(hospitalOrganizationType),"Incorrect option selected in Organizations Type dropdown");
		Assert.assertTrue(editOrganizationPage.isStreetAddress1Displayed(),"Street Address 1 field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isStreetAddress2Displayed(),"Street Address 2 field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isCityDisplayed(),"City field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isStateDisplayed(),"State field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isCountryDisplayed(),"Country field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isNameDisplayed(),"Name field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isParentOrganizationDisplayed(),"Parent Organization dropdown failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isOrganizationTypeDisplayed(),"Organization Type dropdown failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isSubmitDisplayed(),"submit button failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isSaveDisplayed(),"save button failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isCancelDisplayed(),"cancel button failed to display on the Edit Orgnanization page");
		
		/* Verify Hospital Specific fields display */
		Assert.assertTrue(editOrganizationPage.isHospitalTypeDisplayed(),"Hospital Type dropdown failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isUniversityNameDisplayed(),"University Name field failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricResidencyCheckboxDisplayed(),"Pediatric Residency  checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricICUFellowshipCheckboxCheckboxDisplayed(),"Pediatric ICU Fellowship checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isNoneCheckboxCheckboxDisplayed(),"None checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricICUBedsDisplayed(),"Pediatric ICU Beds dropdown failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricICUTypeDisplayed(),"Pediatric ICU Type dropdown failed to display on the Edit Orgnanization page");		
		Assert.assertTrue(editOrganizationPage.isHospitalSizeDisplayed(),"Hospital Size dropdown failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isNeonatalPatientsCheckboxDisplayed(),"Neonatal patients checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPatientsWithCongenitalCardiacDiseasesCheckboxDisplayed(),"Patients with congenital cardiac diseases checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricPatientsWithBurnInjuriesCheckboxDisplayed(),"Pediatric patients with burn injuries checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricPatientsWithTraumaticInjuriesCheckboxDisplayed(),"Pediatric patients with traumatic injuries checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricPatientsFollowingSurgeryCheckboxDisplayed(),"Pediatric patients following surgery checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPediatricPatientsFollowingCardiacSurgeryCheckboxDisplayed(),"Pediatric patients following cardiac surgery checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isArterialLinesCheckboxDisplayed(),"Arterial Lines  checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isBubbleCPAPCheckboxDisplayed(),"Bubble CPAP checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isCentralVenousCathetersCheckboxDisplayed(),"Central Venous Catheters checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isExtraCorporealMembraneOxygenationCheckboxDisplayed(),"ExtraCorporeal Membrane Oxygenation checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isHemodialysisCheckboxDisplayed(),"Hemodialysis checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isHighFrequencyOscillatoryVentilatorCheckboxDisplayed(),"High Frequency Oscillatory Ventilator checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isMechanicalVentilatorCheckboxDisplayed(),"Mechanical Ventilator checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isNonInvasivePositivePressureVentilationCheckboxDisplayed(),"Non-invasive Positive Pressure Ventilation checkbox failed to display on the Edit Orgnanization page");
		Assert.assertTrue(editOrganizationPage.isPeritonealDialysisCheckboxDisplayed(),"Peritoneal Dialysis checkbox failed to display on the Edit Orgnanization page");

		
		hospitalOrganizationName = hospitalOrganizationName + "_EDITED";
		hospitalStatus = "disabled";
		hospitalType = "RURAL";
		universityName = "uni_name_"+d.getTime();
		pediatricICUBeds = "GT20";
		pediatricICUType = "medsurgical";
		hospitalSize = "251TO500";
		
		editOrganizationPage.enterName(hospitalOrganizationName);
		editOrganizationPage.enterStreetAddress1(hospitalStreetAddress1);
		editOrganizationPage.enterStreetAddress2(hospitalStreetAddress2);
		editOrganizationPage.enterCity(hospitalCity);
		editOrganizationPage.enterState(hospitalState);
		editOrganizationPage.enterCountry(hospitalCountry);
		editOrganizationPage.selectHospitalTypeByValue(hospitalType);
		editOrganizationPage.enterUniversityName(universityName);
		editOrganizationPage.selectPediatricICUFellowshipCheckbox();
		editOrganizationPage.selectPediatricICUBedsByValue(pediatricICUBeds);
		editOrganizationPage.selectPediatricICUTypeByValue(pediatricICUType);
		editOrganizationPage.selectHospitalSizeByValue(hospitalSize);
		editOrganizationPage.selectPediatricPatientsFollowingCardiacSurgeryCheckbox();
		editOrganizationPage.selectArterialLinesCheckbox();
		editOrganizationPage.selectPeritonealDialysisCheckbox();		
	
		/* Click submit */
		editOrganizationPage.clickSaveAndClose();
		
		/* Locate which row the edited organization appears */
		row = organizations.getOrganizationRow(hospitalOrganizationName);
		
		/* Click edit */
		organizations.clickEditNthOrganizationsInTable(row);
		
		/* Verify UI element values */
		Assert.assertEquals(editOrganizationPage.getNameValue(), hospitalOrganizationName);
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInOrganizationTypeDropdown(hospitalOrganizationType));
		Assert.assertEquals(editOrganizationPage.getStreetAddress1Value(), hospitalStreetAddress1);
		Assert.assertEquals(editOrganizationPage.getStreetAddress2Value(), hospitalStreetAddress2);
		Assert.assertEquals(editOrganizationPage.getCityValue(), hospitalCity);
		Assert.assertEquals(editOrganizationPage.getStateValue(), hospitalState);
		Assert.assertEquals(editOrganizationPage.getCountryValue(), hospitalCountry);
		
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInHospitalTypeDropdown(hospitalType), "Incorrect option selected in Hospital Type dropdown");
		Assert.assertEquals(editOrganizationPage.getUniversityName(),universityName);

		Assert.assertTrue(editOrganizationPage.isPediatricICUFellowshipCheckboxChecked(),"Pediatric ICU Fellowship checkbox not checked");
		Assert.assertFalse(editOrganizationPage.isPediatricResidencyCheckboxChecked(),"Pediatric Residency checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isNoneCheckboxChecked(),"None checkbox checked in error");

		Assert.assertTrue(editOrganizationPage.isOptionSelectedInPediatricICUBedsDropdown(pediatricICUBeds), "Incorrect option selected in Pediatric ICU Beds dropdown");
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInPediatricICUTypeDropdown(pediatricICUType), "Incorrect option selected in Pediatric ICU Type dropdown");
		Assert.assertTrue(editOrganizationPage.isOptionSelectedInHospitalSizeDropdown(hospitalSize), "Incorrect option selected in Hospital Type dropdown");
		
		/* Verify Patient Population checkboxes */
		Assert.assertFalse(editOrganizationPage.isNeonatalPatientsCheckboxChecked(),"Neonatal Patients checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isPatientsWithCongenitalCardiacDiseasesCheckboxChecked(),"Patients With Congenital Cardiac Diseases checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isPediatricPatientsWithBurnInjuriesCheckboxChecked(),"Pediatric Patients With Burn Injuries checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isPediatricPatientsWithTraumaticInjuriesCheckboxChecked(),"Pediatric Patients With Traumatic Injuries checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isPediatricPatientsFollowingSurgeryCheckboxChecked(),"Pediatric Patients Following Surgery checkbox checked in error");
		Assert.assertTrue(editOrganizationPage.isPediatricPatientsFollowingCardiacSurgeryCheckboxChecked(),"Pediatric Patients Following Cardiac Surgery checkbox not checked however it should have been");
		
		/* Verify Equipment type checkboxes */
		Assert.assertTrue(editOrganizationPage.isArterialLinesCheckboxChecked(),"Arterial Lines checkbox not checked however it should have been");
		Assert.assertFalse(editOrganizationPage.isBubbleCPAPCheckboxChecked(),"Bubble CPAP checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isCentralVenousCathetersCheckboxChecked(),"Central Venous Catheters checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isExtraCorporealMembraneOxygenationCheckboxChecked(),"Extra Corporeal Membrane Oxygenation checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isHemodialysisCheckboxChecked(),"Hemodialysis checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isHighFrequencyOscillatoryVentilatorCheckboxChecked(),"High Frequency Oscillatory Ventilator checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isMechanicalVentilatorCheckboxChecked(),"Mechanical Ventilator checkbox checked in error");
		Assert.assertFalse(editOrganizationPage.isNonInvasivePositivePressureVentilationCheckboxChecked(),"Non Invasive Positive Pressure Ventilation checkbox checked in error");
		Assert.assertTrue(editOrganizationPage.isPeritonealDialysisCheckboxChecked(),"Peritoneal Dialysis checkbox not checked however it should have been");
	}

	@Test(enabled=false,groups={"FullRegression"},dependsOnMethods={"EditHospitalOrganization"}, description="Verifies a Hospital organization can be deleted", alwaysRun = true)
	public void DeleteHospitalOrganization()
	{		
		/* Click Members - Organizations in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		Organizations organizations = leftnav.clickOrganizations();		
		
		/* Locate which row the new organization appears */
		int row = organizations.getOrganizationRow(hospitalOrganizationName);
		
		/* Click Delete */
		organizations.clickDeleteNthOrganizationsInTable(row);
		
		/* Click Organizations in the left hand navigation */
		leftnav.clickOrganizations();
		
		/* Verify organization has been deleted */
		Assert.assertFalse(organizations.isOrganizationNameFound(hospitalOrganizationName),"Organization failed to delete");		
	}
}
