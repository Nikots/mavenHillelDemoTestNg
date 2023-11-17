package hillel.lesson22_pom;

import hillel.pages.LoginPage;
import hillel.pages.MainPage;
import hillel.pages.SecurePage;
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
