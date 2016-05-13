package admin.communications.broadcastEmails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class BroadcastEmails {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By allRegisteredMembers = By.id("allTarget");
	private By byOrganisation = By.id("siteTarget");
	private By byRole = By.id("roleTarget");
	private By subject = By.id("subject");
	private By body = By.id("message");
	private By sendEmail = By.cssSelector("input[type='submit']");
	
	
	public BroadcastEmails(WebDriver driver)
	{
		this.driver=driver;
	}	
		
	public BroadcastEmails enterSubject(String subjectText)
	{
		driver.findElement(subject).sendKeys(subjectText);
		return this;
	}
	
	public BroadcastEmails enterBody(String bodyText)
	{
		driver.findElement(body).sendKeys(bodyText);
		return this;
	}
	
	public void clickSendEmail()
	{
		driver.findElement(sendEmail).click();
	}

	public Boolean isAllRegisteredMembersRadiobuttonDisplayed()
	{
		boolean found = commonMethods.isElementPresent(allRegisteredMembers, driver);
		return found;
	}

	public Boolean isByOrganizationRadiobuttonDisplayed()
	{
		boolean found = commonMethods.isElementPresent(byOrganisation, driver);
		return found;
	}

	public Boolean isByRoleRadiobuttonDisplayed()
	{
		boolean found = commonMethods.isElementPresent(byRole, driver);
		return found;
	}
	
	public Boolean isSubjectDisplayed()
	{
		boolean found = commonMethods.isElementPresent(subject, driver);
		return found;
	}
	
	public Boolean isBodyDisplayed()
	{
		boolean found = commonMethods.isElementPresent(body, driver);
		return found;
	}
	
	public Boolean isSendEmailDisplayed()
	{
		boolean found = commonMethods.isElementPresent(sendEmail, driver);
		return found;
	}
	
	
}
