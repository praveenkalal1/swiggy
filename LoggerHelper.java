package swiggy.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("rawtypes")
public class LoggerHelper {
	
private static boolean root=false;
	
	public static Logger getLogger(Class clas){
		if(root){
			return Logger.getLogger(clas);
		}
		
		//PropertyConfigurator.configure("C://Users//prash//Desktop//Selenium//javaprojects_JDK8//dataDrivenFrame//src//main//resources//projectconfig//log4j.properties");
		PropertyConfigurator.configure(ResourcesHelper.getResourcePath("//src//main//resources//projectConfig//log4j.properties"));
		root=true;
		return Logger.getLogger(clas);		
	}


}
