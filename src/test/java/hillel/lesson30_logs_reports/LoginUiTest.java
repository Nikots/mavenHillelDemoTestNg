package hillel.lesson30_logs_reports;


import hillel.listeners.UiTestListener;
import hillel.ui.pages.lesson22.LoginPage;
import hillel.ui.pages.lesson22.MainPage;
import hillel.ui.pages.lesson22.SecurePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static hillel.utils.WebDriverProvider.getDriver;

//@Listeners(UiTestListener.class)
@Epic("Epic #1")
@Story("Story #1")
public class LoginUiTest {

    @Test
    @TmsLink("002")
    @Description("Test login V1")
    public void testLogin_failed() {
        WebDriver driver = getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tomsmith2", "SuperSecretPassword!");

        SecurePage securePage = new SecurePage(driver);
        String secureHeader = securePage.getHeaderText();
        Assert.assertEquals(secureHeader, "Secure Area");
    }

    @Test
    @TmsLink("001")
    @Description("Test login V2")
    public void testLogin_Passed() {
        WebDriver driver = getDriver();

        String secureHeader = new MainPage(driver)
                .openLoginPage()
                .setUserName("tomsmith")
                .setPassword("SuperSecretPassword!")
                .submit()
                .getHeaderText();

        Assert.assertEquals(secureHeader, "Secure Area");
    }
}