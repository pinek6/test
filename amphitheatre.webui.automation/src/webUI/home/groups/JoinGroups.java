package webUI.home.groups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class JoinGroups extends Groups{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);	
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("div[id^='sls_widgets_components_groups_GroupsHome'] > div > h1");
	private By searchInputLocator = By.cssSelector(".searchInput");
	private By groupsItemLocator = By.cssSelector(".groupsItem");
	private By loading = By.cssSelector(".loading");
	private By overviewTabLocator = By.id("GROUP_OVERVIEW");
	private By leaveLinkLocator = By.id("leaveLinkId");
	
	/* Element String Locators */
	private String leftColumn = "div[id^='sls_widgets_components_groups_GroupsHome_'] > div:nth-child(3)> div > div:nth-child(2) > div > div > div";
	private String subjectHeader = " > h3";
	private String subjectsInLeftColumn = leftColumn + " > div > div > ul > li";
	private String subjectsInFacetList = "div[id^='sls_widgets_components_global_FilterTiles'] > span > ul > li";
	private String groupsList = "div[id^='sls_widgets_components_groups_GroupList_']";
	private String groupsListItem = groupsList + " > div";
	private String groupsListItemImage = " > a";
	private String groupsListItemTitle = " > h3 > a";
	private String groupsListItemMemberCount = " > div > span:nth-child(1)";
	private String groupsListItemMemberDescription = " > div > span:nth-child(2)";
	private String groupsListItemGroupUpdated = " > div:nth-child(4)";
	private String groupsListItemJoinLink = " > div:nth-child(7) > a";

	public JoinGroups(WebDriver driver)
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
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(myInvitationsPanelLocator));
		wait.until(ExpectedConditions.elementToBeClickable(myInvitationsPanelLocator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
		
	public JoinGroups enterSearchText(String input)
	{
		driver.findElement(searchInputLocator).sendKeys(input);
		return this;
	}
	
	public JoinGroups clickSearch()
	{
		driver.findElement(searchInputLocator).sendKeys(Keys.RETURN);
		return this;
	}
	
	public Boolean isSearchFieldDisplayed()
	{
		return commonMethods.isElementDisplayed(searchInputLocator, driver);
	}
	
	public JoinGroups searchFor(String input)
	{
		enterSearchText(input).clickSearch();
		return this;
	}
	
	public JoinGroups clickNthSubjectInLeftColumn(int n)
	{
		String nth=Integer.toString(n);
		driver.findElement(By.cssSelector(subjectsInLeftColumn+ ":nth-child("+nth+") > input")).click();
		return this;
	}
	
	public Boolean isNthSubjectCheckedInLeftColumn(int n)
	{
		String nth=Integer.toString(n);
		return driver.findElement(By.cssSelector(subjectsInLeftColumn+ ":nth-child("+nth+") > input")).isSelected()? true : false;
	}
	
	public List<String> getListOfSubjectsCheckedInLeftColumn()
	{
		List<String> selectedSubjects=new ArrayList<String>();
		int countOfSubjects = getCountOfSubjectsInLeftColumn();
		for (int i=1;i<=countOfSubjects;i++)
		{
			if (isNthSubjectCheckedInLeftColumn(i))
				selectedSubjects.add(driver.findElement(By.cssSelector(subjectsInLeftColumn+ ":nth-child("+i+") > label")).getText());
		}	
		Collections.sort(selectedSubjects);
		return selectedSubjects;
	}
	
	public JoinGroups clickNthSubjectInFacetList(int n)
	{
		String nth=Integer.toString(n);
		driver.findElement(By.cssSelector(subjectsInFacetList+ ":nth-child("+nth+") > a")).click();
		return this;
	}
	
	public List<String> getListOfSubjectsInFacetList()
	{
		List<String> selectedSubjects=new ArrayList<String>();
		int countOfSubjects = driver.findElements(By.cssSelector(subjectsInFacetList+ " > span")).size();
		for (int i=1;i<countOfSubjects+1;i++)
		{
				selectedSubjects.add(driver.findElement(By.cssSelector(subjectsInFacetList+ ":nth-child("+i+") > span")).getText());
		}	
		Collections.sort(selectedSubjects);
		return selectedSubjects;
	}
	
	public int getCountOfSubjectsInLeftColumn()
	{
		return driver.findElements(By.cssSelector(subjectsInLeftColumn+ " > input")).size();
	}
	
	public Boolean isNthCategoryExpanded(int n)
	{
		return driver.findElement(By.cssSelector(leftColumn + ":nth-child("+n+")"+ subjectHeader)).getAttribute("class").equals("facetTitle closed")
		&& driver.findElement(By.cssSelector(leftColumn + ":nth-child("+n+") > ul")).getAttribute("class").equals("facetOptionList hide") ? false : true;
	}
	
	public String getNthCategory(int n)
	{
		return driver.findElement(By.cssSelector(leftColumn + ":nth-child("+n+")"+ subjectHeader)).getText();
	}
	
	public JoinGroups expandCollapseCategory(int n)
	{
		driver.findElement(By.cssSelector(leftColumn + ":nth-child("+n+")"+subjectHeader)).click();
		return this;
	}
	
	public int getCountOfCategories()
	{
		return driver.findElements(By.cssSelector(leftColumn + subjectHeader)).size();
	}
	
	public GroupsView clickNthGroupImage(int n)
	{
		driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemImage)).click();
		return new GroupsView(driver);
	}
	
	public Boolean isNthGroupImageDisplayed(int n)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemImage), driver);
	}
	
	public GroupsView clickNthGroupTitle(int n)
	{
		driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemTitle)).click();
		return new GroupsView(driver);
	}
	
	public Boolean isNthGroupTitleDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemTitle), driver);
	}
	
	public String getNthGroupTitle(int n)
	{
		return driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemTitle)).getText();
	}
	
	public Boolean isNthGroupMemberCountDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemMemberCount), driver);
	}
	
	public String getNthGroupMemberCount(int n)
	{
		return driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemMemberCount)).getText();
	}
	
	public void clickNthGroupMemberDescription(int n)
	{
		driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemMemberDescription)).click();
	}

	public Boolean isNthGroupUpdatedDateDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemGroupUpdated), driver);
	}
	
	public String getNthGroupUpdatedDate(int n)
	{
		return driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+") " + groupsListItemGroupUpdated)).getText();
	}	  
	
	public int getCountOfGroupsDisplayed()
	{
		return driver.findElements(groupsItemLocator).size();
	}
	
	public GroupsView clickNthGroupJoin(int n)
	{
		driver.findElement(By.cssSelector(groupsListItem + ":nth-child("+n+")" + groupsListItemJoinLink)).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(overviewTabLocator));
		wait.until(ExpectedConditions.elementToBeClickable(leaveLinkLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return new GroupsView(driver);
	}
	
	public int findGroup(String groupName)
	{
		int position=-1;
		for (int i = 1; i<= getCountOfGroupsDisplayed(); i++)
		{
			if (getNthGroupTitle(i).equals(groupName))
			{
				/* Navigate to the group */
				position=i;
				i=i+1;
			}
		}
		return position;
	}
}
