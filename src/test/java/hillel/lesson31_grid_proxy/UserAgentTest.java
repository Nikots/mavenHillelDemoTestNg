package hillel.lesson31_grid_proxy;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserAgentTest {

    private WebDriver driver;
    private BrowserUpProxy proxy;

    @BeforeClass
    public void setUp() {
        // Ініціалізація BrowserUp Proxy
        proxy = new BrowserUpProxyServer();
        proxy.start(0);

        // Додавання фільтра для зміни User-Agent
        proxy.addRequestFilter((request, contents, messageInfo) -> {
            request.headers().remove("User-Agent");
            request.headers().add("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
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
    public void openMobileFacebook() throws InterruptedException {
        driver.get("https://facebook.com");
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
