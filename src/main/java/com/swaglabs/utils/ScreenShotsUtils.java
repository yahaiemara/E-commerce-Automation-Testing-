package com.swaglabs.utils;

import com.swaglabs.drivers.DriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShotsUtils {
    public static final String ScreenShot_Path="test-outputs/screenshots";
    private ScreenShotsUtils() {
        super();
    }

    public static void takeScreenShots(String screenshotName) throws IOException {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

            // استخدام اسم الصورة بشكل صحيح
            File screenshotFile = new File(ScreenShot_Path + "/" + screenshotName + ".png");

            // التأكد من أن الدليل موجود قبل نسخ الصورة
            File directory = new File(ScreenShot_Path);
            if (!directory.exists()) {
                directory.mkdirs(); // إذا لم يكن المجلد موجوداً، قم بإنشائه
            }

            // نسخ الصورة إلى المسار المحدد
            FileUtils.copyFile(screenshot, screenshotFile);
            AllureUtils.attachScreenShotToAllure(screenshotName, screenshotFile.getPath());
        } catch (Exception e) {
            LogUtils.error("Failed To Take ScreenShots " + e.getMessage());
        }
    }

}
