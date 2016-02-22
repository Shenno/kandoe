package be.kdg.kandoe.integration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ITtag {
    @Test
    public void testAddTag() {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://127.0.0.1:9966/kandoe/#editTheme/1");
        WebElement element = driver.findElement(By.name("ib_new_tag"));
        element.sendKeys("tag1");
        element = driver.findElement(By.name("btn_add_tag"));
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.findElement(By.id("tag1")) != null);
        element = driver.findElement(By.id("tag1"));
        assertEquals("Tag content must be correct", "tag1", element.getText());

        element = driver.findElement(By.name("btn_save"));
        element.submit();
        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Theme: themename"));
        element = driver.findElement(By.id("tag1"));
        assertEquals("Tag content must be correct", "tag1", element.getText());
    }
}
