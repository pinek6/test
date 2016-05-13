package webUI.home.groups;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class GroupsViewMembersTab extends GroupsView
{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	private String memberListItems = "//div[contains(@id,'sls_widgets_components_groups_MemberListItem')]";
	private String memberListItemMemberImage = "/a/img";
	private String memberListItemMemberName = "/h3";
	private String memberListItemMemberDataPosition = "/div/div";
	private String memberListItemMemberDataOrganization = "/div/div[2]";
	private String memberListItemMemberDataLocation = "/div/div[3]";
	private String memberListItemMemberType = "/div[2]";
	private String memberListItemMemberEdit = "/div[3]/span/a";
	private String memberListItemMemberRemove = "/div[3]/span[2]/a";
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("div.contentBody > h1");
	private By searchInputLocator = By.cssSelector(".searchInput");
	private By memberCountLocator = By.cssSelector("div[id^='sls_widgets_components_groups_MembersTab'] > div > div:nth-child(2)");
	private By pendingInvitationsLocator = By.cssSelector("div[id^='sls_widgets_components_groups_MembersTab'] > div > div:nth-child(3)");

	private By memberListItemsLocator = By.xpath(memberListItems);
	private By ownersPanelLocator = By.cssSelector(".columnsRightBody");
	private By showMoreLinkLocator = By.cssSelector(".linkText2");

	public GroupsViewMembersTab(WebDriver driver)
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
		
		/* Wait for elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(memberCountLocator));
		wait.until(ExpectedConditions.elementToBeClickable(memberCountLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public Boolean isSearchInputDisplayed()
	{
		return commonMethods.isElementDisplayed(searchInputLocator, driver);
	}
	
	public Boolean isMemberCountDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(memberCountLocator, driver);
	}
	
	public Boolean isPendingInvitationsDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(pendingInvitationsLocator, driver);
	}
	
	public String getMemberCountText()
	{
		return driver.findElement(memberCountLocator).getText();
	}
	
	public String getPendingInvitationText()
	{
		return driver.findElement(pendingInvitationsLocator).getText();
	}
	
	public int getCountOfMemberListItems()
	{
		return driver.findElements(memberListItemsLocator).size();
	}
	
	public String getNthMemberName(int n)
	{
		return driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberName)).getText();
	}
	
	public void clickPendingInvitationsLink()
	{
		driver.findElement(pendingInvitationsLocator).click();
	}
	
	public void clickNthMemberName(int n)
	{
		driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberName)).click();
	}

	public void clickNthMemberImage(int n)
	{
		driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberImage)).click();
	}
	
	public String getNthMemberDataPosition(int n)
	{
		return driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberDataPosition)).getText();
	}
	
	public String getNthMemberDataOrganization(int n)
	{
		return driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberDataOrganization)).getText();
	}
	
	public String getNthMemberDataLocation(int n)
	{
		return driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberDataLocation)).getText();
	}
	
	public String getNthMemberType(int n)
	{
		return driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberType)).getText();
	}
	
	public void clickNthMemberEdit(int n)
	{
		driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberEdit)).click();
	}
	
	public void clickNthMemberRemove(int n)
	{
		driver.findElement(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberRemove)).click();
	}
	
	public Boolean isNthMemberImageDisplayed(int n)
	{
		return commonMethods.isElementDisplayed(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberImage), driver);
	}
	
	public Boolean isNthMemberNameDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberName), driver);
	}
	
	public Boolean isNthMemberDataPositionDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberDataPosition), driver);
	}
	
	public Boolean isNthMemberDataOrganizationDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberDataOrganization), driver);
	}
	
	public Boolean isNthMemberDataLocationDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberDataLocation), driver);
	}
	
	public Boolean isNthMemberTypeDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberType), driver);
	}
	public Boolean isNthMemberEditDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberEdit), driver);
	}
	
	public Boolean isNthMemberRemoveDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(memberListItems + "["+n+"]" + memberListItemMemberRemove), driver);
	}
	
	public GroupsViewMembersTab enterSearchText(String textToSearchFor)
	{
		driver.findElement(searchInputLocator).sendKeys(textToSearchFor);
		return this;
	}
	
	public Boolean isOwnersPanelLocatorDisplayed()
	{
		return commonMethods.isElementDisplayed(ownersPanelLocator, driver);
	}
	
	public GroupsViewMembersTab clickShowMoreLink()
	{
		driver.findElement(showMoreLinkLocator).click();
		return this;
	}
	
	public Boolean isShowMoreLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(showMoreLinkLocator, driver);
	}
}
