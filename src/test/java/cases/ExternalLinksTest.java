package cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.AddNewCountryPage;
import pages.AdminCountriesPage;

import java.util.List;

import static config.app.LITECART_ADMIN;

/*
Задание 14. Проверьте, что ссылки открываются в новом окне
Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.

Сценарий должен состоять из следующих частей:

1) зайти в админку
2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
3) открыть на редактирование какую-нибудь страну или начать создание новой
4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы
и открываются в новом окне, именно это и нужно проверить.

Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank".
Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне,
потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.

Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.
 */

public class ExternalLinksTest extends AbstractTest {

    AdminCountriesPage countriesPage;
    AddNewCountryPage addNewCountryPage;

    @Test
    public void externalLinksOpensInNewTab() {
        openPageAndLoginAsAdmin(LITECART_ADMIN + "?app=countries&doc=countries");
        countriesPage = new AdminCountriesPage(driver);
        countriesPage.clickOnAddNewCountry();
        addNewCountryPage = new AddNewCountryPage(driver);

        List<WebElement> extLinks = addNewCountryPage.getAllExternalLinks();
        extLinks.forEach(webElement -> {
            webElement.click();
            Assert.assertTrue(isSecondTabOpened());
            closeSecondTab();
        });
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
