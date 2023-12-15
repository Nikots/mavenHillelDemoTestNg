package hillel.ui.pages.lesson22;

import hillel.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @FindBy(linkText = "Form Authentication")
    WebElement authFormLink;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        driver.get("https://the-internet.herokuapp.com/");
    }

    @Step("Open Login Page")
    public LoginPage openLoginPage() {
        clickButton(authFormLink);
        return new LoginPage(driver);
    }
}
