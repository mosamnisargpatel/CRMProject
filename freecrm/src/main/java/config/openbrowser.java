package config;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class openbrowser {
	
		public Properties p;
	     WebDriver d1;
	    
	
		public WebDriver getDriver() {
			return d1;
		}
		

    public void launchbrowser() throws Exception {
         
      // String propertyPath = System.getProperty("user.dir");
    	   String projectPath = System.getProperty("user.dir");
           String configPath = projectPath + "\\config1.properties";
        FileInputStream fis = new FileInputStream(configPath);
        p = new Properties();
        p.load(fis);
        String browser = p.getProperty("Browser");
        
        if (browser.equalsIgnoreCase("Chrome")) {
        	ChromeOptions op = new ChromeOptions();
        	op.setAcceptInsecureCerts(true);
        	op.addArguments("Ignore certificate error");
            WebDriverManager.chromedriver().setup();
            d1 = new ChromeDriver(op);
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            d1 = new FirefoxDriver();
        
        }

        d1.manage().window().maximize();
     
        d1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        d1.get(p.getProperty("URL"));
        
    }

	
    public void closebrowser() {
      if (d1 != null) {
          
      	d1.quit();
      }	 
        
       }   
      
    }




