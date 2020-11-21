package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminGeoZonesPage extends AdminCountriesPage {

    //Locators
    private final String geoZoneNamesElement = "//td/a[@href][not(@title=\"Edit\")]";

    public AdminGeoZonesPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getGeoZonesList() {
        logger.info("Getting list of geo zones");
        return getList(geoZoneNamesElement);
    }
}
