package report;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
 
public class RetryListener implements IAnnotationTransformer {
 
@Override
public void transform(ITestAnnotation annotation, Class clazz, Constructor constructor,
Method method) {
IRetryAnalyzer retry = annotation.getRetryAnalyzer();
if (retry == null) {
annotation.setRetryAnalyzer(RetryAnalyzer.class);
}
}
}