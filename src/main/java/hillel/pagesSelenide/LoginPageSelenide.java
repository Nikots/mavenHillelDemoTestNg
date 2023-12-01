package hillel.pagesSelenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageSelenide {

    SelenideElement usernameField = $("#username");
    SelenideElement passwordField = $("#password");
    SelenideElement submitBtn = $("button[type='submit']");

    public LoginPageSelenide setPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPageSelenide setLogin(String login) {
        usernameField.setValue(login);
        return this;
    }

    public void submit() {
        submitBtn.click();
    }

    public void login(String login, String password) {
        setLogin(login)
                .setPassword(password)
                .submit();
    }
}
