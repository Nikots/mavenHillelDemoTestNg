package hillel.lesson19_21_uiTests;

import hillel.utils.BaseUiTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

//Lesson #20
public class WaitsTest extends BaseUiTest {

    @Test
    public void testSleep() throws InterruptedException {
        String url = "https://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);

        WebElement removeBtn = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeBtn.click();

        Thread.sleep(3000);

        WebElement text = driver.findElement(By.id("message"));
        Assert.assertEquals(text.getText(), "It's gone!");
    }

    @Test
    public void testImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);

        WebElement removeBtn = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeBtn.click();

        WebElement text = driver.findElement(By.id("message"));
        Assert.assertEquals(text.getText(), "It's gone!");
    }

    @Test
    public void testExplicitWaitNoSuchElement() {
        String url = "https://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);

        WebElement removeBtn = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeBtn.click();

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement text = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("message"))));

        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(text.getText(), "It's gone!");
    }

    @Test
    public void testWithActions() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/overview/defaultcs.aspx");

        By locator = By.xpath("//button[@type='submit']");

        WebElement button = driver.findElement(locator);

        button.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(button)
                .click()
                .build()
                .perform();
    }

    @Test
    public void testAjaxManager() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxmanager/overview/defaultcs.aspx");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement date = driver.findElement(By.xpath("//*[@title='Sunday, May 28, 2006']"));
        date.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_EmailGrid")));

        List<WebElement> emails = driver.findElements(By.xpath("//table[@class='rgMasterTable']/tbody/tr"));

        WebElement emailFromBrannigan = emails.stream().filter(email -> email.getText().contains("Brannigan")).findFirst().get();

        emailFromBrannigan.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_MessageBody")));

        String message = driver.findElement(By.id("ctl00_ContentPlaceholder1_MessageBody")).getText();
        System.out.println(message);

        Assert.assertTrue(message.contains("out-of-date information"));
    }

    @Test
    public void testFluentWait() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        String url = "https://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);

        WebElement removeBtn = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeBtn.click();

        WebElement text = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(text.getText(), "It's gone!");

    }


}
