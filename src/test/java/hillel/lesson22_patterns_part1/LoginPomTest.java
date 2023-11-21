package hillel.lesson22_patterns_part1;

import hillel.pages.lesson22.LoginPage;
import hillel.pages.lesson22.MainPage;
import hillel.pages.lesson22.SecurePage;
import hillel.utils.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPomTest extends BaseUiTest {

    @Test
    public void loginTestV1() {
        MainPage mainPage = openMainPage();
        mainPage.openLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith", "SuperSecretPassword!");

        SecurePage securePage = new SecurePage(driver);
        String secureHeader = securePage.getHeaderText();
        Assert.assertEquals(secureHeader, "Secure Area");
    }

    @Test
    public void loginTestV2() {
        String secureHeader = openMainPage()
                .openLoginPage()
                .setUserName("tomsmith")
                .setPassword("SuperSecretPassword!")
                .submit()
                .getHeaderText();

        Assert.assertEquals(secureHeader, "Secure Area");
    }
}
