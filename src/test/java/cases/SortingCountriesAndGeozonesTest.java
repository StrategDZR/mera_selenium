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
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminCountriesPage;
import pages.AdminGeoZonesPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static config.app.LITECART_ADMIN;

public class SortingCountriesAndGeozonesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    LoginPage loginPage;
    AdminCountriesPage adminCountriesPage;
    AdminGeoZonesPage adminGeoZonesPage;

    @Before
    public void tierUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        loginPage = new LoginPage(driver);
        adminCountriesPage = new AdminCountriesPage(driver);
        adminGeoZonesPage = new AdminGeoZonesPage(driver);
    }

    @Test
    public void testAlphabeticalOrderOfCountries() {
        driver.navigate().to(LITECART_ADMIN + "?app=countries&doc=countries");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));
        loginPage.loginAsAdmin();

        List<WebElement> countries = adminCountriesPage.getCountriesList();
        adminCountriesPage.checkAlphabeticalOrderOfList(countries);
    }

    @Test
    public void testAlphabeticalOrderOfZoneCountries() {
        driver.navigate().to(LITECART_ADMIN + "?app=countries&doc=countries");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));
        loginPage.loginAsAdmin();

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
            adminCountriesPage.checkAlphabeticalOrderOfList(zonesList);
            driver.navigate().back();
        });
    }

    @Test
    public void testAlphabeticalOrderOfGeoZones() {
        driver.navigate().to(LITECART_ADMIN + "?app=geo_zones&doc=geo_zones");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));
        loginPage.loginAsAdmin();

        List<WebElement> geoZones = adminGeoZonesPage.getGeoZonesList();
        adminCountriesPage.checkAlphabeticalOrderOfList(geoZones);
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
