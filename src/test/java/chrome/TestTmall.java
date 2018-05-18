package chrome;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.ThreadUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class TestTmall {


    private static ChromeDriverService service;
    private static WebDriver driver;

    @BeforeClass
    public static void prepareDriver() throws Exception{
        File file = new File("chromedriver");
        service = new ChromeDriverService.Builder().usingDriverExecutable(file)
                .usingAnyFreePort()
                .build();
        service.start();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Nexus 5");
        Map<String, Object> mobileOptions = new HashMap<String, Object>();
        mobileOptions.put("mobileEmulation", mobileEmulation);

        HashMap<String, Object> images = new HashMap<String, Object>();
        images.put("images", 2);
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values", images);
//        mobileOptions.put("prefs",prefs);
        mobileOptions.put("args", Arrays.asList("disable-plugins","disable-images","disable-infobars",
                "disable-gpu", "no-default-browser-check", "ignore-certificate-errors","--proxy-server=localhost:8888"));

        capabilities.setCapability(ChromeOptions.CAPABILITY, mobileOptions);
        driver = new RemoteWebDriver(service.getUrl(),
                capabilities);
    }

    public void openNewWindow(WebElement webElement){
        Actions newwin = new Actions(driver);
        newwin.keyDown(Keys.COMMAND).click(webElement).keyUp(Keys.COMMAND).build().perform();
    }

    @Test
    public void testSearch(){
        String url = "https://list.tmall.com/search_product.htm?cat=50938024";
        driver.get(url);
        ThreadUtil.interval(3000,5000);
        WebElement close = driver.findElement(By.cssSelector("[id$='close']"));
        close.click();
        ThreadUtil.interval(2000,3000);

        List<WebElement> elementList = driver.findElements(By.cssSelector("a.list_item"));
        for(WebElement item : elementList){
            openNewWindow(item);
            ThreadUtil.interval(2000,3000);
        }

//        WebElement search = driver.findElement(By.cssSelector("div.search-input-text"));
//        search.click();
//        ThreadUtil.interval(2000,3000);
//        WebElement mq = driver.findElement(By.cssSelector("input#mq"));
//        mq.sendKeys("电饭煲");
//        ThreadUtil.interval(1000,2000);
//        mq.sendKeys(Keys.ENTER);


        JavascriptExecutor jse = (JavascriptExecutor)driver;
        int i = 10000;
        while(i -- > 0){
            jse.executeScript("window.scrollBy(0,250)", "");
            ThreadUtil.interval(1000);
        }

    }

    @AfterClass
    public static void close(){
        driver.close();
    }

}
