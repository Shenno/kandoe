package be.kdg.kandoe.integration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201614:20
 */
public class ITtheme {
    @Test
    public void testAddTheme(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
         WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:9966/kandoe/#/createTheme");

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-theme"));

        element = element.findElement(By.name("ib_name"));
        assertEquals("input",element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        element.sendKeys("themename");

        element = driver.findElement(By.name("ib_description"));
        assertEquals("input",element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        element.sendKeys("description");

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input",element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        element.click();

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input",element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        element.click();

        // Select dropdown = new Select(driver.findElement(By.name("dd_organisation")));
        //dropdown.selectByVisibleText("organisatie");

        element = driver.findElement(By.id("tags"));
        assertEquals("div",element.getTagName());

        element = driver.findElement(By.name("ib_newTag"));
        assertEquals("input",element.getTagName());
        assertEquals("text", element.getAttribute("type"));

        element= driver.findElement(By.name("btn_addTag"));
        assertEquals("button",element.getTagName());
        assertEquals("Voeg tag toe", element.getText());

        element= driver.findElement(By.name("btn_save"));
        assertEquals("button",element.getTagName());
        assertEquals("Opslaan", element.getText());
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Theme: themename"));
        element = driver.findElement(By.id("p_themename"));
        assertEquals("p",element.getTagName());
        assertEquals("The themename must be correct", "themename", element.getText());
        element = driver.findElement(By.id("p_description"));
        assertEquals("p",element.getTagName());
        assertEquals("The description must be correct", "description", element.getText());
        element = driver.findElement(By.id("tags"));
        assertEquals("div",element.getTagName());
    }

}
