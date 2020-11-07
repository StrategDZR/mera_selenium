package cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DifferentBrowsersTest {
    private WebDriver driver;
    private final String BASIC_URL = "https://google.com";

    @Before
    public void tierUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--incognito"));

//        driver = new InternetExplorerDriver(new InternetExplorerOptions().ignoreZoomSettings());

//        driver = new FirefoxDriver();

//        driver = new FirefoxDriver(new FirefoxOptions().setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe"));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @org.junit.Test
    public void checkPageTitle() {
        driver.get(BASIC_URL);
        String title = driver.getTitle();
        Assert.assertEquals("Google", title);
    }

    @After
    public void tierDown() {
        driver.quit();
    }

}