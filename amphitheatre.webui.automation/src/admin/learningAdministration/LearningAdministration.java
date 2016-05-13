package admin.learningAdministration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class LearningAdministration {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By header = By.id("page_title");
	private By goButton = By.id("ListMember");
	
	/* TABS */
	private By home = By.cssSelector("a[title='Home']") ;
	private By curricula = By.cssSelector("a[title='Curricula']") ;
	private By courses = By.cssSelector("a[title='Courses']") ;
	
	public LearningAdministration(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		String headerText = driver.findElement(header).getText();
		return headerText;
	}
	
	public void clickHomeTab()
	{
		driver.findElement(home).click();
		commonMethods.waitForElementToBeRefreshed(goButton, driver);
	}
	
	public LearningAdministrationCurricula clickCurriculaTab()
	{
		driver.findElement(curricula).click();
		commonMethods.waitForElementToBeRefreshed(goButton, driver);
		return new LearningAdministrationCurricula(driver);
	}
	
	public LearningAdministrationCourses clickCoursesTab()
	{
		driver.findElement(courses).click();
		commonMethods.waitForElementToBeRefreshed(goButton, driver);
		return new LearningAdministrationCourses(driver);
	}
}
