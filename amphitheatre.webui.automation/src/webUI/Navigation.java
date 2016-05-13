package webUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.admin.MyProfile;
import webUI.admin.Settings;
import webUI.home.Home;
import webUI.home.bookmarks.Bookmarks;
import webUI.home.groups.Groups;
import webUI.home.groups.JoinGroups;
import webUI.home.groups.MyGroups;
import webUI.home.library.Library;
import webUI.home.mylearning.MyLearning;
import webUI.home.mylearning.currentActivities.CurrentActivities;
import webUI.home.mylearning.progress.Progress;
import webUI.home.peopleSearch.Directory;
import webUI.home.peopleSearch.ImFollowing;
import webUI.home.questionsAndAnswers.AllQuestions;
import webUI.home.questionsAndAnswers.MyQuestions;
import webUI.home.questionsAndAnswers.QuestionsICanAnswer;

public class Navigation{

	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By homeLocator = By.id("leftnavHome"); 
	private By myLearningLocator = By.id("leftnavLearning");	
	private By currentActivitiesLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_1'] > div > ul > li > a");
	private By progressLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_1'] > div > ul > li:nth-child(2) > a");
	private By libraryLocator = By.id("leftnavLibrary");	
	private By groupsLocator = By.id("leftnavGroups");
	private By myGroupsLocator = By.cssSelector("#sls_widgets_components_global_NavigationBarItem_3 > .leftnavFlyout > ul > li:nth-child(1) > a");	
	private By joinGroupsLocator = By.cssSelector("#sls_widgets_components_global_NavigationBarItem_3 > .leftnavFlyout > ul > li:nth-child(2) > a");		
	private By askLocator = By.id("leftnavAsk");
	private By allQuestionsLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_4'] > div > ul > li > a");
	private By myQuestionsLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_4'] > div > ul > li:nth-child(2) > a");
	private By questionsICanAnswerLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_4'] > div > ul > li:nth-child(3) > a");	
	private By bookmarksLocator = By.id("leftnavLists");
	private By peopleSearchLocator = By.id("leftnavPeopleSearch");
	private By peopleSearchDirectoryLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_6'] > div > ul > li:nth-child(1) > a");
	private By peopleSearchImFollowingLocator = By.cssSelector("div[id^='sls_widgets_components_global_NavigationBarItem_6'] > div > ul > li:nth-child(2) > a");	
	private By appname = By.id("appname");
	private By searchTermLocator = By.id("searchTerm");
	private By headerSearchLocator = By.id("headerSearch");
	private By avatarLocator = By.cssSelector(".profileAvatar");
	private By loggedInUserName = By.cssSelector(".headerNavTitle");
	private By myProfileLocator = By.cssSelector(".profileNavLinkList > li:nth-child(1)");
	private By settingsLocator = By.cssSelector(".profileNavLinkList > li:nth-child(2)");
	private By logoutLocator = By.cssSelector(".profileNavLinkList > li:nth-child(3)");
	
	/* Footer Element Locators */
	private By footerList = By.cssSelector("ul[class='footerlist'] > li > a");
	private By footerHelp = By.id("footerHelp");
	private By footerTermsUse = By.id("footerTermsUse");
	private By footerPrivacy = By.id("footerPrivacy");	
	private By feedbackLocator = By.id("footerFeedback");	
	private By footerContactUs = By.id("footerOPContactUs");
	
	/* Sametime Locators */ 
	private By sametimeDock = By.cssSelector(".stproxy_dock_openMenu");
	
	public Navigation(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad();
	}

