package com.swaglabs.drivers;

import com.swaglabs.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.fail;

public class DriverManager {
    // متغير ThreadLocal لحفظ WebDriver لكل Thread على حدة
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // كونستركتور خاص عشان ماحدش يقدر يعمل كائن من الكلاس
    private DriverManager() {
        super();
    }
    @Step(value = "Create driver instance on:{browserName} ")
   public static WebDriver createInstance(String browserName){
        WebDriver driver=BrowserFactory.getBrowser(browserName);
       LogUtils.info("Driver Created On :",browserName);
        setDriver(driver);
        return getDriver();
   }
    // ميثود عامة ستيتك بترجع WebDriver الخاص بالـ Thread الحالي
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            LogUtils.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }
    // ميثود عامة ستيتك بتحدد الـ WebDriver الخاص بالـ Thread الحالي
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }
}
