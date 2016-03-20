package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.util.SeleniumHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ITCard {

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
    public void testAddCard(){
        String kaartnaam = "kaartje3";
        SeleniumHelper.allowDomToLoad();

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        driver.get("http://localhost:9966/kandoe/#/theme/1/createCard");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-card"));

        element = element.findElement(By.name("ib_text"));
        assertEquals("input",element.getTagName());
        SeleniumHelper.fillTextIntoElement(element,kaartnaam);

        element = driver.findElement(By.name("ib_imageURL"));
        assertEquals("input",element.getTagName());
        String url = "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg";
        SeleniumHelper.fillTextIntoElement(element, url);

        element = driver.findElement(By.name("btn_save"));
        assertEquals(element.getText(),"Opslaan");
        executor.executeScript("arguments[0].click();", element);

        (new WebDriverWait(driver, 15)).until((WebDriver d) -> d.getTitle().equals("Kaart: "+kaartnaam));

        element = driver.findElement(By.id("span_cardName"));
        assertEquals("The cardText must be correct", kaartnaam, element.getText());
        element = driver.findElement(By.id("img_imageUrl"));
        String src = element.getAttribute("src");
        assertEquals("The imageURL must be correct",url, src);

    }

    @Test
    public void testAddCardWithoutUrl(){
        String kaartnaam = "kaartje4";
        SeleniumHelper.allowDomToLoad();

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        driver.get("http://localhost:9966/kandoe/#/theme/1/createCard");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-card"));

        element = element.findElement(By.name("ib_text"));
        assertEquals("input",element.getTagName());
        SeleniumHelper.fillTextIntoElement(element,kaartnaam);

        element = driver.findElement(By.name("btn_save"));
        assertEquals(element.getText(),"Opslaan");
        executor.executeScript("arguments[0].click();", element);

        (new WebDriverWait(driver, 15)).until((WebDriver d) -> d.getTitle().equals("Kaart: "+kaartnaam));

        element = driver.findElement(By.id("span_cardName"));
        assertEquals("The cardText must be correct", kaartnaam, element.getText());
        element = driver.findElement(By.id("img_imageUrl"));
        String src = element.getAttribute("src");
        assertEquals("The imageURL must be correct", "http://1.bp.blogspot.com/-TTxz7Nt7es0/Uxf7CoQJRUI/AAAAAAAAHg4/3XrVdDOIxIE/s1600/dummy.gif", src);

    }
    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
