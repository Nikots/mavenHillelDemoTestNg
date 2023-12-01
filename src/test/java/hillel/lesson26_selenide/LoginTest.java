package hillel.lesson26_selenide;

import hillel.pagesSelenide.LoginPageSelenide;
import hillel.pagesSelenide.MainPageSelenide;
import hillel.pagesSelenide.SecurePageSelenide;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseSelenideTest {

    @Test
    public void loginSelenide() {
        MainPageSelenide mainPage = openMainPage();

        LoginPageSelenide loginPage = mainPage.openLoginPage();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        SecurePageSelenide securePage = new SecurePageSelenide();
        Assert.assertEquals(securePage.getHeaderText(), "Secure Area");
    }
}
