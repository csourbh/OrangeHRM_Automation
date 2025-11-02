package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    public static ExtentReports createInstance() {
        // Create extent-reports folder if it doesn't exist
        File reportsDir = new File("extent-reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "extent-reports/OrangeHRM_Report_" + timeStamp + ".html";
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        sparkReporter.config().setDocumentTitle("Orange HRM Automation Test Report");
        sparkReporter.config().setReportName("Login Module Automation Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // System Information
        extent.setSystemInfo("Organization", "Orange HRM");
        extent.setSystemInfo("Automation QA", "Your Name");
        extent.setSystemInfo("Project", "HRM Automation");
        extent.setSystemInfo("Test Type", "Regression");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        
        return extent;
    }
    
    public static ExtentTest createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
        return extentTest;
    }
    
    public static ExtentTest getTest() {
        return test.get();
    }
    
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}