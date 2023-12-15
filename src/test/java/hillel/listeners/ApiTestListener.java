package hillel.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ApiTestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(ApiTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: " + result.getMethod().getMethodName());
//        logger.debug("It is a debug logger.");
//        logger.error("It is an error logger.");
//        logger.fatal("It is an fatal logger.");
//        logger.info("It is a info logger.");
//        logger.trace("It is a trace logger.");
//        logger.warn("It is a warn logger.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed successfully: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed with error: " + result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Тест skipped: " + result.getMethod().getMethodName());
    }
}
