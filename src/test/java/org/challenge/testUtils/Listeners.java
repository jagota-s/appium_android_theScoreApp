package org.challenge.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporter.getInstance();
    AndroidDriver driver;


    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        ExtentReporter.setTest(test);
        test.log(Status.INFO, "Test Started in listener");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        try{
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
            test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver));
        }catch (Exception e) {
            test.log(Status.ERROR,"Error in screenshot utility "+ e.getMessage());
        }
   }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        test.log(Status.INFO, "Test Finished");
        extent.flush();
    }

}
