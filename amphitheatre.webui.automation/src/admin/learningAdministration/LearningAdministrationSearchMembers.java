package admin.learningAdministration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class LearningAdministrationSearchMembers {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By searchFrame = By.xpath("//iframe[@id='fraMemLst']");
	private By forField = By.id("MemSearchFor22");
	private By submit = By.name("imgSubmit");
	private By search = By.cssSelector("input[value='Search']");
	
	/* String Element Constructors */
	private String searchResults = "form[id='fmSearchCurrGrid'] > table > tbody > tr";
	private String title = " > td > a";
	
	public void waitForPageToLoad()
	{
		
		
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(forField));
		wait.until(ExpectedConditions.visibilityOfElementLocated(forField));
		wait.until(ExpectedConditions.presenceOfElementLocated(forField));	

		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		
	}
	
	public LearningAdministrationSearchMembers(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad();
	}
	
	public void enterFor(String searchterm)
	{
		
		
		driver.findElement(forField).sendKeys(searchterm);
		
	}
	
	public LearningAdministrationSearchMembers clickSearch()
	{
		
		
		driver.findElement(search).click();
		
		return this;
	}
	
	public LearningAdministrationCurriculum clickNthTitle(int n)
	{
		
		
		commonMethods.switchToFrame(driver, searchFrame);
		driver.findElement(By.cssSelector(searchResults+":nth-child("+(n+1)+")"+title)).click();
		
		return new LearningAdministrationCurriculum(driver);
	}
	
	public void clickSubmit()
	{
		
		
		driver.findElement(submit).click();
		
	}
}
