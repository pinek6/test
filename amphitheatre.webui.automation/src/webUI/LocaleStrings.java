package webUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class LocaleStrings 
{	
	private ConfigProperties p = new ConfigProperties();
	private Properties config = new Properties();
	FileInputStream in;
	String localeStringsLocation = "src/strings";
	String locale;
	
	public LocaleStrings()
	{

	}
	
	public LocaleStrings(WebDriver driver)
	{

	}
	
	public String getString(String key)
	{	
		/* Get the application properties so the locale can be determined */
		config = p.getConfigProperties();
		
		/* Get the locale */
		locale = config.getProperty("LOCALE");
		
		/* Get the location/filename of the locale strings.
		 * Filename format is "strings_<<locale>>.properties"
		*/
		localeStringsLocation = localeStringsLocation+"_"+locale+".properties";
		
		/* Read in the locale strings file */
		try {
			in = new FileInputStream(localeStringsLocation);
			config.load(in);
			in.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Return the required string */
		return config.getProperty(key);
		
	}

}
