package admin.members.people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class CreateMember {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By errorMessage = By.cssSelector("div[class='error_message_box']");
	private By errorMessageText = By.cssSelector("div[class='error_message_item']");
	private By resultMessage = By.cssSelector("div[class='result_message_box']");
	private By resultMessageText = By.cssSelector("div[class='result_message_item']");
	private By emailAddress = By.id("email");
	private By emailAddressReadOnly = By.cssSelector("div[id='fieldRowemail'] > div > div:nth-child(2)");
	private By firstName = By.id("firstName");
	private By lastName  = By.id("lastName");
	private By displayName = By.id("ampDisplayName");
	private By academicPositionTitle = By.id("title");
	private By yourOccupation = By.id("occupation");
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
	private By organization = By.id("orgId");
	private By country = By.id("country");
	private By city = By.id("city");
	private By otherOrganization = By.id("otherorgId");
	private By otherCity = By.id("othercity");
	private By contentMasterAuthorCheckbox = By.cssSelector("input[value='ContentMaster']");
	private By contentMasterReviewerCheckbox = By.cssSelector("input[value='ContentMasterReviewer']");
	private By masterAdminCheckbox = By.cssSelector("input[value='MasterAdmin']");
	private By siteAdminCheckbox = By.cssSelector("input[value='SiteAdmin']");
	private By expertise = By.cssSelector("input[id^='expertisesubject#']");	
	private By audiences = By.name("audience");
	private By password = By.id("password");
	private By activeStatus = By.id("statusA");
	private By inactiveStatus = By.id("statusI");	
	private By sendMemberPasswordEmail = By.id("sendEmail");
	private By confirmPassword = By.id("confirm");	
	private By submit = By.cssSelector("input[value='Submit']");
	private By save = By.cssSelector("input[value='Save']");
	private By cancel = By.cssSelector("input[value='Cancel']");

	/* String Element Constructors */
	private String expertiseRow = "div[class='field_row_expertise'] > div[class='input_data'] > span > div";
	private String audiencesRow = "div[class='field_row_audience'] > div[class='input_data'] > span > div";
	private String checkbox = " > input";
	private String label = " > label";
	
	public CreateMember(WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public Boolean isErrorMessageDisplayed()
	{
		
		
		boolean errorMessageDisplayed = commonMethods.isElementTextDisplayedAndNotEmpty(errorMessage, driver);
		
		return errorMessageDisplayed;
	}
	
	public List<String> getErrorMessages()
	{
		
		
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(errorMessageText); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
        
        return l;
	}
	
	public Boolean isResultMessageDisplayed()
	{
		
		
		boolean resultMessageDisplayed = commonMethods.isElementTextDisplayedAndNotEmpty(resultMessage, driver);
		
		return resultMessageDisplayed;
	}
	
	public List<String> getResultMessages()
	{
		
		
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(resultMessageText); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
        
		return l;
	}
	
	public CreateMember enterEmail(String email)
	{
		
		
		driver.findElement(emailAddress).sendKeys(email);
		
		return this;
	}
	
	public String getEmail()
	{
		
		
		String emailText = driver.findElement(emailAddress).getAttribute("value");
		
		return emailText;
	}
	
	public String getEmailAddressReadOnly()
	{
		
		
		String email = driver.findElement(emailAddressReadOnly).getText();
		
		return email;
	}
	
	public Boolean isEmailAddressReadOnlyDisplayed()
	{
		
		
		boolean emailAddressDisplayed = commonMethods.isElementTextDisplayedAndNotEmpty(emailAddressReadOnly, driver);
		
		return emailAddressDisplayed;
	}
	
	public CreateMember enterFirstName(String firstname)
	{
		
		
		driver.findElement(firstName).sendKeys(firstname);
		
		return this;
	}
	
	public String getFirstName()
	{
		
		
		String firstname = driver.findElement(firstName).getAttribute("value");
		
		return firstname;
	}
	
	public CreateMember enterLastName(String lastname)
	{
		
		
		driver.findElement(lastName).sendKeys(lastname);
		
		return this;
	}
	
	public String getLastName()
	{
		
		
		String lastname = driver.findElement(lastName).getAttribute("value");
		
		return lastname;
	}
	
	public CreateMember enterDisplayName(String displayname)
	{
		
		
		driver.findElement(displayName).sendKeys(displayname);
		
		return this;
	}
	
	public String getDisplayName()
	{
		
		
		String displayname = driver.findElement(displayName).getAttribute("value");
		
		return displayname;
	}
	
	public CreateMember enterAcademicPositionTitle(String memberTitle)
	{
		
		
		driver.findElement(academicPositionTitle).sendKeys(memberTitle);
		
		return this;
	}
	
	public String getAcademicPositionTitle()
	{
		
		
		String titleText = driver.findElement(academicPositionTitle).getAttribute("value");
		
		return titleText;
	}
	
	public CreateMember selectYourOccupationByVisibleText(String occ)
	{
		
		
		new Select(driver.findElement(yourOccupation)).selectByVisibleText(occ);
		
		return this;
	}
	
	public CreateMember selectYourOccupationByValue(String val)
	{
		
		
		new Select(driver.findElement(yourOccupation)).selectByValue(val);
		
		return this;
	}
	
	public CreateMember selectYourOccupationByIndex(int index)
	{
		
		
		new Select(driver.findElement(yourOccupation)).selectByIndex(index);
		
		return this;
	}
	
	public CreateMember selectPostGraduateExperienceByVisibleText(String occ)
	{
		
		
		new Select(driver.findElement(postGraduateExperience)).selectByVisibleText(occ);
		
		return this;
	}
	
	/**
	 * 
	 * @param val Accepted values are less_than_1, 1_to_5, 6_to_10, 10_to_15, 15_to_20, more_than_20
	 * @return
	 */
	public CreateMember selectPostGraduateExperienceByValue(String val)
	{
		
		
		new Select(driver.findElement(postGraduateExperience)).selectByValue(val);
		
		return this;
	}
	
	public CreateMember selectPostGraduateExperienceByIndex(int index)
	{
		
		
		new Select(driver.findElement(postGraduateExperience)).selectByIndex(index);
		
		return this;
	}
	
	public String getSelectedPostGraduateExperienceVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(postGraduateExperience);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public int getSelectedPostGraduateExperienceIndex()
	{
		
		
		int selectedOption=-1;
		
		WebElement select = driver.findElement(postGraduateExperience);
        List<WebElement> allElements = select.findElements(By.tagName("option"));
        int i = 1;
        for (WebElement element: allElements) {
        	if(element.isSelected())
        	{
        		selectedOption=i;
        	}
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedPostGraduateExperienceValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(postGraduateExperience);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public CreateMember selectNthSpecialty(int n)
	{
		
		
		driver.findElement(By.cssSelector(specialty+":nth-child("+n+")"+specialtyCheckbox)).click();
		
		return this;
	}
	
	public String getNthSpecialty(int n)
	{
		
		
		String s = driver.findElement(By.cssSelector(specialty+":nth-child("+n+")"+specialtyLabel)).getText();
		
		return s;
	}
	
	public int getCountOfSpecialties()
	{
		
		
		int count = driver.findElements(By.cssSelector(specialty+specialtyLabel)).size();
		
		return count;
	}
	
	public CreateMember selectNthSubSpecialty(int n)
	{
		
		
		driver.findElement(By.cssSelector(subSpecialty+":nth-child("+n+")"+subSpecialtyCheckbox)).click();
		
		return this;
	}
	
	public String getNthSubSpecialty(int n)
	{
		
		
		String s = driver.findElement(By.cssSelector(subSpecialty+":nth-child("+n+")"+subSpecialtyLabel)).getText();
		
		return s;
	}
	
	public int getCountOfSubSpecialties()
	{
		
		
		int count = driver.findElements(By.cssSelector(subSpecialty+subSpecialtyLabel)).size();
		
		return count;
	}
	
	public CreateMember selectNthInterest(int n)
	{
		
		
		driver.findElement(By.cssSelector(interests+":nth-child("+n+")"+interestCheckbox)).click();
		
		return this;
	}
	
	public String getNthInterest(int n)
	{
		
		
		String s = driver.findElement(By.cssSelector(interests+":nth-child("+n+")"+interestLabel)).getText();
		
		return s;
	}
	
	public int getCountOfInterests()
	{
		
		
		int count = driver.findElements(By.cssSelector(interests+interestLabel)).size();
		
		return count;
	}
	
	public String getSelectedYourOccupationVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(yourOccupation);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public int getSelectedYourOccupationIndex()
	{
		
		
		int selectedOption=-1;
		
		WebElement select = driver.findElement(yourOccupation);
        List<WebElement> allElements = select.findElements(By.tagName("option"));
        int i = 1;
        for (WebElement element: allElements) {
        	if(element.isSelected())
        	{
        		selectedOption=i;
        	}
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedYourOccupationValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(yourOccupation);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedOrganizationVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(organization);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public int getSelectedOrganizationIndex()
	{
		
		
		int selectedOption=-1;
		
		WebElement select = driver.findElement(organization);
        List<WebElement> allElements = select.findElements(By.tagName("option"));
        
        int i =1;
        for (WebElement element: allElements) {
        	if(element.isSelected())
        		{selectedOption=i-1;}
        	i++;
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedOrganizationValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(organization);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCountryVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(country);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCountryValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(country);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public int getSelectedCountryIndex()
	{
		
		
		int selectedOption=-1;
		
		WebElement select = driver.findElement(country);
        List<WebElement> allElements = select.findElements(By.tagName("option"));
        int i = 1;
        for (WebElement element: allElements) {
        	if(element.isSelected())
        	{
        		selectedOption=i;
        	}
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCityVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(city);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCityValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(city);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public int getSelectedCityIndex()
	{
		
		
		int selectedOption=-1;
		
		WebElement select = driver.findElement(city);
        List<WebElement> allElements = select.findElements(By.tagName("option"));
        int i = 1;
        for (WebElement element: allElements) {
        	if(element.isSelected())
        	{
        		selectedOption=i;
        	}
        }	
		
		
		return selectedOption;
	}
	
	public CreateMember selectOrganizationByVisibleText(String org)
	{
		
		
		new Select(driver.findElement(organization)).selectByVisibleText(org);
		
		return this;
	}
	
	public CreateMember selectOrganizationByIndex(int index)
	{
		
		
		new Select(driver.findElement(organization)).selectByIndex(index);
		
		return this;
	}
	
	public CreateMember selectOrganizationByValue(String value)
	{
		
		
		new Select(driver.findElement(organization)).selectByValue(value);
		
		return this;
	}
	
	public CreateMember selectCountryByVisibleText(String cntry)
	{
		
		
		new Select(driver.findElement(country)).selectByVisibleText(cntry);
		
		return this;
	}
	
	public CreateMember selectCountryByIndex(int index)
	{
		
		
		new Select(driver.findElement(country)).selectByIndex(index);
		commonMethods.waitForElementToBeRefreshed(city, driver);
		
		return this;
	}
	
	public CreateMember selectCountryByValue(String value)
	{
		
		
		new Select(driver.findElement(country)).selectByValue(value);
		
		return this;
	}
	
	public CreateMember selectCityByVisibleText(String cty)
	{
		
		
		new Select(driver.findElement(city)).selectByVisibleText(cty);
		
		return this;
	}
	
	public CreateMember selectCityByIndex(int index)
	{
		
		
		new Select(driver.findElement(city)).selectByIndex(index);
		
		return this;
	}
	
	public CreateMember selectCityByValue(String value)
	{
		
		
		new Select(driver.findElement(city)).selectByValue(value);
		
		return this;
	}
	
	public CreateMember enterOtherOrganization(String otherOrg)
	{
		
		
		driver.findElement(otherOrganization).sendKeys(otherOrg);
		
		return this;
	}
	
	public String getOtherOrganization()
	{
		
		
		String otherOrganizatn = driver.findElement(otherOrganization).getAttribute("value");
		
		return otherOrganizatn;
	}
	
	public CreateMember enterOtherCity(String othrCty)
	{
		
		
		driver.findElement(otherCity).sendKeys(othrCty);
		
		return this;
	}
	
	public CreateMember selectActiveRadiobutton()
	{
		
		
		driver.findElement(activeStatus).click();
		
		return this;
	}
	
	public Boolean isActiveRadiobuttonChecked()
	{
		
		
		boolean activeRadioButtonChecked = driver.findElement(activeStatus).getAttribute("checked") == null  ? false : true;
		
		return activeRadioButtonChecked;
	}
	
	public CreateMember selectInactiveRadiobutton()
	{
		
		
		driver.findElement(inactiveStatus).click();
		
		return this;
	}
	
	public Boolean isInactiveRadiobuttonChecked()
	{
		
		
		boolean inactiveRadioButtonChecked = driver.findElement(inactiveStatus).getAttribute("checked") == null  ? false : true;
		
		return inactiveRadioButtonChecked;
	}
	
	public CreateMember enterPassword(String newPassword)
	{
		
		
		driver.findElement(password).sendKeys(newPassword);
		
		return this;
	}
	
	public CreateMember enterConfirmPassword(String password)
	{
		
		
		driver.findElement(confirmPassword).sendKeys(password);
		
		return this;
	}
	
	public CreateMember checkSendMemberPasswordEmail()
	{
		
		
		driver.findElement(sendMemberPasswordEmail).click();
		
		return this;
	}
	
	public void clickSubmit()
	{
		
		
		driver.findElement(submit).click();
		
	}
	
	public void clickSave()
	{
		
		
		driver.findElement(save).click();
		
	}
	
	public People clickCancel()
	{
		
		
		driver.findElement(cancel).click();
		
		return new People (driver);
	}
	
	public CreateMember selectContentMasterAuthorCheckbox()
	{
		
		
		driver.findElement(contentMasterAuthorCheckbox).click();
		
		return new CreateMember (driver);
	}
	
	public Boolean isContentMasterAuthorCheckboxChecked()
	{
		
		
		boolean contentMasterAuthorDisplayed = driver.findElement(contentMasterAuthorCheckbox).getAttribute("checked") == null  ? false : true;
		
		return contentMasterAuthorDisplayed;
	}
	
	public CreateMember selectContentMasterReviewerCheckbox()
	{
		
		
		driver.findElement(contentMasterReviewerCheckbox).click();
		
		return new CreateMember (driver);
	}
	
	public Boolean isContentMasterReviewerCheckboxChecked()
	{
		
		
		boolean contentMasterReviewerDisplayed = driver.findElement(contentMasterReviewerCheckbox).getAttribute("checked") == null  ? false : true;
		
		return contentMasterReviewerDisplayed;
	}

	public CreateMember selectMasterAdminCheckbox()
	{
		
		
		driver.findElement(masterAdminCheckbox).click();
		
		return new CreateMember (driver);
	}
	
	public Boolean isMasterAdminCheckboxChecked()
	{
		
		
		boolean masterAdminCheckboxChecked = driver.findElement(masterAdminCheckbox).getAttribute("checked") == null  ? false : true;
		
		return masterAdminCheckboxChecked;
	}

	public CreateMember selectSiteAdminCheckbox()
	{
		
		
		driver.findElement(siteAdminCheckbox).click();
		
		return new CreateMember (driver);
	}
	
	public Boolean isSiteAdminCheckboxChecked()
	{
		
		
		boolean siteAdminCheckboxChecked = driver.findElement(siteAdminCheckbox).getAttribute("checked") == null  ? false : true;
		
		return siteAdminCheckboxChecked;		
	}
	
	public int getNumberOfExpertise()
	{
		
		
		int n = driver.findElements(expertise).size();
		
		return n;
	}
	
	public String getLabelOfExpertise(int n)
	{
		
		
		String str = driver.findElement(By.cssSelector(expertiseRow + ":nth-child("+n+")"+label)).getText();
		
		return str;
	}
	
	public int getNumberOfInterests()
	{
		
		
		int n = driver.findElements(By.cssSelector(interests)).size();
		
		return n;
	}
	
	public int getNumberOfAudiences()
	{
		
		
		int n = driver.findElements(audiences).size();
		
		return n;
	}
	
	public CreateMember selectNthExpertise(int n)
	{
		
		
		driver.findElement(By.cssSelector(expertiseRow + ":nth-child("+n+")" + checkbox)).click();	
		
		return new CreateMember (driver);
	}
	
	public CreateMember selectAllExpertises()
	{
		
		
		int numberOfExpertises = getNumberOfExpertise();
		for (int i=1; i<=numberOfExpertises; i++)
			selectNthExpertise(i);	
		
		return new CreateMember (driver);
	}
	
	public Boolean isNthExpertiseCheckboxChecked(int n)
	{
		
		
		boolean isExpertiseChecked = driver.findElement(By.cssSelector(expertiseRow + ":nth-child("+n+")" + checkbox)).getAttribute("checked") == null  ? false : true;
		
		return isExpertiseChecked;
	}
	
	public CreateMember selectNthAudience(int n)
	{
		
		
		driver.findElement(By.cssSelector(audiencesRow + ":nth-child("+n+")" + checkbox)).click();	
		
		return new CreateMember (driver);
	}
	
	public CreateMember selectAllAudiences()
	{
		
		
		int numberOfAudiences = getNumberOfAudiences();
		for (int i=1; i<=numberOfAudiences; i++)
			selectNthAudience(i);		
		
		return new CreateMember (driver);
	}
	
	public Boolean isNthAudienceCheckboxChecked(int n)
	{
		
		
		boolean isAudienceChecked = driver.findElement(By.cssSelector(audiencesRow + ":nth-child("+n+") > input")).getAttribute("checked") == null  ? false : true;
		
		return isAudienceChecked;
	}
	
	public CreateMember selectAllSpecialties()
	{
		
		
		int n = getCountOfSpecialties();
		for (int i=1; i<=n; i++)
			selectNthSpecialty(i);	
		
		return new CreateMember (driver);
	}
	
	public CreateMember selectAllSubSpecialties()
	{
		
		
		int n = getCountOfSubSpecialties();
		for (int i=1; i<=n; i++)
			selectNthSubSpecialty(i);	
		
		return new CreateMember (driver);
	}
	
	public CreateMember selectAllInterests()
	{
		
		
		int n = getCountOfInterests();
		for (int i=1; i<=n; i++)
			selectNthInterest(i);	
		
		return new CreateMember (driver);
	}
	
	public Boolean isNthSpecialtyCheckboxChecked(int n)
	{
		
		
		boolean isExpertiseChecked = driver.findElement(By.cssSelector(specialty + ":nth-child("+n+")" + specialtyCheckbox)).getAttribute("checked") == null  ? false : true;
		
		return isExpertiseChecked;
	}
		
	public Boolean isNthSubSpecialtyCheckboxChecked(int n)
	{
		
		
		boolean isExpertiseChecked = driver.findElement(By.cssSelector(subSpecialty + ":nth-child("+n+")" + subSpecialtyCheckbox)).getAttribute("checked") == null  ? false : true;
		
		return isExpertiseChecked;
	}
	
	public Boolean isNthInterestCheckboxChecked(int n)
	{
		
		
		boolean isExpertiseChecked = driver.findElement(By.cssSelector(interests + ":nth-child("+n+")" + interestCheckbox)).getAttribute("checked") == null  ? false : true;
		
		return isExpertiseChecked;
	}
	
	public String getNthAudience(int n)
	{
		
		
		String s = driver.findElement(By.cssSelector(audiencesRow+":nth-child("+n+")"+label)).getText();
		
		return s;
	}
	
	public String getNthExpertise(int n)
	{
		
		
		String s = driver.findElement(By.cssSelector(expertiseRow+":nth-child("+n+")"+label)).getText();
		
		return s;
	}
	
	/**
	 * Fills in only the mandatory fields on the create member page
	 * @param email - The member's email address
	 * @param firstname - The member's first name
	 * @param lastname - The member's last name
	 * @param displayname - The member's display name
	 * @param occupation - The member's occupation
	 * @param postGraduateExperience
	 * @param specialty
	 * @param subSpecialty
	 * @param organization - The organization name
	 * @param country - The member's country
	 * @param city - The member's city
	 * @param password - The member's password
	 * @return Returns true if the member was created successfully, otherwise false
	 */
	public Boolean enterMandatoryFieldsAndClickSave(String email, String firstname, String lastname, String displayname, String occupation, int postGraduateExperience,
			int specialty, int subSpecialty, int interest, String country, String city, String password)
	{
		enterEmail(email);		
		enterPassword(password);
		enterConfirmPassword(password);
		enterFirstName(firstname);
		enterLastName(lastname);
		enterDisplayName(displayname);
		selectYourOccupationByVisibleText(occupation);
		selectPostGraduateExperienceByIndex(postGraduateExperience);
		selectNthSpecialty(specialty);
		selectNthSubSpecialty(subSpecialty);
		selectNthInterest(interest);
		selectCountryByVisibleText(country);
		selectCityByVisibleText(city);
		clickSave();
		
		return (isResultMessageDisplayed() /*&& !isErrorMessageDisplayed()*/);
	}
	
	/**
	 * Fills in only the mandatory fields on the create member page and uses system generated password
	 * @param email - The member's email address
	 * @param firstname - The member's first name
	 * @param lastname - The member's last name
	 * @param displayname - The member's display name
	 * @param occupation - The member's occupation
	 * @param organization - The organization name
	 * @param country - The member's country
	 * @param city - The member's city
	 * @return Returns true if the member was created successfully, otherwise false
	 */
	public Boolean enterMandatoryFieldsAndClickSave(String email, String firstname, String lastname, String displayname, int occupation, int postGraduateExperience,
			int specialty, int subSpecialty, int interest, int country, int city)
	{
		enterEmail(email);
		enterFirstName(firstname);
		enterLastName(lastname);
		enterDisplayName(displayname);
		selectYourOccupationByIndex(occupation);
		selectPostGraduateExperienceByIndex(postGraduateExperience);
		selectNthSpecialty(specialty);	
		selectNthSubSpecialty(subSpecialty);
		selectNthInterest(interest);
		selectCountryByIndex(country);
		selectCityByIndex(city);
		clickSave();
		
		return (isResultMessageDisplayed() && !isErrorMessageDisplayed());
	}
}
