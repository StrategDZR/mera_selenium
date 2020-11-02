package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminMainPage;
import pages.LoginPage;

public class TestTask2 {
    private static final String BASIC_URL = "http://localhost/litecart/admin/";
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASS = "admin";
    private WebDriver driver;
    private WebDriverWait wait;

    LoginPage loginPage;
    AdminMainPage adminMainPage;

    @Before
    public void tierUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
        wait = new WebDriverWait(driver, 10);
        loginPage = new LoginPage();
        adminMainPage = new AdminMainPage();
    }

    @Test
    public void testLoginToAdmin(){
        driver.navigate().to(BASIC_URL);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginPage.loginButton)));
        driver.findElement(By.xpath(loginPage.usernameInput)).sendKeys(ADMIN_LOGIN);
        driver.findElement(By.xpath(loginPage.passwordInput)).sendKeys(ADMIN_PASS);
        driver.findElement(By.xpath(loginPage.loginButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(adminMainPage.logoutButton)));
        WebElement logoutButton = driver.findElement(By.xpath(adminMainPage.logoutButton));
        Assert.assertTrue(logoutButton.isDisplayed());
    }

    @After
    public void tierDown(){
        driver.quit();
    }
}
