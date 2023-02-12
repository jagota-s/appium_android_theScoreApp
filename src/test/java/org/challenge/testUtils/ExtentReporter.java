package org.challenge.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {

        if (extent == null) {
            String path = System.getProperty("user.dir") + "//reports//index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Android Test Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Sumit Jagota");

        }
        return extent;
    }

    public static void setTest(ExtentTest test) {
        ExtentReporter.test = test;
    }

    public static ExtentTest getTest() {
        return ExtentReporter.test;
    }

}