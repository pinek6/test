package admin.members.organizations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class CreateOrganization {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By resultMessage = By.cssSelector("div[class='result_message_box']");
	private By validationMessage = By.cssSelector(".error_message_box");
	private By name = By.id("elementTitle");
	private By parentOrganization = By.id("parentId");
	private By organizationType = By.id("customFieldGroup");
	private By country = By.id("orgCountry");
	private By city = By.id("orgCity");
	private By submit = By.name("submit");
	private By save = By.name("apply");
	private By cancel = By.name("cancel");
	
	public CreateOrganization (WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public Boolean isResultMessageDisplayed()
	{
		
		
		boolean resultMessageDisplayed = commonMethods.isElementTextDisplayedAndNotEmpty(resultMessage, driver);
		
		return resultMessageDisplayed;
	}
	
	public Boolean isValidationMessageDisplayed()
	{
		boolean found = false;
		
		
		found = commonMethods.isElementPresent(validationMessage, driver);
		
		return found;
	}
	
	public String getValidationMessage()
	{
		
		
		String found = driver.findElement(validationMessage).getText();
		
		return found;
	}
	
	public CreateOrganization enterName(String nameText)
	{
		
		
		driver.findElement(name).clear();
		driver.findElement(name).sendKeys(nameText);
		
		return this;
	}
	
	public CreateOrganization selectParentOrganizationByIndex(int index)
	{
		
		
		new Select(driver.findElement(parentOrganization)).selectByIndex(index);
		
		return this;
	}
	
	public CreateOrganization selectOrganizationTypeByIndex(int index)
	{
		
		
		new Select(driver.findElement(organizationType)).selectByIndex(index);
		
		return this;
	}
	
	/**
	 * 
	 * @param value Accepted values are basic, Hospital or Site
	 * @return
	 */
	public CreateOrganization selectOrganizationTypeByValue(String value)
	{
		
		
		new Select(driver.findElement(organizationType)).selectByValue(value);
		
		return this;
	}
	
	public void clickSaveAndClose()
	{
		
		
		driver.findElement(submit).click();
		
	}
	
	public void clickSave()
	{
		
		
		driver.findElement(save).click();
		
	}
	
	public void clickCancel()
	{
		
		
		driver.findElement(cancel).click();
		
	}
	
	public Boolean isNameDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(name, driver);
		
		return found;
	}
	
	public Boolean isParentOrganizationDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(parentOrganization, driver);
		
		return found;
	}
	
	public Boolean isOrganizationTypeDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(organizationType, driver);
		
		return found;
	}	
	
	public Boolean isSubmitDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(submit, driver);
		
		return found;
	}
	
	public Boolean isSaveDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(save, driver);
		
		return found;
	}
	
	public Boolean isCancelDisplayed()
	{
		
		
		boolean found = commonMethods.isElementPresent(cancel, driver);
		
		return found;
	}
	
	public String getNameValue()
	{
		
		
		String found = driver.findElement(name).getAttribute("value");
		
		return found;
	}
	
	public Boolean isOptionSelectedInOrganizationTypeDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(organizationType));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		
		
		
		return selected;
	}
	
	public CreateOrganization selectCountryByVisibleText(String cntry)
	{
		
		
		new Select(driver.findElement(country)).selectByVisibleText(cntry);
		
		return this;
	}
	
	public CreateOrganization selectCountryByIndex(int index)
	{
		
		
		new Select(driver.findElement(country)).selectByIndex(index);
		commonMethods.waitForElementToBeRefreshed(city, driver);
		
		return this;
	}
	
	public CreateOrganization selectCountryByValue(String value)
	{
		
		
		new Select(driver.findElement(country)).selectByValue(value);
		
		return this;
	}
	
	public String getSelectedCountryVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(country);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCountryValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(country);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCityVisibleText()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(city);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getText();
        }	
		
		
		return selectedOption;
	}
	
	public String getSelectedCityValue()
	{
		
		
		String selectedOption="";
		
		WebElement select = driver.findElement(city);
        List<WebElement> allElements = select.findElements(By.tagName("option"));

        for (WebElement element: allElements) {
        	if(element.isSelected())
        	selectedOption=element.getAttribute("value");
        }	
		
		
		return selectedOption;
	}
	
	public CreateOrganization selectCityByVisibleText(String cty)
	{
		
		
		new Select(driver.findElement(city)).selectByVisibleText(cty);
		
		return this;
	}
	
	public CreateOrganization selectCityByIndex(int index)
	{
		
		
		new Select(driver.findElement(city)).selectByIndex(index);
		
		return this;
	}
	
	public CreateOrganization selectCityByValue(String value)
	{
		
		
		new Select(driver.findElement(city)).selectByValue(value);
		
		return this;
	}
	
	public Boolean isOptionSelectedInCountryDropdown(String option)
	{
		
		
		
		boolean selected = false;

		/* Create an instance for the drop down. */
		Select DropdownList = new Select(driver.findElement(country));

		/* Get first selected value */
		WebElement firstSelectedOption = DropdownList.getFirstSelectedOption();
		
		if (option.equals(firstSelectedOption.getAttribute("value")))
				selected=true;		

		
		return selected;
	}
	
	public Boolean isCountryDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(country, driver);
	}
	
	public Boolean isCityDropdownDisplayed()
	{
		return commonMethods.isElementDisplayed(city, driver);
	}
}
