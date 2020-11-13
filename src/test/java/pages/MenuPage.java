package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuPage {
    private WebDriver driver;
    private WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(MenuPage.class);

    public enum ListOfMenuItems {
        APPEARANCE("", "Appearence", ""),
        APPEARANCE_TEMPLATE("doc-template", "Template", "Template"),
        APPEARANCE_LOGOTYPE("doc-logotype", "Logotype", "Logotype"),
        CATALOG("", "Catalog", ""),
        CATALOG_CATALOG("doc-catalog", "Catalog", "Catalog"),
        CATALOG_PRODUCT_GROUPS("doc-product_groups", "Product Groups", "Product Groups"),
        CATALOG_OPTIONS_GROUPS("doc-option_groups", "Option Groups", "Option Groups"),
        CATALOG_MANUFACTURERS("doc-manufacturers", "Manufacturers", "Manufacturers"),
        CATALOG_SUPPLIERS("doc-suppliers", "Suppliers", "Suppliers"),
        CATALOG_DELIVERY_STATUSES("doc-delivery_statuses", "Delivery Statuses", "Delivery Statuses"),
        CATALOG_SOLD_OUT_STATUSES("doc-sold_out_statuses", "Sold Out Statuses", "Sold Out Statuses"),
        CATALOG_QUANTITY_UNITS("doc-quantity_units", "Quantity Unit", "Quantity Units"),
        CATALOG_CSV_IMPORT_EXPORT("doc-csv", "CSV Import/Export", "CSV Import/Export"),
        COUNTRIES("", "Countries", ""),
        CURRENCIES("", "Currencies", ""),
        CUSTOMERS("", "Customers", ""),
        CUSTOMERS_CUSTOMERS("doc-customers", "Customers", "Customers"),
        CUSTOMERS_CSV_IMPORT_EXPORT("doc-csv", "CSV Import/Export", "CSV Import/Export"),
        CUSTOMERS_NEWSLETTER("doc-newsletter", "Newsletter", "Newsletter"),
        GEOZONES("", "Geo Zones", ""),
        LANGUAGES("", "Languages", ""),
        LANGUAGES_LANGUAGES("doc-languages", "Languages", "Languages"),
        LANGUAGES_STORAGE_ENCODING("doc-storage_encoding", "Storage Encoding", "Storage Encoding"),
        MODULES("", "Modules", ""),
        MODULES_CUSTOMER("doc-customer", "Customer", "Customer Modules"),
        MODULES_SHIPPING("doc-shipping", "Shipping", "Shipping Modules"),
        MODULES_PAYMENT("doc-payment", "Payment", "Payment Modules"),
        MODULES_ORDER_TOTAL("doc-order_total", "Order Total", "Order Total Modules"),
        MODULES_ORDER_SUCCESS("doc-order_success", "Order Success", "Order Success Modules"),
        MODULES_ORDER_ACTION("doc-order_action", "Order Action", "Order Action Modules"),
        MODULES_BACKGROUND_JOBS("doc-jobs", "Background Jobs", "Job Modules"),
        ORDERS("", "Orders", ""),
        ORDERS_ORDERS("doc-orders", "Orders", "Orders"),
        ORDERS_ORDER_STATUSES("doc-order_statuses", "Order Statuses", "Order Statuses"),
        PAGES("", "Pages", ""),
        REPORTS("", "Reports", ""),
        REPORTS_MONTHLY_SALES("doc-monthly_sales", "Monthly Sales", "Monthly Sales"),
        REPORTS_MOST_SOLD_PRODUCTS("doc-most_sold_products", "Most Sold Products", "Most Sold Products"),
        REPORTS_MOST_SHOPPING_CUSTOMERS("doc-most_shopping_customers", "Most Shopping Customers", "Most Shopping Customers"),
        SETTINGS("", "Settings", ""),
        SETTINGS_STORE_INFO("doc-store_info", "Store Info", "Settings"),
        SETTINGS_DEFAULTS("doc-defaults", "Defaults", "Settings"),
        SETTINGS_GENERAL("doc-general", "General", "Settings"),
        SETTINGS_LISTINGS("doc-listings", "Listings", "Settings"),
        SETTINGS_IMAGES("doc-images", "Images", "Settings"),
        SETTINGS_CHECKOUT("doc-checkout", "Checkout", "Settings"),
        SETTINGS_ADVANCED("doc-advanced", "Advanced", "Settings"),
        SETTINGS_SECURITY("doc-security", "Security", "Settings"),
        SLIDES("", "Slides", ""),
        TAX("", "Tax", ""),
        TAX_CLASSES("doc-tax_classes", "Tax Classes", "Tax Classes"),
        TAX_RATES("doc-tax_rates", "Tax Rates", "Tax Rates"),
        TRANSLATIONS("", "Translations", ""),
        TRANSLATIONS_SEARCH_TRANSLATIONS("doc-search", "Search Translations", "Search Translations"),
        TRANSLATIONS_SCAN_FILES("doc-scan", "Scan Files", " Scan Files For Translations"),
        TRANSLATIONS_CSV_IMPORT_EXPORT("doc-csv", "CSV Import/Export", "CSV Import/Export"),
        USERS("", "Users", ""),
        VQMODS("", "vQmods", ""),
        VQMODS_VQMODS("doc-vqmods", "vQmods", "vQmods"),
        ;

        private final String id;
        private final String text;
        private final String h1;

        ListOfMenuItems(String id, String text, String h1) {
            this.id = id;
            this.text = text;
            this.h1 = h1;
        }

        public String getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public String getH1() {
            return h1;
        }
    }

    public List<ListOfMenuItems> getMenuItems() {
        return Arrays.stream(ListOfMenuItems.values()).collect(Collectors.toCollection(ArrayList::new));
    }

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

    public void checkMenuElementIsDisplayed(String element, String text) {
        logger.info("Check if " + element + " element with " + text + " text is displayed");
        String locator = "//" + element + "[contains(., \"" + text + "\")]";
        WebElement el = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(el));
        Assert.assertTrue(el.isDisplayed());
    }

    public String getMenuLocator(String id, String text) {
        if (id.isEmpty()) {
            return "//span[contains(text(),\"" + text + "\")]";
        } else {
            return "//li[@id=\"" + id + "\"]//span[contains(., \"" + text + "\")]";
        }
    }
}
