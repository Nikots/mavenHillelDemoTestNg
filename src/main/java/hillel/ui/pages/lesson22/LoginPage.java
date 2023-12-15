package hillel.ui.pages.lesson22;

import hillel.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BasePage {
    @FindBy(id = "username" )
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = " xpath to find elements")
    List<WebElement> cells;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage setUserName(String userName) {
        logger.info("setUserName");
        setText(usernameField, userName);
        return this;
    }

    public LoginPage setPassword(String password) {
        logger.info("setPassword");
        setText(passwordField, password);
        return this;
    }

    public SecurePage submit() {
        clickButton(submitBtn);
        return new SecurePage(driver);
    }

    @Step("Login to system")
    public SecurePage login(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        submit();
        return new SecurePage(driver);
    }

    public LoginPage loginWithInvalidCreds(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        submit();
        return new LoginPage(driver);
    }
}
