package hillel.lesson19_21_uiTests;

import hillel.utils.BaseUiTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

//Lesson #19
public class FirstUIDemoTest extends BaseUiTest {

    @Test
    public void testCheckbox() {
        //System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver");
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        checkbox1.click();

        Assert.assertTrue(checkbox1.isSelected(), "Checkbox1 is not selected");
    }

    @Test
    public void testInput() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement inputField = driver.findElement(By.id("target"));
        inputField.sendKeys("1");

        WebElement label = driver.findElement(By.id("result"));
        String color = label.getAttribute("style");
        Assert.assertEquals(color, "color: green;");

        Assert.assertEquals(label.getText(), "You entered: 1");

        inputField.clear();
    }


    @Test
    public void testChainOfFindElements() {
        driver.get("https://the-internet.herokuapp.com/status_codes");

        List<WebElement> statusCodes = driver.findElement(By.tagName("ul")).findElements(By.tagName("a"));

        statusCodes.forEach(el -> System.out.println(el.getText()));
    }

    @Test
    public void testLinkedInMenu() throws InterruptedException {
        driver.get("https://www.linkedin.com/");
        Thread.sleep(5000);

        WebElement meMenu = driver.findElement(By.xpath("//button[contains(@class, 'global-nav__primary-link-me-menu-trigger')]"));
        meMenu.click();

        WebElement viewProfileBtn = driver.findElement(By.linkText("View Profile"));
        viewProfileBtn.click();
        Thread.sleep(5000);
    }
}
