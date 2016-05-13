package tests.webUI.nonFunctional;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import admin.members.people.CreateMember;
import admin.members.people.People;
import admin.navigation.LeftNav;
import admin.navigation.TopNav;
import webUI.ConfigProperties;
import webUI.LoadBrowser;
import webUI.LocaleStrings;
import webUI.Navigation;
import webUI.home.library.Library;
import webUI.home.library.TileView;
import webUI.home.library.mediaTypes.Calculator;
import webUI.home.library.mediaTypes.MediaType;
import webUI.home.library.mediaTypes.Simulator;
import webUI.home.library.mediaTypes.Summary;
import webUI.home.library.mediaTypes.Video;



public class LibraryContentsTests extends LoadBrowser {
	
	public static Set<String> contents = new LinkedHashSet<String>();
	private String email="", displayname, fname, lname, occupation, country, city, password;
	private int postGradExperience, specialty, subSpecialty, interest;
	
	/* Read in the configuration file */
	 ConfigProperties config = new ConfigProperties();
	
	/* Page Headers */
	private String otherCity = new LocaleStrings().getString("OTHER_CITY");
	private int counter;

	
	public String createNewMember()
	{		
		logIntoAdminConsole();
		
		/* Set the email and displayname */
		Date d = new Date();
		String email = "fvt"+d.getTime()+"@bluebox.lotus.com";
		displayname = "z_fvt_"+d.getTime();
		fname = "z_fname";
		lname = "l_fname_"+d.getTime();
		occupation="Surgeon"; 
		country="United States"; 
		city=otherCity; 
		password="Pa88w0rdfvt";
		postGradExperience=1;
		specialty=1;
		subSpecialty=1;
		interest=1;
		
		/* Click Members - People in the left hand navigation */
		LeftNav leftnav = new LeftNav(getDriver());	
		leftnav.clickMembers();
		People peoplePage = leftnav.clickPeople();
				
		/* Click Create Member button */
	    CreateMember createMemberPage = peoplePage.clickCreateMember();   

	    /* Fill in the member details */
		Boolean memberCreated = createMemberPage.enterMandatoryFieldsAndClickSave(email, fname, lname, displayname, occupation, postGradExperience, specialty, subSpecialty, interest,  country, city, password);		

		Assert.assertTrue(memberCreated,"Failed to create new member");		
		//Reporter.log("Member successfully created: " + email);
		
		TopNav topMenu = new TopNav(getDriver());
		topMenu.clickLogout();
				
		return email;
	}
	
	@DataProvider(name = "provideContents")
	public Object[][] provideData() {
	
		Object[][] data = new Object[counter][1];
		
		for(int i=0;i<counter;i++){		
			data[i][0]=i+1;
		}
		return data;
	}
	
	@Test(groups={"FullRegression"}, description="This test is creating new user and getting count of contents in library.")
	public void getContentCount()
	{
		/* Create new member */
		email = createNewMember();
		
		/* Get driver and login*/
		getDriverAndLogin(email,password);
		
		/* Navigate to Library */
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Get count of results*/
		counter = libraryPage.getPagingNumber();
	}
	
	@Test(dependsOnMethods={"getContentCount"}, dataProvider="provideContents", groups={"FullRegression"},description="This test veryfing that all contents loads from library.")
	public void allContentsLoadsFromLibrary(int panel)
	{
		/* Get driver and login */
		getDriverAndLogin(email,password);
		
		/* Navigate to Library*/
		Navigation navigation = new Navigation(getDriver());
		navigation.hoverOverLibrary();
		Library libraryPage = navigation.clickLibrary();
		
		/* Scroll to current panel*/
		libraryPage.scrollToPanel(panel);
		
		/* Load Tile page*/
		TileView tileView = new TileView(getDriver());
		
		/* Get content Title*/
		String contentTitle = tileView.getNthContentTitle(panel);
		
		/* Open panel */
		libraryPage.click1stButtonInNthPanel(panel);
		
		/* Load Media Page */
		MediaType mediaType = new MediaType(getDriver());
		
		/* Get content Type and URL*/
		String contentType = mediaType.getContentType();
		String contentURL = getDriver().getCurrentUrl();
		
		/* Set up String to be saved on global set*/
		String envir = contentURL+"_"+panel+"_"+contentTitle+"_"+contentType;
		
		/* Add envir into global list*/
		contents.add(envir);
		
		/* Load page dependent on content type */
		if(contentType.equals("calculator")){
			Calculator calculator = new Calculator(getDriver());
		}
		else if(contentType.equals("simulator")){
			Simulator simulator = new Simulator(getDriver());
		}
		else if(contentType.equals("pdf")){
			Summary summary = new Summary(getDriver());
		}
		else if(contentType.equals("video")){
			Video video = new Video(getDriver());
		}
		else 
			Assert.fail("Unsupported content Type");
		
		/* Remove result from list if test is passed*/
		contents.remove(envir);
	}
	
	@Test(dependsOnMethods={"allContentsLoadsFromLibrary"}, alwaysRun=true, groups={"FullRegression"},description="This test is saving failures into test results.")
	public void printFailedContents(){
			for(String s: contents){
				Reporter.log(s);
			}
	}
}
