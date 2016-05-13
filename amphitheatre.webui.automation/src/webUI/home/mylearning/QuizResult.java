package webUI.home.mylearning;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class QuizResult {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */	
	private By pieChart = By.id("chartNode");
	private By pieChartPercentCorrect = By.cssSelector(".chartTitle.formText2");
	private By learningPathButton = By.cssSelector("span[data-dojo-attach-point='buttonHolder']>input:nth-child(3)");
	private By continueButton = By.cssSelector(".btnStandard");

	public QuizResult(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}	

	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pieChart));
		wait.until(ExpectedConditions.elementToBeClickable(pieChart));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pieChart));		
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public Boolean isPieChartDisplayed()
	{
		return commonMethods.isElementDisplayed(pieChart, driver);
	}
	
	public int getChartPercentageCorrect()
	{
		String percent;
		percent=driver.findElement(pieChartPercentCorrect).getText();
		percent=percent.substring(0,percent.indexOf("%"));
		return Integer.parseInt(percent);
	}
	
	public void clickLearningPathButton()
	{
		driver.findElement(learningPathButton).click();
	}
	
	public void clickContinueButton()
	{
		driver.findElement(continueButton).click();
	}

	public Boolean isLearningPathButtonDisabled()
	{
		return driver.findElement(learningPathButton).getCssValue("disabled").equals("disabled") ? false : true;
	}
	
	public String getLearningPathButtonText()
	{
		return driver.findElement(learningPathButton).getAttribute("value").toLowerCase(); 
	}
}
