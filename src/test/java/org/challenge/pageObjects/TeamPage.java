package org.challenge.pageObjects;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.challenge.pageUtils.AndroidActions;
import org.challenge.testUtils.ExtentReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class TeamPage extends AndroidActions {

    private AndroidDriver driver;
    @AndroidFindBy(id = "com.fivemobile.thescore:id/team_name")
    private WebElement teamName;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Info']")
    private WebElement infoTab;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/value")
    private List<WebElement> infoTextValues;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Schedule']")
    private WebElement scheduleTab;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@content-desc,'Player Stats')]")
    private WebElement playerStatsTab;

    public TeamPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getTeamName() {
        return teamName.getText();
    }

    public void selectInfoTab() {
        ExtentReporter.getTest().log(Status.INFO, "Navigating to the 'Info' tab");
        this.swipe(scheduleTab, "left");
        this.swipe(playerStatsTab, "left");
        infoTab.click();
    }

    public List<WebElement> getInfoTabAttributes() {
        ExtentReporter.getTest().log(Status.INFO, "Extracting the 'Info tab' details");
        return infoTextValues;
    }

    public void goBack() {
        ExtentReporter.getTest().log(Status.INFO, "Press 'Back' button");
        this.pressBack(AndroidKey.BACK);
    }

    public void validateSelectedTeamName(String selectedTeamName) {
        String displayedName = getTeamName();
        boolean isSame = selectedTeamName.equals(displayedName);
        if (isSame) {
            ExtentReporter.getTest().log(Status.PASS, "Assertion: Correct Team page is displayed. Displayed value: "+displayedName);
        } else {
            ExtentReporter.getTest().log(Status.FAIL, "Assertion: InCorrect Team page is displayed. Displayed value: "+displayedName);
        }
        Assert.assertTrue(isSame);
    }

    public void validateInfoTabDetails(String location, String arena) {
        List<WebElement> attributes = getInfoTabAttributes();
        boolean isLocationCorrect = attributes.get(0).getText().equals(location);
        if (isLocationCorrect) {
            ExtentReporter.getTest().log(Status.PASS, "Assertion: Correct Team location is displayed. Displayed Value: " + attributes.get(0).getText());
        } else {
            ExtentReporter.getTest().log(Status.FAIL, "Assertion: InCorrect Team location is displayed. Displayed Value: " + attributes.get(0).getText());
        }
        Assert.assertTrue(isLocationCorrect);

        boolean isArenaCorrect = attributes.get(1).getText().equals(arena);
        if (isArenaCorrect) {
            ExtentReporter.getTest().log(Status.PASS, "Assertion: Correct Team arena is displayed. Displayed Value: " + attributes.get(1).getText());
        } else {
            ExtentReporter.getTest().log(Status.FAIL, "Assertion: InCorrect Team arena is displayed. Displayed Value: " + attributes.get(1).getText());
        }
        Assert.assertTrue(isArenaCorrect);
    }

}
