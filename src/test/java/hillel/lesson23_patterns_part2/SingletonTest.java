package hillel.lesson23_patterns_part2;

import org.testng.annotations.Test;

public class SingletonTest extends SingletonBaseTest {

    @Test
    public void test1() {
        driver.get("https://translate.google.com.ua/");
    }

    @Test
    public void test2() {
        driver.get("https://makeup.com.ua/ua/");
    }

    @Test
    public void test3() {
        driver.get("https://epicentrk.ua/");
    }
}
