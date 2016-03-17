package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.util.SeleniumHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Devi Bral
 * @version 1.0 17/03/2016 22:01
 */
public class ITwrongPath {
    private static WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testWrongPathAfterHashtag() {

        driver.get("http://localhost:9966/kandoe/#/home");
        SeleniumHelper.allowDomToLoad();
        String orginalUrl = driver.getCurrentUrl();

        System.out.println(orginalUrl);

        driver.get("http://localhost:9966/kandoe/#/wrongUrl");
        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("error"));

        element = driver.findElement(By.id("btn_back"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getCurrentUrl().equals(orginalUrl));
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
