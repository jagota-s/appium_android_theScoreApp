package org.challenge.testUtils;

import bsh.util.JConsole;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BaseTest {

    public AndroidDriver driver;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        //AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//sjagota//.nvm//versions//node//v16.19.0//lib//node_modules//appium//build//lib//main.js"))
        //       .withIPAddress("0.0.0.0").usingPort(4723).build();
        // service.start();
        System.out.println("in configure");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel5");
        options.setApp("//Users//sjagota//Documents//Personal//theScore//qaChallenge//theScore//src//test//resources//theScore.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
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

    public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException
    {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
        System.out.println("path: :" + path);
        String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
