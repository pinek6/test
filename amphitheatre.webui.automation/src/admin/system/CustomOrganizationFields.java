package admin.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class CustomOrganizationFields {

	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);

	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By headerLocator = By.cssSelector(".lotusHeader > h1");
	private By displayNameContains = By.id("nameLike");
	private By filterList = By.name("updatedisplay");
	private By organizationType = By.id("orgFieldGroup");
	private By createCustomOrganizationField = By.cssSelector("input[value='Create Custom Organization Field']");

	/* String Element Locator Constructors */
	private String matchingOrganizationFieldsTable = ".list_results > table > tbody > tr";
	private String matchingOrganizationFieldsTableEdit = " > td:nth-child(2) > a:nth-child(1)";
	private String matchingOrganizationFieldsTableDelete = " > td:nth-child(2) > a:nth-child(2)";
	private String matchingOrganizationFieldsTableOrder = " > td:nth-child(3)";
	private String matchingOrganizationFieldsTableDisplayName = " > td:nth-child(4)";
	private String matchingOrganizationFieldsTableInternalName = " > td:nth-child(5)";
	private String matchingOrganizationFieldsTableFormType = " > td:nth-child(6)";
	private String matchingOrganizationFieldsTableSelectionOptions = " > td:nth-child(7) > a";
	private String matchingOrganizationFieldsTableDefault = " > td:nth-child(8)";
	private String matchingOrganizationFieldsTableOrganizationTypes = " > td:nth-child(9)";

	public CustomOrganizationFields(WebDriver driver)
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

	public CustomOrganizationFields enterDisplayName(String name)
	{


		driver.findElement(displayNameContains).sendKeys(name);

		return this;
	}

	public Boolean isFilterListDisplayed()
	{


		Boolean found = commonMethods.isElementDisplayed(filterList, driver);

		return found;
	}

	public CustomOrganizationFields clickUpdateDisplay()
	{


		driver.findElement(filterList).click();

		return this;
	}

	public Boolean isOrganizationTypeDisplayed()
	{


		Boolean found = commonMethods.isElementDisplayed(organizationType, driver);

		return found;
	}

	public Boolean isCreateCustomOrganizationFieldDisplayed()
	{


		Boolean found = commonMethods.isElementDisplayed(createCustomOrganizationField, driver);

		return found;
	}

	public void clickCreateCustomOrganizationField()
	{


		driver.findElement(createCustomOrganizationField).click();

	}

	public int getCountOfMatchingOrganizationFields()
	{


		int count = driver.findElements(By.cssSelector(matchingOrganizationFieldsTable)).size()-2;

		return count;
	}

	public Boolean isNthEditLinkDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableEdit), driver);

		return found;
	}

	public Boolean isNthDeleteLinkDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableEdit), driver);

		return found;
	}

	public Boolean isNthOrderDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableOrder), driver);

		return found;
	}

	public Boolean isNthDisplayNameDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableDisplayName), driver);

		return found;
	}

	public Boolean isNthInternalNameDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableInternalName), driver);

		return found;
	}

	public Boolean isNthFormTypeDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableFormType), driver);

		return found;
	}

	public Boolean isNthSelectionOptionsDisplayed(int n)
	{


		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableSelectionOptions), driver);

		return found;
	}

	public Boolean isNthDefaultDisplayed(int n)
	{


		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableDefault), driver);

		return found;
	}

	public Boolean isNthOrganizationTypesDisplayed(int n)
	{


		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableOrganizationTypes), driver);

		return found;
	}

	public String getNthEditLinkText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableEdit)).getText();

		return value;
	}

	public String getNthDeleteLinkText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableDelete)).getText();

		return value;
	}

	public String getNthOrderText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableOrder)).getText();

		return value;
	}

	public String getNthDisplayNameText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableDisplayName)).getText();

		return value;
	}

	public String getNthInternalNameText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableInternalName)).getText();

		return value;
	}

	public String getNthFormTypeText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableFormType)).getText();

		return value;
	}

	public String getNthSelectionOptionsText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableSelectionOptions)).getText();

		return value;
	}

	public String getNthDefaultText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableDefault)).getText();

		return value;
	}

	public String getNthOrganizationTypesText(int n)
	{


		String value = driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableOrganizationTypes)).getText();

		return value;
	}

	public void clickNthEditLink(int n)
	{


		driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableEdit)).click();

	}

	public void clickNthDeleteLink(int n)
	{


		driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableDelete)).click();

	}

	public void clickNthSelectionOptionsLink(int n)
	{


		driver.findElement(By.cssSelector(matchingOrganizationFieldsTable+":nth-child("+(n+2)+")"+matchingOrganizationFieldsTableSelectionOptions)).click();

	}
}
