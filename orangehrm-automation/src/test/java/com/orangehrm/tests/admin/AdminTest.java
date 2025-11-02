package com.orangehrm.tests.admin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.tests.BaseTest;
import com.orangehrm.utils.ExtentReportManager;
import com.orangehrm.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AdminTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    
    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(driver);
    }
    
    @Test(priority = 1, description = "Verify left menu options count and navigate to Admin")
    public void testLeftMenuOptionsAndNavigateToAdmin() {
        test = ExtentReportManager.createTest("testLeftMenuOptionsAndNavigateToAdmin", 
                "Verify left menu has 12 options and navigate to Admin page");
        
        try {
            // Step 1: Login with valid credentials
            test.log(Status.INFO, "Step 1: Login with valid credentials");
            dashboardPage = loginPage.login("Admin", "admin123");
            Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Login should be successful");
            test.pass("Login successful with valid credentials");
            
            // Step 2: Get left menu options count
            test.log(Status.INFO, "Step 2: Get left menu options count");
            int menuCount = dashboardPage.getLeftMenuOptionsCount();
            dashboardPage.printLeftMenuOptions();
            
            // Step 3: Verify count is 12
            test.log(Status.INFO, "Step 3: Verify menu options count is 12");
            Assert.assertEquals(menuCount, 12, "Left menu should have exactly 12 options");
            test.pass("Left menu has " + menuCount + " options as expected");
            
            // Step 4: Navigate to Admin page
            test.log(Status.INFO, "Step 4: Navigate to Admin page");
            adminPage = dashboardPage.clickAdminMenu();
            Assert.assertTrue(adminPage.isAdminPageLoaded(), "Admin page should load successfully");
            test.pass("Successfully navigated to Admin page");
            
            // Capture final screenshot
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "LeftMenuOptionsTest");
            test.addScreenCaptureFromPath(screenshotPath);
            
            test.log(Status.PASS, "Left menu options test completed successfully");
            
        } catch (AssertionError e) {
            String failureScreenshot = ScreenshotUtil.captureScreenshot(driver, "LeftMenuOptionsTest_Failure");
            test.addScreenCaptureFromPath(failureScreenshot);
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            String exceptionScreenshot = ScreenshotUtil.captureScreenshot(driver, "LeftMenuOptionsTest_Exception");
            test.addScreenCaptureFromPath(exceptionScreenshot);
            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    @Test(priority = 2, description = "Search existing employee by username")
    public void testSearchByUsername() {
        test = ExtentReportManager.createTest("testSearchByUsername", 
                "Search existing employee by username 'Admin'");
        
        try {
            // Step 1: Login and navigate to Admin
            test.log(Status.INFO, "Step 1: Login and navigate to Admin page");
            dashboardPage = loginPage.login("Admin", "admin123");
            adminPage = dashboardPage.clickAdminMenu();
            Assert.assertTrue(adminPage.isAdminPageLoaded(), "Admin page should load");
            test.pass("Navigated to Admin page successfully");
            
            // Step 2: Search by username
            test.log(Status.INFO, "Step 2: Search by username 'Admin'");
            adminPage.searchByUsername("Admin");
            
            // Step 3: Get and verify record count
            test.log(Status.INFO, "Step 3: Verify search results");
            int recordCount = adminPage.getRecordCount();
            String recordText = adminPage.getRecordFoundText();
            
            Assert.assertTrue(recordCount >= 1, "Should find at least one record for username 'Admin'");
            test.pass("Search successful - " + recordText);
            
            // Step 4: Refresh page
            test.log(Status.INFO, "Step 4: Refresh page");
            adminPage.refreshPage();
            Assert.assertTrue(adminPage.isAdminPageLoaded(), "Admin page should reload after refresh");
            test.pass("Page refreshed successfully");
            
            // Capture screenshot
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "SearchByUsernameTest");
            test.addScreenCaptureFromPath(screenshotPath);
            
            test.log(Status.PASS, "Search by username test completed successfully");
            
        } catch (AssertionError e) {
            String failureScreenshot = ScreenshotUtil.captureScreenshot(driver, "SearchByUsernameTest_Failure");
            test.addScreenCaptureFromPath(failureScreenshot);
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Test(priority = 3, description = "Search existing employee by user role")
    public void testSearchByUserRole() {
        test = ExtentReportManager.createTest("testSearchByUserRole", 
                "Search existing employee by user role 'Admin'");
        
        try {
            // Step 1: Login and navigate to Admin
            test.log(Status.INFO, "Step 1: Login and navigate to Admin page");
            dashboardPage = loginPage.login("Admin", "admin123");
            adminPage = dashboardPage.clickAdminMenu();
            Assert.assertTrue(adminPage.isAdminPageLoaded(), "Admin page should load");
            test.pass("Navigated to Admin page successfully");
            
            // Step 2: Search by user role
            test.log(Status.INFO, "Step 2: Search by user role 'Admin'");
            adminPage.searchByUserRole("Admin");
            
            // Step 3: Get and verify record count
            test.log(Status.INFO, "Step 3: Verify search results");
            int recordCount = adminPage.getRecordCount();
            String recordText = adminPage.getRecordFoundText();
            
            Assert.assertTrue(recordCount >= 1, "Should find records for user role 'Admin'");
            test.pass("Search successful - " + recordText);
            
            // Step 4: Refresh page
            test.log(Status.INFO, "Step 4: Refresh page");
            adminPage.refreshPage();
            Assert.assertTrue(adminPage.isAdminPageLoaded(), "Admin page should reload after refresh");
            test.pass("Page refreshed successfully");
            
            // Capture screenshot
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "SearchByUserRoleTest");
            test.addScreenCaptureFromPath(screenshotPath);
            
            test.log(Status.PASS, "Search by user role test completed successfully");
            
        } catch (AssertionError e) {
            String failureScreenshot = ScreenshotUtil.captureScreenshot(driver, "SearchByUserRoleTest_Failure");
            test.addScreenCaptureFromPath(failureScreenshot);
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Test(priority = 4, description = "Search existing employee by user status")
    public void testSearchByUserStatus() {
        test = ExtentReportManager.createTest("testSearchByUserStatus", 
                "Search existing employee by user status 'Enabled'");
        
        try {
            // Step 1: Login and navigate to Admin
            test.log(Status.INFO, "Step 1: Login and navigate to Admin page");
            dashboardPage = loginPage.login("Admin", "admin123");
            adminPage = dashboardPage.clickAdminMenu();
            Assert.assertTrue(adminPage.isAdminPageLoaded(), "Admin page should load");
            test.pass("Navigated to Admin page successfully");
            
            // Step 2: Search by user status
            test.log(Status.INFO, "Step 2: Search by user status 'Enabled'");
            adminPage.searchByUserStatus("Enabled");
            
            // Step 3: Get and verify record count
            test.log(Status.INFO, "Step 3: Verify search results");
            int recordCount = adminPage.getRecordCount();
            String recordText = adminPage.getRecordFoundText();
            
            Assert.assertTrue(recordCount >= 1, "Should find records for status 'Enabled'");
            test.pass("Search successful - " + recordText);
            
            // Capture screenshot
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "SearchByUserStatusTest");
            test.addScreenCaptureFromPath(screenshotPath);
            
            test.log(Status.PASS, "Search by user status test completed successfully");
            
        } catch (AssertionError e) {
            String failureScreenshot = ScreenshotUtil.captureScreenshot(driver, "SearchByUserStatusTest_Failure");
            test.addScreenCaptureFromPath(failureScreenshot);
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }
}