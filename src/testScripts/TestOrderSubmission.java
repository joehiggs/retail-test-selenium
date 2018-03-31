package testScripts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome


public class TestOrderSubmission {


    public static void main(String[] args) {
        // declaration and instantiation of objects/variables
       System.setProperty("webdriver.firefox.marionette","C:\\Users\\JHIGGS\\Downloads\\geckodriver-v0.20.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //comment the above 2 lines and uncomment below 2 lines to use Chrome
//        System.setProperty("webdriver.chrome.driver","C:\\Users\\JHIGGS\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:4200";
        String customerName = "Selenium test name";
        String address = "Selenium test address";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        // set form values
        driver.findElement(By.id("customerName")).sendKeys(customerName);
        driver.findElement(By.id("address")).sendKeys(address);

        // click submit
        driver.findElement(By.id("submit")).click();

        // Hacky hack to wait for request to send
        // get timestamp to limit execution/prevent infinite loop
        Long start = System.currentTimeMillis();
        while(true) {
            if(driver.findElement(By.id("message")).getText().equals("Error")) {
                System.out.println("ERROR");
                break;
            }
            if(driver.findElement(By.id("message")).getText().equals("Order submitted successfully")) {
                System.out.println("SUCCESS");
                break;
            }
            if(System.currentTimeMillis() - start > 5000) {
                System.out.println("Something went wrong: Request timeout after 5 seconds ");
                break;
            }
        }
        //close Fire fox
        driver.close();

    }

}
