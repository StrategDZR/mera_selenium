package cases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminMainPage;
import pages.AdminLoginPage;

import java.util.concurrent.TimeUnit;
import static config.app.*;

public class Task2Test {
    private WebDriver driver;
    private WebDriverWait wait;

    AdminLoginPage adminLoginPage;
    AdminMainPage adminMainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        adminLoginPage = new AdminLoginPage(driver);
        adminMainPage = new AdminMainPage(driver);
    }

    @Test
    public void testLoginToAdmin() {
        //следующие две строки тоже вынесу в отдельный класс-хелпер в следующем задании
        driver.navigate().to(LITECART_ADMIN);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(adminLoginPage.loginButton)));

        adminLoginPage.loginAsAdmin();
        adminMainPage.checkAdminPageIsOpened();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
