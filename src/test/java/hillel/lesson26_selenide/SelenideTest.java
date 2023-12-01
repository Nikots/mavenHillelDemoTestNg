package hillel.lesson26_selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.partialValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SelenideTest {

    @BeforeTest
    public void setup() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1440x1100";
        Configuration.browserPosition = "50x20";
        Configuration.screenshots = true;
        Configuration.reportsFolder = "foo/bar_reports";
        Configuration.downloadsFolder = "foo/bar_downloads";
        Configuration.savePageSource = false;
      // Configuration.headless = true;
    }

    @Test
    public void loginLogoutTestSelenide() {
        open("/");

        $(By.linkText("Form Authentication")).click();

        $("#username").setValue("tomsmith1");
        $("#password").setValue("SuperSecretPassword!");
        $("button[type='submit']").click();

        String text = $(By.tagName("h2")).getText();

        assertEquals(text, "Secure Area");
    }

    @Test
    public void uploadFileTestSelenide() throws URISyntaxException {
        String filename = "text.txt";
        open("/upload");

        // Отримання URL Ресурсу:
        URL resourceUrl = getClass().getClassLoader().getResource(filename);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Файл не знайдено");
        }
        //Перетворення URL в Об'єкт File:
        File file = new File(resourceUrl.toURI());

        SelenideElement input = $("#file-upload");
        input.uploadFile(file);

        input.shouldHave(partialValue(filename));

        $("#file-submit").click();

        $x("//*[text()='File Uploaded!']").shouldBe(visible);
    }

    @Test
    public void downloadFileTestSelenide() {
        open("https://chromedriver.storage.googleapis.com/index.html?path=114.0.5735.16/");

        String fileName = "chromedriver_mac64.zip";

        File dowloadedFile = $(By.linkText(fileName)).download();

        assertTrue(dowloadedFile.exists());
        assertTrue(dowloadedFile.getName().endsWith(".zip"));
    }

    @Test
    public void testListOfElementsSelenide() {
        open("/add_remove_elements/");

        SelenideElement button = $x("//button[text()='Add Element']");
        button.click();
        button.click();
        button.click();
        button.click();

        $$(".added-manually").shouldHave(size(4));
    }

    @Test
    public void selectFromMenuJSSelenium() {
        open("/dropdown");

        SelenideElement dropdown = $("#dropdown");

        WebElement option = executeJavaScript(
                "return arguments[0].querySelector(\"option[value='2']\");", dropdown);

        executeJavaScript("arguments[0].selected='selected'", option);
    }

    @Test
    public void iframeCheckSelenide() {
        open("/iframe");

        switchTo().frame("mce_0_ifr");
        $("#tinymce p").sendKeys("friday!!!");
        switchTo().defaultContent();

        $(By.linkText("Elemental Selenium")).shouldBe(enabled);
    }

    @Test
    public void getWebDriverFromSelenide() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.findElement(By.tagName(""));
    }
}
