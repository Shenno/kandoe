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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201614:20
 */
public class ITtheme {

    WebDriver driver;

    public ITtheme() {
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
    public void testAddTheme() {

        SeleniumHelper.allowDomToLoad();

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        driver.get("http://localhost:9966/kandoe/#/organisation/1/createTheme");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-theme"));

        element = element.findElement(By.name("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        element.sendKeys("themename");

        element = driver.findElement(By.name("ib_description"));
        assertEquals("textarea", element.getTagName());
        element.sendKeys("d");

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        element.click();

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        element.click();

        // Select dropdown = new Select(driver.findElement(By.name("dd_organisation")));
        //dropdown.selectByVisibleText("organisatie");

        element = driver.findElement(By.id("tags"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.name("ib_newTag"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        element.sendKeys("t0");

        element = driver.findElement(By.name("btn_addTag"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg tag toe", element.getText());
        executor.executeScript("arguments[0].click();", element);

        element = driver.findElement(By.id("tag0"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "t0", element.getText());

        element = driver.findElement(By.id("delete_tag0"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        executor.executeScript("arguments[0].click();", element);

        element = driver.findElement(By.name("ib_newTag"));
        element.sendKeys("t1");

        element = driver.findElement(By.name("btn_addTag"));
        executor.executeScript("arguments[0].click();", element);

        element = driver.findElement(By.id("tag0"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "t1", element.getText());

        element = driver.findElement(By.name("btn_save"));
        assertEquals("button", element.getTagName());
        assertEquals("Opslaan", element.getText());
        executor.executeScript("arguments[0].click();", element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: themename"));

        element = driver.findElement(By.id("span_themename"));
        assertEquals("span", element.getTagName());
        assertEquals("The themename must be correct", "themename", element.getText());

        element = driver.findElement(By.id("p_description"));
        assertEquals("p", element.getTagName());
        assertEquals("The description must be correct", "d", element.getText());

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        assertEquals("true", element.getAttribute("disabled"));
        assertEquals("true", element.getAttribute("checked"));

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        assertEquals("true", element.getAttribute("disabled"));
        assertEquals("true", element.getAttribute("checked"));

        element = driver.findElement(By.id("tags"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("tag0"));
        assertEquals("span", element.getTagName());
        assertEquals("Content of tag must be correct", "tag1Updated", element.getText());
    }

    @Test
    public void testEditTheme() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:9966/kandoe/#/detailTheme/1");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("detail-theme"));

        element = driver.findElement(By.id("span_themename"));
        String themeName = element.getText();

        element = driver.findElement(By.id("p_description"));
        String description = element.getText();

        element = driver.findElement(By.id("tag1"));
        String tag1 = element.getText();

        element = driver.findElement(By.id("btn_edit"));
        assertEquals("button", element.getTagName());
        assertEquals("Wijzigen", element.getText());
        element.submit();

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Wijzig thema"));

        element = driver.findElement(By.id("ib_themeName"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));

        assertEquals("The name must be correct", themeName, element.getAttribute("value"));

        element.clear();
        element.sendKeys("Nieuwe themanaam");

        element = driver.findElement(By.id("ib_description"));
        assertEquals("textarea", element.getTagName());
        assertEquals("The description must be correct", description, element.getAttribute("value"));

        element.clear();
        element.sendKeys("b");

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        element.click();

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        element.click();

        element = driver.findElement(By.id("tags"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("tag1"));
        assertEquals("span", element.getTagName());
        assertEquals("Content of tag must be correct", tag1, element.getText());

        element = driver.findElement(By.id("delete_tag0"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        element.click();

        element = driver.findElement(By.name("ib_newTag"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        element.sendKeys("tag1Updated");

        element = driver.findElement(By.name("btn_addTag"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg tag toe", element.getText());
        element.click();

        element = driver.findElement(By.id("tag1"));
        assertEquals("span", element.getTagName());
        assertEquals("Content of tag must be correct", "tag1Updated", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        element.sendKeys("tag2");

        element = driver.findElement(By.name("btn_addTag"));
        element.click();

        element = driver.findElement(By.id("tag2"));
        assertEquals("span", element.getTagName());
        assertEquals("Content of tag must be correct", "tag2", element.getText());

        element = driver.findElement(By.name("btn_save"));
        assertEquals("button", element.getTagName());
        assertEquals("Opslaan", element.getText());
        element.submit();

        (new WebDriverWait(driver, 15)).until((WebDriver d) -> d.getTitle().equals("Thema: Nieuwe themanaam"));

        element = driver.findElement(By.id("span_themename"));
        assertEquals("span", element.getTagName());
        assertEquals("The themename must be correct", "Nieuwe themanaam", element.getText());

        element = driver.findElement(By.id("p_description"));
        assertEquals("p", element.getTagName());
        assertEquals("The description must be correct", "b", element.getText());

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        assertEquals("true", element.getAttribute("disabled"));
        assertEquals("true", element.getAttribute("checked"));

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        assertEquals("true", element.getAttribute("disabled"));
        assertEquals("true", element.getAttribute("checked"));

        element = driver.findElement(By.id("tags"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("tag1"));
        assertEquals("span", element.getTagName());
        assertEquals("Content of tag must be correct", tag1, element.getText());

        element = driver.findElement(By.id("tag2"));
        assertEquals("span", element.getTagName());
        assertEquals("Content of tag must be correct", "tag2", element.getText());
    }
}
