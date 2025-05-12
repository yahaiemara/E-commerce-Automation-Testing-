package com.swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class BrowserFactory {
    private BrowserFactory(){

    }
    public static WebDriver getBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-notifications");
                options.addArguments("--remote-allow-origins=*");
                Map<String, Object> prefs = Map.of(
                        "profile.default_content_setting_values.notifications", 2,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false
                );
                options.setExperimentalOption("prefs", prefs);
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                // options.addArguments("--headless");
                return new ChromeDriver(options);
            case "firefox":
                FirefoxOptions Fireoptions = new FirefoxOptions();
                Fireoptions.addArguments("--start-maximized");
                Fireoptions.addArguments("--disable-extensions");
                Fireoptions.addArguments("--disable-infobars");
                Fireoptions.addArguments("--disable-notifications");
                Fireoptions.addArguments("--remote-allow-origins=*");
                Fireoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                Fireoptions.setAcceptInsecureCerts(true);
                return new FirefoxDriver(Fireoptions);
            default:
                EdgeOptions Edgoptions = new EdgeOptions();
                Edgoptions.addArguments("--start-maximized");
                Edgoptions.addArguments("--disable-extensions");
                Edgoptions.addArguments("--disable-infobars");
                Edgoptions.addArguments("--disable-notifications");
                Edgoptions.addArguments("--remote-allow-origins=*");
                Map<String, Object> Edgeprefs = Map.of(
                        "profile.default_content_setting_values.notifications", 2,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile_enabled", false
                );
                Edgoptions.setExperimentalOption("prefs", Edgeprefs);
                Edgoptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                // options.addArguments("--headless");
                return new EdgeDriver(Edgoptions);
        }
    }

}
