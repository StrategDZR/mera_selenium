package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShopMainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(ShopMainPage.class);

    //Locators
    private final String products = "//li[@class=\"product column shadow hover-light\"]";
    private final String sticker = ".//div[contains(@class, \"sticker\")]";

    public ShopMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);

    }

    public List<WebElement> getAllProducts() {
        logger.info("Get all products from main page");
        WebElement productsEl = driver.findElement(By.xpath(products));
        wait.until(ExpectedConditions.visibilityOf(productsEl));
        return driver.findElements(By.xpath(products));
    }

    public void checkProductHasOnlyOneSticker(WebElement product) {
        logger.info("Check '" + product.getText().replace("\n", " ") + "' product has only one sticker");
        List<WebElement> stickerEls = product.findElements(By.xpath(sticker));
        wait.until(ExpectedConditions.visibilityOf(stickerEls.get(0)));
        Assert.assertEquals(1, stickerEls.size());
    }
}
