package com.orangehrm.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.orangehrm.utils.ScreenshotUtil;
import com.orangehrm.utils.ExtentReportManager;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        // Capture screenshot on test failure
        Object currentClass = result.getInstance();
        // You can add more logic here if needed
    }
}