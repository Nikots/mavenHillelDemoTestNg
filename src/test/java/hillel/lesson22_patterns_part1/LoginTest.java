package hillel.lesson22_patterns_part1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest {

    @Test
    public void loginLogoutTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1440,1100));

        driver.get("https://the-internet.herokuapp.com/");

        driver.findElement(By.linkText("Form Authentication")).click();

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String secureHeader = driver.findElement(By.tagName("h2")).getText();
        assertEquals(secureHeader, "Secure Area");

        driver.quit();
    }
}
