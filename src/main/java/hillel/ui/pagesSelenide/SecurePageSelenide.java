package hillel.ui.pagesSelenide;

import static com.codeborne.selenide.Selenide.$;

public class SecurePageSelenide {
    public String getHeaderText() {
        return $("h2").text();
    }
}
