package admin.learningAdministration;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class MemberInformation {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By fraCurrentAssign = By.xpath("//iframe[@id='fraCurrentAssign']");
	private By enrol = By.name("imgEnroll");
	private By back = By.name("imgBack");
	private By withdraw = By.name("imgAddOffering");
	private By OKbutton = By.id("button1");
	
	/* String Element Constructors */
	private String currentEnrollmentsSearchResults = "table[id='tblGrid'] > tbody > tr";
	private String checkbox = " > td > input";
	private String title = " > td:nth-child(2) > a";
	
	public MemberInformation(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public EnrolMemberSearch clickEnrol()
	{
		
		
		driver.findElement(enrol).click();
		
		return new EnrolMemberSearch(driver);
	}
	
	public LearningAdministrationHome clickBack()
	{
		
		
		driver.findElement(back).click();
		
		return new LearningAdministrationHome(driver);
	}
	
	public MemberInformation clickWithdraw()
	{
		
		
		driver.findElement(withdraw).click();
		String currentWindowHandle=driver.getWindowHandle();   
		Set<String> openWindowsList=driver.getWindowHandles();        
        for(String windowHandle:openWindowsList)
        {
        	if (!windowHandle.equals(currentWindowHandle))
        	{
        		driver.switchTo().window(windowHandle);
        		if (commonMethods.isElementPresent(OKbutton, driver))
        			driver.findElement(OKbutton).click();
        	}
        }
	
		driver.switchTo().window(currentWindowHandle);
		
		return new MemberInformation(driver);
	}
	
	public int getCountOfCurrentEnrollments()
	{
				
		
		commonMethods.switchToFrame(driver, fraCurrentAssign);
		int currentEnrollments = driver.findElements(By.cssSelector(currentEnrollmentsSearchResults+checkbox)).size()-1;
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return currentEnrollments;
	}
	
	public MemberInformation selectNthEnrollment(int n)
	{
		
		
		commonMethods.switchToFrame(driver, fraCurrentAssign);
		driver.findElement(By.cssSelector(currentEnrollmentsSearchResults+":nth-child("+(n+1)+")"+checkbox)).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return new MemberInformation(driver);
	}
	
	public MemberInformation clickNthEnrollmentTitle(int n)
	{
		
		
		commonMethods.switchToFrame(driver, fraCurrentAssign);
		driver.findElement(By.cssSelector(currentEnrollmentsSearchResults+":nth-child("+(n+1)+")"+title)).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return new MemberInformation(driver);
	}
	
	public String getNthEnrollmentTitle(int n)
	{
		
		
		commonMethods.switchToFrame(driver, fraCurrentAssign);
		String enrolledTitle = driver.findElement(By.cssSelector(currentEnrollmentsSearchResults+":nth-child("+(n+1)+")"+title)).getText();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return enrolledTitle;
	}
	
}
