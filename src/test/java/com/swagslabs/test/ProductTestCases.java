package com.swagslabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.utils.AllureUtils;
import com.swaglabs.utils.FileUtils;
import org.testng.annotations.*;

import java.io.File;

public class ProductTestCases {
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
//        DriverManager.getDriver().quit();
    }

    @AfterClass
    public void afterclass() {
        AllureUtils.attachLogsToAllure();
    }

    @Test(priority = 1)
    public void loginAndOpenProductPage() {
        new LoginPage(DriverManager.getDriver())
                .enterUsername("standard_user")
                .enterpassword("secret_sauce")
                .clickLoginButton();

        ProductPage productPage = new ProductPage(DriverManager.getDriver());
        productPage.navigateToProductPage();

    }

    @Test(priority = 2, dependsOnMethods = {"loginAndOpenProductPage"})
    public void addProductAndVerifyCartButtonText() throws InterruptedException {
        new ProductPage(DriverManager.getDriver()).clickAddToCart().assertItemAddedToCart("1").clickRemoveCart();

    }



}
