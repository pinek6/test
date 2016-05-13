package admin.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class Enumerations {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By headerLocator = By.cssSelector(".lotusHeader > h1");
	private By nameContains = By.id("nameLike");
	private By simpleViewingMode = By.id("simple");
	private By fullViewingMode = By.id("full");
	private By updateDisplayOfCustomEnumerations = By.name("updatedisplay");
	private By createCustomEnumeration = By.cssSelector("input[value='Create Custom Enumeration']");
	
	/* String Element Locator Constructors */
	private String matchingCustomEnumerationTable = "form[name='pdc'] > div:nth-child(3) > table > tbody > tr";
	private String matchingCustomEnumerationTableEdit = " > td:nth-child(2) > a";
	private String matchingCustomEnumerationTableTitle = " > td:nth-child(3)";	
	private String matchingCustomEnumerationTableDescription = " > td:nth-child(4)";
	
	private String systemEnumerationsTable = "form[name='pdc'] > div:nth-child(5) > table > tbody > tr";
	private String systemEnumerationsTableView = " > td:nth-child(2) > a";
	private String systemEnumerationsTableTitle = " > td:nth-child(3)";	
	private String systemEnumerationsTableDescription = " > td:nth-child(4)";
	
	public Enumerations(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		
		
		String header = driver.findElement(headerLocator).getText();
		
		return header;
	}
	
	public Boolean isNameContainsDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(nameContains, driver);
		
		return found;
	}
	
	public Boolean isSimpleViewingModeRadiobuttonDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(simpleViewingMode, driver);
		
		return found;
	}
	
	public Boolean isFullViewingModeRadiobuttonDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(fullViewingMode, driver);
		
		return found;
	}
	
	public Boolean isUpdateDisplayOfCustomEnumerationsDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(updateDisplayOfCustomEnumerations, driver);
		
		return found;
	}
	
	public Boolean isCreateCustomEnumerationDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(createCustomEnumeration, driver);
		
		return found;
	}
	
	public Enumerations enterDisplayName(String name)
	{
		
		
		driver.findElement(nameContains).sendKeys(name);
		
		return this;
	}	
	
	public void clickSimpleViewingMode()
	{
		
		
		driver.findElement(fullViewingMode).click();
		
	}
	
	public void clickFullViewingMode()
	{
		
		
		driver.findElement(simpleViewingMode).click();
		
	}
			
	public void clickUpdateDisplayOfCustomEnumerations()
	{
		
		
		driver.findElement(updateDisplayOfCustomEnumerations).click();
		
	}
	
	public void clickCreateCustomEnumerations()
	{
		
		
		driver.findElement(createCustomEnumeration).click();
		
	}
	
	public int getCountOfMatchingCustomEnumeration()
	{
		
		
		int count = driver.findElements(By.cssSelector(matchingCustomEnumerationTable)).size()-2;
		
		return count;
	}
	
	public int getCountOfSystemEnumerations()
	{
		
		
		int count = driver.findElements(By.cssSelector(systemEnumerationsTable)).size()-2;
		
		return count;
	}
	
	public Boolean isNthEditLinkDisplayedInMatchingCustomEnumerationsTable(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingCustomEnumerationTable+":nth-child("+(n+2)+")"+matchingCustomEnumerationTableEdit), driver);
		
		return found;
	}	

	public Boolean isNthTitleDisplayedInMatchingCustomEnumerationsTable(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingCustomEnumerationTable+":nth-child("+(n+2)+")"+matchingCustomEnumerationTableTitle), driver);
		
		return found;
	}
	
	public Boolean isNthDescriptionDisplayedInMatchingCustomEnumerationsTable(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingCustomEnumerationTable+":nth-child("+(n+2)+")"+matchingCustomEnumerationTableDescription), driver);
		
		return found;
	}
	
	public Boolean isNthViewLinkDisplayedInSystemEnumerationsTable(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(systemEnumerationsTable+":nth-child("+(n+2)+")"+systemEnumerationsTableView), driver);
		
		return found;
	}	

	public Boolean isNthTitleDisplayedInSystemEnumerationsTable(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(systemEnumerationsTable+":nth-child("+(n+2)+")"+systemEnumerationsTableTitle), driver);
		
		return found;
	}
	
	public Boolean isNthDescriptionDisplayedInSystemEnumerationsTable(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(systemEnumerationsTable+":nth-child("+(n+2)+")"+systemEnumerationsTableDescription), driver);
		
		return found;
	}
	public String getNthTitleInMatchingCustomEnumerationsTable(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingCustomEnumerationTable+":nth-child("+(n+2)+")"+matchingCustomEnumerationTableTitle)).getText();
		
		return value;
	}
	
	public String getNthDescriptionInMatchingCustomEnumerationsTable(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingCustomEnumerationTable+":nth-child("+(n+2)+")"+matchingCustomEnumerationTableDescription)).getText();
		
		return value;
	}

	public String getNthTitleInSystemEnumerationsTable(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(systemEnumerationsTable+":nth-child("+(n+2)+")"+systemEnumerationsTableTitle)).getText();
		
		return value;
	}
	
	public String getNthDescriptionInSystemEnumerationsTable(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(systemEnumerationsTable+":nth-child("+(n+2)+")"+systemEnumerationsTableDescription)).getText();
		
		return value;
	}
	
	public void clickNthEditLinkInInMatchingCustomEnumerationsTable(int n)
	{
		
		
		driver.findElement(By.cssSelector(matchingCustomEnumerationTable+":nth-child("+(n+2)+")"+matchingCustomEnumerationTableEdit)).click();
		
	}

	public void clickNthViewLinkInInSystemEnumerationsTable(int n)
	{
		
		
		driver.findElement(By.cssSelector(systemEnumerationsTable+":nth-child("+(n+2)+")"+systemEnumerationsTableView)).click();
		
	}
}
