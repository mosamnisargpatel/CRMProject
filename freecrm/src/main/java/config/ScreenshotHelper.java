package config;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotHelper {
    
    public  String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshot directory if it doesn't exist
            String screenshotDir = "screenshots";
            Files.createDirectories(Paths.get(screenshotDir));
            
            // Generate unique filename with timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotDir + File.separator + fileName;
            
            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            
            return filePath;
        } catch (Exception e) {
            System.out.println("Exception occurred while taking screenshot: " + e.getMessage());
            return null;
        }
    }
}
