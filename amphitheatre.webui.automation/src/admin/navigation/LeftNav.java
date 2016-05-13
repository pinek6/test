package admin.navigation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import admin.communications.announcements.Announcements;
import admin.communications.broadcastEmails.BroadcastEmails;
import admin.contentAdministration.ContentAdministration;
import admin.learningAdministration.LearningAdministrationHome;
import admin.members.invitations.Invitations;
import admin.members.organizations.Organizations;
import admin.members.people.People;
import admin.reports.Reports;
import admin.socialQandA.ListOfUnansweredQuestions;
import admin.system.ConfigurationOptions;
import admin.system.CustomMemberFields;
import admin.system.CustomOrganizationFields;
import admin.system.Enumerations;

public class LeftNav {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By leftnavframe = By.cssSelector("frame[name='frame_left']");
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By communications = By.cssSelector("div[id='tree'] > div > span");
	private By announcements = By.cssSelector("div[id='tree'] > div > span:nth-child(2) > div > a ");
	private By worldSharedPractices = By.cssSelector("div[id='tree'] > div > span:nth-child(2) > div:nth-child(2) > a ");
	private By broadcastEmails = By.id("a-broadcast");
	private By members = By.cssSelector("div[id='tree'] > div > span:nth-child(3)");
	private By organizations = By.cssSelector("span[id='MEMBERSHIP'] > div:nth-child(1) > a ");
	private By people = By.cssSelector("span[id='MEMBERSHIP'] > div:nth-child(2) > a ");
	private By invitations = By.cssSelector("span[id='MEMBERSHIP'] > div:nth-child(3) > a ");
	private By socialQandA = By.cssSelector("div[id='tree'] > div > div > a");	
	//private By contentAdministration = By.xpath("//div[@id='tree']/div/div[2]/a");
	private By contentAdministration = By.linkText("Content Administration");
	private By learningAdministration = By.xpath("//div[@id='tree']/div/div[3]/a");
	private By reports = By.xpath("//div[@id='tree']/div/div[4]/a");
	private By system = By.xpath("//div[@id='tree']/div/span[5]");
	private By configurationOptions = By.xpath("//div[@id='tree']/div/span[6]/div/a");
	private By customMemberFields = By.xpath("//div[@id='tree']/div/span[6]/div[2]/a");
	private By customOrganisationFields = By.xpath("//div[@id='tree']/div/span[6]/div[3]/a");
	private By enumerations = By.xpath("//div[@id='tree']/div/span[6]/div[4]/a");
	//private By help = By.xpath("//div[@id='tree']/div/div[5]/a");
	private By help = By.linkText("Help");
	
	public LeftNav(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad();
	}

	public void waitForPageToLoad()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(leftnavframe));
		wait.until(ExpectedConditions.visibilityOfElementLocated(leftnavframe));
		wait.until(ExpectedConditions.presenceOfElementLocated(leftnavframe));	

		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	

	
	public LeftNav clickCommunications()
	{
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(communications).click();
		commonMethods.waitForElementToBeVisibleAndClickable(broadcastEmails, driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return this;
	}
	
	public LeftNav clickMembers()
	{
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(members).click();
		commonMethods.waitForElementToBeVisibleAndClickable(people, driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return this;
	}
	
	public Announcements clickAnnouncements()
	{
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(announcements).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new Announcements(driver);
	}
	
	public void clickWorldSharedPractices()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(worldSharedPractices).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
	}
	
	public BroadcastEmails clickBroadcastEmails()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(broadcastEmails).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new BroadcastEmails(driver);
	}
	
	public Organizations clickOrganizations()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(organizations).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new Organizations(driver);
	}
	
	public People clickPeople()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(people).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new People(driver);
	}
	
	public Invitations clickInvitations()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(invitations).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new Invitations(driver);
	}
	
	public ListOfUnansweredQuestions clickSocialQandA()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(socialQandA).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new ListOfUnansweredQuestions(driver);
	}
	
	public ContentAdministration clickContentAdministration()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(contentAdministration).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new ContentAdministration(driver);
	}
	
	public LearningAdministrationHome clickLearningAdministration()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(learningAdministration).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new LearningAdministrationHome(driver);
	}

	public Reports clickReports()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(reports).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new Reports(driver);
	}
	
	public void clickSystem()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(system).click();
		commonMethods.waitForElementToBeVisibleAndClickable(enumerations, driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
	}
	
	public ConfigurationOptions clickConfigurationOptions()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(configurationOptions).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new ConfigurationOptions(driver);
	}
	
	public CustomMemberFields clickCustomMemberFields()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(customMemberFields).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new CustomMemberFields(driver);
	}
	
	public CustomOrganizationFields clickCustomOrganizationFields()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(customOrganisationFields).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new CustomOrganizationFields(driver);
	}

	public Enumerations clickEnumerations()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(enumerations).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return new Enumerations(driver);
	}
	
	public void clickHelp()
	{	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, leftnavframe);
		driver.findElement(help).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
	}
}
