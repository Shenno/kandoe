package be.kdg.kandoe.acceptance;

import org.junit.Before;
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
        driver.get("http://localhost:9966/kandoe/#/detailTheme/1/createCard");

        allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-card"));

        element = element.findElement(By.name("ib_text"));
        assertEquals("input",element.getTagName());
        element.sendKeys("cardText");

        element = driver.findElement(By.name("ib_imageURL"));
        assertEquals("input",element.getTagName());
        element.sendKeys("htt");
        element.sendKeys("p:/");
        element.sendKeys("/my");
        element.sendKeys("ede");
        element.sendKeys("n.be");
        element.sendKeys("/im");
        element.sendKeys("age");
        element.sendKeys("/co");
        element.sendKeys("m_de");
        element.sendKeys("ta");
        element.sendKeys("il");
        element.sendKeys("/201");
        element.sendKeys("309");
        element.sendKeys("10_");
        element.sendKeys("183");
        element.sendKeys("661");
        element.sendKeys("691");
        element.sendKeys("352");
        element.sendKeys("2f0");
        element.sendKeys("609");
        element.sendKeys("c0d2");
        element.sendKeys("f.");
        element.sendKeys("j");
        element.sendKeys("p");
        element.sendKeys("g");

        element = driver.findElement(By.name("btn_save"));
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Kaart: cardText"));

        element = driver.findElement(By.id("span_cardName"));
        assertEquals("The cardText must be correct", "cardText", element.getText());
        element = driver.findElement(By.id("img_imageUrl"));
        String src = element.getAttribute("src");
        assertEquals("The imageURL must be correct", "imageURL", src);

    }

    private void allowDomToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
