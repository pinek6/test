package webUI.home.groups.LibraryCourseSources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.home.groups.GroupsViewResourcesTab;

public class Informal extends GroupsViewResourcesTab {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* String Element Locator Constructors */
	private String libraryRowItems = "//div[contains(@id,'sls_widgets_components_groups_LibraryTab')]/div[2]/div/div[2]/div";
	private String libraryRowItemsDownloadButton = "/div[2]/div[2]/div/button";	
	private String libraryRowItemsUploadNewVersion = "/div[2]/div[2]/div/div/span[2]/a";
	private String libraryRowItemsEdit = "/div[2]/div[2]/div/div/span[3]/a";
	private String libraryRowItemsDelete = "/div[2]/div[2]/div/div/span[4]/a";


	public Informal(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}	
	
	public void clickNthCourseDownload(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDownloadButton)).click();
	}
	
	public void clickNthCourseUploadNewVersion(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsUploadNewVersion)).click();
	}
	
	public void clickNthCourseEdit(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsEdit)).click();
	}
	
	public void clickNthCourseDelete(int row)
	{
		driver.findElement(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDelete)).click();
	}
	
	public Boolean isNthCourseDownloadDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDownloadButton), driver);
	}	
	
	public Boolean isNthCourseUploadNewVersionDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsUploadNewVersion), driver);
	}
	
	public Boolean isNthCourseEditDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsEdit), driver);
	}
	
	public Boolean isNthCourseDeleteDisplayed(int row)
	{
		return commonMethods.isElementDisplayed(By.xpath(libraryRowItems+"["+row+"]" + libraryRowItemsDelete), driver);
	}
	
}
