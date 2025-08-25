package com.subhash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By title = By.cssSelector(".title"); // "Checkout: Overview"
    private final By finishBtn = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(title, "Checkout: Overview"));
    }

    public CheckoutCompletePage finish() {
        driver.findElement(finishBtn).click();
        return new CheckoutCompletePage(driver);
    }
}
