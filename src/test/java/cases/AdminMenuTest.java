package cases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MenuPage;

import java.util.concurrent.TimeUnit;

import static config.app.BASIC_URL;

/*
Задание 7. Сделайте сценарий, проходящий по всем разделам админки
Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
1) входит в панель администратора http://localhost/litecart/admin
2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)
 */

public class AdminMenuTest {

    private WebDriver driver;
    private WebDriverWait wait;

    LoginPage loginPage;
    MenuPage menuPage;

    @Before
    public void tierUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
    }

    @Test
    public void testAdminMenu() {
        driver.navigate().to(BASIC_URL);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));

        loginPage.loginAsAdmin();
        //check Appearence menu
        menuPage.clickOnMenuItem(menuPage.appearenceMenuItem);
        menuPage.checkSubMenuIsDisplayed("Template");
        menuPage.clickOnMenuItem(menuPage.templateSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Template");
        menuPage.clickOnMenuItem(menuPage.logotypeSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Logotype");
        //check Catalog menu
        menuPage.clickOnMenuItem(menuPage.catalogMenuItem);
        menuPage.checkSubMenuIsDisplayed("Manufacturers");
        menuPage.clickOnMenuItem(menuPage.catalogSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Catalog");
        menuPage.clickOnMenuItem(menuPage.productGroupsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Product Groups");
        menuPage.clickOnMenuItem(menuPage.optionGroupsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Option Groups");
        menuPage.clickOnMenuItem(menuPage.manufacturersSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Manufacturers");
        menuPage.clickOnMenuItem(menuPage.suppliersSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Suppliers");
        menuPage.clickOnMenuItem(menuPage.deliveryStatusesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Delivery Statuses");
        menuPage.clickOnMenuItem(menuPage.soldOutStatusesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Sold Out Statuses");
        menuPage.clickOnMenuItem(menuPage.quantityUnitsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Quantity Units");
        menuPage.clickOnMenuItem(menuPage.csvImportExportSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("CSV Import/Export");
        //check Countries menu
        menuPage.clickOnMenuItem(menuPage.countriesMenuItem);
        menuPage.checkH1ElementIsDisplayed("Countries");
        //check Currencies menu
        menuPage.clickOnMenuItem(menuPage.currenciesMenuItem);
        menuPage.checkH1ElementIsDisplayed("Currencies");
        //check Customers menu
        menuPage.clickOnMenuItem(menuPage.customersMenuItem);
        menuPage.checkSubMenuIsDisplayed("Newsletter");
        menuPage.clickOnMenuItem(menuPage.customersSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Customers");
        menuPage.clickOnMenuItem(menuPage.csvImportExportSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("CSV Import/Export");
        menuPage.clickOnMenuItem(menuPage.newsletterSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Newsletter");
        //check Geo Zones menu
        menuPage.clickOnMenuItem(menuPage.geoZonesMenuItem);
        menuPage.checkH1ElementIsDisplayed("Geo Zones");
        //check Languages menu
        menuPage.clickOnMenuItem(menuPage.languagesMenuItem);
        menuPage.checkSubMenuIsDisplayed("Storage Encoding");
        menuPage.clickOnMenuItem(menuPage.languagesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Languages");
        menuPage.clickOnMenuItem(menuPage.storageEncodingSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Storage Encoding");
        //check Modules menu
        menuPage.clickOnMenuItem(menuPage.modulesMenuItem);
        menuPage.checkSubMenuIsDisplayed("Payment");
        menuPage.clickOnMenuItem(menuPage.customerSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Customer Modules");
        menuPage.clickOnMenuItem(menuPage.shippingSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Shipping Modules");
        menuPage.clickOnMenuItem(menuPage.paymentSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Payment Modules");
        menuPage.clickOnMenuItem(menuPage.orderTotalSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Order Total Modules");
        menuPage.clickOnMenuItem(menuPage.orderSuccessSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Order Success Modules");
        menuPage.clickOnMenuItem(menuPage.orderActionsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Order Action Modules");
        menuPage.clickOnMenuItem(menuPage.backgroundJobsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Job Modules");
        //check Orders menu
        menuPage.clickOnMenuItem(menuPage.ordersMenuItem);
        menuPage.checkSubMenuIsDisplayed("Order Statuses");
        menuPage.clickOnMenuItem(menuPage.ordersSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Orders");
        menuPage.clickOnMenuItem(menuPage.orderStatusesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Order Statuses");
        //check Pages menu
        menuPage.clickOnMenuItem(menuPage.pagesMenuItem);
        menuPage.checkH1ElementIsDisplayed("Pages");
        //check Reports menu
        menuPage.clickOnMenuItem(menuPage.reportsMenuItem);
        menuPage.checkSubMenuIsDisplayed("Most Sold Products");
        menuPage.clickOnMenuItem(menuPage.monthlySalesMenuItem);
        menuPage.checkH1ElementIsDisplayed("Monthly Sales");
        menuPage.clickOnMenuItem(menuPage.mostSoldProductsMenuItem);
        menuPage.checkH1ElementIsDisplayed("Most Sold Products");
        menuPage.clickOnMenuItem(menuPage.mostShoppingCustomersMenuItem);
        menuPage.checkH1ElementIsDisplayed("Most Shopping Customers");
        //check Settings menu
        menuPage.clickOnMenuItem(menuPage.settingsMenuItem);
        menuPage.checkSubMenuIsDisplayed("Images");
        menuPage.clickOnMenuItem(menuPage.storeInfoSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.defaultsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.generalSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.listingsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.imagesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.checkoutSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.advancedSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        menuPage.clickOnMenuItem(menuPage.securitySubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Settings");
        //check Slides menu
        menuPage.clickOnMenuItem(menuPage.slidesMenuItem);
        menuPage.checkH1ElementIsDisplayed("Slides");
        //check Tax menu
        menuPage.clickOnMenuItem(menuPage.taxMenuItem);
        menuPage.checkSubMenuIsDisplayed("Tax Rates");
        menuPage.clickOnMenuItem(menuPage.taxClassesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Tax Classes");
        menuPage.clickOnMenuItem(menuPage.taxRatesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Tax Rates");
        //check Translations menu
        menuPage.clickOnMenuItem(menuPage.translationsMenuItem);
        menuPage.checkSubMenuIsDisplayed("Scan Files");
        menuPage.clickOnMenuItem(menuPage.searchTranslationsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Search Translations");
        menuPage.clickOnMenuItem(menuPage.scanFilesSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("Scan Files For Translations");
        menuPage.clickOnMenuItem(menuPage.csvImportExportSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("CSV Import/Export");
        //check Users menu
        menuPage.clickOnMenuItem(menuPage.usersMenuItem);
        menuPage.checkH1ElementIsDisplayed("Users");
        //check vQmods menu
        menuPage.clickOnMenuItem(menuPage.vqmodsMenuItem);
        menuPage.checkSubMenuIsDisplayed("vQmods");
        menuPage.clickOnMenuItem(menuPage.vqmodsSubMenuItem);
        menuPage.checkH1ElementIsDisplayed("vQmods");
    }

    @After
    public void tierDown() {
        driver.quit();
    }
}