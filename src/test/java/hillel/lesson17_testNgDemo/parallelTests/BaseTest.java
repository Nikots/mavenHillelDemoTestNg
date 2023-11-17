package hillel.lesson17_testNgDemo.parallelTests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeSuite
    @Parameters({"url", "browser"})
    public void setup(String url, String browser) {
        System.out.println("browser started: " + browser);
        System.out.println("url opened: " + url);
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("browser teared down");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after test");
    }
}
