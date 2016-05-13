package webUI.home.peopleSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class ImFollowing extends PeopleSearch{
	
	private WebDriver driver;
	private CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By recommendedPeoplePanel = By.cssSelector(".columnsRightBody");
	
	public ImFollowing(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public Boolean isRecommendedPeoplePanelDisplayed()
	{
		return commonMethods.isElementDisplayed(recommendedPeoplePanel, driver);
	}
}
