package swiggy.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
















import swiggy.helper.ExcelHelper;
import swiggy.helper.LoggerHelper;
import swiggy.helper.ResourcesHelper;
import swiggy.utility.ExtentManager;





public class TestBase {
	public static WebDriver driver;
	public static Logger log= LoggerHelper.getLogger(TestBase.class);
	public static ExtentReports extent;
	public static ExtentTest test;
	
	/*
	public static void main(String[] args) {
		
		log.info("Test Log");
	}
	*/
	
	/**
	 * this method will give excel data in 2D array
	 * @param excellocation
	 * @param sheetname
	 * @return
	 */
	
	public String[][] excelData(String excelname, String sheetname){
		String excellocation=ResourcesHelper.getResourcePath("/src/main/resources/testData/")+excelname;
		ExcelHelper readExcel=new ExcelHelper();
		return readExcel.excelData(excellocation, sheetname);
	} 
	
	public Object[][] masterExcelData(String excelName, String sheetName, String testName){
		String excellocation=ResourcesHelper.getResourcePath("/src/main/resources/testData/")+excelName;
		ExcelHelper readExcel=new ExcelHelper();
		return readExcel.masterExcelData(excellocation, sheetName, testName);
		
	}
	
	/**
	 * this method will update result in excel sheet
	 * @param excellocation
	 * @param sheetname
	 * @param testcasename
	 * @param status
	 */
	public void updateresult(String excellocation, String sheetname, String testcasename, String status ){
		
		ExcelHelper updateExcel=new ExcelHelper();
		updateExcel.updateresult(excellocation, sheetname, testcasename, status);
		
	}
	/**
	 * this method will create browser objects
	 * @param browser
	 * @return
	 */
	
	@Parameters("browserType")
	public void getBrowser(String browserType){
		if(System.getProperty("os.name").contains("Windows"))
		{
			if(browserType.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("os.name"));
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")+"/src/main/resources/browserdrivers/geckodriver.exe");
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				cap.setCapability("marionette", false);
				driver= new FirefoxDriver(cap);
			}
				else if(browserType.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir")+"/src/main/resources/browserdrivers/chromedriver.exe" );
					driver=new ChromeDriver();					
				}
				else if(System.getProperty("os.name").contains("mac")){
					if(browserType.equalsIgnoreCase("firefox")){
						System.out.println("user.dir");
						System.setProperty("webdriver.firefox.marionette", 
								System.getProperty("user.dir")+"/src/main/resources/browserdrivers/geckodriver.exe");
						driver=new FirefoxDriver();
					}
					else if(browserType.equalsIgnoreCase("chrome")){
						System.setProperty("webdriver.chrome.driver", 
								System.getProperty("user.dir")+"/src/main/resources/browserdrivers/chromedriver.exe");
						driver=new ChromeDriver();
					}			
		}		
	}
	}
		
		public String getScreenShot (String imagename, WebDriver driver) throws Exception{
			log.info("Take screenshot");
			if (imagename.equals("")){    // If we don't give any imagename it will store 
				                              //as blank. it will store as date formater
				imagename="blank";
			}
			File image=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String imageLocation=ResourcesHelper.getBaseResourcePath() + "/src/main/resources/screenshot/";
			Calendar date = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String actualImage= imageLocation + imagename + "_" + formater.format(date.getTime())+ ".png";
			File destFile=new File(actualImage);
			Files.copy(image, destFile);
			log.info("Got the screen shot");
			return actualImage;
		
	}
		/*@AfterTest
		public void afterTest(){
			driver.close();
		}*/
		
		
		 @BeforeSuite
		public void beforeSuite(){
			extent=ExtentManager.getInstance();
		}
		@BeforeClass
		public void beforeClass1() throws Exception{
			test=extent.createTest(getClass().getName());
		}
		@BeforeMethod
		public void beforeMethod(Method method){
			test.log(Status.INFO, method.getName()+ " test started");
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
		
		public String getData(String name){
			return DataSource.OR.getProperty(name);
		}
		
		
		/*public void launchBrowser(){
			try {
				loadPropertiesFile();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}*/
		
		@BeforeTest
		public void launchBrowser(){
			getBrowser(getData("browserType"));
			driver.manage().timeouts().implicitlyWait(DataSource.getImplicitWait(), TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(DataSource.getPageLoadTimeOut(), TimeUnit.SECONDS);
		}
		
		/*public void launchBrowser(){
			try {
			     getBrowser("chrome");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}*/
		
		// To Load Properties
	/*public void loadPropertiesFile() throws Exception{
		
		String logConfigPath="C:\\Users\\prash\\Desktop\\framework\\dataDriven\\src\\main\\resources\\projectConfig\\log4j.properties";
		PropertyConfigurator.configure(logConfigPath);
	    Properties OR = new Properties();
		File f1 = new File(System.getProperty("user.dir")+"/src/main/resources/projectConfig/config.properties");
		FileInputStream file = new FileInputStream(f1);
		OR.load(file);
		
		log.info("loading config.properties");
			
		}*/

		
		public void getApplication(String website){
			log.info(website);
			driver.get(website);
			
		}
}
