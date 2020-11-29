package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class ShopMainPage extends AbstractPage {

    //Locators
    private final String productsElement = "//li[@class=\"product column shadow hover-light\"]";
    private final String stickerElement = ".//div[contains(@class, \"sticker\")]";
    private final String shopMainPageUniqueElement = "//div[@id=\"box-most-popular\"]";
    private final String createAccountLinkLoc = "//td/a[contains(@href, \"create_account\")]";
    private final String emailInputLoc = "//input[@name=\"email\"]";
    private final String passwordInputLoc = "//input[@name=\"password\"]";
    private final String loginButtonLoc = "//button[@name=\"login\"]";
    private final String logoutButtonLoc = "//li/a[contains(@href, \"logout\")]";

    public ShopMainPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(shopMainPageUniqueElement)));
    }

    public List<WebElement> getAllProducts() {
        logger.info("Get all products from main page");
        WebElement productsEl = driver.findElement(By.xpath(productsElement));
        wait.until(ExpectedConditions.visibilityOf(productsEl));
        return driver.findElements(By.xpath(productsElement));
    }

    public int getProductStickersQty(WebElement product) {
        logger.info("Check '" + product.getText().replace("\n", " ") + "' product has only one sticker");
        List<WebElement> stickerEls = product.findElements(By.xpath(stickerElement));
        wait.until(ExpectedConditions.visibilityOf(stickerEls.get(0)));
        return stickerEls.size();
    }

    public void openProductPage(String block, int positionOfProduct) {
        logger.info("Click on product with position " + positionOfProduct);
        String productElement = "(//h3[contains(., " + block + ")]/following-sibling::div//li)[" + positionOfProduct + "]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productElement)));
        driver.findElement(By.xpath(productElement)).click();
    }

    public HashMap<String, String> getProductDetailsFromMainPage(String block, int positionOfProduct) {
        logger.info("Getting product details from the main page");
        HashMap<String, String> detailsObj = new HashMap<>();

        String productElement = "(//h3[contains(., " + block + ")]/following-sibling::div//li)[" + positionOfProduct + "]";

        WebElement product = driver.findElement(By.xpath(productElement));
        WebElement regularPrice = product.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"regular-price\"]"));
        WebElement campaignPrice = product.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"campaign-price\"]"));

        detailsObj.put("name", product.findElement(By.xpath("//div[@class=\"name\"]")).getText());

        detailsObj.put("regular_price", regularPrice.getText());
        detailsObj.put("regular_price_text_decoration", regularPrice.getCssValue("text-decoration"));
        detailsObj.put("regular_price_text_color", regularPrice.getCssValue("color"));
        detailsObj.put("regular_price_font_size", regularPrice.getCssValue("font-size"));

        detailsObj.put("campaign_price", campaignPrice.getText());
        detailsObj.put("campaign_price_font_weight", campaignPrice.getCssValue("font-weight"));
        detailsObj.put("campaign_price_text_color", campaignPrice.getCssValue("color"));
        detailsObj.put("campaign_price_font_size", campaignPrice.getCssValue("font-size"));

        return detailsObj;
    }

    public void login(String email, String password) {
        logger.info("Login from main page as " + email);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginButtonLoc)));
        driver.findElement(By.xpath(emailInputLoc)).sendKeys(email);
        driver.findElement(By.xpath(passwordInputLoc)).sendKeys(password);
        driver.findElement(By.xpath(loginButtonLoc)).click();
    }

    public void clickOnCreateAccountLink() {
        logger.info("Click on Create Account link");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createAccountLinkLoc)));
        driver.findElement(By.xpath(createAccountLinkLoc)).click();
    }


    public void logout() {
        logger.info("Logging out...");
        driver.findElement(By.xpath(logoutButtonLoc)).click();
    }
}
