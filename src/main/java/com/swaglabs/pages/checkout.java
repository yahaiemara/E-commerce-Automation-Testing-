package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkout {
    private final WebDriver driver;
    private final By Chec_out = By.xpath("//a[contains(@class,'btn_action checkout_button')]");
    private final By cancel_Button = By.xpath("//a[contains(@class,'cart_cancel_link btn_secondary')]");
    private final By first_Name = By.xpath("//input[contains(@id,'first-name')]");
    private final By last_Name = By.xpath("//input[contains(@id,'last-name')]");
    private final By zipCode = By.xpath("//input[contains(@id,'postal-code')]");
    private final By continue_Button = By.xpath("//input[contains(@class,'btn_primary cart_button')]");
    private final By name = By.xpath("//div[contains(@class,'inventory_item_name')]");
    private final By desc = By.xpath("//div[contains(@class,'inventory_item_desc')]");
    private final By price = By.xpath("//div[contains(@class,'inventory_item_price')]");
    private final By finish_Button = By.xpath("//a[contains(@class,'btn_action cart_button')]");


    public checkout(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to product page")
    public void navigateTCheckPage() {
        browserAction.navigateUrl(driver, "https://www.saucedemo.com/v1/checkout-step-one.html");
    }


    @Step("Enter firstName:{0}")
    public checkout enterfirstName(String firsname) {
        elementAction.sendkey(driver, this.first_Name, firsname);
        return this;
    }

    @Step("Enter lastName:{0}")
    public checkout enterlastName(String lastName) {
        elementAction.sendkey(driver, this.last_Name, lastName);
        return this;
    }

    @Step("Enter zipCode:{0}")
    public checkout enterzipCode(String zipcode) {
        elementAction.sendkey(driver, this.zipCode, zipcode);
        return this;
    }

    @Step("Enter ClickButton")
    public checkout ClickButtonContinue() {
        elementAction.clickElement(driver, continue_Button);
        return this;
    }

    @Step("Get product name")
    public String getProductDetailsName() {
        return elementAction.getText(driver, name);
    }

    @Step("Get product name")
    public String getProductDetailsPrice() {
        return elementAction.getText(driver, price);
    }

    @Step("Get product name")
    public String getProductDetailsDesc() {
        return elementAction.getText(driver, desc);
    }

    @Step("Click Button CheckOut")
    public checkout checkOut() {
        wait.waitForWeElementVisiable(driver, Chec_out);
        wait.waitForWebElementclickability(driver, Chec_out);
        elementAction.clickElement(driver, Chec_out);
        return this;
    }

    @Step("Click Cancle Button is Working")
    public checkout cancel() {
        wait.waitForWeElementVisiable(driver, cancel_Button);
        wait.waitForWebElementclickability(driver, cancel_Button);
        elementAction.clickElement(driver, cancel_Button);
        String actualresults = driver.getCurrentUrl();
        validations.validateEqual("https://www.saucedemo.com/v1/cart.html", actualresults, "The Url is not Match ");
        return this;
    }

    @Step("assert Continue Button Is Working")
    public checkout continue_button() {
        wait.waitForWebElementclickability(driver, continue_Button);
        wait.waitForWebElementclickability(driver, continue_Button);
        elementAction.clickElement(driver, continue_Button);
        String actularesults = driver.getCurrentUrl();
        validations.validateEqual("https://www.saucedemo.com/v1/checkout-step-two.html", actularesults, "the url is not match");
        return this;
    }

    @Step("Finish The Order")
    public checkout finish_Button() {
        wait.waitForWeElementVisiable(driver, finish_Button);
        wait.waitForWebElementclickability(driver, finish_Button);
        scroll.scrollToElement(driver, finish_Button);
        elementAction.clickElement(driver, finish_Button);
        String acutlaresults = driver.getCurrentUrl();
        validations.validateEqual("https://www.saucedemo.com/v1/checkout-complete.html",
                acutlaresults,
                "the url is not match ");

        return this;
    }


}
