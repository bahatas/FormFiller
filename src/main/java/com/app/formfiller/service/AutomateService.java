package com.app.formfiller.service;

import com.app.formfiller.pages.LoginPage;
import com.app.formfiller.utilities.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutomateService {

    LoginPage loginPage = new LoginPage();

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
            Driver.get().quit();
        }catch (Exception e ){
            e.printStackTrace();
            log.error("This form is already filled ");
        }
    }
}
