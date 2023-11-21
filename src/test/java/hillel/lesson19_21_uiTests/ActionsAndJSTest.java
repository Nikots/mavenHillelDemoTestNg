package hillel.lesson19_21_uiTests;

import hillel.utils.BaseUiTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

//Lesson #21
public class ActionsAndJSTest extends BaseUiTest {

    @Test
    public void selectFromMenu() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 1");
    }

    @Test
    public void dragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement elementA = driver.findElement(By.id("column-a"));
        WebElement elementB = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(elementA)
                .moveToElement(elementB)
                .release(elementB)
                .build()
                .perform();
    }

    @Test
    public void downloadUsingMenu() {
        driver.get("https://the-internet.herokuapp.com/jqueryui/menu#");

        WebElement enabled = driver.findElement(By.xpath("//a[text()='Enabled']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        Actions actions = new Actions(driver);
        actions.moveToElement(enabled).perform();

        WebElement downloads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Downloads']")));
        actions.moveToElement(downloads).perform();

        WebElement csv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='CSV']")));
        actions.moveToElement(csv).click().perform();
    }

    @Test
    public void selectFromMenuJS() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = driver.findElement(By.id("dropdown"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement option = (WebElement) js.executeScript("return arguments[0].querySelector(\"option[value='2']\");", dropdown);
        js.executeScript("arguments[0].selected='selected'", option);

    }

    @Test
    public void selectDateForCard() {
        driver.get("https://demos.telerik.com/aspnet-ajax/monthyearpicker/overview/defaultcs.aspx");

        WebElement input = driver.findElement(By.id("ctl00_ContentPlaceholder1_RadMonthYearPicker1_dateInput"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String date = "12/2030";

        js.executeScript("arguments[0].value='" + date + "';", input);
        Assert.assertEquals(input.getAttribute("value"), date);
    }

    @Test
    void selectDatesInCalendar() {
        driver.get("https://demos.telerik.com/aspnet-ajax/datepicker/overview/defaultcs.aspx");

        WebElement dateFromInput = driver.findElement(By.id("ctl00_ContentPlaceholder1_RadDatePicker1_dateInput"));
        WebElement dateToInput = driver.findElement(By.id("ctl00_ContentPlaceholder1_RadDatePicker2_dateInput"));

        String dateFromValue = "21/12/2020";
        String dateToValue = "01/01/2026";

        // Використання JavascriptExecutor для зміни значення поля
        // варіант1 виконання JS скрипта
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // варіант2 виконання JS скрипта
        // js.executeScript("arguments[0].value='" + dateFromValue + "'; arguments[1].value='" + dateToValue + "';", dateFromInput, dateToInput);

        js.executeScript("arguments[0].value=arguments[1]; arguments[2].value=arguments[3];", dateFromInput, dateFromValue, dateToInput, dateToValue);

        Assert.assertEquals(dateFromValue, dateFromInput.getAttribute("value"));
        Assert.assertEquals(dateToValue, dateToInput.getAttribute("value"));
    }

    @Test
    void waitPageLoadedAndScroll() {
        driver.get("https://demos.telerik.com/aspnet-ajax/datepicker/overview/defaultcs.aspx");
        ExpectedCondition<Boolean> pageLoadCondition =
                driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

        // wait until page is loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(pageLoadCondition);

        // scroll till the end of page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
    }

    @Test
    public void getShadowElements() {
        driver.get("http://watir.com/examples/shadow_dom.html");
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));
        WebElement info = shadowHost.getShadowRoot().findElement(By.className("info"));

        // Attention: Locator By.id("nested_shadow_host") does not work
        // BUT By.cssSelector("#nested_shadow_host") it works correctly
        WebElement nestedText = shadowHost.getShadowRoot().findElement(By.cssSelector("#nested_shadow_host"));

        System.out.println(info.getText());
        System.out.println(nestedText.getText());
    }

    @Test
    public void alertCheck() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement button1 = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        button1.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.switchTo().defaultContent();
        WebElement button2 = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        button2.click();

    }

    @Test
    public void iframeCheck() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);

        WebElement field = driver.findElement(By.xpath("//*[@id='tinymce']/p"));
        field.sendKeys("java qa auto");

        driver.switchTo().defaultContent();

        WebElement mainPageElement = driver.findElement(By.linkText("Elemental Selenium"));

    }

}
