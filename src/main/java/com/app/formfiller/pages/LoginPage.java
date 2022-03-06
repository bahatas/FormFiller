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


    @FindBy(xpath="//span[@data-i18n='Grid' and .='Products']")
    public WebElement productsLinkFromDashBoard;

    @FindBy(xpath="//span[@data-i18n='UI Kit' and .='Stock Management']")
    public WebElement stockManagementLink;
}
