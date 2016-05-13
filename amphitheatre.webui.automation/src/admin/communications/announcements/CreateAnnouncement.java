package admin.communications.announcements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import webUI.CommonMethods;

public class CreateAnnouncement {
	
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	
	/* Element Locators */
	private By rightnavframe = By.cssSelector("frame[name='frame_right']");
	private By ckeditor = By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']");
	private By announcementCreatedSuccessfully = By.cssSelector(".result_message_item");
	private By validationMessage = By.cssSelector(".error_message_box");
	private By internalTitle = By.id("title");
	private By type = By.id("typeCode");
	private By html = By.cssSelector("body[class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']");
	private By displayOrder = By.id("sortOrder");
	private By uploadImageButton = By.name("Upload");
	private By uploadedImage = By.cssSelector("img[title='Uploaded image']");
	private By status = By.id("stagingId");
	private By autoActiveMonth = By.name("activemonth");
	private By autoActiveDay = By.name("activeday");
	private By autoActiveYear = By.name("activeyear");
	private By autoArchiveMonth = By.name("archivemonth");
	private By autoArchiveDay = By.name("archiveday");
	private By autoArchiveYear = By.name("archiveyear");
	private By submit = By.id("update");
	private By save = By.id("save");
	private By cancel = By.cssSelector(".input_button cancel_button");
	
	public CreateAnnouncement(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public Boolean isAnnouncementSavedSuccessfully()
	{
		boolean found = false;
		
		
		found = commonMethods.isElementPresent(announcementCreatedSuccessfully, driver);
		
		return found;
	}
	
	public Boolean isValidationMessageDisplayed()
	{
		boolean found = false;
		
		
		found = commonMethods.isElementPresent(validationMessage, driver);
		
		return found;
	}
	
	public CreateAnnouncement enterInternalTitle(String title)
	{
		
		
		driver.findElement(internalTitle).clear();
		driver.findElement(internalTitle).sendKeys(title);
		
		return new CreateAnnouncement(driver);
	}
	
	/**
	 * @param value supported options are basic or WSP
	 */
	public CreateAnnouncement selectTypeByValue(String value)
	{
		
		
		new Select(driver.findElement(type)).selectByValue(value);
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement enterHTML(String text)
	{
		
		
		commonMethods.switchToFrame(driver, ckeditor);
		driver.findElement(html).click();
		driver.findElement(html).clear();
		driver.findElement(html).sendKeys(text);
		commonMethods.switchBackToMainFrame(driver);
		commonMethods.switchToFrame(driver,rightnavframe);
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement enterDisplayOrder(String order)
	{
		
		
		driver.findElement(displayOrder).clear();
		driver.findElement(displayOrder).sendKeys(order);
		
		return new CreateAnnouncement(driver);
	}
	
	public UploadImage clickUploadImage()
	{
		
		
		driver.findElement(uploadImageButton).click();
		
		return new UploadImage(driver);
	}
	
	public Boolean isUploadImageButtonDisplayed()
	{
		boolean found;
		
		
		found = commonMethods.isElementPresent(uploadImageButton, driver);
		
		return found;
	}
	
	public Boolean isUploadedImageDisplayed()
	{
		boolean found;
		
		
		found = commonMethods.isElementPresent(uploadedImage, driver);
		
		return found;
	}
	
	/**
	 * @param status supported values are 30=Draft, 40=Ready, 50=Active, 60=Archived
	 */
	public CreateAnnouncement selectStatusByValue(String statusValue)
	{
		
		
		new Select(driver.findElement(status)).selectByValue(statusValue);
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement selectAutoActiveMonthByValue(int month)
	{
		
		
		new Select(driver.findElement(autoActiveMonth)).selectByValue(Integer.toString(month));
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement selectAutoActiveDayByValue(int day)
	{
		
		
		new Select(driver.findElement(autoActiveDay)).selectByValue(Integer.toString(day));
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement selectAutoActiveYearByValue(int year)
	{
		
		
		new Select(driver.findElement(autoActiveYear)).selectByValue(Integer.toString(year));
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement selectAutoArchiveMonthByValue(int month)
	{
		
		
		new Select(driver.findElement(autoArchiveMonth)).selectByValue(Integer.toString(month));
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement selectAutoArchiveDayByValue(int day)
	{
		
		
		new Select(driver.findElement(autoArchiveDay)).selectByValue(Integer.toString(day));
		
		return new CreateAnnouncement(driver);
	}
	
	public CreateAnnouncement selectAutoArchiveYearByValue(int year)
	{
		
		
		new Select(driver.findElement(autoArchiveYear)).selectByValue(Integer.toString(year));
		
		return new CreateAnnouncement(driver);
	}
	
	public void clickSubmit()
	{
		
		
		driver.findElement(submit).click();
		
	}
	
	public void clickSave()
	{
		
		
		driver.findElement(save).click();
		
	}
	
	public void clickCancel()
	{
		
		
		driver.findElement(cancel).click();
		
	}
	
}
