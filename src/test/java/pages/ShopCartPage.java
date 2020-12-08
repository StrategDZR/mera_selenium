package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShopCartPage extends AbstractPage {

    //Locators
    private final String qtyOfProducts = "//td[@style=\"text-align: center;\"]";
    private final String firstShortcutLoc = "//li[@class=\"shortcut\"]";
    private final String removeButtonLoc = "//button[@name=\"remove_cart_item\"]";
    private final String tableLoc = "//table[@class=\"dataTable rounded-corners\"]/tbody";
    private final String emptyCartMessage = "//em[text()=\"There are no items in your cart.\"]";


    public ShopCartPage(WebDriver driver) {
        super(driver);
    }

    private int getProductQty() {
        logger.info("Get qty of products in the cart");
        List<WebElement> list = driver.findElements(By.xpath(qtyOfProducts));
        return list.stream().mapToInt(webElement -> Integer.parseInt(webElement.getText())).sum();
    }

    public void deleteAllProducts() {
        logger.info("Deleting all products in the cart");
        WebElement table = driver.findElement(By.xpath(tableLoc));

        try {
            clickOn(firstShortcutLoc);
        } catch (TimeoutException e) {
            System.err.println("No shortcuts found");
        }

        while (driver.findElements(By.xpath(qtyOfProducts)).size() > 0) {
            clickOn(removeButtonLoc);
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(qtyOfProducts))));
        }
    }

    public boolean IsCartEmpty() {
        return driver.findElement(By.xpath(emptyCartMessage)).isDisplayed();
    }
}
