package hillel.pages;

import hillel.pages.lesson23.components.HeaderComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public final WebDriver driver;
    public Actions actions;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    public HeaderComponent header;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
        this.header = new HeaderComponent(driver);
    }

    public void clickButton(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
