package admin.learningAdministration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class LearningAdministrationCurricula extends LearningAdministration {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By goButton = By.id("ListMember");
	private By resultsFrame = By.cssSelector("iframe[id='fraCurrList']");
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By searchFor = By.id("CurrKeyWord");
	private By resultsPerPage = By.cssSelector("select[name='RowsPerPage']");
	
	/* String Element Locator Constructors */
	private String curriculaTable = "table[id='tblHeader']";
	private String curriculaTableRow = curriculaTable + " > tbody > tr";
	private String curriculaTableCheckbox = " > td > input";
	private String curriculaTableTitle = " > td:nth-child(2) > a";
	private String curriculaTableVersion = " > td:nth-child(3)";
	private String curriculaTableStatus = " > td:nth-child(4)";
	private String curriculaTableType = " > td:nth-child(5)";
	private String curriculaTableSequenced = " > td:nth-child(6)";
	private String curriculaTableDescription = " > td:nth-child(7)";	
	
	public LearningAdministrationCurricula(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public int getCountOfCurriculaInCurriculaTable()
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		int count =  driver.findElements(By.cssSelector(curriculaTableRow)).size()-1;
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return count;
	}
	
	public LearningAdministrationCurricula clickNthCheckboxInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableCheckbox)).click();	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);	
		return this;
	}
	
	public LearningAdministrationCurriculumDetails clickNthTitleInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableTitle)).click();	
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);	
		return new LearningAdministrationCurriculumDetails(driver);
	}
	
	public String getNthTitleInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableTitle)).getText().trim();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public String getNthVersionInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableVersion)).getText().trim();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public String getNthStatusInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableStatus)).getText().trim();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public String getNthTypeInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableType)).getText().trim();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public String getNthSequencedInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableSequenced)).getText().trim();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public String getNthDescriptionInCurriculaTable(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		String title = driver.findElement(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableDescription)).getText().trim();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return title;
	}
	
	public Boolean isNthCheckboxInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableCheckbox), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthTitleInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableTitle), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthVersionInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableVersion), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthStatusInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableStatus), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthTypeInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableType), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthSequencedInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableSequenced), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public Boolean isNthDescriptionInCurriculaTableDisplayed(int n)
	{
		commonMethods.switchToFrame(driver, resultsFrame);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(curriculaTableRow+":nth-child("+(n+1)+")"+curriculaTableDescription), driver);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);		
		return found;
	}
	
	public LearningAdministrationCurricula clickGo()
	{
		driver.findElement(goButton).click();
		return this;
	}
	
	public LearningAdministrationCurricula enterSearchFor(String text)
	{
		driver.findElement(searchFor).sendKeys(text);
		return this;
	}
	
	public Boolean isSearchForDisplayed()
	{
		return commonMethods.isElementDisplayed(searchFor, driver);
	}
	
	public LearningAdministrationCurricula selectNResultsPerPage(int n)
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
