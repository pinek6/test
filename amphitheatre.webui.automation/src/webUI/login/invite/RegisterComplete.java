package webUI.login.invite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;
import webUI.login.Login;

public class RegisterComplete {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);

	/* Element Locators */	
	private By continueButton = By.id("btnSubmit");

	public RegisterComplete(WebDriver driver) {
		this.driver = driver;
	}

	public Boolean isContinueButtonDisplayed() {
		return commonMethods.isElementDisplayed(continueButton, driver);
	}	

	public Login clickContinue() {
		driver.findElement(continueButton).click();
		return new Login(driver);
	}
}
