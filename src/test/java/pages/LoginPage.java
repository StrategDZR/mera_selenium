package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static config.app.ADMIN_LOGIN;
import static config.app.ADMIN_PASS;

public class LoginPage {
    private WebDriver driver;

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