package admin.contentAdministration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class ContentAdministration {
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By contentFrame = By.xpath("//frame[@title='Portal Main']");
	private By header = By.id("page_title");
	private By firstTab = By.cssSelector("table[class='navigate_tab'] > tbody > tr > td:nth-child(2) > a");
	
	public ContentAdministration(WebDriver driver)
	{
		this.driver=driver;
	}	
	
	public String getHeader()
	{
		commonMethods.switchToFrame(driver, contentFrame);
		String headerText = driver.findElement(header).getText();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return headerText;
	}

	public ContentAdministration clickFirstTab()
	{
		commonMethods.switchToFrame(driver, contentFrame);
		driver.findElement(firstTab).click();
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver, rightnavframe);
		return this;
	}
	
	
}