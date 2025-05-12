package com.swaglabs.utils;
import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    public static final String allure_result = "test-outputs/allure-results";

    private AllureUtils() {
        super();
    }

    public static void attachLogsToAllure() {
        try {
            File logfile = FileUtils.getLastestFile(LogUtils.Logs_Results);

            if (!logfile.exists()) {
                LogUtils.warn("Log File Dose Not Exists: " + LogUtils.Logs_Results);
                return;
            }
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logfile.getPath())));
            LogUtils.info("Logs attach in Allure report");
        } catch (Exception e) {
            LogUtils.warn("SomeThing wrung in LogsFile : " + e.getMessage());
        }
    }

    public static void attachScreenShotToAllure(String screenshotName, String screenshotPath) {
        try {
            // التحقق إذا كان المسار موجودًا أولاً
            File screenshotFile = new File(screenshotPath);
            if (!screenshotFile.exists()) {
                throw new FileNotFoundException("Screenshot file not found at: " + screenshotPath);
            }

            // إضافة الصورة إلى Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));

        } catch (FileNotFoundException e) {
            LogUtils.error("File not found: " + e.getMessage());
        } catch (IOException e) {
            LogUtils.error("IOException occurred while attaching screenshot: " + e.getMessage());
        } catch (Exception e) {
            LogUtils.error("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }

}
