package admin.communications.announcements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class UploadImage {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	//private By browse = By.cssSelector("input[class='file']");
	private By browse = By.xpath("//input[@type='file']");
	private By upload = By.cssSelector("input[value='Upload']");
	private By cancel = By.cssSelector("input[value='Cancel']");
	
	public UploadImage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public UploadImage clickUpload()
	{
		
		
		driver.findElement(upload).click();
		
		return this;
	}
	
	public UploadImage clickCancel()
	{
		
		
		driver.findElement(cancel).click();
		
		return this;
	}
	
	public UploadImage enterImage(String imageLocation)
	{
		
		
		driver.findElement(browse).sendKeys(imageLocation);
		
		return this;
	}
}
