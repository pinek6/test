package report;

import org.testng.ITestResult;

public class TestUtil {

//    public static int getId(ITestResult result) {
//        int id = result.getTestClass().getName().hashCode();
//        id = 31 * id + result.getMethod().getMethodName().hashCode();
//        id = 31 * id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
//        return id;
//    }
    
    public static String getId(ITestResult result){
    	StringBuilder builder = new StringBuilder();
    	builder.append(result.getTestClass().getName());
    	builder.append(result.getMethod().getMethodName());
    	if (result.getParameters() != null && result.getParameters().length != 0) {
            for (int i = 0; i < result.getParameters().length; i++) {
            	builder.append(result.getParameters()[i]);
               }
        }
    	return builder.toString();
    }
}
