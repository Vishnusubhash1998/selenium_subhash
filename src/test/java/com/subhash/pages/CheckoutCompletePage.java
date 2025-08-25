package com.subhash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutCompletePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By header = By.cssSelector(".complete-header"); // "Thank you for your order!"

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public String successMessage() {
        return driver.findElement(header).getText();
    }
}
