package swiggy.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import swiggy.helper.LoggerHelper;

public class AlertHelper {

	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver){
		this.driver=driver;
		log.info("Alert Helper object is created");
		
	}
	
	public Alert getAlert(){
		log.info("alert test: "+driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}
	
	public void alertAccept(){
		log.info("Accepting alert");
		getAlert().accept();
	}
	
	public void alertDismiss(){
		log.info("Dismiss Alert");
		getAlert().dismiss();
	}
	
	public String getAlertText(){
		String text=getAlert().getText();
		log.info("alert text "+text);
		return text;
	}
	
	public boolean isAlertPresent(){
		try {
			driver.switchTo().alert();
			log.info("Alert is present");
			return true;
		} catch (Exception e) {
			log.info(e.getCause());
			return false;
		}
	}
	
	public void acceptAlertIfPresent(){
		if(isAlertPresent()){
			alertAccept();
		}else{
			log.info("Alert is not Present");
		}
			
	}
	
	public void dismissAlertIfPresent(){
		if(isAlertPresent()){
			alertDismiss();
		}else{
			log.info("Alert is not Present");
		}
			
	}
	
	public void acceptPrompt(String text){
		if(isAlertPresent()){
			Alert alert=getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text: "+text);
		}
	}
}
