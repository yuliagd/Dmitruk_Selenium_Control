import logic.data.*;
import logic.setup.BaseStep;
import logic.steps.HeaderPageSteps;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HeaderPageCheck extends BaseStep {
    @BeforeTest
    public void install(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/");
    }

    @AfterTest
    public void cleanUP(){
        //driver.close();
    }

    @Test
    public void test001_OpenLaptopPLPPage(){
        new HeaderPageSteps()
                .checkSection(CatalogSection.PC_AND_LAPTOP.get())
                .openSection(CatalogSection.PC_AND_LAPTOP.get())
                .openSectionLAPTOP(PCAndLaptopSection.LAPTOP.get())
                .selectFilter(SelectingDropdownMods.TEXT.get(), GoodsSelectingMods.NOVELTY.get())
                .openRandomPage()
                .createTable();
    }

}

