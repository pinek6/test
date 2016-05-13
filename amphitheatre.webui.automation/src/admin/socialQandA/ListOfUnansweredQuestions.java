package admin.socialQandA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class ListOfUnansweredQuestions {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By header = By.cssSelector(".lotusHeader > h1");

	private By show10 = By.cssSelector(".pag_bottom > td > div > ul > li > a");
	
	/* String Element Constructors */
	private String listOfUnansweredQuestionsTable = "div[class='list_results'] > table > tbody > tr";
	private String listOfUnansweredQuestionsTableRemind = " > td:nth-child(2) > a";
	private String listOfUnansweredQuestionsTableSubject = " > td:nth-child(3)";
	private String listOfUnansweredQuestionsTableSubjectArea = " > td:nth-child(4)";
	private String listOfUnansweredQuestionsTableQuestionDate = " > td:nth-child(5)";
	
	public ListOfUnansweredQuestions(WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public String getHeader()
	{
		String headerText = driver.findElement(header).getText();
		return headerText;
	}

	public Boolean isListOfUnansweredQuestionsTableDisplayed()
	{
		Boolean found = commonMethods.isElementDisplayed(By.cssSelector(listOfUnansweredQuestionsTable), driver);
		return found;
	}

	public void clickNthUnansweredQuestionRemind(int n)
	{
		driver.findElement(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableRemind)).click();
	}
	
	public String getNthUnansweredQuestionSubject(int n)
	{
		String found = driver.findElement(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableSubject)).getText();
		return found;
	}

	public String getNthUnansweredQuestionSubjectArea(int n)
	{
		String found = driver.findElement(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableSubjectArea)).getText();
		return found;
	}

	public String getNthUnansweredQuestionQuestionDate(int n)
	{
		String found = driver.findElement(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableQuestionDate)).getText();
		return found;
	}
	
	public int getCountOfUnansweredQuestionsDisplayed()
	{
		int found = driver.findElements(By.cssSelector(listOfUnansweredQuestionsTable)).size()-3;
		return found;
	}
	
	public Boolean isNthRemindLinkDisplayed(int n)
	{
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableRemind), driver);
		return found;
	}
	
	public Boolean isNthSubjectDisplayed(int n)
	{
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableSubject), driver);
		return found;
	}
	
	public Boolean isNthSubjectAreaDisplayed(int n)
	{
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableSubjectArea), driver);
		return found;
	}
	
	public Boolean isNthQuestionDateDisplayed(int n)
	{
		Boolean found = commonMethods.isElementTextDisplayedAndNotEmpty(By.cssSelector(listOfUnansweredQuestionsTable+":nth-child("+(n+2)+")"+listOfUnansweredQuestionsTableQuestionDate), driver);
		return found;
	}
	
	public ListOfUnansweredQuestions clickShow10()
	{
		driver.findElement(show10).click();
		commonMethods.waitForElementToBeRefreshed(By.cssSelector(listOfUnansweredQuestionsTable), driver);
		return this;
	}
	
}