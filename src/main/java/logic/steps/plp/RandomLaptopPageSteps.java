package logic.steps.plp;

import logic.annotation.Steps;
import logic.pages.plp.RandomLaptopPage;
import logic.setup.BaseStep;
import logic.setup.TableManager;
import org.openqa.selenium.WebElement;

import java.util.*;

public class RandomLaptopPageSteps extends BaseStep {

    private RandomLaptopPage randomLaptopPage;

    public RandomLaptopPageSteps() {
        driver.manage().window().maximize();
        this.randomLaptopPage = new RandomLaptopPage();
    }

    public ArrayList<Integer> getPricesLaptopList (){
        List<WebElement> listOfPrice = waitElements(randomLaptopPage.laptopPrice);
        int price;
        ArrayList<Integer> priceslist = new ArrayList<>();
        for (int i = 0; i < listOfPrice.size(); i++) {
            String priceStr = listOfPrice.get(i).getText().replaceAll("\\s", "");
            price = Integer.parseInt(priceStr);
            priceslist.add(price);
        }
        return priceslist;
    }

    public ArrayList<String> getLaptopLinksList(){
        List<WebElement> listOfLinks = waitElements(randomLaptopPage.pictureOfLap);
        String link;
        ArrayList<String> linkslist = new ArrayList<>();
        for (int i = 0; i < listOfLinks.size(); i++) {
            link =  listOfLinks.get(i).getAttribute("href");
            linkslist.add(link);
        }
        return linkslist;
    }

    public String getMinPricesLapLink(){
        String link = getLaptopLinksList().get(
                getPricesLaptopList().indexOf(
                        Collections.min(getPricesLaptopList())));
        return link;
    }

    public String getMaxPricesLapLink(){
        String link = getLaptopLinksList().get(
                getPricesLaptopList().indexOf(
                        Collections.max(getPricesLaptopList())));
        return link;
    }


    public ArrayList<String> getCharacteristicsLabel(){
        List<WebElement> chLabel = waitElements(randomLaptopPage.characteristicLabel);
        ArrayList<String> charLabel = new ArrayList<>();
        for (int i = 0; i < chLabel.size(); i++) {
            String characterLabel = chLabel.get(i).getText();
            charLabel.add(characterLabel);
        }
        return charLabel;
    }

    public ArrayList<String> getCharacteristicsValue(){
        List<WebElement> chValue = waitElements(randomLaptopPage.characteristicValue);
        ArrayList<String> charValue = new ArrayList<>();
        for (int i = 0; i < chValue.size(); i++) {
            String characterValue = chValue.get(i).getText();
            charValue.add(characterValue);
        }
        return charValue;
    }

    public HashMap<String, String> getCharacteristicsLabValue(){
        getCharacteristicsLabel();
        getCharacteristicsValue();
        HashMap<String, String> characteristicPair = new HashMap<>();
        for (int i = 0; i < getCharacteristicsLabel().size(); i++) {
            characteristicPair.put(getCharacteristicsLabel().get(i), getCharacteristicsValue().get(i));
        }
        return characteristicPair;
    }

    @Steps
    public void createTable( ) {
        try {
            TableManager manager = new TableManager("schemas/notebook.json");

            manager.createTable();

            String linkMin = getMinPricesLapLink()+"characteristics/";
            String linkMax = getMaxPricesLapLink()+"characteristics/";

            driver.get(linkMin);
            Map<String, String> notebookMin = getCharacteristicsLabValue();
            WebElement minPricedLap = waitElement(randomLaptopPage.laptopName);
            String productName = minPricedLap.getText();
            manager.addItem(productName, linkMin, notebookMin);

            driver.get(linkMax);
            Map<String, String> notebookMax = getCharacteristicsLabValue();
            WebElement maxPricedLap = waitElement(randomLaptopPage.laptopName);
            String productName2 = maxPricedLap.getText();
            manager.addItem(productName2, linkMax, notebookMax);

            System.out.println("\n" + manager.getReport() + "\n");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
