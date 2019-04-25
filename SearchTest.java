/*package swiggyTest;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swiggy.pageobjects.Management;
import swiggy.pageobjects.SLogin;
import swiggy.pageobjects.Search;
import swiggy.testBase.DataSource;
import swiggy.testBase.TestBase;


public class SearchTest extends TestBase {
	
	SLogin login;
	
    @DataProvider(name="testData")
	public Object[][] testData(){
    	String[][] data=excelData("DataDriven.xlsx", "Restaurant");
    	return data;
    }
    
    @BeforeClass
	public void beforeClass() throws Exception{
		getApplication(DataSource.OR.getProperty("website"));
		login=new SLogin(driver);
	} 
	
	@Test(dataProvider="testData")
	public void testRestaurant(String userName, String restaurant, String runMode) throws Exception{
		
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
		Management mgmt=login.loginModule(userName);
		Search search=mgmt.mainPage();
		search.deliveryPage(restaurant);
	}

}
*/