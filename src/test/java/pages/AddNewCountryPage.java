package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddNewCountryPage extends AbstractPage {

    private final String uniquePageElementLoc = "//h1[contains(., \" Add New Country\")]";
    private final String externalLink = "//i[contains(@class, \"external-link\")]";

    public AddNewCountryPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(uniquePageElementLoc)));
    }

    public List<WebElement> getAllExternalLinks() {
        logger.info("Getting all external links");
        return driver.findElements(By.xpath(externalLink));
    }
}
