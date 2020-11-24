package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class ShopProductPage extends AbstractPage {

    //Locators
    private final String productPageUniqueElement = "//div[@id=\"box-product\"]";

    public ShopProductPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productPageUniqueElement)));
    }

    public HashMap<String, String> getProductDetailsFromProductPage() {
        logger.info("Getting product details from the product page");
        HashMap<String, String> detailsObj = new HashMap<>();

        detailsObj.put("name", driver.findElement(By.xpath("//h1[@class=\"title\"]")).getText());
        detailsObj.put("regular_price", driver.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"regular-price\"]")).getText());
        detailsObj.put("campaign_price", driver.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"campaign-price\"]")).getText());

        return detailsObj;
    }

    public boolean isNameTheSame(HashMap<String, String> map1, HashMap<String, String> map2) {
        logger.info("Checking name is the same");
        return map1.get("name").equals(map2.get("name"));
    }

    public boolean isPriceTheSame(HashMap<String, String> map1, HashMap<String, String> map2) {
        logger.info("Checking price is the same");
        return map1.get("regular_price").equals(map2.get("regular_price")) &&
                map1.get("campaign_price").equals(map2.get("campaign_price"));
    }
}
