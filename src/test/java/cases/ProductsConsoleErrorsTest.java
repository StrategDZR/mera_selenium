package cases;

/*
Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения
при открытии страниц в учебном приложении, а именно -- страниц товаров в каталоге в административной панели.

Сценарий должен состоять из следующих частей:

1) зайти в админку
2) открыть каталог, категорию, которая содержит товары
(страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)

 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.AdminCatalogPage;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static config.app.LITECART_ADMIN;

public class ProductsConsoleErrorsTest extends AbstractTest {

    AdminCatalogPage adminCatalogPage;

    @Test
    public void consoleErrorsOnProductsPageTest() {
        openPageAndLoginAsAdmin(LITECART_ADMIN + "?app=catalog&doc=catalog&category_id=1");
        AtomicInteger errorsCounter = new AtomicInteger();
        adminCatalogPage = new AdminCatalogPage(driver);
        List<String> productsList = adminCatalogPage.getProductNamesFromCatalogPage();

        productsList.forEach(name -> {
            adminCatalogPage.clickOnText(name);
            if (isConsoleErrorExists()) {
                errorsCounter.getAndIncrement();
            }
            driver.navigate().back();
        });
        Assert.assertEquals(0, errorsCounter.get());
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
