package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submit;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String inputFirstName, String inputLastName, String inputUsername, String inputPassword) {
        this.inputFirstName.sendKeys(inputFirstName);
        this.inputLastName.sendKeys(inputLastName);
        this.inputUsername.sendKeys(inputUsername);
        this.inputPassword.sendKeys(inputPassword);
        this.submit.click();
    }
}