	public void waitForPageToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(homeLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(homeLocator));
		wait.until(ExpectedConditions.presenceOfElementLocated(homeLocator));	
		wait.until(ExpectedConditions.elementToBeClickable(avatarLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(avatarLocator));
		wait.until(ExpectedConditions.presenceOfElementLocated(avatarLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void waitForSametimeDockToBeDisplayed()
	{
		commonMethods.waitForElementToBeVisibleAndClickable(sametimeDock, driver);
	}
	
	public Boolean isSametimeDockDisplayed()
	{
		return commonMethods.isElementDisplayed(sametimeDock, driver);
	}
	
	public Home clickHome()
	{
		driver.findElement(homeLocator).click();
		return new Home(driver);
	}

	public void hoverOverHome()
	{
//		WebElement elems=driver.findElement(homeLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverAvatar= builder.moveToElement(elems);
//		hoverOverAvatar.perform();
		WebElement elems = driver.findElement(homeLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public MyLearning clickMyLearning()
	{
		driver.findElement(myLearningLocator).click();
		return new MyLearning(driver);
	}
	
	public void hoverOverMyLearning()
	{
//		WebElement elems=driver.findElement(myLearningLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverMyLearning= builder.moveToElement(elems);
//		hoverOverMyLearning.perform();
		WebElement elems = driver.findElement(myLearningLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		commonMethods.waitForElementToBeVisibleAndClickable(currentActivitiesLocator, driver);
	}
	
	public CurrentActivities clickCurrentActivities()
	{
		driver.findElement(currentActivitiesLocator).click();
		return new CurrentActivities(driver);
	}
	
	public Progress clickProgress()
	{
		driver.findElement(progressLocator).click();
		return new Progress(driver);
	}
	
	public Library clickLibrary()
	{
		driver.findElement(libraryLocator).click();
		return new Library(driver);
	}
	
	public Groups clickGroups()
	{
		driver.findElement(groupsLocator).click();
		return new Groups(driver);
	}
	
	public MyGroups clickMyGroups()
	{
		driver.findElement(myGroupsLocator).click();
		return new MyGroups(driver);
	}
	
	public JoinGroups clickJoinGroups()
	{
		driver.findElement(joinGroupsLocator).click();
		return new JoinGroups(driver);
	}
	
	public void hoverOverLibrary()
	{
//		WebElement elems=driver.findElement(libraryLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverLibrary= builder.moveToElement(elems);
//		hoverOverLibrary.perform();
		
		WebElement elems = driver.findElement(libraryLocator);
		{
			//boolean result = false;
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}	
		commonMethods.waitForElementToBeVisibleAndClickable(libraryLocator, driver);
	}
	
	public MyProfile clickMyProfile()
	{
		commonMethods.waitForElementToBeVisibleAndClickable(myProfileLocator, driver);
		driver.findElement(myProfileLocator).click();
		return new MyProfile(driver);
	}

	public Settings clickSettings()
	{
		driver.findElement(settingsLocator).click();
		return new Settings(driver);
	}
	
	public void clickLogout()
	{
		commonMethods.waitForElementToBeVisibleAndClickable(myProfileLocator, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(settingsLocator, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(logoutLocator, driver);
		driver.findElement(logoutLocator).click();

		//WebElement logoutLink =driver.findElement(logoutLocator);
		//Actions builder = new Actions(driver); 
		//Actions clickLogout = builder.click(logoutLink);
		//clickLogout.perform();
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("javascript:document.getElementById('logoutlink').click()");

		//return this;
		//commonMethods.waitForElementToBeNotVisible(homeLocator, driver);
		commonMethods.waitForElementToBeNotVisible(myLearningLocator, driver);
		commonMethods.waitForElementToBeNotVisible(libraryLocator, driver);
		commonMethods.waitForElementToBeNotVisible(groupsLocator, driver);
		commonMethods.waitForElementToBeNotVisible(askLocator, driver);
		commonMethods.waitForElementToBeNotVisible(bookmarksLocator, driver);
		commonMethods.waitForElementToBeNotVisible(peopleSearchLocator, driver);
	}
	
	public MyQuestions clickAsk()
	{
		driver.findElement(askLocator).click();
		return new MyQuestions(driver);
	}
	
	public void hoverOverGroups()
	{  
		WebElement elems = driver.findElement(groupsLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		commonMethods.waitForElementToBeVisibleAndClickable(joinGroupsLocator, driver);
//		WebElement elems=driver.findElement(groupsLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverAsk= builder.moveToElement(elems);
//		hoverOverAsk.perform();		
	}
	
	public void hoverOverAsk()
	{
		WebElement elems = driver.findElement(askLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		commonMethods.waitForElementToBeVisibleAndClickable(allQuestionsLocator, driver);
//		WebElement elems=driver.findElement(askLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverAsk= builder.moveToElement(elems);
//		hoverOverAsk.perform();
	}
	
	public AllQuestions clickAllQuestions()
	{
		driver.findElement(allQuestionsLocator).click(); 
		return new AllQuestions(driver);
	}
	
	public MyQuestions clickMyQuestions()
	{
		driver.findElement(myQuestionsLocator).click(); 
		return new MyQuestions(driver);
	}
	
	public QuestionsICanAnswer clickQuestionsICanAnswer()
	{
		driver.findElement(questionsICanAnswerLocator).click(); 
		return new QuestionsICanAnswer(driver);
	}
	
	public void hoverOverBookmarks()
	{  
//		WebElement elems=driver.findElement(bookmarksLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverAsk= builder.moveToElement(elems);
//		hoverOverAsk.perform();
		WebElement elems = driver.findElement(bookmarksLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public Bookmarks clickBookmarks()
	{
		driver.findElement(bookmarksLocator).click();
		return new Bookmarks(driver);
	}
	
	public void hoverOverPeopleSearch()
	{  
//		WebElement elems=driver.findElement(peopleSearchLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverAsk= builder.moveToElement(elems);
//		hoverOverAsk.perform();	
		WebElement elems = driver.findElement(peopleSearchLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		commonMethods.waitForElementToBeVisibleAndClickable(peopleSearchDirectoryLocator, driver);
	}
	
	public Directory clickPeopleSearchDirectory()
	{
		driver.findElement(peopleSearchDirectoryLocator).click(); 
		return new Directory(driver);
	}
	
	public ImFollowing clickPeopleSearchImFollowing()
	{
		driver.findElement(peopleSearchImFollowingLocator).click(); 
		return new ImFollowing(driver);
	}
	
	public void clickHeaderSearch()
	{
		driver.findElement(headerSearchLocator).click();
	}
	
	public void enterSearchTerm(String searchString)
	{
		driver.findElement(searchTermLocator).sendKeys(searchString);
	}
	
	public Navigation clickAvatar()
	{
		driver.findElement(avatarLocator).click();
		return this;
	}
	
	public void hoverOverAvatar()
	{		
//		WebElement elems=driver.findElement(avatarLocator);
//		Actions builder = new Actions(driver); 
//		Actions hoverOverAvatar= builder.moveToElement(elems);
//		hoverOverAvatar.perform();
		WebElement elems = driver.findElement(avatarLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		/* Wait for Logout button to be visible */
		commonMethods.waitForElementToBeVisibleAndClickable(myProfileLocator, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(settingsLocator, driver);
		commonMethods.waitForElementToBeVisibleAndClickable(logoutLocator, driver);
	}
	
	public void hoverOverAvatarAndClickLogout()
	{		
		WebElement elems = driver.findElement(avatarLocator);
		{
			try
			{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, elems);
				commonMethods.waitForElementToBeVisibleAndClickable(logoutLocator, driver);
				//WebElement elems2 = driver.findElement(logoutLocator);
				js.executeScript("document.getElementById('logoutlink').click();");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		/* Wait for Logout button to be visible */
		commonMethods.waitForElementToBeNotVisible(myLearningLocator, driver);
		commonMethods.waitForElementToBeNotVisible(libraryLocator, driver);
		commonMethods.waitForElementToBeNotVisible(groupsLocator, driver);
		commonMethods.waitForElementToBeNotVisible(askLocator, driver);
		commonMethods.waitForElementToBeNotVisible(bookmarksLocator, driver);
		commonMethods.waitForElementToBeNotVisible(peopleSearchLocator, driver);
	}
	
	public Boolean isSearchDisplayed()
	{
		return commonMethods.isElementDisplayed(headerSearchLocator, driver);
	}
	
	public Boolean isAvatarDisplayed()
	{
		return commonMethods.isElementDisplayed(avatarLocator, driver);
	}
	
	public void clickFooterHelpLink()
	{
		driver.findElement(footerHelp).click();
	}
	
	public void clickFooterTermsOfUse()
	{
		driver.findElement(footerTermsUse).click();
	}
	
	public void clickFooterPrivacyLink()
	{
		driver.findElement(footerPrivacy).click();
	}

	public void clickContactUsLink()
	{
		driver.findElement(footerContactUs).click();
	}

	public void clickFooterFeedbackLink()
	{
		driver.findElement(feedbackLocator).click();
	}
	
	public Boolean isFooterHelpLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(footerHelp, driver);
	}
	
	public Boolean isFooterTermsOfUseLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(footerTermsUse, driver);
	}
	
	public Boolean isFooterPrivacyLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(footerPrivacy, driver);
	}

	public Boolean isFooterContactUsLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(footerContactUs, driver);
	}

	public Boolean isFooterFeedbackLinkDisplayed()
	{
		return commonMethods.isElementDisplayed(feedbackLocator, driver);
	}
	
	public String getLoggedInUsername()
	{
		return driver.findElement(loggedInUserName).getText().toLowerCase();
	}
	
	public Boolean isAppNameDisplayed()
	{
		return commonMethods.isElementDisplayed(appname, driver);
	}
	
	public int getCountOfFooterListItems()
	{
		return driver.findElements(footerList).size();
	}
	
}
