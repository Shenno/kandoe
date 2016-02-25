package be.kdg.kandoe.integration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/kandoe/#organisations");
        //TODO Login stuff
        WebElement element = driver.findElement(By.name("ib_new_organisation"));
        element.sendKeys(organisationName);
        element = driver.findElement(By.name("btn_add_organisation"));
        element.submit();
        (new WebDriverWait(driver, 5)).until((WebDriver d) -> d.findElement(By.id(organisationName)).equals("Organisation IT"));
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisation IT"));
        element = driver.findElement(By.id("tag1"));
        assertEquals("Tag content must be correct", "tag1", element.getText());

        element = driver.findElement(By.name("btn_save"));
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Theme: themename"));
        element = driver.findElement(By.id("tag1"));
        assertEquals("Tag content must be correct", "tag1", element.getText());
    }

}
