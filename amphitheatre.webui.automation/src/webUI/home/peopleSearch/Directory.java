package webUI.home.peopleSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;
import webUI.LocaleStrings;

public class Directory extends PeopleSearch{
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	private String noPeopleErrorMessage = new LocaleStrings().getString("NO_PEOPLE_ERROR_MESSAGE");
	
	/* Element Locators */
	private By searchInputLocator = By.cssSelector(".searchInput");
	private By clearSearchLinkLocator = By.cssSelector(".linkText5");
	private By pagingCountLocator = By.cssSelector("div[id^='sls_widgets_widgets_views_search_PeopleDirectory']>div>div:nth-child(2)>span");
//	private By expertsOnlyCheckboxLocator = By.cssSelector("input[id='experts only']");
	private By expertsOnlyCheckboxLocator = By.cssSelector("label[for='experts only']");
	private By searchButton = By.cssSelector(".searchColLeft>button");
	private By expandProfileButton = By.cssSelector("#twistie_0_undefined");
	private By followLink = By.cssSelector(".linkText6.follow.peopleSearchCharSeparate.peopleSearchFollow");
	private By stopFollowingLink = By.cssSelector(".linkText6.peopleSearchCharSeparate.peopleSearchFollow");
	private By expertsListLocator= By.cssSelector(".peopleSearchExpert");
	private By counterLocator = By.cssSelector(".bodyText1Bold");
	private By loading = By.cssSelector(".loadstr");
	//private By tictacTabLocator = By.cssSelector(".selectedList.selectedFacetList");
	private By peopleListLocator = By.cssSelector(".profileAvatarFrame");
	
	/* String Element Locators */
	//private String subSpecialtyTitle = "//div[contains(@id,'sls_widgets_widgets_views_search_PeopleDirectory')]/div/div/div[2]";
	private String subSpecialtyTitle = "div[id*=sls_widgets_widgets_views_search_PeopleDirectory] > div > div > div:nth-of-type(2)";
	private String expandLocator = "div[id^='sls_widgets_widgets_views_search_PeopleDirectory_'] > div:nth-of-type(2) > div:nth-of-type(2) > div:nth-of-type(1)";
	private String tictacTabLocator = "div[id^='sls_widgets_components_global_FilterTiles_'] > span > ul";
	private String noPeopleErrorMessageLocator = "div[id^='sls_widgets_widgets_views_search_PeopleDirectory_'] > div:nth-of-type(1) > div:nth-of-type(2)  > span";
	
