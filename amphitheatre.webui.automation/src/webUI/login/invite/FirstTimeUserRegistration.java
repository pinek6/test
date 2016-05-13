package webUI.login.invite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class FirstTimeUserRegistration {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);

	/* Element Locators */
	private By gender = By.id("gender");
	private By emailAddress = By.id("email");
	private By password = By.id("password");
	private By confirmPassword = By.id("confirm");
	private By firstname = By.cssSelector("input[id^='first'][id$='ame']");
	private By lastName = By.cssSelector("input[id^='last'][id$='ame']");
	private By occupation = By.id("occupation");
	private By postGraduateExperience = By.id("postGraduateExperience");
	private String specialty = "div[id='fieldRowbchSpecialty'] > div > div:nth-child(3) > span > div";
	private String specialtyCheckbox = " > input";
	private String specialtyLabel = " > label";
	private String subSpecialty = "div[id='fieldRowbchSubSpecialty'] > div > div:nth-child(3) > span > div";
	private String subSpecialtyCheckbox = " > input";
	private String subSpecialtyLabel = " > label";
	private String interests = "div[id='fieldRowinterests'] > div > div:nth-child(3) > span > div";
	private String interestCheckbox = " > input";
	private String interestLabel = " > label";
	private By researchAndClinicalInterests = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	private By organization = By.id("orgId");
	private By country = By.id("country");
	private By city = By.id("city");
	private By otherOrganization = By.id("otherorgId");
	private By otherCity = By.id("othercity");
	// private By hospital = By.id("hospital");
	// private By position = By.id("position");
	// private By title = By.id("title");
	// private By pediatricsCheckbox = By.id("Subspecialty_PEDIATRICS");
	// private By generalMedicineCheckbox = By.id("Subspecialty_GENERAL");
	// private By anesthesiaCheckbox = By.id("Subspecialty_ANESTHESIA");
	// private By criticalCareSubspecialtyTrainingCheckbox =
	// By.id("Subspecialty_CRITICAL");
	// private By neonatologyCheckbox = By.id("Subspecialty_NEONATOLOGY");
	private By otherCheckbox = By.id("Subspecialty_OTHER");
	// private By otherInputField = By.id("subspecOther");
	// private By experience = By.id("experience");
	private By healthcareProfessionalVerification = By.id("certifiedProviderY");
	private By acceptTermsAndConditions = By.id("termsOfUseY");
	private By disclaimerForCommunicationAndPosts = By
			.id("communicationRulesY");
	private By registerButton = By.id("btnSubmit");

	public FirstTimeUserRegistration(WebDriver driver) {
		this.driver = driver;
	}

	public Boolean isGenderDisplayed() {
		return commonMethods.isElementDisplayed(gender, driver);
	}

	public Boolean isEmailAddressDisplayed() {
		return commonMethods.isElementDisplayed(emailAddress, driver);
	}

	public Boolean isPasswordDisplayed() {
		return commonMethods.isElementDisplayed(password, driver);
	}

	public Boolean isConfirmPasswordDisplayed() {
		return commonMethods.isElementDisplayed(confirmPassword, driver);
	}

	public Boolean isFirstnameDisplayed() {
		return commonMethods.isElementDisplayed(firstname, driver);
	}

	public Boolean isLastNameDisplayed() {
		return commonMethods.isElementDisplayed(lastName, driver);
	}
	
	public Boolean isYourOccupationDisplayed() {
		return commonMethods.isElementDisplayed(occupation, driver);
	}
	
	public Boolean isYearsOfPostGradExperienceDisplayed() {
		return commonMethods.isElementDisplayed(postGraduateExperience, driver);
	}
	
	public Boolean isSpecialtyDisplayed() {
		return commonMethods.isElementDisplayed(By.cssSelector(specialty), driver);
	}
	
	public Boolean isSubSpecialtyDisplayed() {
		return commonMethods.isElementDisplayed(By.cssSelector(subSpecialty), driver);
	}
	
	public Boolean isResearchAndClinicalInterestsDisplayed() {
		return commonMethods.isElementDisplayed(researchAndClinicalInterests, driver);
	}
	
	public Boolean isCountryDisplayed() {
		return commonMethods.isElementDisplayed(country, driver);
	}
	
	public Boolean isCityDisplayed() {
		return commonMethods.isElementDisplayed(city, driver);
	}
	
	public Boolean isHospitalOrInstitutionWhereYouWorkDisplayed()
	{
		return commonMethods.isElementDisplayed(organization, driver);
	}

	// public Boolean isHospitalDisplayed(){return
	// commonMethods.isElementDisplayed(hospital, driver);}
	// public Boolean isPositionDisplayed(){return
	// commonMethods.isElementDisplayed(position, driver);}
	// public Boolean isTitleDisplayed(){return
	// commonMethods.isElementDisplayed(title, driver);}
	// public Boolean isPediatricsCheckboxDisplayed(){return
	// commonMethods.isElementDisplayed(pediatricsCheckbox, driver);}
	// public Boolean isGeneralMedicineCheckboxDisplayed(){return
	// commonMethods.isElementDisplayed(generalMedicineCheckbox, driver);}
	// public Boolean isAnesthesiaCheckboxDisplayed(){return
	// commonMethods.isElementDisplayed(anesthesiaCheckbox, driver);}
	// public Boolean
	// isCriticalCareSubspecialtyTrainingCheckboxDisplayed(){return
	// commonMethods.isElementDisplayed(criticalCareSubspecialtyTrainingCheckbox,
	// driver);}
	// public Boolean isNeonatologyCheckboxDisplayed(){return
	// commonMethods.isElementDisplayed(neonatologyCheckbox, driver);}
	public Boolean isOtherCheckboxDisplayed() {
		return commonMethods.isElementDisplayed(otherCheckbox, driver);
	}

	// public Boolean isOtherInputFieldDisplayed(){return
	// commonMethods.isElementDisplayed(otherInputField, driver);}
	// public Boolean isExperienceDisplayed(){return
	// commonMethods.isElementDisplayed(experience, driver);}
	public Boolean isInterestsDisplayed() {
		return commonMethods.isElementDisplayed(By.cssSelector(interests),
				driver);
	}

	public Boolean isHealthcareProfessionalVerificationDisplayed() {
		return commonMethods.isElementDisplayed(
				healthcareProfessionalVerification, driver);
	}

	public Boolean isAcceptTermsAndConditionsDisplayed() {
		return commonMethods.isElementDisplayed(acceptTermsAndConditions,
				driver);
	}

	public Boolean isDisclaimerForCommunicationAndPostsDisplayed() {
		return commonMethods.isElementDisplayed(
				disclaimerForCommunicationAndPosts, driver);
	}

	public Boolean isRegisterButtonDisplayed() {
		return commonMethods.isElementDisplayed(registerButton, driver);
	}

	public FirstTimeUserRegistration selectGenderByValue(String g) {
		new Select(driver.findElement(gender)).selectByValue(g);
		return this;
	}

	public FirstTimeUserRegistration enterEmailAddress(String email) {
		driver.findElement(emailAddress).sendKeys(email);
		return this;
	}

	public FirstTimeUserRegistration enterPassword(String pw) {
		driver.findElement(password).sendKeys(pw);
		return this;
	}

	public FirstTimeUserRegistration enterConfirmPassword(String cnfpw) {
		driver.findElement(confirmPassword).sendKeys(cnfpw);
		return this;
	}

	public FirstTimeUserRegistration enterFirstName(String fname) {
		driver.findElement(firstname).sendKeys(fname);
		return this;
	}

	public FirstTimeUserRegistration enterLastName(String lname) {
		driver.findElement(lastName).sendKeys(lname);
		return this;
	}

	public FirstTimeUserRegistration selectYourOccupationByVisibleText(
			String occ) {
		new Select(driver.findElement(occupation)).selectByVisibleText(occ);
		return this;
	}

	public FirstTimeUserRegistration selectYourOccupationByValue(String val) {
		new Select(driver.findElement(occupation)).selectByValue(val);
		return this;
	}

	public FirstTimeUserRegistration selectYourOccupationByIndex(int index) {
		new Select(driver.findElement(occupation)).selectByIndex(index);
		return this;
	}

	public String getSelectedGenderVisibleText() {
		String selectedOption = "";
		WebElement select = driver.findElement(gender);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));
		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getText();
		}
		return selectedOption;
	}

	public String getSelectedOccupationVisibleText() {
		String selectedOption = "";
		WebElement select = driver.findElement(occupation);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getText();
		}
		return selectedOption;
	}

	public int getSelectedOccupationIndex() {
		int selectedOption = -1;
		WebElement select = driver.findElement(occupation);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));
		int i = 1;
		for (WebElement element : allElements) {
			if (element.isSelected()) {
				selectedOption = i;
			}
		}
		return selectedOption;
	}

	public String getSelectedOccupationValue() {
		String selectedOption = "";
		WebElement select = driver.findElement(occupation);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));
		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getAttribute("value");
		}
		return selectedOption;
	}

	public void clickRegister() {
		driver.findElement(registerButton).click();
	}

	public FirstTimeUserRegistration selectPostGraduateExperienceByVisibleText(
			String occ) {
		new Select(driver.findElement(postGraduateExperience))
				.selectByVisibleText(occ);
		return this;
	}

	/**
	 * 
	 * @param val
	 *            Accepted values are less_than_1, 1_to_5, 6_to_10, 10_to_15,
	 *            15_to_20, more_than_20
	 * @return
	 */
	public FirstTimeUserRegistration selectPostGraduateExperienceByValue(
			String val) {
		new Select(driver.findElement(postGraduateExperience))
				.selectByValue(val);
		return this;
	}

	public FirstTimeUserRegistration selectPostGraduateExperienceByIndex(
			int index) {
		new Select(driver.findElement(postGraduateExperience))
				.selectByIndex(index);
		return this;
	}

	public String getSelectedPostGraduateExperienceVisibleText() {
		String selectedOption = "";

		WebElement select = driver.findElement(postGraduateExperience);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getText();
		}
		return selectedOption;
	}

	public int getSelectedPostGraduateExperienceIndex() {
		int selectedOption = -1;

		WebElement select = driver.findElement(postGraduateExperience);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));
		int i = 1;
		for (WebElement element : allElements) {
			if (element.isSelected()) {
				selectedOption = i;
			}
		}
		return selectedOption;
	}

	public String getSelectedPostGraduateExperienceValue() {
		String selectedOption = "";

		WebElement select = driver.findElement(postGraduateExperience);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getAttribute("value");
		}
		return selectedOption;
	}

	public FirstTimeUserRegistration selectNthSpecialty(int n) {
		driver.findElement(
				By.cssSelector(specialty + ":nth-child(" + n + ")"
						+ specialtyCheckbox)).click();
		return this;
	}

	public String getNthSpecialty(int n) {
		String s = driver.findElement(
				By.cssSelector(specialty + ":nth-child(" + n + ")"
						+ specialtyLabel)).getText();
		return s;
	}

	public int getCountOfSpecialties() {
		int count = driver.findElements(
				By.cssSelector(specialty + specialtyLabel)).size();
		return count;
	}

	public Boolean isNthSpecialtyCheckboxChecked(int n) {
		boolean isExpertiseChecked = driver.findElement(
				By.cssSelector(specialty + ":nth-child(" + n + ")"
						+ specialtyCheckbox)).getAttribute("checked") == null ? false
				: true;
		return isExpertiseChecked;
	}

	public FirstTimeUserRegistration selectNthSubSpecialty(int n) {
		driver.findElement(
				By.cssSelector(subSpecialty + ":nth-child(" + n + ")"
						+ subSpecialtyCheckbox)).click();
		return this;
	}

	public String getNthSubSpecialty(int n) {
		String s = driver.findElement(
				By.cssSelector(subSpecialty + ":nth-child(" + n + ")"
						+ subSpecialtyLabel)).getText();
		return s;
	}

	public int getCountOfSubSpecialties() {
		int count = driver.findElements(
				By.cssSelector(subSpecialty + subSpecialtyLabel)).size();
		return count;
	}

	public Boolean isNthSubSpecialtyCheckboxChecked(int n) {
		boolean isExpertiseChecked = driver.findElement(
				By.cssSelector(subSpecialty + ":nth-child(" + n + ")"
						+ subSpecialtyCheckbox)).getAttribute("checked") == null ? false
				: true;
		return isExpertiseChecked;
	}

	public FirstTimeUserRegistration selectNthInterest(int n) {
		driver.findElement(
				By.cssSelector(interests + ":nth-child(" + n + ")"
						+ interestCheckbox)).click();
		return this;
	}

	public String getNthInterest(int n) {
		String s = driver.findElement(
				By.cssSelector(interests + ":nth-child(" + n + ")"
						+ interestLabel)).getText();
		return s;
	}

	public int getCountOfInterests() {
		int count = driver.findElements(
				By.cssSelector(interests + interestLabel)).size();
		return count;
	}

	public int getNumberOfInterests() {
		int n = driver.findElements(By.cssSelector(interests)).size();
		return n;
	}

	public Boolean isNthInterestCheckboxChecked(int n) {
		boolean isExpertiseChecked = driver.findElement(
				By.cssSelector(interests + ":nth-child(" + n + ")"
						+ interestCheckbox)).getAttribute("checked") == null ? false
				: true;
		return isExpertiseChecked;
	}

	public FirstTimeUserRegistration checkHealthcareProfessionalVerification() {
		driver.findElement(healthcareProfessionalVerification).click();
		return this;
	}

	public FirstTimeUserRegistration checkAcceptTermsAndConditions() {
		driver.findElement(acceptTermsAndConditions).click();
		return this;
	}

	public FirstTimeUserRegistration checkDisclaimerForCommunicationAndPosts() {
		driver.findElement(disclaimerForCommunicationAndPosts).click();
		return this;
	}

	public int getSelectedOrganizationIndex() {

		int selectedOption = -1;

		WebElement select = driver.findElement(organization);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		int i = 1;
		for (WebElement element : allElements) {
			if (element.isSelected()) {
				selectedOption = i - 1;
			}
			i++;
		}

		return selectedOption;
	}

	public String getSelectedOrganizationValue() {

		String selectedOption = "";

		WebElement select = driver.findElement(organization);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getAttribute("value");
		}

		return selectedOption;
	}

	public String getSelectedOrganizationVisibleText() {

		String selectedOption = "";

		WebElement select = driver.findElement(organization);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getText();
		}

		return selectedOption;
	}

	public FirstTimeUserRegistration selectOrganizationByVisibleText(String org) {

		new Select(driver.findElement(organization)).selectByVisibleText(org);

		return this;
	}

	public FirstTimeUserRegistration selectOrganizationByIndex(int index) {

		new Select(driver.findElement(organization)).selectByIndex(index);

		return this;
	}

	public FirstTimeUserRegistration selectOrganizationByValue(String value) {

		new Select(driver.findElement(organization)).selectByValue(value);

		return this;
	}

	public String getSelectedCountryVisibleText() {

		String selectedOption = "";

		WebElement select = driver.findElement(country);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getText();
		}

		return selectedOption;
	}

	public String getSelectedCountryValue() {

		String selectedOption = "";

		WebElement select = driver.findElement(country);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getAttribute("value");
		}

		return selectedOption;
	}

	public int getSelectedCountryIndex() {

		int selectedOption = -1;

		WebElement select = driver.findElement(country);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));
		int i = 1;
		for (WebElement element : allElements) {
			if (element.isSelected()) {
				selectedOption = i;
			}
		}

		return selectedOption;
	}

	public FirstTimeUserRegistration selectCountryByVisibleText(String cntry) {

		new Select(driver.findElement(country)).selectByVisibleText(cntry);

		return this;
	}

	public FirstTimeUserRegistration selectCountryByIndex(int index) {

		new Select(driver.findElement(country)).selectByIndex(index);
		commonMethods.waitForElementToBeRefreshed(city, driver);

		return this;
	}

	public FirstTimeUserRegistration selectCountryByValue(String value) {

		new Select(driver.findElement(country)).selectByValue(value);

		return this;
	}

	public String getSelectedCityVisibleText() {

		String selectedOption = "";

		WebElement select = driver.findElement(city);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getText();
		}

		return selectedOption;
	}

	public String getSelectedCityValue() {

		String selectedOption = "";

		WebElement select = driver.findElement(city);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));

		for (WebElement element : allElements) {
			if (element.isSelected())
				selectedOption = element.getAttribute("value");
		}

		return selectedOption;
	}

	public int getSelectedCityIndex() {

		int selectedOption = -1;

		WebElement select = driver.findElement(city);
		List<WebElement> allElements = select
				.findElements(By.tagName("option"));
		int i = 1;
		for (WebElement element : allElements) {
			if (element.isSelected()) {
				selectedOption = i;
			}
		}

		return selectedOption;
	}

	public FirstTimeUserRegistration selectCityByVisibleText(String cty) {

		new Select(driver.findElement(city)).selectByVisibleText(cty);

		return this;
	}

	public FirstTimeUserRegistration selectCityByIndex(int index) {

		new Select(driver.findElement(city)).selectByIndex(index);

		return this;
	}

	public FirstTimeUserRegistration selectCityByValue(String value) {

		new Select(driver.findElement(city)).selectByValue(value);

		return this;
	}

	public FirstTimeUserRegistration enterOtherOrganization(String otherOrg) {

		driver.findElement(otherOrganization).sendKeys(otherOrg);

		return this;
	}

	public String getOtherOrganization() {

		String otherOrganizatn = driver.findElement(otherOrganization)
				.getAttribute("value");

		return otherOrganizatn;
	}

	public FirstTimeUserRegistration enterOtherCity(String othrCty) {

		driver.findElement(otherCity).sendKeys(othrCty);

		return this;
	}
}
