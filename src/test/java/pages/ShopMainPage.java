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
        driver.findElement(By.xpath(productElement)).click();
    }

    public HashMap<String, String> getProductDetailsFromMainPage(String block, int positionOfProduct) {
        logger.info("Getting product details from the main page");
        HashMap<String, String> detailsObj = new HashMap<>();

        String productElement = "(//h3[contains(., " + block + ")]/following-sibling::div//li)[" + positionOfProduct + "]";
        WebElement product = driver.findElement(By.xpath(productElement));

        detailsObj.put("name", product.findElement(By.xpath("//div[@class=\"name\"]")).getText());
        detailsObj.put("regular_price", product.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"regular-price\"]")).getText());
        detailsObj.put("campaign_price", product.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"campaign-price\"]")).getText());

        return detailsObj;
    }

}
