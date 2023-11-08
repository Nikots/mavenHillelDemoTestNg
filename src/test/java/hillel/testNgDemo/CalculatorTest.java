package hillel.testNgDemo;

import hillel.Calculator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class CalculatorTest {
    Calculator calculator = new Calculator();

    @BeforeClass //@BeforeAll JUnit
    public void beforeClass() {
        System.out.println("before class");
    }

    @AfterClass //@AfterAll JUnit
    public void afterClass() {
        System.out.println("after class");
    }

//    @BeforeMethod //@BeforeEach JUnit
//    public void beforeMethod() {
//        System.out.println("before method");
//    }
//
//    @AfterMethod //@AfterEach JUnit
//    public void afterMethod() {
//        System.out.println("after method");
//    }
//
//    @BeforeGroups(groups = "qa")
//    public void beforeGroup() {
//        System.out.println("before group 'qa'");
//    }
//
//    @AfterGroups(groups = {"qa"})
//    public void afterGroup() {
//        System.out.println("after group 'qa'");
//    }

    @Test(groups = {"addition", "qa"})
    public void checkAddition() {
        assertEquals(10, calculator.add(3, 7));
        System.out.println("test1");
    }

    @Test(expectedExceptions = ArithmeticException.class, dependsOnGroups = "qa")
    public void checkDivisionByZero() {
        System.out.println("test2");
        //assertThrows(ArithmeticException.class, () -> calculator.divide(3, 0));
        int result = calculator.divide(3, 0);
    }

    @Test(groups = {"qa"})
    public void testGroup() {
        System.out.println("test3");
        assertTrue(false);
    }


}
