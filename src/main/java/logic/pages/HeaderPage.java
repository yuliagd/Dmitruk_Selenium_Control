package logic.pages;

import org.openqa.selenium.By;

public class HeaderPage {

    public By btn_catalog = By.xpath("//button[@class = 'menu__toggle ng-star-inserted']");
    public By btn_catalog_res = By.xpath("//button[@class = 'button button--medium button--with-icon menu__toggle ng-star-inserted']");
    public By linkCatalogSection = By.xpath("//a[@class = 'menu-categories__link']");


    public By img_logo = new By.ByXPath("//a[@class='header__logo']//img[@alt='Rozetka Logo']");
}
