package webUI.home.groups;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.ConfigProperties;

public class GroupsViewOverviewTab extends GroupsView
{
	
	private WebDriver driver;

	/* Element Locators */ 
	private By headerLocator = By.id("nameId");	
	
	private By customContentPanel = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedContent_']:nth-child(1)");	
	private By customContentTitle = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedContent_']:nth-child(1) > h3 > span:nth-child(3)");	
	private By customContentText = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedContent_'] > div:nth-child(4)");	
	private By customContentEditLinkLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedContent_'] > div:nth-child(5) > a");	
	private By featuredResourcesPanel = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedResources_']");
	private By featuredResourcesTitle = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedResources_'] > div > div > h3");
	private By featuredResourcesText = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedResources_'] > div > div:nth-child(2)");	
	private By featuredResourcesEditLinkLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedResources_'] > div:nth-child(4) > a");	
	private By featuredPeoplePanel = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedPeople_']");
	private By featuredPeopleTitle = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedPeople_'] > h3");
	private By featuredPeopleText = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedPeople_'] > div > div");	
	private By featuredPeopleEditLinkLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewFeaturedPeople_'] > div:nth-child(3) > a");
	private By newsPanel = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewNews_']");
	private By newsTitle = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewNews_'] > h3");
	private By newsText = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewNews_']:nth-child(1) > div:nth-child(2)");	
	private By newsEditLinkLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewNews_'] > div:nth-child(3) > a");
	
	private By recentUpdatesPanel = By.cssSelector("div[id^='sls_widgets_components_groups_GroupOverviewRecentUpdates_']");

	public GroupsViewOverviewTab(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
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
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public EditBlock clickEditCustomContent()
	{
		driver.findElement(customContentEditLinkLocator).click();
		return new EditBlock(driver);
	}

	public EditBlock clickEditFeaturedResources()
	{
		driver.findElement(featuredResourcesEditLinkLocator).click();
		return new EditBlock(driver);
	}

	public EditBlock clickEditFeaturedPeople()
	{
		driver.findElement(featuredPeopleEditLinkLocator).click();
		return new EditBlock(driver);
	}

	public EditBlock clickEditNews()
	{
		driver.findElement(newsEditLinkLocator).click();
		return new EditBlock(driver);
	}
	
	public String getCustomContentTitle()
	{
		return driver.findElement(customContentTitle).getText();
	}
	
	public String getCustomContentText()
	{
		return driver.findElement(customContentText).getText();
	}
	
	public String getFeaturedResourcesTitle()
	{
		return driver.findElement(featuredResourcesTitle).getText();
	}
	
	public String getFeaturedResourcesText()
	{
		return driver.findElement(featuredResourcesText).getText();
	}
	
	public String getFeaturedPeopleTitle()
	{
		return driver.findElement(featuredPeopleTitle).getText();
	}
	
	public String getFeaturedPeopleText()
	{
		return driver.findElement(featuredPeopleText).getText();
	}
	
	public String getNewsTitle()
	{
		return driver.findElement(newsTitle).getText();
	}
	
	public String getNewsText()
	{
		return driver.findElement(newsText).getText();
	}
	
	public Boolean isCustomContentPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(customContentPanel, driver);
	}
	
	public Boolean isFeaturedResourcesPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(featuredResourcesPanel, driver);
	}
	
	public Boolean isFeaturedPeoplePanelDisplayed()
	{
		return commonMethods.isElementDisplayed(featuredPeoplePanel, driver);
	}
	
	public Boolean isNewsPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(newsPanel, driver);
	}
	
	public Boolean isRecentUpdatesPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(recentUpdatesPanel, driver);
	}
}
