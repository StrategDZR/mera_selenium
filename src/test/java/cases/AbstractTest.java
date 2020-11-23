package cases;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

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
        LoginPage loginPage = new LoginPage(driver);
        driver.navigate().to(url);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));
        loginPage.loginAsAdmin();
    }

}
