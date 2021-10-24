package logic.pages.plp;

import org.openqa.selenium.By;

public class RandomLaptopPage {
    public By laptopPrice = new By.ByXPath("//span[@class='goods-tile__price-value']");
    public By pictureOfLap = new By.ByXPath ("//a[@class='goods-tile__picture ng-star-inserted']");
    public By characteristicLabel = new By.ByXPath("//dt[@class='characteristics-full__label']");
    public By characteristicValue = new By.ByXPath("//dd[@class='characteristics-full__value']");
    public By laptopName = new By.ByXPath("//h1[@class='product__title']");

}
