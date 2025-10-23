package Listeners;

import Utils.AllureCommandRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

public class TestListener implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("STARTING TEST: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASSED: {}", result.getMethod().getMethodName());
        // attach artifacts for passed (optional);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAILED: {} - {}", result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("SKIPPED: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test suite started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test suite finished: {}", context.getName());
        // generate Allure report (tuỳ bạn có muốn auto gen sau mỗi suite hay gen trong CI)
        try {
            AllureCommandRunner.runCommand("allure generate  --single-file target/allure-results -o target/allure-report");
        } catch (Exception e) {
            log.error("Fail to generate Allure report: {}", e.getMessage(), e);
        }
    }

    // other ITestListener methods can be left default (no-op)
}
