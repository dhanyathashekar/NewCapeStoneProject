package reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;



public class ExtentTestNGITestlistners implements ITestListener {
	/*
	 * ThreadLocal is a class in java.lang package provides a way to store values in per thread basis
	 * It create separate instance of the value for each thread allowing each thread to have its own copy of the value
	 * without interfering with other threads.
	 * 
	 * It is useful for storing values that are specific to a single thread such as transaction or request specific data
	 * ThreadLocal provides a simple and efficient way to store thread local variables without having to pass them as method arguments or store them as instance variables. 
	 */
	ExtentReports extent=ExtentManager.getInstance();
	
	ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result) 
	{
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
	
		parentTest.set(extentTest);
	}
	
	
	public void onTestSuccess(ITestResult result) {
	   
		
		
		parentTest.get().pass("Test Passed");
	  }
	
	
	public void onTestFailure(ITestResult result) 
	{
		
		
		parentTest.get().fail("Test Failed "+result.getThrowable().getMessage());
		
	   
	  }
	
	
	public void onTestSkipped(ITestResult result) {
		parentTest.get().skip("Test Skipped "+result.getThrowable().getMessage());
	   
	  }
	
	
	public  void onFinish(ITestContext context) {
		extent.flush();
	    
	  }
}