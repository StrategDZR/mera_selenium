package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddNewCountryPage extends AbstractPage{

    private final String uniquePageElementLoc = "//h1[contains(., \" Add New Country\")]";
    private final String externalLink = "//i[contains(@class, \"external-link\")]";

    public AddNewCountryPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(uniquePageElementLoc)));
    }

    public void clickOnExternalLink(){
        logger.info("Click on the first external link");
        clickOn(externalLink);
    }
}
