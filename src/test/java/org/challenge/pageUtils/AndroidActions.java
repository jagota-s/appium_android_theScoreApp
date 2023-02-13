package org.challenge.pageUtils;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.challenge.testUtils.ExtentReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class AndroidActions {

    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
    }

    public void swipe(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", direction,
                "percent", 1
        ));
    }

    public void longClick(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId()
        ));
    }

    public void doubleClick(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId()
        ));
    }

    public void singleClick(WebElement ele) {
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId()
        ));
    }

    public boolean scroll(WebElement ele, String direction, int percent) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", direction,
                "percent", percent
        ));
    }

    public void pressBack(AndroidKey key) {
        driver.pressKey(new KeyEvent(key));
    }

    public void waitForElement(AndroidDriver driver, WebElement element) {
        FluentWait<AndroidDriver> wait = new FluentWait<AndroidDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(d -> element.isDisplayed());
        } catch (Exception e) {
            // ExtentReporter.getTest().log(Status.INFO, "");
            System.out.println(e.getMessage());
        }
    }



}
