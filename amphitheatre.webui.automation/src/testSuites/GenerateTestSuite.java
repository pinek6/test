package testSuites;
 
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlClass;
 
//this class generates testng suite based on parameter 'test level'
//to run this class execute command from cmd: java testSuites.GenerateTestSuite "testLevel" "testProject"
public class GenerateTestSuite {
	
	public static void main(String[] arg) throws IOException, URISyntaxException
	{
		//Validate input parameters
		Boolean validate1 = false;
		Boolean validate2 = false;
		for (String str: validTestLevels){
			if(str.trim().contains(arg[0])){
				validate1 = true;
			}
		}
		for (String str: validTestEnvironments){
			if(str.trim().contains(arg[1])){
				validate2 = true;
			}
		}
		
		//If inputs correct create XML file else print error
		if(validate1&&validate2){
			createXmlSuite(arg[0],arg[1]);
			System.out.println("Test XML suite succesfully crated");
			System.out.println("Test level: "+arg[0]);
			System.out.println("Test environment: "+arg[1]);
		}
		else
			System.out.println("Incorrect parameters - please investigate.");
	}
	
	public static void createXmlSuite(String testLevel, String testEnvironment) throws IOException, URISyntaxException {
		 
		 //Create an instance on TestNG
		 TestNG myTestNG = new TestNG();
		 
	   	 //Create an instance of XML Suite, assign a name parallel and thread count for it
		 XmlSuite mySuite = new XmlSuite();
		 mySuite.setName(testEnvironment+" "+testLevel+" - Test Suite");
		 mySuite.setParallel("tests");
		 mySuite.setThreadCount(6);
		 
		 //Setup suite listeners
		 mySuite.addListener("report.CustomReporter");
		 mySuite.addListener("report.RetryListener");
		 mySuite.addListener("report.FixRetryListener");
		 mySuite.addListener("report.MyListener");
		 
		 //Get test environments and test classes
		 String[][] environments=getEnvironments(testLevel);
		 List<String> testClasses = getTestClasses();	 
		
		 //Initialise list of XmlTests
		 List<XmlTest> myTests = new ArrayList<XmlTest>();
		 
		 //Loop over browser/version/platform
		 for(int i=0;i<environments.length;i++){
			 
			 //Loop over test classes
			 for(int j=0;j<testClasses.size();j++){
				 
				//Create an instance of XmlTest and assign a name for it.
				XmlTest myTest = new XmlTest(mySuite);
				
				//Get test name
				String testName = testNameSetup(environments[i], testClasses.get(j));
				
				//Set test name
				myTest.setName(testName);
				 
				//Set correct groups depends on test level
				myTest.addIncludedGroup(testLevel);
				
				//Exclude IEonly test if this if not IE test
				if(!environments[i][0].equals("internet explorer"))
					myTest.addExcludedGroup("IEonly");
				 
				//Add any parameters that you want to set to the Test.
				Map<String,String> testngParams = new HashMap<String,String> ();
				testngParams.put("browser", environments[i][0]);
				testngParams.put("version", environments[i][1]);
				testngParams.put("platform", environments[i][2]);
				myTest.setParameters(testngParams);
				 
				//Create a list which can contain the classes that you want to run.
				List<XmlClass> myClasses = new ArrayList<XmlClass> ();
				myClasses.add(new XmlClass(testClasses.get(j)));
			
				//Assign that to the XmlTest Object created earlier.
				myTest.setXmlClasses(myClasses);
				 
				//Add test to xml tests
				myTests.add(myTest);
			 }
		 }	 
		 
		//add the list of tests to your Suite.
		mySuite.setTests(myTests);
		
		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		 
		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);
		 
		//Get suite XML
		String xmlSource = mySuite.toXml();
		 
		//Save XML to a file
		java.io.FileWriter fw = new java.io.FileWriter("my-file.xml");
		   fw.write(xmlSource);
		   fw.close();
		}
	
	//Valid Test levels
	static String[] validTestLevels = {"BVT","FullRegression","test","UAT-BVT"};
	
	//Valid Test Environments
	//TODO this will needs to be updated when we will know what environments we support
	static String[] validTestEnvironments = {"BASE","BCH","IBMHR"};
	
	//BVT environment configuration
	static String[][] environmentsBVT = {
		{"firefox"				,""		,"WINDOWS"},
		{"chrome"				,""		,"WIN8"}
   	};
	
	//Full regression environment configuration
	static String[][] environmentsFullRegression = {
		{"internet explorer"	,"10"	,"WIN8"},
		{"chrome"				,""		,"WIN8"},
		{"internet explorer"	,"9"	,"WIN7"},
		{"firefox"				,""		,"WIN7"},
		{"safari"				,""		,"MAC"},
		{"chrome"				,""		,"MAC"}
   	};
	
	//Function to get different environments depends on test level
	public static String[][] getEnvironments(String testLevel){
		String[][] environments={{"chrome","","WIN8"}};
		
		if(testLevel.equals("BVT")||testLevel.equals("UAT-BVT"))
			environments=environmentsBVT;
		else if(testLevel.equals("FullRegression")||testLevel.equals("test"))
			environments=environmentsFullRegression;
		
		return environments;
	}
	
	//TODO this will need to be updated to return different results depend on testEnvironment parameter
	//Function to get test classes
	
	public static List<String> getTestClasses() throws IOException, URISyntaxException{
		
		List<String> classesListAdmin = getClassNamesFromPackage("tests.admin");
		List<String> classesListUIf = getClassNamesFromPackage("tests.webUI.functional");
		List<String> classesListUInf = getClassNamesFromPackage("tests.webUI.nonFunctional");
		
		List<String> classesList = new ArrayList<String>();
		
		classesList.addAll(classesListAdmin);
		classesList.addAll(classesListUIf);
		classesList.addAll(classesListUInf);
		
		for (int i=0;i<classesList.size();i++){
			String temp = classesList.get(i);
			temp = temp.replace("/", ".");
			classesList.set(i, temp);

		}
		return classesList;
	}
			
	public static List<String> getClassNamesFromPackage(String packageName) throws IOException, URISyntaxException{
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    URL packageURL;
	    List<String> names = new ArrayList<String>();
	    packageName = packageName.replace(".", "/");
	    packageURL = classLoader.getResource(packageName);

	    URI uri = new URI(packageURL.toString());
	    File folder = new File(uri.getPath());
	        // won't work with path which contains blank (%20)
	        // File folder = new File(packageURL.getFile()); 
	        File[] contenuti = folder.listFiles();
	        String entryName;
	        for(File actual: contenuti){
	            entryName = actual.getName();
	            entryName = entryName.substring(0, entryName.lastIndexOf('.'));
	            names.add(packageName+"/"+entryName);
	        }
	        
	    return names;
	}
	
	public static String testNameSetup(String[] environments, String className){
		
		//cut tests
		String area = className.substring(className.indexOf(".")+1,className.length());
		//cut everything after amidn or webUI
		String area2 = area.substring(0,area.indexOf("."));
		// get class name
		String classN = className.substring(className.lastIndexOf(".")+1,className.length());
		
		String functional ="";

		if (area2.equals("webUI")){
			functional = area.substring(area.indexOf(".")+1,area.length());
			functional = functional.substring(0,functional.indexOf("."));
			functional = "("+functional+")";
			}
		
		return "["+environments[2]+"/"+environments[0]+"/"+environments[1]+"] - "+area2+" - "+classN+functional;
	}
}