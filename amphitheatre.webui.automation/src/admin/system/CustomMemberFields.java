package admin.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class CustomMemberFields {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By headerLocator = By.cssSelector(".lotusHeader > h1");
	private By displayNameContains = By.id("nameLike");
	private By filterList = By.name("updatedisplay");
	
	/* String Element Locator Constructors */
	private String matchingMemberFieldsTable = ".list_results > table > tbody > tr";
	private String matchingMemberFieldsTableEdit = " > td:nth-child(2) > a";
	private String matchingMemberFieldsTableOrder = " > td:nth-child(3)";
	private String matchingMemberFieldsTableDisplayName = " > td:nth-child(4)";
	private String matchingMemberFieldsTableInternalName = " > td:nth-child(5)";
	private String matchingMemberFieldsTableFormType = " > td:nth-child(6)";
	private String matchingMemberFieldsTableSelectionOptions = " > td:nth-child(7) > a";
	private String matchingMemberFieldsTableDefault = " > td:nth-child(8)";
	private String matchingMemberFieldsTableRequired = " > td:nth-child(9)";
	private String matchingMemberFieldsTableInRegistration = " > td:nth-child(10)";
	private String matchingMemberFieldsTableAdminEditable = " > td:nth-child(11)";
	
	
	public CustomMemberFields(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		
		
		String header = driver.findElement(headerLocator).getText();
		
		return header;
	}
	
	public Boolean isDisplayNameContainsDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(displayNameContains, driver);
		
		return found;
	}
	
	public CustomMemberFields enterDisplayName(String name)
	{
		
		
		driver.findElement(displayNameContains).sendKeys(name);
		
		return this;
	}
	
	public Boolean isFilterListDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(filterList, driver);
		
		return found;
	}
	
	public CustomMemberFields clickFilterList()
	{
		
		
		driver.findElement(filterList).click();
		
		return this;
	}
	
	public int getCountOfMatchingMemberFields()
	{
		
		
		int count = driver.findElements(By.cssSelector(matchingMemberFieldsTable)).size()-2;
		
		return count;
	}		

	public void clickNthEditLink(int n)
	{
		
		
		driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableEdit)).click();
		
	}

	public Boolean isNthEditLinkDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableEdit), driver);
		
		return found;
	}	

	public Boolean isNthOrderDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableOrder), driver);
		
		return found;
	}

	public Boolean isNthDisplayNameDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableDisplayName), driver);
		
		return found;
	}

	public Boolean isNthInternalNameDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableInternalName), driver);
		
		return found;
	}

	public Boolean isNthFormTypeDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableFormType), driver);
		
		return found;
	}

	public Boolean isNthSelectionOptionsDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableSelectionOptions), driver);
		
		return found;
	}

	public Boolean isNthDefaultDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableDefault), driver);
		
		return found;
	}

	public Boolean isNthRequiredDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableRequired), driver);
		
		return found;
	}	

	public Boolean isNthInRegistrationDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableInRegistration), driver);
		
		return found;
	}	

	public Boolean isNthAdminEditableDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableAdminEditable), driver);
		
		return found;
	}	
	
	public String getNthEditLinkText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableEdit)).getText();
		
		return value;
	}	

	public String getNthOrderText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableOrder)).getText();
		
		return value;
	}

	public String getNthDisplayNameText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableDisplayName)).getText();
		
		return value;
	}

	public String getNthInternalNameText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableInternalName)).getText();
		
		return value;
	}

	public String getNthFormTypeText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableFormType)).getText();
		
		return value;
	}

	public String getNthSelectionOptionsText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableSelectionOptions)).getText();
		
		return value;
	}

	public String getNthDefaultText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableDefault)).getText();
		
		return value;
	}

	public String getNthRequiredText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableRequired)).getText();
		
		return value;
	}	

	public String getNthInRegistrationText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableInRegistration)).getText();
		
		return value;
	}	

	public String getNthAdminEditableText(int n)
	{
		
		
		String value = driver.findElement(By.cssSelector(matchingMemberFieldsTable+":nth-child("+(n+2)+")"+matchingMemberFieldsTableAdminEditable)).getText();
		
		return value;
	}

}
