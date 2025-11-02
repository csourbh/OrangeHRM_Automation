package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.orangehrm.utils.ScreenshotUtil;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    @FindBy(name = "username")
    private WebElement usernameField;
    
    @FindBy(name = "password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    
    @FindBy(className = "oxd-userdropdown-name")
    private WebElement userDropdown;
    
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;
    
    @FindBy(className = "oxd-alert-content-text")
    private WebElement errorMessage;
    
    @FindBy(className = "oxd-text--h6")
    private WebElement dashboardHeader;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
        // Capture screenshot after entering username
        ScreenshotUtil.captureScreenshotAtEveryStep(driver, "EnteredUsername_" + username);
    }
    
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
        // Capture screenshot after entering password
        ScreenshotUtil.captureScreenshotAtEveryStep(driver, "EnteredPassword");
    }
    
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        // Capture screenshot after clicking login
        ScreenshotUtil.captureScreenshotAtEveryStep(driver, "ClickedLogin");
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    public void logout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(userDropdown));
            userDropdown.click();
            wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
            logoutLink.click();
            // Wait for login page to load after logout
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            // Capture screenshot after logout
            ScreenshotUtil.captureScreenshotAtEveryStep(driver, "AfterLogout");
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
        }
    }
    
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "Error message not found";
        }
    }
    
    public boolean isLoginPageDisplayed() {
        try {
            return usernameField.isDisplayed() && passwordField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}