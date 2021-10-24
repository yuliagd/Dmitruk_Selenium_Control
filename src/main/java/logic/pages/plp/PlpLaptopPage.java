package logic.pages.plp;

import org.openqa.selenium.By;

public class PlpLaptopPage {
    public By slct_filter = new By.ByXPath("//select[contains(@class, 'select-css ng-untouched')]");
    public By numberOfLastPage = new By.ByXPath("(//a[@class = 'pagination__link ng-star-inserted'])[last()]");
}
