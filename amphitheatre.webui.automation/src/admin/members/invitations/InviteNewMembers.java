package admin.members.invitations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class InviteNewMembers 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By addMembers = By.cssSelector("input[value='Add Members']");
	private By emailAddresses = By.id("emailList");
	private By sendInvitations = By.cssSelector("input[value='Send Invitations']");
	private By removeAll = By.cssSelector("input[value='Remove All']");
	
	/* String Element Constructors */
	private String listOfInviteesTable = "table[id='batchTable'] > tbody > tr";
	private String listOfInviteesTableEdit = " > td > a:nth-child(1)";
	private String listOfInviteesTableSave = " > td > a:nth-child(2)";
	private String listOfInviteesTableDelete = " > td > a:nth-child(3)";
	private String listOfInviteesTableEmail = " > td:nth-child(3) > input";
	private String listOfInviteesTableFirstName = " > td:nth-child(4) > input";
	private String listOfInviteesTableLastName = " > td:nth-child(5) > input";
	
	public InviteNewMembers (WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickAddMembers()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(addMembers).click();
		commonMethods.switchBackToMainFrame(driver);
	}
	
	public Boolean isAddMembersButtonDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(addMembers, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public InviteNewMembers enterEmailAddress(String emailAddress)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(emailAddresses).sendKeys(emailAddress);
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public Boolean isEmailAddressesDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(emailAddresses, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public InvitationResults clickSendInvitations()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(sendInvitations).click();
		commonMethods.switchBackToMainFrame(driver);
		return new InvitationResults(driver);
	}
	
	public Boolean isSendInvitationsButtonDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(sendInvitations, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public void clickRemoveAll()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(removeAll).click();
		commonMethods.switchBackToMainFrame(driver);
	}
	
	public Boolean isRemoveAllButtonDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(removeAll, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public InviteNewMembers clickNthInviteeEdit(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableEdit)).click();
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public InviteNewMembers clickNthInviteeSave(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableSave)).click();
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public InviteNewMembers clickNthInviteeDelete(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableDelete)).click();
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public Boolean isNthInviteeEditButtonDisplayed(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableEdit), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteeSaveButtonDisplayed(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableSave), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteeDeleteButtonDisplayed(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableDelete), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteeEmailFieldDisplayed(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableEmail), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteeFirstnameFieldDisplayed(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableFirstName), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteeLastNameFieldDisplayed(int n)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableLastName), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public InviteNewMembers enterNthEmail(int n, String email)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableEmail)).sendKeys(email);
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public InviteNewMembers enterNthFirstname(int n, String fname)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableFirstName)).sendKeys(fname);
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
	
	public InviteNewMembers enterNthLastName(int n, String lname)
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(listOfInviteesTable+":nth-child("+n+")"+listOfInviteesTableLastName)).sendKeys(lname);
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}
}
