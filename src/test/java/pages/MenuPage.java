package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
    private WebDriver driver;

    //Locators
    public String appearenceMenuItem = "//span[contains(text(),\"Appearence\")]";
    public String templateSubMenuItem = "//span[contains(text(),\"Template\")]";
    public String logotypeSubMenuItem = "//span[contains(text(),\"Logotype\")]";

    public String catalogMenuItem = "//span[contains(text(),\"Catalog\")]";
    public String catalogSubMenuItem = "(//span[contains(text(),\"Catalog\")])[2]";
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

    public String geoZonesMenuItem = "//span[contains(text(),\"GeoZones\")]";

    public String languagesMenuItem = "//span[contains(text(),\"Languages\")]";

    public String modulesMenuItem = "//span[contains(text(),\"Modules\")]";

    public String ordersMenuItem = "//span[contains(text(),\"Orders\")]";
    public String pagesMenuItem = "//span[contains(text(),\"Pages\")]";
    public String reportsMenuItem = "//span[contains(text(),\"Reports\")]";
    public String settingsMenuItem = "//span[contains(text(),\"Settings\")]";
    public String slidesMenuItem = "//span[contains(text(),\"Slides\")]";
    public String taxMenuItem = "//span[contains(text(),\"Tax\")]";
    public String translationsMenuItem = "//span[contains(text(),\"Translations\")]";
    public String usersMenuItem = "//span[contains(text(),\"Users\")]";
    public String vqmodsMenuItem = "//span[contains(text(),\"vQmods\")]";



    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnCatalogMenuItem(){
        driver.findElement(By.xpath(catalogMenuItem)).click();
    }
}
