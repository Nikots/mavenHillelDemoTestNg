package hillel.pages.lesson23;

import hillel.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {
    private WebElement title;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return title.getText();
    }


}
