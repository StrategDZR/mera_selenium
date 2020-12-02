package cases;

/*
Задание 12. Сделайте сценарий добавления товара

Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).

Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product",
заполнить поля с информацией о товаре и сохранить.

Достаточно заполнить только информацию на вкладках General, Information и Prices.
Скидки (Campaigns) на вкладке Prices можно не добавлять.

Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу.

Картинку с изображением товара нужно уложить в репозиторий вместе с кодом.
При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет.
Надо средствами языка программирования преобразовать относительный путь в абсолютный.

После сохранения товара нужно убедиться, что он появился в каталоге (в админке).
Клиентскую часть магазина можно не проверять.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.AdminCatalogPage;

import static config.app.LITECART_ADMIN;

public class AddProductTest extends AbstractTest {

    AdminCatalogPage adminCatalogPage;

    @Test
    public void addProductTest() {
        openPageAndLoginAsAdmin(LITECART_ADMIN + "?app=catalog&doc=catalog");
        adminCatalogPage = new AdminCatalogPage(driver);
        adminCatalogPage.clickOnAddNewProductButton();
        adminCatalogPage.fillGeneralTab();
        adminCatalogPage.clickOnInformationTab();
        adminCatalogPage.fillInformationTab();
        adminCatalogPage.clickOnPricesTab();
        adminCatalogPage.fillPricesTab();
        adminCatalogPage.clickOnSaveButton();
        Assert.assertTrue(adminCatalogPage.isProductShownInCatalog(adminCatalogPage.getProductName()));
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}
