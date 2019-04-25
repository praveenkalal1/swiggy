package swiggyTest;



import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swiggy.pageobjects.Management;
import swiggy.pageobjects.SLogin;
import swiggy.testBase.DataSource;
import swiggy.testBase.TestBase;



public class SLogTest extends TestBase{
	
	SLogin login;
	
	@DataProvider(name="testData")
	public Object[][] testData(){
		String [][] data=excelData("DataDriven.xlsx", "Swiggy");
		return data;			
	}
	
	@BeforeClass
	public void beforeClass() throws Exception{
		test=extent.createTest(getClass().getName());
		getApplication(DataSource.OR.getProperty("website"));
		login=new SLogin(driver);
	} 
	
	@Test(dataProvider="testData")
	public void testRestaurant(String userName, String runMode) throws Exception{
		
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
		Management mgmt=login.loginModule(userName);
		mgmt.mainPage();
		
	}

}
