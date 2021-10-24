package logic.steps;

import logic.annotation.Steps;
import logic.pages.HeaderPage;
import logic.setup.BaseStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class HeaderPageSteps extends BaseStep {
    private HeaderPage headerPage;

    public HeaderPageSteps() {
        driver.manage().window().maximize();
        this.headerPage = new HeaderPage();
    }
    private WebElement getMainSection(String section){
        return   waitElements(headerPage.linkCatalogSection)
                .stream()
                .filter(o->o.getText().contains(section))
                .findFirst()
                .get();
    }

    @Steps
    public HeaderPageSteps checkSection(String section){
        WebElement element = getMainSection(section);
        assertThat(element.isEnabled(), is(true));
        return this;
    }

    @Steps
    public PCAndLaptopPageSteps openSection(String section){
        getMainSection(section).click();
        return new PCAndLaptopPageSteps();
    }



}
