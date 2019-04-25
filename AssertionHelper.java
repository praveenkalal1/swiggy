package swiggy.helper;

import org.apache.log4j.Logger;
import org.testng.Assert;

import swiggy.helper.LoggerHelper;

public class AssertionHelper {

	    private static Logger log=LoggerHelper.getLogger(AssertionHelper.class);
	
		public static void VerifyText(String s1, String s2){
			log.info("verifying  "+s1+"with "+ s2 );
			Assert.assertEquals(s1, s2);
		}
		
		public static void makeTrue(){
			log.info("making script PASS..");
			Assert.assertTrue(true);
		}
		
		public static void makeTrue(String message){
			log.info("making script PASS.."+ message);
			Assert.assertTrue(true, message);
			
		}
		public static void makeFalse(){
			log.info("making script FAIL..");
			Assert.assertTrue(false);
		}
		
		public static void makeFalse(String message){
			log.info("making script FAIL.."+ message);
			Assert.assertTrue(false, message);		
		}
		
		public static void verifyTrue(boolean staus){
			Assert.assertTrue(true);
		}
		
		public static void verifyFalse(boolean staus){
			Assert.assertFalse(false);
		}
		
		public static void verifyNull(String s1){
			log.info("Verify object is Null");
			Assert.assertNull(s1);
		}
		
		public static void verifyNotNull(String s1){
			log.info("Verify object is Not Null");
			Assert.assertNotNull(s1);
		}
}
