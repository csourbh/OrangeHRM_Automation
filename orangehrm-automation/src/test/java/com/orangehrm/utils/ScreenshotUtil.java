package com.orangehrm.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create screenshots folder if it doesn't exist
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }
            
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destination = "screenshots/" + screenshotName + "_" + timeStamp + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
            System.out.println("Screenshot captured: " + destination);
            return destination;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return "";
        }
    }
    
    public static String captureScreenshotAtEveryStep(WebDriver driver, String stepName) {
        return captureScreenshot(driver, stepName);
    }
}