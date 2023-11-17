package hillel.lesson17_testNgDemo;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        System.out.println("Started " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Finished " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Failed " + result.getName());
    }
}
