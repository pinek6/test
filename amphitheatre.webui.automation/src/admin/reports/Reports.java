package admin.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class Reports {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By header = By.cssSelector(".headerTitle");
	
	public Reports(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeader()
	{
		String headerText = driver.findElement(header).getText();
		return headerText;
	}
}
