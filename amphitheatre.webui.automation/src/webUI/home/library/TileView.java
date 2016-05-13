package webUI.home.library;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class TileView extends Library
{	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
		
	/* String element locators */
	private String tile = "[class^='browseItem']";
	private String icon = " > div > div > span";
	private String watch = " > div > button";
	private String switchToFront = " > a[class$='switchToFront']";
	private String switchToBack = " > a[class$='switchToBack']";
	private String bookmark = " > div > div[class='contentSocialStats linkText3'] > a";
	private String likes = " > div > div > span[class^='charSeparate']:nth-child(1)";
	private String comments = " > div > div > span[class='charSeparate']:nth-child(2)";
	private String authorFront = " > div > div > div > a";
	private String authorBack = " > div > div > span> a";
	private String title = " > div[class='front frontContent'] > div > a";	
	private String tileIcon = " > div > div > span[class^='typeIcon']";
	private String tileTitle = " > div > div[class='frontText'] > a";
	private String firstButton = " > div > button";
	private String secondButton = " > div > a";
	
	public TileView(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public Library clickNthSwitchToFront(int row)
	{
		driver.findElement(By.cssSelector(tile + ":nth-child("+row+")" + switchToFront)).click();
		return this;
	}
	
	public Library clickNthSwitchToBack(int row)
	{
		driver.findElement(By.cssSelector(tile + ":nth-child("+row+")" + switchToBack)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(tile + ":nth-child("+row+")" + switchToFront), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(tile + ":nth-child("+row+")" + likes), driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(tile + ":nth-child("+row+")" + comments), driver);
		return this;
	}
	
	public Library clickNthWatch(int row)
	{
		driver.findElement(By.cssSelector(tile + ":nth-child("+row+")" + watch)).click();
		return this;
	}
	
	public Library clickNthBookmark(int row)
	{
		driver.findElement(By.cssSelector(tile + ":nth-child("+row+")" + bookmark)).click();
		return this;
	}
	
	public String getBookmarkCount(int row)
	{
		return driver.findElement(By.cssSelector(tile+":nth-child("+row+")"+bookmark)).getText();
	}

	public String getLikesCount(int row)
	{
		String temp = driver.findElement(By.cssSelector(tile+":nth-child("+row+")"+likes)).getText(); 
		return temp.substring(0, temp.indexOf(" "));
	}

	public String getCommentsCount(int row)
	{
		return driver.findElement(By.cssSelector(tile+":nth-child("+row+")"+comments)).getText();
	}

	public Boolean isSwitchToFrontDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tile + ":nth-child("+row+")" + switchToFront), driver);
	}
	
	public Boolean isSwitchToBackDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tile + ":nth-child("+row+")" + switchToBack), driver);
	}

	public Boolean isWatchDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tile + ":nth-child("+row+")" + watch), driver);
	}
	
	public Boolean isBookmarkDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tile+":nth-child("+row+")"+bookmark), driver);
	}

	public Boolean isLikesDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tile+":nth-child("+row+")"+likes), driver);
	}

	public Boolean isCommentsDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(tile+":nth-child("+row+")"+comments), driver);
	}
	
	
	/**
	 * Takes in the row number and returns the icon type
	 * @param row The table row
	 * @return Returns either calculator, simulator, summary or video
	 */
	public String getNthCourseIcon(int row)
	{
		String iconClass = driver.findElement(By.cssSelector(tile+":nth-child("+row+")"+icon)).getAttribute("class");
		String type="";
		if (iconClass.equals("typeIcon typeIconCalculator"))
			type="calculator";
		else if (iconClass.equals("typeIcon typeIconSimulator"))
			type="simulator";
		else if (iconClass.equals("typeIcon typeIconSummary"))
			type="summary";
		else if (iconClass.equals("typeIcon typeIconVideo"))
			type="video";
		else
			type="type not found";
		return type;
	}
	
	public int getCountOfContentsWithAuthors()
	{
		return driver.findElements(By.cssSelector(tile+authorFront)).size();
	}
	
	public int getPanelWithFirstExistingAuthor(){
		
		int panel=0;
		
		for(int i=1;i<=12;i++){
			if(commonMethods.isElementDisplayedInstant(By.cssSelector(tile +":nth-child("+i+")"+ authorFront), driver)){
				panel=i;
				break;
			}
		}
		return panel;
	}
	
	public Library clickFirstAuthorFront()
	{
		int panel = getPanelWithFirstExistingAuthor();
		
		driver.findElement(By.cssSelector(tile +":nth-child("+panel+")"+ authorFront)).click();
		return this;
	}
	
	public Library clickFirstAuthorBack()
	{
		
		int panel = getPanelWithFirstExistingAuthor();
		
		driver.findElement(By.cssSelector(tile +":nth-child("+panel+")"+ authorBack)).click();
		return this;
	}
	public List<String> getListOfContentTitles()
	{
		List<String> contentTitles=new ArrayList<String>();
		int countOfContents = getPagingNumber();
	
		for (int i=1;i<=countOfContents;i++)
		{
			String result = driver.findElement(By.cssSelector(tile+ ":nth-child("+i+")"+title)).getText().toLowerCase();
			result = result.replaceAll("[^a-zA-Z]", "");
			contentTitles.add(result);					

		}	
		return contentTitles;
	}
	
	public String getNthContentTitle(int i){
		return driver.findElement(By.cssSelector(tile+ ":nth-child("+i+")"+title)).getText().toLowerCase();
	}
	public int getPanelNumberForTitle(String contentTtitle)
	{
		int countOfContents = getPagingNumber();
		int panel = 0;
		scrollEndOfResults();
		for (int i=1;i<=countOfContents;i++)
		{
			String result = driver.findElement(By.cssSelector(tile+ ":nth-child("+i+")"+title)).getText();
			if(result.equals(contentTtitle))
				panel = i;

		}	
		return panel;
	}
	
	public Boolean isIconDisplayenOnNthTile(int panel){
		return commonMethods.isElementDisplayed(By.cssSelector(tile+":nth-child("+panel+")"+tileIcon), driver);
	}
	
	public Boolean isTitleDisplayenOnNthTile(int panel){
		return commonMethods.isElementDisplayed(By.cssSelector(tile+":nth-child("+panel+")"+tileTitle), driver);
	}
	
	public String getIconTypeForNthTile(int panel){
		
		String iconType = driver.findElement(By.cssSelector(tile+":nth-child("+panel+")"+tileIcon)).getAttribute("class");
		iconType = iconType.substring(iconType.lastIndexOf(" ")+1, iconType.length());
		return iconType;
	}
	
	public String getFirstButtonTextOnNthTile(int panel){
		return driver.findElement(By.cssSelector(tile+":nth-child("+panel+")"+firstButton)).getText();
	}
	
	public String getSecondButtonTextOnNthTile(int panel){
		return driver.findElement(By.cssSelector(tile+":nth-child("+panel+")"+secondButton)).getText();
	}
	
	public Boolean isSecondButtonDisplayedOnNthPanel(int panel){	
		return commonMethods.isElementDisplayedInstant(By.cssSelector(tile+":nth-child("+panel+")"+secondButton), driver);
	}
}
