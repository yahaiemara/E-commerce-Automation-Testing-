package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final By name_product_desc = By.xpath("//div[contains(@class,'inventory_item_name')]");
    private final By desc_product_desc = By.xpath("//div[contains(@class,'inventory_item_desc')]");
    private final By price_product_details = By.xpath("//div[contains(@class,'inventory_item_price')]");
    private final By button_cart = By.xpath("//button[contains(.,'ADD TO CART')]");
    private final By cart_icon = By.xpath("//span[contains(@class,'fa-layers-counter shopping_cart_badge')]");
    private final By remove_cart = By.xpath("//button[contains(.,'REMOVE')]");
    private final By continueShopping = By.xpath("//a[@class='btn_secondary']");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to product page")
    public void navigateToCartPage() {
        browserAction.navigateUrl(driver, "https://www.saucedemo.com/v1/cart.html");
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
    public CartPage clickAddToCart() {
        wait.waitForWebElementclickability(driver, button_cart);
        scroll.scrollToElement(driver, button_cart);
        elementAction.clickElement(driver, button_cart);
        return this;
    }


    @Step("Get cart badge count")
    public String getCartBadgeCount() {
        return elementAction.getText(driver, cart_icon);
    }

    @Step("Assert item added to cart: {expectedCount}")
    public CartPage assertItemAddedToCart(String expectedCount) throws InterruptedException {
        Thread.sleep(10);
        String cartCount = getCartBadgeCount();
        validations.validateEqual(cartCount, expectedCount, "Cart count not as expected: " + cartCount);
        return this;
    }



    @Step("Assert Button Continue Shopping Is Working")
    public CartPage assertContinueShoppingButton() {
        wait.waitForWeElementVisiable(driver, continueShopping);
        wait.waitForWebElementclickability(driver, continueShopping);
        elementAction.clickElement(driver, continueShopping);
        String actualresults = driver.getCurrentUrl();
        validations.validateEqual("https://www.saucedemo.com/v1/inventory.html", actualresults, "the expected is not match with actual");
        return this;

    }
    @Step("Assert product details")
    public CartPage assertProductDetails(String expectedName, String expectedDesc, String expectedPrice) {
        validations.validateEqual(getProductDetailsName(), expectedName, "Product name not as expected");
        validations.validateEqual(getProductDescriptionDesc(), expectedDesc, "Product description not as expected");
        validations.validateEqual(getProductDescriptionPrice(), expectedPrice, "Product price not as expected");
        return this;
    }

}
