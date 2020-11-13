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
import pages.LoginPage;
import pages.MenuPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static config.app.BASIC_URL;

/*
Задание 7. Сделайте сценарий, проходящий по всем разделам админки
Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
1) входит в панель администратора http://localhost/litecart/admin
2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
3) для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)
 */

public class AdminMenuTest {

    private WebDriver driver;
    private WebDriverWait wait;

    LoginPage loginPage;
    MenuPage menuPage;

    @Before
    public void tierUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
    }

    @Test
    public void testAdminMenu() {
        driver.navigate().to(BASIC_URL);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));

        loginPage.loginAsAdmin();

        List<List<String>> menuItems = menuPage.getMenuItems();
        for (List<String> menuItem : menuItems) {
            menuPage.clickOnMenuItem(menuPage.getMenuLocator(menuItem.get(0), menuItem.get(1)));
            if (!menuItem.get(3).isEmpty()) {
                menuPage.checkMenuElementIsDisplayed("h1", menuItem.get(2));
            }
        }
    }

    @After
    public void tierDown() {
        driver.quit();
        driver = null;
    }
}