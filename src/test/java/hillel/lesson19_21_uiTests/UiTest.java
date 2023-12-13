package hillel.lesson19_21_uiTests;

import hillel.listeners.UiTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static hillel.utils.WebDriverProvider.getDriver;

@Listeners(UiTestListener.class)
public class UiTest {

    @Test
    public void selectFromMenu() {
        WebDriver driver = getDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 1");
    }

    @Test
    public void iframeCheck() {
        WebDriver driver = getDriver();

        driver.get("https://the-internet.herokuapp.com/iframe");
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);

        WebElement field = driver.findElement(By.xpath("//*[@id='tinymce']/p"));
        field.sendKeys("java qa auto");

        driver.switchTo().defaultContent();

        WebElement mainPageElement = driver.findElement(By.linkText("Elemental Selenium"));

    }

}
