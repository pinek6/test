package admin.learningAdministration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class LearningAdministrationHome extends LearningAdministration {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By resultsIframe = By.id("fraUserList");
	private By searchFor = By.id("KeyWord");
	private By go = By.id("ListMember");
	
	/* String Element Constructors */
	private String membersTable = "table[id='tblHeader'] > tbody > tr";
	private String membersTableCheckbox = " > td > input";
	private String membersTableName = "> td:nth-child(2) > a";
	private String membersTableStatus = "> td:nth-child(3)";
	
	public LearningAdministrationHome(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public LearningAdministrationHome enterSearchFor(String text)
	{
		
		
		driver.findElement(searchFor).clear();
		driver.findElement(searchFor).sendKeys(text);
		
		return new LearningAdministrationHome(driver);
	}
	
	public LearningAdministrationHome clickGo()
	{
		
		
		driver.findElement(go).click();
		
		return new LearningAdministrationHome(driver);
	}
	
	public LearningAdministrationHome clickNthCheckboxInMembersResults(int n)
	{
		
		
		commonMethods.switchToFrame(driver, resultsIframe);
		driver.findElement(By.cssSelector(membersTable+":nth-child("+(n+1)+")"+membersTableCheckbox)).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return new LearningAdministrationHome(driver);
	}
	
	public MemberInformation clickNthNameInMembersResults(int n)
	{
		
		
		commonMethods.switchToFrame(driver, resultsIframe);
		driver.findElement(By.cssSelector(membersTable+":nth-child("+(n+1)+")"+membersTableName)).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return new MemberInformation(driver);
	}
	
	public String getNthStatusInMembersResults(int n)
	{
		
		
		commonMethods.switchToFrame(driver, resultsIframe);
		String status = driver.findElement(By.cssSelector(membersTable+":nth-child("+(n+1)+")"+membersTableStatus)).getText();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return status;
	}
}
