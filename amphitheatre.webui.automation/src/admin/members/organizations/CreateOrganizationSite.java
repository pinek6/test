package admin.members.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class CreateOrganizationSite extends CreateOrganization {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");	
	private By sametimeStatus = By.id("sametimeStatus");
	private By organizationTypeAdditionalInformation = By.id("siteOrgType");
	
	public CreateOrganizationSite (WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}	
	
	/**
	 * 
	 * @param value Supported options are Active or Disabled
	 * @return
	 */
	public CreateOrganizationSite selectSametimeStatusByValue(String value)
	{
		
		
		new Select(driver.findElement(sametimeStatus)).selectByValue(value);
		
		return this;
	}
	
	/**
	 * 
	 * @param value Only supported option is Group
	 * @return
	 */
	public CreateOrganizationSite selectOrganizationTypeAdditionalInformationByValue(String value)
	{
		
		
		new Select(driver.findElement(organizationTypeAdditionalInformation)).selectByValue(value);
		
		return this;
	}
	
	/**
	 * 
	 * @param option Supported options are Active or Disabled
	 * @return
	 */
	public Boolean isOptionSelectedInSametimeStatusDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(sametimeStatus));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
	
	/**
	 * 
	 * @param option Supported option is Group
	 * @return
	 */
	public Boolean isOptionSelectedInOrganizationTypeAdditionalInformationDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(organizationTypeAdditionalInformation));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
}
