package report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;


public class MyListener implements ITestListener
{

	@Override
	public void onFinish(ITestContext result) {
	}

	@Override
	public void onStart(ITestContext result) {
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override //this override, deletes failed previous retries if finally test passes
	public void onTestSuccess(ITestResult result) {
		for (ITestNGMethod resultToCheck : result.getTestContext().getAllTestMethods()){
				result.getTestContext().getFailedTests().removeResult(result.getMethod());
		}
	 }
}
