package webUI.home.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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


public class Library {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);

	public Library(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	/* Element Locators */
	private By headerLocator = By.cssSelector("[class='contentBodyWrapper'] > [class ='contentBody'] > div > h1");
	private By tileViewLocator = By.cssSelector("#tile > a");
	private By listViewLocator = By.cssSelector("#row > a");
	private By listViewGridLocator = By.cssSelector(".browseResultRow");
	private By browseResult = By.cssSelector(".browseResults");
	private By tileViewGridLocator = By.cssSelector("div.browseItem.front");
	private By searchInputLocator = By.cssSelector("[class='contentBodyWrapper'] > div > div > div > [class =  'searchInput']");
	private By searchButton = By.cssSelector(".searchColLeft button");
	private By pagingTextLocator = By.cssSelector("div[class='searchColRight searchItemCounter'] > span");
	private By clearSearchLinkLocator = By.cssSelector(".linkText5");
	private By categoriesLocator = By.cssSelector(".facetCategory");
	private By calculatorFilterLocator = By.cssSelector("input[data-filtername='Calculator']");
	private By simulationFilterLocator = By.cssSelector("input[data-filtername='Simulation']");
	private By summaryFilterLocator = By.cssSelector("input[data-filtername='Summary']");
	private By videoFilterLocator = By.cssSelector("input[data-filtername='Video']");
	private By activeView = By.cssSelector(".active");
	private By showMore = By.cssSelector(".linkText2");
	private By columnLabel = By.cssSelector(".colSortRow");
	//private By browseResutlsRow =By.cssSelector(".browseResultRow > div:not([class=browseRowDetail])"); 
	public By loading = By.cssSelector(".loading");
	/* String element locators */
	private String categoryTitle = "div[id^='sls_widgets_widgets_views_library_Library'] > div > div > div:nth-child(5) > div > div";
	private String categoriesInFacetList = "div[id^='sls_widgets_components_global_FilterTiles_'] > span > ul > li";
	private String resultsTiles = "div[id*='sls_widgets_widgets_views_library_Library']>div>div>div:nth-child(5)>div:nth-child(2)>div>div";
	private String resultsList  = "div[id^='sls_widgets_widgets_views_library_Library'] > div > div > div:nth-child(5) > div:nth-child(2) > div > div:nth-child(3) > div";
	private String libraryResultType = " > div > div > span";
	private String firstButton = " > div > button";
	private String secondButton = " > div > button:nth-child(2)";
	private String columnLabels = ".sortingRow > a";
	private String noItemsMsg = new LocaleStrings().getString("NO_ITEMS_MSG");
		
	private String summaryClass = "typeIcon typeIconSummary";
	private String videoClass = "typeIcon typeIconVideo";
	private String calculatorClass = "typeIcon typeIconCalculator";
	private String simulatorClass = "typeIcon typeIconSimulator";

	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}
	
	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLocator));
		wait.until(ExpectedConditions.elementToBeClickable(headerLocator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pagingTextLocator));
		wait.until(ExpectedConditions.elementToBeClickable(pagingTextLocator));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchInputLocator));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public TileView clickTileView()
	{
		driver.findElement(tileViewLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return new TileView(driver);
	}
	
	public ListView clickListView()
	{
		driver.findElement(listViewLocator).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(listViewGridLocator, driver);
		commonMethods.waitForElementToBeRefreshed(columnLabel,driver);
		return new ListView(driver);
	}
	
	public Library clickMthSubjectInNthCategoryInLeftColumns(int nth, int mth)
	{	
		String subject = categoryTitle+ ":nth-child("+nth+") > ul > li:nth-child("+mth+") > label";
		commonMethods.scrollIntoView(driver, subject);
		driver.findElement(By.cssSelector(categoryTitle+ ":nth-child("+nth+") > ul > li:nth-child("+mth+") > input")).click();
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		commonMethods.waitForElementToBeRefreshed(browseResult, driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public Library clickNthCategoryInFacetList(int n)
	{
		String nth=Integer.toString(n);
		driver.findElement(By.cssSelector(categoriesInFacetList+ ":nth-child("+nth+") > a")).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}

	public void click1stButtonInNthPanel(int panel)
	{
		commonMethods.scrollIntoView(driver, resultsTiles + ":nth-child("+panel+")" + firstButton);
		driver.findElement(By.cssSelector(resultsTiles + ":nth-child("+panel+")" + firstButton)).click();
	}
	
	public void click2ndButtonInNthPanel(int panel)
	{
		driver.findElement(By.cssSelector(resultsTiles + ":nth-child("+panel+")" + secondButton)).click();
	}
	
	public Library clickSearch()
	{
		clickSearchButton();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator,driver);
		return this;
	}

	public Library enterSearchTerm(String searchTerm)
	{
		driver.findElement(searchInputLocator).sendKeys(searchTerm);
		return this;
	}
	
	public String getSearchFieldText()
	{
		return driver.findElement(searchInputLocator).getText();
	}
	
	public Library clickClearSearch()
	{
		driver.findElement(clearSearchLinkLocator).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
	public Library clickSearchButton()
	{
		driver.findElement(searchButton).click();
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public String getPagingText()
	{
		return driver.findElement(pagingTextLocator).getText();
	}
	public int getPagingNumber()
	{
		String counter = getPagingText();
		
		if (counter.equals(noItemsMsg))
			return 0;
		else
			return Integer.parseInt(counter.substring(0,counter.indexOf(' ')));
	}
		
	public Library searchFor(String searchTerm)
	{
		enterSearchTerm(searchTerm);
		return clickSearch();		
	}

	public Library toggleNthCategory(int nth)
	{
		driver.findElement(By.cssSelector(categoryTitle + ":nth-child("+nth+") > h3")).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		return this;
	}
	
	public Library expandNthCategory(int nth)
	{
		if (!isNthCategoryExpanded(nth))
			driver.findElement(By.cssSelector(categoryTitle + ":nth-child("+nth+") > h3")).click();
		return this;
	}
	
	public Library collapseNthCategory(int nth)
	{
		if (isNthCategoryExpanded(nth))
			driver.findElement(By.cssSelector(categoryTitle + ":nth-child("+nth+") > h3")).click();
		return this;
	}
	
	public Library selectAllSubjects()
	{
		int countOfCategories = getCountOfCategories();
	
		for (int i=1;i<=countOfCategories;i++)
		{
			int countOfSubject = getCountOfSubjectsInNthCategory(i);
			for (int j=1;j<=countOfSubject;j++)
			{
				if (!isNthSubjectCheckedInLeftColumn(i,j))
				{
					clickMthSubjectInNthCategoryInLeftColumns(i,j);			
				}
			}
		}	
		return this;
	}
	
	public Library deselectAllSubjects()
	{
		int countOfCategories = getCountOfCategories();
	
		for (int i=1;i<=countOfCategories;i++)
		{
			int countOfSubject = getCountOfSubjectsInNthCategory(i);
			for (int j=1;j<=countOfSubject;j++)
			{
				if (isNthSubjectCheckedInLeftColumn(i,j))
				{
					clickMthSubjectInNthCategoryInLeftColumns(i,j);			
				}
			}
		}	
		return this;
	}
	
	public String getMthSubjectInNthCategoryInLeftColumns(int nth, int mth)
	{
		return driver.findElement(By.cssSelector(categoryTitle+ ":nth-child("+nth+") > ul > li:nth-child("+mth+") > label")).getText().toLowerCase();
	}

	public int getCountOfCategories()
	{
		return driver.findElements(categoriesLocator).size();
	}

	public int getCountOfSubjectsInNthCategory(int nth)
	{
		return driver.findElements(By.cssSelector(categoryTitle + ":nth-child("+nth+") > ul > li")).size();
	}

	public String getNthCategoryInFacetList(int nth)
	{
		return driver.findElement(By.cssSelector(categoriesInFacetList+ ":nth-child("+nth+")")).getText();
	}

	public String getNthCategoryInLeftColumns(int nth)
	{
		return driver.findElement(By.cssSelector(categoryTitle + ":nth-child("+nth+") > h3")).getText();
	}

	public List<String> getListOfSubjectsCheckedInLeftColumn()
	{
		List<String> selectedSubjects=new ArrayList<String>();
		int countOfCategories = getCountOfCategories();
	
		for (int i=1;i<=countOfCategories;i++)
		{
			int countOfSubject = getCountOfSubjectsInNthCategory(i);
			for (int j=1;j<=countOfSubject;j++)
			{
				if (isNthSubjectCheckedInLeftColumn(i,j))
				{
					selectedSubjects.add(getNthCategoryInLeftColumns(i) + ": " +
							driver.findElement(By.cssSelector(categoryTitle+ ":nth-child("+i+") > ul > li:nth-child("+j+") > label")).getText());					
				}
			}
		}	
		Collections.sort(selectedSubjects);
		return selectedSubjects;
	}

	public List<String> getListOfSubjectsInFacetList()
	{
		List<String> selectedSubjects=new ArrayList<String>();
		int countOfSubjects = driver.findElements(By.cssSelector(categoriesInFacetList+ " > span")).size();
		for (int i=1;i<countOfSubjects+1;i++)
		{
				selectedSubjects.add(getNthCategoryInFacetList(i));
		}	
		Collections.sort(selectedSubjects);
		return selectedSubjects;
	}

	public Boolean isListViewDisplayed()
	{	
		Boolean found = false;
		List<WebElement> deleteLinks = driver.findElements(listViewGridLocator);
		for (WebElement w : deleteLinks)
		{
			if (w.isDisplayed())
				found=true;
		}		
		return found;		
	}

	public Boolean isTileViewDisplayed()
	{		
		Boolean found = false;
		List<WebElement> deleteLinks = driver.findElements(tileViewGridLocator);
		for (WebElement w : deleteLinks)
		{
			if (w.isDisplayed())
				found=true;
		}		
		return found;	
	}

	public Boolean isNthCategoryExpanded(int n)
	{
		return driver.findElement(By.cssSelector(categoryTitle + ":nth-child("+n+") > h3")).getAttribute("class").equals("facetTitle closed")
		&& driver.findElement(By.cssSelector(categoryTitle + ":nth-child("+n+") > ul")).getAttribute("class").equals("facetOptionList hide")? false : true;
	}

	public Boolean isNthSubjectCheckedInLeftColumn(int n, int m)
	{
		String nth=Integer.toString(n);
		String mth=Integer.toString(m);
		return driver.findElement(By.cssSelector(categoryTitle+ ":nth-child("+nth+") > ul > li:nth-child("+mth+") > input")).isSelected()? true : false;
	}
	
	public String getLibraryResultType(int panel)
	{
		String type = driver.findElement(By.cssSelector(resultsTiles+ ":nth-child("+panel+")"+libraryResultType)).getAttribute("class");
		
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
	
	public int getCountOfResultsInTileView()
	{
		return driver.findElements(By.cssSelector(resultsTiles)).size();
	}
	
	public int getCountOfResultsInListView()
	{
		return driver.findElements(By.cssSelector(resultsList)).size()/2;
	}
	
	public Library selectCalculatorCheckbox()
	{	
		driver.findElement(calculatorFilterLocator).click();
//		commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
	public Library selectSimulationCheckbox()
	{	
		driver.findElement(simulationFilterLocator).click();
//		commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
	public Library selectSummaryCheckbox()
	{	
		driver.findElement(summaryFilterLocator).click();
//		commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
	public Library selectVideoCheckbox()
	{	
		driver.findElement(videoFilterLocator).click();
//		commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(pagingTextLocator, driver);
		return this;
	}
	
	public String getActiveView()
	{
		return driver.findElement(activeView).getText();
	}
	
	
	public int getCountOfResults()
	{
		String view = getActiveView();
		
		if (view.equals("tile"))
			return getCountOfResultsInTileView();
		if (view.equals("view"))
			return getCountOfResultsInTileView();
		if (view.equals("row"))
			return getCountOfResultsInListView();
		else
			return 0;
	}
	
	public Library scrollEndOfResults()
	{
		int totalItemsCount = getPagingNumber();
		int divisor = 12;
		int scrollCounter = 0;
		String view = getActiveView();
		
		if (totalItemsCount%divisor==0)
			 scrollCounter = (totalItemsCount/divisor)-1;
		else
			 scrollCounter = (totalItemsCount/divisor);	

		for(int i=0;i<scrollCounter;i++)
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
					+ "document.body.scrollHeight,document.documentElement.clientHeight));");
			
			if (view.equals("tile")){
				//commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
				commonMethods.waitForElementToBeNotVisible(loading, driver);}
			if (view.equals("view")){
				//commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
				commonMethods.waitForElementToBeNotVisible(loading, driver);}
			if (view.equals("row")){
				//commonMethods.waitForElementToBeRefreshed(browseResutlsRow,driver);
				commonMethods.waitForElementToBeNotVisible(loading, driver);}
		}
		return this;
	}
	public Library clickEndOfResults()
	{
		int totalItemsCount = getPagingNumber();
		int displayedItemsCount = 0; 
		displayedItemsCount	=	getCountOfResults();
		
		while(totalItemsCount!=displayedItemsCount)
		{	
			driver.findElement(showMore).click();
			commonMethods.waitForElementToBeRefreshed(By.cssSelector(resultsTiles),driver);
			commonMethods.waitForElementToBeNotVisible(loading, driver);
			displayedItemsCount = getCountOfResults();
		}
		return this;
	}
	
	public Boolean isPushScrollingWorks()
	{
		int displayedItemsCountBefore = getCountOfResults();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000)", "");
		jse.executeScript("window.scrollBy(0,1000)", "");
		
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(resultsTiles),driver);
		commonMethods.waitForElementToBeNotVisible(loading, driver);
			
		int displayedItemsCountAfter = getCountOfResults();
		if (displayedItemsCountBefore==displayedItemsCountAfter)
			return false;
		else
			return true;
	}
	public Library goToTopOfThePage()
	{
		//Actions builder = new Actions(driver);
		//WebElement title = driver.findElement(headerLocator);
        //builder.moveToElement(title).build().perform();
        
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(Math.min(document.documentElement.scrollHeight,"
				+ "document.body.scrollHeight,document.documentElement.clientHeight),0);");
        
		return this;
	}
	
	public List<String> sortList(List<String> list, String ascdesc)
	{	
		if (ascdesc.equals("asc"))
			Collections.sort(list);
		if (ascdesc.equals("desc")) 
			Collections.sort(list,Collections.reverseOrder());
		else
			Collections.sort(list);
		return list;
	}
	
	public List<Date> sortDateList(List<Date> list, String ascdesc)
	{	
		if (ascdesc.equals("asc"))
			Collections.sort(list);
		if (ascdesc.equals("desc")) 
			Collections.sort(list,Collections.reverseOrder());
		else
			Collections.sort(list);
		return list;
	}
	
	public Boolean isSortingPanelDisplayed(){
		Boolean result = true;
		for(int i=1;i<=5;i++){
			result = commonMethods.isElementDisplayedInstant(By.cssSelector(columnLabels+":nth-child("+i+")"), driver);
		}
		return result;
	}
	public Library scrollToPanel(int panel){
		
		int totalItemsCount = panel;
		int divisor = 12;
		int scrollCounter = 0;
		String view = getActiveView();
		
		if (totalItemsCount%divisor==0)
			 scrollCounter = (totalItemsCount/divisor)-1;
		else
			 scrollCounter = (totalItemsCount/divisor);	

		for(int i=0;i<scrollCounter;i++)
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
					+ "document.body.scrollHeight,document.documentElement.clientHeight));");
			
			if (view.equals("tile")){
				//commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
				commonMethods.waitForElementToBeNotVisible(loading, driver);}
			if (view.equals("view")){
				//commonMethods.waitForElementToBeRefreshed(By.xpath(resultsTiles),driver);
				commonMethods.waitForElementToBeNotVisible(loading, driver);}
			if (view.equals("row")){
				//commonMethods.waitForElementToBeRefreshed(browseResutlsRow,driver);
				commonMethods.waitForElementToBeNotVisible(loading, driver);}
		}
		return this;
	}
	
}
