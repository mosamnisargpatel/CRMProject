package config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager extends EmailSender {
    public static ExtentReports extent;
    public static ExtentTest test;


    @BeforeSuite
    public void initializeReport() {
        if (extent == null) {
            extent = new ExtentReports();
            String reportPath = System.getProperty("user.dir") + File.separator + "ExtentReport.html"; ;
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent.attachReporter(spark);
        }
    }

    public static void createTest(String testName) {
        if (extent == null) {
            // ensure extent initialized (static block should have run, but double-check)
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            extent.attachReporter(spark);
        }
        test = extent.createTest(testName);
    }

    @AfterSuite
    public void flushReport() throws Exception {
        extent.flush();
        sendemail();
       
         }
    

}
