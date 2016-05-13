package admin.members.invitations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class Invitations 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	private int countOfInvitesLoadedByDefault = 25;
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By inviteMoreUsers = By.cssSelector("input[value='Invite More Users']");
	
	/* String Element Constructors */
	private String invitationsTable = "div[id='inviteGrid'] > div:nth-child(2) > div > div > div > div";
	private String invitationsTableRows = invitationsTable + " > div";
	private String invitationsTableRow = " > div";
	private String invitationsTableName = " > table > tbody > tr > td:nth-child(1)";
	private String invitationsTableEmail = " > table > tbody > tr > td:nth-child(2)";
	private String invitationsTableStatus = " > table > tbody > tr > td:nth-child(3)";
	private String invitationsTableSinceInvite = " > table > tbody > tr > td:nth-child(4)";
	private String invitationsTableOrganization = " > table > tbody > tr > td:nth-child(5)";
	private String invitationsTableResendInvite = " > table > tbody > tr > td:nth-child(6) > a";
	private String invitationsTableWithdrawInvite = " > table > tbody > tr > td:nth-child(7) > a";
	private String invitationsTableViewInvite = " > table > tbody > tr > td:nth-child(8) > a";
	
	public Invitations (WebDriver driver)
	{
		this.driver=driver;
	}
	
	public InviteNewMembers clickInviteMoreUsers()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(inviteMoreUsers).click();
		commonMethods.switchBackToMainFrame(driver);
		return new InviteNewMembers(driver);
	}
	
	public Boolean isInviteMoreUsersDisplayed()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementDisplayed(inviteMoreUsers, driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	/**
	 * By default, not all invitations are loaded.  This method keeps clicking the last loaded invite until
	 * all results appear.
	 */
	public void showFullListOfInvitations()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		
		int countBefore, countAfter;		
		do
		{
			countBefore = getCountOfInvitesCurrentlyLoaded();
			clickNthInviteEmail(countBefore);
			countAfter = getCountOfInvitesCurrentlyLoaded();
		}
		while(countBefore!=countAfter);	 
		
		commonMethods.switchBackToMainFrame(driver);
	}
	
	public String getNthInviteName(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		String found = driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableName)).getText();
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public String getNthInviteEmail(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		String found = driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableEmail)).getText();
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public String getNthInviteStatus(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		String found = driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableStatus)).getText();
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public String getNthInviteSinceInvite(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		String found = driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableSinceInvite)).getText();
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public String getNthInviteOrganization(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		String found = driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableOrganization)).getText();
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Invitations clickNthInviteEmail(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableEmail)).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(invitationsTable), driver);
		commonMethods.switchBackToMainFrame(driver);
		return this;
	}	
	
	public void clickNthInviteResendInvite(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableResendInvite)).click();
		commonMethods.switchBackToMainFrame(driver);
	}	
	
	public void clickNthInviteWithdrawInvite(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableWithdrawInvite)).click();
		commonMethods.switchBackToMainFrame(driver);
	}	
	
	public void clickNthInviteViewInvite(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		driver.findElement(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableViewInvite)).click();
		commonMethods.switchBackToMainFrame(driver);
	}
	
	/**
	 * By default, not all invitations are loaded.  This method returns the numbers of invitations
	 * loaded in the table
	 */
	public int getCountOfInvitesCurrentlyLoaded()
	{
		commonMethods.switchBackToMainFrame(driver);
		
		int count = driver.findElements(By.cssSelector(invitationsTableRows)).size();
		commonMethods.switchBackToMainFrame(driver);
		return count;
	}
	
	public int findRowWithEmail(String email)
	{
		int totalInvites = getCountOfInvitesCurrentlyLoaded();
		int returnRow=0;
		for (int i = 1; i<= totalInvites; i++)
		{
			if (getNthInviteEmail(i).equals(email))
				returnRow=i;
		}
		return returnRow;
	}
	
	public Boolean isNthInviteNameDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableName), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteEmailDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableEmail), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public Boolean isNthInviteStatusDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableStatus), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}

	
	public Boolean isNthInviteSinceInviteDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableSinceInvite), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}

	
	public Boolean isNthInviteOrganizationDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableOrganization), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}

	
	public Boolean isNthInviteResendInviteDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableResendInvite), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}

	
	public Boolean isNthInviteWithdrawInviteDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableWithdrawInvite), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}

	
	public Boolean isNthInviteViewInviteDisplayed(int n)
	{
		int x = getInvitationsDiv(n);
		int y = getInvitationsRow(n);
		
		commonMethods.switchBackToMainFrame(driver);
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(invitationsTable + ":nth-child("+(x)+")" +invitationsTableRow + ":nth-child("+y+")" + invitationsTableViewInvite), driver);
		commonMethods.switchBackToMainFrame(driver);
		return found;
	}
	
	public int getInvitationsDiv(int n)
	{
		int x = (n/countOfInvitesLoadedByDefault);
		int y = (n%countOfInvitesLoadedByDefault);
		if (y==0)
		{
			y=countOfInvitesLoadedByDefault;
			x=x-1;				
		}
		x+=1;
		return x;
	}
	
	public int getInvitationsRow(int n)
	{
		int y = (n%countOfInvitesLoadedByDefault);
		if (y==0)
		{
			y=countOfInvitesLoadedByDefault;			
		}
		return y;
	}
}
