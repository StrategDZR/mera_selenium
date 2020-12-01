package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AbstractPage {

    public final WebDriver driver;
    public final WebDriverWait wait;
    static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public List<WebElement> getList(String locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return driver.findElements(By.xpath(locator));
    }

    public boolean listIsSortedAlphabetically(List<WebElement> list) {
        logger.info("Checking alphabetical order");
        List<String> initialListOfNames = new ArrayList<>();
        List<String> sortedListOfNames = new ArrayList<>();
        list.forEach(element -> {
            initialListOfNames.add(element.getText());
            sortedListOfNames.add(element.getText());
        });
        sortedListOfNames.sort(Comparator.naturalOrder());
        return initialListOfNames.equals(sortedListOfNames);
    }

    public void clickOn(String locatorByXpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorByXpath)));
        driver.findElement(By.xpath(locatorByXpath)).click();
    }

    public void sendKeysTo(String locatorByXpath, String keys, boolean shouldBeClearedBefore) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorByXpath)));
        if (shouldBeClearedBefore) {
            driver.findElement(By.xpath(locatorByXpath)).clear();
        }
        driver.findElement(By.xpath(locatorByXpath)).sendKeys(keys);
    }

    public void uploadImage(String locatorByXpath, String fileName) {
        logger.info("Uploading image with name: " + fileName);

        String path = null;
        try {
            path = new File("src/test/java/attachments/img/" + fileName).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendKeysTo(locatorByXpath, path, false);
    }
}