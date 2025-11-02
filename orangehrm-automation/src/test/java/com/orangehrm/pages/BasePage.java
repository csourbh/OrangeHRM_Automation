package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.orangehrm.utils.ScreenshotUtil;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    
    protected void clickElement(WebElement element, String elementName) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        ScreenshotUtil.captureScreenshotAtEveryStep(driver, "Clicked_" + elementName);
        System.out.println("Clicked on: " + elementName);
    }
    
    protected void enterText(WebElement element, String text, String elementName) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        ScreenshotUtil.captureScreenshotAtEveryStep(driver, "Entered_" + elementName + "_" + text);
        System.out.println("Entered text '" + text + "' in: " + elementName);
    }
    
    protected void selectFromDropdown(WebElement dropdown, String value, String dropdownName) {
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
        ScreenshotUtil.captureScreenshotAtEveryStep(driver, "Selected_" + dropdownName + "_" + value);
        System.out.println("Selected '" + value + "' from: " + dropdownName);
    }
    
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    protected void waitForPageToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}