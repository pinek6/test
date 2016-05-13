package admin.learningAdministration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class LearningAdministrationCourses extends LearningAdministration {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By goButton = By.id("ListMember");
	private By resultsFrame = By.cssSelector("iframe[id='fraCrsList']");
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By searchFor = By.id("CurrKeyWord");
	private By resultsPerPage = By.cssSelector("select[name='RowsPerPage']");
	
	/* String Element Locator Constructors */
	private String coursesTable = "table[id='tblHeader'] > tbody > tr";
	private String coursesTableCheckbox = " > td> input";
	private String coursesTableTitle = " > td:nth-child(2) > a";
	private String coursesTableStatus = " > td:nth-child(3)";
	private String coursesTableOrganization = " > td:nth-child(5) > a";
	private String coursesTableOfferings = " > td:nth-child(6) > img";
	
	public LearningAdministrationCourses(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public int getCountOfCoursesInCoursesTable()
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		int count =  driver.findElements(By.cssSelector(coursesTable)).size()-1;
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return count;
	}
	
	public LearningAdministrationCourses clickNthCheckboxInCoursesTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		driver.findElement(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableCheckbox)).click();	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);	
		return this;
	}
	
	public LearningAdministrationCourses clickNthTitleInCoursesTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		driver.findElement(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableTitle)).click();	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);	
		return this;
	}
	
	public String getNthTitleInCoursesTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableTitle)).getText();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public Boolean isNthCheckboxInCoursesTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableCheckbox), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthTitleInCoursesTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableTitle), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthStatusInCoursesTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableStatus), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthOrganizationInCoursesTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableOrganization), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthOfferingsInCoursesTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(coursesTable+":nth-child("+(n+1)+")"+coursesTableOfferings), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public LearningAdministrationCourses clickGo()
	{
		driver.findElement(goButton).click();
		return this;
	}
	
	public LearningAdministrationCourses enterSearchFor(String text)
	{
		driver.findElement(searchFor).sendKeys(text);
		return this;
	}
	
	public Boolean isSearchForDisplayed()
	{
		return commonMethods.isElementDisplayed(searchFor, driver);
	}
	
	public LearningAdministrationCourses selectNResultsPerPage(int n)
	{	
		commonMethods.switchToFrame(driver, resultsFrame);
		new Select(driver.findElement(resultsPerPage)).selectByValue(Integer.toString(n));
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return this;
	}
	
	public Boolean isResultsPerPageDisplayed()
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(resultsPerPage, driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isGoButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(goButton, driver);
	}
}
