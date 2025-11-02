package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    
    // Page Locators
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
    
    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenu;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    // Page Methods
    public void enterUsername(String username) {
        enterText(usernameField, username, "UsernameField");
    }
    
    public void enterPassword(String password) {
        enterText(passwordField, password, "PasswordField");
    }
    
    public void clickLogin() {
        clickElement(loginButton, "LoginButton");
    }
    
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        waitForPageToLoad();
        return new DashboardPage(driver);
    }
    
    public void logout() {
        try {
            clickElement(userDropdown, "UserDropdown");
            clickElement(logoutLink, "LogoutLink");
            waitForPageToLoad();
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
        }
    }
    
    public boolean isLoginSuccessful() {
        return isElementDisplayed(dashboardHeader);
    }
    
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (Exception e) {
            return "Error message not found";
        }
    }
    
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(usernameField) && isElementDisplayed(passwordField);
    }
    
    public AdminPage navigateToAdminPage() {
        clickElement(adminMenu, "AdminMenu");
        waitForPageToLoad();
        return new AdminPage(driver);
    }
}