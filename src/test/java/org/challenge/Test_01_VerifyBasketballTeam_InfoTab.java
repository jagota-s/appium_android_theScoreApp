package org.challenge;

import PageObjects.TeamPage;
import PageObjects.FavoritesPage;
import PageObjects.InitialSetupPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class Test_01_VerifyBasketballTeam_InfoTab extends BaseTest
{
    @Test(dataProvider="getData")
    public void appium(HashMap<String,String> input) throws MalformedURLException, InterruptedException {
        InitialSetupPage initialSetupPage = new InitialSetupPage(driver);
        initialSetupPage.clickGetStartedButton();
        initialSetupPage.selectLeague(input.get("leagueName"));
        initialSetupPage.handleLocationPopup();
        initialSetupPage.selectTeam(input.get("teamName")); //todo
        initialSetupPage.clickDoneButton();


        FavoritesPage favoritesPage = new FavoritesPage(driver);
        favoritesPage.verifyFavoritesPageDisplayed();
        favoritesPage.clickSelectedTeam();


        TeamPage teamPage = new TeamPage(driver);
        Assert.assertEquals(teamPage.getSelectedTeamName(), input.get("teamName"));
        teamPage.selectInfoTab();
        List<WebElement> infoTextValues = teamPage.locationInfo();
        Assert.assertEquals(infoTextValues.get(0).getText(), input.get("teamLocation"));
        Assert.assertEquals(infoTextValues.get(1).getText(), input.get("teamArena"));
        teamPage.goBack();


        favoritesPage.verifyFavoritesPageDisplayed();
    }

    @DataProvider
    public Object[][] getData() throws IOException
    {
        List<HashMap<String, String>>	data =getJsonData(this.getClass().getSimpleName());
        return new Object[][] { {data.get(0)},{data.get(1)}  };
    }

}
