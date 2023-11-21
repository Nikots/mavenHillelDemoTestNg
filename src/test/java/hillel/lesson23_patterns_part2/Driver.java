package hillel.lesson23_patterns_part2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriverManager.chromedriver().setup();
            driverThreadLocal.set(new ChromeDriver());
        }
        return driverThreadLocal.get();
    }

    public static void closeBrowser() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }
}