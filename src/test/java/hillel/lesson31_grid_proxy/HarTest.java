package hillel.lesson31_grid_proxy;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.Har;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HarTest {

    private WebDriver driver;
    private BrowserUpProxy proxy;

    @BeforeClass
    public void setUp() {
        // Ініціалізація BrowserUp Proxy
        proxy = new BrowserUpProxyServer();
        // запускається проксі-сервер на випадковому порту
        proxy.start(0);
        proxy.enableHarCaptureTypes(
                CaptureType.REQUEST_CONTENT,
                CaptureType.REQUEST_HEADERS,
                CaptureType.REQUEST_COOKIES,
                CaptureType.RESPONSE_CONTENT,
                CaptureType.RESPONSE_HEADERS,
                CaptureType.RESPONSE_COOKIES);

        // Налаштування WebDriver
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Test
    public void getHarForGoogleSearch() throws InterruptedException, IOException {
        // Запуск запису HAR
        proxy.newHar("google");

        // Відкриття Google і виконання пошуку
        driver.get("https://www.google.com");
        driver.findElement(By.cssSelector("[type='search']")).sendKeys("BrowserUp Proxy" + Keys.ENTER);

        // Чекаємо на завантаження результатів пошуку
        Thread.sleep(3000); // Використовуйте WebDriverWait у реальних тестах

        // Збереження HAR файлу
        Har har = proxy.getHar();
        File harFile = new File("google_search.har");
        har.writeTo(harFile);
    }

    @AfterClass
    public void tearDown() {
        // Закриття WebDriver та Proxy
        if (driver != null) {
            driver.quit();
        }
        if (proxy != null) {
            proxy.stop();
        }
    }
}