	public Directory(WebDriver driver)
	{
		super(driver);
		waitForPageToLoad(driver);
		this.driver=driver;
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchInputLocator));
		wait.until(ExpectedConditions.elementToBeClickable(searchInputLocator));
		wait.until(ExpectedConditions.presenceOfElementLocated(expertsOnlyCheckboxLocator));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isSearchInputDisplayed()
	{
		return commonMethods.isElementDisplayed(searchInputLocator, driver);
	}
	
	public Directory enterSearchText(String text)
	{
		driver.findElement(searchInputLocator).sendKeys(text);
		return this;
	}
	
	public Boolean isClearSearchDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(clearSearchLinkLocator, driver);
	}
	
	public Directory clickClearSearch()
	{
		driver.findElement(clearSearchLinkLocator).click();
		return this;
	}
	
	public Boolean isExpertsOnlyCheckboxDisplayed()
	{
		return commonMethods.isElementDisplayed(expertsOnlyCheckboxLocator, driver);
	}
	
	public Directory clickExpertsOnlyCheckbox()
	{
		driver.findElement(expertsOnlyCheckboxLocator).click();
		//commonMethods.waitForElementToBeRefreshed(pagingCountLocator, driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(expandLocator), driver);
		return this;
	}
	
	public Directory clickSearchButton()
	{
		driver.findElement(searchButton).click();
		commonMethods.waitForElementToBeRefreshed(pagingCountLocator, driver);
		return this;
	}
	
	public Directory clickExpandProfile()
	{
		driver.findElement(expandProfileButton).click();
		commonMethods.waitForElementToBeVisibleAndClickable(followLink, driver);
		return this;
	}
	
	public Directory clickFollowLink()
	{
		driver.findElement(followLink).click();
		commonMethods.waitForElementToBeVisibleAndClickable(stopFollowingLink, driver);
		return this;
	}
	
	public Directory clickStopFollowLink()
	{
		driver.findElement(stopFollowingLink).click();
		commonMethods.waitForElementToBeVisibleAndClickable(followLink, driver);
		return this;
	}
	
	public int getCounter()
	{
		commonMethods.waitForElementToBeVisibleAndClickable(counterLocator, driver);
		String c = driver.findElement(counterLocator).getText();
		int counter = Integer.parseInt(c.substring(0, c.indexOf(" ")));
		return counter;
	}
	
	public int getTotalExperts()
	{	
		int x = (driver.findElements(expertsListLocator)).size();
		return x;
	}
	
	public Directory scrollDownPeopleList()
	{
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		int totalMembers = getCounter();
		int scrollCounter = 0;
		int x = getCounter()/12;
		
		if (x % 12 == 0)
			scrollCounter = (totalMembers/12)-1;
		else
			scrollCounter = totalMembers/12;
		
		for (int i = 0 ; i < scrollCounter ; i++){
			jsx.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			commonMethods.waitForElementToBeNotVisible(loading, driver);
		}
		
		return this;
	}
	
	public String getNoPeopleErrorMessage()
	{
		return driver.findElement(By.cssSelector(noPeopleErrorMessageLocator)).getText();
	}
	
	public void scrollUp()
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(250, 0)");
	}
	
	public Boolean isTicTacTabDisplayed()
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tictacTabLocator), driver);
	}
	
	public List<String> getTicTacListDisplayed()
	{
		List<String> l = new ArrayList<String>();
		List<WebElement> allElements = driver.findElements(By.cssSelector(tictacTabLocator+">li"));
		
		for (WebElement retval: allElements){		
			l.add((retval.getText()).substring(10));
		}
		
		return l;
	}
	
	public Boolean isNthTicTacLabelDisplayed(int nth)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tictacTabLocator + "> li:nth-of-type("+ nth +")"),driver);
	}
	
	public String getNthTicTacLabel(int nth)
	{
		return driver.findElement(By.cssSelector(tictacTabLocator + "> li:nth-of-type("+ nth +")")).getText();
	}
	
	public Directory clickNthTicTacCloseButton(int nth)
	{
		driver.findElement(By.cssSelector(tictacTabLocator + "> li:nth-of-type("+ nth +") > a")).click();
		commonMethods.waitForElementToBeRefreshed(counterLocator, driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(expandLocator),driver);
		return this;
	}
	
	public Directory clickNthInterestInLeftColumns(int nth)
	{
		driver.findElement(By.cssSelector(subSpecialtyTitle+ "> ul > li:nth-of-type("+ nth +") > input")).click();
		commonMethods.waitForElementToBeRefreshed(counterLocator, driver);
		if (!getNoPeopleErrorMessage().equals(noPeopleErrorMessage)) commonMethods.waitForElementToBeRefreshed(By.cssSelector(expandLocator),driver);
		return this;
	}
	
	public String getNthInterestInLeftColumns(int nth)
	{
		return driver.findElement(By.cssSelector(subSpecialtyTitle+ "> ul > li:nth-of-type("+ nth +") > label")).getText();
	}
	
	public int getTotalInterestsInLeftColumns()
	{
		int x = driver.findElements(By.cssSelector(subSpecialtyTitle+ "> ul > li")).size();
		return x;
	}
	
	public String getNthExpertiseAreaLabel(int nth)
	{
		return driver.findElement(By.cssSelector(expandLocator+ "div > div:nth-of-type("+nth+") > span")).getText();
	}
		
	public Boolean isInterestDisplayedUnderExpertiseArea(List<String> interests)
	{
		List<String> l = new ArrayList<String>();
		String expertise_area = "";
		int x = getCounter()/12;
		
		//Handles all members displayed on all divs except the last one
		for (int i = 1 ; i < x+1 ; i++){
			for (int j = 1 ; j < 13 ; j++){
				
				int temp = j*2;
				expertise_area = driver.findElement(By.cssSelector(expandLocator + "> div:nth-of-type("+i+") > div:nth-of-type("+temp+") > span")).getText();
				String str = expertise_area.substring(16);
				
				for (String retval: str.trim().split("\\s*,\\s*")){
					l.add(retval);
				}

				if (Collections.disjoint(l,interests)) return false;
				l.clear();				
			}
		}
		
		//Handles the members displayed on the last div
		int last_div = x+1;
		int a = getCounter() % 12;
		if (a != 0){
			for (int z = 1 ; z < a+1 ; z++){
						
				int last_temp = z*2;
				expertise_area = driver.findElement(By.cssSelector(expandLocator + "> div:nth-of-type("+last_div+") > div:nth-of-type("+last_temp+") > span")).getText();
				String str2 = expertise_area.substring(16);

				for (String retval: str2.trim().split("\\s*,\\s*")){
					l.add(retval);
				}

				if (Collections.disjoint(l,interests)) return false;
				l.clear();
			}
		} 

		return true;
	}
	
	public Directory clickExpand(int nth)
	{
		driver.findElement(By.id("twistie_"+nth+"_undefined")).click();
		commonMethods.waitForElementToBeVisibleAndClickable(followLink, driver);
		return this;
	}
	
	
	public Directory clickExpandAll()
	{
		int x = getCounter()/12;
		
		//Handles all members displayed on all divs except the last one
		for (int i = 1 ; i < x+1 ; i++){
			for (int j = 1 ; j < 13 ; j++){
				
				int count = j-1;
				WebElement e = driver.findElement(By.cssSelector(expandLocator + "> div:nth-of-type("+i+") > span > span:nth-of-type(2) > a#twistie_"+ count +"_undefined"));
				
				JavascriptExecutor jsx = (JavascriptExecutor)driver;
				jsx.executeScript("arguments[0].click();", e);
				
				int temp = j*2;				
				commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(expandLocator+"> div:nth-of-type("+i+") > div:nth-of-type("+ temp +") > div > span > span > span > span > span > img"), driver);				
			}
		}
		
		//Handles the members displayed on the last div
		int last_div = x+1;
		int a = getCounter() % 12;
		if (a != 0){
			for (int z = 1 ; z < a+1 ; z++){
				
				int count2 = z-1;
				WebElement last_e = driver.findElement(By.cssSelector(expandLocator+"> div:nth-of-type("+ last_div +") > span > span:nth-of-type(2) > a#twistie_"+ count2+"_undefined"))/*.click()*/;
			
				JavascriptExecutor jsx = (JavascriptExecutor)driver;
				jsx.executeScript("arguments[0].click();", last_e);
				
				int last_temp = z*2;
				commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(expandLocator+"> div:nth-of-type("+ last_div +") > div:nth-of-type("+ last_temp +") > div > a"), driver);
			}
		}
		
		return this;
	}
	
	
}
