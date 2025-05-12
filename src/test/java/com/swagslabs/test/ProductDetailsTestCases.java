package com.swagslabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.pages.productdetails;
import com.swaglabs.utils.AllureUtils;
import com.swaglabs.utils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class ProductDetailsTestCases {
    File allure_results = new File("test-outputs/allure-results");

    @BeforeSuite
    public void beforeSuite() throws InterruptedException {
        FileUtils.deleteFiles(allure_results);
    }

    @BeforeTest
    public void setup() {
        DriverManager.createInstance("chrome");
    }


    @AfterTest
    public void exit() {
        // DriverManager.getDriver().quit();
    }

    @AfterClass
    public void afterClass() {
        AllureUtils.attachLogsToAllure();
    }

    @Test(priority = 1)
    public void verifyProductDetails() {
        productdetails productDetailsPage = new productdetails(DriverManager.getDriver());
        productDetailsPage.navigateToProductPageDetails();
        productDetailsPage.assertProductDetails(
                "Sauce Labs Backpack",
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "$29.99"
        );
    }

    @Test(priority = 2)
    public void addProductAndVerifyCartButtonText() throws InterruptedException {
       productdetails productDetailsPage=new productdetails(DriverManager.getDriver());
       productDetailsPage.navigateToProductPageDetails();
       productDetailsPage.clickAddToCart().assertItemAddedToCart("1");

    }




}
