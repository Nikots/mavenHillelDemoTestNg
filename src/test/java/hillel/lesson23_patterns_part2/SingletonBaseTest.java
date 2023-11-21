package hillel.lesson23_patterns_part2;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class SingletonBaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = Driver.getDriver();
        driver.manage().window().setSize(new Dimension(1440, 1100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeBrowser();
    }

}
