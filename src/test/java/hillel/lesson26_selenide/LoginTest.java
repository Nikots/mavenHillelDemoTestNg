package hillel.lesson26_selenide;

import hillel.ui.pagesSelenide.LoginPageSelenide;
import hillel.ui.pagesSelenide.MainPageSelenide;
import hillel.ui.pagesSelenide.SecurePageSelenide;
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
