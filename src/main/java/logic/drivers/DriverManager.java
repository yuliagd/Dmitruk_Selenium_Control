package logic.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class DriverManager {

    private static DriverManager driverManager;
    private  WebDriver webDriver;

    public DriverManager (String type){
        //For Mac
        System.setProperty("webdriver.chrome.driver", "/Users/uliadmitruk/soft/chromedriver");
        // For Windows
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver_92.exe");
        System.setProperty("webdriver.edge.driver","src/main/resources/drivers/msedgedriver_91_48.exe");
        System.setProperty("webdriver.firefox.driver","src/main/resources/drivers/geckodriver.exe");


        switch (type){
            case "CHROME":{
                this.webDriver = new WebDriverBuilder (new ChromeDriver())
                        .setScriptTimeout(4)
                        .setFullscreenView()
                        .setPageLoadTimeout(6)
                        .getWebDriver();
                break;
            }
            case "FF":{
                this.webDriver = new WebDriverBuilder (new FirefoxDriver())
                        .setScriptTimeout(4)
                        .setFullscreenView()
                        .setPageLoadTimeout(6)
                        .getWebDriver();
                break;
            }
            case "SAFARI":{
                this.webDriver = new WebDriverBuilder (new SafariDriver())
                        .setScriptTimeout(4)
                        .setFullscreenView()
                        .setPageLoadTimeout(6)
                        .getWebDriver();
                break;
            }
            default:
                throw new IllegalArgumentException("The driver type has not been found.");
        }
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public static DriverManager getInstance(String driverType) {
        if (driverManager == null) {
            driverManager = new DriverManager(driverType);
        }
        return driverManager;
    }




}
