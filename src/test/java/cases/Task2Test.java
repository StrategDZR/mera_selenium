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
import pages.LoginPage;

import java.util.concurrent.TimeUnit;
import static config.app.*;

public class Task2Test {
    private WebDriver driver;
    private WebDriverWait wait;

    LoginPage loginPage;
    AdminMainPage adminMainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        loginPage = new LoginPage(driver);
        adminMainPage = new AdminMainPage(driver);
    }

    @Test
    public void testLoginToAdmin() {
        //следующие две строки тоже вынесу в отдельный класс-хелпер в следующем задании
        driver.navigate().to(BASIC_URL);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));

        loginPage.loginAsAdmin();
        adminMainPage.checkAdminPageIsOpened();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
