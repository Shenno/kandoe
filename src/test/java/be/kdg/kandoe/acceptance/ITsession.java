package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.util.SeleniumHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;


public class ITsession {

    private static WebDriver driver;
    private static String userName;

    public void login(ChromeDriver driver, String userName, String password) {
        driver.get("http://localhost:9966/kandoe/#/login");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("login"));

        element = element.findElement(By.id("ib_username"));
        SeleniumHelper.fillTextIntoElement(element, userName);

        element = driver.findElement(By.id("ib_password"));
        SeleniumHelper.fillTextIntoElement(element, password);

        element = driver.findElement(By.name("btn_login"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Kandoe"));
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://localhost:9966/kandoe/#/login");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("login"));

        element = element.findElement(By.id("ib_username"));
        userName = "scott.tiger@live.com";
        SeleniumHelper.fillTextIntoElement(element, userName);

        element = driver.findElement(By.id("ib_password"));
        SeleniumHelper.fillTextIntoElement(element, "scott");

        element = driver.findElement(By.name("btn_login"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Kandoe"));

        element = driver.findElement(By.id("a_organisations"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisaties"));

        element = driver.findElement(By.id("btn_add_Organisation"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Maak organisatie aan"));

        element = driver.findElement(By.name("ib_new_organisation"));
        SeleniumHelper.fillTextIntoElement(element, "SessionOrganisation");

        element = driver.findElement(By.name("btn_add_organisation"));
        SeleniumHelper.clickOnElement(driver,element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisatie: SessionOrganisationTest"));

        element = driver.findElement(By.id("btn_addTheme"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Maak thema aan"));

        element = driver.findElement(By.id("ib_name"));
        SeleniumHelper.fillTextIntoElement(element, "SessionTheme");

        element = driver.findElement(By.name("btn_save"));
        SeleniumHelper.clickOnElement(driver, element);

        String basicCardName = "MyCardName";

        for(int i = 0; i < 3; i++){
            (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: SessionTheme"));

            element = driver.findElement(By.id("btn_addCard"));
            SeleniumHelper.clickOnElement(driver, element);

            (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Maak kaartje aan"));

            String newCardName = basicCardName + i;
            element = driver.findElement(By.name("ib_text"));
            assertEquals("input", element.getTagName());
            SeleniumHelper.fillTextIntoElement(element, newCardName);

            element = driver.findElement(By.name("ib_imageURL"));
            assertEquals("input", element.getTagName());
            String url = "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg";
            SeleniumHelper.fillTextIntoElement(element, url);

            element = driver.findElement(By.name("btn_save"));
            assertEquals(element.getText(), "Opslaan");
            SeleniumHelper.clickOnElement(driver, element);

            (new WebDriverWait(driver, 15)).until((WebDriver d) -> d.getTitle().equals("Kaart: " + newCardName));

            element = driver.findElement(By.id("btn_backToTheme"));
            SeleniumHelper.clickOnElement(driver, element);
        }
    }

    public String addSession() {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        assertEquals("a", element.getTagName());
        assertEquals("Maak een cirkelsessie", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("ib_amount"));
        assertEquals("input", element.getTagName());
        assertEquals("number", element.getAttribute("type"));
        element.clear();
        SeleniumHelper.fillTextIntoElement(element, "6");

        element = driver.findElement(By.id("participant0"));
        assertEquals("li", element.getTagName());
        assertEquals("Participant must be correct", userName, element.getText());

        element = driver.findElement(By.id("dd_users"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "clarence.ho@gmail.com");

        element = driver.findElement(By.name("btn_addParticipant"));
        assertEquals("button", element.getTagName());
        assertEquals("Voeg deelnemer toe", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("participant1"));
        assertEquals("li", element.getTagName());
        assertEquals("Participant must be correct", "clarence.ho@gmail.com", element.getText());

        element = driver.findElement(By.id("delete_participant1"));
        assertEquals("span", element.getTagName());
        assertEquals("glyphicon glyphicon-remove", element.getAttribute("class"));
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("dd_users"));
        SeleniumHelper.selectOptionOnDropdown(element, "john.smith@live.com");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.name("btn_addParticipant"));
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("participant1"));
        assertEquals("Participant must be correct", "john.smith@live.com", element.getText());

        element = driver.findElement(By.id("dd_themes"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        for (int i = 0; i < 3; i++) {
            element = driver.findElement(By.id("cb_cardStatus" + i));
            assertEquals("input", element.getTagName());
            assertEquals("checkbox", element.getAttribute("type"));
            assertEquals("true", element.getAttribute("checked"));
        }

        SeleniumHelper.clickOnElement(driver, element);

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Kandoe sessie"));

        String currentUrl = driver.getCurrentUrl();

        for (int i = 0; i < 2; i++) {
            element = driver.findElement(By.id("card" + i + "_number"));
            assertEquals("span", element.getTagName());
            assertEquals(Integer.toString(i+1), element.getText());

            element = driver.findElement(By.id("card" + i + "_image"));
            assertEquals("The image must be correct", "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg", element.getAttribute("src"));

            element = driver.findElement(By.id("card" + i + "_name"));
            assertEquals("span", element.getTagName());
            assertEquals("MyCardName" + i, element.getText());

            element = driver.findElement(By.id("card" + i));
            assertEquals("div", element.getTagName());

            element = driver.findElement(By.id("card" + i + "_numberOnCircle"));
            assertEquals("span", element.getTagName());
            assertEquals(Integer.toString(i+1), element.getText());

            element = driver.findElement(By.id("card" + i + "_text"));
            assertEquals("span", element.getTagName());
            assertEquals("MyCardName" + i, SeleniumHelper.getInnerHtmlOfElement(driver, element));
        }
        return currentUrl;
    }

    @Test
    public void testAddSessionAndPlay() {
        String url = addSession();
        ChromeDriver scottDriver = new ChromeDriver();
        ChromeDriver johnDriver = new ChromeDriver();
        login(scottDriver, "scott.tiger@live.com", "scott");
        login(johnDriver, "john.smith@live.com", "john");

        scottDriver.get(url);
        johnDriver.get(url);
        SeleniumHelper.allowDomToLoad();

        ChromeDriver currentDriver = null;
        for(int i = 0; i < 7; i++) {
            if(i%2 == 0) {
                currentDriver = scottDriver;
            } else {
                currentDriver = johnDriver;
            }
            WebElement element = currentDriver.findElement(By.id("card" + 0));
            SeleniumHelper.clickOnElement(currentDriver, element);
            SeleniumHelper.allowResourcesToGetPolled();
        }

        WebElement element = currentDriver.findElement(By.id("game_over"));
        assertEquals("h2", element.getTagName());
        scottDriver.close();
        johnDriver.close();
    }


    @Test
    public void testAddEmptySessionName () {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("dd_themes"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "De sessie moet een naam hebben.", element.getText());
    }

    @Test
    public void testNotEnoughRings () {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("ib_amount"));
        element.clear();
        SeleniumHelper.fillTextIntoElement(element, "1");

        element = driver.findElement(By.id("dd_themes"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.", element.getText());
    }

    @Test
    public void testTooManyRings () {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("ib_amount"));
        element.clear();
        SeleniumHelper.fillTextIntoElement(element, "10");

        element = driver.findElement(By.id("dd_themes"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "Een Kandoecirkel moet minimum 3 en maximum 8 schillen hebben.", element.getText());
    }

    @Test
    public void testNoParticipants() {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("delete_participant0"));
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_noParticipants"));
        assertEquals("p", element.getTagName());
        assertEquals("The content of the error must be correct", "Momenteel nog niemand! Voeg snel iemand toe, alleen spelen is niet leuk!", element.getText());

        element = driver.findElement(By.id("dd_themes"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "Een sessie moet minimaal 2 deelnemers hebben.", element.getText());

    }

    @Test
    public void testOneParticipant() {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        assertEquals("input", element.getTagName());
        assertEquals("text", element.getAttribute("type"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("dd_themes"));
        assertEquals("select", element.getTagName());
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "Een sessie moet minimaal 2 deelnemers hebben.", element.getText());

    }

    @Test
    public void testNoCards() {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("dd_themes"));
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        for (int i = 0; i < 3; i++) {
            element = driver.findElement(By.id("cb_cardStatus" + i));
            SeleniumHelper.clickOnElement(driver, element);
        }

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "Een sessie moet minimum 2 en maximum 24 kaarten bevatten.", element.getText());

    }

    @Test
    public void testOneCard() {
        driver.get("http://localhost:9966/kandoe/#/");

        WebElement element = driver.findElement(By.id("a_createSession"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

        element = driver.findElement(By.id("ib_name"));
        SeleniumHelper.fillTextIntoElement(element, "MySession");

        element = driver.findElement(By.id("dd_themes"));
        SeleniumHelper.selectOptionOnDropdown(element, "SessionTheme");

        SeleniumHelper.allowDomToLoad();

        for (int i = 0; i < 2; i++) {
            element = driver.findElement(By.id("cb_cardStatus" + i));
            SeleniumHelper.clickOnElement(driver, element);
        }

        element = driver.findElement(By.id("btn_createSession"));
        assertEquals("button", element.getTagName());
        assertEquals("Sessie aanmaken rond het thema SessionTheme", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("err_session"));
        assertEquals("div", element.getTagName());
        assertEquals("The content of the error must be correct", "Een sessie moet minimum 2 en maximum 24 kaarten bevatten.", element.getText());

    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }
}
