package hillel.ui.pages.lesson23;

import hillel.ui.pages.BasePage;
import hillel.ui.pages.lesson23.components.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends BasePage {
    public List<WebElement> newBooks;

   // public WebElement catalog;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice(int num) {
        return new ProductComponent(newBooks.get(num)).getPrice();
    }

}
