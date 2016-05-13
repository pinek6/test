package admin.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class ConfigurationOptions {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By headerLocator = By.cssSelector(".lotusHeader > h1");
	
	/* String Element Locator Constructors */
	private String configurationOptionsTable = ".list_results > table > tbody > tr";
	private String configurationOptionsTableEdit = " > td:nth-child(2) > a";
	private String configurationOptionsTableGroup = " > td:nth-child(3)";
	private String configurationOptionsTableName = " > td:nth-child(4)";
	private String configurationOptionsTableValue = " > td:nth-child(5)";
	private String configurationOptionsTableDescription = " > td:nth-child(6)";
	
	public ConfigurationOptions(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		
		
		String header = driver.findElement(headerLocator).getText();
		
		return header;
	}
	
	public Boolean isConfigurationOptionsTableDisplayed()
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(configurationOptionsTable), driver);
		
		return found;
	}
	
	public int getCountOfConfigurationOptions()
	{
		
		
		int count = driver.findElements(By.cssSelector(configurationOptionsTable)).size()-1;
		
		return count;
	}

	public Boolean isNthEditLinkDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableEdit), driver);
		
		return found;
	}

	public Boolean isNthGroupDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableGroup), driver);
		
		return found;
	}

	public Boolean isNthNameDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableName), driver);
		
		return found;
	}

	public Boolean isNthValueDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableValue), driver);
		
		return found;
	}

	public Boolean isNthDescriptionDisplayed(int n)
	{
		
		
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableDescription), driver);
		
		return found;
	}
	
	public void clickNthEditLink(int n)
	{
		
		
		driver.findElement(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableEdit)).click();
		
	}
	
	public String getNthGroup(int n)
	{
		
		
		String found = driver.findElement(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableGroup)).getText();
		
		return found;
	}
	
	public String getNthName(int n)
	{
		
		
		String found = driver.findElement(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableName)).getText();
		
		return found;
	}
	
	public String getNthValue(int n)
	{
		
		
		String found = driver.findElement(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableValue)).getText();
		
		return found;
	}
	
	public String getNthDescription(int n)
	{
		
		
		String found = driver.findElement(By.cssSelector(configurationOptionsTable+":nth-child("+(n+1)+")"+configurationOptionsTableDescription)).getText();
		
		return found;
	}
	
}
