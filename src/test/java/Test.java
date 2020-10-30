import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test {
    private WebDriver driver;
    private final String BASIC_URL = "https://google.com";


    @Before
    public void tierUp(){
        driver = new ChromeDriver( new ChromeOptions().addArguments("--incognito"));
    }

    @org.junit.Test
    public void checkPageTitle(){
        driver.get(BASIC_URL);
        String title = driver.getTitle();
        Assert.assertEquals("Google", title);
    }

    @After
    public void tierDown(){
        driver.close();
        driver.quit();
    }

}
