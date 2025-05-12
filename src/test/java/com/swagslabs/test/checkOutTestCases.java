package com.swagslabs.test;

import com.swaglabs.drivers.DriverManager;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.pages.checkout;
import com.swaglabs.utils.AllureUtils;
import com.swaglabs.utils.FileUtils;
import com.swaglabs.utils.wait;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.io.File;

public class checkOutTestCases {
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

    @Test
    public void goToCheckoutPage() throws InterruptedException {
        new LoginPage(DriverManager.getDriver()).enterUsername("standard_user").
                enterpassword("secret_sauce").clickLoginButton();
        ProductPage productPage = new ProductPage(DriverManager.getDriver());
//        productPage.navigateToProductPage();
        productPage.clickAddToCart();
        CartPage cartPage = new CartPage(DriverManager.getDriver());
        cartPage.navigateToCartPage();
        new checkout(DriverManager.getDriver()).checkOut();


    }

    @Test
    public void ClickCancleButton() {
        new checkout(DriverManager.getDriver()).cancel();
    }

    @Test(dependsOnMethods = {"goToCheckoutPage"})
    public void successfullCheckout() {
        new checkout(DriverManager.getDriver()).enterfirstName("Chan").enterlastName("Amara")
                .enterzipCode("12354").continue_button().finish_Button();

    }





}
