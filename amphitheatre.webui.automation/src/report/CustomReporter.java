package report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.reporters.EmailableReporter2;
import org.testng.xml.XmlSuite;

import webUI.ConfigProperties;

public class CustomReporter extends EmailableReporter2 {

	public String emailReport = new ConfigProperties().getConfigProperties().getProperty("EMAIL_REPORT").toUpperCase();
	public String emailReportTo = new ConfigProperties().getConfigProperties().getProperty("EMAIL_TO").toUpperCase();
	public String urlUnderTest = new ConfigProperties().getConfigProperties().getProperty("URL") + "webUI";
	public String environment;	
	public String buildVersion = "";
	
	/* Create an array to extract individual email addresses */	
	public String[] recipientList = emailReportTo.replace(" ", "").split(",");	
	
	public String getBuildVersion() throws IOException
	{
		String line;
		BufferedReader br = new BufferedReader(new FileReader("buildVersion.txt"));
	    try 
	    {
	    	line = br.readLine();
	    } 
	    finally
	    {
	        br.close();
	    }	    
	    return line;
	}
	
	public String getEnvironment(String url)
	{
		if (url.startsWith("https://ralbz2127"))
			return "Q0";
		if (url.startsWith("https://ralbz3243"))
			return "Q1";
		if (url.startsWith("https://atlbz153026"))
			return "ATL";
		if (url.startsWith("https://ralbz3165"))
			return "D1";
		if (url.startsWith("https://wdc01-00002.ibmsls.com/"))
			return "UAT";
		if (url.startsWith("https://isl.app.openpediatrics.org"))
			return "UAT";
		if (url.startsWith("https://hq.app.openpediatrics.org"))
			return "PROD";
		if (url.startsWith("https://kxaautomation100.rtp"))
			return "D1 RTP";
		if (url.startsWith("https://stage"))
			return "UAT Stage";
		if (url.startsWith("https://demo"))
			return "PoC1";
		if (url.startsWith("https://108.168.169.170"))
			return "PoC2";
		if (url.startsWith("https://ibmhr"))
			return "PoC3";
		if (url.startsWith("https://kxaautomation36"))
			return "Q2";
		if (url.startsWith("https://sls204"))
			return "D2 RTP";
		if (url.startsWith("https://kxaautomation133"))
			return "Q1 RTP";
		if (url.startsWith("https://sls204.rtp"))
			return "Q2 RTP";		
		else
			return "";
	}
	
