package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ExcelReader;
import com.orangehrm.utils.ExtentReportManager;
import com.orangehrm.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String filePath = "src/test/resources/testdata/LoginTestData.xlsx";
        return ExcelReader.getTestData(filePath, "Sheet1").toArray(new Object[0][]);
    }
    
    @Test(dataProvider = "loginData", description = "Test login functionality with multiple datasets")
    public void testLoginWithMultipleData(String testCaseID, String username, String password, 
                                        String expectedResult, String description) {
        // Create test in extent report
        test = ExtentReportManager.createTest(testCaseID, description);
        test.log(Status.INFO, "Starting test: " + testCaseID);
        test.log(Status.INFO, "Description: " + description);
        test.log(Status.INFO, "Username: " + username + ", Password: " + password);
        
        LoginPage loginPage = new LoginPage(driver);
        
        try {
            // Step 1: Verify login page is displayed
            test.log(Status.INFO, "Step 1: Verify login page is displayed");
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
            test.pass("Login page is displayed successfully");
            
            // Step 2: Enter username with screenshot
            test.log(Status.INFO, "Step 2: Enter username: " + username);
            loginPage.enterUsername(username);
            test.pass("Username entered successfully");
            
            // Step 3: Enter password with screenshot
            test.log(Status.INFO, "Step 3: Enter password");
            loginPage.enterPassword(password);
            test.pass("Password entered successfully");
            
            // Step 4: Click login button with screenshot
            test.log(Status.INFO, "Step 4: Click login button");
            loginPage.clickLogin();
            test.pass("Login button clicked successfully");
            
            // Wait for login process to complete
            Thread.sleep(3000);
            
            // Capture final result screenshot
            String finalScreenshot = ScreenshotUtil.captureScreenshot(driver, "FinalResult_" + testCaseID);
            test.addScreenCaptureFromPath(finalScreenshot);
            
            // Step 5: Verify login result based on expected outcome
            if ("PASS".equals(expectedResult)) {
                test.log(Status.INFO, "Step 5: Verify successful login");
                Assert.assertTrue(loginPage.isLoginSuccessful(), 
                    "Login should be successful with valid credentials");
                test.pass("Login successful - User redirected to dashboard");
                
                // Step 6: Perform logout for successful login
                test.log(Status.INFO, "Step 6: Perform logout");
                loginPage.logout();
                Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
                    "Should be redirected to login page after logout");
                test.pass("Logout successful - Redirected to login page");
                
            } else {
                test.log(Status.INFO, "Step 5: Verify login failure with invalid credentials");
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                    "Error message should be displayed for invalid credentials");
                String errorMessage = loginPage.getErrorMessage();
                test.pass("Login failed as expected - Error message: " + errorMessage);
            }
            
            test.log(Status.PASS, "Test " + testCaseID + " completed successfully");
            
        } catch (AssertionError e) {
            // Capture screenshot on assertion failure
            String failureScreenshot = ScreenshotUtil.captureScreenshot(driver, "AssertionFailure_" + testCaseID);
            test.addScreenCaptureFromPath(failureScreenshot);
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
            
        } catch (Exception e) {
            // Capture screenshot on exception
            String exceptionScreenshot = ScreenshotUtil.captureScreenshot(driver, "Exception_" + testCaseID);
            test.addScreenCaptureFromPath(exceptionScreenshot);
            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}