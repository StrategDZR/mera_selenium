package cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.ShopMainPage;
import pages.ShopProductPage;

import java.util.HashMap;

import static config.app.LITECART_SHOP_MAIN;
import static pages.ShopProductPage.*;

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
    HashMap<String, String> detailsFromMainPage;
    HashMap<String, String> detailsFromProductPage;

    @Before
    public void setUp() {
        super.setUp();

        openPage(LITECART_SHOP_MAIN);

        mainPage = new ShopMainPage(driver);
        detailsFromMainPage = mainPage.getProductDetailsFromMainPage("Campaigns", 1);
        mainPage.openProductPage("Campaigns", 1);

        productPage = new ShopProductPage(driver);
        detailsFromProductPage = productPage.getProductDetailsFromProductPage();
    }

    @Test
    public void checkTextIsTheSameOnMainAndProductPages() {
        Assert.assertTrue("Incorrect page is opened: name is not the same",
                isNameTheSame(detailsFromMainPage, detailsFromProductPage));
    }

    @Test
    public void checkPriceIsTheSameOnMainAndProductPages() {
        Assert.assertTrue("Incorrect page is opened: price is not the same",
                isPriceTheSame(detailsFromMainPage, detailsFromProductPage));
    }

    @Test
    public void checkRegularPriceIsStrikethroughAndGreyOnMainAndProductPage() {
        Assert.assertTrue("Incorrect text decoration: regular price on the main page is not strikethrough and grey",
                isRegularPriceStrikethroughAndGrey(detailsFromMainPage));
        Assert.assertTrue("Incorrect text decoration: regular price on the product page is not strikethrough and grey",
                isRegularPriceStrikethroughAndGrey(detailsFromProductPage));
    }

    @Test
    public void checkCampaignPriceIsBoldAndRedOnMainAndProductPage() {
        Assert.assertTrue("Incorrect text decoration: campaign price on the main page is not bold and red",
                isCampaignPriceBoldAndRed(detailsFromMainPage));
        Assert.assertTrue("Incorrect text decoration: campaign price on the product page is not bold and red",
                isCampaignPriceBoldAndRed(detailsFromProductPage));
    }

    @Test
    public void checkCampaignPriceIsBiggerThanRegularPriceOnMainAndProductPage() {
        Assert.assertTrue("Incorrect text decoration: campaign price on the main page is not bigger than regular price",
                isCampaignFontIsBigger(detailsFromMainPage));
        Assert.assertTrue("Incorrect text decoration: campaign price on the main page is not bigger than regular price",
                isCampaignFontIsBigger(detailsFromProductPage));
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }

}
