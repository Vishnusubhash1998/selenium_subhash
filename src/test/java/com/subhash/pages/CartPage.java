package com.subhash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartTitle = By.cssSelector(".title"); // "Your Cart"
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // First, the cart page title element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        // And the checkout button is present (page fully ready)
        wait.until(ExpectedConditions.presenceOfElementLocated(checkoutBtn));
    }

    public CheckoutInfoPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutInfoPage(driver);
    }
}
