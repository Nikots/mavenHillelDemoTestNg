package hillel.lesson24_files;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertiesTest {
    private static ConfigLoader config;

    @BeforeClass
    public void setup() {
        // Читання середовища з системних властивостей або змінних середовища
        String environment = System.getProperty("env", "dev"); // Значення за замовчуванням - "dev"
        config = new ConfigLoader(environment);
    }

    @Test
    public void testFeature() {
        String baseUrl = config.getProperty("baseUrl");
        // Виконання тесту з використанням baseUrl та інших налаштувань
        System.out.println(baseUrl);
    }
}
