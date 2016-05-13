package webUI.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.home.groups.Groups;
import webUI.home.library.Library;

public class Home {

	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	private String URL = new ConfigProperties().getConfigProperties().getProperty("URL");
	private String defaultAnnouncementImageLocation = URL + "webUI/sls/themes/images/default_group.jpg";

	/* Element Locators */
	private By instantMessagingDisabled = By.cssSelector("div[id^='sls_sametime_PopupCheckerBasicDialog_'");
	private By closeInstantMessagingDisabled = By.id("popupCheckerBasicClose");
	
	private By column1 = By.cssSelector(".homeColumn1");
	
	private By libraryPanelLocator = By.cssSelector("div[class='homeColumn1'] > div[id^='sls_widgets_components_home_Home']:nth-of-type(1)"); 
	private By libraryTitleLocator = By.cssSelector("div[class='homeColumn1'] > div[id^='sls_widgets_components_home_Home']:nth-of-type(1) > h2"); 
	private By searchLibraryButtonLocator = By.cssSelector("div[class='homeColumn1'] > div[id^='sls_widgets_components_home_Home']:nth-of-type(1) > button");
	
	private By myQuestionsPanelLocator = By.cssSelector("div[id^='sls_widgets_components_home_HomeGoItem']:nth-of-type(2)");
	private By myQuestionsTitleLocator = By.cssSelector("div[id^='sls_widgets_components_home_HomeGoItem']:nth-of-type(2) > h2");	
	private By myQuestionsViewButtonLocator = By.cssSelector("div[id^='sls_widgets_components_home_HomeGoItem']:nth-of-type(2) > button");
	
	private By questionsICanAnswerPanelLocator = By.cssSelector("div[id^='sls_widgets_components_home_HomeGoItem']:nth-of-type(4)");
	private By questionsICanAnswerTitleLocator = By.cssSelector("div[id^='sls_widgets_components_home_HomeGoItem']:nth-of-type(4) > h2");	
	private By questionsICanAnswerViewButtonLocator = By.cssSelector("div[id^='sls_widgets_components_home_HomeGoItem']:nth-of-type(4) > button");
	
	private By groupsPanelLocator =	By.cssSelector("div[id^='sls_widgets_components_home_HomeGroups_']");
	private By groupsTitleLocator =	By.cssSelector("div[id^='sls_widgets_components_home_HomeGroups_'] > h2");
	private By visitButtonLocator =	By.cssSelector("div[id^='sls_widgets_components_home_HomeGroups_'] > button");
	
	private By announcementsPanelLocator = By.id("homeAnnouncement");
	private By globalCommunityPanelLocator = By.id("homeSpecial");
	
	private By announcementsPanelDescription = By.cssSelector("div[id='homeAnnouncement'] > div > div");
	private By announcementsPanelImage = By.cssSelector("div[id='homeAnnouncement'] > div > img");
	private By announcementsPanelNavLeft = By.cssSelector("div[id='homeAnnouncement'] > div:nth-child(3) > div:nth-child(1) > a:nth-child(1)");
	private By announcementsPanelPaging = By.cssSelector("div[id='homeAnnouncement'] > div:nth-child(3) > div:nth-child(2)");
	private By announcementsPanelNavRight = By.cssSelector("div[id='homeAnnouncement'] > div:nth-child(3) > div:nth-child(3) > a:nth-child(1)");
	
	private By globalCommunityPanelDescription = By.cssSelector("div[id='homeSpecial'] > div > div");
	private By globalCommunityPanelImage = By.cssSelector("div[id='homeSpecial'] > div > img");
	private By globalCommunityPanelNavLeft = By.cssSelector("div[id='homeSpecial'] > div:nth-child(3) > div:nth-child(1) > a");
	private By globalCommunityPanelPaging = By.cssSelector("div[id='homeSpecial'] > div:nth-child(3) > div:nth-child(2)");
	private By globalCommunityPanelNavRight = By.cssSelector("div[id='homeSpecial'] > div:nth-child(3) > div:nth-child(3) > a");
	
	
	private By myLearningPanelLocator = By.cssSelector(".homelearning.homeColumnContent");
	private By myLearningPanelNotEnrolled = By.cssSelector(".homelearning.homeColumnContent > div > div > div");
	
