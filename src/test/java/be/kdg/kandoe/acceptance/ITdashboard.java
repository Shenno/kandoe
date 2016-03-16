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

/**
 * @author Devi Bral
 * @version 1.0 12/03/2016 18:04
 */
public class ITdashboard {

    private static WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testGetMostFrequentCardCombinations() {

        //TODO: Kan pas getest worden als er eerst een thema met sessions en kaartjes wordt aangemaakt. Eerst acceptatietest van session dus!
       /* driver.get("http://localhost:9966/kandoe/#/theme/1/dashboard");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("dashboard"));

        element = driver.findElement(By.tagName("apriori")); */

        driver.get("http://localhost:9966/kandoe/#/theme/1");
        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("btn_dashboard"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: Drankjes - Dashboard"));

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("no_sessions"));
        assertEquals("The 'no sessions' message must be correct", "Er zijn nog geen sessies gespeeld voor dit thema!", element.getText());
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
