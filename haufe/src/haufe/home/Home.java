package haufe.home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import haufe.CommonMethods;

public class Home{
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	public static String applicationURL = "http://www.umantis.com/";
	
	/* Element Locators */
	private By langDropDown = By.cssSelector("[class='meta-nav'] > [class='dropdown']");
	private String langDropDownOptions = "[class='meta-nav'] > [class*='dropdown'] > ul > li";
	private By slogan = By.cssSelector("[class='jumbotron__slogan']");
	
	public Home(WebDriver driver)
	{	
		/* Read in the configuration file */
		this.driver=driver;
		driver.navigate().to(applicationURL);	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong("60"), TimeUnit.SECONDS); 
	}
	
	public Home clickLangDropdown(){
		
		WebElement elems = driver.findElement(langDropDown);
		
		elems.click();
		return this;
	}

	public Home selectLanguage(String lang){
		
		int langCount = driver.findElements(By.cssSelector(langDropDownOptions)).size();
		
		for(int  i=1;i<=langCount;i++)
		{
			WebElement currrentElem = driver.findElement(By.cssSelector(langDropDownOptions+":nth-child("+i+")"));
			
			if(currrentElem.getText().contains(lang))
			{
				currrentElem.click();
				break;
			}
				
		}
		
		//Console.log(langCount);
		return this;		
	}

	public String getSloganText(){
		
		return driver.findElement(slogan).getText();
	}
}
