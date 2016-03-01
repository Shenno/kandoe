package be.kdg.kandoe.integration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by   Shenno Willaert
 * Date         23/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.integration
 */
public class ITorganisation {
    @Test
    public void testAddOrganisation() {
        String organisationName = "KdG Test";

        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:9966/kandoe/#/createOrganisation");

        allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-organisation"));

        //TODO Login stuff

        element = driver.findElement(By.name("ib_new_organisation"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        element.sendKeys(organisationName);

        element = driver.findElement(By.name("btn_add_organisation"));
        assertEquals("button", element.getTagName());
        assertEquals("Organisatie toevoegen", element.getText());
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisatie: "+organisationName));

        element = driver.findElement(By.id("h1_organisationName"));
        assertEquals("h1", element.getTagName());
        assertEquals("The organistation name must be correct", organisationName, element.getText());
    }
    private void allowDomToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
