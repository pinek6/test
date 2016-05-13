package webUI.home.groups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class NewGroupReady {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */ 
	private By getStartedButtonLocator = By.id("closeButtonId");

	public NewGroupReady(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public GroupsView clickGetStarted()
	{
		driver.findElement(getStartedButtonLocator).click();
		return new GroupsView(driver);
	}

}
