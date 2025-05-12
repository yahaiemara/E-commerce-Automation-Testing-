package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class scroll {

@Step("Scroll To The Element:{0}")
    public static void scrollToElement(WebDriver driver, By locator) {
        LogUtils.info("Scrolling To The Element: ", locator.toString());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementAction.findelement(driver, locator));
    }
}
