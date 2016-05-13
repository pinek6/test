package webUI.home.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class GroupsViewDiscussionsTab extends GroupsView
{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	private String discussionList = "div[id*=sls_widgets_components_groups_DiscussionList]";
	private String discussionListItem = "div[id*=sls_widgets_components_groups_DiscussionListItem]";
	private String discussionListItemTitle = "> h3 > a";
	private String discussionListItemStartedBy = "> div > span > a";
	private String discussionListItemLikes = "> div > span:nth-of-type(2)";
	private String discussionListItemReplies = "> div > span:nth-of-type(3)";
	private String discussionListItemLastUpdatedBy = "> div > span:nth-of-type(4) > a";
	private String saveButtonLocator = "[id*=sls_widgets_components_groups_DiscussionEdit_] > div:nth-of-type(2) > div:nth-of-type(6) > button";
	private String cancelButtonLocator = "[id*=sls_widgets_components_groups_DiscussionEdit_] > div:nth-of-type(2) > div:nth-of-type(6) > a";
	
	/* Element Locators */ 
	private By newDicussionLocator = By.id("newDiscussion");
	private By showMoreLinkLocator = By.cssSelector(".linkText2");
	private By showFilterDropdownLocator = By.id("filter");
	private By discussionNameLocator = By.id("discussionTitleId");
	private By discussionSubjectDropdownLocator = By.id("discussionSubjectId");
	private By questionCheckboxLocator = By.id("isQuestionId");
	private By loading = By.cssSelector(".loading");
	private By allDiscussionsMeTypeDropdownLocator = By.id("GROUP_DISCUSSIONS_MY_DISCUSSIONS");
	private By myUnansweredQuestionsTypeDropdownLocator = By.id("GROUP_DISCUSSIONS_MY_UNANSWERED");
	private By myAnsweredQuestionsTypeDropdownLocator = By.id("GROUP_DISCUSSIONS_MY_ANSWERED");
	private By allDiscussionsTypeDropdownLocator = By.id("GROUP_DISCUSSIONS_ALL_DISCUSSIONS");
	private By allUnansweredQuestionsTypeDropdownLocator = By.id("GROUP_DISCUSSIONS_ALL_UNANSWERED");
	private By allAnsweredQuestionsTypeDropdownLocator = By.id("GROUP_DISCUSSIONS_ALL_ANSWERED");

	public GroupsViewDiscussionsTab(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for UI elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(newDicussionLocator));
		wait.until(ExpectedConditions.elementToBeClickable(newDicussionLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(showFilterDropdownLocator));
		wait.until(ExpectedConditions.elementToBeClickable(showFilterDropdownLocator));
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(discussionList), driver);
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isNewDicussionButtonDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(newDicussionLocator, driver);
	}
	
	public Boolean isDicussionListDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(discussionList), driver);
	}
	
	public void clickNewDicussionButton()
	{
		driver.findElement(newDicussionLocator).click();
	}
	
	public int getCountOfDiscussionsDisplayed()
	{
		return driver.findElements(By.cssSelector(discussionListItem)).size();
	}
	
	public String getNthDiscussionTitle(int row)
	{
		return driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemTitle)).getText();
	}
	
	public Discussion clickNthDiscussionTitle(int row)
	{
		driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemTitle)).click();
		return new Discussion(driver);
	}
	
	public Boolean isNthUnansweredQuestionDisplayed(int row)
	{
		int count = row + 1;
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem + ":nth-of-type(" + count +") >div:nth-of-type(1).authorType.unanswered"), driver);
	}
	
	public Boolean isNthAnsweredQuestionDisplayed(int row)
	{
		int count = row + 1;
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem + ":nth-of-type(" + count +") >div:nth-of-type(1).authorType.answered"), driver);
	}
	
	public Boolean isNthDiscussionTitleDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemTitle), driver);
	}
	
	public String getNthDiscussionStartedBy(int row)
	{
		return driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemStartedBy)).getText();
	}
	
	public void clickNthDiscussionStartedBy(int row)
	{
		driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemStartedBy)).click();
	}
	
	public Boolean isNthDiscussionStartedByDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemStartedBy), driver);
	}
	
	public String getNthDiscussionLikes(int row)
	{
		return driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemLikes)).getText();
	}
	
	public Boolean isNthDiscussionLikesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemLikes), driver);
	}
	
	public String getNthDiscussionReplies(int row)
	{
		return driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemReplies)).getText();
	}
	
	public Boolean isNthDiscussionRepliesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemReplies), driver);
	}
	
	public String getNthDiscussionLastUpdatedBy(int row)
	{
		return driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemLastUpdatedBy)).getText();
	}
	
	public void clickNthDiscussionLastUpdatedBy(int row)
	{
		driver.findElement(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemLastUpdatedBy)).click();
	}
	
	public Boolean isNthDiscussionLastUpdatedByDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(discussionListItem+":nth-of-type("+row+")"+discussionListItemLastUpdatedBy), driver);
	}
	
	public GroupsViewDiscussionsTab clickShowMoreLink()
	{
		driver.findElement(showMoreLinkLocator).click();
		return this;
	}
	
	public Boolean isShowMoreLinkDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(showMoreLinkLocator, driver);
	}
	
	public Boolean isDiscussionNameDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(discussionNameLocator, driver);
	}
	
	public GroupsViewDiscussionsTab enterDiscussionName(String name)
	{
		driver.findElement(discussionNameLocator).clear();
		driver.findElement(discussionNameLocator).sendKeys(name);
		return this;
	}
	
	public Boolean isDiscussionSubjectDropdownDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(discussionSubjectDropdownLocator, driver);
	}
	
	public GroupsViewDiscussionsTab selectDiscussionSubjectDropdownByIndex(int index)
	{
		new Select(driver.findElement(discussionSubjectDropdownLocator)).selectByIndex(index);
		return this;
	}
	
	public Boolean isDiscussionTypeDropdownDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(showFilterDropdownLocator, driver);
	}
	
	public GroupsViewDiscussionsTab selectDiscussionTypeDropdownByValue(String value)
	{
		new Select(driver.findElement(showFilterDropdownLocator)).selectByValue(value);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public GroupsViewDiscussionsTab selectAllDiscussionsMeTypeDropdown()
	{
		driver.findElement(allDiscussionsMeTypeDropdownLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public GroupsViewDiscussionsTab selectMyUnansweredQuestionsTypeDropdown()
	{
		driver.findElement(myUnansweredQuestionsTypeDropdownLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public GroupsViewDiscussionsTab selectMyAnsweredQuestionsTypeDropdown()
	{
		driver.findElement(myAnsweredQuestionsTypeDropdownLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public GroupsViewDiscussionsTab selectAllDiscussionsTypeDropdown()
	{
		driver.findElement(allDiscussionsTypeDropdownLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public GroupsViewDiscussionsTab selectAllUnansweredQuestionsTypeDropdown()
	{
		driver.findElement(allUnansweredQuestionsTypeDropdownLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public GroupsViewDiscussionsTab selectAllAnsweredQuestionsTypeDropdown()
	{
		driver.findElement(allAnsweredQuestionsTypeDropdownLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public Boolean isQuestionCheckboxDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(questionCheckboxLocator, driver);
	}
	
	public GroupsViewDiscussionsTab selectQuestionCheckbox()
	{
		driver.findElement(questionCheckboxLocator).click();
		return this;
	}
	
	public GroupsViewDiscussionsTab clickSave()
	{
		driver.findElement(By.cssSelector(saveButtonLocator)).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for UI elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(newDicussionLocator));
		wait.until(ExpectedConditions.elementToBeClickable(newDicussionLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(showFilterDropdownLocator));
		wait.until(ExpectedConditions.elementToBeClickable(showFilterDropdownLocator));
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(discussionList), driver);
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return this;
	}
	
	public GroupsViewDiscussionsTab clickCancel()
	{
		driver.findElement(By.cssSelector(cancelButtonLocator)).click();
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for UI elements to appear */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(newDicussionLocator));
		wait.until(ExpectedConditions.elementToBeClickable(newDicussionLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(showFilterDropdownLocator));
		wait.until(ExpectedConditions.elementToBeClickable(showFilterDropdownLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return this;
	}
	
	public List<String> getListOfDiscussionTitles()
	{
		List<String> l = new ArrayList<String>();

        List<WebElement> allElements = driver.findElements(By.cssSelector(discussionListItem + discussionListItemTitle)); 

        for (WebElement element: allElements) {
        	l.add(element.getText());
        }	   
        
		return l;
	}
}
