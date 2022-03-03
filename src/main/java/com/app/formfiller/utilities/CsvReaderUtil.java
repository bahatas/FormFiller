package com.app.formfiller.utilities;

public class CsvReaderUtil {
    //Provide test data CSV file path. As below path based on Mac machine. So, lets say you are using windows machine then write the below path accordingly.
    String CSV_PATH = "./uploads/";
//    WebDriver driver;

    private CSVReader csvReader;
    String[] csvCell;

    @BeforeTest
    public void setup() throws Exception {

        //You can specify the hardcoded value of a chrome driver or driver based on your browser like below line
        //System.setProperty("webdriver.chrome.driver", "/Users/d33p4k/driver/chromedriver");

        //OR

        //Use below line to manage driver by WebdriverManager for chrome browser in our case (you can use any other driver of your choice)
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://amazon.com");
    }

    @Test
    public void dataRead_CSV() throws IOException, CsvValidationException {
        //Create an object of CSVReader
        csvReader = new CSVReader(new FileReader(CSV_PATH));

        driver.findElement(By.id("nav-link-accountList")).click();
        driver.findElement(By.id("createAccountSubmit")).click();

        //You can use while loop like below, It will be executed until the last line in CSV used.
        while ((csvCell = csvReader.readNext()) != null) {
            String CustomerName = csvCell[0];
            String CustomerEmail = csvCell[1];
            String CustomerPassword = csvCell[2];
            String CustomerConfirmPassword = csvCell[3];
            driver.findElement(By.id("ap_customer_name")).clear();
            driver.findElement(By.id("ap_customer_name")).sendKeys(CustomerName);
            driver.findElement(By.id("ap_email")).clear();
            driver.findElement(By.id("ap_email")).sendKeys(CustomerEmail);
            driver.findElement(By.id("ap_password")).clear();
            driver.findElement(By.id("ap_password")).sendKeys(CustomerPassword);
            driver.findElement(By.id("ap_password_check")).clear();
            driver.findElement(By.id("ap_password_check")).sendKeys(CustomerConfirmPassword);
            driver.findElement(By.id("continue")).click();
        }
    }

    @AfterTest
    public void exit() {
        driver.close();
        driver.quit();
    }
}
