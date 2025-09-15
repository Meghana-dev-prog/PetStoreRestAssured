package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            // Generate timestamp
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("API Automation Report");
            spark.config().setReportName("PetStore User API Test Report - " + timeStamp);
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info
            extent.setSystemInfo("Project", "PetStore API");
            extent.setSystemInfo("Tester", "QA Team");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(Status.FAIL, "Test Failed");
            extentTest.log(Status.FAIL, result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            test.get().log(Status.SKIP, "Test Skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // write report to file
    }
}

