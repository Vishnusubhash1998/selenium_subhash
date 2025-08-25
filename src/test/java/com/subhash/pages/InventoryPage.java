package com.subhash.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class InventoryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.cssSelector(".title"); // "Products"
    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");
    private final By cartIcon = By.id("shopping_cart_container");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Products"));
    }

    public String title() {
        // read the text from the page title element
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public InventoryPage addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBackpack)).click();
        // confirm add by waiting for either badge to appear or button to switch to Remove
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(cartBadge),
                ExpectedConditions.presenceOfElementLocated(removeBackpack)
        ));
        return this;
    }

    public CartPage goToCart() {
        // Use the link itself, not the outer container
        By cartLink = By.cssSelector("a.shopping_cart_link");

        // 1) Wait until clickable and click
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        link.click();

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            shortWait.until(ExpectedConditions.urlContains("cart.html"));
        } catch (TimeoutException e) {
            // 2) Fallback: JS click (in case of overlay/paint timing)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            try {
                shortWait.until(ExpectedConditions.urlContains("cart.html"));
            } catch (TimeoutException e2) {
                // 3) Last fallback: send ENTER on the link
                link.sendKeys(Keys.ENTER);
                shortWait.until(ExpectedConditions.urlContains("cart.html"));
            }
        }

        return new CartPage(driver);
    }
}
