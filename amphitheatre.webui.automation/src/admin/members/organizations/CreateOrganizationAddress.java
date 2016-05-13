package admin.members.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class CreateOrganizationAddress extends CreateOrganization {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");	
	private By streetAddress1 = By.id("OrgStreetAddress_1");
	private By streetAddress2 = By.id("OrgStreetAddress_2");
	private By city = By.id("orgCity");
	private By state = By.id("orgState");
	private By country = By.id("OrgCountry");
	
	public CreateOrganizationAddress (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	
	public CreateOrganization enterStreetAddress1(String ad1)
	{
		
		
		driver.findElement(streetAddress1).clear();
		driver.findElement(streetAddress1).sendKeys(ad1);
		
		return this;
	}

	public CreateOrganization enterStreetAddress2(String ad2)
	{
		
		
		driver.findElement(streetAddress2).clear();
		driver.findElement(streetAddress2).sendKeys(ad2);
		
		return this;
	}
	
	public CreateOrganization enterCity(String cityText)
	{
		
		
		driver.findElement(city).clear();
		driver.findElement(city).sendKeys(cityText);
		
		return this;
	}

	public CreateOrganization enterState(String stateText)
	{
		
		
		driver.findElement(state).clear();
		driver.findElement(state).sendKeys(stateText);
		
		return this;
	}
	
	public CreateOrganization enterCountry(String countryText)
	{
		
		
		driver.findElement(country).clear();
		driver.findElement(country).sendKeys(countryText);
		
		return this;
	}
	
	public Boolean isStreetAddress1Displayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(streetAddress1, driver);
		
		return found;
	}
	
	public Boolean isStreetAddress2Displayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(streetAddress2, driver);
		
		return found;
	}
	
	public Boolean isCityDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(city, driver);
		
		return found;
	}

	public Boolean isStateDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(state, driver);
		
		return found;
	}
	
	public Boolean isCountryDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(country, driver);
		
		return found;
	}
	
	public String getStreetAddress1Value()
	{
		
		
		String found = driver.findElement(streetAddress1).getAttribute("value");
		
		return found;
	}
	
	public String getStreetAddress2Value()
	{
		
		
		String found = driver.findElement(streetAddress2).getAttribute("value");
		
		return found;
	}
	
	public String getCityValue()
	{
		
		
		String found = driver.findElement(city).getAttribute("value");
		
		return found;
	}

	public String getStateValue()
	{
		
		
		String found = driver.findElement(state).getAttribute("value");
		
		return found;
	}
	
	public String getCountryValue()
	{
		
		
		String found = driver.findElement(country).getAttribute("value");
		
		return found;
	}
}
