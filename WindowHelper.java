package swiggy.helper;

import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import swiggy.helper.LoggerHelper;



public class WindowHelper {

	
	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(WindowHelper.class);
	
	public WindowHelper(WebDriver driver){
		this.driver=driver;
	}
	
	public void switchToParentWindow(){
		log.info("switch to parent Window");
		driver.switchTo().defaultContent();
	}
	
	public void switchToWindows(int index){
		ArrayList<String> a=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(a.get(index));
	}
	
	public void switchtoWindow(int index){
				
		Set<String> windows=driver.getWindowHandles();
		int i=1;
		for(String window:windows){
			
			if(i==index){
				log.info("Switch To: "+index+"window");
				driver.switchTo().window(window);
				
			}
			else{
				i++;
			}
		}
		
	}
	
	public void closeAllTabsSwitchtoMainWindow(){
		Set<String> windows=driver.getWindowHandles();
		String mainWindow=driver.getWindowHandle();
		for(String window:windows){
			if(!window.equalsIgnoreCase(mainWindow)){
				driver.close();
			}
		}
		log.info("Switching to main window");
		driver.switchTo().window(mainWindow);
	}
	
	public void navigateBack(){
		log.info("GO to Previous page");
		driver.navigate().back();
	}
	
	public void navigateForward(){
		log.info("GO to Next page");
		driver.navigate().forward();
	}
}