	private String myLearningCoursesList = ".homelearning.homeColumnContent > div > div"; 
	private String myLearningCoursesListTitle = " > h3 > a";
	private String myLearningCoursesListPercentComplete = " > div";
	private String myLearningCoursesListButton = " > button";
	
	public Home(WebDriver driver)
	{	
		this.driver=driver;
		waitForPageToLoad();
	}
	
	public String getGlobalCommunityPagingText()
	{
		return driver.findElement(globalCommunityPanelPaging).getText();
	}	

	public Boolean isDefaultGlobalCommunityImageDisplayed()
	{
		if (driver.findElement(globalCommunityPanelImage).getAttribute("src").equals(defaultAnnouncementImageLocation))
			return true;
		else
			return false;			
	}
	
	public String getGlobalCommunityDescription()
	{
		return driver.findElement(globalCommunityPanelDescription).getText();
	}
	

	public Home clickNavLeftGlobalCommunity()
	{
		driver.findElement(globalCommunityPanelNavLeft).click();
		commonMethods.waitForElementToBeRefreshed(globalCommunityPanelPaging, driver);
		return new Home(driver);
	}
	
	public Home clickNavRightGlobalCommunity()
	{
		driver.findElement(globalCommunityPanelNavRight).click();
		commonMethods.waitForElementToBeRefreshed(globalCommunityPanelPaging, driver);
		return new Home(driver);
	}
	
	/**
	 * Loops through the global community searching for the description text
	 * @param description Enter text to be found
	 * @return Returns true if found, false if not
	 */
	public Boolean isGlobalCommunityFound(String description)
	{
		boolean found = false;
		String paging = getGlobalCommunityPagingText();
		
		if (paging.equals(""))
		{
			if (getGlobalCommunityDescription().equals(description))
			{
				found=true;
			}
			else
			{
				found=false;
			}
		}
		else
		{
			int total;
			total = Integer.parseInt(paging.substring(paging.lastIndexOf(" ")+1,paging.length()));
			
			for (int i = 1; i<=total; i++)
			{
				if (getGlobalCommunityDescription().equals(description))
				{
					found=true;
					i=total+1;
				}
				else
				{
					if (i!=total)
					{
						clickNavRightGlobalCommunity();
					}
				}
			}	
		}
		return found;
	}
	
	public String getAnnouncementsPagingText()
	{
		return driver.findElement(announcementsPanelPaging).getText();
	}
	
	public Boolean isDefaultAnnouncementImageDisplayed()
	{
		if (driver.findElement(announcementsPanelImage).getAttribute("src").equals(defaultAnnouncementImageLocation))
			return true;
		else
			return false;			
	}
	
	public String getAnnouncementsDescription()
	{
		return driver.findElement(announcementsPanelDescription).getText();
	}
	
	public Home clickNavLeftAnnouncement()
	{
		driver.findElement(announcementsPanelNavLeft).click();
		commonMethods.waitForElementToBeRefreshed(announcementsPanelPaging, driver);
		return new Home(driver);
	}
	
	public Home clickNavRightAnnouncement()
	{
		driver.findElement(announcementsPanelNavRight).click();
		commonMethods.waitForElementToBeRefreshed(announcementsPanelPaging, driver);
		return new Home(driver);
	}

	/**
	 * Loops through the announcements searching for the description text
	 * @param description Enter text to be found
	 * @return Returns true if found, false if not
	 */
	public Boolean isAnnouncementFound(String description)
	{
		boolean found = false;
		String paging = getAnnouncementsPagingText();
		
		if (paging.equals(""))
		{
			if (getAnnouncementsDescription().equals(description))
			{
				found=true;
			}
			else
			{
				found=false;
			}
		}
		else
		{		
			int total;
			total = Integer.parseInt(paging.substring(paging.lastIndexOf(" ")+1,paging.length()));
			
			for (int i = 1; i<=total; i++)
			{
				if (getAnnouncementsDescription().equals(description))
				{
					found=true;
					i=total+1;
				}
				else
				{
					if (i!=total)
					{
						clickNavRightAnnouncement();
					}
				}
			}	
		}
		return found;
	}

