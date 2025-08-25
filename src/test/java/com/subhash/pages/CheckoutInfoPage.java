package com.subhash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CheckoutInfoPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.id("first-name");
    private final By lastName  = By.id("last-name");
    private final By postal    = By.id("postal-code");
    private final By contBtn   = By.id("continue");

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public CheckoutInfoPage fill(String f, String l, String zip) {
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postal).sendKeys(zip);
        return this;
    }

    public CheckoutOverviewPage continueNext() {
        driver.findElement(contBtn).click();
        return new CheckoutOverviewPage(driver);
    }
}
