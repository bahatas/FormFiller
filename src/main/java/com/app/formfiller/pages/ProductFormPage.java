package com.app.formfiller.pages;

import com.app.formfiller.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductFormPage {
    public ProductFormPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//*[.='Add Product']")
    public WebElement addProductButton;

    @FindBy(xpath = "//input[@placeholder='Product Name']")
    public WebElement nameFieldOfForm;

    @FindBy(xpath = "//input[@placeholder='Quantity']")
    public WebElement quantityFieldOfForm;

    @FindBy(xpath = "//*[.='Select Status']")
    public WebElement selectStatusFieldOfForm;

    @FindBy(xpath = "//*[.='Select Category']")
    public WebElement selectCategoryFieldOfForm;

    @FindBy(xpath = "//input[@placeholder='Ex: Home product, Kitchen Tools ']")
    public WebElement descriptionFieldOfForm;

    @FindBy(xpath = "//*[.='Select Unit']")
    public WebElement selectUnitFieldOfForm;


    @FindBy(xpath = "//input[@placeholder='Quantity for low limit Alert']")
    public WebElement lowLimitAlertFieldOfForm;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement createProductButton;









}
