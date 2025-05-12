package com.swagslabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.AllureUtils;
import com.swaglabs.utils.FileUtils;
import com.swaglabs.utils.ScreenShotsUtils;
import com.swaglabs.utils.validations;
import org.aspectj.lang.annotation.After;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;

public class LoginTestCases {

    File allure_results = new File("test-outputs/allure-results");

    @BeforeSuite
    public void beforeSuit() throws InterruptedException {

        FileUtils.deleteFiles(allure_results);
    }

    @BeforeTest
    public void setup() {
        DriverManager.createInstance("chrome");
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage();
    }

    @AfterTest
    public void exit() {
        DriverManager.getDriver().quit();
    }

    @AfterClass
    public void afterclass() {
        AllureUtils.attachLogsToAllure();
    }

    @Test
    public void successfullyLogin() throws IOException {
        new LoginPage(DriverManager.getDriver()).enterUsername("standard_user").
                enterpassword("secret_sauce").clickLoginButton().assertLoginSoft();
        ScreenShotsUtils.takeScreenShots("screenshot-login");


    }

    @Test(dependsOnMethods = {"successfullyLogin"})
    public void unsuccessfullyLogin() {
        new LoginPage(DriverManager.getDriver()).enterUsername("standard_user").
                enterpassword("invalid password").clickLoginButton().assertunsuccessLoginPage();
    }

    @Test
    public void loginWithEmptyFields() {
        new LoginPage(DriverManager.getDriver())
                .enterUsername("")
                .enterpassword("")
                .clickLoginButton();
        validations.validateEqual(
                "Epic sadface: Username is required",
                new LoginPage(DriverManager.getDriver()).getmessageerror(),
                "The Expected Is Match With Actual ");

    }

    @Test
    public void loginWithOnlyUsername() {
        new LoginPage(DriverManager.getDriver())
                .enterUsername("standard_user")
                .enterpassword("")
                .clickLoginButton();
        validations.validateEqual(
                "Epic sadface: Password is required",
                new LoginPage(DriverManager.getDriver()).getmessageerror(),
                "The Expected Is Match With Actual");
    }

    @Test(dependsOnMethods = {"successfullyLogin"})
    public void logoutAfterLogin() {
        new LoginPage(DriverManager.getDriver())
                .logout();

    }
}
