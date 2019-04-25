package swiggy.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import swiggy.helper.LoggerHelper;
import swiggy.helper.ResourcesHelper;



public class DataSource {
	public static Logger log= LoggerHelper.getLogger(DataSource.class); 
	
	public static Properties OR;
	
	private String browserType;
	private String userName;
	private String password;
	private String website;
	

	long implicitWait;
	long explicitWait;
	long pageLoadTimeOut;
	
	public String getUsername() {
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName= userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website){
		this.website=website;
	}
	
	public String getBrowser() {
		return browserType;
	}
	
	public void setBrowser(String browserType){
		this.browserType=browserType;
	}
	
	public static long getPageLoadTimeOut() {
		return Integer.parseInt(OR.getProperty("pageLoadTimeOut"));
	}
	
	public void setPageLoadTimeOut(long pageLoadTimeOut){
		this.pageLoadTimeOut=pageLoadTimeOut;
	}
	
	public static long getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitWait"));
	}
	
	public void setImplicitWait(long implicitWait) {
		this.implicitWait=implicitWait;
	}
	
	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitWait"));
	}
	public void setExplicitWait(long explicitWait){
		this.explicitWait=explicitWait;
	}
	 
	static {
		log.info("loading config properties");
		OR= new Properties();
		File f1=new File(ResourcesHelper.getResourcePath("/src/main/resources/projectConfig/config.properties"));
		try {
			FileInputStream file=new FileInputStream(f1);
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Loding has done");
	}

}
