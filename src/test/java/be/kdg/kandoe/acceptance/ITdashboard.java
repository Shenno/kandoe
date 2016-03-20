package be.kdg.kandoe.acceptance;

import be.kdg.kandoe.util.SeleniumHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ITdashboard {

    private static WebDriver driver;

    private static String sessionThemeId = "";
    private static String sessionThemeName = "";

    private static String noSessionThemeId = "";
    private static String noSessionThemeName = "";

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://localhost:9966/kandoe/#/login");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("login"));

        element = element.findElement(By.id("ib_username"));
        String userName = "scott.tiger@live.com";
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
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Organisatie: SessionOrganisation"));

        element = driver.findElement(By.id("btn_addTheme"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Maak thema aan"));

        element = driver.findElement(By.id("ib_name"));
        sessionThemeName = "SessionTheme";
        SeleniumHelper.fillTextIntoElement(element, sessionThemeName);

        element = driver.findElement(By.name("btn_save"));
        SeleniumHelper.clickOnElement(driver, element);

        String basicCardName = "MyCardName";

        for (int i = 0; i < 3; i++) {
            (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: " + sessionThemeName));
            if (sessionThemeId.isEmpty()) {
                element = driver.findElement(By.id("themeId"));
                sessionThemeId = SeleniumHelper.getInnerHtmlOfElement(driver, element);
            }
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

        //TODO: Nog een session aanmaken hier.

        element = driver.findElement(By.id("btn_backToList"));
        assertEquals("button", element.getTagName());
        assertEquals("Terug naar lijst", element.getText());
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 15)).until((WebDriver d) -> d.getTitle().equals("Organisatie: SessionOrganisation"));

        element = driver.findElement(By.id("btn_addTheme"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Maak thema aan"));

        element = driver.findElement(By.id("ib_name"));
        noSessionThemeName = "NoSessionTheme";
        SeleniumHelper.fillTextIntoElement(element, noSessionThemeName);

        element = driver.findElement(By.name("btn_save"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: " + noSessionThemeName));

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("themeId"));
        noSessionThemeId = SeleniumHelper.getInnerHtmlOfElement(driver, element);

        for (int i = 0; i < 2; i++) {
            element = driver.findElement(By.id("a_createSession"));
            SeleniumHelper.clickOnElement(driver, element);

            (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Start een nieuwe Kandoe sessie!"));

            element = driver.findElement(By.id("ib_name"));
            SeleniumHelper.fillTextIntoElement(element, "DashboardSession" + i);

            element = driver.findElement(By.id("ib_amount"));
            element.clear();
            SeleniumHelper.fillTextIntoElement(element, "6");

            element = driver.findElement(By.id("dd_users"));
            SeleniumHelper.selectOptionOnDropdown(element, "clarence.ho@gmail.com");

            element = driver.findElement(By.name("btn_addParticipant"));
            SeleniumHelper.clickOnElement(driver, element);

            SeleniumHelper.allowDomToLoad();

            element = driver.findElement(By.id("dd_themes"));
            assertEquals("select", element.getTagName());
            SeleniumHelper.selectOptionOnDropdown(element, sessionThemeName);

            SeleniumHelper.allowDomToLoad();

            if (i == 1) {
                element = driver.findElement(By.id("cb_cardStatus0"));
                SeleniumHelper.clickOnElement(driver, element);
            }

            element = driver.findElement(By.id("btn_createSession"));
            SeleniumHelper.clickOnElement(driver, element);

            (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Kandoe sessie"));
        }
    }

    @Test
    public void testMostFrequentCardCombinations() {

        driver.get("http://localhost:9966/kandoe/#/theme/" + sessionThemeId + "/dashboard");

        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("app"));
        element = element.findElement(By.tagName("dashboard"));

        element = driver.findElement(By.tagName("apriori"));

        //TODO: Verder uitwerken

        element = driver.findElement(By.id("cardCombination0_id"));
        assertEquals("span", element.getTagName());
        assertEquals(Integer.toString(1), element.getText());

        element = driver.findElement(By.id("card0_0_number"));
        assertEquals(Integer.toString(1), element.getText());

        element = driver.findElement(By.id("card0_1_number"));
        assertEquals(Integer.toString(2), element.getText());

        element = driver.findElement(By.id("cardCombination1_id"));
        assertEquals("span", element.getTagName());
        assertEquals(Integer.toString(2), element.getText());

        element = driver.findElement(By.id("card1_0_number"));
        assertEquals(Integer.toString(1), element.getText());

        element = driver.findElement(By.id("card1_1_number"));
        assertEquals(Integer.toString(2), element.getText());

        element = driver.findElement(By.id("cardCombination2_id"));
        assertEquals("span", element.getTagName());
        assertEquals(Integer.toString(3), element.getText());

        element = driver.findElement(By.id("card2_0_number"));
        assertEquals(Integer.toString(1), element.getText());

        element = driver.findElement(By.id("card2_1_number"));
        assertEquals(Integer.toString(2), element.getText());

        element = driver.findElement(By.id("cardCombination3_id"));
        assertEquals("span", element.getTagName());
        assertEquals(Integer.toString(4), element.getText());

        element = driver.findElement(By.id("card3_0_number"));
        assertEquals(Integer.toString(1), element.getText());

        element = driver.findElement(By.id("card3_1_number"));
        assertEquals(Integer.toString(2), element.getText());

        element = driver.findElement(By.id("cardCombination4_id"));
        assertEquals("span", element.getTagName());
        assertEquals(Integer.toString(5), element.getText());

        element = driver.findElement(By.id("card4_0_number"));
        assertEquals(Integer.toString(1), element.getText());

        element = driver.findElement(By.id("card4_1_number"));
        assertEquals(Integer.toString(2), element.getText());

        element = driver.findElement(By.id("card4_2_number"));
        assertEquals(Integer.toString(3), element.getText());
    }

    @Test
    public void testNoSessions() {
        SeleniumHelper.allowDomToLoad();
        driver.get("http://localhost:9966/kandoe/#/theme/" + noSessionThemeId);
        SeleniumHelper.allowDomToLoad();

        WebElement element = driver.findElement(By.id("btn_dashboard"));
        SeleniumHelper.clickOnElement(driver, element);

        (new WebDriverWait(driver, 10)).until((WebDriver d) -> d.getTitle().equals("Thema: " + noSessionThemeName + " - Dashboard"));

        SeleniumHelper.allowDomToLoad();

        element = driver.findElement(By.id("no_sessions"));
        assertEquals("The 'no sessions' message must be correct", "Er zijn nog geen sessies gespeeld voor dit thema!", element.getText());
    }

    @AfterClass
    public static void tearDownClass() {
        //driver.close();
    }
}
