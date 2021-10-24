package logic.drivers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;

public class WebDriverBuilder {
    private WebDriver driver;

    public WebDriverBuilder(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverBuilder setMaximize() {
        this.driver.manage().window().maximize();
        return this;
    }

    public WebDriverBuilder setFullscreenView() {
        this.driver.manage().window().fullscreen();
        return this;
    }

    public WebDriverBuilder setImplicitlyWait(long seconds) {
        this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        return this;
    }

    public WebDriverBuilder setScriptTimeout(long seconds) {
        this.driver.manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);
        return this;
    }

    public WebDriverBuilder setPageLoadTimeout(long seconds) {
        this.driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
        return this;
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }
}
