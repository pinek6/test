package admin.members.people;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class People {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By nameContains = By.id("nameLike");
	private By orgnanization = By.id("orgId");
	private By emailContains = By.id("emailLike");
	private By noOfItems = By.id("itemsPerPage");
	private By updateDisplay = By.name("updatedisplay");
	private By createMember = By.cssSelector("input[value='Create Member']");
	private By pagingText = By.cssSelector("div[class='lotusLeft']");
	private By previousLink = By.cssSelector("div[class='lotusPaging'] > ul > li");
	private By nextLink = By.cssSelector("div[class='lotusPaging'] > ul > li:nth-child(2)");
	private By show10ItemsLink = By.cssSelector("tr[class='pag_bottom'] > td > div > ul > li > a");
	private By show20ItemsLink = By.cssSelector("tr[class='pag_bottom'] > td > div > ul > li:nth-child(2) > a");
	private By show50ItemsLink = By.cssSelector("tr[class='pag_bottom'] > td > div > ul > li:nth-child(3) > a");
	private By show100ItemsLink = By.cssSelector("tr[class='pag_bottom'] > td > div > ul > li:nth-child(4) > a");

	/* String Element Constructors */
	private String resultsGrid = "div[class='list_results'] > table > tbody > tr";
	private String resultsGridEditLink = " td:nth-child(2) > a";
	private String resultsGridResetPasswordLink = " td:nth-child(2) > span > a";
	private String resultsGridName = " td:nth-child(3)";
	private String resultsGridEmail = " td:nth-child(4)";
	private String resultsGridStatus = " td:nth-child(5)";
	private String resultsGridOrganization = " td:nth-child(6)";
	
	public People(WebDriver driver)
	{
		this.driver=driver;
	}	

	public CreateMember clickCreateMember()
	{
		
		
		driver.findElement(createMember).click();
		
		return new CreateMember(driver);
	}
	
	public People enterNameContains(String name)
	{
		
		
		driver.findElement(nameContains).sendKeys(name);
		
		return this;
	}
	
	public People selectOrganizationByIndex(int index)
	{
		
		
		new Select(driver.findElement(orgnanization)).selectByIndex(index);
		
		return this;
	}
	
	public People selectOrganizationByValue(String value)
	{
		
		
		new Select(driver.findElement(orgnanization)).selectByValue(value);
		
		return this;
	}
	
	public People selectOrganizationByVisibleText(String text)
	{
		
		
		new Select(driver.findElement(orgnanization)).selectByVisibleText(text);
		
		return this;
	}
	
	public People enterEmailContains(String email)
	{
		
		
		driver.findElement(emailContains).sendKeys(email);
		
		return this;
	}
	
	public People enterNumOfItems(String number)
	{
		
		
		driver.findElement(noOfItems).sendKeys(number);
		
		return this;
	}
	
	public People clickUpdateDisplay()
	{		
		
		
		driver.findElement(updateDisplay).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return this;
	}
	
	public String getPagingText()
	{
		
				
		String text = driver.findElement(pagingText).getText();
		
		return text;		
	}
	
	public CreateMember clickPrevious()
	{
		
		
		driver.findElement(previousLink).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return new CreateMember(driver);
	}
	
	public CreateMember clickNext()
	{
		
		
		driver.findElement(nextLink).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return new CreateMember(driver);
	}
	
	public CreateMember clickNthEdit(int n)
	{
		
		
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridEditLink)).click();
		
		return new CreateMember(driver);
	}
	
	public CreateMember clickNthResetPassword(int n)
	{
		
		
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridResetPasswordLink)).click();
		
		return new CreateMember(driver);
	}
	
	public String getNthNameInResultsGrid(int n)
	{
		
		
		String name = driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridName)).getText();		
		
		return name;
	}
	
	public String getNthEmailInResultsGrid(int n)
	{
		
		
		String name = driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridEmail)).getText();		
		
		return name;
	}
	
	public String getNthStatusInResultsGrid(int n)
	{
		
		
		String name = driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridStatus)).getText();		
		
		return name;
	}
	
	public String getNthOrganizationInResultsGrid(int n)
	{
		
		
		String name = driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridOrganization)).getText();		
		
		return name;
	}
	
	public int getCountOfResults()
	{
		
		
		int resultsCount = driver.findElements(By.cssSelector(resultsGrid+resultsGridEditLink)).size()-3;		
		
		return resultsCount;
	}
	
	public CreateMember clickShow10()
	{
		
		
		driver.findElement(show10ItemsLink).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return new CreateMember(driver);
	}
	
	public CreateMember clickShow20()
	{
		
		
		driver.findElement(show20ItemsLink).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return new CreateMember(driver);
	}
	
	public CreateMember clickShow50()
	{
		
		
		driver.findElement(show50ItemsLink).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return new CreateMember(driver);
	}
	
	public CreateMember clickShow100()
	{
		
		
		driver.findElement(show100ItemsLink).click();
		commonMethods.waitForElementToBeRefreshed(pagingText, driver);
		
		return new CreateMember(driver);
	}
	
	public Boolean isNthEditLinkDisplayed(int n)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(resultsGrid+":nth-child("+(n+2)+")"+resultsGridEditLink), driver);
	}
}

