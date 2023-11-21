package hillel.pages.lesson22;

import hillel.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurePage extends BasePage {

    @FindBy(tagName = "h2")
    WebElement header;

    public SecurePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {
        return header.getText();
    }
}
