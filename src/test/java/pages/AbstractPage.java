package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AbstractPage {

    public WebDriver driver;
    public WebDriverWait wait;
    static Logger logger = LoggerFactory.getLogger(AbstractPage.class);

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
}
