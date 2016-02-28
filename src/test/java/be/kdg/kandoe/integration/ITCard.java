package be.kdg.kandoe.integration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by Len on 26-2-2016.
 */
public class ITCard {

    @Test
    public void testAddCard(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://127.0.0.1:9966/kandoe/#/createCard");
        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-card"));
        element = element.findElement(By.name("ib_text"));
        assertEquals("input",element.getTagName());
        element.sendKeys("cardText");
        element = driver.findElement(By.name("ib_imageURL"));
        assertEquals("input",element.getTagName());
        element.sendKeys("imageURL");

       /* element= driver.findElement(By.name("btn_save"));
        assertEquals("button",element.getTagName());
        element.submit();*/

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Card: text"));
        element = driver.findElement(By.id("p_cardText"));
        assertEquals("p",element.getTagName());
        assertEquals("The cardText must be correct", "cardText", element.getText());
        element = driver.findElement(By.id("p_imageURL"));
        assertEquals("p",element.getTagName());
        assertEquals("The imageURL must be correct", "imageURL", element.getText());

    }
}
