package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ShopProductPage extends AbstractPage {

    //Locators
    private final String productPageUniqueElement = "//div[@id=\"box-product\"]";
    private final String addTOCartButtonLoc = "//button[@name=\"add_cart_product\"]";
    private final String setSizeSelectorLoc = "//select[@name=\"options[Size]\"]";
    private final String setMediumSizeOptionLoc = "//option[@value=\"Medium\"]";


    public ShopProductPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productPageUniqueElement)));
    }

    public HashMap<String, String> getProductDetailsFromProductPage() {
        logger.info("Getting product details from the product page");
        HashMap<String, String> detailsObj = new HashMap<>();

        WebElement regularPrice = driver.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"regular-price\"]"));
        WebElement campaignPrice = driver.findElement(By.xpath("//div[@class=\"price-wrapper\"]/*[@class=\"campaign-price\"]"));

        detailsObj.put("name", driver.findElement(By.xpath("//h1[@class=\"title\"]")).getText());

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

    public static boolean isNameTheSame(HashMap<String, String> map1, HashMap<String, String> map2) {
        logger.info("Checking name is the same");
        return map1.get("name").equals(map2.get("name"));
    }

    public static boolean isPriceTheSame(HashMap<String, String> map1, HashMap<String, String> map2) {
        logger.info("Checking price is the same");
        return map1.get("regular_price").equals(map2.get("regular_price")) &&
                map1.get("campaign_price").equals(map2.get("campaign_price"));
    }

    public static boolean isRegularPriceStrikethroughAndGrey(HashMap<String, String> map) {
        logger.info("Checking if regular price is strikethrough and grey");
        return checkTextIsStrikethrough(map.get("regular_price_text_decoration")) &&
                checkColorIsGrey(map.get("regular_price_text_color"));
    }

    public static boolean isCampaignPriceBoldAndRed(HashMap<String, String> map) {
        logger.info("Checking if campaign font is bold and red");
        return checkTextIsBold(map.get("campaign_price_font_weight")) &&
                checkColorIsRed(map.get("campaign_price_text_color"));
    }

    public static boolean isCampaignFontIsBigger(HashMap<String, String> map) {
        logger.info("Checking campaign font is bigger than font of regular price");
        String campaignFont = map.get("campaign_price_font_size").replace("px", "");
        String regularFont = map.get("regular_price_font_size").replace("px", "");
        return Float.parseFloat(campaignFont) > Float.parseFloat(regularFont);
    }

    /**
     * Check if all RGB parameters are the same (which means color is grey)
     */
    private static boolean checkColorIsGrey(String rgba) {
        logger.info("Checking if color is grey");
        String[] colors = StringUtils.substringBetween(rgba, "(", ")")
                .replace(" ", "")
                .split(",");
        ArrayList<String> rgbOnly = new ArrayList<>(Arrays.asList(colors));
        rgbOnly.remove(3);  //remove "a" from "rgba"
        return rgbOnly.stream().distinct().count() == 1;
    }

    /**
     * Check if G and B parameters in RGBa are 0 (which means color is red)
     */
    private static boolean checkColorIsRed(String rgba) {
        logger.info("Checking if color is red");
        String[] colors = StringUtils.substringBetween(rgba, "(", ")")
                .replace(" ", "")
                .split(",");
        return colors[1].equals("0") && colors[2].equals("0");
    }

    /**
     * Check if text-decoration has 'line-through' parameter
     */
    private static boolean checkTextIsStrikethrough(String rawParametersFromCss) {
        logger.info("Checking if text is strikethrough");
        return rawParametersFromCss.contains("line-through");
    }

    /**
     * Check if font-weight has '700' value
     */
    private static boolean checkTextIsBold(String rawParametersFromCss) {
        logger.info("Checking if text is bold");
        return rawParametersFromCss.contains("700");
    }

    public void setMediumSize() {
        logger.info("Trying to set Medium Size");
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            clickOn(setSizeSelectorLoc);
            clickOn(setMediumSizeOptionLoc);
        } catch (TimeoutException e) {
            System.err.println("No Size field found");
        }
    }

    public void clickOnAddToCartButton() {
        logger.info("Clicking on Add to Cart button");
        clickOn(addTOCartButtonLoc);
    }
}
