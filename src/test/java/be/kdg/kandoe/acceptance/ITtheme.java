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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author: Evelien
 * @versionon 1.0 23-2-201614:20
 */
public class ITtheme {

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

        SeleniumHelper.fillTextIntoElement(element, "scott.tiger@live.com");

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

        SeleniumHelper.allowDomToLoad(); //allow time for login to complete

        driver.get("http://localhost:9966/kandoe/#/organisation/1/createTheme");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-theme"));

        element = element.findElement(By.name("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "my themename");

        element = driver.findElement(By.name("ib_description"));
        assertEquals("textarea", element.getTagName());
        SeleniumHelper.fillTextIntoElement(element, "my description");

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("tags"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.name("ib_newTag"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "mytag0");

        element = driver.findElement(By.name("btn_addTag"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg tag toe", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("tag0"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag0", element.getText());

        element = driver.findElement(By.id("delete_tag0"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_tag"));
        assertEquals("span", element.getTagName());
        assertEquals("Tag error message must be filled in", "Tag mag niet leeg zijn", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        SeleniumHelper.fillTextIntoElement(element, "mytag1");

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("tag0"));
        assertEquals("Content of tag must be correct", "mytag1", element.getText());

        element = driver.findElement(By.id("err_tag"));
        assertEquals("Tag error message must be empty", "", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        SeleniumHelper.fillTextIntoElement(element, "mytag1");

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_tag"));
        assertEquals("Tag error message must be filled in", "Tag \"mytag1\" bestaat al", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        SeleniumHelper.fillTextIntoElement(element, "mytag2 mytag3");

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_tag"));
        assertEquals("Tag error message must be empty", "", element.getText());

        element = driver.findElement(By.id("tag1"));
        assertEquals("Content of tag must be correct", "mytag2", element.getText());

        element = driver.findElement(By.id("tag2"));
        assertEquals("Content of tag must be correct", "mytag3", element.getText());

        element = driver.findElement(By.id("organisators"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("dd_users"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "clarence.ho@gmail.com");

        element = driver.findElement(By.name("btn_addOrganisator"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg organisator toe", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("organisator0"));
        assertEquals("p", element.getTagName());
        assertEquals("The right organisator must be shown", "clarence.ho@gmail.com", element.getText());

        element = driver.findElement(By.id("delete_organisator0"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_organisator"));
        assertEquals("span", element.getTagName());
        assertEquals("Organisator error message must be empty", "", element.getText());

        element = driver.findElement(By.name("btn_addOrganisator"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_organisator"));
        assertEquals("span", element.getTagName());
        assertEquals("Organisator error message must be filled in", "Gekozen organisator mag niet leeg zijn", element.getText());

        SeleniumHelper.allowDomToLoad(); //otherwise Selenium won't select the right element on dropdown below

        element = driver.findElement(By.id("dd_users"));
        SeleniumHelper.selectOptionOnDropdown(element, "john.smith@live.com");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.name("btn_addOrganisator"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("organisator0"));
        assertEquals("The right organisator must be shown", "john.smith@live.com", element.getText());

        SeleniumHelper.allowDomToLoad(); //otherwise Selenium won't select the right element on dropdown below

        element = driver.findElement(By.id("dd_users"));
        SeleniumHelper.selectOptionOnDropdown(element, "john.smith@live.com");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.name("btn_addOrganisator"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_organisator"));
        assertEquals("span", element.getTagName());
        assertEquals("Organisator error message must be filled in", "Gebruiker \"john.smith@live.com\" is reeds toegevoegd als organisator", element.getText());

        element = driver.findElement(By.name("btn_save"));
        assertEquals("button", element.getTagName());
        assertEquals("Opslaan", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: my themename"));

        element = driver.findElement(By.id("span_themename"));
        assertEquals("span", element.getTagName());
        assertEquals("The themename must be correct", "my themename", element.getText());

        element = driver.findElement(By.id("p_description"));
        assertEquals("p", element.getTagName());
        assertEquals("The description must be correct", "my description", element.getText());

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

        element = driver.findElement(By.id("collapse_tags"));
        SeleniumHelper.clickOnElement(driver, element);
        SeleniumHelper.allowDomToLoad(); //allow time to expand panel

        element = driver.findElement(By.id("tag0"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag1", element.getText());

        element = driver.findElement(By.id("tag1"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag2", element.getText());

        element = driver.findElement(By.id("tag2"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag3", element.getText());

        element = driver.findElement(By.id("organisators"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("collapse_organisators"));
        SeleniumHelper.clickOnElement(driver, element);
        SeleniumHelper.allowDomToLoad(); //allow time to expand panel

        element = driver.findElement(By.id("organisator0"));
        assertEquals("p", element.getTagName());
        assertEquals("The first organisator must be correct", "scott.tiger@live.com", element.getText());

        element = driver.findElement(By.id("organisator1"));
        assertEquals("p", element.getTagName());
        assertEquals("The second organisator must be correct", "john.smith@live.com", element.getText());
    }

    @Test
    public void testCancelAddingTheme() {
        SeleniumHelper.allowDomToLoad(); //allow time for login to complete

        driver.get("http://localhost:9966/kandoe/#/organisation/1/createTheme");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("create-theme"));

        element = element.findElement(By.name("btn_cancel"));
        assertEquals("button", element.getTagName());
        assertEquals("Annuleren", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisatie: Orga1"));

    }

    @Test
    public void testEditTheme() {

        SeleniumHelper.allowDomToLoad(); //allow time for login to complete

        driver.get("http://localhost:9966/kandoe/#/theme/1");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("detail-theme"));

        element = driver.findElement(By.id("span_themename"));
        String themeName = element.getText();

        element = driver.findElement(By.id("p_description"));
        String description = element.getText();

        element = driver.findElement(By.id("btn_edit"));
        assertEquals("button", element.getTagName());
        assertEquals("Wijzigen", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Wijzig thema"));

        element = driver.findElement(By.id("ib_themeName"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));

        assertEquals("The name must be correct", themeName, element.getAttribute("value"));

        element.clear();
        SeleniumHelper.fillTextIntoElement(element, "New themename");

        element = driver.findElement(By.id("ib_description"));
        assertEquals("textarea", element.getTagName());
        assertEquals("The description must be correct", description, element.getAttribute("value"));

        element.clear();
        SeleniumHelper.fillTextIntoElement(element, "New description");

        element = driver.findElement(By.name("cb_commentaryAllowed"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.name("cb_addingAdmitted"));
        assertEquals("input", element.getTagName());
        assertEquals("checkbox", element.getAttribute("type"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("tags"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.name("ib_newTag"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "mytag0");

        element = driver.findElement(By.name("btn_addTag"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg tag toe", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("tag0"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag0", element.getText());

        element = driver.findElement(By.id("delete_tag0"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_tag"));
        assertEquals("span", element.getTagName());
        assertEquals("Tag error message must be filled in", "Tag cannot be empty", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        SeleniumHelper.fillTextIntoElement(element, "mytag1");

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("tag0"));
        assertEquals("Content of tag must be correct", "mytag1", element.getText());

        element = driver.findElement(By.id("err_tag"));
        assertEquals("Tag error message must be empty", "", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        SeleniumHelper.fillTextIntoElement(element, "mytag1");

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_tag"));
        assertEquals("Tag error message must be filled in", "Tag \"mytag1\" already exists", element.getText());

        element = driver.findElement(By.name("ib_newTag"));
        SeleniumHelper.fillTextIntoElement(element, "mytag2 mytag3");

        element = driver.findElement(By.name("btn_addTag"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("err_tag"));
        assertEquals("Tag error message must be empty", "", element.getText());

        element = driver.findElement(By.id("tag1"));
        assertEquals("Content of tag must be correct", "mytag2", element.getText());

        element = driver.findElement(By.id("tag2"));
        assertEquals("Content of tag must be correct", "mytag3", element.getText());

        element = driver.findElement(By.id("organisators"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("dd_users"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "clarence.ho@gmail.com");

        element = driver.findElement(By.name("btn_addOrganisator"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg organisator toe", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("organisator0"));
        assertEquals("p", element.getTagName());
        assertEquals("The right organisator must be shown", "clarence.ho@gmail.com", element.getText());

        element = driver.findElement(By.id("delete_organisator0"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad(); //otherwise Selenium won't select the right element on dropdown below

        element = driver.findElement(By.id("dd_users"));
        SeleniumHelper.selectOptionOnDropdown(element, "john.smith@live.com");

        element = driver.findElement(By.name("btn_addOrganisator"));
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("organisator0"));
        assertEquals("The right organisator must be shown", "john.smith@live.com", element.getText());

        element = driver.findElement(By.name("btn_save"));
        assertEquals("button", element.getTagName());
        assertEquals("Opslaan", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: New themename"));

        element = driver.findElement(By.id("span_themename"));
        assertEquals("span", element.getTagName());
        assertEquals("The themename must be correct", "New themename", element.getText());

        element = driver.findElement(By.id("p_description"));
        assertEquals("p", element.getTagName());
        assertEquals("The description must be correct", "New description", element.getText());

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

        element = driver.findElement(By.id("collapse_tags"));
        SeleniumHelper.clickOnElement(driver, element);
        SeleniumHelper.allowDomToLoad(); //allow time to expand panel

        element = driver.findElement(By.id("tag0"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag1", element.getText());

        element = driver.findElement(By.id("tag1"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag2", element.getText());

        element = driver.findElement(By.id("tag2"));
        assertEquals("p", element.getTagName());
        assertEquals("Content of tag must be correct", "mytag3", element.getText());

        element = driver.findElement(By.id("organisators"));
        assertEquals("div", element.getTagName());

        element = driver.findElement(By.id("collapse_organisators"));
        SeleniumHelper.clickOnElement(driver, element);
        SeleniumHelper.allowDomToLoad(); //allow time to expand panel

        element = driver.findElement(By.id("organisator0"));
        assertEquals("p", element.getTagName());
        assertEquals("The first organisator must be correct", "scott.tiger@live.com", element.getText());

        element = driver.findElement(By.id("organisator1"));
        assertEquals("p", element.getTagName());
        assertEquals("The second organisator must be correct", "john.smith@live.com", element.getText());
    }

    @Test
    public void testCancelEditingTheme() {
        SeleniumHelper.allowDomToLoad(); //allow time for login to complete

        driver.get("http://localhost:9966/kandoe/#/theme/1");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("detail-theme"));

        element = driver.findElement(By.id("span_themename"));
        String themeName = element.getText();

        element = driver.findElement(By.id("btn_edit"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Wijzig thema"));

        element = driver.findElement(By.name("btn_cancel"));
        assertEquals("button", element.getTagName());
        assertEquals("Annuleren", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: " + themeName));
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
