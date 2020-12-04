package cases;

/*
Задание 13. Сделайте сценарий работы с корзиной
Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.

1) открыть главную страницу
2) открыть первый товар из списка
2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
3) подождать, пока счётчик товаров в корзине обновится
4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза,
чтобы в общей сложности в корзине было 3 единицы товара
5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.ShopCartPage;
import pages.ShopMainPage;
import pages.ShopProductPage;

import static config.app.LITECART_SHOP_MAIN;

public class CartTest extends AbstractTest {

    ShopMainPage mainPage;
    ShopProductPage productPage;
    ShopCartPage cartPage;
    final int qtyOfProducts = 3;

    @Test
    public void workingWithCartTest() {
        openPage(LITECART_SHOP_MAIN);
        mainPage = new ShopMainPage(driver);
        for (int i = 0; i < qtyOfProducts; i++) {
            mainPage.openProductPage("Most Popular", 1);
            productPage = new ShopProductPage(driver);
            productPage.setMediumSize();
            productPage.clickOnAddToCartButton();
            openPage(LITECART_SHOP_MAIN);
        }
        mainPage.openCheckout();
        cartPage = new ShopCartPage(driver);
        cartPage.deleteAllProducts();
        Assert.assertTrue(cartPage.IsCartEmpty());
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
