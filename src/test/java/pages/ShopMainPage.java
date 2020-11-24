package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShopMainPage extends AbstractPage {

    //Locators
    private final String productsElement = "//li[@class=\"product column shadow hover-light\"]";
    private final String stickerElement = ".//div[contains(@class, \"sticker\")]";
    private final String shopMainPageUniqueElement = ;

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

    public void checkProductHasOnlyOneSticker(WebElement product) {
        logger.info("Check '" + product.getText().replace("\n", " ") + "' product has only one sticker");
        List<WebElement> stickerEls = product.findElements(By.xpath(stickerElement));
        wait.until(ExpectedConditions.visibilityOf(stickerEls.get(0)));
        Assert.assertEquals(1, stickerEls.size());
    }

    public void getProductDetails(int positionOfProduct){

    }

    public void openProductPage(int positionOfProduct){

    }
}
