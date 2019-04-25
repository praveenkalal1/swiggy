package swiggy.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import swiggy.helper.LoggerHelper;



public class GenericHelper {
	
private static final Logger log=LoggerHelper.getLogger(GenericHelper.class);
	
	public String readValuefromElement(WebElement element){
		
		if(null==element){
			log.info("WebElement is null");
			return null;
			}
		boolean displayed= false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}
		if(!displayed){
			return null;
		}
		String text=element.getText();
		log.info("WebElement value is" +text);
		return text;
	}
	public String readvaluefromInput(WebElement element){
		if(null==element){
			return null;
		}
			if(!isDisplayed(element)){
				return null;
			}
			String value=element.getAttribute("value");
			log.info("WebElement is"+value);
			return value;
			}
	public boolean isDisplayed(WebElement element){
		try {
			element.isDisplayed();
			log.info("element is displayed" +element);
			return true;
		} catch (Exception e) {
			log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}
	}
		public boolean isNotDisplayed(WebElement element){
			try {
				element.isDisplayed();
				log.info("element is dispalyed "+element);
				return false;
			} catch (Exception e) {
				log.error(e);
				Reporter.log(e.fillInStackTrace().toString());
				return true;
			}
		}
		
		protected String getDisplayedText(WebElement element){
			if(null==element){
				return null;
			}
				if(!isDisplayed(element)){
					return null;
				}
				return element.getText();
			}
		
		public static synchronized String getElementText(WebElement element){
			if(null==element){
				log.info("WebElement is null");
				return null;
			}
			String elementText=null;
			try {
				elementText=element.getText();
			} catch (Exception ex) {
				log.info("Element not found"+ex);
				Reporter.log(ex.fillInStackTrace().toString());
			}
			return elementText;
			
			
		}
		
	

}