	public Boolean isLibraryPanelTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(libraryTitleLocator, driver);
	}
	
	public Boolean isLibraryPanelDisplayed()
	{		
		return commonMethods.isElementDisplayed(libraryPanelLocator, driver);		
	}
	
	public Boolean isSearchLibraryButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(searchLibraryButtonLocator, driver);
	}
	
	public Library clickSearchLibrary()
	{
		driver.findElement(searchLibraryButtonLocator).click();
		return new Library(driver);
	}
	
	public Boolean isMyQuestionsPanelTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(myQuestionsTitleLocator, driver);	
	}
	
	public Boolean isMyQuestionsPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(myQuestionsPanelLocator, driver);	
	}	
	
	public Boolean isQuestionsICanAnswerPanelTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(questionsICanAnswerTitleLocator, driver);	
	}
	
	public Boolean isQuestionsICanAnswerPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(questionsICanAnswerPanelLocator, driver);	
	}
	
	public Boolean isQuestionsICanAnswerViewButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(questionsICanAnswerViewButtonLocator, driver);
	}
	
	public Boolean isMyQuestionsViewButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(myQuestionsViewButtonLocator, driver);
	}
	
	public Boolean isGroupsPanelTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(groupsTitleLocator, driver);	
	}
	
	public Boolean isGroupsPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(groupsPanelLocator, driver);	
	}
	
	public Boolean isVisitButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(visitButtonLocator, driver);
	}
	
	public Groups clickVisit()
	{
		driver.findElement(visitButtonLocator).click();
		return new Groups(driver);
	}
	
	public Boolean isAnnouncementsPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(announcementsPanelLocator, driver);
	}
	
	public Boolean isGlobalCommunityPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(globalCommunityPanelLocator, driver);
	}
	
	public Boolean isMyLearningPanelDisplayed()
	{
		return commonMethods.isElementDisplayed(myLearningPanelLocator, driver);
	}
	
	public int getCountOfMyLearningCourses()
	{
		return driver.findElements(By.cssSelector(myLearningCoursesList+myLearningCoursesListTitle)).size();
	}
	
	public String getNthMyLearningTitle(int n)
	{
		return driver.findElement(By.cssSelector(myLearningCoursesList+":nth-child("+n+")"+myLearningCoursesListTitle)).getText();
	}
	
	public void clickNthMyLearningTitle(int n)
	{
		driver.findElement(By.cssSelector(myLearningCoursesList+":nth-child("+n+")"+myLearningCoursesListTitle)).click();
	}
	
	public String getNthMyLearningPercentComplete(int n)
	{
		return driver.findElement(By.cssSelector(myLearningCoursesList+":nth-child("+n+")"+myLearningCoursesListPercentComplete)).getText();
	}
	
	public void clickNthMyLearningButton(int n)
	{
		driver.findElement(By.cssSelector(myLearningCoursesList+":nth-child("+n+")"+myLearningCoursesListButton)).click();
	}
	
	/**
	 * 
	 * @param n
	 * @return Returns either start or continue
	 */
	public String getNthMyLearningButtonType(int n)
	{
		return driver.findElement(By.cssSelector(myLearningCoursesList+":nth-child("+n+")"+myLearningCoursesListButton)).getAttribute("class").equals("btnAlternate btnSmall") 
		? "start" : "continue";
	}
	
	public String getMyLearningNotEnrolledText()
	{
		return driver.findElement(myLearningPanelNotEnrolled).getText();
	}
	
	public Boolean isMyLearningNotEnrolledTextDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(myLearningPanelNotEnrolled, driver);
	}
	
	public Boolean isInstantMessagingDisabledDisplayed()
	{
		return commonMethods.isElementPresent(instantMessagingDisabled, driver);
	}
	
	public Home clickCloseInstantMessagingDisabled()
	{
		driver.findElement(closeInstantMessagingDisabled).click();
		return this;
	}
	
	public Boolean isColumn1Displayed()
	{
		return commonMethods.isElementDisplayed(column1, driver);
	}
	
	public void waitForPageToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for elements to be visible  */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(myLearningPanelLocator));
		wait.until(ExpectedConditions.presenceOfElementLocated(myLearningPanelLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
