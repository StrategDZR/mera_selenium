package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    //в следующем задании вынесу креденщиалы в отдельный файл
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASS = "admin";

    //Locators
    public String usernameInput = "//input[@name=\"username\"]";
    public String passwordInput = "//input[@name=\"password\"]";
    public String loginButton = "//button[@name=\"login\"]";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAsAdmin() {
        login(ADMIN_LOGIN, ADMIN_PASS);
    }

    public void login(String username, String password) {
        driver.findElement(By.xpath(usernameInput)).sendKeys(username);
        driver.findElement(By.xpath(passwordInput)).sendKeys(password);
        driver.findElement(By.xpath(loginButton)).click();
    }
}