package cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Task1Test {
    private WebDriver driver;
    private final String BASIC_URL = "https://google.com";

    @Before
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));
    }

    @Test
    public void checkPageTitle() {
        driver.get(BASIC_URL);
        String title = driver.getTitle();
        Assert.assertEquals("Google", title);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
