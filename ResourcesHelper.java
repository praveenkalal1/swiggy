package swiggy.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;



public class ResourcesHelper {
	
	// this method will return resource path
		public static String getResourcePath(String resource){
			
			String path= getBaseResourcePath()+resource;
			return path;		
		}
		
		// this method will return project location,  irrespective of driverlocations 
		public static String getBaseResourcePath() {
			// user.dir will give project location
			String path= System.getProperty("user.dir");
			return path;
		}
		
		public static InputStream getResourcePathInputStream(String path)throws FileNotFoundException{
			return new FileInputStream(ResourcesHelper.getResourcePath(path));
			
		}

}
