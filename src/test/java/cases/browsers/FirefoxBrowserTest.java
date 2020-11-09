package cases.browsers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FirefoxBrowserTest {
    private WebDriver driver;
    private final String BASIC_URL = "https://google.com";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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