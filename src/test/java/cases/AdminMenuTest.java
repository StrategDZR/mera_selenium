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



    }

    @After
    public void tierDown() {
        driver.quit();
    }
}