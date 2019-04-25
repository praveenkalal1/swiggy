package swiggy.utility;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentBase {

	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void beforeSuite(){
		extent=ExtentManager.getInstance();
	}
	@BeforeClass
	public void beforeClass(){
		test=extent.createTest(getClass().getName());
	}
	@BeforeMethod
	public void beforeMethod(Method method){
		test.log(Status.INFO, method.getName()+ " test started" );
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			test.log(Status.FAIL, result.getThrowable());			
		} else if(result.getStatus()==ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		} else if(result.getStatus()==ITestResult.SUCCESS){
			test.log(Status.PASS, result.getTestName()+ "  is pass");
		}
		extent.flush();
	}
	
	
}
