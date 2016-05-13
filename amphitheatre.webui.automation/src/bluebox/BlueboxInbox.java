package bluebox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.login.invite.FirstTimeUserRegistration;

public class BlueboxInbox 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By checkYourInboxField = By.id("Email");
	private By submitButton = By.cssSelector("input[value='Submit']");
	private By refreshButton = By.cssSelector("div[class='lotusContent'] > div > div > div > ul > li:nth-child(3) > a");
	private By emailPreview = By.id("text-tab");
		/* Invitation Email */
		private By emailSalutation = By.xpath("//div[@id='HtmlBody']/table/tbody/tr[2]/td/table[2]/tbody/tr/td/p");
		private By registerLink = By.xpath("/html/body/div/div[4]/div[2]/div[5]/table/tbody/tr[2]/td/table[3]/tbody/tr/td/table/tbody/tr/td[2]/p/a");
		
	/* String Element Constructors */
	private String inboxTable = "div[id='grid'] > div:nth-child(2) > div:nth-child(2) > div > div > div > div";
	private String inboxTableFrom = " > table > tbody > tr > td:nth-child(1)";
	private String inboxTableSubject = " > table > tbody > tr > td:nth-child(2)";
	private String inboxTableDate = " > table > tbody > tr > td:nth-child(3)";
	private String inboxTableSize = " > table > tbody > tr > td:nth-child(4)";	
	
	public BlueboxInbox (WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public BlueboxInbox enterEmailAddress(String emailAddress)
	{
		driver.findElement(checkYourInboxField).clear();
		driver.findElement(checkYourInboxField).sendKeys(emailAddress);
		return this;
	}
	
	public BlueboxInbox clickSubmit()
	{
		driver.findElement(submitButton).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(inboxTable), driver);
		return this;
	}
	
	public BlueboxInbox clickRefresh()
	{
		driver.findElement(refreshButton).click();
		return this;
	}
	
	public int getCountOfEmails()
	{
		int count;
		count = driver.findElements(By.cssSelector(inboxTable)).size();
		return count;
	}
	
	public BlueboxInbox clickRefreshUntilNEmails(int n)
	{
		for (int i = 1; i<30; i++ )
			if (getCountOfEmails()!=0)
				i=30;
			else
				clickRefresh();
		return this;
	}
	
	public BlueboxInbox clickNthEmailFromField(int n)
	{
		driver.findElement(By.cssSelector(inboxTable+":nth-child("+(n)+")"+inboxTableFrom)).click();
		return this;
	}
	
	public BlueboxInbox clickNthEmailSubjectField(int n)
	{
		driver.findElement(By.cssSelector(inboxTable+":nth-child("+(n)+")"+inboxTableSubject)).click();
		commonMethods.waitForElementToBeRefreshed(emailPreview, driver);
		return this;
	}
	
	public String getNthEmailFrom(int n)
	{
		return driver.findElement(By.cssSelector(inboxTable+":nth-child("+(n)+")"+inboxTableFrom)).getText();
	}
	
	public String getNthEmailSubject(int n)
	{
		return driver.findElement(By.cssSelector(inboxTable+":nth-child("+(n)+")"+inboxTableSubject)).getText();
	}
	
	public String getNthEmailDate(int n)
	{
		return driver.findElement(By.cssSelector(inboxTable+":nth-child("+(n)+")"+inboxTableDate)).getText();
	}
	
	public String getNthEmailSize(int n)
	{
		return driver.findElement(By.cssSelector(inboxTable+":nth-child("+(n)+")"+inboxTableSize)).getText();
	}	
	
	public String getEmailBody()
	{
		return driver.findElement(emailPreview).getAttribute("value");
	}
	
	public String getPassword()
	{
		String email = getEmailBody();		
		String splits[] = email.split("\\n");
		return splits[6].trim();
	}

	public String getSalutationInvitationEmail()
	{
		return driver.findElement(emailSalutation).getText();
	}
	
	public FirstTimeUserRegistration clickRegisterLinkInInvitationEmail()
	{
		driver.findElement(registerLink).click();
		return new FirstTimeUserRegistration(driver);
	}	
	
	public Boolean isSalutationInInvitationEmailDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(emailSalutation, driver);
	}

	public Boolean isRegisterLinkInInvitationEmailDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(registerLink, driver);
	}
	
	
}
