package org.challenge.pageObjects;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.challenge.pageUtils.AndroidActions;
import org.challenge.testUtils.ExtentReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WelcomePage extends AndroidActions {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/action_button_text")
    private WebElement getGetStartedButton;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_primary")
    private WebElement league_continue_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/img_location")
    private WebElement location_popup;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_disallow")
    private WebElement deny_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/search_bar_placeholder")
    private WebElement searchBar;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/search_src_text")
    private WebElement searchText;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/action_button_text")
    private WebElement continue_teamButton;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/action_button_text")
    private WebElement done_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/button_positive")
    private WebElement download_button;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/dismiss_modal")
    private WebElement dismiss_button;

    @AndroidFindBy(xpath = "//*[@resource-id='com.fivemobile.thescore:id/txt_name']")
    private List<WebElement> teams;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/accept_button")
    private WebElement ccpa_continue_button;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Privacy Policy\"]")
    private WebElement ccpa_privacy_policy;
    public WelcomePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickGetStartedButton() {
        ExtentReporter.getTest().log(Status.INFO, "Clicking 'Get Started' button");
        getGetStartedButton.click();
    }

    public boolean selectLeague(String leagueName) {
        ExtentReporter.getTest().log(Status.INFO, "Selecting the league: " + leagueName);
        WebElement league = driver.findElement(By.xpath("//*[@text='" + leagueName + "']"));
        if (league != null) {
            league.click();
            league_continue_button.click();
            return true;
        } else {
            return false;
        }
    }

    public void handleLocationPopup() {

        if (location_popup != null && location_popup.isDisplayed()) {
            ExtentReporter.getTest().log(Status.INFO, "Dismiss the location popup");
            deny_button.click();
        }
    }

    public boolean selectTeam(String teamName) {
        ExtentReporter.getTest().log(Status.INFO, "Selecting the Team: " + teamName);
        searchBar.click();
        searchText.sendKeys(teamName);
        // Thread.sleep(2000); // todo
        if (teams.size() > 0) {
            driver.hideKeyboard();
            teams.get(0).click();
            continue_teamButton.click();
            return true;
        } else {
            return false;
        }
    }

    public void clickDoneButton() {
        ExtentReporter.getTest().log(Status.INFO, "Clicking on 'Done' button.");
        waitForElement(driver, done_button);
        done_button.click();
        handleAdModalPopUp();
    }

    private void handleAdModalPopUp() {
        waitForElement(driver, download_button);
        try {
            if (download_button.isDisplayed()) {
                ExtentReporter.getTest().log(Status.INFO, "Dismiss the 'Download app' popup");
                dismiss_button.click();
            }
        } catch (Exception e) {
            ExtentReporter.getTest().log(Status.INFO, "theBet download popup not displayed");
        }
    }

    public void handleCcpaModal() {

        try {
            waitForElement(driver, ccpa_continue_button);
            if(ccpa_privacy_policy.isDisplayed()) {
                ccpa_continue_button.click();
                clickGetStartedButton();
            }
        } catch (Exception e) {
            ExtentReporter.getTest().log(Status.INFO, "CCPA screen not displayed");
        }
    }


}
