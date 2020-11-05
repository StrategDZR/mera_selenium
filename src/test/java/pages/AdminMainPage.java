package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminMainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Locators
    public String logoutButton = "//a[@title=\"Logout\"]";

    public AdminMainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void checkAdminPageIsOpened(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoutButton)));
        WebElement logoutButtonEl = driver.findElement(By.xpath(logoutButton));
        Assert.assertTrue(logoutButtonEl.isDisplayed());
    }
}
