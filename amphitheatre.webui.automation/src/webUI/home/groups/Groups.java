package webUI.home.groups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class Groups {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);	
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupsHome'] > div > h1");
	private By myGroupsTabLocator = By.id("MY_GROUPS");
	private By joinGroupsTabLocator = By.id("JOIN_GROUPS");
	public By myInvitationsPanelLocator = By.cssSelector(".columnsRightBody");
	public By myInvitationsCount = By.cssSelector(".columnsRightBody > h4 > span");

	public Groups(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public MyGroups clickMyGroupsTab()
	{
		driver.findElement(myGroupsTabLocator).click();
		return new MyGroups(driver);
	}
	
	public JoinGroups clickJoinGroupsTab()
	{
		driver.findElement(joinGroupsTabLocator).click();
		return new JoinGroups(driver);
	}
	
	public Boolean isMyGroupsTabSelected()
	{
		return driver.findElement(myGroupsTabLocator).getAttribute("class").contains("selected") ? true : false;
	}
	
	public Boolean isJoinGroupsTabSelected()
	{
		return driver.findElement(joinGroupsTabLocator).getAttribute("class").contains("selected") ? true : false;
	}	
	
	public Boolean isMyInvitationsPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(myInvitationsPanelLocator, driver);
	}
	
	public int getMyInvitationsCount()
	{
		return Integer.parseInt(driver.findElement(myInvitationsCount).getText().trim());
	}
	
}
