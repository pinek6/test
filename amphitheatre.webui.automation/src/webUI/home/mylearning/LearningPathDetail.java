package webUI.home.mylearning;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;

public class LearningPathDetail {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By courseTitle = By.cssSelector(".learningTitleCol1");
	private By percentComplete = By.cssSelector(".learningPercent");
	
	/* String Element Locator Constructors */
	private String module = "div[id^='sls_widgets_components_learning_LearningPathWidgetDetail_'] > div";
	private String moduleTwistie = " > div > div > a";
	private String moduleName = " > div > div > h3 > span:nth-child(2)";
	private String moduleButton = " > div > div > button";
	private String lesson = " > div:nth-child(2) > div";
	private String lessonName = " > h4";
	private String lessonViewSummary = " > div > a";
	private String lessonShowLessonPlan = " > div > ul > li > a";
	private String learningObjectList = " > div > div:nth-child(3)";
	private String learningObject = " > div > div:nth-child(3) > div";
	private String learningObjectName=" > h5 > a";
	private String learningObjectPercentageCorrect = " > div:nth-child(3)";
	
	public LearningPathDetail(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForPageToLoad()
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for elements to be visible  */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(percentComplete));
		wait.until(ExpectedConditions.presenceOfElementLocated(percentComplete));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	public String getCourseTitle()
	{
		return driver.findElement(courseTitle).getText();
	}
	
