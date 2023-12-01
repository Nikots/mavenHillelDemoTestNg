package hillel.lesson26_selenide;

import com.codeborne.selenide.Configuration;
import hillel.pagesSelenide.MainPageSelenide;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class BaseSelenideTest {

    @BeforeMethod
    public void setup() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1440x1100";
        Configuration.downloadsFolder = "foo/bar_downloads";
    }


    public MainPageSelenide openMainPage() {
        open("");
        return new MainPageSelenide();
    }
}
