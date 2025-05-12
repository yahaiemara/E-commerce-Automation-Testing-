package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class elementAction {
    private elementAction(){}
    @Step("Send Data:{data} to the element: {locator}")
    public  static void  sendkey(WebDriver driver , By locator,String data){
        wait.waitForWeElementVisiable(driver,locator);
        scroll.scrollToElement(driver,locator);
        findelement(driver,locator).sendKeys(data);
        LogUtils.info("Data entered: ",data,"in the failed : ",locator.toString());
    }
    @Step("Click The Element: {locator}")
    public static void clickElement(WebDriver driver,By locator){
        wait.waitForWebElementclickability(driver,locator);
        scroll.scrollToElement(driver,locator);
        findelement(driver,locator).click();
        LogUtils.info("Clicked On The Element: ",locator.toString());

    }
    @Step("Get Text From The Element : {locator}")
    public static String getText(WebDriver driver,By locator){
        wait.waitForWeElementVisiable(driver,locator);
        scroll.scrollToElement(driver,locator);
        LogUtils.info("Getting Text From Element : ",locator.toString()," Text : ",findelement(driver,locator).getText());
        return findelement(driver,locator).getText();
    }
    public static WebElement findelement(WebDriver driver,By locator){
        LogUtils.info("Finding element: ",locator.toString());
        return driver.findElement(locator);
    }
}
