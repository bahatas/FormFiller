package com.app.formfiller.controller;

import com.app.formfiller.dto.Dto;
import com.app.formfiller.pages.LoginPage;
import com.app.formfiller.service.AutomateService;
import com.app.formfiller.utilities.ConfigurationReader;
import com.app.formfiller.utilities.Driver;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@Slf4j
public class MainController {


    @Autowired
    private AutomateService automateService;

    private final String UPLOAD_DIR = "./uploads/";



    @GetMapping({"/", "/welcome"})
    public String getForm() {


        return "dashboard.html";
    }

    @PostMapping({"/navigate"})
    public String navigate(String url, Model model, Dto dto) {


        model.addAttribute("appurl", dto);

        System.out.println(Driver.get().getTitle());
        return "dashboard.html";
    }

    @GetMapping({"/navigate-url"})
    public String navigate() throws InterruptedException, CsvValidationException, IOException {
        automateService.getautomate();
        Thread.sleep(2000);
        log.info("navigate Product page called");
        automateService.navigateProductPage();
        Thread.sleep(2000);
        automateService.fillForm();


        return "directpage.html";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/";
    }
}
