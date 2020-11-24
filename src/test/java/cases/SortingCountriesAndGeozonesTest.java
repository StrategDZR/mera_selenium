package cases;

/*
Задание 9. Проверить сортировку стран и геозон в админке

Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.
1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
а) проверить, что страны расположены в алфавитном порядке
б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить,
что зоны расположены в алфавитном порядке

2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.AdminCountriesPage;
import pages.AdminGeoZonesPage;

import java.util.ArrayList;
import java.util.List;

import static config.app.LITECART_ADMIN;

public class SortingCountriesAndGeozonesTest extends AbstractTest {

    AdminCountriesPage adminCountriesPage;
    AdminGeoZonesPage adminGeoZonesPage;

    @Test
    public void testAlphabeticalOrderOfCountries() {
        openPageAndLoginAsAdmin(LITECART_ADMIN + "?app=countries&doc=countries");
        adminCountriesPage = new AdminCountriesPage(driver);
        List<WebElement> countries = adminCountriesPage.getCountriesList();
        Assert.assertTrue(adminCountriesPage.listIsSortedAlphabetically(countries));
    }

    @Test
    public void testAlphabeticalOrderOfZoneCountries() {
        openPageAndLoginAsAdmin(LITECART_ADMIN + "?app=countries&doc=countries");
        adminCountriesPage = new AdminCountriesPage(driver);
        List<WebElement> countries = adminCountriesPage.getCountriesList();
        List<String> countryNamesWithNotZeroZones = new ArrayList<>();

        for (int i = 0; i < countries.size(); i++) {
            if (!adminCountriesPage.getZones(i).equals(0)) {
                countryNamesWithNotZeroZones.add(countries.get(i).getText());
            }
        }

        countryNamesWithNotZeroZones.forEach(country -> {
            adminCountriesPage.openCountry(country);
            List<WebElement> zonesList = adminCountriesPage.getZonesList();
            Assert.assertTrue(adminCountriesPage.listIsSortedAlphabetically(zonesList));
            driver.navigate().back();
        });
    }

    @Test
    public void testAlphabeticalOrderOfGeoZones() {
        openPageAndLoginAsAdmin(LITECART_ADMIN + "?app=geo_zones&doc=geo_zones");
        adminGeoZonesPage = new AdminGeoZonesPage(driver);
        List<WebElement> geoZones = adminGeoZonesPage.getGeoZonesList();
        Assert.assertTrue(adminGeoZonesPage.listIsSortedAlphabetically(geoZones));
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
