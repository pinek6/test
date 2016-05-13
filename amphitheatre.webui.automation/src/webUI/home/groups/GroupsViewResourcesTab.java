package webUI.home.groups;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class GroupsViewResourcesTab extends GroupsView
{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	private String libraryRowItems = "//div[contains(@id,'sls_widgets_components_groups_LibraryTab_')]/div[2]/div/div/div[3]/div";
	private String libraryRowItemsTitle = "/div/span[2]/a";
	private String libraryRowItemsSource = "/div/span[3]";
	private String libraryRowItemsAddedBy = "/div/span[4]";
	private String libraryRowItemsDateAdded = "/div/span[5]";
	private String libraryRowItemsDateTwistie = "/div/span[6]/a";
	private String libraryRowItemsContentDescription = "/div[2]/div";
	private String libraryRowItemsFlagAsFeatured = "/div[2]/div[2]/div/span[1]/a";
	
	/* Element Locators */ 
	private By headerLocator = By.cssSelector("div.contentBody > h1");
	private By searchInputLocator = By.cssSelector(".searchInput");
	private By addItemButtonLocator = By.id("btnAddLibraryItem");
	private By featuredPanelLocator = By.cssSelector(".columnsRightBody");
	private By showMoreLinkLocator = By.cssSelector(".linkText2");
	
	public GroupsViewResourcesTab(WebDriver driver)
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
		
		/* Wait for page elements to be appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchInputLocator));
		wait.until(ExpectedConditions.elementToBeClickable(searchInputLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addItemButtonLocator));
		wait.until(ExpectedConditions.elementToBeClickable(addItemButtonLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	
	public Boolean isNthRowExpanded(int row)
	{
		return driver.findElement(By.xpath(libraryRowItems+"["+row+"]/div[2]")).getAttribute("class").contains("groupLibraryItemDetail hide") ? false : true ;
	}
	
	public AddItem clickAddItemButton()
	{
		driver.findElement(addItemButtonLocator).click();
		return new AddItem(driver);
	}
	
	public GroupsViewResourcesTab enterSearchText(String textToSearchFor)
	{
		driver.findElement(searchInputLocator).sendKeys(textToSearchFor);
		return this;
	}
	
	public GroupsViewResourcesTab clickSearch()
	{
		driver.findElement(searchInputLocator).sendKeys(Keys.ENTER);
		return this;
	}
	
	public String getNthResourceTitle(int row)
	{
		return driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsTitle)).getText();
	}
	
	public String getNthResourceSource(int row)
	{
		return driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsSource)).getText();
	}
	
	public String getNthResourceAddedBy(int row)
	{
		return driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsAddedBy)).getText();
	}
	
	public String getNthResourceDateAdded(int row)
	{
		return driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDateAdded)).getText();
	}
	
	public String getNthResourceContentDescription(int row)
	{
		return driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsContentDescription)).getText();
	}
	
	public int getCountOfResourcesDisplayed()
	{
		return driver.findElements(By.xpath(libraryRowItems)).size();
	}
	
	public GroupsViewResourcesTab clickNthTwistie(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDateTwistie)).click();
		return this;
	}	

	public void clickNthCourseFlagAsFeatured(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsFlagAsFeatured)).click();
	}

	public Boolean isSearchInputDisplayed()
	{
		return commonMethods.isElementDisplayed(searchInputLocator, driver);

	}

	public Boolean isNthCourseFlagAsFeaturedDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsFlagAsFeatured), driver);
	}
	
	public Boolean isFeaturedPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(featuredPanelLocator, driver);
	}

	public Boolean isAddItemButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(addItemButtonLocator, driver);
	}

	public Boolean isResourcesResultsDisplayed()
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems), driver);
	}
	
	public Boolean isNthResourceTitleDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsTitle), driver);
	}
	
	public Boolean isNthResourceSourceDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsSource), driver);
	}
	
	public Boolean isNthResourceAddedByDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsAddedBy), driver);
	}
	
	public Boolean isNthResourceDateAddedDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDateAdded), driver);
	}
	
	public Boolean isNthResourceContentDescriptionDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsContentDescription), driver);
	}
	
	public GroupsViewResourcesTab clickShowMoreLink()
	{
		driver.findElement(showMoreLinkLocator).click();
		return this;
	}
	
	public Boolean isShowMoreLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(showMoreLinkLocator, driver);
	}	
	
	public Boolean isResourceInResultsDisplayed(String resourceTitle)
	{
		int countOfResults = getCountOfResourcesDisplayed();
		Boolean found = false;
		
		for (int i=1;i<=countOfResults;i++)
		{
			if (getNthResourceTitle(i).equals(resourceTitle))
				found=true;
		}
		return found;
	}

	public void waitForResultsToRefresh() 
	{
		commonMethods.waitForElementToBeRefreshed(By.xpath(libraryRowItems), driver);
	}
}
