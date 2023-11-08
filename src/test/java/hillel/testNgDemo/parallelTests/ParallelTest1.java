package hillel.testNgDemo.parallelTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParallelTest1 extends BaseTest {

    @BeforeClass //@BeforeAll JUnit
    public void beforeClass() {
        System.out.println("before class1");
    }

    @AfterClass //@AfterAll JUnit
    public void afterClass() {
        System.out.println("after class1");
    }

    @Test
    public void test1() throws InterruptedException {
        Thread.sleep(5000);
    }
}
