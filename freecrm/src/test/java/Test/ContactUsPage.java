package Test;


import static org.testng.Assert.assertTrue;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Login;
import Pages.cotactus;
import config.ExtentReportManager;
import config.ScreenshotHelper;


public class ContactUsPage extends ExtentReportManager {
	Login l1;
	cotactus c;
	config.openbrowser ob;
	ScreenshotHelper sh = new ScreenshotHelper();

	@BeforeMethod
    public void openurl() throws Exception
     { 
		ob = new config.openbrowser();
	    ob.launchbrowser();
     }

	
	@Test
	public void contactuspage() throws Exception
	{
		
		 l1 = new Login(ob.getDriver());
		 c = new cotactus(ob.getDriver());
		
		 createTest("Contactus page");
		 l1.enterusername("mosampatel175@gmail.com");
	     l1.enterpassword("Mosam_123");
	     Thread.sleep(2000);
	     l1.clickloginbutton();
	     Thread.sleep(2000);
	        
	        c.user();
	        c.contactus();
	        Thread.sleep(3000);
	     
	        c.firstname("Samarth");
	        c.lastname("Patel");
	        c.save();
	        
	        boolean task1 = c.task();
	        assertTrue(task1);;
	  
	        
	}

    @AfterMethod
    public void tearDown1(ITestResult result)
    {
	      String path = null;
		   if (result.getStatus() == ITestResult.SUCCESS)
		    {	
			   test.info("Create new contact");
		 	 path = sh.takeScreenshot(ob.getDriver(), result.getTestName());
			 test.pass(com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		    } 
	       else if (result.getStatus() == ITestResult.FAILURE)
		    {
	    	   test.info("Create new contact");
    		 path = sh.takeScreenshot(ob.getDriver(), result.getTestName());			
			 test.fail(com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		     }
		ob.closebrowser();

     }
}
