package com.subhash.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// optional: headless support
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void init() {
        // tweak options if you want headless:
        ChromeOptions opts = new ChromeOptions();
        // opts.addArguments("--headless=new");
        // opts.addArguments("--window-size=1400,1000");
        // ✅ Keep tests clean: disable password manager & leak detection bubbles
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        opts.setExperimentalOption("prefs", prefs);

        // Also turn off the leak detection & some autofill UI
        opts.addArguments(
                "--disable-features=PasswordLeakDetection,AutofillServerCommunication,AutofillEnableAccountWalletStorage"
        );

        // Optional: isolate from your main Chrome user profile
        // opts.addArguments("--incognito"); // quick isolation
        // OR use a dedicated profile folder:
        // opts.addArguments("user-data-dir=C:/Users/subha/AppData/Local/Google/Chrome/SeleniumProfile");

        TL.set(new ChromeDriver(opts));
    }

    public static WebDriver get() { return TL.get(); }

    public static void quit() {
        if (TL.get() != null) {
            TL.get().quit();
            TL.remove();
        }
    }
}
