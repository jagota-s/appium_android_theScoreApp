package org.challenge.testUtils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public AndroidDriver driver;
    private String ipAddress;
    private String port;
    private String deviceName;

    @BeforeClass
    public void configureAppium() throws IOException {
        //AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//sjagota//.nvm//versions//node//v16.19.0//lib//node_modules//appium//build//lib//main.js"))
        //       .withIPAddress("0.0.0.0").usingPort(4723).build();
        // service.start();
        System.out.println("in configure");
        loadProperties();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(System.getProperty("user.dir") + "//src//test//resources//theScore.apk");

        //driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
        driver = new AndroidDriver(new URL(ipAddress + ":" + port), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown() {
        System.out.println("In tear down");
        driver.quit();
        //  service.stop();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public List<HashMap<String, String>> getJsonData(String className) throws IOException, IOException {
        String filePath = System.getProperty("user.dir") + "//testData//" + className + ".json";
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/reports//" + testCaseName + ".png";
        System.out.println("path: :" + path);
        String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

    public void loadProperties() throws IOException {
        Properties pro = new Properties();
        FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "//data.properties");
        pro.load(fi);
        ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : (String) pro.get("ipAddress");
        port = System.getProperty("port") != null ? System.getProperty("port") : (String) pro.get("port");
        deviceName = System.getProperty("AndroidDeviceName") != null ? System.getProperty("AndroidDeviceName") : (String) pro.get("AndroidDeviceName");
    }

}
