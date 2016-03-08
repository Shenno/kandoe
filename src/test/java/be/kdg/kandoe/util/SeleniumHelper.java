package be.kdg.kandoe.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Devi Bral
 * @version 1.0 7/03/2016 14:05
 */
public class SeleniumHelper {

    public static void allowDomToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fillTextIntoElement(WebElement element, String text) {
        String[] splitText = text.split("");
        for (String s : splitText){
            element.sendKeys(s);
        }
    }

    public static void clickOnElement(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
