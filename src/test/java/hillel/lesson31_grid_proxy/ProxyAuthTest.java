package hillel.lesson31_grid_proxy;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ProxyAuthTest {

    private WebDriver driver;
    private BrowserUpProxy proxy;

    @BeforeClass
    public void setUp() {
        // Ініціалізація BrowserUp Proxy
        proxy = new BrowserUpProxyServer();
        proxy.start(0);

        // Додавання заголовку Basic Authentication
        String username = "admin";
        String password = "admin";
        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        proxy.addRequestFilter((request, contents, messageInfo) -> {
            request.headers().add("Authorization", "Basic " + encodedCredentials);
            return null;
        });

        // Налаштування WebDriver
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLoginViaProxy() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/basic_auth");

        String text = driver.findElement(By.className("example")).getText();
        Assert.assertTrue(text.contains("Congratulations! You must have the proper credentials."));
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (proxy != null) {
            proxy.stop();
        }
    }
}