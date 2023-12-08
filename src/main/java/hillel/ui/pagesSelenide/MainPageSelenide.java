package hillel.ui.pagesSelenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPageSelenide {

    SelenideElement authFormLink = $(By.linkText("Form Authentication"));

    public LoginPageSelenide openLoginPage() {
        authFormLink.click();
        return new LoginPageSelenide();
    }
}
