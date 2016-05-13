package webUI;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import report.RetryAnalyzer;
import webUI.home.Home;
import webUI.login.Login;
import admin.login.ConsoleLogin;
import admin.login.ConsoleLoginAdministration;
import admin.navigation.TopNav;

public class LoadBrowser {
	
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	 /* Read in the configuration file */
	 ConfigProperties config = new ConfigProperties();
	 
	 /* Selenium Grid HUB URL */
	 private String applicationURL = config.getConfigProperties().getProperty("URL") + "webUI";
	 private String hubURL = config.getConfigProperties().getProperty("GRID_URL");
	 
	 /* Variables */
	 String appTitle = new LocaleStrings().getString("APP_TITLE");
  	 String homePageHeader = new LocaleStrings().getString("HOME_PAGE_HEADER");
	 String username = config.getConfigProperties().getProperty("DOCTOR_USERNAME");
	 String password = config.getConfigProperties().getProperty("DOCTOR_PASSWORD");
	 
	 public String currentBrowser;
	 public String version;
	 public String platform;
	 
	 int numberOfTimesToRetryInitialization = 3;
	 int currentRetry=0;
	 
	 /* Supported browsers are internet explorer, chrome, safari and firefox */
	 @Parameters({"browser","version","platform"})
	 @BeforeMethod(groups={"BVT","FullRegression","test","UAT-BVT"})
	 public synchronized void setUp( @Optional("chrome") String browser, @Optional("")String version, @Optional("")String platform) throws MalformedURLException 
	 { 		 	
		 	this.currentBrowser=browser.toLowerCase();
		 	this.version=version.toLowerCase();
		 	this.platform=platform.toLowerCase();		 	
		 	
		 	threadDriver = new ThreadLocal<RemoteWebDriver>();    
	        DesiredCapabilities dc = new DesiredCapabilities();	       
	        
	        /* Set platform, if required */
	        if (!platform.equals(""))
	        {
	        	if (platform.equals("WIN7"))	        	
	        		dc.setPlatform(Platform.WINDOWS);
	        	else if (platform.equals("MAC"))
	        		dc.setPlatform(Platform.MAC);
	        	else if (platform.equals("WIN8"))
	        		dc.setPlatform(Platform.WIN8);
	        	else
	        		dc.setPlatform(Platform.WINDOWS);
	        }	 	        
	        
	        /* Allowed browser values are chrome, firefox and internet explorer , safari*/
	        dc.setBrowserName(browser);
	        
	        /* For internet explorer, ignore security domains to prevent UnreachableBrowserException error */
	        if (browser.equals("internet explorer"))
	        	dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	        
	        /* Set browser version, if required */
	        if (!version.equals(""))
	        	dc.setVersion(version);        
	        
	        /* Retry launching the driver n times
		 	 * This prevents the error in firefox
		 	 * Unable to bind to locking port 7054 within 45000 ms */
	        currentRetry=0;
		 	while (currentRetry<numberOfTimesToRetryInitialization)
		 	{
		 		try {
		 			threadDriver.set(new RemoteWebDriver(new URL(hubURL), dc));
		 			if (currentRetry!=0)
		 				Reporter.log("Successfully initialized driver on retry");
		 			currentRetry=numberOfTimesToRetryInitialization;		 			
		 		}
		 		catch(Exception e) {
		 			currentRetry++;
		 			Reporter.log(e.getMessage());
		 			/* Wait 60 seconds */
		 			try {
		 				Reporter.log("Waiting 60 seconds to retry initializing driver");
						Thread.sleep(60000);
						Reporter.log("Retrying to initialize driver");
					} catch (InterruptedException t) {
						t.printStackTrace();						
					}
		 		}		 		
		 	}
	        
	        if (platform.equals("MAC"))
	        {
	        	java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        	int width = (int)screenSize.getWidth();
	        	int height = (int)screenSize.getHeight();
	        	Point p = new Point(0,0);
	        	Dimension d = new Dimension(width,height);
	        	getDriver().manage().window().setPosition(p);
	        	getDriver().manage().window().setSize(d);
	        }
	        else
	        	getDriver().manage().window().maximize();
	
	        /* Add implicit wait */
	        getDriver().manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	 }
	 
	 public WebDriver getDriver() 
	 {
			return threadDriver.get();
	 }
	 
	 public synchronized void logIntoAdminConsole()
	 {
		ConsoleLogin l = new ConsoleLogin(getDriver());

		/* Click certificate error */
     	if (currentBrowser.equals("internet explorer") && getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}   
		
		l.enterUsername(username);
		l.enterPassword(password);
		ConsoleLoginAdministration c = l.clickLogin();
		TopNav topNav = new TopNav(getDriver());
		
		//Assert.assertEquals(c.getTitle(), appTitle);
		Assert.assertTrue(topNav.isLogoutDisplayed());
	 }
	 
