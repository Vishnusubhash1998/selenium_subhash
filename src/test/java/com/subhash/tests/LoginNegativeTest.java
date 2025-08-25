package com.subhash.tests;

import com.subhash.core.BaseTest;
import com.subhash.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativeTest extends BaseTest {

    @Test
    public void showsErrorForInvalidPassword() {
        String errorMsg = new LoginPage(driver)
                .open()
                .typeUsername("standard_user")
                .typePassword("wrong_password")
                .submitInvalid()
                .getErrorText();

        System.out.println("Error banner: " + errorMsg);

        Assert.assertTrue(
                errorMsg.toLowerCase().contains("username and password do not match"),
                "Expected invalid-credentials error, but got: " + errorMsg
        );
    }
}
