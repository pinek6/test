package admin.learningAdministration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class LearningAdministrationCurriculumDetails  {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By curriculumName = By.cssSelector("td[class='PAGE-TITLE']");
	private By enrol = By.name("imgEnroll");
	private By back = By.name("imgBack");
	
	public LearningAdministrationCurriculumDetails(WebDriver driver)
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
		wait.until(ExpectedConditions.elementToBeClickable(enrol));
		wait.until(ExpectedConditions.visibilityOfElementLocated(enrol));
		wait.until(ExpectedConditions.presenceOfElementLocated(enrol));	
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
	}
	
	public LearningAdministrationSearchMembers clickEnrol()
	{	
		driver.findElement(enrol).click();		
		return new LearningAdministrationSearchMembers(driver);
	}
	
	public void clickBack()
	{
		driver.findElement(back).click();		
	}	
	
	public String getCurriculumName()
	{
		return driver.findElement(curriculumName).getText();
	}
	
	public Boolean isCurriculumNameDisplayed()
	{
		return commonMethods.isElementDisplayed(curriculumName, driver);
	}
	
	public Boolean isEnrollButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(enrol, driver);
	}
	
	public Boolean isBackButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(back, driver);
	}
}
