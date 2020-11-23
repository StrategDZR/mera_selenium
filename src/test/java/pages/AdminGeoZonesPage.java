package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminGeoZonesPage extends AbstractPage {

    //Locators
    private final String geoZoneNamesElement = "//td/a[@href][not(@title=\"Edit\")]";
    private final String geoZonePageElement = "//form[@name=\"geo_zones_form\"]";

    public AdminGeoZonesPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(geoZonePageElement)));
    }

    public List<WebElement> getGeoZonesList() {
        logger.info("Getting list of geo zones");
        return getList(geoZoneNamesElement);
    }
}
