package webUI.home.groups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class LeaveGroup {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */ 
	private By getLeaveButtonLocator = By.id("okButtonId");
	private By getCancelButtonLocator = By.id("closeButtonId");

	public LeaveGroup(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public MyGroups clickLeaveButton()
	{
		driver.findElement(getLeaveButtonLocator).click();
		return new MyGroups(driver);
	}
	
	public GroupsView clickCancelButton()
	{
		driver.findElement(getCancelButtonLocator).click();
		return new GroupsView(driver);
	}

}
