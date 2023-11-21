package hillel.lesson23_patterns_part2;

import hillel.pages.lesson23.CatalogPage;
import hillel.pages.lesson23.MainPage;
import hillel.utils.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComponentObjTest extends BaseUiTest {

    @Test
    public void componentTest() {
        MainPage mainPage = new MainPage(driver);

        CatalogPage catalogPage = mainPage.header.openCatalog();
        catalogPage.selectCategory("popular");

        mainPage = catalogPage.header.openMainPage();

        mainPage.header.executeSearch("book");

        Assert.assertEquals(mainPage.getProductPrice(1), "100");

    }
}
