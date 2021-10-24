package logic.steps.plp;

import logic.annotation.Steps;
import logic.pages.plp.PlpLaptopPage;
import logic.setup.BaseStep;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.ThreadLocalRandom;

public class PlpLaptopPageSteps extends BaseStep {
    private PlpLaptopPage plpPage;


    public PlpLaptopPageSteps() {
        driver.manage().window().maximize();
        this.plpPage = new PlpLaptopPage();
    }

    @Steps
    public PlpLaptopPageSteps selectFilter(String type, String value){
        Select select = new Select( waitElement(plpPage.slct_filter));
        switch (type){
            case "byIndex":{
                select.selectByIndex(Integer.valueOf(value));
                break;
            }
            case "byValue":{
                select.selectByValue(value);
                break;
            }
            case "byVisibleText":{
                select.selectByVisibleText(value);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid type of selection.");
        }

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getLinkOfRandomPage(){
        WebElement lastPaginator = waitElement(plpPage.numberOfLastPage);
        String textElement = lastPaginator.getText();
        int numberOfLastPage = Integer.parseInt(textElement);
        int randomNumberOfPage = ThreadLocalRandom.current().nextInt(1, numberOfLastPage+1);
        String linkOfPages = lastPaginator.getAttribute("href");
        String linkOfRandomPage = linkOfPages.replaceAll("page=\\d+", "page="+randomNumberOfPage);
        return linkOfRandomPage;
    }

    public void getText (){
        System.out.println(getLinkOfRandomPage());
    }

    @Steps
    public RandomLaptopPageSteps openRandomPage (){
        driver.get(getLinkOfRandomPage());
        return new RandomLaptopPageSteps();
    }



}
