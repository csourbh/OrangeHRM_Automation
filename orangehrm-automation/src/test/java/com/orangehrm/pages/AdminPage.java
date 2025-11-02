package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class AdminPage extends BasePage {
    
    // Admin Page Locators
    @FindBy(xpath = "//h5[text()='System Users']")
    private WebElement systemUsersHeader;
    
    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement usernameSearchField;
    
    @FindBy(xpath = "//label[text()='User Role']/following::div[1]")
    private WebElement userRoleDropdown;
    
    @FindBy(xpath = "//label[text()='Status']/following::div[1]")
    private WebElement statusDropdown;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//button[text()=' Reset ']")
    private WebElement resetButton;
    
    @FindBy(className = "oxd-loading-spinner")
    private WebElement loadingSpinner;
    
    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span")
    private WebElement recordFoundText;
    
    @FindBy(xpath = "//div[@class='oxd-table-card']")
    private List<WebElement> searchResults;
    
    // Dropdown options locators
    @FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
    private List<WebElement> dropdownOptions;
    
    public AdminPage(WebDriver driver) {
        super(driver);
    }
    
    // Verify Admin page is loaded
    public boolean isAdminPageLoaded() {
        return isElementDisplayed(systemUsersHeader);
    }
    
    // Search by Username
    public void searchByUsername(String username) {
        enterText(usernameSearchField, username, "UsernameSearchField");
        clickElement(searchButton, "SearchButton");
        waitForSearchResults();
    }
    
    // Search by User Role
    public void searchByUserRole(String userRole) {
        // Click to open dropdown
        clickElement(userRoleDropdown, "UserRoleDropdown");
        
        // Select option from dropdown
        selectDropdownOption(userRole);
        
        clickElement(searchButton, "SearchButton");
        waitForSearchResults();
    }
    
    // Search by Status
    public void searchByUserStatus(String status) {
        // Click to open dropdown
        clickElement(statusDropdown, "StatusDropdown");
        
        // Select option from dropdown
        selectDropdownOption(status);
        
        clickElement(searchButton, "SearchButton");
        waitForSearchResults();
    }
    
    // Method to select dropdown option
    private void selectDropdownOption(String optionText) {
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(optionText)) {
                clickElement(option, "DropdownOption_" + optionText);
                break;
            }
        }
    }
    
    // Reset search
    public void resetSearch() {
        clickElement(resetButton, "ResetButton");
        waitForPageToLoad();
    }
    
    // Get record count from search results
    public int getRecordCount() {
        try {
            if (isElementDisplayed(recordFoundText)) {
                String recordText = recordFoundText.getText();
                // Extract number from text like "(1) Record Found"
                if (recordText.contains("Record Found")) {
                    String countText = recordText.split("\\(")[1].split("\\)")[0];
                    return Integer.parseInt(countText);
                }
            }
            return searchResults.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public String getRecordFoundText() {
        return isElementDisplayed(recordFoundText) ? recordFoundText.getText() : "No records found";
    }
    
    // Wait for search results to load
    private void waitForSearchResults() {
        try {
            // Wait for loading spinner to disappear
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Refresh page
    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageToLoad();
    }
}