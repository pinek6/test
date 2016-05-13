package haufe;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class LoadBrowser_WebDriver {
	
	protected WebDriver driver = null;

	 
	 /* Selenium Grid HUB URL */
	 public static String applicationURL = "http://www.umantis.com/";
	 public String currentBrowser;
	 public String version;
	 public String platform;
	 
	 int numberOfTimesToRetryInitialization = 3;
	 int currentRetry=0;
	 
	 /* Supported browsers are internet explorer, chrome, safari and firefox */
	 @Parameters("browser")
	 @BeforeMethod
	 public void launchBrowser(@Optional("FF") String browser) throws MalformedURLException {
	        if(browser.equalsIgnoreCase("chrome")){
	        	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Laptok\\workspace\\chromedriver.exe");
	            driver = new ChromeDriver();
	        } else if(browser.equalsIgnoreCase("FF")) {
	            driver = new FirefoxDriver();
	        } else if(browser.equalsIgnoreCase("IE")) {
	            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	            caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	            driver = new InternetExplorerDriver(caps);  
	        } else if(browser.equalsIgnoreCase("Opera"))  {
	            driver = new OperaDriver();
	        } else if(browser.equalsIgnoreCase("Safari")){
	            driver = new SafariDriver();
	        }

	        /* Add implicit wait */
	        driver.manage().timeouts().implicitlyWait(Long.parseLong("60"), TimeUnit.SECONDS);
	  }

	 
	 public void getHomePage()
	 {
		 
		 /* The home page should launch */
		 driver.get(applicationURL);		 
	 }
	 	 			
	@AfterMethod(groups={"BVT","FullRegression"}, alwaysRun = true)
	 public void closeBrowser() 
	 {
		/* Close the browser */
		if (driver != null)
			driver.quit();	 
	 }
}
