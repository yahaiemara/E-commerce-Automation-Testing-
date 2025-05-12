package com.swaglabs.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    public static File getLastestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogUtils.warn("No File Found in Directory : " + folderPath);
            return null;
        }
        File latestfile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestfile.lastModified()) {
                latestfile = file;

            }
        }
        return latestfile;
    }


    public static void deleteFiles(File dirPath) {
        if (dirPath == null || !dirPath.exists()) {
            LogUtils.warn("Directory does not exist: " + dirPath);
            return;
        }


        File[] filesList = dirPath.listFiles();
        if (filesList == null) {
            LogUtils.warn("Failed to list files in: " + dirPath);
            return;
        }

        for (File file : filesList) {
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogUtils.error("Failed to delete file: " + file);
                }
            }
        }
    }

}
