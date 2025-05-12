package com.swaglabs.utils;

import io.opentelemetry.sdk.trace.data.ExceptionEventData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
// present-visiable-clickable
public class wait {
    private wait(){}
    public static WebElement waitForWebElementPresent(WebDriver driver, By locator){
        LogUtils.info("Wanting For Element Present: ",locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver1->driver1.findElement(locator));
    }

    //visiable
    public static WebElement waitForWeElementVisiable(WebDriver driver,By locator){
       LogUtils.info("Waiting For Element Visible: ",locator.toString());
        return new WebDriverWait(driver,Duration.ofSeconds(5)).until(
                driver1->{
                    WebElement element=waitForWebElementPresent(driver,locator);
                    return element.isDisplayed()?element :null;
                }
        );
    }
    //clickability
    public static WebElement waitForWebElementclickability(WebDriver driver,By locator){
      LogUtils.info("Waiting Form Element To Clickability");
        return new WebDriverWait(driver,Duration.ofSeconds(5)).until(driver1->{
            WebElement element=waitForWeElementVisiable(driver,locator);
            return element.isEnabled()?element:null;
        });
    }
}
