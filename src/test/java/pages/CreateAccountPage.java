package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Formatter;

public class CreateAccountPage extends AbstractPage {

    //Locators
    private final String firstNameInputLoc = "//input[@name=\"firstname\"]";
    private final String lastNameInputLoc = "//input[@name=\"lastname\"]";
    private final String address1InputLoc = "//input[@name=\"address1\"]";
    private final String postcodeInputLoc = "//input[@name=\"postcode\"]";
    private final String cityInputLoc = "//input[@name=\"city\"]";
    private final String countryInputLoc = "//span[@class=\"select2-selection select2-selection--single\"]";
    private final String countrySearchInputLoc = "//input[@type=\"search\"]";
    private final String countryByNameLoc = "//li[@role=\"treeitem\"][text()=\"%s\"]";
    private final String emailInputLoc = "//input[@name=\"email\"]";
    private final String phoneInputLoc = "//input[@name=\"phone\"]";
    private final String desiredPasswordInputLoc = "//input[@name=\"password\"]";
    private final String confirmPasswordInputLoc = "//input[@name=\"confirmed_password\"]";
    private final String createAccountButtonLoc = "//button[@name=\"create_account\"]";
    private final String warningLoc = "//div[@class=\"notice errors\"]";

    Faker faker = new Faker();
    private final String email;
    private final String password;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(createAccountButtonLoc)));
        email = faker.internet().safeEmailAddress();
        password = faker.internet().password();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void fillAllFieldsWithRandomData() {
        logger.info("Start creating new account with random parameters");
        driver.findElement(By.xpath(firstNameInputLoc)).sendKeys(faker.name().firstName());
        driver.findElement(By.xpath(lastNameInputLoc)).sendKeys(faker.name().lastName());
        driver.findElement(By.xpath(address1InputLoc)).sendKeys(faker.address().fullAddress());
        driver.findElement(By.xpath(postcodeInputLoc)).sendKeys(faker.address().zipCode());
        driver.findElement(By.xpath(cityInputLoc)).sendKeys(faker.address().cityName());
        setCountry("United States");
        driver.findElement(By.xpath(emailInputLoc)).sendKeys(getEmail());
        driver.findElement(By.xpath(phoneInputLoc)).sendKeys("+7" + faker.number().digits(10));
        driver.findElement(By.xpath(desiredPasswordInputLoc)).sendKeys(getPassword());
        driver.findElement(By.xpath(confirmPasswordInputLoc)).sendKeys(getPassword());
        driver.findElement(By.xpath(createAccountButtonLoc)).click();
        // expects warning since Zone can't be selected w/o raising an error
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(warningLoc)));
        driver.findElement(By.xpath(desiredPasswordInputLoc)).sendKeys(getPassword());
        driver.findElement(By.xpath(confirmPasswordInputLoc)).sendKeys(getPassword());
        driver.findElement(By.xpath(createAccountButtonLoc)).click();

        logger.info("User with the following credentials was created: " + email + " " + password);
    }

    private void setCountry(String countryName) {
        Formatter formatter = new Formatter();
        String country = String.valueOf(formatter.format(countryByNameLoc, countryName));

        driver.findElement(By.xpath(countryInputLoc)).click();
        driver.findElement(By.xpath(countrySearchInputLoc)).sendKeys(countryName);
        driver.findElement(By.xpath(country)).click();
    }
}
