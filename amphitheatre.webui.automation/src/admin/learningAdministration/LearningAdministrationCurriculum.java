package admin.learningAdministration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class LearningAdministrationCurriculum  {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By coursesFrame = By.cssSelector("iframe[name='fraReqCourse']");
	private By enrol = By.name("imgEnroll");
	private By back = By.name("imgBack");
	private By OKbutton = By.id("button1");
	
	/* String Element Locator Constructors */
	private String moduleList = "form[id='frmCourse'] > table > tbody > tr";
	private String moduleListCourseTitle = " > td > table > tbody > tr > td";
	private String moduleListSequence = "";
	private String moduleListRequired = "";
	private String moduleListOfferings = "";
	
	public LearningAdministrationCurriculum(WebDriver driver)
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
	
	public LearningAdministrationCurriculum clickEnrol()
	{
		/* Get the count of current windows open */
		int countOfCurrentWindows=driver.getWindowHandles().size();	

		/* Click enrol */
		driver.findElement(enrol).click();	
		
		/* Wait until one more window is open */
		commonMethods.waitForNumberOfWindowsToEqual(countOfCurrentWindows+1, driver);	
		
		String currentWindowHandle=driver.getWindowHandle();   
		Set<String> openWindowsList=driver.getWindowHandles();
        for(String windowHandle:openWindowsList)
        {
        	if (!windowHandle.equals(currentWindowHandle))
        	{
        		if (driver.switchTo().window(windowHandle).getTitle().equals("Information"))
        		driver.close();
        	}
        }
	
		driver.switchTo().window(currentWindowHandle);		
		return this;
	}
	
	public EnrolMemberSearch clickBack()
	{
		driver.findElement(back).click();		
		return new EnrolMemberSearch(driver);
	}
	
	public EnrolMemberSearch clickOKButtonOnPopup() throws InterruptedException
	{	
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		String popup="";
		
		Thread.sleep(4000);  // wait for popup to appear
		
		for (String winHandle : driver.getWindowHandles()){
			   driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			   System.out.println(driver.getTitle() + "--" + winHandle);
			   if (driver.getTitle().equals("Information"))
				   popup=winHandle;
			   
			}
		System.out.println("Selected ->" + popup);

		driver.switchTo().window(popup);
		
		//driver.close();
		driver.findElement(OKbutton).click();
		
		driver.switchTo().window(parentHandle); // switch back to the original window		
		return new EnrolMemberSearch(driver);

	}

	public int getCountOfModules()
	{
		commonMethods.switchToFrame(driver,coursesFrame);
		int count = driver.findElements(By.cssSelector(moduleList)).size()-1;		
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return count;
	}
	
	public String getNthModuleCourseTitle(int n)
	{
		commonMethods.switchToFrame(driver,coursesFrame);
		String title = driver.findElement(By.cssSelector(moduleList + ":nth-child("+(n+1)+")" + moduleListCourseTitle)).getText().trim();		
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return title;
	}
	
	public List<String> getAllCourseTitles()
	{
		List<String> l = new ArrayList<String>();

		int countOfModules = getCountOfModules();
        for (int i = 1; i <= countOfModules; i++) {
        	l.add(getNthModuleCourseTitle(i));
        }	   
        
		return l;
	}
	
	
}
