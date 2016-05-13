package report;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

import webUI.ConfigProperties;
 
public class RetryAnalyzer implements IRetryAnalyzer {
 
private int count = 0;
private int maxCount = Integer.parseInt(new ConfigProperties().getConfigProperties().getProperty("NUM_RETRIES"));
private int invocationCounter = 0;
 
@Override
public boolean retry(ITestResult result) {
	  
	  int currentInvocationCounter = result.getMethod().getCurrentInvocationCount();

	  if (!(currentInvocationCounter==invocationCounter+1)){
		  count=0;
		  result.setStatus(ITestResult.SUCCESS);
	  }

      if (count < maxCount-1) {
		count++;
		invocationCounter = result.getMethod().getCurrentInvocationCount();
		result.setStatus(ITestResult.SUCCESS);
		String message = result.getName() + " test failed. Retrying "+ (maxCount - count) + " more times";
		System.out.println(message);
		Reporter.log(message);
		return true;
      } 
      else{
		count=0;
		result.setStatus(ITestResult.FAILURE);
		invocationCounter = result.getMethod().getCurrentInvocationCount();
		String message = result.getName() + " test failed "+maxCount+" times. No more retries.";
		System.out.println(message);
      }
	  
	  return false;
}
}