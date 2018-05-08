package chrome;


import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
@RunWith(JUnit4.class)
public class ChromeTest extends TestCase {

    private static ChromeDriverService service;
    private static WebDriver driver;


    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @BeforeClass
    public static void createDriver() throws Exception{
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("chromedriver"))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }
    @BeforeClass
    public static void createAndStartService() throws Exception{

    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("http://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("webdriver");

        assertEquals("webdriver - Google Search", driver.getTitle());
    }
}
