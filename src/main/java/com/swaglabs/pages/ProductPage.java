package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private final WebDriver driver;

    private final By name_product = By.xpath("//div[contains(@class,'inventory_item_name')]");
    private final By description_product = By.xpath("//div[contains(@class,'inventory_item_desc')]");
    private final By price_product = By.xpath("//div[contains(@class,'inventory_item_price')]");
    private final By button_cart = By.xpath("//button[contains(.,'ADD TO CART')]");
    private final By cart_icon = By.xpath("//span[contains(@class,'fa-layers-counter shopping_cart_badge')]");
    private final By remove_cart=By.xpath("//button[contains(.,'REMOVE')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to product page")
    public void navigateToProductPage() {
        browserAction.navigateUrl(driver, "https://www.saucedemo.com/v1/inventory.html");
    }

    @Step("Get product name")
    public String getProductName() {
        return elementAction.getText(driver, name_product);
    }

    @Step("Get product description")
    public String getProductDescription() {
        return elementAction.getText(driver, description_product);
    }

    @Step("Get product price")
    public String getProductPrice() {
        return elementAction.getText(driver, price_product);
    }

    @Step("Click Add to Cart")
    public ProductPage clickAddToCart() {
         wait.waitForWebElementclickability(driver, button_cart);
        scroll.scrollToElement(driver, button_cart);
        elementAction.clickElement(driver, button_cart);
        return this;
    }
    @Step("Click Remove to Cart")
    public ProductPage clickRemoveCart() {
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
    public ProductPage assertItemAddedToCart(String expectedCount) throws InterruptedException {
        Thread.sleep(10);
        String cartCount = getCartBadgeCount();
        validations.validateEqual(cartCount, expectedCount, "Cart count not as expected: " + cartCount);
        return this;
    }



}
