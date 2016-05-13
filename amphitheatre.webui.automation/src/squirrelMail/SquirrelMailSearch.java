package squirrelMail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class SquirrelMailSearch 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	private By rightnavframe = By.cssSelector("frame[name='right']");
	private By searchTerm = By.name("what");
	private By searchWhere = By.name("where");
	private By searchButton = By.name("submit");
	
	/* String Element Locator Constructors */
	private String resultsTable = "table[bgColor='#ffffcc'] > tbody > tr";
	private String resultsTableFrom = " > td:nth-child(2)";
	private String resultsTableDate = " > td:nth-child(3)";
	private String resultsTableSubjectUnread = " > td:nth-child(5) > b > a";
	private String resultsTableSubjectRead = " > td:nth-child(5) > a";
	
	public SquirrelMailSearch (WebDriver driver)
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
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
		wait.until(ExpectedConditions.presenceOfElementLocated(searchButton));	
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);		
	}
	
	public SquirrelMailSearch enterSearchTerm(String str)
	{
		driver.findElement(searchTerm).sendKeys(str);
		return this;
	}
	
	public void clickSearch()
	{
		driver.findElement(searchButton).click();
	}
	
	public void searchFor(String str)
	{		
		System.out.println("Searching for email");
		enterWhereToSearch("TO");
		enterSearchTerm(str);
		do
		{			
			clickSearch();
		}
		while (getCountOfEmails()==0);
		System.out.println("");
		System.out.print("Email Found");
	}
	
	public SquirrelMailSearch enterWhereToSearch(String value)
	{
		new Select(driver.findElement(searchWhere)).selectByValue(value);
		return this;
	}
	
	public int getCountOfEmails()
	{
		int emails = driver.findElements(By.cssSelector(resultsTable)).size();
		return emails==0 ? 0 : emails-3;
	}
	
	public String getNthEmailFrom(int n)
	{
		return driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")" + resultsTableFrom)).getText();
	}
	
	public String getNthEmailDate(int n)
	{
		return driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")" + resultsTableDate)).getText();
	}
	
	public String getNthEmailSubjectUnread(int n)
	{
		return driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")" + resultsTableSubjectUnread)).getText();
	}
	
	public void clickNthEmailUnread(int n)
	{
		driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")" + resultsTableSubjectUnread)).click();
	}
	
	public String getNthEmailSubjectRead(int n)
	{
		return driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")" + resultsTableSubjectRead)).getText();
	}
	
	public void clickNthEmailRead(int n)
	{
		driver.findElement(By.cssSelector(resultsTable+":nth-child("+(n+2)+")" + resultsTableSubjectRead)).click();
	}
}
