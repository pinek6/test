package admin.login;

import org.openqa.selenium.By;

public class ConsoleLoginFields{
	public By usernameLocator = By.id("username");
	public By passwordLocator = By.id("password");
	public By loginButtonLocator = By.cssSelector("input[value='Login']");
	
	public ConsoleLoginFields()
	{
		
	}
	
	public By getUserNameLocator()
	{
		return usernameLocator;
	}
	
	public By getPasswordLocator()
	{
		return passwordLocator;
	}
	
	public By getLoginButtonLocator()
	{
		return loginButtonLocator;
	}
}


