package hillel.uiTests_lesson19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

//Lesson #19
public class FirstUIDemoTest {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() {
        //System.setProperty("webdriver.chrome.driver", "/Users/ninakotsar/IdeaProjects/mavenHillelDemoTestNg/src/test/resources/chromedriver");
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        checkbox1.click();

        Assert.assertTrue(checkbox1.isSelected(), "Checkbox1 is not selected");
    }

    @Test
    public void test2() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement inputField = driver.findElement(By.id("target"));
        inputField.sendKeys("1");

        WebElement label = driver.findElement(By.id("result"));
        String color = label.getAttribute("style");
        Assert.assertEquals("color:green", color);

       // String text = label.getText();
        Assert.assertEquals("You entered: 1", label.getText());

        inputField.clear();
    }


    @Test
    public void test3() {
        driver.get("https://the-internet.herokuapp.com/status_codes");

        List<WebElement> statusCodes = driver.findElement(By.tagName("ul")).findElements(By.tagName("a"));

        statusCodes.forEach(el -> System.out.println(el.getText()));
    }

    @Test
    public void testLinkedIn() throws InterruptedException {
        driver.get("https://www.linkedin.com/");
        Thread.sleep(5000);

        WebElement meMenu = driver.findElement(By.xpath("//button[contains(@class, 'global-nav__primary-link-me-menu-trigger')]"));
        meMenu.click();

        WebElement viewProfileBtn = driver.findElement(By.linkText("View Profile"));
        viewProfileBtn.click();
        Thread.sleep(5000);
    }
}
