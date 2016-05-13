package webUI.home.groups;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class GroupsView {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	public GroupsView(WebDriver driver)
	{
		this.driver=driver;
	}
	
	/* Element Locators */ 
	private By headerLocator = By.id("nameId");
	private By membersOnlyGroup = By.cssSelector(".groupType.private");
	private By publicAccessGroup = By.cssSelector(".groupType.public");
	private By editGroupButtonLocator = By.id("editDoneLinkId");
	private By followLinkLocator = By.id("followLinkId");
	private By deleteLinkLocator = By.id("deleteLinkId");
	private By leaveLinkLocator = By.id("leaveLinkId");
	private By overviewTabLocator = By.id("GROUP_OVERVIEW");
	private By membersTabLocator = By.id("GROUP_MEMBERS");
	private By resourcesTabLocator = By.id("GROUP_RESOURCES");
	private By discussionsTabLocator = By.id("GROUP_DISCUSSIONS");
	private By getStartedButtonLocator = By.cssSelector("input[value='get started']");
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLocator));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editGroupButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(editGroupButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(overviewTabLocator));
		wait.until(ExpectedConditions.elementToBeClickable(membersTabLocator));
		wait.until(ExpectedConditions.elementToBeClickable(resourcesTabLocator));
		wait.until(ExpectedConditions.elementToBeClickable(discussionsTabLocator));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(followLinkLocator));
//		wait.until(ExpectedConditions.elementToBeClickable(followLinkLocator));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(leaveLinkLocator));
//		wait.until(ExpectedConditions.elementToBeClickable(leaveLinkLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public GroupsViewOverviewTab clickOverviewTab()
	{
		driver.findElement(overviewTabLocator).click();
		return new GroupsViewOverviewTab(driver);
	}
	
	public GroupsViewMembersTab clickMembersTab()
	{
		driver.findElement(membersTabLocator).click();
		return new GroupsViewMembersTab(driver);
	}

	public GroupsViewResourcesTab clickResourcesTab()
	{
		driver.findElement(resourcesTabLocator).click();
		return new GroupsViewResourcesTab(driver);
	}

	public GroupsViewDiscussionsTab clickDiscussionsTab()
	{
		driver.findElement(discussionsTabLocator).click();
		return new GroupsViewDiscussionsTab(driver);
	}
	
	public Boolean isOverviewTabDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(overviewTabLocator, driver);
	}
	
	public Boolean isMembersTabDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(membersTabLocator, driver);
	}

	public Boolean isResourcesTabDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(resourcesTabLocator, driver);
	}

	public Boolean isDiscussionsTabDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(discussionsTabLocator, driver);
	}
	
	public Boolean isOverviewTabSelected()
	{
		return driver.findElement(overviewTabLocator).getAttribute("class").contains("selected") ? true : false ;
	}
	
	public Boolean isMembersTabSelected()
	{
		return driver.findElement(membersTabLocator).getAttribute("class").contains("selected") ? true : false ;
	}

	public Boolean isResourcesTabSelected()
	{
		return driver.findElement(resourcesTabLocator).getAttribute("class").contains("selected") ? true : false ;
	}

	public Boolean isDiscussionsTabSelected()
	{
		return driver.findElement(discussionsTabLocator).getAttribute("class").contains("selected") ? true : false ;
	}
	
	public GroupsView clickEditGroup()
	{
		commonMethods.waitForElementToBeVisibleAndClickable(editGroupButtonLocator, driver);
		driver.findElement(editGroupButtonLocator).click();
		commonMethods.waitForElementToBeVisibleAndClickable(editGroupButtonLocator, driver);
		return this;
	}
	
	public void clickFollow()
	{
		driver.findElement(followLinkLocator).click();
	}
	
	public LeaveGroup clickLeave()
	{
		driver.findElement(leaveLinkLocator).click();
		return new LeaveGroup(driver);
	}
	
	public Boolean isPublicAccessTextDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(publicAccessGroup, driver);
	}
	
	public Boolean isMembersOnlyTextDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(membersOnlyGroup, driver);
	}
	
	public DeleteThisGroup clickDelete()
	{
		driver.findElement(deleteLinkLocator).click();
		return new DeleteThisGroup(driver);
	}
	
	public void clickGetStarted()
	{
		driver.findElement(getStartedButtonLocator).click();
	}
}
