package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuPage {
    private WebDriver driver;
    private WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(MenuPage.class);

    //Locators
    public String appearenceMenuItem = "//span[contains(text(),\"Appearence\")]";
    public String templateSubMenuItem = "//span[contains(text(),\"Template\")]";
    public String logotypeSubMenuItem = "//span[contains(text(),\"Logotype\")]";

    public String catalogMenuItem = "//span[contains(text(),\"Catalog\")]";
    public String catalogSubMenuItem = "//li[@id=\"doc-catalog\"]//span[contains(., \"Catalog\")]";
    public String productGroupsSubMenuItem = "//span[contains(text(),\"Product Groups\")]";
    public String optionGroupsSubMenuItem = "//span[contains(text(),\"Option Groups\")]";
    public String manufacturersSubMenuItem = "//span[contains(text(),\"Manufacturers\")]";
    public String suppliersSubMenuItem = "//span[contains(text(),\"Suppliers\")]";
    public String deliveryStatusesSubMenuItem = "//span[contains(text(),\"Delivery Statuses\")]";
    public String soldOutStatusesSubMenuItem = "//span[contains(text(),\"Sold Out Statuses\")]";
    public String quantityUnitsSubMenuItem = "//span[contains(text(),\"Quantity Units\")]";
    public String csvImportExportSubMenuItem = "//span[contains(text(),\"CSV Import/Export\")]";

    public String countriesMenuItem = "//span[contains(text(),\"Countries\")]";

    public String currenciesMenuItem = "//span[contains(text(),\"Currencies\")]";

    public String customersMenuItem = "//span[contains(text(),\"Customers\")]";
    public String customersSubMenuItem = "//ul[@class=\"docs\"]//span[contains(., \"Customers\")]";
    public String newslettersSubMenuItem = "//span[contains(text(),\"Newsletters\")]";

    public String geoZonesMenuItem = "//span[contains(text(),\"GeoZones\")]";

    public String languagesMenuItem = "//span[contains(text(),\"Languages\")]";
    public String languagesSubMenuItem = "//li[@id=\"doc-languages\"]//span[contains(., \"Languages\")]";
    public String storageEncodingSubMenuItem = "//span[contains(text(),\"Storage Encoding\")]";

    public String modulesMenuItem = "//span[contains(text(),\"Modules\")]";
    public String customerSubMenuItem = "//li[@id=\"doc-customer\"]//span[contains(., \"Customers\")]";
    public String shippingSubMenuItem = "//span[contains(text(),\"Shipping\")]";
    public String paymentSubMenuItem = "//span[contains(text(),\"Payment\")]";
    public String orderTotalSubMenuItem = "//span[contains(text(),\"Order Total\")]";
    public String orderSuccessSubMenuItem = "//span[contains(text(),\"Order Success\")]";
    public String orderActionsSubMenuItem = "//span[contains(text(),\"Order Actions\")]";
    public String backgroundJobsSubMenuItem = "//span[contains(text(),\"Background Jobs\")]";

    public String ordersMenuItem = "//span[contains(text(),\"Orders\")]";
    public String ordersSubMenuItem = "//li[@id=\"doc-orders\"]//span[contains(., \"Orders\")]";
    public String orderStatusesSubMenuItem = "//span[contains(text(),\"Order Statuses\")]";

    public String pagesMenuItem = "//span[contains(text(),\"Pages\")]";

    public String reportsMenuItem = "//span[contains(text(),\"Reports\")]";
    public String monthlySalesMenuItem = "//span[contains(text(),\"Monthly Sales\")]";
    public String mostSoldProductsMenuItem = "//span[contains(text(),\"Most Sold Products\")]";
    public String mostShoppingCustomersMenuItem = "//span[contains(text(),\"Most Shopping Customers\")]";

    public String settingsMenuItem = "//span[contains(text(),\"Settings\")]";
    public String storeInfoMenuItem = "//span[contains(text(),\"Store Info\")]";
    public String defaultsSubMenuItem = "//span[contains(text(),\"Defaults\")]";
    public String generalSubMenuItem = "//span[contains(text(),\"General\")]";
    public String listingsSubMenuItem = "//span[contains(text(),\"Listings\")]";
    public String imagesSubMenuItem = "//span[contains(text(),\"Images\")]";
    public String checkoutSubMenuItem = "//span[contains(text(),\"Checkout\")]";
    public String advancedSubMenuItem = "//span[contains(text(),\"Advanced\")]";
    public String securitySubMenuItem = "//span[contains(text(),\"Security\")]";

    public String slidesMenuItem = "//span[contains(text(),\"Slides\")]";

    public String taxMenuItem = "//span[contains(text(),\"Tax\")]";
    public String taxClassesSubMenuItem = "//span[contains(text(),\"Tax Classes\")]";
    public String taxRatesSubMenuItem = "//span[contains(text(),\"Tax Rates\")]";

    public String translationsMenuItem = "//span[contains(text(),\"Translations\")]";
    public String searchTranslationsSubMenuItem = "//span[contains(text(),\"Search Translations\")]";
    public String scanFilesSubMenuItem = "//span[contains(text(),\"Scan Files\")]";

    public String usersMenuItem = "//span[contains(text(),\"Users\")]";

    public String vqmodsMenuItem = "//span[contains(text(),\"vQmods\")]";
    public String vqmodsSubMenuItem = "//li[@id=\"doc-vqmods\"]//span[contains(., \"vQmods\")]";


    public MenuPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void clickOnMenuItem(String locator) {
        logger.info("Try to click in " + locator);
        WebElement el = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(el));
        driver.findElement(By.xpath(locator)).click();
    }

    public void checkSubMenuIsDisplayed(String subMenuText) {
        logger.info("Check if " + subMenuText + " is displayed");
        String locator = "//span[contains(., \"" + subMenuText + "\")]";
        WebElement el = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(el));
        Assert.assertTrue(el.isDisplayed());
    }

    public void checkH1ElementIsDisplayed(String h1Text) {
        logger.info("Check if " + h1Text + " h1 element is displayed");
        String locator = "//h1[contains(., \"" + h1Text + "\")]";
        WebElement el = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(el));
        Assert.assertTrue(el.isDisplayed());
    }
}
