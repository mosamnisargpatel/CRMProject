package Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import Pages.Login;
import config.openbrowser;
import config.ExcelDataConfig;
import config.ExtentReportManager;
import config.ScreenshotHelper;



public class Loginpage extends ExtentReportManager {
    WebDriver d1;
    openbrowser open;
    Login l;
    boolean help1;
     ScreenshotHelper sh = new ScreenshotHelper();
     
     @BeforeMethod
     public void browseropen() throws Exception
     {
    	    open = new openbrowser();
            open.launchbrowser();
     }
    
    @Test(dataProvider = "login")
    public void homepagenavigation(int rownum, String username, String password) throws Exception {
      
        
         
    
            d1 = open.getDriver();          
            l = new Login(d1);
            // ensure an Extent test exists for this test method
            config.ExtentReportManager.createTest("Loginpage");      
           
         if(rownum == 0 )
         {
           test.info("Login with valid credential");
            l.enterusername(username);   
            l.enterpassword(password);
            Thread.sleep(2000);
            l.clickloginbutton();

            Thread.sleep(2000);
            help1 = l.help();
            assertTrue(help1);  
         }
         
         if(rownum ==1)
         {
        	    test.info("Login with invalid credential");
                l.enterusername(username);   
                l.enterpassword(password);
                Thread.sleep(2000);
                l.clickloginbutton();

                Thread.sleep(2000);
               assertEquals(d1.getTitle(), "Free CRM");
         }
            
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
    
    	String path = null;
    	
		if (result.getStatus() == ITestResult.SUCCESS)
		{
			
			path = sh.takeScreenshot(open.getDriver(), result.getTestName());
			if(path !=null)
			{
			test.pass(com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
		} 
		else if (result.getStatus() == ITestResult.FAILURE)
		{
			
			path = sh.takeScreenshot(open.getDriver(), result.getTestName());
			 if(path!=null) {
			test.fail(com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			 }
			
		}
		 System.out.print(path);
		open.closebrowser();
    }
    
    @DataProvider(name = "login")
    public Object[][] testdata() throws Exception
    {
    	String Excelpath = System.getProperty("user.dir") + File.separator  + "TestData" + File.separator + "mosampatel175gmailcom.xlsx";
    	ExcelDataConfig config =  new ExcelDataConfig(Excelpath);
        int row = config.getrownum(0);
        int i;
        Object[][] data = new Object[row][3];
        for(i=0 ; i<row; i++)
       {
        data[i][0] = i;
      	data[i][1] = config.getdata(i, 0);
      	data[i][2] = config.getdata(i, 1);
        	
        }
           return data;
   }
}
