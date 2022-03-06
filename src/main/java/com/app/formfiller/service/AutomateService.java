package com.app.formfiller.service;

import com.app.formfiller.pages.LoginPage;
import com.app.formfiller.pages.ProductFormPage;
import com.app.formfiller.utilities.BrowserUtils;
import com.app.formfiller.utilities.Driver;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
@Slf4j
public class AutomateService {

    @Autowired
    CsvFillerService csvFillerService;

    LoginPage loginPage = new LoginPage();
    ProductFormPage productFormPage = new ProductFormPage();

    private CSVReader csvReader;
    String[] csvCell;

    String CSV_PATH ="uploads/product.csv";

    public void getautomate() throws InterruptedException {
        try {


            String urlbackup = "https://cydeo-accounting.herokuapp.com";
            Driver.get().get(urlbackup);
            log.info("Title is : " + Driver.get().getTitle());
            Driver.get().manage().window().maximize();
            loginPage.username.sendKeys("cydeo@ct.com");
            loginPage.password.sendKeys("Abc1");
            loginPage.submit.click();
            log.info("Login successfull");
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("This form is already filled ");
        }

    }

    public void navigateProductPage() throws InterruptedException {
        Actions action = new Actions(Driver.get());
        BrowserUtils.waitForVisibility(loginPage.stockManagementLink,10);
        action.moveToElement(loginPage.stockManagementLink).perform();
        Thread.sleep(2000);
        loginPage.productsLinkFromDashBoard.click();
        BrowserUtils.waitForVisibility(productFormPage.addProductButton, 30);

        BrowserUtils.waitForVisibility(productFormPage.addProductButton, 30);
        productFormPage.addProductButton.click();
    }

    public void fillForm() throws CsvValidationException, IOException {
        if(!Driver.get().getTitle().equals("Add Product")){
            BrowserUtils.waitForVisibility(productFormPage.addProductButton, 30);
            productFormPage.addProductButton.click();
        }

//Create an object of CSVReader
        csvReader = new CSVReader(new FileReader(CSV_PATH));
        while ((csvCell = csvReader.readNext()) != null) {
            String productName = csvCell[0];
            String quantity = csvCell[1];
            String status = csvCell[2];
            String category = csvCell[3];
            String description = csvCell[4];
            String unit = csvCell[5];
            String lowlimitAlertQuantity = csvCell[6];

            System.out.println("csvCell = " + csvCell);
        }



    }
}