	 public void emailReport(List<ISuite> iSuites)
	 {	
		/* Get the environment name */
		environment=getEnvironment(urlUnderTest);
		if (environment!="PROD")
		{
			try {
				buildVersion = getBuildVersion();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		 
		}
		
		double passes,fails,skips;
		BigDecimal passPercent,failPercent,skipPercent;
    	passes=0;
    	fails=0;
    	skips=0;
    	
    	/* Iterating over each suite included in the test */
        for (ISuite suite : iSuites) 
        {
        	/* Getting the results for the suite */
        	Map<String,ISuiteResult> suiteResults = suite.getResults();
        	for (ISuiteResult sr : suiteResults.values()) 
        	{
        		ITestContext tc = sr.getTestContext();
        		/* Getting the number of tests that passed,failed or were skipped */
        		passes+= tc.getPassedTests().getAllResults().size();
        		fails += tc.getFailedTests().getAllResults().size();
        		skips += tc.getSkippedTests().getAllResults().size();
        	}
        }
		
        /* Set the percentage pass/fail and skipped */
        passPercent = new BigDecimal(passes /(passes+fails+skips)*100,MathContext.DECIMAL64);
        failPercent = new BigDecimal(fails /(passes+fails+skips)*100,MathContext.DECIMAL64);
        skipPercent = new BigDecimal(skips /(passes+fails+skips)*100,MathContext.DECIMAL64);
        passPercent = passPercent.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        failPercent= failPercent.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        skipPercent = skipPercent.setScale(2,BigDecimal.ROUND_HALF_EVEN);

	    /* Sender's email address */
	    String from = "owenfletcher@ie.ibm.com";
 
		Properties props = new Properties();		
		props.put("mail.smtp.host","everclear.swg.usma.ibm.com");		
		Session session = Session.getInstance(props);		
 
		try
		{
	         /* Create a default MimeMessage object. */
	         MimeMessage message = new MimeMessage(session);

	         /* Set From: header field of the header. */
	         message.setFrom(new InternetAddress(from));

	        /* Add the recipients to the email */
	        for (int i=0; i<recipientList.length; i++)
		    {
        	 message.addRecipient(Message.RecipientType.TO,
                     new InternetAddress(recipientList[i]));	
 		    }        

	         Date dNow = new Date( );
	         SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss 'on' dd/MM/yyyy");
	         
	         /* Get the Suite Name (modifiable in TestNG xml file) */
	         String suiteName=iSuites.get(0).getName();
	         
	         /* Set Subject: header field */
	         message.setSubject(environment + " "+suiteName+" Test Results | " + ft.format(dNow) + " | Passes:" + (int)passes + ", Fails: " + (int)fails+ ", Skips: " + (int)skips);

	         String htmlBody = "<head><style>body {font-family:sans-serif,arial,helvetica,sans-serif;font-size:9pt;}"
	        	 +"table {border-collapse:collapse;border:1px solid black;}"
	        	 +"th {background-color:grey;color:white;padding:5px;border:1px solid black;font-size:9pt;}"
	        	 +"td {padding:5px;border:1px solid black;font-size:9pt;}"
	        	 +"h3 {font-size:12pt;}"
	        	 +".passed {background-color:#00FF00}"
	        	 +".failed {background-color:#FF0000}"
	        	 +".skipped {background-color:#DEDEDE}"
	        	 +".italic {font-style:italic}"
	        	 +"</style></head>"
	          + "Application under test - <a href=" + urlUnderTest + ">"+urlUnderTest+"</a><br>";
	          if (buildVersion!="")
	          {
	        	  htmlBody+= "Build - <strong>" + buildVersion + "</strong>";
	          }
	          htmlBody+= "<h3>Summary</h3>"
	          + "<TABLE>" 
	          + "<TR><TH>Result</TH><TH>Count</TH><TH>%</TH>";

	          
	         
	          if (passPercent.equals(new BigDecimal(100).setScale(2,BigDecimal.ROUND_HALF_EVEN)))
	        	  htmlBody += "<TR><TD class=passed>Passes</TD><TD class=passed style=padding:5px>" + (int)passes + "</TD><TD class=passed>"+ passPercent +"%</TD></TR>";	
	          else
	        	  htmlBody += "<TR><TD>Passes</TD><TD style=padding:5px>" + (int)passes + "</TD><TD>"+ passPercent +"%</TD></TR>";	
	          if (fails>0)	          
	        	  htmlBody+= "<TR><TD class=failed>Fails</TD><TD class=failed>" + (int)fails + "</TD><TD class=failed>"+ failPercent +"%</TD></TR>";
	          else
	        	  htmlBody+= "<TR><TD>Fails</TD><TD>" + (int)fails + "</TD><TD>"+ failPercent +"%</TD></TR>";
	          if (skips>0)
	        	  htmlBody+= "<TR><TD class=skipped>Skips</TD><TD class=skipped>" + (int)skips + "</TD><TD class=skipped>"+ skipPercent +"%</TD></TR></TABLE>";
	          else
	        	  htmlBody+= "<TR><TD>Skips</TD><TD>" + (int)skips + "</TD><TD>"+ skipPercent +"%</TD></TR></TABLE>";

	         
	         String result = null;
	         htmlBody += "<h3>Breakdown</h3>";
	         for (ISuite suite : iSuites) 
		        {
		            /* Following code gets the suite name */
		            htmlBody += "<TABLE >";
		            htmlBody += "<TD colspan=7>"+suite.getName()+"</TD>";
		            htmlBody += "<TR><TH>Area</TH><TH>Result</TH><TH>Passes</TH><TH>Failures</TH><TH>Skipped</TH><TH>Failed Tests</TH><TH>Skipped Tests</TH></TR>";
		            	
		        	/* Getting the results for the suite and sorting them */
		        	Map<String,ISuiteResult> suiteResults = suite.getResults();
		        	suiteResults=sortByComparator(suiteResults);
		        	for (ISuiteResult sr : suiteResults.values()) 
		        	{	
		        		
		        		IResultMap m = sr.getTestContext().getFailedTests();
		    	        Set<ITestResult> failedTests = m.getAllResults();
		    	         
		    	        IResultMap n = sr.getTestContext().getSkippedTests();
		    	        Set<ITestResult> skippedTests = n.getAllResults();
		    	        
		        		ITestContext tc = sr.getTestContext();
		        		int total = sr.getTestContext().getPassedTests().size()+sr.getTestContext().getFailedTests().size()+sr.getTestContext().getSkippedTests().size();
		        		if (tc.getFailedTests().getAllResults().size()>0 || tc.getSkippedTests().getAllResults().size()>0)
		        		{
		        			result = "<TD class=failed>FAIL</TD>";
		        		}
		        		else if (total==0)
		        		{
		        			result = "<TD class=skipped>N/A</TD>";
		        		}
		        		else
		        		{
		        			result = "<TD class=passed>PASS</TD>";
		        		}
		        		
		        		/* Getting the number of tests that passed,failed or were skipped */
		        		htmlBody += "<TR><TD>" + sr.getTestContext().getName() + "</TD>"+result;
		        		if (total==0)
		        		{
		        			htmlBody += "<TD colspan=6>No tests enabled for this environment</TD></TR>";
		        		}
		        		else
		        		{
		        			htmlBody += "<TD>"+sr.getTestContext().getPassedTests().size()+"</TD>";
		        			htmlBody += "<TD>"+sr.getTestContext().getFailedTests().size()+"</TD>";
		        			htmlBody += "<TD>"+sr.getTestContext().getSkippedTests().size()+"</TD>"; 
		        			htmlBody += "<TD>";
		        			int i = 1;
		        			/* Print out the failing tests */
		        			for (ITestResult s : failedTests) {
		        				if (i==1){
		        					htmlBody +=s.getName();}
		        				else{
		        					htmlBody +=", " + s.getName();}
		        				i++;
			    	        }
		        			htmlBody += "</TD>";
		        			htmlBody += "<TD>";
		        			/* Print out the skipped tests */
		        			i = 1;
		        			for (ITestResult s : skippedTests) {
		        				if (i==1){
		        					htmlBody +=s.getName();}
		        				else{
		        					htmlBody +=", " + s.getName();}
		        				i++;
			    	        }
		        			htmlBody += "</TD>";
			    	        htmlBody += "</TR>";
		        		}
		        	}
		        	htmlBody += "</TABLE><BR>";
		        	
		        }

	        
	         
	         
	         
	         htmlBody += "<BR>For a detailed analysis of the results, please open the attachment (<span class=italic>emailable-report.html</span>) ";
	         htmlBody += "<BR>Click <a href=https://w3-connections.ibm.com/wikis/home?lang=en-ie#!/wiki/W122fb9f7e945_439a_96a9_c8f1fe431930/page/Automation%20Test%20Coverage>here</a> to view the automated test coverage matrix";
      
	         Multipart mp = new MimeMultipart();

	         /* Creating html email */
	         MimeBodyPart htmlPart = new MimeBodyPart();
	         htmlPart.setContent(htmlBody, "text/html");
	         mp.addBodyPart(htmlPart);

	         /* Attaching report */
	         MimeBodyPart attachment = new MimeBodyPart();
	         String filename = "test-output/emailable-report.html";
	         DataSource source = new FileDataSource(filename);
	         attachment.setDataHandler(new DataHandler(source));
	         attachment.setFileName(filename);
	         mp.addBodyPart(attachment);

	         /* Setting the content of the email */
	         message.setContent(mp);
	         
	         /* Send message */
	         System.out.println("Emailing report...");
	         Transport.send(message);
	         System.out.println("Report emailed successfully to " + emailReportTo);
	    }
		catch (MessagingException mex) 
		{
	         mex.printStackTrace();		      
		}		 
	 }

	 private static Map<String,ISuiteResult> sortByComparator(Map<String,ISuiteResult> unsortMap) {
		 
			List list = new LinkedList(unsortMap.entrySet());
	 
			// sort list based on comparator
			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					return ((Comparable) ((Map.Entry) (o1)).getValue())
	                                       .compareTo(((Map.Entry) (o2)).getValue());
				}
			});
	 
			// put sorted list into map again
	                //LinkedHashMap make sure order in which keys were inserted
			Map sortedMap = new LinkedHashMap();
			for (Iterator it = list.iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				sortedMap.put(entry.getKey(), entry.getValue());
			}
			return sortedMap;
		}
	 
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> iSuites, String s) 
    {	    
    	/* Generate the report */
    	super.generateReport(xmlSuites, iSuites, s);

    	/* Email the report */ 
    	if (emailReport.equals("Y"))
    		emailReport(iSuites);
    }
}
