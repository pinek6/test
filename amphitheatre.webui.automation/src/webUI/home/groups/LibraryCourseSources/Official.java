package webUI.home.groups.LibraryCourseSources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.home.groups.GroupsViewResourcesTab;

public class Official extends GroupsViewResourcesTab{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	private String libraryRowItems = "//div[contains(@id,'sls_widgets_components_groups_LibraryTab')]/div[2]/div/div[2]/div";
	private String libraryRowItemsViewButton = "/div[2]/div[2]/div/button";	
	private String libraryRowItemsEdit = "/div[2]/div[2]/div/div/span[2]/a";
	private String libraryRowItemsDelete = "/div[2]/div[2]/div/div/span[3]/a";
	private String libraryRowItemsComments = "/div[2]/div[2]/div/div/span[4]/a";
	private String libraryRowItemsLikes = "/div[2]/div[2]/div/span/a";

	public Official(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}	
	
	public void clickNthCourseView(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsViewButton)).click();
	}
	
	public void clickNthCourseEdit(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsEdit)).click();
	}
	
	public void clickNthCourseDelete(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDelete)).click();
	}
	
	public void clickNthCourseComments(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsComments)).click();
	}
	
	public void clickNthCourseLikes(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsLikes)).click();
	}
	
	public Boolean isNthCourseViewDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsViewButton), driver);
	}	
	
	public Boolean isNthCourseEditDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsEdit), driver);
	}
	
	public Boolean isNthCourseDeleteDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDelete), driver);
	}
	
	public Boolean isNthCourseCommentsDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsComments), driver);
	}
	
	public Boolean isNthCourseLikesDisplayed(int row)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsLikes), driver);
	}	



	
}
