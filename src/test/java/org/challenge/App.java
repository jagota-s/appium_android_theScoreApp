package org.challenge;

import PageObjects.TeamPage;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import PageObjects.FavoritesPage;
import PageObjects.InitialSetupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class App extends BaseTest
{
    @Test
    public void appium() throws MalformedURLException, InterruptedException {
        InitialSetupPage initialSetupPage = new InitialSetupPage(driver);
        initialSetupPage.clickGetStartedButton();
        initialSetupPage.selectLeague("NBA Basketball");
        initialSetupPage.handleLocationPopup();
        initialSetupPage.selectTeam("Toronto Raptors"); //todo
        initialSetupPage.clickDoneButton();


        FavoritesPage favoritesPage = new FavoritesPage(driver);
        favoritesPage.verifyFavoritesPageDisplayed();
        favoritesPage.clickSelectedTeam();


        TeamPage teamPage = new TeamPage(driver);
        Assert.assertEquals(teamPage.getSelectedTeamName(), "Toronto Raptors");
        teamPage.selectInfoTab();
        List<WebElement> infoTextValues = teamPage.locationInfo();
        Assert.assertEquals(infoTextValues.get(0).getText(), "Toronto");
        Assert.assertEquals(infoTextValues.get(1).getText(), "Scotiabank Arena");
        teamPage.goBack();


        favoritesPage.verifyFavoritesPageDisplayed();
    }
}
