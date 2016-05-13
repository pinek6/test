package webUI.home.bookmarks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class Bookmarks {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("[class='contentBodyWrapper'] > [class ='contentBody'] > div > h1");	
	private By searchInputLocator = By.cssSelector(".searchInput");
	private By bookmarksCounter = By.cssSelector("[class = 'contentBodyWrapper'] > div > div > [class = 'bodyText1Bold']");
	private By emptyState = By.cssSelector("[class = 'contentBodyWrapper']  > div > [class^='bookmarkEmptyMessage']");
	
	/*String locators*/
	private String bookmark = "[class = 'bookmarkResultRow']";
	private String bookmarksIcon = "[class^='colBookmarkTitle'] > span";
	private String bookmarksTitle = "[class^='colBookmarkTitle'] > a";
	private String bookmarksType = "[class^='colBookmarkType']";
	private String bookmarksDate = "[class^='colBookmarkDate']";
	private String bookmarksNotes = "[class^='colBookmarkNotes'] > textArea";
	private String bookmarksNotesCharCount = "[class^='colBookmarkNotes'] > div > p[data-dojo-attach-point='charCount']";
	private String bookmarksNotesSave = "[class^='colBookmarkNotes'] > div > p > a[data-dojo-attach-point='saveClick']";
	private String bookmarksNotesCancel = "[class^='colBookmarkNotes'] > div > p > a[data-dojo-attach-point='cancelClick']";
	private String moreDetailsButton = "[class^='colBrowseExpand'] > a";
	private String removeLink = "[class^='bookmarkRemove']";
	
	private String deletePopup = "[class^='slsPopup']";
	//private String deleteConfirmation = " > div > div > input";
	private String deleteConfirmation = "[id='okButtonId']";
	
	private String deleteCancel = "[id='closeButtonId']";
	private String deleteClose = "[class='btnClosePopup']";
	
	private String summaryClass = "typeIcon typeIconSummary";
	private String videoClass = "typeIcon typeIconVideo";
	private String calculatorClass = "typeIcon typeIconCalculator";
	private String simulatorClass = "typeIcon typeIconSimulator";

	public Bookmarks(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}	
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/*  Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLocator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bookmarksCounter));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}

	public Boolean isSearchInputDisplayed()
	{
		return commonMethods.isElementDisplayed(searchInputLocator, driver);
	}
	
	public Bookmarks enterSearchText(String text)
	{
		driver.findElement(searchInputLocator).sendKeys(text);
		return this;
	}
	
	public int getCountOfBookmarks()
	{
		return  driver.findElements(By.cssSelector(bookmark)).size();
	}
	
	public int getBookmarksCounter()
	{
		String bookmakrsCounter = driver.findElement(bookmarksCounter).getText();
		bookmakrsCounter = bookmakrsCounter.substring(0, bookmakrsCounter.indexOf(" "));
		return Integer.parseInt(bookmakrsCounter);
	}
	
	public String getNthBookmarkTitle(int nth)
	{
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksTitle)).getText();
	}
	
	public String getNthBookmarkType(int nth)
	{
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksType)).getText().toLowerCase();
	}
	
	public String getNthBookmarkDate(int nth)
	{
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksDate)).getText();
	}
	
	public String getNthBookmarkNotes(int nth)
	{
		String bookmarkNotes = driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotes)).getAttribute("value");
		if (bookmarkNotes.length() > 150)
		{		
			bookmarkNotes = bookmarkNotes.substring(0,150);
		}	
		return bookmarkNotes;
	}
	
	public Bookmarks clickNthBookmarkNotes(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotes)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesSave), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCancel), driver);
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCharCount), driver);		
		return this;
	}
	
	public Boolean isCharCounterDisplayedForNthBookmarkNotes(int nth)
	{
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCharCount)).isDisplayed();
	}
	
	public Boolean isSaveDisplayedForNthBookmarkNotes(int nth)
	{
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesSave)).isDisplayed();
	}
	
	public Bookmarks clickSaveForNthBookmarkNotes(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesSave)).click();
		
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesSave), driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCancel), driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCharCount), driver);
		return this;
	}
	
	public Bookmarks clickCancelForNthBookmarkNotes(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCancel)).click();
		
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesSave), driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCancel), driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCharCount), driver);
		return this;
	}
	
	public Boolean isCancelDisplayedForNthBookmarkNotes(int nth)
	{
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCancel)).isDisplayed();
	}
	
	public Bookmarks clickNthBookmarkTitle(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksTitle)).click();
		return this;
	}
	
	public Bookmarks clickMoreDetailsButtonOnNthBookmark(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+moreDetailsButton)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1)"), driver);
		return this;
	}
	
	public Bookmarks clickRemoveButtonOnNthBookmark(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(2) > "+removeLink)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(deletePopup/*+deleteConfirmation*/),driver);
		return this;
	}
	
	public Bookmarks clickDeleteButtonInDeletionPopup()
	{
		driver.findElement(By.cssSelector(deleteConfirmation)).click();
		commonMethods.waitForElementToBeRefreshed(bookmarksCounter, driver);
		return this;
	}
	
	public Bookmarks clickCancelButtonInDeletionPopup()
	{
		driver.findElement(By.cssSelector(deleteCancel)).click();
		return this;
	}
	
	public Bookmarks clickCloseButtonInDeletionPopup()
	{
		driver.findElement(By.cssSelector(deleteClose)).click();
		return this;
	}
	
	public String getNthBookmarkIcon(int nth)
	{
		String type = driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksIcon)).getAttribute("class");
		
		if (type.equals(summaryClass))
			return "summary";		
		if (type.equals(videoClass))
			return "video";		
		if (type.equals(calculatorClass))
			return "calculator";		
		if (type.equals(simulatorClass))
			return "simulator";
		else
			return "unknown type";
	}
	
	public Boolean isBookmarksEmptyStateMsgDisplayed()
	{
		return driver.findElement(emptyState).isDisplayed();
	}
	public String getBookmarksEmptyStateMsgDisplayed()
	{
		String msg =  driver.findElement(emptyState).getText();
		msg = msg.replace("\n","");
		return msg;
	}
	public Bookmarks clearNotesBoxOnNthBookmark(int nth)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotes)).clear();
		driver.findElement(headerLocator).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotes), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotesCharCount), driver);
		return this;
	}
	
	public String getNthBookmarkNotesPlaceholder(int nth)
	{
		
		return driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotes)).getAttribute("placeholder");
	}
	
	public Bookmarks enterNotesIntoNthBookmark(int nth, String newNotes)
	{
		driver.findElement(By.cssSelector(bookmark+":nth-child("+nth+") "
				+ "> div:nth-child(1) > span"+bookmarksNotes)).sendKeys(newNotes);
		return this;
	}
}
