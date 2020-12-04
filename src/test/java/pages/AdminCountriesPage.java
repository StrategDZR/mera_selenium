package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Formatter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AdminCountriesPage extends AbstractPage {

    //Locators
    private final String zonesCountElement = "(//form[@name=\"countries_form\"]//tr//td)[%d]";
    private final String countryNamesElement = "//td/a[@href][not(@title=\"Edit\")]";
    private final String findCountryByNameElement = "//form[@name=\"countries_form\"]//td/a[contains(., \"%s\")]";
    private final String countryPageElement = "//form[@name=\"countries_form\"]";
    private final String zoneNamesElement = "//table[@id=\"table-zones\"]//tr/td/input[contains(@name, \"[name]\")][not(@value=\"\")]";
    private final String addNewCountryButtonLoc = "//a[@class=\"button\"][contains(@href, \"edit_country\")]";

    public AdminCountriesPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(countryPageElement)));
    }

    public Integer getZones(int position) {
        logger.info("Getting quantity of zones");
        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(zonesCountElement, position * 7 + 6));
        WebElement zone = driver.findElement(By.xpath(locator));
        return parseInt(zone.getText());
    }

    public void openCountry(String countryName) {
        logger.info("Clicking in country name");
        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(findCountryByNameElement, countryName));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        driver.findElement(By.xpath(locator)).click();
    }

    public List<WebElement> getCountriesList() {
        logger.info("Getting list of countries");
        return getList(countryNamesElement);
    }

    public List<WebElement> getZonesList() {
        logger.info("Getting list of zones");
        return getList(zoneNamesElement);
    }

    public void clickOnAddNewCountry() {
        logger.info("Clicking on Add New Country button");
        clickOn(addNewCountryButtonLoc);
    }
}
