package logic.steps;

import logic.annotation.Steps;
import logic.pages.PCAndLaptopPage;
import logic.setup.BaseStep;

import logic.steps.plp.PlpLaptopPageSteps;
import org.openqa.selenium.WebElement;

public class PCAndLaptopPageSteps extends BaseStep {

    private PCAndLaptopPage pcAndLaptopPage;

    public PCAndLaptopPageSteps() {
        driver.manage().window().maximize();
        this.pcAndLaptopPage = new PCAndLaptopPage();
    }

    private WebElement getSectionLAPC(String sectionLAPC) {
        return waitElements(pcAndLaptopPage.link_sections)
                .stream()
                .filter(o -> o.getAttribute("alt").contains(sectionLAPC))
                .findFirst()
                .get();
    }


   @Steps
    public PlpLaptopPageSteps openSectionLAPTOP(String section){
        getSectionLAPC(section).click();
        return new PlpLaptopPageSteps();
    }


}
