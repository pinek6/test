package webUI.home.mylearning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class SubwayMap {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By subwayMap = By.cssSelector("div[class='learningDropdownTab']");
	private By courseName = By.cssSelector("span[class='learningDropdownTabTitle']");
	private By percentComplete = By.cssSelector("span[class='learningPercent learningDropdownPercent']");
	public By arrow = By.cssSelector("span[class='learningDropdownOpenIcon']");
	
	private String moduleList = ".learningDropdownModuleList > div";
	private String moduleListName = " > div > div >  h3 > span:nth-child(2)";
	private String moduleListTwistie = " > div > div > a";
	
	private String lessonList = " > div:nth-child(2) > div";
	private String lessonListTitle = " > h4";
	private String lessonListViewSummary = " > div > a";
	private String lessonListShowHideLessonPlan = " > div > ul > li > a";
	
	private String learningObject = " > div > div:nth-child(3) > div";
	private String learningObjectTitle = " > h5 > a";
	private String learningObjectList = " > div > div:nth-child(2)";
	private String learningObjectInformation = " > div:nth-child(3)";
	
	public SubwayMap(WebDriver driver)
	{
		this.driver=driver;
		waitForMapToLoad();
	}
	
	public void waitForMapToLoad()
	{	
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.elementToBeClickable(subwayMap));
		wait.until(ExpectedConditions.visibilityOfElementLocated(subwayMap));
		wait.until(ExpectedConditions.presenceOfElementLocated(subwayMap));	

		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
		
		commonMethods.switchBackToMainFrame(driver);
	}

	public Boolean isSubwayMapDisplayed()
	{
		return commonMethods.isElementDisplayed(subwayMap, driver);
	}

	public Boolean isCourseNameDisplayed()
	{
		return commonMethods.isElementDisplayed(courseName, driver);
	}

	public Boolean isPercentCompleteDisplayed()
	{
		return commonMethods.isElementDisplayed(percentComplete, driver);
	}

	public Boolean isOpenArrowDisplayed()
	{
		return commonMethods.isElementDisplayed(arrow, driver);
	}
	
	public SubwayMap clickOpenArrow()
	{
		driver.findElement(arrow).click();
		commonMethods.waitForElementToBeVisibleAndClickable(By.cssSelector(moduleList), driver);
		return this;
	}
	
	public SubwayMap clickCloseArrow()
	{
		driver.findElement(arrow).click();
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(moduleList), driver);
		return this;
	}
	
	public String getCourseName()
	{
		return driver.findElement(courseName).getText();
	}
	
	public String getPercentComplete()
	{
		return driver.findElement(percentComplete).getText().replace("%", "");
	}
	
	public int getPercentCompleteValue()
	{
		return Integer.parseInt(driver.findElement(percentComplete).getText().replace("%", ""));
	} 
	
	public int getCountOfModules()
	{
		return driver.findElements(By.cssSelector(moduleList)).size();
	}
	
	public String getNthModuleName(int n)
	{
		String s = moduleList+":nth-child("+n+")" + moduleListName;
		commonMethods.scrollIntoView(driver, s);
		return driver.findElement(By.cssSelector(s)).getText().trim();
	}
	
	public List<String> getAllModuleNames()
	{
		List<String> l = new ArrayList<String>();

		int countOfModules = getCountOfModules();
        for (int i = 1; i <= countOfModules; i++) {
        	l.add(getNthModuleName(i));
        }	   
        
		return l;
	}
	
	public String getNthModuleStatus(int n)
	{
		String s = moduleList+":nth-child("+n+")";
		commonMethods.scrollIntoView(driver, s);
		String status = driver.findElement(By.cssSelector(s)).getAttribute("class");
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
	
	public SubwayMap clickNthModuleTwistie(int n)
	{
		String s = String.format(moduleList+":nth-child("+n+")" + moduleListTwistie);
		commonMethods.scrollIntoView(driver, s);
		driver.findElement(By.cssSelector(moduleList+":nth-child("+n+")" + moduleListTwistie)).click();
		return this;
	}
	
	public String getMthLessonTitleOfNthModule(int n, int m)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + lessonListTitle;
		commonMethods.scrollIntoView(driver, s);
		return driver.findElement(By.cssSelector(s)).getText();
	}
	
	public void clickMthLessonViewSummaryOfNthModule(int n, int m)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + lessonListViewSummary;
		commonMethods.scrollIntoView(driver, s);
		driver.findElement(By.cssSelector(s)).click();
		//return new ViewSummary(driver);
	}
	
	public Boolean isMthLessonViewSummaryOfNthModuleDisplayed(int n, int m)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + lessonListViewSummary;
		commonMethods.scrollIntoView(driver, s);
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(s), driver);
		return found;
	}
	
	public String getMthLessonStatusOfNthModule(int n, int m)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList;
		commonMethods.scrollIntoView(driver, s);
		String status =  driver.findElement(By.cssSelector(s)).getAttribute("class");
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
	
	public SubwayMap clickMthShowLessonPlanOfNthModule(int n, int m)
	{
		String s = String.format(moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + lessonListShowHideLessonPlan);
		By showLink = By.cssSelector(s);		
		commonMethods.scrollIntoView(driver, s);
		driver.findElement(showLink).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObjectList),driver);
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject + ":nth-child(2)"),driver);
		return this;
	}
	
	public SubwayMap clickMthHideLessonPlanOfNthModule(int n, int m)
	{
		String s = String.format(moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + lessonListShowHideLessonPlan);
		By hideLink = By.cssSelector(s);
		commonMethods.scrollIntoView(driver, s);
		driver.findElement(hideLink).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObjectList),driver);
		commonMethods.waitForElementToBeNotVisible(By.cssSelector(moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject + ":nth-child(2)"),driver);
		return this;
	}
	
	public int getCountOfLessonsInNthModule(int n)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList;
		commonMethods.scrollIntoView(driver, s);
		return driver.findElements(By.cssSelector(s)).size();
	}
	
	public int getCountOfLearningObjectsInMthLessonOfNthModule(int n, int m)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject;
		commonMethods.scrollIntoView(driver, s);
		return driver.findElements(By.cssSelector(s)).size()-1;
	}			
	
	public String getOthLearningObjectNameOfMthLessonOfNthModule(int n, int m, int o)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject + ":nth-child("+(o+1)+")" + learningObjectTitle;
		commonMethods.scrollIntoView(driver, s);
		return driver.findElement(By.cssSelector(s)).getText();
	}
	
	public void clickOthLearningObjectNameOfMthLessonOfNthModule(int n, int m, int o)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject + ":nth-child("+(o+1)+")" + learningObjectTitle;
		By title = By.cssSelector(s);
		commonMethods.scrollIntoView(driver, s);
		driver.findElement(title).click();
	}
	
	/**
	 * 
	 * @param n
	 * @param m
	 * @param o
	 * @return The %Complete for a learning object or the Avg Time
	 */
	public String getOthLearningObjectInformationOfMthLessonOfNthModule(int n, int m, int o)
	{

		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject + ":nth-child("+(o+1)+")" + learningObjectInformation;
		commonMethods.scrollIntoView(driver, s);
		return driver.findElement(By.cssSelector(s)).getText();		
	}
	
	public int getPercentCorrectOfOthLearningObjectOfMthLessonOfNthModule(int n, int m, int o)
	{
		return Integer.parseInt(getOthLearningObjectInformationOfMthLessonOfNthModule(n,m,o).substring(0,getOthLearningObjectInformationOfMthLessonOfNthModule(n,m,o).indexOf("%")));
	}
	
	public String getOthLearningObjectStatusOfMthLessonOfNthModule(int n, int m, int o)
	{
		String s = moduleList+":nth-child("+n+")" + lessonList + ":nth-child("+m+")" + learningObject + ":nth-child("+(o+1)+")";
		commonMethods.scrollIntoView(driver, s);
		String status =  driver.findElement(By.cssSelector(s)).getAttribute("class");
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
}
