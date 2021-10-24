package logic.setup;

import logic.drivers.DriverManager;
import logic.drivers.DriverTypes;
import logic.drivers.WebDriverBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseStep {

    protected static WebDriver driver;
    protected final WebDriverWait wait;

    public BaseStep() {
        driver = DriverManager.getInstance(DriverTypes.CHROME.name()).getDriver();
        wait = new WebDriverWait(driver, 20, 2000L);
        new WebDriverBuilder(driver)
                .setScriptTimeout(4)
                .setPageLoadTimeout(6)
                .getWebDriver();
    }

    protected List<WebElement> getElements(By by){
        return driver.findElements(by);
    }

    protected void useScript(String script){
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
        javaScriptExecutor.executeScript(script);
    }

    private void waitForJSToLoad(){
        wait.until(WebElement->((JavascriptExecutor)driver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }


    protected WebElement waitElement(By by){
        waitForJSToLoad();
        WebElement element;
        try{
            element = driver.findElement(by);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            highligthElement(element);
        }catch (StaleElementReferenceException ex){
            element = driver.findElement(by);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            highligthElement(element);
        }
        return element;
    }

    protected List<WebElement> waitElements(By by){
        waitForJSToLoad();
        try{
            List<WebElement> list = driver.findElements(by);
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
            return list;
        }catch(StaleElementReferenceException ex){
            List<WebElement> list = driver.findElements(by);
            wait.until(ExpectedConditions.visibilityOfAllElements(list));
            return list;
        }
    }

    protected List<WebElement> waitForVisible(List<WebElement> elements){
        waitForJSToLoad();
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }



    private void highligthElement(WebElement element){
        if(driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("element = arguments[0];" +
                    "original_style = element.getAttribute('style');" +
                    "element.setAttribute('style', original_style + \"; " +
                    "background: yellow; border: 4px dashed blue;\");" +
                    "setTimeout(function(){ " +
                    "element.setAttribute('style', original_style);" +
                    "}, 400);", element);
        }
    }




    protected WebElement waitForElement(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element =
                wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }


}