	public Boolean isCourseTitleDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(courseTitle, driver);
	}
	
	public Boolean isPercentCompleteDisplayed()
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(percentComplete, driver);
	}	
	
	public String getPercentComplete()
	{
		return driver.findElement(percentComplete).getText();
	}
	
	public int getPercentCompleteValue()
	{
		return Integer.parseInt(getPercentComplete().substring(getPercentComplete().indexOf(" ")).replace("%", "").replace(" ", ""));
	}
	
	public Boolean isNthModuleButtonDisplayed(int n)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(module+":nth-child("+n+")"+moduleButton), driver);
	}
	
	public void clickNthModuleButton(int n)
	{
		driver.findElement(By.cssSelector(module+":nth-child("+n+")"+moduleButton)).click();
	}
	
	public String getNthModuleButtonType(int n)
	{
		String buttonType = driver.findElement(By.cssSelector(module+":nth-child("+n+")"+moduleButton)).getAttribute("aria-label");
		if (buttonType.contains("continue"))
			buttonType="continue";
		else if (buttonType.contains("start"))
			buttonType="start";
		else
			buttonType="unknown button type";
		return buttonType;
	}
	
	public double getCountOfModules()
	{
		return driver.findElements(By.cssSelector(module)).size();
	}
	
	/**
	 * 
	 * @param n Module number
	 * @return
	 */
	public String getNthModuleStatus(int n)
	{
		String status = driver.findElement(By.cssSelector(module+":nth-child("+n+")")).getAttribute("class");
		if (status.contains("complete"))
			status="complete";
		else if (status.contains("resume"))
			status="resume";
		else if (status.contains("locked"))
			status="locked";
		else if (status.contains("start"))
			status="start";
		else 
			status="status unknown";
		return status;
	}
	
	public String getNthModuleName(int n )
	{
		return driver.findElement(By.cssSelector(module+":nth-child("+n+")"+moduleName)).getText();
	}
	
	public LearningPathDetail clickNthModuleTwistie(int n)
	{
		driver.findElement(By.cssSelector(module+":nth-child("+n+")"+moduleTwistie)).click();
		return this;
	}
	
	public int getCountOfLessonsInNthModule(int n)
	{
		return driver.findElements(By.cssSelector(module+":nth-child("+n+")"+ lesson)).size();
	}
	
	/**
	 * 
	 * @param n Module number
	 * @param m Lesson number
	 * @return
	 */
	public String getMthLessonStatusInNthModule(int n, int m)
	{
		String status = driver.findElement(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")")).getAttribute("class");
		if (status.contains("complete"))
			status="complete";
		else if (status.contains("resume"))
			status="resume";
		else if (status.contains("locked"))
			status="locked";
		else if (status.contains("start"))
			status="start";
		else 
			status="status unknown";
		return status;
	}
	
	public String getMthLessonNameInNthModule(int n, int m)
	{
		return driver.findElement(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + lessonName)).getText();	
	}
	
	public ViewSummary clickMthViewSummaryInNthModule(int n, int m)
	{
		driver.findElement(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + lessonViewSummary)).click();
		return new ViewSummary(driver);
	}
	
	public Boolean isMthViewSummaryLinkInNthModuleDisplayed(int n, int m)
	{
		return commonMethods.isElementDisplayed(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + lessonViewSummary),driver);
	}
	
	public LearningPathDetail clickMthShowHideLessonPlanLinkInNthModule(int n, int m)
	{
		if (driver.findElement(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + learningObjectList)).getAttribute("class").contains("hide"))
		{
			driver.findElement(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + lessonShowLessonPlan)).click();
			commonMethods.waitForElementToBeRefreshed(By.cssSelector(module+":nth-child("+n+")"+ lesson+ ":nth-child("+m+")"), driver);
			commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + learningObject+ ":nth-child("+(2)+")" + learningObjectName), driver);
		}
		else
		{
			driver.findElement(By.cssSelector(module+":nth-child("+n+")"+ lesson + ":nth-child("+m+")" + lessonShowLessonPlan)).click();
			commonMethods.waitForElementToBeRefreshed(By.cssSelector(module+":nth-child("+n+")"+ lesson+ ":nth-child("+m+")"), driver);
		}		
		return this;
	}
	
	/**
	 * 
	 * @param moduleNum Module number
	 * @param lessonNum Lesson number
	 * @param lessonObjectNum = Lesson Object number
	 * @return
	 */
	public String getOthLearningObjectStatusInMthLessonInNthModule(int moduleNum, int lessonNum, int lessonObjectNum)
	{
		String status = driver.findElement(By.cssSelector(module+":nth-child("+moduleNum+")"+ lesson + ":nth-child("+lessonNum+")" + learningObject+ ":nth-child("+(lessonObjectNum+1)+")")).getAttribute("class");
		if (status.contains("complete"))
			status="complete";
		else if (status.contains("resume"))
			status="resume";
		else if (status.contains("locked"))
			status="locked";
		else if (status.contains("start"))
			status="start";
		else 
			status="status unknown";
		return status;
	}
	
	/**
	 * 
	 * @param moduleNum Module number
	 * @param lessonNum Lesson number
	 * @param lessonObjectNum = Lesson Object number
	 * @return
	 */
	public String getOthLearningObjectNameInMthLessonInNthModule(int moduleNum, int lessonNum, int lessonObjectNum)
	{
		return driver.findElement(By.cssSelector(module+":nth-child("+moduleNum+")"+ lesson + ":nth-child("+lessonNum+")" + learningObject+ ":nth-child("+(lessonObjectNum+1)+")" + learningObjectName)).getText();
	}
	
	/**
	 * 
	 * @param moduleNum Module number
	 * @param lessonNum Lesson number
	 * @param lessonObjectNum = Lesson Object number
	 * @return
	 */
	public String getOthLearningObjectPercentageCorrectInMthLessonInNthModule(int moduleNum, int lessonNum, int lessonObjectNum)
	{
		String percentCompleteString = driver.findElement(By.cssSelector(module+":nth-child("+moduleNum+")"+ lesson + ":nth-child("+lessonNum+")" + learningObject+ ":nth-child("+(lessonObjectNum+1)+")" + learningObjectPercentageCorrect)).getText();
		return percentCompleteString.substring(0, percentCompleteString.indexOf("%"));
	}
	
	/**
	 * 
	 * @param moduleNum Module number
	 * @param lessonNum Lesson number
	 * @param lessonObjectNum = Lesson Object number
	 * @return
	 */
	public Boolean isOthLearningObjectNameInMthLessonInNthModuleDisplayed(int moduleNum, int lessonNum, int lessonObjectNum)
	{
		return commonMethods.isElementTextDisplayedAndNotEmpty((By.cssSelector(module+":nth-child("+moduleNum+")"+ lesson + ":nth-child("+lessonNum+")" + learningObject+ ":nth-child("+(lessonObjectNum+1)+")" + learningObjectName)),driver);
	}
	
	/**
	 * 
	 * @param moduleNum Module number
	 * @param lessonNum Lesson number
	 * @param lessonObjectNum = Lesson Object number
	 * @return
	 */
	public void clickOthLearningObjectNameInMthLessonInNthModule(int moduleNum, int lessonNum, int lessonObjectNum)
	{
		driver.findElement(By.cssSelector(module+":nth-child("+moduleNum+")"+ lesson + ":nth-child("+lessonNum+")" + learningObject+ ":nth-child("+(lessonObjectNum+1)+")" + learningObjectName)).click();
	}
	
	public int getCountOfLearningObjectsInMthLessonInNthModule(int moduleNum, int lessonNum)
	{
		return driver.findElements(By.cssSelector(module+":nth-child("+moduleNum+")"+ lesson + ":nth-child("+lessonNum+")" + learningObject)).size()-1;
	}
}
