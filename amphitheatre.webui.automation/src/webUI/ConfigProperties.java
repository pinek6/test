package webUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class ConfigProperties {	

	private Properties config = new Properties();
	FileInputStream in;
	String propertiesLocation = "src/config.properties";
	
	public ConfigProperties()
	{

	}
	
	public ConfigProperties(WebDriver driver, String propertiesLocation)
	{
		this.propertiesLocation=propertiesLocation;
	}
	
	public Properties getConfigProperties()
	{		
		try {
			in = new FileInputStream(propertiesLocation);
			config.load(in);
			in.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public String getLocation() {
		return this.propertiesLocation;
	}
	public void setLocation(String input) {
		this.propertiesLocation = input;
	}
}
