package com.subhash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By user = By.id("user-name");
    private final By pass = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public LoginPage typeUsername(String u) {
        wait.until(ExpectedConditions.elementToBeClickable(user)).clear();
        driver.findElement(user).sendKeys(u);
        return this;
    }

    public LoginPage typePassword(String p) {
        driver.findElement(pass).clear();
        driver.findElement(pass).sendKeys(p);
        return this;
    }

    public InventoryPage submitValid() {
        driver.findElement(loginBtn).click();
        return new InventoryPage(driver);
    }

    public LoginPage submitInvalid() {
        driver.findElement(loginBtn).click();
        return this;
    }

    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).getText();
    }
}
