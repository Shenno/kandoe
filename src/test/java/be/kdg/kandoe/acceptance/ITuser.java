package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.util.SeleniumHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

//test sfsdf
public class ITuser {
    private static WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() {

        driver.get("http://localhost:9966/kandoe/#/login");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("login"));

        element = element.findElement(By.id("ib_username"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));

        SeleniumHelper.fillTextIntoElement(element, "clarence.ho@gmail.com");

        element = driver.findElement(By.id("ib_password"));
        assertEquals("input", element.getTagName());
        assertEquals("password", element.getAttribute("type"));

        SeleniumHelper.fillTextIntoElement(element, "scott");

        element = driver.findElement(By.name("btn_login"));
        assertEquals("button", element.getTagName());
        assertEquals("Get lucky", element.getText());

        SeleniumHelper.clickOnElement(driver, element);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