	 public synchronized void getDriverAndLogin() 
	{		
 		/* Launch the login page */
     	Login loginPage = new Login(getDriver());

     	/* Click certificate error */
     	if (currentBrowser.equals("internet explorer") && getDriver().getTitle().contains("Certificate Error"))
     	{
     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
     	}     	
     	
     	/* Check if the username field appears, if it doesn't, click Refresh */
     	if (loginPage.isPasswordFieldDisplayed()==false)
     		getDriver().navigate().refresh();
     	
     	/* Enter a username and password */     	
		loginPage.enterUsername(username);     	
		loginPage.enterPassword(password);
     	
     	/* Click Login */
     	loginPage.clickLogin();
   	
     	/* The home page should launch */
     	Home homePage = new Home(getDriver());
     	
     	/* Verify the ui elements appears */
//		Assert.assertTrue(homePage.isLibraryPanelDisplayed(),"Library panel on home page failed to load");
//		Assert.assertTrue(homePage.isGroupsPanelDisplayed(),"Groups panel on home page failed to load");
//		Assert.assertTrue(homePage.isMyLearningPanelDisplayed(),"My Learning panel on home page failed to load");
		Assert.assertTrue(homePage.isColumn1Displayed(), "Column 1 on the home page failed to load");
		
		/* Declare a navigation variable */
    	Navigation navigation = new Navigation(getDriver());
	}
	 
	 	public synchronized void getDriverAndLogin(String username, String password) 
		{		
	 		/* Launch the login page */
	     	Login loginPage = new Login(getDriver());

	     	/* Click certificate error */
	     	if (currentBrowser.equals("internet explorer") && getDriver().getTitle().contains("Certificate Error"))
	     	{
	     		getDriver().navigate().to("javascript:document.getElementById('overridelink').click()");
	     	}     	
	     	
	     	/* Check if the username field appears, if it doesn't, click Refresh */
	     	if (loginPage.isPasswordFieldDisplayed()==false)
	     		getDriver().navigate().refresh();
	     	
	     	/* Enter a username and password */     	
			loginPage.enterUsername(username);     	
			loginPage.enterPassword(password);
	     	
	     	/* Click Login */
	     	loginPage.clickLogin();
	   	
	     	/* The home page should launch */
	     	Home homePage = new Home(getDriver());
	     	
	     	/* Verify the ui elements appears */
//			Assert.assertTrue(homePage.isLibraryPanelDisplayed(),"Library panel on home page failed to load");
//			Assert.assertTrue(homePage.isGroupsPanelDisplayed(),"Groups panel on home page failed to load");
//			Assert.assertTrue(homePage.isMyLearningPanelDisplayed(),"My Learning panel on home page failed to load");
			Assert.assertTrue(homePage.isColumn1Displayed(), "Column 1 on the home page failed to load");
		}

	//@BeforeSuite(groups={"BVT","FullRegression"}, alwaysRun = true)
	public void getBuildVersion() throws MalformedURLException, FileNotFoundException, UnsupportedEncodingException
	{
		DesiredCapabilities dc = new DesiredCapabilities(); 
		dc.setBrowserName("chrome");
        threadDriver = new ThreadLocal<RemoteWebDriver>();
        threadDriver.set(new RemoteWebDriver(new URL(hubURL), dc));		
        
        getDriver().manage().window().maximize();
        if (!applicationURL.startsWith("https://hq.app"))
        {
	        getDriver().navigate().to(applicationURL + "/buildVersion.txt");
	        String version=getDriver().findElement(By.cssSelector("body>pre")).getText();
			
			PrintWriter writer = new PrintWriter("buildVersion.txt", "UTF-8");
			writer.println(version);
			writer.close();
        }
		getDriver().quit();	
	}
	
	@BeforeTest(groups={"BVT","FullRegression","test"}, alwaysRun = true)
	 public void beforeSuite(ITestContext context) {
	     for (ITestNGMethod method : context.getAllTestMethods()) {
	         method.setRetryAnalyzer(new RetryAnalyzer());
	     }
	 }
		
	@AfterMethod(groups={"BVT","FullRegression"}, alwaysRun = true)
	 public void closeBrowser() 
	 {
		/* Close the browser */
		if (getDriver() != null)
			getDriver().quit();	 
	 }
	
	@AfterSuite(groups={"BVT","FullRegression"})
	public void afterSuite()
	{
		/* After each suite, delete the existing emailable-report and 
		 * wait for the new report to be created
		 */
		File filePath = new File("test-output/emailable-report.html");
		if (filePath.exists())
			filePath.delete();  		
	}

}
