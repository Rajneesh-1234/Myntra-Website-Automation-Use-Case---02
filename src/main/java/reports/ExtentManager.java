package reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.DriverFactory;
import utils.ScreenshotUtil;

public class ExtentManager implements ITestListener {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static void createReport() {

        if (extent == null) {

        

            String reportPath = System.getProperty("user.dir")
                    + "/reports/AutomationReport_" + ".html";

            sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("Automation Testing Report");
            sparkReporter.config().setReportName("Functional Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "Hackathon Automation");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
        }
    }

    @Override
    public void onStart(ITestContext context) {
        createReport();  
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(
                result.getTestClass().getName() + " - " + result.getName());

        test.set(extentTest);

    
        test.get().assignCategory(contextName(result));
        test.get().info("Test Execution Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed Successfully");

        attachScreenshot(result, "Success Screenshot");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        attachScreenshot(result, "Failure Screenshot");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");

        attachScreenshot(result, "Skipped Screenshot");
    }

    private void attachScreenshot(ITestResult result, String label) {

        try {
            String path = ScreenshotUtil.captureScreenshot(
                    DriverFactory.getDriver(),
                    result.getName());

            test.get().addScreenCaptureFromPath(path, label);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    private String contextName(ITestResult result) {
        return result.getTestContext().getName();
    }
}