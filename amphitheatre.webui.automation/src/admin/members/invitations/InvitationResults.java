package admin.members.invitations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class InvitationResults 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By inviteMoreMembers = By.cssSelector("input[value='Invite More Members']");
	private By successMessage = By.cssSelector("div[id='ibm-top'] > div > div > div:nth-child(2) > div > h2");
	
	public InvitationResults (WebDriver driver)
	{
		this.driver=driver;
	}
	
	public InviteNewMembers clickInviteMoreMembers()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(inviteMoreMembers).click();
		commonMethods.switchBackToMainFrame(driver);
		return new InviteNewMembers(driver);
	}
	
	public Boolean isInviteMoreMembersDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(inviteMoreMembers, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public String getSuccessMessage()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		String found = driver.findElement(successMessage).getText();
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
}
