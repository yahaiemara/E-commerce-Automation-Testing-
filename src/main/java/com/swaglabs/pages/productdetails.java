package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class productdetails {
    private static final Logger log = LoggerFactory.getLogger(productdetails.class);
    private WebDriver driver;  // اجعل driver غير ثابت
    private final By name_product_desc = By.xpath("//div[contains(@class,'inventory_details_name')]");
    private final By desc_product_desc = By.xpath("//div[@class='inventory_details_desc']");
    private final By price_product_details = By.xpath("//div[contains(@class,'inventory_details_price')]");
    private final By button_cart = By.xpath("//button[contains(.,'ADD TO CART')]");
    private final By cart_icon = By.xpath("//span[contains(@class,'fa-layers-counter shopping_cart_badge')]");
    private final By remove_cart = By.xpath("//button[contains(.,'REMOVE')]");



    public productdetails(WebDriver driver) {
        this.driver = driver;  // تهيئة المتغير driver عبر البناء
    }

    @Step("Navigate to product page")
    public void navigateToProductPageDetails() {
        browserAction.navigateUrl(driver, "https://www.saucedemo.com/v1/inventory-item.html?id=4");
    }

    @Step("Get product name")
    public String getProductDetailsName() {
        return elementAction.getText(driver, name_product_desc);
    }

    @Step("Get product description")
    public String getProductDescriptionDesc() {
        return elementAction.getText(driver, desc_product_desc);
    }

    @Step("Get product price")
    public String getProductDescriptionPrice() {
        return elementAction.getText(driver, price_product_details);
    }


    @Step("Click Add to Cart")
    public productdetails clickAddToCart() {
        wait.waitForWebElementclickability(driver, button_cart);
        scroll.scrollToElement(driver, button_cart);
        elementAction.clickElement(driver, button_cart);
        return this;
    }

    @Step("Click Remove to Cart")
    public productdetails clickRemoveCart() {
        wait.waitForWebElementclickability(driver, remove_cart);
        scroll.scrollToElement(driver, remove_cart);
        elementAction.clickElement(driver, remove_cart);
        return this;
    }

    @Step("Get cart badge count")
    public String getCartBadgeCount() {
        return elementAction.getText(driver, cart_icon);
    }

    @Step("Assert item added to cart: {expectedCount}")
    public productdetails assertItemAddedToCart(String expectedCount) throws InterruptedException {
        Thread.sleep(10);
        String cartCount = getCartBadgeCount();
        validations.validateEqual(cartCount, expectedCount, "Cart count not as expected: " + cartCount);
        return this;
    }

    @Step("Assert product details")
    public productdetails assertProductDetails(String expectedName, String expectedDesc, String expectedPrice) {
        validations.validateEqual(getProductDetailsName(), expectedName, "Product name not as expected");
        validations.validateEqual(getProductDescriptionDesc(), expectedDesc, "Product description not as expected");
        validations.validateEqual(getProductDescriptionPrice(), expectedPrice, "Product price not as expected");
        return this;
    }
//

}
