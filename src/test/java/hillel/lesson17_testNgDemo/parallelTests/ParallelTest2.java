package hillel.lesson17_testNgDemo.parallelTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParallelTest2 extends BaseTest {
    @BeforeClass //@BeforeAll JUnit
    public void beforeClass() {
        System.out.println("before class2");
    }

    @AfterClass //@AfterAll JUnit
    public void afterClass() {
        System.out.println("after class2");
    }
    @Test
    public void test2() throws InterruptedException {
        Thread.sleep(5000);
    }
}
