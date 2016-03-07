package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.util.SeleniumHelper;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        driver.get("http://localhost:9966/kandoe/#/detailTheme/1/createCard");

        allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-card"));

        element = element.findElement(By.name("ib_text"));
        assertEquals("input",element.getTagName());
        element.sendKeys("card");

        element = driver.findElement(By.name("ib_imageURL"));
        assertEquals("input",element.getTagName());
        String url = "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg";
        SeleniumHelper.fillTextIntoElement(element, url);

        element = driver.findElement(By.name("btn_save"));
        assertEquals(element.getText(),"Opslaan");
        executor.executeScript("arguments[0].click();", element);

        (new WebDriverWait(driver, 15)).until((WebDriver d) -> d.getTitle().equals("Kaart"));

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
