package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //locator
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By login_buton = By.id("login-button");
    private final By errormessage = By.xpath("//h3[contains(@data-test,'error')]");
    private final By menu_btn = By.xpath("//button[contains(.,'Open Menu')]");
    private final By logout_button = By.xpath("//a[contains(.,'Logout')]");

    public void navigateToLoginPage() {
        browserAction.navigateUrl(driver, "https://www.saucedemo.com/v1/");
    }

    //action
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
@Step("Enter Username:{0}")
    public LoginPage enterUsername(String username) {
        elementAction.sendkey(driver, this.username, username);
        return this;
    }
@Step("Enter Password: {0}")
    public LoginPage enterpassword(String password) {
        elementAction.sendkey(driver, this.password, password);
        return this;
    }
@Step("Click Button")
    public LoginPage clickLoginButton() {
        elementAction.clickElement(driver, this.login_buton);
        return this;
    }
@Step("Get Message Error")
    public String getmessageerror() {
        return elementAction.getText(driver, this.errormessage);
    }

    //    validate
    @Step("Assert Login Page")
    public LoginPage assertLoginpageUrl() {
        CustomSoftAssertion.softAssertion.assertEquals(browserAction.getcurrentUrl(driver), "https://www.saucedemo.com/v1/inventory.html", "Url is not a expected");
        return this;
    }
@Step("Assert Login Page Title")
    public LoginPage assertLoginpageLogo() {
        CustomSoftAssertion.softAssertion.assertEquals(browserAction.getTitle(driver), "Swag Labs", "Title is not a expected");
        return this;
    }
@Step("Assert Login Soft")
    public LoginPage assertLoginSoft() {
        assertLoginpageUrl().assertLoginpageLogo();
        return this;
    }
@Step("Assert Login Page Url")
    public LoginPage assertsuccessLoginPage() {
        validations.vlaidatapageurl(driver, "https://www.saucedemo.com/v1/inventory.html");
        return this;
    }
@Step("Assert Unsuccess Login Page")
    public LoginPage assertunsuccessLoginPage() {
        validations.validateEqual(getmessageerror(), "Epic sadface: Username and password do not match any user in this service", "Error Message Is Not A Expected");
        return this;
    }
@Step("Logout")
    public LoginPage logout() {
        wait.waitForWebElementPresent(driver, menu_btn);
        elementAction.findelement(driver, menu_btn).click();
        elementAction.clickElement(driver, logout_button);
        return this;
    }
}
