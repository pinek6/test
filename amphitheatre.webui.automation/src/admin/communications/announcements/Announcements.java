package admin.communications.announcements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class Announcements {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By createAnnouncement = By.cssSelector("input[value='Create Announcement']");
	private By accouncementsTableRow = By.cssSelector(".initial");
	private By resultMessage = By.cssSelector(".result_message_item");
	
	/* String Element Constructors */
	private String resultsTable = "table[class='lotusTable']> tbody > tr";
	private String resultsTableEditLink = " > td:nth-child(2) > a";
	private String resultsTableDeleteLink = " > td:nth-child(2) > a:nth-child(2)";
	private String resultsTableInternalName = " > td:nth-child(3)";
	private String resultsTableOrder = " > td:nth-child(4)";
	private String resultsTableStatus = " > td:nth-child(6)";
	private String resultsTableAutoActiveDate = " > td:nth-child(7)";
	private String resultsTableAutoArchiveDate = " > td:nth-child(8)";
	
	
	public Announcements(WebDriver driver)
	{
		this.driver=driver;
	}
	
	/**
	 * This message should appear when an announcement is deleted
	 * @return
	 */
	public Boolean isResultMessageDisplayed()
	{
		boolean found = false;
		
		
		found = commonMethods.isElementDisplayed(resultMessage, driver);
		
		return found;
	}
	
	public CreateAnnouncement clickCreateAnnouncement()
	{
		
		
		driver.findElement(createAnnouncement).click();
		
		return new CreateAnnouncement(driver);
	}
	
	public int getCountOfAnnouncements()
	{
		
		
		int count = driver.findElements(accouncementsTableRow).size();
		
		return count;
	}
	
	public CreateAnnouncement clickNthEditLink(int n)
	{
		
		
		driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableEditLink)).click();
		
		return new CreateAnnouncement(driver);
	}
	
	public void clickNthDeleteLink(int n)
	{
		
		
		driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableDeleteLink)).click();
		driver.switchTo().alert().accept();
		
	}
	
	public String getNthAnnouncementInternalTitle(int n)
	{
		
		
		String title = driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableInternalName)).getText();
		
		return title;
	}
	
	public String getNthAnnouncementOrder(int n)
	{
		
		
		String title = driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableOrder)).getText();
		
		return title;
	}
	
	public String getNthAnnouncementStatus(int n)
	{
		
		
		String title = driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableStatus)).getText();
		
		return title;
	}	
	
	public String getNthAnnouncementAutoActiveDate(int n)
	{
		
		
		String title = driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableAutoActiveDate)).getText();
		
		return title;
	}
	
	public String getNthAnnouncementAutoArchiveDate(int n)
	{
		
		
		String title = driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")"+resultsTableAutoArchiveDate)).getText();
		
		return title;
	}
	
	public Boolean isAnnouncementInternalTitleFound(String title)
	{
		boolean found = false;
		
		
		
		for (int i = 1; i <= getCountOfAnnouncements(); i++ )
		{
			if (getNthAnnouncementInternalTitle(i).equals(title))
				found=true;
		}	
		
		
		return found;
	}
	
	public void deleteAnnouncement(String internalTitle)
	{
		
		
		
		int countOfAnnouncements = getCountOfAnnouncements();
		for (int i = 1; i <= countOfAnnouncements; i++ )
		{
			if (getNthAnnouncementInternalTitle(i).equals(internalTitle))
			{
				clickNthDeleteLink(i);
				i=countOfAnnouncements+1;
			}
		}	
		
		
	}
	/**
	 * This method returns the row number where the internal title is passed as a parameter
	 * @param internalTitle The internal title string
	 * @return Returns -1 if not found or the row number if found
	 */
	public int getAnnouncementRow(String internalTitle)
	{
		int row = -1;
		
		
		int countOfAnnouncements = getCountOfAnnouncements();
		for (int i = 1; i<=countOfAnnouncements; i++)
		{
			if (getNthAnnouncementInternalTitle(i).equals(internalTitle))
			{
				row=i;
				i=countOfAnnouncements+1;
			}
		}
		
		
		return row;
	}
	
}
