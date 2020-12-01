package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("SameParameterValue")
public class AdminCatalogPage extends AbstractPage {

    final Faker faker = new Faker();
    private final String productName;

    //Locators
    private final String addNewProductButtonLoc = "//a[@class=\"button\"][contains(., \"Add New Product\")]";

    private final String generalStatusLoc = "//label[contains(., \" %s\")]";
    private final String generalProductNameLoc = "//input[@name=\"name[en]\"]";
    private final String generalCodeLoc = "//input[@name=\"code\"]";
    private final String generalGenderLoc = "//td[contains(., \"%s\")]/preceding-sibling::td/input";
    private final String generalQtyLoc = "//input[@name=\"quantity\"]";
    private final String generalImageUploadLoc = "//input[@name=\"new_images[]\"]";
    private final String generalValidDateFromLoc = "//input[@name=\"date_valid_from\"]";

    private final String informationTabLoc = "//a[@href=\"#tab-information\"]";
    private final String informationManufacturerSelectLoc = "//select[@name=\"manufacturer_id\"]";
    private final String informationManufacturerOptionLoc = "//select[@name=\"manufacturer_id\"]/option[@value=1]";
    private final String informationKeywordsInputLoc = "//input[@name=\"keywords\"]";
    private final String informationShortDescriptionInputLoc = "//input[@name=\"short_description[en]\"]";
    private final String informationDescriptionInputLoc = "//div[@class=\"trumbowyg-editor\"]";
    private final String informationHeadTitleInputLoc = "//input[@name=\"head_title[en]\"]";
    private final String informationMetaDescriptionInputLoc = "//input[@name=\"meta_description[en]\"]";

    private final String pricesTabLoc = "//a[@href=\"#tab-prices\"]";
    private final String pricesPurchasePriceInputLoc = "//input[@name=\"purchase_price\"]";
    private final String pricesCurrencySelectorLoc = "//select[@name=\"purchase_price_currency_code\"]";
    private final String pricesCurrencyOptionUSDLoc = "//option[@value=\"USD\"]";

    private final String saveButtonLoc = "//button[@name=\"save\"]";

    private final String searchInputLoc = "//input[@name=\"query\"]";
    private final String searchResultLoc = "//a[contains(., \"%s\")]";


    public AdminCatalogPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addNewProductButtonLoc)));
        productName = faker.commerce().productName();
    }

    public String getProductName() {
        return productName;
    }

    public void clickOnAddNewProductButton() {
        logger.info("Clicking on Add New Product button");
        clickOn(addNewProductButtonLoc);
    }

    //General tab

    public void fillGeneralTab() {
        setStatus("Enabled");
        setRandomName();
        setRandomCode();
        setGender("Unisex");
        setRandomQty();
        uploadImage(generalImageUploadLoc, "product_image.jpg");
        setValidDateFrom();
    }

    private void setStatus(String status) {
        logger.info("Setting status: " + status);
        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(generalStatusLoc, status));
        clickOn(locator);
    }

    private void setRandomName() {
        logger.info("Setting random name for product");
        sendKeysTo(generalProductNameLoc, productName, false);
    }

    private void setRandomCode() {
        logger.info("Setting random code for product");
        sendKeysTo(generalCodeLoc, faker.commerce().promotionCode(), false);
    }

    private void setGender(String gender) {
        logger.info("Setting gender: " + gender);
        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(generalGenderLoc, gender));
        clickOn(locator);
    }

    private void setRandomQty() {
        logger.info("Setting random qty for product");
        sendKeysTo(generalQtyLoc, String.valueOf(faker.number().randomDigitNotZero()), true);
    }

    private void setValidDateFrom() {
        logger.info("Setting valid date from for product");
        LocalDate localDate = faker.date().future(3, TimeUnit.HOURS)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String dateNumbers = String.valueOf(localDate.getDayOfMonth() + localDate.getMonthValue() + localDate.getYear());

        sendKeysTo(generalValidDateFromLoc, dateNumbers, false);
    }

    //Information tab

    public void clickOnInformationTab() {
        logger.info("Clicking on Information tab");
        clickOn(informationTabLoc);
    }

    public void fillInformationTab() {
        setManufacturer();
        setKeywords();
        setShortDescription();
        setDescription();
        setHeadTitle();
        setMetaDescription();
    }

    private void setManufacturer() {
        logger.info("Setting manufacturer with value = 1");
        clickOn(informationManufacturerSelectLoc);
        clickOn(informationManufacturerOptionLoc);
    }

    private void setKeywords() {
        logger.info("Setting keyword for product");
        sendKeysTo(informationKeywordsInputLoc, faker.funnyName().name(), false);
    }

    private void setShortDescription() {
        logger.info("Setting short description for product");
        sendKeysTo(informationShortDescriptionInputLoc, faker.lorem().sentence(), false);
    }

    private void setDescription() {
        logger.info("Setting description for product");
        sendKeysTo(informationDescriptionInputLoc, faker.lorem().paragraph(), false);
    }

    private void setHeadTitle() {
        logger.info("Setting head title for product");
        sendKeysTo(informationHeadTitleInputLoc, faker.lorem().sentence(3), false);
    }

    private void setMetaDescription() {
        logger.info("Setting meta description for product");
        sendKeysTo(informationMetaDescriptionInputLoc, faker.lorem().words(13).toString(), false);
    }

    //Prices tab

    public void clickOnPricesTab() {
        logger.info("Clicking on prices tab");
        clickOn(pricesTabLoc);
    }

    public void fillPricesTab() {
        setPurchasePrice();
        setCurrency();
    }

    private void setPurchasePrice() {
        logger.info("Setting purchase price for product");
        sendKeysTo(pricesPurchasePriceInputLoc, faker.commerce().price(), true);
    }

    private void setCurrency() {
        logger.info("Setting USD currency for product");
        clickOn(pricesCurrencySelectorLoc);
        clickOn(pricesCurrencyOptionUSDLoc);
    }

    public void clickOnSaveButton() {
        logger.info("Clicking on Save button");
        clickOn(saveButtonLoc);
    }

    public boolean isProductShownInCatalog(String productName) {
        logger.info("Checking if product is shown in catalog");
        makeSearchInCatalog(productName);

        Formatter formatter = new Formatter();
        String locator = String.valueOf(formatter.format(searchResultLoc, productName));
        WebElement searchResult = driver.findElement(By.xpath(locator));

        return searchResult.isDisplayed();
    }

    public void makeSearchInCatalog(String productName) {
        logger.info("Making search in catalog by: " + productName);
        sendKeysTo(searchInputLoc, productName, false);
        driver.findElement(By.xpath(searchInputLoc)).sendKeys(Keys.ENTER);
    }
}
