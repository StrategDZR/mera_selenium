package cases.browsers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IeBrowserTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASIC_URL = "https://google.com";

    @Before
    public void setUp() {
        driver = new InternetExplorerDriver(new InternetExplorerOptions().ignoreZoomSettings());
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
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