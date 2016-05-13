package webUI.home.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.home.library.mediaTypes.MediaType;

public class ListView extends Library
{	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rowDetail = By.cssSelector(".browseRowDetail");
	
	/* String element locators */
	private String resultsGrid = ".browseResultRow > div";
	private String twistie = " > span[class='colBrowseExpand'] > a";
	private String watch = " > div > input";
	private String bookmarks = " > div > a";
	//private String views = "/div/span[1]";
	private String discussions = " > div > span[class='bodyText2']";
	private String courseIcon = " > span[class^='typeIcon']";
	private String courseTitle = " > span[class^='colBrowseTitle '] > a";
	private String courseType = " > span[class^='colBrowseType']";
	private String courseSubjectArea = " > span[class^='colBrowseSubject']";
	private String courseAuthorName = " > span[class^='colBrowseAuthor'] > a";
	private String courseDateAdded = " > span[class^='colBrowseDate']";
	private String columnLabel = ".colSortRow";
	private String sortingRow = ".sortingRow:nth-child(1) > a";
	private String likes = " > div > span[class^='likeCount']";

	public ListView(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}

	public ListView clickTwistieExpand(int row)
	{
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+twistie)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+watch), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+bookmarks), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+discussions), driver);
		return this;
	}
	
	public ListView clickTwistieCollapse(int row)
	{
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+twistie)).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+watch), driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+bookmarks), driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+discussions), driver);
		return this;
	}
	
	public MediaType clickNthCourseTitle(int row)
	{
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseTitle)).click();
		return new MediaType(driver) ;
	}
	
	public ListView clickNthWatch(int row)
	{
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+watch)).click();
		return this;
	}

	/**
	 * Takes in the row number and returns the icon type
	 * @param row The table row
	 * @return Returns either calculator, simulator, summary or video
	 */
	public String getNthCourseIcon(int row)
	{
		String icon = driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseIcon)).getAttribute("class");
		String type="";
		if (icon.equals("typeIcon typeIconCalculator"))
			type="calculator";
		else if (icon.equals("typeIcon typeIconSimulator"))
			type="simulator";
		else if (icon.equals("typeIcon typeIconSummary"))
			type="summary";
		else if (icon.equals("typeIcon typeIconVideo"))
			type="video";
		else
			type="type not found";
		return type;
	}
	
	public String getNthCourseTitle(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseTitle)).getText();
	}
	
	public String getNthCourseType(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseType)).getText().toLowerCase();
	}

	public String getNthCourseSubjectArea(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseSubjectArea)).getText().toLowerCase();
	}

	public String getNthCourseAuthorName(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseAuthorName)).getText();
	}
	
	public ListView clickNthCourseAuthorName(int row)
	{
		driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseAuthorName)).click();
		return this;
	}

	public String getNthCourseDateAdded(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+courseDateAdded)).getText();
	}

	/*public String getViewsCount(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+(row*2)+")"+views)).getText();
	}*/

	public String getDiscussionsCount(int row)
	{
		return driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-2)+")"+discussions)).getText();
	}
	
	public int getCountOfResultsInTileView()
	{
		return driver.findElements(rowDetail).size();
	}

	public Boolean isRowDetailExpanded(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(resultsGrid +":nth-child("+((row*3)-1)+") > div"), driver);
	}
	
	public Boolean isWatchButtonDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(resultsGrid +":nth-child("+((row*3)-1)+")" + watch), driver);
	}
	
	public Boolean isBookmarksDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(resultsGrid +":nth-child("+((row*3)-1)+")" + bookmarks), driver);
	}
	
	public Boolean isLikesDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(resultsGrid +":nth-child("+((row*3)-1)+")" + likes), driver);
	}
	
	public Boolean isDiscussionsDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(resultsGrid +":nth-child("+((row*3)-1)+")" + discussions), driver);
	}
	
	public Boolean isAscSortIconDisplayed(int col)
	{
		String icon = driver.findElement(By.cssSelector(sortingRow +":nth-child("+col+") > span")).getAttribute("class");
		if (icon.equals("colSortAsc"))
			return true;
		else
			return false;
	}
	
	public Boolean isDescSortIconDisplayed(int col)
	{
		String icon = driver.findElement(By.cssSelector(sortingRow +":nth-child("+col+") > span")).getAttribute("class");
		if (icon.equals("colSortDesc"))
			return true;
		else
			return false;
	}
	
	public List<String> getListOfContentTitles()
	{
		List<String> contentTitles=new ArrayList<String>();
		int countOfContents = getPagingNumber();
		String result = "";
		for (int i=1;i<=countOfContents;i++)
		{	
			int counter = i*3-2;
			result = driver.findElement(By.cssSelector(resultsGrid+ ":nth-child("+counter+")"+courseTitle)).getText().toLowerCase();
			result = result.replaceAll("[^a-zA-Z]", "");
			contentTitles.add(result);					

		}	
		return contentTitles;
	}
	
	public List<String> getListOfContentTypes()
	{
		List<String> contentTypes=new ArrayList<String>();
		int countOfContents = getPagingNumber();
	
		for (int i=1;i<=countOfContents;i++)
		{	
			int counter = i*3-2;
			String result = driver.findElement(By.cssSelector(resultsGrid+ ":nth-child("+counter+")"+courseType)).getText().toLowerCase();
			contentTypes.add(result);					

		}	
		return contentTypes;
	}
	
	public List<String> getListOfContentSubjects()
	{
		List<String> contentSubjects=new ArrayList<String>();
		int countOfContents = getPagingNumber();
	
		for (int i=1;i<=countOfContents;i++)
		{	
			int counter = i*3-2;
			String result = driver.findElement(By.cssSelector(resultsGrid+ ":nth-child("+counter+")"+courseSubjectArea)).getText().toLowerCase();
			contentSubjects.add(result);					

		}	
		return contentSubjects;
	}
	
	public List<String> getListOfContentAuthors()
	{
		List<String> contentAuthors=new ArrayList<String>();
		int countOfContents = getPagingNumber();
	
		for (int i=1;i<=countOfContents;i++)
		{	
			int counter = i*3-2;
			String result = driver.findElement(By.cssSelector(resultsGrid+ ":nth-child("+counter+")"+courseAuthorName)).getText();
			//result = result.replaceAll("[^a-zA-Z]", "");
			if (!(result.equals("")))
			contentAuthors.add(result);					

		}	
		return contentAuthors;
	}
	
	
	public List<Date> getListOfContentDatesAdded() throws ParseException
	{
		List<Date> contentDatesAdded=new ArrayList<Date>();
		int countOfContents = getPagingNumber();
		
		for (int i=1;i<=countOfContents;i++)
		{	
			int counter = i*3-2;
			
			Calendar now = Calendar.getInstance();   
			String currentYear = Integer.toString(now.get(Calendar.YEAR));			
			
			String result = driver.findElement(By.cssSelector(resultsGrid+ ":nth-child("+counter+")"+courseDateAdded)).getText();
			
			String[] splitted = result.split("\\s");
			String month = splitted[0];
			String day = splitted[1]; 
			String year = currentYear;
			
			if(day.length() == 1)
				day = "0"+day;
			
			if(splitted.length > 2)
				year = splitted[2];
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMddyyyy");

			Date date = dateFormat.parse(month+day+year);
			contentDatesAdded.add(date);
	
		}	
		return contentDatesAdded;
	}
	
	public ListView clickNthColumnLabel(int column)
	{
		driver.findElement(By.cssSelector(columnLabel+":nth-child("+column+")")).click();
		commonMethods.waitForElementToBeNotVisible(loading, driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(columnLabel), driver);
		return this;
	}
	
	public int getLikesCounterForNthItem(int row){
		String likesStr =  driver.findElement(By.cssSelector(resultsGrid+":nth-child("+((row*3)-1)+")"+likes)).getText();
		likesStr = likesStr.substring(0, likesStr.indexOf(" "));
		return Integer.parseInt(likesStr);
	}
}
