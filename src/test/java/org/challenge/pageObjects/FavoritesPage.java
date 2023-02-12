package org.challenge.pageObjects;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.challenge.pageUtils.AndroidActions;
import org.challenge.testUtils.ExtentReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FavoritesPage extends AndroidActions {
    AndroidDriver driver;

    public FavoritesPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Favorites']")
    private WebElement favoriteTab;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/icon_team_logo")
    private WebElement selectedTeam;


    public void verifyFavoritesPageDisplayed() {
        String focussed = favoriteTab.getAttribute("focusable");
        boolean isDisplayed = focussed.equals("true");
        if (isDisplayed) {
            ExtentReporter.getTest().log(Status.PASS, "Assertion: Favorite page displayed");
        } else {
            ExtentReporter.getTest().log(Status.FAIL, "Assertion: Favorite page is not displayed");
        }
        Assert.assertTrue(isDisplayed);
    }

    public void clickSelectedTeam() {
        ExtentReporter.getTest().log(Status.INFO, "Clicking on the selected team logo");
        selectedTeam.click();
    }

}
