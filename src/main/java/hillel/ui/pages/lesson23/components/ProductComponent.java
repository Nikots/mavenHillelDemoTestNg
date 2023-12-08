package hillel.ui.pages.lesson23.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductComponent {

    private WebElement parent;

    public ProductComponent(WebElement parent) {
        this.parent = parent;
        PageFactory.initElements(parent, this);
    }

    public String getName() {
        return  parent.findElement(By.id("name")).getText();
    }

    public String getPrice() {
        return  parent.findElement(By.id("price")).getText();
    }
}
