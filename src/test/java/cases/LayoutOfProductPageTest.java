package cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.ShopMainPage;
import pages.ShopProductPage;

import java.util.HashMap;

import static config.app.LITECART_SHOP_MAIN;

/*
Задание 10. Проверить, что открывается правильная страница товара

Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.

Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:

а) на главной странице и на странице товара совпадает текст названия товара
б) на главной странице и на странице товара совпадают цены (обычная и акционная)
в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении
одинаковые значения для каналов R, G и B)
г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении
каналы G и B имеют нулевые значения)
(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)

Необходимо убедиться, что тесты работают в разных браузерах, желательно проверить во всех трёх ключевых браузерах
(Chrome, Firefox, IE).
 */

public class LayoutOfProductPageTest extends AbstractTest {

    ShopProductPage productPage;
    ShopMainPage mainPage;

    @Test
    public void checkTextIsTheSameOnMainAndProductPages() {
        openPage(LITECART_SHOP_MAIN);

        mainPage = new ShopMainPage(driver);
        HashMap<String, String> detailsFromMainPage = mainPage.getProductDetailsFromMainPage("Campaigns", 1);
        mainPage.openProductPage("Campaigns", 1);

        productPage = new ShopProductPage(driver);
        HashMap<String, String> detailsFromProductPage = productPage.getProductDetailsFromProductPage();

        Assert.assertTrue(productPage.isNameTheSame(detailsFromMainPage, detailsFromProductPage));
    }

    @Test
    public void checkPriceIsTheSameOnMainAndProductPages() {
        openPage(LITECART_SHOP_MAIN);

        mainPage = new ShopMainPage(driver);
        HashMap<String, String> detailsFromMainPage = mainPage.getProductDetailsFromMainPage("Campaigns", 1);
        mainPage.openProductPage("Campaigns", 1);

        productPage = new ShopProductPage(driver);
        HashMap<String, String> detailsFromProductPage = productPage.getProductDetailsFromProductPage();

        Assert.assertTrue(productPage.isPriceTheSame(detailsFromMainPage, detailsFromProductPage));
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }

}
