package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AdminCountriesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(AdminCountriesPage.class);


    //Locators
    private final String zonesCountElement = "(//form[@name=\"countries_form\"]//tr//td)[%d]";
    private final String countryNamesElement = "//td/a[@href][not(@title=\"Edit\")]";
    private final String findCountryByNameElement = "//td/a[contains(., \"%s\")]";
    private final String zoneNamesElement = "//table[@id=\"table-zones\"]//tr/td/input[contains(@name, \"[name]\")][not(@value=\"\")]";

    public AdminCountriesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public Integer getZones(int position) {
        logger.info("Getting quantity of zones");
        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(zonesCountElement, position * 7 + 6));
        WebElement zone = driver.findElement(By.xpath(locator));
        return parseInt(zone.getText());
    }

    public void checkAlphabeticalOrderOfList(List<WebElement> list) {
        logger.info("Checking alphabetical order");
        List<String> initialListOfNames = new ArrayList<>();
        List<String> sortedListOfNames = new ArrayList<>();
        list.forEach(element -> {
            initialListOfNames.add(element.getText());
            sortedListOfNames.add(element.getText());
        });
        sortedListOfNames.sort(Comparator.naturalOrder());
        Assert.assertEquals(initialListOfNames, sortedListOfNames);
    }

    public void openCountry(String countryName) {
        logger.info("Clicking in country name");
        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(findCountryByNameElement, countryName));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        driver.findElement(By.xpath(locator)).click();
    }

    public List<WebElement> getList(String locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return driver.findElements(By.xpath(locator));
    }

    public List<WebElement> getCountriesList() {
        logger.info("Getting list of countries");
        return getList(countryNamesElement);
    }

    public List<WebElement> getZonesList() {
        logger.info("Getting list of zones");
        return getList(zoneNamesElement);
    }
}
