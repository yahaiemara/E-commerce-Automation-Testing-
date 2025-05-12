package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class validations {

@Step("Validate True")
    public static void validateTrue(Boolean condation, String message) {

        Assert.assertEquals(condation, message);
    }
 @Step("Validate False")
    public static void validatefalse(Boolean condation, String message) {

        Assert.assertNotEquals(condation, message);
    }
@Step("Validate Equal")
    public static void validateEqual( String expected,String actual, String message) {
        Assert.assertEquals(actual, expected, message);
    }
@Step("Validate Not Equal")
    public static void vlaidateNotEqual(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }
@Step("Validate Page Url: {expected}")
    public static void vlaidatapageurl(WebDriver driver, String expected) {
        Assert.assertEquals(browserAction.getcurrentUrl(driver), expected);
    }
@Step("Validate Page Title: {expected}")
    public static void vlaidategetpagetitle(WebDriver driver, String expected) {
        Assert.assertEquals(browserAction.getTitle(driver), expected);
    }



}
