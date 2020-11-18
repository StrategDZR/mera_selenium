package cases;

/*
Задание 8. Сделайте сценарий, проверяющий наличие стикеров у товаров
Сделайте сценарий, проверяющий наличие стикеров у всех товаров в учебном приложении litecart на главной странице.
Стикеры -- это полоски в левом верхнем углу изображения товара, на которых написано New или Sale или что-нибудь другое.
Сценарий должен проверять, что у каждого товара имеется ровно один стикер.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ShopMainPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static config.app.LITECART_SHOP_MAIN;

public class ShopMainPageHasStickersTest {
    private WebDriver driver;
    private WebDriverWait wait;

    ShopMainPage shopMainPage;

    @Before
    public void tierUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        shopMainPage = new ShopMainPage(driver);
    }

    @Test
    public void testProductHasSticker() {
        driver.navigate().to(LITECART_SHOP_MAIN);

        List<WebElement> products = shopMainPage.getAllProducts();
        products.forEach(product ->
            shopMainPage.checkProductHasOnlyOneSticker(product)
        );
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
