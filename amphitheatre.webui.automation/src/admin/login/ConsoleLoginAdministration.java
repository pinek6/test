package admin.login;

import org.openqa.selenium.WebDriver;


public class ConsoleLoginAdministration {

	private WebDriver driver;
	
	public ConsoleLoginAdministration(WebDriver driver)
	{	
		this.driver=driver;
	}
	
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	
}
