package admin.members.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class CreateOrganizationBasicOrganization extends CreateOrganizationAddress {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");	
	private By location = By.id("location");
	
	public CreateOrganizationBasicOrganization (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}	
	
	public CreateOrganization enterLocation(String locationText)
	{
		
		
		driver.findElement(location).clear();
		driver.findElement(location).sendKeys(locationText);
		
		return this;
	}

	public Boolean isLocationDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(location, driver);
		
		return found;
	}
	
	public String getLocationValue()
	{
		
		
		String found = driver.findElement(location).getAttribute("value");
		
		return found;
	}
}
