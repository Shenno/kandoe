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
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by   Shenno Willaert
 * Date         23/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.integration
 */
public class ITorganisation {

    private static WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void setup() {

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
    @Test
    public void testAddOrganisation() {
        String organisationName = "KdG Test 2";
        SeleniumHelper.allowDomToLoad();

        driver.get("http://localhost:9966/kandoe/#/createOrganisation");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-organisation"));

        //TODO Login stuff

        element = driver.findElement(By.name("ib_new_organisation"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element,organisationName);

        element = driver.findElement(By.name("btn_add_organisation"));
        assertEquals("button", element.getTagName());
        assertEquals("Toevoegen", element.getText());
        SeleniumHelper.clickOnElement(driver,element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisatie: "+organisationName));

        element = driver.findElement(By.id("span_organisationname"));
        assertEquals("span", element.getTagName());
        assertEquals("The organistation name must be correct", organisationName, element.getText());
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
