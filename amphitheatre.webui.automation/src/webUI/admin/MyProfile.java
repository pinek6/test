package webUI.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.home.groups.GroupsView;


public class MyProfile extends Profile{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private String URL = new ConfigProperties().getConfigProperties().getProperty("URL");

	public MyProfile(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	/*  Element Locators */
	private By profilePhotoLocator = By.cssSelector(".profileHeroImg");
	
	/* Edit Icons */
	private By editProfileLocator = By.id("btnEditProfile");
	private By doneEditingLocator = editProfileLocator;
	private By editProfilePhotoLocator = By.id("btnEditProfilePhoto");
	private By editNameLocator = By.id("btnEditName");
	private By editTitleLocator = By.id("btnEditTitle");
	private By editResearchClinicalInterestsButtonLocator = By.id("editUserBackgroundButton");
	private By editContactInfoButtonLocator = By.id("editContactInfoButton");
	private By editProfessionalDetailsButtonLocator = By.id("editProfessionalDetailsButton");
	private By editAdditionalInfoLocator = By.id("additionalInfoButton");
	private By editInterestsButtonLocator = By.id("editInterestsButton");
	private By infoExpertiseAreaLocator = By.id("editExpertiseButton");
	private By infoGroupsLocator = By.id("learnAboutGroupsButton");
	
	/* Profile Information */
	//private By nameLocator = By.cssSelector(".colSubSection>h2");
	private By nameLocator = By.cssSelector("#userName>span");
	//private By titleLocator = By.cssSelector(".colSubSection>h2:nth-child(2)");
	private By titleLocator = By.cssSelector("#userTitle>span");
	private By additionalTitleLocator = By.cssSelector("#additionalTitles");
	private By addressLocator = By.id("ProfileAddress1");
	//private By backgroundLocator = By.id("BGDetails");
	private By backgroundLocator = By.cssSelector(".colSection>p>p");
	private By additionalInfoLocator = By.cssSelector("#additionalContactInfo>p");
	private By expertiseLocator = By.cssSelector("div[id^='sls_widgets_components_profile_ExpertiseDetails'] > ul > li");
	private By numberGroupsLocator = By.cssSelector(".title5.profileSectionHead>span");
	private By sendEmailLinkLocator = By.cssSelector(".sendMessageIcon.showIcons");
	private By additionalContactInfoLocator = By.id("additionalInfoButton");
	private By chatLinkLocator = By.cssSelector("span[id*='sametime_LiveName_']");
	private By orgLocator = By.cssSelector("#orgName");
	private By occupationLocator = By.cssSelector("#professionalDetailsOccupationList>a");
	
	/* String Element Locator Constructors */
	private String interestsList = "ul[id='interestsListHolder'] > li > a";
	//private String expertiseList = "[id*=sls_widgets_components_profile_ExpertiseDetails_ > ul]";
	private String expertiseList = "div[id^='sls_widgets_components_profile_ExpertiseDetails'] > ul > li";
	private String groupsList = "ul[id*='sls_widgets_components_profile_ProfileGroupList_']";
	private String specialtyList = "ul[id='professionalDetailsSpecialityList'] > a";
	private String subSpecialtyList = "ul[id='professionalDetailsSubSpecialityList'] > a";
	private String emptyGroupsMessageLocator = "[id*=sls_widgets_components_profile_ProfileDetails_] > div:nth-of-type(3) > div:nth-of-type(3) > ul > li";
	private String emptyInterestsMessageLocator = "[id*=sls_widgets_components_profile_ProfileDetails_] > div:nth-of-type(3) > div:nth-of-type(2) > div > ul > li";
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/*  Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(editProfileLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(profilePhotoLocator));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void waitForResearchClinicalInterestsToLoad(String text, WebDriver driver)
	{
		commonMethods.waitForTextToBePresentInElement(backgroundLocator, text, driver);
	}
	
	public void waitForAdditionalInfoToLoad(String text, WebDriver driver)
	{
		commonMethods.waitForTextToBePresentInElement(additionalInfoLocator, text, driver);
	}
	
	public void waitForSendEmailIconToBeVisible(WebDriver driver)
	{
		commonMethods.waitForElementToBeVisibleAndClickable(sendEmailLinkLocator, driver);
	}
	
	public void waitForSendEmailIconToBeNotVisible(WebDriver driver)
	{
		commonMethods.waitForElementToBeNotVisible(sendEmailLinkLocator, driver);
	}

	public Boolean profilePhotoLoads()
	{
		return commonMethods.isElementDisplayed(profilePhotoLocator, driver);
	}
	
	public MyProfile clickEditProfile()
	{
		driver.findElement(editProfileLocator).click();
		return this;
	}
	
	public MyProfile clickDoneEditing()
	{
		driver.findElement(doneEditingLocator).click();
		return this;
	}
	
	public EditProfilePhoto clickEditProfilePhoto()
	{
		driver.findElement(editProfilePhotoLocator).click();
		return new EditProfilePhoto(driver);
	}
	
	public EditName clickEditName()
	{
		driver.findElement(editNameLocator).click();
		return new EditName(driver);
	}
	
	public EditPosition clickEditTitle()
	{
		driver.findElement(editTitleLocator).click();
		return new EditPosition(driver);
	}
	
	public EditResearchClinicalInterests clickEditResearchClinicalInterests()
	{
		driver.findElement(editResearchClinicalInterestsButtonLocator).click();
		return new EditResearchClinicalInterests(driver);
	}
	
	public EditAdditionalInformation clickEditAdditionalInformation()
	{
		driver.findElement(editAdditionalInfoLocator).click();
		return new EditAdditionalInformation(driver);
	}
	
	public EditContactInformation clickEditContactInfo()
	{
		driver.findElement(editContactInfoButtonLocator).click();
		return new EditContactInformation(driver);
	}
	
	public EditProfessionalDetails clickEditProfessionalDetails()
	{
		driver.findElement(editProfessionalDetailsButtonLocator).click();
		return new EditProfessionalDetails(driver);
	}
	
	public EditInterests clickEditInterests()
	{
		driver.findElement(editInterestsButtonLocator).click();
		return new EditInterests(driver);
	}
	
	public LearnAboutExpertAreas clickInfoExpertiseArea()
	{
		driver.findElement(infoExpertiseAreaLocator).click();
		return new LearnAboutExpertAreas(driver);
	}
	
	public LearnAboutGroups clickInfoGroups()
	{
		driver.findElement(infoGroupsLocator).click();
		return new LearnAboutGroups(driver);
	}
	
	public GroupsView clickNthGroup(int nth)
	{
		driver.findElement(By.cssSelector(groupsList + "> li:nth-of-type("+nth+") > a")).click();
		return new GroupsView(driver);
	}
	
	public String getEditButtonText()
	{
		return driver.findElement(editProfileLocator).getAttribute("value");
	}
	
	public Boolean areEditIconsDisplaying()
	{
		return 	commonMethods.isElementDisplayed(editProfileLocator, driver) &&
				commonMethods.isElementDisplayed(editProfilePhotoLocator, driver) &&
				commonMethods.isElementDisplayed(editNameLocator, driver) &&
				commonMethods.isElementDisplayed(editTitleLocator, driver) &&
				commonMethods.isElementDisplayed(editResearchClinicalInterestsButtonLocator, driver) &&
				commonMethods.isElementDisplayed(editContactInfoButtonLocator, driver) &&
				commonMethods.isElementDisplayed(editProfessionalDetailsButtonLocator, driver) &&
				commonMethods.isElementDisplayed(editInterestsButtonLocator, driver);
	}
	
	public String getName()
	{
		return driver.findElement(nameLocator).getText();
	}
	
	public String getTitle()
	{
		return driver.findElement(titleLocator).getText();
	}
	
	public String getAdditionalTitle()
	{
		return driver.findElement(additionalTitleLocator).getText();
	}
	
	public String getAddress()
	{
		return driver.findElement(addressLocator).getText();
	}
	
	public String getResearchClinicalInterests()
	{
		return driver.findElement(backgroundLocator).getText();
	}
	
	public String getAdditionalInfo()
	{
		return driver.findElement(additionalInfoLocator).getText();
	}
	
	public int getNumberGroups()
	{
		String ngroups = driver.findElement(numberGroupsLocator).getText();
		String str = ngroups.trim();
		int n = Integer.parseInt(str);
		return n;
	}
	
	public String getPositionDetails()
	{
		return driver.findElement(orgLocator).getText();
	}
	
	public String getOccupation()
	{
		return driver.findElement(occupationLocator).getText();
	}
	
	public SharedOccupation clickOccupation()
	{
		driver.findElement(occupationLocator).click();
		return new SharedOccupation(driver);
	}
	
	public SharedSpecialty clickNthSpecialty(int nth)
	{
		driver.findElement(By.cssSelector(specialtyList + ":nth-of-type("+ nth +")")).click();
		return new SharedSpecialty(driver);
	}
	
	public String getNthSpecialty(int nth)
	{
		return driver.findElement(By.cssSelector(specialtyList + ":nth-of-type("+ nth +")")).getText();
	}
	
	public SharedSubSpecialty clickNthSubSpecialty(int nth)
	{
		driver.findElement(By.cssSelector(subSpecialtyList + ":nth-of-type("+ nth +")")).click();
		return new SharedSubSpecialty(driver);
	}
	
	public String getNthSubSpecialty(int nth)
	{
		return driver.findElement(By.cssSelector(subSpecialtyList + ":nth-of-type("+ nth +")")).getText();
	}
	
	public String getEmptyGroupsMessage()
	{
		return driver.findElement(By.cssSelector(emptyGroupsMessageLocator)).getText();
	}
	
	public String getEmptyInterestsMessage()
	{
		return driver.findElement(By.cssSelector(emptyInterestsMessageLocator)).getText();
	}
	
	public List<String> getExpertiseList()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(expertiseLocator); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }		 
		return l;
	}
	
	public SharedExpertise clickNthExpertise(int nth)
	{
		driver.findElement(By.cssSelector(expertiseList + ":nth-of-type("+nth+") > a")).click();
		return new SharedExpertise(driver);
	}
	
	public String getNthExpertise(int nth)
	{
		return driver.findElement(By.cssSelector(expertiseList + ":nth-of-type("+ nth +")")).getText();
	}
	
	public Boolean isEditProfileButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(editProfileLocator, driver);
	}
	
	public Boolean isEditProfilePhotoButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(editProfilePhotoLocator, driver);
	}
	
	public long getProfileImageDisplayedIdentifier()
	{	
		String src = driver.findElement(profilePhotoLocator).getAttribute("src");
		int n = src.lastIndexOf("=");
		String number = src.substring(n+1);
		long x = Long.parseLong(number);
		
		return x;	
	}
	
	public Boolean isEditNameLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editNameLocator, driver);
	}
	
	public Boolean isEditPositionLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editTitleLocator, driver);
	}
	
	public Boolean isEditResearchClinicalInterestsLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editResearchClinicalInterestsButtonLocator, driver);
	}
	
	public Boolean isEditAdditionalInfoLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editAdditionalInfoLocator, driver);
	}
	
	public Boolean isEditContactInfoLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editContactInfoButtonLocator, driver);
	}
	
	public Boolean isEditProfessionalDetailsLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editProfessionalDetailsButtonLocator, driver);
	}
	
	public Boolean isEditInterestsLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(editInterestsButtonLocator, driver);
	}
	
	public Boolean isChatLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(chatLinkLocator, driver);
	}
	
	public Boolean isSendEmailLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(sendEmailLinkLocator, driver);
	}
	
	public Boolean isAdditionalContactInfoLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(additionalContactInfoLocator, driver);
	}
	
	public String getOtherContactInfo()
	{
		return driver.findElement(additionalContactInfoLocator).getText();
	}
	
	public String getChatLinkStatus()
	{
		return driver.findElement(chatLinkLocator).getText();
	}
	
	public String getEmailLinkRef()
	{
		String href = driver.findElement(sendEmailLinkLocator).getAttribute("href");
		String email = href.substring(7);
		String final_email = email.replaceAll("%40", "@");
		return final_email;
	}
	
	
	public List<String> getListOfInterests()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(interestsList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfExpertise()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(expertiseLocator); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfGroups()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(groupsList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfSpecialty()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(specialtyList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
	
	public List<String> getListOfSubSpecialty()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(subSpecialtyList)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        Collections.sort(l);
		return l;
	}
}
