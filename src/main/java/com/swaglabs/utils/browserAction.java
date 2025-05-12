package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;

public class browserAction {
    private browserAction() {
    }
    @Step("Navigate To Url: {url}")
    public static void navigateUrl(WebDriver driver, String url) {
        driver.get(url);
        LogUtils.info("NavigateUrl: ", url);
    }
    @Step("Get Current Url")
    public static String getcurrentUrl(WebDriver driver) {
        LogUtils.info("Current Url: ",driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    @Step("Get Title Page")
    public static String getTitle(WebDriver driver) {
        LogUtils.info("The Title Is: ",driver.getTitle());
        return driver.getTitle();
    }
  @Step("Refreshing The Page")
    public static void refreshPage(WebDriver driver) {
        LogUtils.info("Refreshing The Page");
        driver.navigate().refresh();
    }

}
