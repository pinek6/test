package webUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	public WebDriver driver;
	
	public CommonMethods(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean isElementPresent(By by, WebDriver driver) 
	{
		this.driver=driver;
	    try 
	    {
	      driver.findElement(by);
	    	  return true;
	    } 
	    catch (NoSuchElementException e) 
	    {
	      return false;
	    }
	}
	
	public boolean isElementDisplayed(By by, WebDriver driver) 
	{
		this.driver=driver;
	    try 
	    {
	      if (driver.findElement(by).isDisplayed())
	    	  return true;
	      else
	    	  return false;
	    } 
	    catch (NoSuchElementException e) 
	    {
	      return false;
	    }
	}
	
	public boolean isElementTextDisplayedAndNotEmpty(By by, WebDriver driver) 
	{
		this.driver=driver;
	    try 
	    {
	      driver.findElement(by);
	      if (driver.findElement(by).isDisplayed() && driver.findElement(by).getText().length()>0)
	    	  return true;
	      else
	    	  return false;
	    } 
	    catch (NoSuchElementException e) 
	    {
	      return false;
	    }
	}
	
	public boolean isCheckboxSelected(By by, WebDriver driver) 
	{
		this.driver=driver;
	    try 
	    {
	      driver.findElement(by).isSelected();
	    	  return true;
	    } 
	    catch (NoSuchElementException e) 
	    {
	      return false;
	    }
	}
	
	public void waitForElementToBeRefreshed(By element, WebDriver driver)
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(element)));

		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void waitForElementToBeVisibleAndClickable(By element, WebDriver driver)
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));

		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void waitForElementToBeVisibleAndClickable(By element, WebDriver driver, Long waitInSeconds)
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
	}
	
	public void waitForElementToBeNotVisible(By element, WebDriver driver)
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));

		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void waitForTextToBePresentInElement(By element, String text, WebDriver driver)
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/*  Wait for elements to be visible */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public void waitForTextNotToBe(By element, String text, WebDriver driver)
	{
		this.driver=driver;
		for (int i = 1; i<=30; i++)
		{
			if (driver.findElement(element).getText().equals(text))
				i=30;
		}
	}
	
	public void switchBackToMainFrame(WebDriver driver)
	{
		/* Switch back to the main page */
		driver.switchTo().defaultContent();
	}
	
	public void switchToFrame(WebDriver driver, By by )
	{
		driver.switchTo().frame(driver.findElement(by));
	}

	public void scrollIntoView(WebDriver driver, String st)
	{
		String s = String.format("var topPos = document.querySelector(\""+st+"\"); topPos.scrollIntoView(false); ");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(s);
	}
	public boolean isElementDisplayedInstant(By by, WebDriver driver) 
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		boolean indicator;
		
		/*  Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try
		{
	      if (driver.findElement(by).isDisplayed())
	    	  indicator = true;
	      else
	    	  indicator = false;
	    } 
	    catch (NoSuchElementException e) 
	    {
	    	indicator =  false;
	    }
		
		/*  Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		return indicator;		
	}
	
	/**
	 * Wait for the number of windows open to be numberOfWindows
	 * @param numberOfWindows
	 */
	public void waitForNumberOfWindowsToEqual(final int numberOfWindows, WebDriver driver) 
	{
		this.driver=driver;
		
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
	    new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT"))) {
	    }.until(new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver driver) {                        
	            return (driver.getWindowHandles().size() == numberOfWindows);
	        }
	    });
	    
	    /* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
}
