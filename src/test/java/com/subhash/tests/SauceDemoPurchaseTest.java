package com.subhash.tests;

import com.subhash.core.BaseTest;
import com.subhash.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoPurchaseTest extends BaseTest {

    @Test
    public void userCanBuyBackpack() {
        // Login
        var login = new LoginPage(driver).open()
                .typeUsername("standard_user")
                .typePassword("secret_sauce");
        var inventory = login.submitValid();
        Assert.assertEquals(inventory.title(), "Products", "Should land on Products page");

        // Add to cart
        inventory.addBackpackToCart();
        var cart = inventory.goToCart();

        // Checkout flow
        var info = cart.checkout();
        var overview = info.fill("Subhash", "A", "500001").continueNext();
        var complete = overview.finish();

        // Assert success
        Assert.assertTrue(complete.successMessage().toLowerCase().contains("thank you"),
                "Expected order confirmation message");
    }
}
