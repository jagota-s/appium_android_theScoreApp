package org.challenge;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    AndroidDriver driver;
    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        //AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//sjagota//.nvm//versions//node//v16.19.0//lib//node_modules//appium//build//lib//main.js"))
        //       .withIPAddress("0.0.0.0").usingPort(4723).build();
        // service.start();
        System.out.println( "in configure" );
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel5");
        options.setApp("//Users//sjagota//Documents//Personal//theScore//qaChallenge//theScore//src//test//resources//theScore.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterClass
    public void tearDown() {
        System.out.println( "In tear down" );
       // driver.quit();
        //  service.stop();

    }
}
