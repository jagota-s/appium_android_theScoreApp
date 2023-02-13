package org.challenge.testUtils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
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

    private AndroidDriver driver;
    private String ipAddress;
    private String port;
    private String deviceName;
    private String runTimeEnv;
    private static final String USER_DIR = "user.dir";

    @BeforeClass
    public void configureAppium() throws IOException {
        //AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//sjagota//.nvm//versions//node//v16.19.0//lib//node_modules//appium//build//lib//main.js"))
        //       .withIPAddress("0.0.0.0").usingPort(4723).build();
        // service.start();
        System.out.println("in configure");
        loadProperties();
        //runTimeEnv = "cloud";
        if (runTimeEnv.equals("localEmulator")) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(deviceName);
            options.setApp(System.getProperty(USER_DIR) + "//src//test//resources//theScore.apk");
            driver = new AndroidDriver(new URL(ipAddress + ":" + port), options);
        } else if (runTimeEnv.equals("cloud")) {
            MutableCapabilities caps = new MutableCapabilities();
            caps.setCapability("platformName","Android");
            caps.setCapability("appium:deviceName","Android GoogleAPI Emulator");
            caps.setCapability("appium:deviceOrientation", "portrait");
            caps.setCapability("appium:platformVersion","12.0");
            caps.setCapability("appium:automationName", "UiAutomator2");
            caps.setCapability("appium:app", "storage:filename=theScore.apk");
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("build", "appium-build-BF1V8");
            sauceOptions.setCapability("name", "theScore");
            caps.setCapability("sauce:options", sauceOptions);

             URL url = new URL("https://oauth-sumit.scotts-215c3:5dce9506-a0e5-4d37-bbd5-dc1e50924cc5@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new AndroidDriver(url, caps);
        }
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
        String filePath = System.getProperty(USER_DIR) + "//testData//" + className + ".json";
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty(USER_DIR) + "/reports//" + testCaseName + ".png";
        System.out.println("path: :" + path);
        String destinationFile = System.getProperty(USER_DIR) + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

    public void loadProperties() throws IOException {
        Properties pro = new Properties();
        FileInputStream fi = new FileInputStream(System.getProperty(USER_DIR) + "//data.properties");
        pro.load(fi);
        ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : (String) pro.get("ipAddress");
        port = System.getProperty("port") != null ? System.getProperty("port") : (String) pro.get("port");
        deviceName = System.getProperty("AndroidDeviceName") != null ? System.getProperty("AndroidDeviceName") : (String) pro.get("AndroidDeviceName");
        runTimeEnv = System.getProperty("runTimeEnv") != null ? System.getProperty("runTimeEnv") : "localEmulator";
    }

}
