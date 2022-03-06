package com.app.formfiller.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CsvFillerService {

    //Provide test data CSV file path. As below path based on Mac machine. So, lets say you are using windows machine then write the below path accordingly.
    String CSV_PATH = "./uploads/";


    private CSVReader csvReader;
    String[] csvCell;

    public void fillFormwithCsv() throws CsvValidationException, IOException {
        while ((csvCell = csvReader.readNext()) != null) {
            String productName = csvCell[0];
            String CustomerEmail = csvCell[1];
            String CustomerPassword = csvCell[2];
            String CustomerConfirmPassword = csvCell[3];
        }
    }
}
