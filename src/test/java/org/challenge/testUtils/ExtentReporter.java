package org.challenge.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.lang.reflect.Method;

public class ExtentReporter {
    static ExtentReports extent;

    public static ExtentReports getReporterObject() {

        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Android Test Report");
        reporter.config().setDocumentTitle("Test Results");


        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Sumit Jagota");
        return extent;
    }
}