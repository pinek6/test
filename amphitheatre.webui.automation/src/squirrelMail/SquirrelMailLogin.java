package squirrelMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUI.CommonMethods;

public class SquirrelMailLogin 
{
	private WebDriver driver;
	CommonMethods commonMethods = new CommonMethods(driver);
	 
	private String url = "http://llnsvtsmtp.swg.usma.ibm.com/webmail/src/login.php";
	private String nm = "fvt"; 
	private String pw = "passw0rd";
	
	private By name = By.name("login_username");
	private By password = By.name("secretkey");
	private By login = By.cssSelector("input[type='submit']");
	
	public SquirrelMailLogin (WebDriver driver)
	{
		this.driver=driver;
		driver.navigate().to(url);
		loginAs(this.nm,this.pw);
	}
	
	public SquirrelMailLogin (WebDriver driver, String nm, String pw)
	{
		this.driver=driver;	
		driver.navigate().to(url);
		loginAs(nm,pw);
	}
	
	public SquirrelMailLogin enterName(String nm)
	{
		driver.findElement(name).clear();
		driver.findElement(name).sendKeys(nm);
		return this;
	}
	
	public SquirrelMailLogin enterPassword(String pw)
	{
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pw);
		return this;
	}
	
	public void clickLogin()
	{
		driver.findElement(login).click();
	}
	
	public void loginAs(String nm, String pw)
	{
		enterName(nm);
		enterPassword(pw);
		clickLogin();
	}
}
