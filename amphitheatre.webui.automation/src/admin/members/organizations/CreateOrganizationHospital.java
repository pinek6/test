package admin.members.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class CreateOrganizationHospital extends CreateOrganizationAddress {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");	
	private By hospitalType = By.id("HospType");
	private By universityName = By.id("HospUnivName");
	private By pediatricResidencyCheckbox = By.cssSelector("input[value='pedResidency']");
	private By pediatricICUFellowshipCheckbox = By.cssSelector("input[value='pedFellowship']");
	private By noneCheckbox = By.cssSelector("input[value='None']");
	private By pediatricICUBeds = By.id("HospPedICUBeds");
	private By pediatricICUType = By.id("HospPedICUType");
	private By hospitalSize = By.id("HospSize");
	
	/* Patient Population checkboxes */
	private By neonatalPatients = By.cssSelector("input[value='NEONATAL']");
	private By patientsWithCongenitalCardiacDiseases = By.cssSelector("input[value='CARDIAC']");
	private By pediatricPatientsWithBurnInjuries = By.cssSelector("input[value='PEDBURN']");
	private By pediatricPatientsWithTraumaticInjuries = By.cssSelector("input[value='TRAUMA']");
	private By pediatricPatientsFollowingSurgery  = By.cssSelector("input[value='PEDSURGERY']");
	private By pediatricPatientsFollowingCardiacSurgery  = By.cssSelector("input[value='PEDCARDSURG']");
	
	/* Equipment type checkboxes */
	private By arterialLines = By.cssSelector("input[value='ARTERIAL_LINES']");
	private By bubbleCPAP = By.cssSelector("input[value='BUBBLE_CPAP']");
	private By centralVenousCatheters = By.cssSelector("input[value='CV_CATHETERS']");
	private By extraCorporealMembraneOxygenation = By.cssSelector("input[value='ECMO']");
	private By hemodialysis = By.cssSelector("input[value='HEMODIALYSYS']");
	private By highFrequencyOscillatoryVentilator = By.cssSelector("input[value='HFOV']");
	private By mechanicalVentilator = By.cssSelector("input[value='MECH_VENT']");
	private By nonInvasivePositivePressureVentilation = By.cssSelector("input[value='NI_VENTILATOR']");
	private By peritonealDialysis = By.cssSelector("input[value='PERITODIALYSIS']");
	
	public CreateOrganizationHospital (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}	
	
	/**
	 * 
	 * @param value Supported options are GOVERNMENT, PRIVATE, RURAL or TEACHING
	 * @return
	 */
	public CreateOrganizationHospital selectHospitalTypeByValue(String value)
	{
		
		
		new Select(driver.findElement(hospitalType)).selectByValue(value);
		
		return this;
	}
	
	public CreateOrganizationHospital enterUniversityName(String name)
	{
		
		
		driver.findElement(universityName).sendKeys(name);
		
		return this;
	}
	
	public CreateOrganizationHospital selectPediatricResidencyCheckbox()
	{
		
		
		driver.findElement(pediatricResidencyCheckbox).click();
		
		return this;
	}
	
	public CreateOrganizationHospital selectPediatricICUFellowshipCheckbox()
	{
		
		
		driver.findElement(pediatricICUFellowshipCheckbox).click();
		
		return this;
	}
	
	public CreateOrganizationHospital selectNoneCheckbox()
	{
		
		
		driver.findElement(noneCheckbox).click();
		
		return this;
	}
	
	/**
	 * 
	 * @param value Supported values are LT10, 10T020 or GT20
	 * @return
	 */
	public CreateOrganizationHospital selectPediatricICUBedsByValue(String value)
	{
		
		
		new Select(driver.findElement(pediatricICUBeds)).selectByValue(value);
		
		return this;
	}
	
	/**
	 * 
	 * @param value Supported values are open, closed, mixed, medical, surgical, medsurgical, cardiac, truama, burn, neuorlogy
	 * @return
	 */
	public CreateOrganizationHospital selectPediatricICUTypeByValue(String value)
	{
		
		
		new Select(driver.findElement(pediatricICUType)).selectByValue(value);
		
		return this;
	}
	
	/**
	 * 
	 * @param value Supported values are LT100, 100TO250, 251TO500, GT500
	 * @return
	 */
	public CreateOrganizationHospital selectHospitalSizeByValue(String value)
	{
		
		
		new Select(driver.findElement(hospitalSize)).selectByValue(value);
		
		return this;
	}
	
	public CreateOrganizationHospital selectNeonatalPatientsCheckbox()
	{
		
		
		driver.findElement(neonatalPatients).click();
		
		return this;
	}

	public CreateOrganizationHospital selectPatientsWithCongenitalCardiacDiseasesCheckbox()
	{
		
		
		driver.findElement(patientsWithCongenitalCardiacDiseases).click();
		
		return this;
	}

	public CreateOrganizationHospital selectPediatricPatientsWithBurnInjuriesCheckbox()
	{
		
		
		driver.findElement(pediatricPatientsWithBurnInjuries).click();
		
		return this;
	}

	public CreateOrganizationHospital selectPediatricPatientsWithTraumaticInjuriesCheckbox()
	{
		
		
		driver.findElement(pediatricPatientsWithTraumaticInjuries).click();
		
		return this;
	}

	public CreateOrganizationHospital selectPediatricPatientsFollowingSurgeryCheckbox()
	{
		
		
		driver.findElement(pediatricPatientsFollowingSurgery).click();
		
		return this;
	}

	public CreateOrganizationHospital selectPediatricPatientsFollowingCardiacSurgeryCheckbox()
	{
		
		
		driver.findElement(pediatricPatientsFollowingCardiacSurgery).click();
		
		return this;
	}
	
	public CreateOrganizationHospital selectArterialLinesCheckbox()
	{
		
		
		driver.findElement(arterialLines).click();
		
		return this;
	}

	public CreateOrganizationHospital selectBubbleCPAPCheckbox()
	{
		
		
		driver.findElement(bubbleCPAP).click();
		
		return this;
	}

	public CreateOrganizationHospital selectCentralVenousCathetersCheckbox()
	{
		
		
		driver.findElement(centralVenousCatheters).click();
		
		return this;
	}

	public CreateOrganizationHospital selectExtraCorporealMembraneOxygenationCheckbox()
	{
		
		
		driver.findElement(extraCorporealMembraneOxygenation).click();
		
		return this;
	}

	public CreateOrganizationHospital selectHemodialysisCheckbox()
	{
		
		
		driver.findElement(hemodialysis).click();
		
		return this;
	}

	public CreateOrganizationHospital selectHighFrequencyOscillatoryVentilatorCheckbox()
	{
		
		
		driver.findElement(highFrequencyOscillatoryVentilator).click();
		
		return this;
	}

	public CreateOrganizationHospital selectMechanicalVentilatorCheckbox()
	{
		
		
		driver.findElement(mechanicalVentilator).click();
		
		return this;
	}

	public CreateOrganizationHospital selectNonInvasivePositivePressureVentilationCheckbox()
	{
		
		
		driver.findElement(nonInvasivePositivePressureVentilation).click();
		
		return this;
	}

	public CreateOrganizationHospital selectPeritonealDialysisCheckbox()
	{
		
		
		driver.findElement(peritonealDialysis).click();
		
		return this;
	}
	
	public Boolean isHospitalTypeDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(hospitalType, driver);
		
		return found;
	}
	
	public Boolean isUniversityNameDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(universityName, driver);
		
		return found;
	}
	
	public Boolean isPediatricResidencyCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricResidencyCheckbox, driver);
		
		return found;
	}
	
	public Boolean isPediatricICUFellowshipCheckboxCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricICUFellowshipCheckbox, driver);
		
		return found;
	}
	
	public Boolean isNoneCheckboxCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricICUFellowshipCheckbox, driver);
		
		return found;
	}
	
	public Boolean isPediatricICUBedsDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricICUBeds, driver);
		
		return found;
	}
	
	public Boolean isPediatricICUTypeDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricICUType, driver);
		
		return found;
	}
	
	public Boolean isHospitalSizeDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(hospitalSize, driver);
		
		return found;
	}

	public Boolean isNeonatalPatientsCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(neonatalPatients, driver);
		
		return found;
	}

	public Boolean isPatientsWithCongenitalCardiacDiseasesCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(patientsWithCongenitalCardiacDiseases, driver);
		
		return found;
	}

	public Boolean isPediatricPatientsWithBurnInjuriesCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricPatientsWithBurnInjuries, driver);
		
		return found;
	}

	public Boolean isPediatricPatientsWithTraumaticInjuriesCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricPatientsWithTraumaticInjuries, driver);
		
		return found;
	}

	public Boolean isPediatricPatientsFollowingSurgeryCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricPatientsFollowingSurgery, driver);
		
		return found;
	}

	public Boolean isPediatricPatientsFollowingCardiacSurgeryCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(pediatricPatientsFollowingCardiacSurgery, driver);
		
		return found;
	}
	
	public Boolean isArterialLinesCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(arterialLines, driver);
		
		return found;
	}

	public Boolean isBubbleCPAPCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(bubbleCPAP, driver);
		
		return found;
	}

	public Boolean isCentralVenousCathetersCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(centralVenousCatheters, driver);
		
		return found;
	}

	public Boolean isExtraCorporealMembraneOxygenationCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(extraCorporealMembraneOxygenation, driver);
		
		return found;
	}

	public Boolean isHemodialysisCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(hemodialysis, driver);
		
		return found;
	}

	public Boolean isHighFrequencyOscillatoryVentilatorCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(highFrequencyOscillatoryVentilator, driver);
		
		return found;
	}

	public Boolean isMechanicalVentilatorCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(mechanicalVentilator, driver);
		
		return found;
	}

	public Boolean isNonInvasivePositivePressureVentilationCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(nonInvasivePositivePressureVentilation, driver);
		
		return found;
	}

	public Boolean isPeritonealDialysisCheckboxDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(peritonealDialysis, driver);
		
		return found;
	}
	
	/**
	 * 
	 * @param option Supported options are GOVERNMENT, PRIVATE, RURAL or TEACHING
	 * @return
	 */
	public Boolean isOptionSelectedInHospitalTypeDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(hospitalType));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
	
	public String getUniversityName()
	{
		
		
		String found = driver.findElement(universityName).getAttribute("value");
		
		return found;
	}
	
	public Boolean isPediatricResidencyCheckboxChecked()
	{
		
		
		boolean activeRadioButtonChecked = driver.findElement(pediatricResidencyCheckbox).getAttribute("checked") == null  ? false : true;
		
		return activeRadioButtonChecked;
	}

	public Boolean isPediatricICUFellowshipCheckboxChecked()
	{
		
		
		boolean activeRadioButtonChecked = driver.findElement(pediatricICUFellowshipCheckbox).getAttribute("checked") == null  ? false : true;
		
		return activeRadioButtonChecked;
	}
	
	public Boolean isNoneCheckboxChecked()
	{
		
		
		boolean activeRadioButtonChecked = driver.findElement(noneCheckbox).getAttribute("checked") == null  ? false : true;
		
		return activeRadioButtonChecked;
	}
	
	/**
	 * 
	 * @param value Supported values are LT10, 10T020 or GT20
	 * @return
	 */
	public Boolean isOptionSelectedInPediatricICUBedsDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(pediatricICUBeds));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
	
	/**
	 * 
	 * @param value Supported values are open, closed, mixed, medical, surgical, medsurgical, cardiac, truama, burn, neuorlogy
	 * @return
	 */
	public Boolean isOptionSelectedInPediatricICUTypeDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(pediatricICUType));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
	
	/**
	 * 
	 * @param value Supported values are LT100, 100TO250, 251TO500, GT500
	 * @return
	 */
	public Boolean isOptionSelectedInHospitalSizeDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(hospitalSize));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
	
	public Boolean isNeonatalPatientsCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(neonatalPatients).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isPatientsWithCongenitalCardiacDiseasesCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(patientsWithCongenitalCardiacDiseases).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isPediatricPatientsWithBurnInjuriesCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(pediatricPatientsWithBurnInjuries).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isPediatricPatientsWithTraumaticInjuriesCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(pediatricPatientsWithTraumaticInjuries).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isPediatricPatientsFollowingSurgeryCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(pediatricPatientsFollowingSurgery).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isPediatricPatientsFollowingCardiacSurgeryCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(pediatricPatientsFollowingCardiacSurgery).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}
	
	public Boolean isArterialLinesCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(arterialLines).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isBubbleCPAPCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(bubbleCPAP).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isCentralVenousCathetersCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(centralVenousCatheters).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isExtraCorporealMembraneOxygenationCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(extraCorporealMembraneOxygenation).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isHemodialysisCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(hemodialysis).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isHighFrequencyOscillatoryVentilatorCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(highFrequencyOscillatoryVentilator).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isMechanicalVentilatorCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(mechanicalVentilator).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isNonInvasivePositivePressureVentilationCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(nonInvasivePositivePressureVentilation).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}

	public Boolean isPeritonealDialysisCheckboxChecked()
	{
		
		
		boolean checked = driver.findElement(peritonealDialysis).getAttribute("checked") == null  ? false : true;
		
		return checked;
	}
}
