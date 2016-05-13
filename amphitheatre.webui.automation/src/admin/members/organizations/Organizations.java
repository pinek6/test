package admin.members.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class Organizations {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By createOrganization = By.cssSelector("input[type='button']");
	private By displayingcell = By.cssSelector(".displayingcell");
	
	/* String Element Constructors */	
	private String organizationsTable = "table[class='lotusTable'] > tbody > tr";
	private String organizationsTableEditLink = " > td:nth-child(1) > a:nth-child(1)";
	private String organizationsTableDeleteLink = " > td:nth-child(1) > a:nth-child(2)";
	private String organizationsTableName = "> td:nth-child(2)";
	private String organizationsCountry = "> td:nth-child(3)";
	private String organizationsCity = "> td:nth-child(4)";
	private String organizationsTableOrganizationType = "> td:nth-child(4)";
	
	public Organizations (WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public CreateOrganization clickCreateOrganization()
	{
		
		
		driver.findElement(createOrganization).click();
		
		return new CreateOrganization(driver);
	}
		
	public Boolean isCreateOrganizationDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(createOrganization, driver);
		
		return found;
	}
	
	public int getCountOfOrganizationsInTable()
	{
		
		
		int orgs = driver.findElements(By.cssSelector(organizationsTable+organizationsTableEditLink)).size();
		
		return orgs;
	}
	
	public void clickEditNthOrganizationsInTable(int n )
	{
		
		
		driver.findElement(By.cssSelector(organizationsTable+":nth-child("+(n+2)+")"+organizationsTableEditLink)).click();
		
	}
	
	public void clickDeleteNthOrganizationsInTable(int n )
	{
		
		commonMethods.scrollIntoView(driver, organizationsTable+":nth-child("+(n+2)+")"+organizationsTableDeleteLink);
		driver.findElement(By.cssSelector(organizationsTable+":nth-child("+(n+2)+")"+organizationsTableDeleteLink)).click();
		driver.switchTo().alert().accept();
		//commonMethods.waitForElementToBeRefreshed(By.cssSelector(organizationsTable), driver);
		
	}
	
	public String getNthNameInOrganizationsTable(int n)
	{
		
		
		String name = lTrim(driver.findElement(By.cssSelector(organizationsTable+":nth-child("+(n+2)+")"+organizationsTableName)).getText());
		
		return name;
	}
	
	public String getNthCountryInOrganizationsTable(int n)
	{
		
		
		String name = lTrim(driver.findElement(By.cssSelector(organizationsTable+":nth-child("+(n+2)+")"+organizationsCountry)).getText());
		
		return name;
	}
	
	public String getNthCityInOrganizationsTable(int n)
	{
		
		
		String name = lTrim(driver.findElement(By.cssSelector(organizationsTable+":nth-child("+(n+2)+")"+organizationsCity)).getText());
		
		return name;
	}
	
	public String getNthOrganizationTypeInOrganizationsTable(int n)
	{
		
		
		String orgType = driver.findElement(By.cssSelector(organizationsTable+":nth-child("+(n+2)+")"+organizationsTableOrganizationType)).getText();
		
		return orgType;
	}
	
	public Boolean isOrganizationNameFound(String orgname)
	{
		boolean found = false;
		
		
		int countOfOrganizationsInTable = getCountOfOrganizationsInTable();
		for (int i = 1; i<=countOfOrganizationsInTable; i++)
		{
			if (lTrim(getNthNameInOrganizationsTable(i)).equals(orgname))
			{
				i=countOfOrganizationsInTable+1;
				found=true;
			}	
		}
		
		return found;
	}
	
	/**
	 * Returns the row number of the organization being searched for
	 * @param name The name of the organization being searched for
	 * @return
	 */
	public int getOrganizationRow(String orgname)
	{
		int row = -1;
		
		
		int countOfOrganizationsInTable = getCountOfOrganizationsInTable();
		for (int i = 1; i<=countOfOrganizationsInTable; i++){			
			if (lTrim(getNthNameInOrganizationsTable(i)).equals(orgname))
			{
				row=i;
				i=countOfOrganizationsInTable+1;
			}
		}
		
		return row;
	}
	
	/**
	 * Method to trim leading spaces from a string
	 * @param s
	 * @return
	 */
	public static String lTrim(String s) {
	    return s.replaceAll("^\\s+", "");
	}
	
	/**
	 * Method to trim trailing spaces from a string
	 * @param s
	 * @return
	 */
	public static String rTrim(String s) {
	    return s.replaceAll("\\s+$", "");
	} 
}
