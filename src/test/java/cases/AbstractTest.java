package cases;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminLoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static pages.AbstractPage.logger;

public class AbstractTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public void openPageAndLoginAsAdmin(String url) {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        driver.navigate().to(url);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(adminLoginPage.loginButton)));
        adminLoginPage.loginAsAdmin();
    }

    public void openPage(String url) {
        driver.navigate().to(url);
    }

    public boolean isConsoleErrorExists() {
        List<LogEntry> errors = driver.manage().logs().get("browser").getAll();
        logger.info("Following errors were found: " + errors);
        return errors.size() > 0;
    }
}
