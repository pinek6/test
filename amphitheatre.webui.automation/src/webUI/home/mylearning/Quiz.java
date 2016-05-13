package webUI.home.mylearning;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webUI.CommonMethods;
import webUI.ConfigProperties;


public class Quiz {
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By headerLocator = By.cssSelector(".contentBody>div>h1");	
	private By iDontKnowButton = By.xpath("//input[@value=\"I don't know\"]");
	private By nextButton = By.cssSelector("input[value='next']");
	private By questionCount = By.cssSelector("div[class='quizQuestionCount formText1']");
	private By questionText = By.cssSelector("div[class='quizQuestionText formText3']");
	
	private String radiobuttonList = "ul[class='quizAnswerList'] > li";
	private String radiobuttonListRadioButton = " > input";
	private String radiobuttonListAnswerText = " > label";

	public Quiz(WebDriver driver)
	{
		this.driver=driver;
		waitForPageToLoad(driver);
	}
	
	public String getHeader()
	{
		return driver.findElement(headerLocator).getText();
	}

	public void waitForPageToLoad(WebDriver driver)
	{
		/* Load in properties */
		ConfigProperties config = new ConfigProperties();
		
		/* Set the implicit wait to zero */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		/* Wait for header to be updated */
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton));
		wait.until(ExpectedConditions.elementToBeClickable(iDontKnowButton));
		wait.until(ExpectedConditions.visibilityOfElementLocated(iDontKnowButton));
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionCount));
		wait.until(ExpectedConditions.elementToBeClickable(questionCount));
		wait.until(ExpectedConditions.visibilityOfElementLocated(questionCount));		
		wait.until(ExpectedConditions.presenceOfElementLocated(questionCount));
		
		SubwayMap m = new SubwayMap(driver);
		wait.until(ExpectedConditions.elementToBeClickable(m.arrow));
		wait.until(ExpectedConditions.visibilityOfElementLocated(m.arrow));		
		wait.until(ExpectedConditions.presenceOfElementLocated(m.arrow));
		
		/* Reset the implicit wait */
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getConfigProperties().getProperty("TIMEOUT")), TimeUnit.SECONDS);
	}
	
	/**
	 * This method clicks the "i don't know" button on a quiz question
	 */
	public Quiz clickIDontKnow()
	{
		driver.findElement(iDontKnowButton).click();
		commonMethods.waitForElementToBeRefreshed(questionCount, driver);
		return new Quiz(driver);
	}
	
	/**
	 * This method clicks the "i don't know" button on a quiz question on the last question in a quiz
	 */
	public QuizResult clickIDontKnowLastQuestion()
	{
		driver.findElement(iDontKnowButton).click();
		commonMethods.waitForElementToBeNotVisible(iDontKnowButton, driver);
		commonMethods.waitForElementToBeNotVisible(nextButton, driver);
		return new QuizResult(driver);
	}
	
	/**
	 * This method clicks the "next" button on a quiz question
	 * 
	 */
	public Quiz clickNext()
	{
		driver.findElement(nextButton).click();
		commonMethods.waitForElementToBeRefreshed(questionCount, driver);
		return new Quiz(driver);
	}
	
	/**
	 * This method clicks the "next" button on the last question in a quiz
	 * 
	 */
	public QuizResult clickNextLastQuestion()
	{
		driver.findElement(nextButton).click();
		commonMethods.waitForElementToBeNotVisible(iDontKnowButton, driver);
		commonMethods.waitForElementToBeNotVisible(nextButton, driver);
		return new QuizResult(driver);
	}
	
	public Boolean isNextButtonEnabled()
	{
		return driver.findElement(nextButton).getAttribute("aria-disabled").equals("true") ? false : true;
	}
	
	public Quiz selectNthRadioButton(int n)
	{
		driver.findElement(By.cssSelector(radiobuttonList+":nth-child("+n+")" + radiobuttonListRadioButton)).click();
		commonMethods.waitForElementToBeVisibleAndClickable(nextButton, driver);
		return this;		
	}

	public String getNthAnswerText(int n)
	{
		return driver.findElement(By.cssSelector(radiobuttonList+":nth-child("+n+")" + radiobuttonListAnswerText)).getText();
	}
	
	public int getCountOfAnswers()
	{
		return driver.findElements(By.cssSelector(radiobuttonList+radiobuttonListAnswerText)).size();
	}

	public void clickCorrectAnswer()
	{
		int numOfAnswers=getCountOfAnswers();
		for (int i =1; i<=getCountOfAnswers(); i++)
			if (getNthAnswerText(i).contains("[FVT_CORRECT"))
			{
				//System.out.println("Correct answer in row:" + i + ": "+getNthAnswerText(i));
				selectNthRadioButton(i);
				i=numOfAnswers;
			}				
	}
	
	public void clickIncorrectAnswer()
	{
		int numOfAnswers=getCountOfAnswers();
		int wrongAnswer;
		String questionText="";
		do
		{
			wrongAnswer=randInt(1,numOfAnswers);
			questionText=getNthAnswerText(wrongAnswer);
		}
		while (questionText.contains("[FVT_CORRECT"));
		
		selectNthRadioButton(wrongAnswer);
	}

	public String getQuestionCountText()
	{
		return driver.findElement(questionCount).getText();
	}
	
	public int getTotalCountOfQuestions()
	{
		String questionCountText = getQuestionCountText();
		return Integer.parseInt(questionCountText.substring(questionCountText.lastIndexOf(" ")+1));
	}
	
	public String getQuestionText()
	{
		return driver.findElement(questionText).getText();
	}
	
	public int getQuestionNumberFromQuestionText()
	{
		String questionText = getQuestionText();
		int locationOfHyphen=questionText.indexOf(":");
		int questionNum = Integer.parseInt(questionText.substring(1, locationOfHyphen));
		return questionNum;
	}
	
	public Boolean isNextButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(nextButton, driver);
	}
	
	public Boolean isIDontKnowButtonDisplayed()
	{
		return commonMethods.isElementDisplayed(iDontKnowButton, driver);
	}

	public int getNumberOfAnswers()
	{
		return driver.findElements(By.cssSelector(radiobuttonList)).size();
	}
	
	public int getAnswerNumber(int n)
	{
		String s = driver.findElement(By.cssSelector(radiobuttonList+":nth-child("+n+")"+radiobuttonListAnswerText)).getText();
		String l=s.replace("FVT_CORRECT", "").substring(s.indexOf("[")).replace("[, ","[");		
		return Integer.parseInt(l.substring(l.indexOf("[")+1,l.indexOf(" ")-1));
	}
	
	public int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
