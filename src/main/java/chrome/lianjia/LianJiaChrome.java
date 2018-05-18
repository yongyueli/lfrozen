package chrome.lianjia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LianJiaChrome {


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.taobao.com");

        WebElement search_input = ((ChromeDriver) driver).findElementByCssSelector(".search-combobox-input-wrap input");
        search_input.sendKeys("美的");

        WebElement search_button = ((ChromeDriver) driver).findElementByCssSelector("#J_TSearchForm > div.search-button");
        search_button.click();
        driver.close();
    }
}
