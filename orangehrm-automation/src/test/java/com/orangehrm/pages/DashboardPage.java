package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class DashboardPage extends BasePage {
    
    // Left side menu options locators
    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li/a")
    private List<WebElement> leftMenuOptions;
    
    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenu;
    
    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;
    
    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;
    
    @FindBy(xpath = "//span[text()='Time']")
    private WebElement timeMenu;
    
    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentMenu;
    
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoMenu;
    
    @FindBy(xpath = "//span[text()='Performance']")
    private WebElement performanceMenu;
    
    @FindBy(xpath = "//span[text()='Dashboard']")
    private WebElement dashboardMenu;
    
    @FindBy(xpath = "//span[text()='Directory']")
    private WebElement directoryMenu;
    
    @FindBy(xpath = "//span[text()='Maintenance']")
    private WebElement maintenanceMenu;
    
    @FindBy(xpath = "//span[text()='Claim']")
    private WebElement claimMenu;
    
    @FindBy(xpath = "//span[text()='Buzz']")
    private WebElement buzzMenu;
    
    @FindBy(className = "oxd-userdropdown-name")
    private WebElement userDropdown;
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    // Method to get all left menu options count and names
    public int getLeftMenuOptionsCount() {
        waitForPageToLoad();
        return leftMenuOptions.size();
    }
    
    public List<String> getLeftMenuOptionsNames() {
        waitForPageToLoad();
        return leftMenuOptions.stream()
                .map(WebElement::getText)
                .toList();
    }
    
    public void printLeftMenuOptions() {
        List<String> menuOptions = getLeftMenuOptionsNames();
        System.out.println("Total left menu options: " + menuOptions.size());
        System.out.println("Menu Options:");
        for (int i = 0; i < menuOptions.size(); i++) {
            System.out.println((i + 1) + ". " + menuOptions.get(i));
        }
    }
    
    public AdminPage clickAdminMenu() {
        clickElement(adminMenu, "AdminMenu");
        waitForPageToLoad();
        return new AdminPage(driver);
    }
    
    public boolean isDashboardDisplayed() {
        return isElementDisplayed(userDropdown);
    }
}