package hillel.ui.pages.lesson23.components;

import hillel.ui.pages.BasePage;
import hillel.ui.pages.lesson23.CatalogPage;
import hillel.ui.pages.lesson23.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderComponent extends BasePage {
    private WebElement searchInput;
    private WebElement icon;
    private WebElement catalog;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public CatalogPage openCatalog() {
        catalog.click();
        return new CatalogPage(driver);
    }

    public MainPage openMainPage() {
        icon.click();
        return new MainPage(driver);
    }

    public void executeSearch(String search) {
        //todo implement
    }
}
