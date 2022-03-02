package com.app.formfiller.pages;

import com.app.formfiller.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "password")
    public  WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public  WebElement submit;
}
