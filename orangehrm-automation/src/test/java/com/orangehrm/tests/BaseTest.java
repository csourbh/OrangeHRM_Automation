package com.orangehrm.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ExtentReportManager;
import com.orangehrm.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    
    @BeforeMethod
    public void setUp() {
        // Initialize Extent Reports
        extent = ExtentReportManager.createInstance();
        
        // Setup WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        // Browser configuration
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getTimeout()));
        driver.manage().deleteAllCookies();
        
        // Navigate to application
        driver.get(ConfigReader.getUrl());
        System.out.println("Navigated to: " + ConfigReader.getUrl());
        
        // Capture initial page screenshot
        ScreenshotUtil.captureScreenshot(driver, "InitialPage");
    }
    
    @AfterMethod
    public void tearDown() {
        // Capture final screenshot before closing
        ScreenshotUtil.captureScreenshot(driver, "FinalPage");
        
        // Close browser
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
        
        // Flush extent reports
        ExtentReportManager.flushReport();
    }
}